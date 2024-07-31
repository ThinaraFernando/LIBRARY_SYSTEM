package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.DefaultService;

public class DefaultFormController {

    
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
}
