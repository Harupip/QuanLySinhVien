package QuanLy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;

public class MySqlConnection {
    private static final String url = "jdbc:mysql://localhost:3307/quanlysinhvien";
    private static final String username = "root";
    private static final String password = "12345678";

    public MySqlConnection () {}

    public static Connection getAccess ()  {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
