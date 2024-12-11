package DAO;

import DTO.ChiTietVe;
import DTO.Ve;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;

public class ChiTietVe_DAO {
    public boolean themDanhSachChiTietVe(ArrayList<ChiTietVe> danhSachChiTietVe){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "insert into ChiTietVe values (?, ?, ?, ?, ?, ?)";
        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, chiTietVe.getVe().getMaVe());
                statement.setString(2, chiTietVe.getCho().getMaCho());
                statement.setString(3, chiTietVe.getKhachHang().getMaKhachHang());
                statement.setDouble(4, chiTietVe.getGiaCho());
                statement.executeUpdate();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
    public static ArrayList<ChiTietVe> xuatDanhSachChiTietVeTheoMaVe(String maVe){
        ArrayList<ChiTietVe> danhSachChiTietVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "select * from ChiTietVe where maVe = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVe);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Ve ve = new Ve(rs.getString("maVe"));
                Cho cho = Cho_DAO.getChoTheoMaCho(rs.getString("maCho"));
                KhachHang khachHang = KhachHang_DAO.getKhachHangTheoMaKhachHang(rs.getString("maKhachHang"));
                double giaCho = rs.getDouble("giaCho");
                ChiTietVe chiTietVe = new ChiTietVe(ve, cho, khachHang, giaCho,rs.getDouble("phanTramGiamGia"));
                danhSachChiTietVe.add(chiTietVe);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return danhSachChiTietVe;
    }

}
