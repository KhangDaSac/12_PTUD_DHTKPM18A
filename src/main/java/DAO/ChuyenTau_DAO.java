package DAO;

import DTO.ChuyenTau;
import DTO.TuyenTau;
import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChuyenTau_DAO {
    public ArrayList<ChuyenTau> xuatDanhSachChuyenTau(){
        ArrayList<ChuyenTau> dsChuyenTau= new ArrayList<ChuyenTau>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query ="select * from ChuyenTau";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                String maChuyenTau =rs.getString(1);
                TuyenTau tuyenTau = new TuyenTau(rs.getString(2));
                int soLuongCho =  rs.getInt(3);
             ChuyenTau chuyenTau = new ChuyenTau(maChuyenTau, tuyenTau, soLuongCho);
                dsChuyenTau.add(chuyenTau);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dsChuyenTau;
    }

    public ChuyenTau timChuyenTauTheoMa(String maChuyen) {
        Connection con = ConnectDB.getInstance().getConnection();
        ChuyenTau chuyenTau = null;
        try {
            String query = "select * from ChuyenTau where maChuyenTau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maChuyen);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String maChuyenTau = rs.getString(1);
                TuyenTau tuyenTau = new TuyenTau(rs.getString(2));
                int soLuongCho = rs.getInt(4);
                chuyenTau = new ChuyenTau(maChuyenTau, tuyenTau, soLuongCho);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chuyenTau;
    }
    public void suaThoiGianKhoiHanh (LocalDateTime thoiGianKhoiHanh, String maChuyen){
        Connection con = ConnectDB.getInstance().getConnection();
        try{
            String query = "update ChuyenTau set ngayKhoiHanh = ? where maChuyenTau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            Timestamp thoiGian = Timestamp.valueOf(thoiGianKhoiHanh);
            statement.setTimestamp(1,thoiGian);
            statement.setString(2,maChuyen);
            statement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<ChuyenTau> getDanhSachChuyenTau(String maGaDi, String maGaDen, LocalDate ngayDi){
        ArrayList<ChuyenTau> dsChuyenTau= new ArrayList<ChuyenTau>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query ="exec timDanhSachChuyenTau ?, ?, ?, ?, ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maGaDi);
            statement.setString(2, maGaDen);
            statement.setInt(3, ngayDi.getDayOfMonth());
            statement.setInt(4, ngayDi.getMonthValue());
            statement.setInt(5, ngayDi.getYear());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String maChuyenTau =rs.getString("maChuyenTau");
                int soLuongCho = rs.getInt("soLuongCho");
                LocalDateTime thoiGianDi = rs.getTimestamp("thoiGianDi").toLocalDateTime();
                LocalDateTime thoiGianDen = rs.getTimestamp("thoiGianDen").toLocalDateTime();
                ChuyenTau chuyenTau = new ChuyenTau(maChuyenTau, soLuongCho, thoiGianDi, thoiGianDen);
                dsChuyenTau.add(chuyenTau);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dsChuyenTau;
    }

}
