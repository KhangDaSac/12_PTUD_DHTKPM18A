package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVien_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static NhanVien getNhanVienTheoMaNhanVien(String maNhanVien){
        try {
            String query = "select maNhanVien, CCCD, tenNhanVien, soDienThoai, email, loaiNhanVien from NhanVien where maNhanVien = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maNhanVien);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getString("maNhanVien"),
                        rs.getString("CCCD"),
                        rs.getString("tenNhanVien"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        LoaiNhanVien.valueOf(rs.getString("loaiNhanVien"))
                );
                return nhanVien;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
