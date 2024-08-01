package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import dto.BookDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.BookService;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class BookController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnBack;

    @FXML
    private TableColumn<BookDto, String> colAuthor;

    @FXML
    private TableColumn<BookDto, Integer> colBookId;

    @FXML
    private TableColumn<BookDto, Integer> colCategoryId;

    

    @FXML
    private TableColumn<BookDto, String> colPub;

    @FXML
    private TableColumn<BookDto, String> colTitle;

    @FXML
    private TableColumn<BookDto, Integer> colYear;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookDto> tblBook;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtCategoryId;

   
    @FXML
    private TextField txtPublisher;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtYear;

    private BookService bookService = ServiceFactory.getInstance().getService(ServiceType.Books);

    @FXML
    void initialize() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        colPub.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        loadBooks();

        tblBook.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFormFields(newValue);
            }
        });
    }

    private void populateFormFields(BookDto book) {
        txtBookId.setText(String.valueOf(book.getBookID()));
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtCategoryId.setText(String.valueOf(book.getCategoryID()));
        txtPublisher.setText(book.getPublisher());
        txtYear.setText(String.valueOf(book.getYear()));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
         try {
            BookDto book = new BookDto(
                Integer.parseInt(txtBookId.getText()),
                txtTitle.getText(),
                txtAuthor.getText(),
                Integer.parseInt(txtCategoryId.getText()),
                txtPublisher.getText(),
                Integer.parseInt(txtYear.getText())
            );
            boolean isAdded = bookService.add(book);
            if (isAdded) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully!");
                loadBooks();
                clearFields();
            }else{
                showAlert(Alert.AlertType.WARNING, "Failed", "Book addition failed!");
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while adding the book.");
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = bookService.delete(txtBookId.getText());
            if (isDeleted) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book deleted successfully!");
                loadBooks();
                clearFields();
            }else{
                showAlert(Alert.AlertType.WARNING, "Failed", "Book deletion failed!");
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while deleting the book.");
        }
        }

    

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            BookDto book = new BookDto(
                Integer.parseInt(txtBookId.getText()),
                txtTitle.getText(),
                txtAuthor.getText(),
                Integer.parseInt(txtCategoryId.getText()),
                txtPublisher.getText(),
                Integer.parseInt(txtYear.getText())
            );
            boolean isUpdated = bookService.update(book);
            if (isUpdated) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully!");
                loadBooks();
                clearFields();
            }else{
                showAlert(Alert.AlertType.WARNING, "Failed", "Book update failed!");
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while updating the book.");
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

     private void loadBooks() {
        try {
            ObservableList<BookDto> bookList = FXCollections.observableArrayList(bookService.getAll());
            tblBook.setItems(bookList);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    private void clearFields() {
        txtBookId.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtCategoryId.clear();
        txtPublisher.clear();
        txtYear.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
