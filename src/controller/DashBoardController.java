package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashBoardController {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane main;

    @FXML
    void btnBookCategoriesOnAction(ActionEvent event)throws IOException {
        initUI("Book Categories.fxml");
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Book Category Form");

    }

    @FXML
    void btnBooksOnAction(ActionEvent event)throws IOException {
        initUI("Books.fxml");
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Book Form");

    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event)throws IOException  {
        initUI("DefaultForm.fxml");
    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event)throws IOException {
        initUI("Issue Form.fxml");
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Course Form");

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException{
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
    }

    @FXML
    void btnMembersOnAction(ActionEvent event)throws IOException {
        initUI("Members.fxml");
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Course Form");

    }

    private void initUI(String location) throws IOException {
        this.main.getChildren().clear();
        this.main.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/"+location)));
    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) throws IOException {
        initUI("ReturnForm.fxml");
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Return Book  Form");

    }
}
