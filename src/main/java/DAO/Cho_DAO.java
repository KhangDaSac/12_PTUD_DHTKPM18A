package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Cho_DAO {
    public ArrayList<Cho> xuatDanhSachCho() {
        ArrayList<Cho> danhSachCho = new ArrayList<Cho>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select * from Cho";
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query);
             while (rs.next()) {
                 String maCho = rs.getString(1);
                int soCho = rs.getInt(2);
                double doDaiChangToiThieu = rs.getDouble(3);
                 LoaiCho loaiCho = new LoaiCho(rs.getString(4));
                 ToaTau toaTau = new ToaTau(rs.getString(5));
                    Cho cho = new Cho(maCho, soCho, doDaiChangToiThieu, toaTau, loaiCho);
                 danhSachCho.add(cho);
             }
        } catch (Exception e) {
            e.printStackTrace();
    }
        return null;
    }

    public static Cho timChoTheoMaCho(String maChoTim) {
        Connection con = ConnectDB.getInstance().getConnection();
        Cho cho = null;
        try {
            String query = "select * from Cho where maCho = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maChoTim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maCho = rs.getString("maCho");
                int soCho = rs.getInt("soCho");
                ToaTau toaTau = new ToaTau(rs.getString("maToaTau"));
                //double giacho =rs.getDouble("giaCho");
                cho = new Cho(toaTau,maCho, soCho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cho;
    }

    public static void setChoTheoMaChovaMaToa(){
        Connection con = ConnectDB.getInstance().getConnection();
        try{

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cho> xuatDanhSachChoTheoLoai(String maLoaiCho) {
        ArrayList<Cho> danhSachCho = new ArrayList<Cho>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select * from Cho where maLoaiCho = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maLoaiCho);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maCho = rs.getString(1);
                int soCho = rs.getInt(2);
                double doDaiChangToiThieu = rs.getDouble(3);
                LoaiCho loaiCho = new LoaiCho(rs.getString(4));
                ToaTau toaTau = new ToaTau(rs.getString(5));
                Cho cho = new Cho(maCho, soCho, doDaiChangToiThieu, toaTau, loaiCho);
                danhSachCho.add(cho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachCho;
    }
    public ArrayList<Cho> xuatDanhSachChoTheoToa(String maToa) {
        ArrayList<Cho> danhSachCho = new ArrayList<Cho>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select * from Cho where maToa = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maToa);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maCho = rs.getString(1);
                int soCho = rs.getInt(2);
                double doDaiChangToiThieu = rs.getDouble(3);
                LoaiCho loaiCho = new LoaiCho(rs.getString(4));
                ToaTau toaTau = new ToaTau(rs.getString(5));
                Cho cho = new Cho(maCho, soCho, doDaiChangToiThieu, toaTau, loaiCho);
                danhSachCho.add(cho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachCho;
    }

    public ArrayList<Cho> getDanhSachChoTheoMaToaTau(String maToaTau, String gaDi, String gaDen){
        ArrayList<Cho> danhSachCho = new ArrayList<Cho>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "exec dbo.UDP_GetDanhSachChoTheo_GaDi_GaDen_MaToa ?, ?, ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, gaDi);
            statement.setString(2, gaDen);
            statement.setString(3, maToaTau);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maCho = rs.getString("maCho");
                int soCho = rs.getInt("soCho");
                LoaiCho loaiCho = new LoaiCho(rs.getString("maLoaiCho"), rs.getString("tenLoaiCho"), rs.getDouble("heSoGiaCho"));
                LoaiToaTau loaiToaTau = new LoaiToaTau(rs.getString("maLoaiToa"), rs.getString("tenLoaiToa"), rs.getDouble("heSoGiaToaTau"));
                ToaTau toaTau = new ToaTau(rs.getString("maToaTau"), rs.getInt("thuTuToa"), loaiToaTau);
                TrangThaiCho trangThaiCho = TrangThaiCho.values()[rs.getInt("trangThaiCho")];
                Cho cho = new Cho(maCho, soCho, toaTau, loaiCho, trangThaiCho);
                danhSachCho.add(cho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachCho;
    }

}
