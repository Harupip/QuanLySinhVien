package UI.quanlysinhvien;

import QuanLy.SinhVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class addSvController {
    @FXML
    private TextField mssvTextArea;

    @FXML
    private TextField nameTextArea;

    @FXML
    private DatePicker birthTextField;

    @FXML
    private TextField addressTextArea;

    @FXML
    private TextField phoneTextArea;

    @FXML
    private TextField emailTextArea;

    @FXML
    private ChoiceBox<String> khoaChoiceBox;

    @FXML
    private Button okButton;

    private String[] khoa = {"Công nghệ thông tin", "Khoa học máy tính"};

    @FXML
    void addAccepted(ActionEvent event) throws SQLException {
        try {
            String khoaid = "CNTT";
            if (khoaChoiceBox.getValue().equals("Khoa học máy tính")) khoaid = "KHMT";
            String mssv = null;
            String name = null;
            String birth = null;
            String address = null;
            String phone = null;
            String email = null;
            if (!mssvTextArea.equals(null) && mssvTextArea.getLength() == 8) mssv = mssvTextArea.getText();
            if (!nameTextArea.equals(null)) name = nameTextArea.getText();
            if (!birthTextField.equals(null)) birth = birthTextField.getValue().toString();
            if (!addressTextArea.equals(null)) address = addressTextArea.getText();
            if (!phoneTextArea.equals(null)) phone = phoneTextArea.getText();
            if (!emailTextArea.equals(null)) email = emailTextArea.getText();
            SinhVien sinhVien = new SinhVien();
            sinhVien.addSV(mssv,khoaid,name,birth,address,phone,email);

            mssvTextArea.setText(null);
            nameTextArea.setText(null);
            birthTextField.setValue(null);
            addressTextArea.setText(null);
            phoneTextArea.setText(null);
            emailTextArea.setText(null);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            showAlertWithoutHeaderText();
        }

    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Chưa đủ thông tin yêu cầu");

        alert.showAndWait();
    }


    @FXML
    void initialize() {
        khoaChoiceBox.getItems().addAll(khoa);
    }
}
