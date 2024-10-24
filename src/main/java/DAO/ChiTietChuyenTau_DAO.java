package DAO;

import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import DTO.GaTau;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChiTietChuyenTau_DAO {
    public ArrayList<ChiTietChuyenTau> xuatDanhSachChiTietChuyenTau (){
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<ChiTietChuyenTau> dsChiTietChuyenTau = new ArrayList<>();
        try {
            String query = "select * from ChiTietTuyenTau";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ChuyenTau chuyenTau = new ChuyenTau(rs.getString(1));
            GaTau gaTau = new GaTau(rs.getString(2));
            LocalDateTime thoiGianDen = rs.getTimestamp(3).toLocalDateTime();
            LocalDateTime thoiGianDi = rs.getTimestamp(4).toLocalDateTime();
            int thuTuGa = rs.getInt(5);
            double soKm = rs.getDouble(6);
            ChiTietChuyenTau chiTietChuyenTau = new ChiTietChuyenTau(chuyenTau,gaTau, thoiGianDen,thoiGianDi,thuTuGa,soKm );
            dsChiTietChuyenTau.add(chiTietChuyenTau);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return  dsChiTietChuyenTau;
    }
}
