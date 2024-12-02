package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietVeDat_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static ArrayList<ChiTietVeDat> getDanhSachPhieuDatVeTheoMaHoaDonDatVe(String maVeDat) {
        ArrayList<ChiTietVeDat> chiTietVeDat_list = new ArrayList<ChiTietVeDat>();

        try {
            String query = "exec UDP_TimDanhSachPhieuDatVeTheoMaHoaDonDatVe ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVeDat);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ChiTietVeDat chiTietVeDat = new ChiTietVeDat(

                );
                //veDat_list.add(veDat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietVeDat_list;
    }
}
