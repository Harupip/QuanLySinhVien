package UI.quanlysinhvien;

import QuanLy.MySqlConnection;
import QuanLy.Score;
import QuanLy.SinhVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class scoreSvController implements Initializable {
    private String studentID = "";

    @FXML
    private Label mssvLabel;

    @FXML
    private TableView<Score> diemTableView;

    @FXML
    private TableColumn<Score, String> subjectIDCol;

    @FXML
    private TableColumn<Score, Float> diemGkCol;

    @FXML
    private TableColumn<Score, Float> diemCkCol;

    @FXML
    private TableColumn<Score, Float> diemTBCol;

    @FXML
    private Button okButton;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet =null;

    ObservableList<Score> scoreList = FXCollections.observableArrayList();

    void refreshTable() throws SQLException {
        scoreList.clear();

        String a =  "MSSV = " + "'" + getStudentID() + "'";

        query = "select * from diem WHERE " + a;
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String mssv = resultSet.getString("MSSV");
            String idmonhoc = resultSet.getString("IDMonHoc");
            float diemGiuaKi = resultSet.getFloat("DiemGiuaKi");
            float diemCuoiKi = resultSet.getFloat("DiemCuoiKi");
            float diemTB = diemGiuaKi * 40/100 + diemCuoiKi * 60/100 ;

            scoreList.add(new Score(mssv,idmonhoc,diemGiuaKi,diemCuoiKi,diemTB));
            diemTableView.setItems(scoreList);

        }
    }

    void setMssvLabel (String a) {
        mssvLabel.setText(a);
    }

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        try {
            loadDate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDate() throws SQLException {
        connection = MySqlConnection.getAccess();
        refreshTable();

        subjectIDCol.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        diemGkCol.setCellValueFactory(new PropertyValueFactory<>("diemGiuaKi"));
        diemCkCol.setCellValueFactory(new PropertyValueFactory<>("diemCuoiKi"));
        diemTBCol.setCellValueFactory(new PropertyValueFactory<>("diemTB"));

    }

    @FXML
    void fixDiem(ActionEvent event) throws SQLException {
        try {
            Score score = diemTableView.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("fix-Diem.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fixDiemController fixDiemController = loader.getController();
        fixDiemController.setMssv(score.getMssv());
        fixDiemController.setGKTextArea(score.getDiemGiuaKi());
        fixDiemController.setCKTextArea(score.getDiemCuoiKi());
        fixDiemController.setMonHocLabel(score.getSubjectId());
        refreshTable();


        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        } catch (Exception e) {
            showAlertWithoutHeaderText();
        }
    }


    @FXML
    void addDiem(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("add-Diem.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addDiemController addDiemController = loader.getController();


        addDiemController.setMssv(studentID);

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        refreshTable();
    }

    @FXML
    void deleteDiem(ActionEvent event) {

    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Hãy chọn môn học cần sửa");

        alert.showAndWait();
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
