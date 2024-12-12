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
    private static Connection con = ConnectDB.getInstance().getConnection();

    public static ChiTietChuyenTau getChiTietTuyenTauTheoChuyenTauVaGaTau(String maChuyenTau, String maGaTau){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select * \n" +
                    "from ChiTietChuyenTau\n" +
                    "where maChuyenTau = ? and maGaTau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maChuyenTau);
            statement.setString(2, maGaTau);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
                GaTau gaTau = GaTau_DAO.getGaTauTheoMaGaTau(rs.getString("maGaTau"));
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

    public static ArrayList<ChiTietChuyenTau> getLichTrinhTheoMaChuyenTau(String maChuyenTau){
        ArrayList<ChiTietChuyenTau> lichTrinh = new ArrayList<ChiTietChuyenTau>();
        try {
            String query = "EXEC UDP_GetLichTrinhTheoMaChuyenTau ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maChuyenTau);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
                GaTau gaTau = new GaTau(
                        rs.getString("maGaTau"),
                        rs.getString("tenGaTau"),
                        rs.getString("diaChi")
                );
                LocalDateTime thoiGianDen = rs.getTimestamp("thoiGianDen").toLocalDateTime();
                LocalDateTime thoiGianDi = rs.getTimestamp("thoiGianDi").toLocalDateTime();
                int thuTuGa = rs.getInt("thuTuGa");
                double soKm = rs.getDouble("soKm");

                ChiTietChuyenTau chiTietChuyenTau = new ChiTietChuyenTau(chuyenTau, gaTau, thoiGianDen, thoiGianDi, thuTuGa, soKm);
                lichTrinh.add(chiTietChuyenTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lichTrinh;
    }
}
