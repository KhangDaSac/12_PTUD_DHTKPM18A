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

public class ChiTietVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static boolean themDanhSachChiTietVe(ArrayList<ChiTietVe> danhSachChiTietVe){
        String query = "insert into ChiTietVe values (?, ?, ?, ?, ?)";
        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, chiTietVe.getVe().getMaVe());
                statement.setString(2, chiTietVe.getCho().getMaCho());
                statement.setString(3, chiTietVe.getKhachHang().getMaKhachHang());
                statement.setDouble(4, chiTietVe.getGiaCho());
                statement.setDouble(5, chiTietVe.getPhanTramGiamGia());
                statement.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
}
