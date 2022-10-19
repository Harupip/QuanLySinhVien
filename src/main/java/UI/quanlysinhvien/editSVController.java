package UI.quanlysinhvien;

import QuanLy.SinhVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.time.LocalDate;

public class editSVController {
    @FXML
    private TextField nameTextArea;

    @FXML
    private ChoiceBox<String> khoaChoiceBox;

    @FXML
    private TextField addressTextArea;

    @FXML
    private DatePicker birthTextField;

    @FXML
    private TextField phoneTextArea;

    @FXML
    private TextField emailTextArea;

    @FXML
    private Button okButton;

    @FXML
    private Label mssvLabel;

    private String[] khoa = {"Công nghệ thông tin", "Khoa học máy tính"};

    @FXML
    void fixSv(ActionEvent event) {
        try {
            String mssv = null;
            String name = null;
            String birth = null;
            String address = null;
            String phone = null;
            String email = null;
            if (!nameTextArea.equals(null)) name = nameTextArea.getText();
            if (!birthTextField.equals(null)) birth = birthTextField.getValue().toString();
            if (!addressTextArea.equals(null)) address = addressTextArea.getText();
            if (!phoneTextArea.equals(null)) phone = phoneTextArea.getText();
            if (!emailTextArea.equals(null)) email = emailTextArea.getText();
            SinhVien sinhVien = new SinhVien();
            sinhVien.fixSV(mssvLabel.getText(),"CNTT",name,birth,address,phone,email);

            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            showAlertWithoutHeaderText();
        }
    }

    void setMssvLabel (String a) {
        mssvLabel.setText(a);
    }

    void setNameTextArea (String a) {
        nameTextArea.setText(a);
    }

    void setAddressTextArea (String a) {
        addressTextArea.setText(a);
    }

    void setPhoneTextArea (String a) {
        phoneTextArea.setText(a);
    }

    void setEmailTextArea (String a) {
        emailTextArea.setText(a);
    }

    @FXML
    void initialize() {
        khoaChoiceBox.getItems().addAll(khoa);
    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Chưa đủ thông tin yêu cầu");

        alert.showAndWait();
    }

}
