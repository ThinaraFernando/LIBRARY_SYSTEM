package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashBoardController {

       @FXML
    private JFXButton btnLogout;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane main;

    @FXML
    void btnBookCategoriesOnAction(ActionEvent event) {
        try {
            initUI("BookCategory.fxml");
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle("Book Category Form");
        } catch (IOException e) {
            e.printStackTrace();  // Log the exception
        }
    }

    @FXML
    void btnBooksOnAction(ActionEvent event) {
        try {
            initUI("Books.fxml");
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle("Book Form");
        } catch (IOException e) {
            e.printStackTrace();  // Log the exception
        }
    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) {
        try {
            initUI("DefaultForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();  // Log the exception
        }
    }

    @FXML
    void btnIssueBookOnAction(ActionEvent event) {
        try {
            initUI("IssueForm.fxml");
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle("Issue Form");
        } catch (IOException e) {
            e.printStackTrace();  // Log the exception
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        try {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoardForm.fxml")); // Update the path accordingly
        AnchorPane root = loader.load();
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the dashboard.");
        e.printStackTrace();
    }
    }

    @FXML
    void btnMembersOnAction(ActionEvent event) {
        try {
            initUI("Members.fxml");
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle("Member Form");
        } catch (IOException e) {
            e.printStackTrace();  // Log the exception
        }
    }

    private void initUI(String location) throws IOException {
        
            if (location == null || location.isEmpty()) {
                throw new IllegalArgumentException("Location cannot be null or empty");
            }
            this.main.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/" + location));
            this.main.getChildren().add(loader.load());
        }
        
    

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        try {
            initUI("ReturnForm.fxml");
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle("Return Book Form");
        } catch (IOException e) {
            e.printStackTrace();  // Log the exception
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
