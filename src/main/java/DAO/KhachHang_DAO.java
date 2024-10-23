package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class KhachHang_DAO {
    public ArrayList<KhachHang> xuatDanhSachKhachHang (){
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
        try {
            String query = "select * from KhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String maKhachHang = rs.getString("maKhachHang");
            String cCCD = rs.getString("CCCD");
            String tenKhachHang = rs.getString("tenKhachHang");
            String soDienThoai = rs.getString("soDienThoai");
            LoaiKhachHang loaiKhachHang = new LoaiKhachHang("maLoaiKhachHang");
            LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
            KhachHang khachHang = new KhachHang(maKhachHang,cCCD, tenKhachHang,soDienThoai,loaiKhachHang,ngaySinh );
            dsKhachHang.add(khachHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  dsKhachHang;
    }
}
