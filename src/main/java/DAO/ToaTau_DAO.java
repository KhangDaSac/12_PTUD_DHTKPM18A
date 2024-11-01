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
    public ArrayList<ToaTau> xuatDanhSachToaTau() {
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<ToaTau> danhSachToaTau = new ArrayList<ToaTau>();
        try {
            String query = "select * from ToaTau";
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query);
             while (rs.next()) {
                 String maToa = rs.getString(1);
                 int thuTuToa = rs.getInt(2);
                 int soLuongCho = rs.getInt(3);
                 LoaiToaTau loaiToaTau = new LoaiToaTau(rs.getString(4));
                 ChuyenTau chuyenTau = new ChuyenTau(rs.getString(5));
                 ToaTau toaTau = new ToaTau(maToa, thuTuToa, soLuongCho, loaiToaTau, chuyenTau);
                 danhSachToaTau.add(toaTau);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachToaTau;
    }

    public ToaTau timToaTauTheoMa(String maToaTau) {
        Connection con = ConnectDB.getInstance().getConnection();
        ToaTau toaTau = null;
        try {
            String query = "select * from ToaTau where maToa = ?";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String maToa = rs.getString(1);
                int thuTuToa = rs.getInt(2);
                int soLuongCho = rs.getInt(3);
                LoaiToaTau loaiToaTau = new LoaiToaTau(rs.getString(4));
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString(5));
                toaTau = new ToaTau(maToa, thuTuToa, soLuongCho, loaiToaTau, chuyenTau);
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
            String query = "select * from ToaTau where maChuyen = ?";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String maToa = rs.getString(1);
                int thuTuToa = rs.getInt(2);
                int soLuongCho = rs.getInt(3);
                LoaiToaTau loaiToaTau = new LoaiToaTau(rs.getString(4));
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString(5));
                ToaTau toaTau = new ToaTau(maToa, thuTuToa, soLuongCho, loaiToaTau, chuyenTau);
                danhSachToaTau.add(toaTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachToaTau;
    }
    public ArrayList<ToaTau> xuatDanhSachToaTheoLoai(String maLoaiToa) {
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<ToaTau> danhSachToaTau = new ArrayList<ToaTau>();
        try {
            String query = "select * from ToaTau where maLoaiToa = ?";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String maToa = rs.getString(1);
                int thuTuToa = rs.getInt(2);
                int soLuongCho = rs.getInt(3);
                LoaiToaTau loaiToaTau = new LoaiToaTau(rs.getString(4));
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString(5));
                ToaTau toaTau = new ToaTau(maToa, thuTuToa, soLuongCho, loaiToaTau, chuyenTau);
                danhSachToaTau.add(toaTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachToaTau;
    }


    public ArrayList<ToaTau> getDanhSachToaTauTheoChuyen(String maChuyen, String maGaDi, String maGaDen) {
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<ToaTau> danhSachToaTau = new ArrayList<ToaTau>();
        try {
            String query = "exec dbo.UDP_TimDanhSachToaTauTheoMaChuyenTau ?, ?, ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maChuyen);
            statement.setString(2, maGaDi);
            statement.setString(3, maGaDen);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maToaTau = rs.getString("maToaTau");
                int thuTuToa = rs.getInt("thuTuToa");
                int soLuongCho = rs.getInt("soLuongCho");
                int soLuongChoDanhChoChangDaiHon = rs.getInt("soLuongChoDanhChoChangDaiHon");
                int soLuongChoDaDatVaBan = rs.getInt("soLuongChoDaDatVaBan");
                int soLuongChoTrong = rs.getInt("soLuongChoTrong");
                LoaiToaTau loaiToaTau = new LoaiToaTau(rs.getString("maLoaiToa"), rs.getString("tenLoaiToa"), rs.getDouble("heSoGia"));
                ChuyenTau chuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));

                ToaTau toaTau = new ToaTau(maToaTau, thuTuToa, soLuongCho, loaiToaTau, chuyenTau, soLuongChoDanhChoChangDaiHon, soLuongChoDaDatVaBan, soLuongChoTrong);
                danhSachToaTau.add(toaTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachToaTau;
    }
}
