package DAO;

import DTO.ChiTietHoaDonLayVe;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ChiTietHoaDonLayVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static boolean themDanhSachChiTietHoaDonLayVe(ArrayList<ChiTietHoaDonLayVe> danhSachChiTietHoaDonLayVe){
        try {
            String query = "insert into ChiTietHoaDonLayVe values (?, ?, ?)";
            for(ChiTietHoaDonLayVe chiTietHoaDonLayVe : danhSachChiTietHoaDonLayVe){
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, chiTietHoaDonLayVe.getVeDat().getMaVeDat());
                statement.setString(2, chiTietHoaDonLayVe.getVe().getMaVe());
                statement.setString(3, chiTietHoaDonLayVe.getHoaDonLayVe().getMaHoaDonLayVe());
                statement.execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
