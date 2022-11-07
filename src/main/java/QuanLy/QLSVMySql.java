package QuanLy;

import QuanLy.SinhVien;

import java.sql.*;

public class QLSVMySql {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/quanlysinhvien";
        String username = "root";
        String password = "12345678";

        try {
            SinhVien sinhVien = new SinhVien("25021234","CNTT","Đỗ Thành Trung","2002/10/02","Ha Noi","0902547890","dgs@gmail.com");
//            sinhVien.addSV("25021234","CNTT","Đỗ Thành Trung","2002/10/02",1,"Ha Noi","0902547890","dgs@gmail.com");
//            sinhVien.deleteSV("25021234");
            sinhVien.fixSV("25021234","","GAY","","","","ha899@gmail.com");
//            Connection connection = DriverManager.getConnection(url,username,password);
//            System.out.println("Success");

            /**
             * // Them du lieu vao database
            String sql = "INSERT INTO customer (firstname,lastname) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Ndasdsa");
            statement.setString(2, "Btich");

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("A row");
            }
            statement.close();
            */

//             Lay du lieu tu database
//            String nani = "FROM";
//            String sql = "SELECT * "+nani+" customer";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            int count = 0;
//
//            while (resultSet.next()) {
//                String firstname = resultSet.getString("firstname");
//                String lastname = resultSet.getString("lastname");
//                count++;
//                System.out.println("Customer " + count + ": " + firstname + " " + lastname);
//
//            }
//
//            connection.close();

        } catch (SQLException e) {
            System.out.println("Fail");
                e.printStackTrace();
        }
    }

}
