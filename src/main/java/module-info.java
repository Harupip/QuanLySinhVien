module UI.quanlysinhvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;

    opens UI.quanlysinhvien to javafx.fxml;
    exports UI.quanlysinhvien;

    opens QuanLy to java.sql;
    exports QuanLy;
}