package DAO;

import connectDB.ConnectDB;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PhieuDatVe_DAO {
    public static ArrayList<String> getDanhSachMaPhieuDatVe(String maHoaDon){
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<String> dsMaPhieuDatVe = new ArrayList<>();
        try {
            String query = "SELECT maPhieuDatVe FROM PhieuDatVe WHERE maHoaDon = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dsMaPhieuDatVe.add(rs.getString("maPhieuDatVe"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsMaPhieuDatVe;
    }
}
