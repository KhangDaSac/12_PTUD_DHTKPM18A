package DAO;

import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import DTO.GaTau;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ChiTietChuyenTau_DAO {
    public ArrayList<ChiTietChuyenTau> xuatDanhSachChiTietChuyenTau (){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select * from ChiTietTuyenTau";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ChuyenTau chuyenTau = new ChuyenTau(rs.getString(1));
            GaTau
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
}
