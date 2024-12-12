package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static connectDB.ConnectDB.con;

public class KhachHang_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static KhachHang getKhachHangTheoCCCD(String cccd){
        try {
            String query = "select maKhachHang, CCCD, tenKhachHang, soDienThoai, lkh.maLoaiKhachHang, tenLoaiKhachHang, phanTramGiamGia" +
                    " from KhachHang kh" +
                    " join LoaiKhachHang lkh on kh.maLoaiKhachHang = lkh.maLoaiKhachHang" +
                    " where CCCD = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, cccd);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String cccdKH = rs.getString("cccd");
                String soDienThoai = rs.getString("soDienThoai");
                String maLoaiKhachHang = rs.getString("maLoaiKhachHang");
                String tenLoaiKhachHang = rs.getString("tenLoaiKhachHang");
                double phanTramGiamGia = rs.getDouble("phanTramGiamGia");
                LoaiKhachHang loaiKhachHang = new LoaiKhachHang(maLoaiKhachHang, tenLoaiKhachHang, phanTramGiamGia);
                return new KhachHang(maKhachHang, cccdKH, tenKhachHang, soDienThoai, loaiKhachHang);
            }
        }catch (Exception e){

        }
        return null;
    }


}
