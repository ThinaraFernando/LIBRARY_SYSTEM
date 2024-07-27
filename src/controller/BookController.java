package controller;

import com.jfoenix.controls.JFXButton;

import dto.BookDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.BookService;
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
                loadBooks();
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = bookService.delete(txtBookId.getText());
            if (isDeleted) {
                loadBooks();
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace(); 
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
                loadBooks();
                clearFields();
            }
        } catch (Exception e) {
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

}
