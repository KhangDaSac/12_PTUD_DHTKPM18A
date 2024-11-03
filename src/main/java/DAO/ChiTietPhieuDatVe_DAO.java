package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietPhieuDatVe_DAO {
    public static ArrayList<ChiTietPhieuDatVe> getDanhSachChiTietPhieuDatVeTheoMaHoaDon(String maHD){
        ArrayList<ChiTietPhieuDatVe> chiTietPhieuDatVeList = new ArrayList<ChiTietPhieuDatVe>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "exec UDP_TimDanhSachChiTietPhieuDatVeThaoMaHoaDon ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                ToaTau toaTau = new ToaTau(
                        rs.getString("maToaTau"),
                        rs.getInt("thuTuToa")
                );

                Cho cho = new Cho(
                        rs.getString("soCho"),
                        rs.getInt("thuTuToa"),
                        toaTau
                );

                LoaiKhachHang loaiKhachHang = new LoaiKhachHang(
                        rs.getString("maLoaiKhachHang"),
                        rs.getString("tenLoaiKhachHang"),
                        rs.getDouble("phanTramGiamGia"));

                KhachHang khachHang = new KhachHang(
                        rs.getString("maKhachHang"),
                        rs.getString("CCCD"),
                        rs.getString("tenKhachHang"),
                        loaiKhachHang
                );

                double giaCho = rs.getDouble("giaCho");
                double soTienGiamGia = rs.getDouble("soTienGiamGia");
                double thanhTien = rs.getDouble("thanhTien");
                PhieuDatVe phieuDatVe = new PhieuDatVe(
                        rs.getString("maPhieuDatVe"),
                        LoaiPhieuDatVe.valueOf(rs.getString("loaiPhieuDatVe"))
                );

                ChiTietPhieuDatVe chiTietPhieuDatVe = new ChiTietPhieuDatVe(
                        giaCho,
                        cho,
                        phieuDatVe,
                        khachHang,
                        soTienGiamGia,
                        thanhTien
                );

                chiTietPhieuDatVeList.add(chiTietPhieuDatVe);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietPhieuDatVeList;
    }
    public static ArrayList<ChiTietPhieuDatVe> getDanhSachChiTietPhieuDatVeTheoMaPhieuDatVe(String maPDV){
        ArrayList<ChiTietPhieuDatVe> chiTietPhieuDatVeList = new ArrayList<ChiTietPhieuDatVe>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "exec UDP_TimDanhSachChiTietPhieuDatVeTheoMaPhieuDatVe ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maPDV);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                ToaTau toaTau = new ToaTau(
                        rs.getString("maToaTau"),
                        rs.getInt("thuTuToa")
                );

                Cho cho = new Cho(
                        rs.getString("soCho"),
                        rs.getInt("thuTuToa"),
                        toaTau
                );

                LoaiKhachHang loaiKhachHang = new LoaiKhachHang(
                        rs.getString("maLoaiKhachHang"),
                        rs.getString("tenLoaiKhachHang"),
                        rs.getDouble("phanTramGiamGia"));

                KhachHang khachHang = new KhachHang(
                        rs.getString("maKhachHang"),
                        rs.getString("CCCD"),
                        rs.getString("tenKhachHang"),
                        loaiKhachHang
                );

                double giaCho = rs.getDouble("giaCho");
                double soTienGiamGia = rs.getDouble("soTienGiamGia");
                double thanhTien = rs.getDouble("thanhTien");
                PhieuDatVe phieuDatVe = new PhieuDatVe(
                        rs.getString("maPhieuDatVe"),
                        LoaiPhieuDatVe.valueOf(rs.getString("loaiPhieuDatVe"))
                );

                ChiTietPhieuDatVe chiTietPhieuDatVe = new ChiTietPhieuDatVe(
                        giaCho,
                        cho,
                        phieuDatVe,
                        khachHang,
                        soTienGiamGia,
                        thanhTien
                );

                chiTietPhieuDatVeList.add(chiTietPhieuDatVe);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietPhieuDatVeList;
    }
}
