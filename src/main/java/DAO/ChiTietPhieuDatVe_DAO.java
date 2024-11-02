package DAO;

import DTO.ChiTietPhieuDatVe;
import DTO.PhieuDatVe;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietPhieuDatVe_DAO {
//    public static ArrayList<PhieuDatVe> getDanhSachChiTietPhieuDatVeTheoMaHoaDon(String maHD){
//        ArrayList<ChiTietPhieuDatVe> chiTietPhieuDatVeList = new ArrayList<ChiTietPhieuDatVe>();
//        Connection con = ConnectDB.getInstance().getConnection();
//        try {
//            String query = "exec UDP_TimDanhSachChiTietPhieuDatVeThaoMaHoaDon ?";
//            PreparedStatement statement = con.prepareStatement(query);
//            statement.setString(1, maHD);
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                String maPhieuDatVe = rs.getString("maPhieuDatVe");
//                String maCho = rs.getString("maCho");
//                String maKhachHang = rs.getString("maKhachHang");
//                double giaCho = rs.getDouble("giaCho");
//                double soTienGiamGia = rs.getDouble("soTienGiamGia");
//                double thanhTien = rs.getDouble("thanhTien");
//                String maHoaDon = rs.getString("maHoaDon");
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
