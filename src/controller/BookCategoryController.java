package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import dto.BookCategoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.BookCategoryService;

public class BookCategoryController implements Initializable {

    private BookCategoryService bookCategoryService;
  @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnBack;

    @FXML
    private TableColumn<BookCategoryDto, Integer> colCategoryId;

    @FXML
    private TableColumn<BookCategoryDto, String> colCategoryName;

    @FXML
    private Label lblCategoryId;

    @FXML
    private Label lblCategoryName;

    @FXML
    private TableView<BookCategoryDto> tblCategory;

    @FXML
    private TextField txtCategoryId;

    @FXML
    private TextField txtCategoryName;

    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookCategoryService = ServiceFactory.getInstance().getService(ServiceType.BookCategory);
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        loadTable();

        tblCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFormFields(newValue);
            }
        });
    }

    private void loadTable() {
        try {
            ObservableList<BookCategoryDto> categories = FXCollections.observableArrayList(bookCategoryService.getAll());
            tblCategory.setItems(categories);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     private void populateFormFields(BookCategoryDto bookcategory) {
        txtCategoryId.setText(String.valueOf(bookcategory.getCategoryId()));
        txtCategoryName.setText(bookcategory.getCategoryName());
        
    }

    

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            boolean isAdded = bookCategoryService.add(new BookCategoryDto(
                Integer.parseInt(txtCategoryId.getText()),
                txtCategoryName.getText()
            ));
            if (isAdded) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Category added successfully!");
                loadTable();
                clearFields();
            }else{
                showAlert(Alert.AlertType.WARNING, "Failed", "Book addition failed!");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while adding the book.");
            e.printStackTrace();

        }
    }


        

    
    

     @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = bookCategoryService.delete(txtCategoryId.getText());
            if (isDeleted) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book deleted successfully!");

                loadTable();
                clearFields();
            }else{
                showAlert(Alert.AlertType.WARNING, "Failed", "Book deletion failed!");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while deleting the book.");
            e.printStackTrace();
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            boolean isUpdated = bookCategoryService.update(new BookCategoryDto(
                Integer.parseInt(txtCategoryId.getText()),
                txtCategoryName.getText()
            ));
            if (isUpdated) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully!");

                loadTable();
                clearFields();
            }else{
                showAlert(Alert.AlertType.WARNING, "Failed", "Book update failed!");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while updaying the book.");
            e.printStackTrace();
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


    

    private void clearFields() {
        txtCategoryId.clear();
        txtCategoryName.clear();
       
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
    
    

