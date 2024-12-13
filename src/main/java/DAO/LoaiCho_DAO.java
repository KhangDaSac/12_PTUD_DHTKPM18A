package DAO;

import DTO.LoaiCho;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LoaiCho_DAO {
    public ArrayList<LoaiCho> getDSLoaiCho() {
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<LoaiCho> dsLoaiCho = new ArrayList<>();
        try {
            String query = "select * from LoaiCho";
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query);
             while (rs.next()) {
                 String maLoaiCho = rs.getString(1);
                 String tenLoaiCho = rs.getString(2);
                 double heSoGiaCho = rs.getDouble(3);
                 LoaiCho loaiCho = new LoaiCho(maLoaiCho, tenLoaiCho,heSoGiaCho);
                 dsLoaiCho.add(loaiCho);
             }
        } catch (Exception e) {
            e.printStackTrace();
    }
        return dsLoaiCho;
    }

    public static LoaiCho getLoaiChoTheoMa(String maLoaiCho) {
        Connection con = ConnectDB.getInstance().getConnection();
        LoaiCho loaiCho = null;
        try {
            String query = "select * from LoaiCho where maLoaiCho = ? ";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maLoaiCho);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String tenLoaiCho = rs.getString(2);
                double heSoGiaCho = rs.getDouble(3);
                loaiCho = new LoaiCho(maLoaiCho, tenLoaiCho, heSoGiaCho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiCho;
    }
}
