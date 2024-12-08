package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietHoaDonHuyDatVe_DAO {
    public static ArrayList<ChiTietHoaDonHuyDatVe> getDanhSachChiTietHoaDonHuyDatVeTheoMaHoaDonHuyDatVe(String maHoaDon){
        ArrayList<ChiTietHoaDonHuyDatVe> dsChiTietHoaDonHuyDatVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "EXEC timDanhSachChiTietHoaDonHuyDatVeTheoMaHoaDonHuyDatVe ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                HoaDonDatVe hoaDonDatVe = new HoaDonDatVe(rs.getString("maHoaDonDatVe"));
                ChiTietChuyenTau thongTinGaTauDi = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),new GaTau(rs.getString("maGaDi")), rs.getTimestamp("thoiGianDi").toLocalDateTime());
                ChiTietChuyenTau thongTinGaTauDen = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),new GaTau(rs.getString("maGaDen")), rs.getTimestamp("thoiGianDen").toLocalDateTime());
                VeDat veDat = new VeDat(rs.getString("maVeDat"),hoaDonDatVe,thongTinGaTauDi,thongTinGaTauDen,TrangThaiVeDat.valueOf(rs.getString("trangThaiVeDat")),LoaiVe.valueOf(rs.getString("loaiVe")));
                ChiTietVeDat chiTietVeDat = new ChiTietVeDat(new Cho(rs.getString("maCho")),veDat,rs.getDouble("giaCho"),new KhachHang(rs.getString("maKhachHang")),rs.getDouble("phanTramGiamGia"));
                ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe = null;
                for(ChiTietHoaDonHuyDatVe cthd:dsChiTietHoaDonHuyDatVe) {
                    if (cthd.getHoaDonHuyDatVe().getMaHoaDonHuyDatVe().equals(rs.getString("maHoaDonHuyDatVe"))) {
                        chiTietHoaDonHuyDatVe = cthd;
                    }
                }
                if(chiTietHoaDonHuyDatVe== null){
                    chiTietHoaDonHuyDatVe = new ChiTietHoaDonHuyDatVe(new HoaDonHuyDatVe(rs.getString("maHoaDonHuyDatVe")),veDat,rs.getDouble("phanTramLePhi"));
                    dsChiTietHoaDonHuyDatVe.add(chiTietHoaDonHuyDatVe);
                }
                chiTietHoaDonHuyDatVe.getVeDat().addChiTietVeDat(chiTietVeDat);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dsChiTietHoaDonHuyDatVe;
    }
}
