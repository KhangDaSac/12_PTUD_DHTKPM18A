package DAO;

import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import DTO.GaTau;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public ChiTietChuyenTau getChiTietTuyenTauTheoChuyenTauVaGaTau(String maChuyenTau, String maGaTau){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select maChuyenTau, gt.maGaTau, tenGaTau, thoiGianDi, thoiGianDen, thuTuGa, soKm" +
                    " from ChiTietChuyenTau ctct" +
                    " join GaTau gt on ctct.maGaTau = gt.maGaTau" +
                    " where maChuyenTau = ? and gt.maGaTau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maChuyenTau);
            statement.setString(2, maGaTau);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
                GaTau gaTau = new GaTau(rs.getString("maGaTau"), rs.getString("tenGaTau"));
                LocalDateTime thoiGianDen = rs.getTimestamp("thoiGianDen").toLocalDateTime();
                LocalDateTime thoiGianDi = rs.getTimestamp("thoiGianDi").toLocalDateTime();
                int thuTuGa = rs.getInt("thuTuGa");
                double soKm = rs.getDouble("soKm");

                ChiTietChuyenTau chiTietChuyenTau = new ChiTietChuyenTau(chuyenTau, gaTau, thoiGianDen, thoiGianDi, thuTuGa, soKm);
                return chiTietChuyenTau;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
