package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import dto.LoginDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.LoginService;

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

    private final LoginService service = ServiceFactory.getInstance().getService(ServiceType.LOGIN);


    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException{
        try {
            String userName = txtUserName.getText().trim();
            String password = pswPassword.getText().trim();
            if (!userName.isEmpty() && !password.isEmpty()) {
                LoginDto login = service.getLogin(userName);
                if (login != null && login.getPassword().equals(password)) {
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setTitle("DashBoard Form");
                    stage.centerOnScreen();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/DashBoardForm.fxml"))));
                } else {
                    new Alert(Alert.AlertType.ERROR, "User Name or Password does not match!", ButtonType.OK).show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Enter UserName and Password", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Login Failed! Try Again!", ButtonType.OK).show();
        }
    }

    @FXML
    void hprRegisterOnAction(ActionEvent event)throws IOException {
         Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Register Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/RegistrationForm.fxml"))));

    }

    @FXML
    void pswPasswordOnAction(ActionEvent event)throws IOException  {

        if (!txtUserName.getText().trim().isEmpty()) {
            txtUserName.setStyle("-fx-border-color: #0fbcf9;-fx-border-width: 3  ");
            btnLoginOnAction(event);
        } else {
            txtUserName.setStyle("-fx-border-color: #f53b57;-fx-border-width: 3  ");
            txtUserName.requestFocus();
        }

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

        if (!txtUserName.getText().trim().isEmpty()) {
            txtUserName.setStyle("-fx-border-color: #0fbcf9;-fx-border-width: 3  ");
            pswPassword.requestFocus();
        } else {
            txtUserName.setStyle("-fx-border-color: #f53b57;-fx-border-width: 3  ");
            txtUserName.requestFocus();
        }
    }

    }


