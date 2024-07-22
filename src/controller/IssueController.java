package controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class IssueController {
 @FXML
    private JFXButton btnIssueBook;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colIssueDate;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblIssueDate;

    @FXML
    private Label lblMemberId;

    @FXML
    private TableView<?> tblIssueBookDetails;


    @FXML
    private Label lblMemberName;

    @FXML
    private TextField tblMemberName;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtDueDate;

    @FXML
    private TextField txtIssueDate;

    @FXML
    private TextField txtMemberId;

    @FXML
    void btnIsssueBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

}
