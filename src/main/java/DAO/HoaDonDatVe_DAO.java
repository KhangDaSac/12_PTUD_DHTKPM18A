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
            statement.setDate(2, Date.valueOf(thoigianLap));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                HoaDonDatVe hoaDonDatVe = new HoaDonDatVe();
                hoaDonDatVe.setMaHoaDonDatVe(rs.getString("maHoaDonDatVe"));
                hoaDonDatVe.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDonDatVe.setKhachHangDatVe(new KhachHang(rs.getString("maKhachHang"),rs.getString("tenKhachHang")));
                hoaDonDatVe.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDonDatVe.setDanhSachVeDat(VeDat_DAO.getDanhSachVeDatTheoMaHoaDonDat(rs.getString("maHoaDonDatVe")));
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
        String query = "select top 10 HDDV.maHoaDonDatVe, KH.maKhachHang,KH.tenKhachHang,HDDV.thoiGianLap,maCaLamViec FROM HoaDonDatVe as HDDV join KhachHang as KH on HDDV.maKhachHangDatVe = KH.maKhachHang  order by thoiGianLap DESC";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                HoaDonDatVe hoaDonDatVe = new HoaDonDatVe();
                hoaDonDatVe.setMaHoaDonDatVe(rs.getString("maHoaDonDatVe"));
                hoaDonDatVe.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDonDatVe.setKhachHangDatVe(new KhachHang(rs.getString("maKhachHang"),rs.getString("tenKhachHang")));
                hoaDonDatVe.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDonDatVe.setDanhSachVeDat(VeDat_DAO.getDanhSachVeDatTheoMaHoaDonDat(rs.getString("maHoaDonDatVe")));
                dsHoaDonDatVe.add(hoaDonDatVe);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsHoaDonDatVe;
    }
}
