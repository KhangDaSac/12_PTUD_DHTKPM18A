package DAO;

import DTO.CaLamViec;
import DTO.HoaDonDatVe;
import DTO.KhachHang;
import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class HoaDonDatVe_DAO {
    public static ArrayList<HoaDonDatVe> getDanhSachHoaDonDatVeTheoCCCD(String CCCD, LocalDate thoigianLap) {
        ArrayList<HoaDonDatVe> dsHoaDonDatVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "exec timHoaDonDatVeTheoCCCDVaThoiGianLap ?,?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, CCCD);
            if (thoigianLap != null) {
                statement.setDate(2, Date.valueOf(thoigianLap));
            } else {
                statement.setNull(2, Types.DATE);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                HoaDonDatVe hoaDonDatVe = new HoaDonDatVe();
                hoaDonDatVe.setMaHoaDonDatVe(rs.getString("maHoaDonDatVe"));
                hoaDonDatVe.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDonDatVe.setKhachHangDatVe(new KhachHang(rs.getString("maKhachHang"),rs.getString("tenKhachHang")));
                hoaDonDatVe.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDonDatVe.setDanhSachVeDat(VeDat_DAO.getDanhSachVeDatOTrangThaiChoLayVeTheoMaHoaDonDat(rs.getString("maHoaDonDatVe")));
                dsHoaDonDatVe.add(hoaDonDatVe);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsHoaDonDatVe;
    }
    public static ArrayList<HoaDonDatVe> get10HoaDonDatGanNhat(){
        ArrayList<HoaDonDatVe> dsHoaDonDatVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "exec top10HoaDonDatVeCoVeOTrangThaiChoLayVe";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                HoaDonDatVe hoaDonDatVe = new HoaDonDatVe();
                hoaDonDatVe.setMaHoaDonDatVe(rs.getString("maHoaDonDatVe"));
                hoaDonDatVe.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDonDatVe.setKhachHangDatVe(new KhachHang_DAO().getKhachHangTheoMaKhachHang(rs.getString("maKhachHang")));
                hoaDonDatVe.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDonDatVe.setDanhSachVeDat(VeDat_DAO.getDanhSachVeDatOTrangThaiChoLayVeTheoMaHoaDonDat(rs.getString("maHoaDonDatVe")));
                dsHoaDonDatVe.add(hoaDonDatVe);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsHoaDonDatVe;
    }
    public static HoaDonDatVe getHoaDonDatVeTheoMa(String maHoaDon){
        HoaDonDatVe hoaDonDatVe = new HoaDonDatVe();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = " select * from HoaDonDatVe where maHoaDonDatVe = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                hoaDonDatVe.setMaHoaDonDatVe(rs.getString("maHoaDonDatVe"));
                hoaDonDatVe.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDonDatVe.setKhachHangDatVe(KhachHang_DAO.getKhachHangTheoMaKhachHang(rs.getString("maKhachHangDatVe")));
                hoaDonDatVe.setCaLamViec(CaLamViec_DAO.getCaLamViecTheoMa(rs.getString("maCaLamViec")));
                hoaDonDatVe.setDanhSachVeDat(VeDat_DAO.getDanhSachVeDatTheoMaHoaDonDat(rs.getString("maHoaDonDatVe")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hoaDonDatVe;
    }
}
