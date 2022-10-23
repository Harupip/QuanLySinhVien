package QuanLy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Score {
    private String mssv;
    private String subjectId;
    private float diemCuoiKi = 0;
    private float diemGiuaKi = 0;
    private float diemTB = 0;

    public Score () {}

    public Score (String mssv, String subjectId, float diemGiuaKi, float diemCuoiKi, float diemTB) {
        this.mssv = mssv;
        this.setSubjectId(subjectId);
        this.setDiemGiuaKi(diemGiuaKi);
        this.setDiemCuoiKi(diemCuoiKi);
        this.diemTB = diemTB;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }



    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void addDiem (String mssv, String subjectId, float diemGiuaKi, float diemCuoiKi) throws SQLException {
        Connection connection = MySqlConnection.getAccess();
        String sql = "INSERT diem VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, mssv);
        statement.setString(2, subjectId);
        statement.setFloat(3, diemGiuaKi);
        statement.setFloat(4, diemCuoiKi);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void deleteDiem (String mssv) throws SQLException {
        Connection connection = MySqlConnection.getAccess();
        String sth = "";
        if (!mssv.equals("")) {
            sth += "MSSV = " + "'" + mssv + "'";
        }
        String sql = "DELETE FROM diem WHERE " + sth;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void fixDiem (String mssv, String subjectId, float diemGiuaKi, float diemCuoiKi) throws SQLException {
        Connection connection = MySqlConnection.getAccess();

        String sth = "DiemGiuaKi = " + diemGiuaKi + " , ";
        sth += "DiemCuoiKi = " + diemCuoiKi;

        String sql = "UPDATE diem SET " + sth + " WHERE MSSV = " + "'" + mssv + "'" + " AND IDMonHoc =" + "'" + subjectId + "'";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }



    public float getDiemCuoiKi() {
        return diemCuoiKi;
    }

    public void setDiemCuoiKi(float diemCuoiKi) {
        this.diemCuoiKi = diemCuoiKi;
    }

    public float getDiemGiuaKi() {
        return diemGiuaKi;
    }

    public void setDiemGiuaKi(float diemGiuaKi) {
        this.diemGiuaKi = diemGiuaKi;
    }

    public float getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(float diemTB) {
        this.diemTB = diemTB;
    }
}
