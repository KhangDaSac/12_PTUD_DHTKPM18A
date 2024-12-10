package DAO;

import DTO.HoaDonHuyDatVe;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class HoaDonHuyDatVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static String layMaHoaDonHuyDatVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonLonNhat = null;
        try {
            String query = "select max(maHoaDonHuyDatVe) as maHoaDonHuyDatVe from HoaDonHuyDatVe where maHoaDonHuyDatVe like 'HDHD' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maHoaDonLonNhat = rs.getString("maHoaDonHuyDatVe");
            }
        } catch (Exception e) {
                throw new RuntimeException(e);
        }
        return maHoaDonLonNhat;
    }

    public static boolean themHoaDonHuyDatVe(HoaDonHuyDatVe hoaDonHuyDatVe){
        try{
            String query = "insert into HoaDonHuyDatVe values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, hoaDonHuyDatVe.getMaHoaDonHuyDatVe());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(hoaDonHuyDatVe.getThoiGianHuy()));
            statement.setString(3,hoaDonHuyDatVe.getCaLamViec().getMaCaLamViec());
            statement.setString(4, hoaDonHuyDatVe.getKhachHangHuyDatVe().getMaKhachHang());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static HoaDonHuyDatVe getHoaDonHuyDatVeTheoMa(String maHoaDon){
        String query = "select * from HoaDonHuyDatVe where maHoaDonHuyDatVe = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                HoaDonHuyDatVe hoaDonHuyDatVe = new HoaDonHuyDatVe();
                hoaDonHuyDatVe.setMaHoaDonHuyDatVe(rs.getString("maHoaDonHuyDatVe"));
                hoaDonHuyDatVe.setThoiGianHuy(rs.getTimestamp("thoiGianHuy").toLocalDateTime());
                hoaDonHuyDatVe.setCaLamViec(new CaLamViec_DAO().getCaLamViecTheoMa(rs.getString("maCaLamViec")));
                hoaDonHuyDatVe.setKhachHangHuyDatVe(new KhachHang_DAO().getKhachHangTheoMaKhachHang(rs.getString("maKhachHangHuyDatVe")));
                hoaDonHuyDatVe.setDanhSachChiTietHoaDonHuyDatVe(ChiTietHoaDonHuyDatVe_DAO.getDanhSachChiTietHoaDonHuyDatVeTheoMaHoaDonHuyDatVe(hoaDonHuyDatVe.getMaHoaDonHuyDatVe()));
                return hoaDonHuyDatVe;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
