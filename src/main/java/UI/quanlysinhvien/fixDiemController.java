package UI.quanlysinhvien;

import QuanLy.Score;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

public class fixDiemController {
    private String mssv ="";
    private String subjectID = "";

    @FXML
    private TextField GKTextArea;

    @FXML
    private TextField CKTextArea;

    @FXML
    private Label monHocLabel;

    @FXML
    private Button okButton;

    String[] monhoc = {"Xác xuất thống kê", "Cơ sở dữ liệu"};

    @FXML
    void fixAccepted(ActionEvent event)  {
        try {
            float gk = 0;
            float ck = 0;
            gk = Float.parseFloat(getGKTextArea().getText());
            ck = Float.parseFloat(getCKTextArea().getText());

            if ((gk < 0 || gk > 10) || ck < 0 || ck > 10) {
                showAlertWithoutHeaderText();
            }
            else {
                new Score().fixDiem(mssv,subjectID,gk,ck);
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

    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public void setSubjectID(String subjID) {
        this.subjectID = subjID;
    }

    public TextField getGKTextArea() {
        return GKTextArea;
    }

    public void setGKTextArea(float a) {
        GKTextArea.setText(String.valueOf(a));
    }

    public TextField getCKTextArea() {
        return CKTextArea;
    }

    public void setCKTextArea(float a) {
        CKTextArea.setText(String.valueOf(a));
    }

    public Label getMonHocLabel() {
        return monHocLabel;
    }

    public void setMonHocLabel(String a) {
        String b="";
        if (a.equals("MAT1101")) b = "Xác xuất thống kê";
        if (a.equals("INT2211")) b = "Cơ sở dữ liệu";
        monHocLabel.setText(b);
    }
}
