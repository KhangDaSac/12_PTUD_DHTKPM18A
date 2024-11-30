package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChiTietHoaDonLayVe_DAO {
    public static ArrayList<ChiTietHoaDonLayVe> getDanhSachChiTietHoaDonLayVeTheoMaHoaDonLayVe(String maHoaDon){
        ArrayList<ChiTietHoaDonLayVe> dsChiTietHoaDonLayVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "EXEC timDanhSachChiTietHoaDonLayVeTheoMaHoaDon ?";
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
                ChiTietHoaDonLayVe chiTietHoaDonLayVe = null;
                for(ChiTietHoaDonLayVe cthd:dsChiTietHoaDonLayVe){
                    if (cthd.getHoaDonLayVe().getMaHoaDonLayVe().equals(rs.getString("maHoaDonLayVe"))){
                        chiTietHoaDonLayVe =cthd;
                    }
                    if(chiTietHoaDonLayVe== null){
                        chiTietHoaDonLayVe = new ChiTietHoaDonLayVe(new VeDat(rs.getString("maVeDat")),new Ve(rs.getString("maVe")),new HoaDonLayVe(rs.getString("maHoaDonLayVe")));
                        dsChiTietHoaDonLayVe.add(chiTietHoaDonLayVe);
                    }
                    chiTietHoaDonLayVe.getVeDat().addChiTietVeDat(chiTietVeDat);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dsChiTietHoaDonLayVe;
    }
}
