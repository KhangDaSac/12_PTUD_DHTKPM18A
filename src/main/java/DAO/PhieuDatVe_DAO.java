package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PhieuDatVe_DAO {
    public static ArrayList<PhieuDatVe> getDanhSachPhieuDatVeTheoMaHoaDon(String maHD){
        ArrayList<PhieuDatVe> phieuDatVeList = new ArrayList<PhieuDatVe>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "exec UDP_TimDanhSachPhieuDatVeTheoMaHoaDon ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maPhieuDatVe = rs.getString("maPhieuDatVe");
                HoaDon hoaDon = new HoaDon(rs.getString("maHoaDon"));
                ChiTietChuyenTau chiTietChuyenTauDi = new ChiTietChuyenTau(
                        new ChuyenTau(rs.getString("maChuyenTau")),
                        new GaTau(rs.getString("maGaDi"), rs.getString("tenGaDi")),
                        rs.getTimestamp("thoiGianDi").toLocalDateTime()
                        );

                ChiTietChuyenTau chiTietChuyenTauDen = new ChiTietChuyenTau(
                        new ChuyenTau(rs.getString("maChuyenTau")),
                        new GaTau(rs.getString("maGaDen"), rs.getString("tenGaDen")));
                double giamGiaVeTapThe = rs.getDouble("giamGiaVeTapThe");
                double tongTienVe = rs.getDouble("tongTienVe");
                double tongTienDatCoc = rs.getDouble("tongTienDatCoc");
                TrangThaiPhieuDatVe trangThaiPhieuDatVe = TrangThaiPhieuDatVe.valueOf(rs.getString("trangThaiPhieuDatVe"));
                LoaiPhieuDatVe loaiPhieuDatVe = LoaiPhieuDatVe.valueOf(rs.getString("loaiPhieuDatVe"));

                PhieuDatVe phieuDatVe = new PhieuDatVe(
                        maPhieuDatVe,
                        hoaDon,
                        chiTietChuyenTauDi,
                        chiTietChuyenTauDen,
                        giamGiaVeTapThe,
                        tongTienVe,
                        tongTienDatCoc,
                        trangThaiPhieuDatVe,
                        loaiPhieuDatVe
                );

                phieuDatVeList.add(phieuDatVe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phieuDatVeList;
    }
    public static ArrayList<String> getDanhSachMaPhieuDatVe(String maHoaDon){
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<String> dsMaPhieuDatVe = new ArrayList<>();
        try {
            String query = "SELECT maPhieuDatVe FROM PhieuDatVe WHERE maHoaDon = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dsMaPhieuDatVe.add(rs.getString("maPhieuDatVe"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsMaPhieuDatVe;
    }
}
