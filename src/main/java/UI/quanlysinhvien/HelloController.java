package UI.quanlysinhvien;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import QuanLy.MySqlConnection;
import QuanLy.SinhVien;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addNewSV;

    @FXML
    private Button search;

    @FXML
    private TextField searchText;

    @FXML
    private TableView<SinhVien> studentsTable;

    @FXML
    private TableColumn<SinhVien,String> mssvCol;

    @FXML
    private TableColumn<SinhVien,String> idKhoaCol;

    @FXML
    private TableColumn<SinhVien,String> nameCol;

    @FXML
    private TableColumn<SinhVien,String> birthCol;

    @FXML
    private TableColumn<SinhVien,String> addressCol;

    @FXML
    private TableColumn<SinhVien,String> phoneCol;

    @FXML
    private TableColumn<SinhVien,String> emailCol;

    @FXML
    private TableColumn<SinhVien, String> editCol;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet =null;
//    SinhVien student = new SinhVien("12345678","CNTT","sada","1234/12/4","sad","213123","1231@ad");
      SinhVien student = null;

    ObservableList<SinhVien> SVlist = FXCollections.observableArrayList();

    void refreshTable() throws SQLException {
        SVlist.clear();

        query = "select * from sinhvien";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String mssv = resultSet.getString("MSSV");
            String idkhoa = resultSet.getString("IDKhoa");
            String ten = resultSet.getString("TenSV");
            String ngaysinh = resultSet.getString("NgaySinh");
            String noisinh = resultSet.getString("NoiSinh");
            String sdt = resultSet.getString("SoDienThoai");
            String email = resultSet.getString("Email");

            SVlist.add(new SinhVien(mssv,idkhoa,ten,ngaysinh,noisinh,sdt,email));
            studentsTable.setItems(SVlist);

        }
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

        mssvCol.setCellValueFactory(new PropertyValueFactory<>("MSSV"));
        idKhoaCol.setCellValueFactory(new PropertyValueFactory<>("khoaID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        Callback<TableColumn<SinhVien, String>, TableCell<SinhVien, String>> cellFoctory = (TableColumn<SinhVien, String> param) -> {
            // make cell containing buttons
            final TableCell<SinhVien, String> cell = new TableCell<SinhVien, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIconView detail = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        detail.setStyle(" -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            student = studentsTable.getSelectionModel().getSelectedItem();
                            try {
                                student = studentsTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM sinhvien WHERE MSSV  =" +"'"+ student.getMSSV() +"'";
                                connection = MySqlConnection.getAccess();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            student = studentsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("edit-SV.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            editSVController editStudentController = loader.getController();
                            editStudentController.setMssvLabel(student.getMSSV());
                            editStudentController.setNameTextArea(student.getName());
                            editStudentController.setAddressTextArea(student.getAddress());
                            editStudentController.setEmailTextArea(student.getEmail());
                            editStudentController.setPhoneTextArea(student.getPhone());

                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            try {
                                refreshTable();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });

                        detail.setOnMouseClicked((MouseEvent event) -> {
                            student = studentsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("score-SV.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            scoreSvController scoreSvController = loader.getController();
                            scoreSvController.setStudentID(student.getMSSV());
                            try {
                                scoreSvController.refreshTable();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            scoreSvController.setMssvLabel(student.getMSSV());

                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });

                        HBox managebtn = new HBox(detail,editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(detail, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editCol.setCellFactory(cellFoctory);
        studentsTable.setItems(SVlist);
    }

    @FXML
    void addSvHandler(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("add-SV.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Sinh viên mới");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        refreshTable();
        stage.show();
    }

    @FXML
    void refreshButton(ActionEvent event) throws SQLException {
        refreshTable();
    }

    @FXML
    void searchButton(ActionEvent event) throws SQLException {
        SVlist.clear();
        String search  = searchText.getText();

        query = "select * from sinhvien where MSSV like '" + search + "%'";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String mssv = resultSet.getString("MSSV");
            String idkhoa = resultSet.getString("IDKhoa");
            String ten = resultSet.getString("TenSV");
            String ngaysinh = resultSet.getString("NgaySinh");
            String noisinh = resultSet.getString("NoiSinh");
            String sdt = resultSet.getString("SoDienThoai");
            String email = resultSet.getString("Email");

            SVlist.add(new SinhVien(mssv,idkhoa,ten,ngaysinh,noisinh,sdt,email));
            studentsTable.setItems(SVlist);

        }
    }

}