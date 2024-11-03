package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class KhachHang_DAO {
    public KhachHang getKhachHangTheoCCCD(String cccd){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select maKhachHang, CCCD, tenKhachHang, soDienThoai, lkh.maLoaiKhachHang, tenLoaiKhachHang, phanTramGiamGia" +
                    " from KhachHang kh" +
                    " join LoaiKhachHang lkh on kh.maLoaiKhachHang = lkh.maLoaiKhachHang" +
                    " where CCCD = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, cccd);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String cccdKH = rs.getString("cccd");
                String soDienThoai = rs.getString("soDienThoai");
                String maLoaiKhachHang = rs.getString("maLoaiKhachHang");
                String tenLoaiKhachHang = rs.getString("tenLoaiKhachHang");
                double phanTramGiamGia = rs.getDouble("phanTramGiamGia");
                LoaiKhachHang loaiKhachHang = new LoaiKhachHang(maLoaiKhachHang, tenLoaiKhachHang, phanTramGiamGia);
                return new KhachHang(maKhachHang, cccdKH, tenKhachHang, soDienThoai, loaiKhachHang);
            }
        }catch (Exception e){

        }
        return null;
    }


    public ArrayList<KhachHang> xuatDanhSachKhachHang (){
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
        try {
            String query = "select * from KhachHang kh join LoaiKhachHang lkh on lkh.maLoaiKhachHang= kh.maLoaiKhachHang ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String maKhachHang = rs.getString("maKhachHang");
                String cCCD = rs.getString("CCCD");
                String tenKhachHang = rs.getString("tenKhachHang");
                String soDienThoai = rs.getString("soDienThoai");
                LoaiKhachHang loaiKhachHang = new LoaiKhachHang(rs.getString("maLoaiKhachHang"),rs.getString("tenLoaiKhachHang"),rs.getDouble("phanTramGiamGia"));
                LocalDate ngaySinh = rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null;
                //LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                KhachHang khachHang = new KhachHang(maKhachHang,cCCD, tenKhachHang,soDienThoai,ngaySinh, loaiKhachHang);
                dsKhachHang.add(khachHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  dsKhachHang;
    }

    public void suaThongTinKhachHang (KhachHang kh){
        Connection con = ConnectDB.getInstance().getConnection();
        try{
            String query = "update KhachHang SET CCCD = ?, tenKhachHang = ?, soDienThoai = ?, maLoaiKhachHang = ?, ngaySinh = ? WHERE maKhachHang = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,kh.getCCCD());
            statement.setString(2,kh.getTenKhachHang());
            //statement.setDate(3,Date.valueOf(kh.getNgaySinh()));
            statement.setString(3,kh.getSoDienThoai());

            statement.setString(4,kh.getLoaiKhachHang().getMaLoaiKhachHang());
            statement.setString(6,kh.getMaKhachHang());
            if (kh.getNgaySinh() != null) {
                statement.setDate(5, Date.valueOf(kh.getNgaySinh()));
            } else {
                statement.setNull(5, java.sql.Types.DATE);
            }
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public  void addKhachHang (KhachHang kh){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "insert into KhachHang values(?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            System.out.println(kh.getLoaiKhachHang()+"thêm");
            statement.setString(1,kh.getMaKhachHang());
            statement.setString(2,kh.getCCCD());
            statement.setString(3,kh.getTenKhachHang());
            statement.setDate(4,Date.valueOf(kh.getNgaySinh()));
            statement.setString(5,kh.getSoDienThoai());
            statement.setString(6,kh.getLoaiKhachHang().getMaLoaiKhachHang());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
