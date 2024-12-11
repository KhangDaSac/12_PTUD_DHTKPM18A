package DAO;

import DTO.GaTau;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GaTau_DAO {
    public ArrayList<GaTau> xuatDanhSachGaTau() {
        ArrayList<GaTau> danhSachGaTau = new ArrayList<GaTau>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select * from GaTau order by tenGaTau";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String maGaTau = rs.getString(1);
                String tenGaTau = rs.getString(2);
                String diaChi = rs.getString(3);
                GaTau gaTau = new GaTau(maGaTau, tenGaTau, diaChi);
                danhSachGaTau.add(gaTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachGaTau;
    }

    public GaTau timGaTauTheoMa(String maGa) {
        Connection con = ConnectDB.getInstance().getConnection();
        GaTau gaTau = null;
        try {
            String query = "select * from GaTau where maGaTau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maGa);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String maGaTau = rs.getString(1);
                String tenGaTau = rs.getString(2);
                String diaChi = rs.getString(3);
                gaTau = new GaTau(maGaTau, tenGaTau, diaChi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gaTau;
    }

    public static GaTau getGaTauTheoMaGaTau(String maGaTau) {
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select * from GaTau where maGaTau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maGaTau);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                GaTau gaTau = new GaTau(rs.getString("maGaTau"), rs.getString("tenGaTau"), rs.getString("diaChi"));
                return gaTau;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
