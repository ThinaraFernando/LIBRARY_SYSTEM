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
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle("Login Form");
            stage.centerOnScreen();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();  // Log the exception
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
}
