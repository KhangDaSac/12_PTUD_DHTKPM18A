package DAO;

import DTO.ChiTietVe;
import DTO.Ve;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietVe_DAO {
    public void themDanhSachChiTietVe(ArrayList<ChiTietVe> danhSachChiTietVe){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "insert into ChiTietVe values (?, ?, ?, ?, ?, ?)";
        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, chiTietVe.getVe().getMaVe());
                statement.setString(2, chiTietVe.getCho().getMaCho());
                statement.setString(3, chiTietVe.getKhachHang().getMaKhachHang());
                statement.setDouble(4, chiTietVe.getGiaCho());
                statement.setDouble(5, chiTietVe.getSoTienGiamGia());
                statement.setDouble(6, chiTietVe.getThanhTien());

                statement.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
