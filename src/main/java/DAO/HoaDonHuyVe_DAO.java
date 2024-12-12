package DAO;

import DTO.CaLamViec;
import DTO.HoaDonHuyVe;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HoaDonHuyVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static HoaDonHuyVe getHoaDonHuyVeTheoMa(String maHoaDonHuyVe) {
        String query = "select * from HoaDonHuyVe where maHoaDonHuyVe = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDonHuyVe);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                HoaDonHuyVe hoaDonHuyVe = new HoaDonHuyVe();
                hoaDonHuyVe.setMaHoaDonHuyVe(rs.getString("maHoaDonHuyVe"));
                hoaDonHuyVe.setThoiGianHuyVe(rs.getTimestamp("thoiGianHuyVe ").toLocalDateTime());
                hoaDonHuyVe.setKhachHangHuyVe(KhachHang_DAO.getKhachHangTheoMaKhachHang(rs.getString("maKhachHangHuyVe")));
                hoaDonHuyVe.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDonHuyVe.setDanhSachChiTietHoaDonHuyVe(ChiTietHoaDonHuyVe_DAO.getDanhSachChiTietHoaDonHuyVeTheoMaHoaDonHuyVe(hoaDonHuyVe.getMaHoaDonHuyVe()));
                return hoaDonHuyVe;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
