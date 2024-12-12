package DAO;

import DTO.LoaiToaTau;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class LoaiToaTau_DAO {
    public ArrayList<LoaiToaTau> xuatDanhSachLoaiToaTau() {
        ArrayList<LoaiToaTau> dsLoaiToaTau = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select * from LoaiToaTau";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String maLoaiToa = rs.getString(1);
                String tenLoaiToa = rs.getString(2);
                float heSoGia = rs.getFloat(3);
                LoaiToaTau loaiToaTau = new LoaiToaTau(maLoaiToa, tenLoaiToa,heSoGia);
                dsLoaiToaTau.add(loaiToaTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiToaTau;
    }

    public static LoaiToaTau timLoaiToaTauTheoMa(String maLoai) {
        Connection con = ConnectDB.getInstance().getConnection();
        LoaiToaTau loaiToaTau = null;
        try {
            String query = "select * from LoaiToaTau where maLoaiToa = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maLoai);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maLoaiToa = rs.getString(1);
                String tenLoaiToa = rs.getString(2);
                float heSoGia = rs.getFloat(3);
                loaiToaTau = new LoaiToaTau(maLoaiToa, tenLoaiToa, heSoGia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiToaTau;
    }
}
