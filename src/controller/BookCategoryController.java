package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import dto.BookCategoryDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    }

    private void loadTable() {
        try {
            ObservableList<BookCategoryDto> categories = FXCollections.observableArrayList(bookCategoryService.getAll());
            tblCategory.setItems(categories);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            boolean isAdded = bookCategoryService.add(new BookCategoryDto(
                Integer.parseInt(txtCategoryId.getText()),
                txtCategoryName.getText()
            ));
            if (isAdded) {
                loadTable();
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        

    
    

     @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = bookCategoryService.delete(txtCategoryId.getText());
            if (isDeleted) {
                loadTable();
                clearFields();
            }
        } catch (Exception e) {
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
                loadTable();
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    private void clearFields() {
        txtCategoryId.clear();
        txtCategoryName.clear();
       
    }
}
    
    

