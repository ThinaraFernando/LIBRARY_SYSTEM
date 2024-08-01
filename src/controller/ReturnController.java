package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import com.jfoenix.controls.JFXButton;

import dto.ReturnBookDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.ReturnBookService;

public class ReturnController {
   @FXML
    private JFXButton btnReturnBook;

    @FXML
    private JFXButton btnBack;
    
    @FXML
    private TableColumn<ReturnBookDto, Integer> colBookName;

    @FXML
    private TableColumn<ReturnBookDto, Long> colDaysElp;

    @FXML
    private TableColumn<ReturnBookDto, BigDecimal> colFine;

    @FXML
    private TableColumn<ReturnBookDto, Integer> colId;

    @FXML
    private TableColumn<ReturnBookDto, Integer> colMemberId;

    @FXML
    private TableColumn<ReturnBookDto, String> colMemberName;

    @FXML
    private TableColumn<ReturnBookDto, LocalDate> colReturnDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ReturnBookDto> tblReturnBookDetails;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtDaysElapsed;

    @FXML
    private TextField txtDueDate;

    @FXML
    private TextField txtFine;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtMemberName;

         private ReturnBookService returnBookService;

    public void initialize() {
        returnBookService = ServiceFactory.getInstance().getService(ServiceType.ReturnBook);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookId")); // Assuming book ID is being used
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colDaysElp.setCellValueFactory(new PropertyValueFactory<>("dateElapsed"));
        colFine.setCellValueFactory(new PropertyValueFactory<>("fine"));

        loadReturnBookDetails();
    }

    private void loadReturnBookDetails() {
        try {
            ObservableList<ReturnBookDto> returnBookList = FXCollections.observableArrayList(returnBookService.getAllReturnBooks());
            tblReturnBookDetails.setItems(returnBookList);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Unable to load return book details.");
        }
    }
    @FXML
    void btnReturnOnAction(ActionEvent event) {
          try {
        if (txtMemberId.getText().isEmpty() || txtBookId.getText().isEmpty() || txtDueDate.getText().isEmpty()) {
            showAlert("Error", "Please fill in all required fields.");
            return;
        }

        int memberId = Integer.parseInt(txtMemberId.getText());
        int bookId = Integer.parseInt(txtBookId.getText());
        LocalDate dueDate = LocalDate.parse(txtDueDate.getText());
        LocalDate returnDate = LocalDate.now();

        long daysElapsed = ChronoUnit.DAYS.between(dueDate, returnDate);
        BigDecimal fine = BigDecimal.ZERO;

        if (daysElapsed > 0) {
            fine = BigDecimal.valueOf(daysElapsed * 100);
        }

        ReturnBookDto returnBookDto = new ReturnBookDto();
        returnBookDto.setMemberId(memberId);
        returnBookDto.setBookId(bookId);
        returnBookDto.setReturnDate(returnDate);
        returnBookDto.setDateElapsed((int) daysElapsed);
        returnBookDto.setFine(fine);

        returnBookService.addReturnBook(returnBookDto);
        showAlert("Success", "Book returned successfully.");
        clearFields();
        loadReturnBookDetails();

    } catch (NumberFormatException e) {
        showAlert("Error", "Invalid number format.");
    } catch (DateTimeParseException e) {
        showAlert("Error", "Invalid date format.");
    } catch (SQLException e) {
        showAlert("Error", "Database error: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        showAlert("Error", "An unexpected error occurred.");
    }
    }
    @FXML
    void onMemberIdEntered(KeyEvent event) {
         try {
        if (event.getCode() == KeyCode.ENTER) {
            int memberId = Integer.parseInt(txtMemberId.getText());
            ReturnBookDto returnBookDto = returnBookService.getReturnBookDetailsByMemberId(memberId);
            if (returnBookDto != null) { 
                txtMemberName.setText(returnBookDto.getMemberName());
                txtBookId.setText(String.valueOf(returnBookDto.getBookId()));
                // No need to handle returnIdTextField here anymore
            } else {
                showError("No return book details found for member ID: " + memberId);
            }
        }
    } catch (NumberFormatException e) {
        showError("Invalid member ID");
    } catch (IllegalArgumentException e) {
        showError(e.getMessage());
    } catch (SQLException e) {
        showError("Database error: " + e.getMessage());
    } catch (Exception e) {
        showError("An unexpected error occurred: " + e.getMessage());
    }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DashBoardForm.fxml"));
            AnchorPane root = loader.load();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            showAlert( "Error", "An error occurred while loading the dashboard.");
            e.printStackTrace();
        }

    }

    private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

private void clearFields() {
    txtMemberId.clear();
    txtBookId.clear();
    txtDueDate.clear();
    txtDaysElapsed.clear();
    txtFine.clear();
}

private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}


    

