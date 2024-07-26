package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
@FXML
    private JFXButton btnLogin;

    @FXML
    private Hyperlink hprRegister;

    @FXML
    private PasswordField pswPassword;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException{
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("DashBoard Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml"))));

    }

    @FXML
    void hprRegisterOnAction(ActionEvent event)throws IOException {
         Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Register Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/RegistrationForm.fxml"))));

    }

    @FXML
    void pswPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

}
