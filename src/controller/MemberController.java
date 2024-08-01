package controller;


import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import dto.MemberDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.ServiceType;
import service.custom.MemberService;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MemberController {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnBack;


    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<MemberDto, String> colAddress;

    @FXML
    private TableColumn<MemberDto, Integer> colMemberId;

    @FXML
    private TableColumn<MemberDto, String> colName;

    @FXML
    private TableColumn<MemberDto, String> colPhone;

    @FXML
    private TableView<MemberDto> tblMember;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    private MemberService memberService = ServiceFactory.getInstance().getService(ServiceType.Members);

    @FXML
    void initialize() {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadMembers();

        tblMember.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFormFields(newValue);
            }
        });
    }

    private void loadMembers() {
        try {
            ObservableList<MemberDto> memberList = FXCollections.observableArrayList(memberService.getAll());
            tblMember.setItems(memberList);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load members", Alert.AlertType.ERROR);
        }
    }

    private void populateFormFields(MemberDto member) {
        txtMemberId.setText(String.valueOf(member.getMemberId()));
        txtName.setText(member.getName());
        txtAddress.setText(member.getAddress());
        txtPhone.setText(member.getPhone());
    }
    

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            MemberDto member = new MemberDto(Integer.parseInt(txtMemberId.getText()), txtName.getText(), txtAddress.getText(), txtPhone.getText());
            memberService.add(member);
            loadMembers();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid data", Alert.AlertType.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add member", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        MemberDto selectedMember = tblMember.getSelectionModel().getSelectedItem();
        if (selectedMember != null) {
        try {
            memberService.delete(String.valueOf(selectedMember.getMemberId()));
            loadMembers();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete member", Alert.AlertType.ERROR);
        }
    } else {
        showAlert("No Member Selected", "Please select a member to delete.", Alert.AlertType.WARNING);
    }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        MemberDto selectedMember = tblMember.getSelectionModel().getSelectedItem();
        if (selectedMember != null) {
        try {
            selectedMember.setName(txtName.getText());
            selectedMember.setAddress(txtAddress.getText());
            selectedMember.setPhone(txtPhone.getText());
            
            memberService.update(selectedMember);
            loadMembers();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update member", Alert.AlertType.ERROR);
        }
    } else {
        showAlert("No Member Selected", "Please select a member to update.", Alert.AlertType.WARNING);
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
            showAlert("Error", "An error occurred while loading the dashboard.",Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void clearFields() {
        txtMemberId.clear();
        txtName.clear();
        txtAddress.clear();
        txtPhone.clear();
    }
}