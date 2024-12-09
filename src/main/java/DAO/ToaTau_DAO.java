package DAO;

import DTO.ChuyenTau;
import DTO.LoaiToaTau;
import DTO.ToaTau;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ToaTau_DAO {
    public static Connection con = ConnectDB.getInstance().getConnection();;
    public static ToaTau timToaTauTheoMaChuyenTau(String maChuyenTau) {
        ToaTau toaTau = null;
        try {
            String query = "select * from ToaTau where maChuyenTau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maChuyenTau);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maToa = rs.getString("maToaTau");
                int thuTuToa = rs.getInt("thuTuToa");
                LoaiToaTau loaiToaTau = new LoaiToaTau(rs.getString("maLoaiToa"));
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
                toaTau = new ToaTau(maToa, thuTuToa, loaiToaTau, chuyenTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toaTau;
    }

    public ArrayList<ToaTau> xuatDanhSachToaTauTheoChuyen(String maChuyen) {
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<ToaTau> danhSachToaTau = new ArrayList<ToaTau>();
        try {
            String query = "select * from ToaTau where maChuyenTau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maChuyen);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maToa = rs.getString(1);
                int thuTuToa = rs.getInt(2);
                LoaiToaTau loaiToaTau = new LoaiToaTau(rs.getString(4));
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString(5));
                ToaTau toaTau = new ToaTau(maToa, thuTuToa, loaiToaTau, chuyenTau);
                danhSachToaTau.add(toaTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachToaTau;
    }


    public static ArrayList<ToaTau> getDanhSachToaTauTheoChuyen(String maChuyen, String maGaDi, String maGaDen) {
        ArrayList<ToaTau> danhSachToaTau = new ArrayList<ToaTau>();
        try {
            String query = "exec dbo.UDP_GetDanhSacToaTauTheo_GaDi_GaDen_MaChuyen ?, ?, ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maGaDi);
            statement.setString(2, maGaDen);
            statement.setString(3, maChuyen);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maToaTau = rs.getString("maToaTau");
                int thuTuToa = rs.getInt("thuTuToa");
                int soLuongChoDanhChoChangDaiHon = rs.getInt("danhChoChangDaiHon");
                int soLuongChoDaDat = rs.getInt("daDat");
                int soLuongChoDaBan = rs.getInt("daBan");
                int soLuongChoTrong = rs.getInt("conTrong");
                LoaiToaTau loaiToaTau = new LoaiToaTau(rs.getString("maLoaiToa"), rs.getString("tenLoaiToa"), rs.getDouble("heSoGiaToaTau"));
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
                ToaTau toaTau = new ToaTau(
                        maToaTau,
                        thuTuToa,
                        loaiToaTau,
                        chuyenTau,
                        soLuongChoDaBan,
                        soLuongChoDaDat,
                        soLuongChoDanhChoChangDaiHon,
                        soLuongChoTrong
                );
                danhSachToaTau.add(toaTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachToaTau;
    }
    public static ToaTau getToaTauTheoMa(String maToa){
        String query = "select * from ToaTau where maToaTau = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maToa);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                LoaiToaTau loaiToaTau = LoaiToaTau_DAO.timLoaiToaTauTheoMa(rs.getString("maLoaiToa"));
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
                ToaTau toaTau = new ToaTau(rs.getString("maToaTau"), rs.getInt("thuTuToa"), loaiToaTau, chuyenTau);
                return toaTau;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
