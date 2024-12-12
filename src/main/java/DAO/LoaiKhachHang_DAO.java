package DAO;

import DTO.KhachHang;
import DTO.LoaiKhachHang;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoaiKhachHang_DAO {
    Connection con = ConnectDB.getInstance().getConnection();
    ArrayList<LoaiKhachHang> dsLoaiKhachHang = new ArrayList<>();

    public ArrayList<LoaiKhachHang> xuatDanhSachLoaiKhachHang() {
        try {
            String query = "select * from LoaiKhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String maLoaiKhachHang = rs.getString("maLoaiKhachHang");
            String tenLoaiKhachHang = rs.getString("tenLoaiKhachHang");
            double phamTramGiamGia = rs.getDouble("phamTramGiamGia");
            LoaiKhachHang loaiKhachHang = new LoaiKhachHang(maLoaiKhachHang, tenLoaiKhachHang, phamTramGiamGia);
            dsLoaiKhachHang.add(loaiKhachHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  dsLoaiKhachHang;
    }
//    public ArrayList<LoaiKhachHang> xuatDSvaoTBL () {
//        try {
//            String query = "select kh.maKhachHang,kh.ngaySinh, kh.CCCD,kh.tenKhachHang,kh.soDienThoai,lkh.maLoaiKhachHang,lkh.phanTramGiamGia from KhachHang kh\n" +
//                    "join LoaiKhachHang lkh on kh.maLoaiKhachHang = lkh.maLoaiKhachHang";
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                String maKhachHang = rs.getString("maKhachHang");
//                String cCCD = rs.getString("CCCD");
//                String tenKhachHang = rs.getString("tenKhachHang");
//                String soDienThoai = rs.getString("soDienThoai");
//                LoaiKhachHang loaiKhachHang = new LoaiKhachHang("maLoaiKhachHang");
//                LocalDate ngaySinh = rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null;
////                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
//                String maLoaiKhachHang = rs.getString("maLoaiKhachHang");
//                double phamTramGiamGia = rs.getDouble("phanTramGiamGia");
//                LoaiKhachHang loaikhachHang = new LoaiKhachHang(maKhachHang, cCCD, tenKhachHang, soDienThoai, loaiKhachHang, ngaySinh, maLoaiKhachHang, phamTramGiamGia);
//                dsLoaiKhachHang.add(loaiKhachHang);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsLoaiKhachHang;
//    }
public static LoaiKhachHang getLoaiKhachHangTheoMaKhachHang(String maKhachHang) {
    Connection con = ConnectDB.getInstance().getConnection();
    LoaiKhachHang loaiKhachHang = null;
    try {
        String query = "select * from LoaiKhachHang as LKH  join KhachHang as KH on KH.maLoaiKhachHang = LKH.maLoaiKhachHang where KH.maKhachHang = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, maKhachHang);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            loaiKhachHang = new LoaiKhachHang(rs.getString("maLoaiKhachHang"), rs.getString("tenLoaiKhachHang"), rs.getDouble("phanTramGiamGia"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return loaiKhachHang;
}
}
