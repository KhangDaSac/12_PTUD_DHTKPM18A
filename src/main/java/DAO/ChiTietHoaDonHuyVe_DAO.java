package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietHoaDonHuyVe_DAO {
    public static ArrayList<ChiTietHoaDonHuyVe> getDanhSachChiTietHoaDonHuyVeTheoMaHoaDonHuyVe(String maHoaDon){
        ArrayList<ChiTietHoaDonHuyVe> dsChiTietHoaDonHuyVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "EXEC timDanhSachChiTietHoaDonHuyVeTheoMaHoaDonHuyVe ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                HoaDonBanVe hoaDonBanVe = new HoaDonBanVe(rs.getString("maHoaDonBanVe"));
                ChiTietChuyenTau thongTinGaTauDi = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),new GaTau(rs.getString("maGaDi")), rs.getTimestamp("thoiGianDi").toLocalDateTime());
                ChiTietChuyenTau thongTinGaTauDen = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),new GaTau(rs.getString("maGaDen")), rs.getTimestamp("thoiGianDen").toLocalDateTime());
                Ve ve = new Ve(rs.getString("maVe"),hoaDonBanVe,thongTinGaTauDi,thongTinGaTauDen,LoaiVe.valueOf("loaiVe"),TrangThaiVe.valueOf("trangThaiVe"));
                ChiTietVe chiTietVe = new ChiTietVe(ve,new Cho(rs.getString("maCho")),new KhachHang(rs.getString("maKhachHang")),rs.getDouble("giaCho"),rs.getDouble("phanTramGiamGia"));
                ChiTietHoaDonHuyVe chiTietHoaDonHuyVe = null;
                for(ChiTietHoaDonHuyVe cthd:dsChiTietHoaDonHuyVe) {
                    if (cthd.getHoaDonHuyVe().getMaHoaDonHuyVe().equals(rs.getString("maHoaDonHuyVe"))) {
                        chiTietHoaDonHuyVe = cthd;
                    }
                }
                if(chiTietHoaDonHuyVe== null){
                    chiTietHoaDonHuyVe = new ChiTietHoaDonHuyVe(new HoaDonHuyVe(rs.getString("maHoaDonHuyVe")),ve,rs.getDouble("phanTramLePhi"));
                    dsChiTietHoaDonHuyVe.add(chiTietHoaDonHuyVe);
                }
                chiTietHoaDonHuyVe.getVe().addChiTietVe(chiTietVe);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dsChiTietHoaDonHuyVe;
    }
}
