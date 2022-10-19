package QuanLy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SinhVien {
    private String MSSV = "";
    private String khoaID = "";
    private String name = "";
    private String birthday = "";
    private String address = "";
    private String phone = "";
    private String email = "";


    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SinhVien () {}

    public SinhVien (String MSSV, String khoaID ,String name, String birthday, String address, String phone, String email) {
        this.MSSV = MSSV;
        this.address = address;
        this.setKhoaID(khoaID);
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }

    public void addSV (String MSSV, String khoaID ,String name, String birthday,
                       String address, String phone, String email) throws SQLException {
        Connection connection = MySqlConnection.getAccess();
        String sql = "INSERT sinhvien VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, MSSV);
        statement.setString(2, khoaID);
        statement.setString(3, name);
        statement.setString(4, birthday);
        statement.setString(5, address);
        statement.setString(6, phone);
        statement.setString(7, email);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void deleteSV (String MSSV1) throws SQLException {
        Connection connection = MySqlConnection.getAccess();
        String sth = "";
        if (!MSSV1.equals("")) {
            sth += "MSSV = " + "'" + MSSV1 + "'";
        }
        String sql = "DELETE FROM sinhvien WHERE " + sth;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void fixSV (String MSSV1, String khoaID ,String name, String birthday,
                       String address, String phone, String email) throws SQLException {
        Connection connection = MySqlConnection.getAccess();

        String sth = "";
        if (!name.equals("")) {
            sth += "TenSV = " + "'" + name + "'";
        }
        if (!khoaID.equals("")) {
            if (!sth.equals("")) sth += ", ";
            sth += "IDKhoa = " + "'" + khoaID + "'";
        }
        if (!birthday.equals("")) {
            if (!sth.equals("")) sth += ", ";
            sth += "NgaySinh = " + "'" + birthday + "'";
        }
        if (!address.equals("")) {
            if (!sth.equals("")) sth += ", ";
            sth += "NoiSinh = " + "'" + address + "'";
        }
        if (!phone.equals("")) {
            if (!sth.equals("")) sth += ", ";
            sth += "SoDienThoai = " + "'" + phone + "'";
        }
        if (!email.equals("")) {
            if (!sth.equals("")) sth += ", ";
            sth += "Email = " + "'" + email + "'";
        }


        String sql = "UPDATE sinhvien SET " + sth + "WHERE MSSV = " + "'" + MSSV1 + "'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public String getKhoaID() {
        return khoaID;
    }

    public void setKhoaID(String khoaID) {
        this.khoaID = khoaID;
    }
}
