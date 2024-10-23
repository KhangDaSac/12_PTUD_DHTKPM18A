package DAO;

import DTO.KhachHang;
import DTO.LoaiKhachHang;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoaiKhachHang_DAO {
    public ArrayList<LoaiKhachHang> xuatDanhSachLoaiKhachHang (){
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<LoaiKhachHang> dsLoaiKhachHang = new ArrayList<>();
        try {
            String query = "select * from LoaiKhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String maLoaiKhachHang = rs.getString("maLoaiKhachHang");
            String tenLoaiKhachHang = rs.getString("tenLoaiKhachHang");
            double phanTramGiamGia = rs.getDouble("panTramGiamGia");
            LoaiKhachHang loaiKhachHang = new LoaiKhachHang(maLoaiKhachHang,tenLoaiKhachHang, phanTramGiamGia);
            dsLoaiKhachHang.add(loaiKhachHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  dsLoaiKhachHang;
    }
}
