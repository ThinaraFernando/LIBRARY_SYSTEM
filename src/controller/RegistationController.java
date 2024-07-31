package controller;

import java.io.IOException;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;

import dto.LoginDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.LoginService;

public class RegistationController {
    @FXML
    private JFXButton btnRegister;

    @FXML
    private Hyperlink hprLogin;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;


    private final LoginService loginService = ServiceFactory.getInstance().getService(ServiceType.LOGIN);


    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        try {
            String userName = txtUserName.getText().trim();
            String pw = txtPassword.getText().trim();

            if (userName.length() > 0 && pw.length() > 0) {
                boolean success = loginService.saveLogin(new LoginDto(userName, pw));
                if (success) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered! Login!", ButtonType.OK).showAndWait();
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Login.fxml"))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "UserName Already Exists!!", ButtonType.OK).show();
        }
    }
    
    

    

    @FXML
    void hprLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Login.fxml"))));

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        if (Pattern.compile("^[A-z|1-9]{1,}$").matcher(txtPassword.getText().trim()).matches()) {
            txtPassword.setStyle("-fx-border-color: #0fbcf9; -fx-border-width: 3 ");
            btnRegister.requestFocus();
            btnRegisterOnAction(event);
        } else {
            txtPassword.setStyle("-fx-border-color: #f53b57; -fx-border-width: 3 ");
            txtPassword.requestFocus();
        }

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        if (Pattern.compile("^[A-z|1-9| ]{1,}$").matcher(txtUserName.getText().trim()).matches()) {
            txtUserName.setStyle("-fx-border-color: #0fbcf9;-fx-border-width: 3  ");
            txtPassword.requestFocus();
        } else {
            txtUserName.setStyle("-fx-border-color: #f53b57;-fx-border-width: 3  ");
            txtUserName.requestFocus();
        }
    }
}
    



