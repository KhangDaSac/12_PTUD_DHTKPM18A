package DAO;

import DTO.CaLamViec;
import DTO.HoaDonDatVe;
import DTO.KhachHang;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class HoaDonDatVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static ArrayList<HoaDonDatVe> getDanhSachHoaDonDatTheoMaKhachHang(String maKhachHang){
        ArrayList<HoaDonDatVe> danhSachHoaDonDat = new ArrayList<HoaDonDatVe>();
        try {
            String query = "exec UDP_GetDanhSachHoaDonDatTheoMaKhachHang ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maKhachHang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                HoaDonDatVe hoaDonDatVe = new HoaDonDatVe(
                        rs.getString("maHoaDonDatVe"),
                        rs.getTimestamp("thoiGianLap").toLocalDateTime(),
                        new CaLamViec(rs.getString("maCaLamViec"))
                );
                hoaDonDatVe.setDanhSachVeDat(VeDat_DAO.getDanhSachPhieuDatVeTheoMaHoaDonDatVe(hoaDonDatVe.getMaHoaDonDatVe()));
                danhSachHoaDonDat.add(hoaDonDatVe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachHoaDonDat;
    }

    public static String layMaHoaDonDatVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonLonNhat = null;
        try {
            String query = "select max(maHoaDonDatVe) as maHoaDonDatVe from HoaDonDatVe where maHoaDonDatVe like 'HDDV' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maHoaDonLonNhat = rs.getString("maHoaDonDatVe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoaDonLonNhat;
    }


    public static boolean themHoaDonDatVe(HoaDonDatVe hoaDonDatVe){
        try {
            String query = "insert into HoaDonDatVe values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, hoaDonDatVe.getMaHoaDonDatVe());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(hoaDonDatVe.getThoiGianLap()));
            statement.setString(3, hoaDonDatVe.getCaLamViec().getMaCaLamViec());
            statement.setString(4, hoaDonDatVe.getKhachHangDatVe().getMaKhachHang());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
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
