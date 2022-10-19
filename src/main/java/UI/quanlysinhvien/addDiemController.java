package UI.quanlysinhvien;

import QuanLy.Score;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class addDiemController {
    private String mssv ="";

    @FXML
    private TextField GKTextArea;

    @FXML
    private TextField CKTextArea;

    @FXML
    private ChoiceBox<String> monHocChoiceBox;

    @FXML
    private Button okButton;

    String[] monhoc = {"Xác xuất thống kê", "Cơ sở dữ liệu"};

    @FXML
    void addAccepted(ActionEvent event)  {
        try {
            float gk = 0;
            float ck = 0;
            gk = Float.parseFloat(GKTextArea.getText());
            ck = Float.parseFloat(CKTextArea.getText());

            if ((gk < 0 || gk > 10) || ck < 0 || ck > 10) {
                showAlertWithoutHeaderText();
            }
            else {
                new Score().addDiem(mssv,"INT2211",gk,ck);
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }


        } catch (Exception e) {
            showAlertWithoutHeaderText();
        }


    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Nhập sai dữ liệu hoặc môn này đã có điểm");

        alert.showAndWait();
    }


    @FXML
    void initialize() {
        monHocChoiceBox.getItems().addAll(monhoc);
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
}
