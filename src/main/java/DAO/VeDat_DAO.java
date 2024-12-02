package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VeDat_DAO {
    public static ArrayList<VeDat> getDanhSachPhieuDatVeTheoMaHoaDon(String maHD) {
        ArrayList<VeDat> phieuDatVeList = new ArrayList<VeDat>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "exec UDP_TimDanhSachPhieuDatVeTheoMaHoaDon ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maPhieuDatVe = rs.getString("maPhieuDatVe");
                HoaDonBanVe hoaDonBanVe = new HoaDonBanVe(rs.getString("maHoaDon"));
                ChiTietChuyenTau chiTietChuyenTauDi = new ChiTietChuyenTau(
                        new ChuyenTau(rs.getString("maChuyenTau")),
                        new GaTau(rs.getString("maGaDi"), rs.getString("tenGaDi")),
                        rs.getTimestamp("thoiGianDi").toLocalDateTime()
                );

                ChiTietChuyenTau chiTietChuyenTauDen = new ChiTietChuyenTau(
                        new ChuyenTau(rs.getString("maChuyenTau")),
                        new GaTau(rs.getString("maGaDen"), rs.getString("tenGaDen")));
                double phanTramGiamGiaVeTapThe = rs.getDouble("phanTramGiamGiaVeTapThe");
                double tongTienVe = rs.getDouble("tongTienVe");
                double tongTienDatCoc = rs.getDouble("tongTienDatCoc");
                TrangThaiVeDat trangThaiPhieuDatVe = TrangThaiVeDat.valueOf(rs.getString("trangThaiPhieuDatVe"));
                LoaiVe loaiPhieuDatVe = LoaiVe.valueOf(rs.getString("loaiPhieuDatVe"));

//                VeDat phieuDatVe = new VeDat(
//                        maPhieuDatVe,
//                        hoaDonBanVe,
//                        chiTietChuyenTauDi,
//                        chiTietChuyenTauDen,
//                        phanTramGiamGiaVeTapThe,
//                        tongTienVe,
//                        tongTienDatCoc,
//                        trangThaiPhieuDatVe,
//                        loaiPhieuDatVe
//                );

                //phieuDatVeList.add(phieuDatVe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phieuDatVeList;
    }

    public static boolean capNhatTrangThaiPhieuDatVe(String maPhieuDatVe, String trangThai){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "UPDATE PhieuDatVe SET trangThaiPhieuDatVe = ? WHERE maPhieuDatVe = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, trangThai);
            statement.setString(2, maPhieuDatVe);
            statement.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void huyPhieuDatVe(String maPhieuDatVe) {
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "update PhieuDatVe set trangThaiPhieuDatVe = 'DAHUY' where maPhieuDatVe = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maPhieuDatVe);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
