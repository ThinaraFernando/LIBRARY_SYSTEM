package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import dto.IssueDetailDto;
import dto.IssueDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.IssueBookService;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class IssueController {

    @FXML
    private JFXButton btnIssueBook;

   
    @FXML
    private JFXButton btnBack;

    @FXML
    private TableColumn<IssueDetailDto, String> colBookId;

    @FXML
    private TableColumn<IssueDetailDto, LocalDate> colDueDate;

    @FXML
    private TableColumn<IssueDetailDto, String> colId;

    @FXML
    private TableColumn<IssueDetailDto, LocalDate> colIssueDate;

    @FXML
    private TableColumn<IssueDetailDto, String> colMemberId;

    @FXML
    private DatePicker datePickerIssueDate;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<IssueDetailDto> tblIssueBookDetails;

    @FXML
    private TextField tblMemberName;

    @FXML
    private TextField txtBookId;

    @FXML
    private DatePicker datePickerDueDate;

    @FXML
    private TextField txtMemberId;

    

    private IssueBookService issueBookService;

     public void initialize() {
        issueBookService = ServiceFactory.getInstance().getService(ServiceType.IssueBooks);

        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
    colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    colId.setCellValueFactory(new PropertyValueFactory<>("issueDetailId"));
    colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
    colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));

    loadTableData();
    datePickerIssueDate.setValue(LocalDate.now());
    datePickerDueDate.setValue(LocalDate.now().plusWeeks(2));
}

private void loadTableData() {
    try {
        List<IssueDetailDto> issueDetails = issueBookService.getAllIssueDetails();
        tblIssueBookDetails.getItems().setAll(issueDetails);
    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Error", "Failed to load issue details: " + e.getMessage());
    }
}


  
    @FXML
    void btnIsssueBookOnAction(ActionEvent event) {
        try {
            String memberId = txtMemberId.getText();
            LocalDate issueDate = datePickerIssueDate.getValue();
            LocalDate dueDate = datePickerDueDate.getValue();

            if (issueDate == null || dueDate == null) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select issue and due dates");
                return;
            }

            List<IssueDetailDto> issueDetails = new ArrayList<>();
            IssueDto issueDto = new IssueDto("ISSUE001", memberId, issueDate, dueDate, issueDetails);
            issueBookService.placeIssue(issueDto);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Book issued successfully");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to issue book: " + e.getMessage());
        }
    }


    

     @FXML
    void onMemberIdEntered(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
        try {
            String memberId = txtMemberId.getText();
            if (memberId.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Member ID cannot be empty.");
                return;
            }

            // Assuming the service method getMemberNameById retrieves the member name based on member ID
            String memberName = issueBookService.getMemberNameById(memberId);
            if (memberName != null) {
                tblMemberName.setText(memberName);
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "No member found with ID: " + memberId);
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve member details: " + e.getMessage());
        }
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
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the dashboard.");
            e.printStackTrace();
        }

    }

    
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
   
}