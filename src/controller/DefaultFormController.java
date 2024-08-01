package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.DefaultService;

public class DefaultFormController {

     @FXML
    private JFXButton btnBack;

    
    @FXML
    private Label lblTotBooks;

    @FXML
    private Label lblTotIssueBooks;

    @FXML
    private Label lblTotMembers;

    @FXML
    private Label lblTotReturnBooks;

    @FXML
    private AnchorPane main;

    private final DefaultService defaultService = (DefaultService) ServiceFactory.getInstance().getService(ServiceType.DEFAULT);

    public void initialize() {
        getTotBooks();
        getTotIssuedBooks();
        getTotMembers();
        getTotReturnBooks();
    }

    void getTotBooks() {
        try {
            lblTotBooks.setText(String.valueOf(defaultService.getTotalBooks()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getTotIssuedBooks() {
        try {
            lblTotIssueBooks.setText(String.valueOf(defaultService.getTotalIssuedBooks()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getTotMembers() {
        try {
            lblTotMembers.setText(String.valueOf(defaultService.getTotalMembers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getTotReturnBooks() {
        try {
            lblTotReturnBooks.setText(String.valueOf(defaultService.getTotalReturnBooks()));
        } catch (Exception e) {
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
