package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PhieuDatVe_DAO {


    public static boolean capNhatTrangThaiPhieuDatVe(String maPhieuDatVe, String trangThai){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "UPDATE PhieuDatVe SET trangThaiPhieuDatVe = ? WHERE maPhieuDatVe = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, trangThai);
            statement.setString(2, maPhieuDatVe);
            statement.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void huyPhieuDatVe(String maPhieuDatVe) {
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "update PhieuDatVe set trangThaiPhieuDatVe = 'DAHUY' where maPhieuDatVe = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maPhieuDatVe);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
