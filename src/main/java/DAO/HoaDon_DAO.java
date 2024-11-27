package DAO;

import DTO.*;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Connection;

public class HoaDon_DAO {

//    public HoaDonBanVe getHoaDonTheoMa(String ma){
//        Connection con = ConnectDB.getInstance().getConnection();
//        HoaDonBanVe hoaDon = new HoaDonBanVe();
//        try{
//            String query = "SELECT * FROM HoaDon WHERE maHoaDon = ?";
//            PreparedStatement statement = con.prepareStatement(query);
//            statement.setString(1,ma);
//            ResultSet rs = statement.executeQuery();
//            statement.execute();
//            while (rs.next()){
//                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
//                hoaDon.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
//                hoaDon.setTongTien(rs.getDouble("tongTien"));
//                hoaDon.setTongTienDaDatCoc(rs.getDouble("tongTienDaDatCoc"));
//                hoaDon.setTongTienKhachHangTra(rs.getDouble("tongTienKhachHangTra"));
//                hoaDon.setTrangThaiHoaDon(TrangThaiHoaDonDat.valueOf(rs.getString("trangThaiHoaDon")));
//                hoaDon.setLoaiHoaDon(LoaiHoaDon.valueOf(rs.getString("loaiHoaDon")));
//                hoaDon.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
//                hoaDon.setKhachHangMua(new KhachHang(rs.getString("maKhachhangMua")));
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return hoaDon;
//    }
    public ArrayList<HoaDonBanVe> getDSHoaDonTheoCacTieuChi(String maHoaDon, String maKhachHang, String maCaLam, String trangThai, String loaiHoaDon, LocalDate thoiGianLap) {
        ArrayList<HoaDonBanVe> dsHoaDon = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
//        try {
//            String query = "exec timHoaDonTheoCacTieuChi ?,?,?,?,?,?";
//            PreparedStatement statement = con.prepareStatement(query);
//
//            statement.setString(1, maHoaDon != null && !maHoaDon.isEmpty() ? maHoaDon : null);
//            statement.setString(2, maKhachHang != null && !maKhachHang.isEmpty() ? maKhachHang : null);
//            statement.setString(3, maCaLam != null && !maCaLam.isEmpty() ? maCaLam : null);
//            statement.setString(4, trangThai != null && !trangThai.isEmpty() ? trangThai : null);
//            statement.setString(5, loaiHoaDon != null && !loaiHoaDon.isEmpty() ? loaiHoaDon : null);
//            statement.setDate(6, thoiGianLap != null ? Date.valueOf(thoiGianLap) : null);
//
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                HoaDonBanVe hoaDon = new HoaDonBanVe();
//                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
//                hoaDon.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
//                hoaDon.setTongTien(rs.getDouble("tongTien"));
//                hoaDon.setTongTienDaDatCoc(rs.getDouble("tongTienDaDatCoc"));
//                hoaDon.setTongTienKhachHangTra(rs.getDouble("tongTienKhachHangTra"));
//                hoaDon.setTrangThaiHoaDon(TrangThaiHoaDonDat.valueOf(rs.getString("trangThaiHoaDon")));
//                hoaDon.setLoaiHoaDon(LoaiHoaDon.valueOf(rs.getString("loaiHoaDon")));
//                hoaDon.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
//                hoaDon.setKhachHangMua(new KhachHang(rs.getString("maKhachhangMua")));
//                dsHoaDon.add(hoaDon);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return dsHoaDon;
    }
    public String layMaHoaDonLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonLonNhat = null;
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select max(maHoaDon) as maHoaDon from HoaDon where maHoaDon like 'HD' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maHoaDonLonNhat = rs.getString("maHoaDon");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoaDonLonNhat;
    }

//    public boolean themHoaDon(HoaDonBanVe hoaDon){
//        Connection con = ConnectDB.getInstance().getConnection();
//        try {
//            String query = "insert into HoaDon values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement statement = con.prepareStatement(query);
//            statement.setString(1, hoaDon.getMaHoaDon());
//            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(hoaDon.getThoiGianLap()));
//            statement.setDouble(3, hoaDon.getTongTien());
//            statement.setDouble(4, hoaDon.getTongTienDaDatCoc());
//            statement.setDouble(5, hoaDon.getTongTienKhachHangTra());
//            statement.setString(6, hoaDon.getLoaiHoaDon().toString());
//            statement.setString(7, hoaDon.getTrangThaiHoaDon().toString());
//            statement.setString(8, hoaDon.getCaLamViec().getMaCaLamViec());
//            statement.setString(9, hoaDon.getKhachHangMua().getMaKhachHang());
//            statement.execute();
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }

    public static ArrayList<HoaDonBanVe> getHoaDonTheoMaKhachHangVaThoiGianLap(String maKhachHang, LocalDate thoiGianLap) {
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<HoaDonBanVe> dsHoaDon = new ArrayList<>();
//        try {
//            String query = "exec timHoaDonDatVe ?,?";
//            PreparedStatement statement = con.prepareStatement(query);
//            statement.setString(1, maKhachHang != null && !maKhachHang.isEmpty() ? maKhachHang : null);
//            statement.setDate(2, thoiGianLap != null ? Date.valueOf(thoiGianLap) : null);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                HoaDonBanVe hoaDon = new HoaDonBanVe();
//                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
//                hoaDon.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
//                hoaDon.setTongTien(rs.getDouble("tongTien"));
//                hoaDon.setTongTienDaDatCoc(rs.getDouble("tongTienDaDatCoc"));
//                KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"), KhachHang_DAO.getTenKhachHangTheoMa(rs.getString("maKhachHang")));
//                hoaDon.setKhachHangMua(khachHang);
//                dsHoaDon.add(hoaDon);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return dsHoaDon;
    }
    public static ArrayList<HoaDonBanVe> getDanhSachHoaDon() {
        ArrayList<HoaDonBanVe> dsHoaDon = new ArrayList<>();
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            if (con == null) {
//                throw new SQLException("Failed to establish a database connection.");
//            }
//            String query = "SELECT * FROM HoaDon";
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                HoaDonBanVe hoaDon = new HoaDonBanVe();
//                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
//                hoaDon.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
//                hoaDon.setTongTien(rs.getDouble("tongTien"));
//                hoaDon.setTongTienDaDatCoc(rs.getDouble("tongTienDaDatCoc"));
//                hoaDon.setTongTienKhachHangTra(rs.getDouble("tongTienKhachHangTra"));
//                hoaDon.setTrangThaiHoaDon(TrangThaiHoaDonDat.valueOf(rs.getString("trangThaiHoaDon")));
//                hoaDon.setLoaiHoaDon(LoaiHoaDon.valueOf(rs.getString("loaiHoaDon")));
//                hoaDon.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
//                KhachHang khachHang= new KhachHang(rs.getString("maKhachHangMua"),KhachHang_DAO.getTenKhachHangTheoMa(rs.getString("maKhachHangMua")));
//                hoaDon.setKhachHangMua(khachHang);
//                dsHoaDon.add(hoaDon);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return dsHoaDon;
    }
    public static ArrayList<HoaDonBanVe> getDanhSachHoaDonDat() {
        ArrayList<HoaDonBanVe> dsHoaDon = new ArrayList<>();
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            if (con == null) {
//                throw new SQLException("Failed to establish a database connection.");
//            }
//            String query = "SELECT * FROM HoaDon where loaiHoaDon = 'HOADONDAT' AND trangThaiHoaDon = 'CHOLAYVE'";
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                HoaDonBanVe hoaDon = new HoaDonBanVe();
//                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
//                hoaDon.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
//                hoaDon.setTongTien(rs.getDouble("tongTien"));
//                hoaDon.setTongTienDaDatCoc(rs.getDouble("tongTienDaDatCoc"));
//                hoaDon.setTongTienKhachHangTra(rs.getDouble("tongTienKhachHangTra"));
//                hoaDon.setTrangThaiHoaDon(TrangThaiHoaDonDat.valueOf(rs.getString("trangThaiHoaDon")));
//                hoaDon.setLoaiHoaDon(LoaiHoaDon.valueOf(rs.getString("loaiHoaDon")));
//                hoaDon.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
//                KhachHang khachHang= new KhachHang(rs.getString("maKhachHangMua"),KhachHang_DAO.getTenKhachHangTheoMa(rs.getString("maKhachHangMua")));
//                hoaDon.setKhachHangMua(khachHang);
//                dsHoaDon.add(hoaDon);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return dsHoaDon;
    }
    public ArrayList<HoaDonBanVe> getDanhSachHoaDonDatTheoMaKhachHang(String maKhachHang){
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<HoaDonBanVe> hoaDonList = new ArrayList<HoaDonBanVe>();
//        try{
//            String query = "exec UDP_TimDanhSachHoaDonDatTheoMaKhachHang ?";
//            PreparedStatement statement = con.prepareStatement(query);
//            statement.setString(1, maKhachHang);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()){
//                String maHoaDon = rs.getString("maHoaDon");
//                LocalDateTime thoiGianLap = rs.getTimestamp("thoiGianLap").toLocalDateTime();
//                double tongTien = rs.getDouble("tongTien");
//                double tongTienDatCoc = rs.getDouble("tongTienDaDatCoc");
//                TrangThaiHoaDonDat trangThaiHoaDon = TrangThaiHoaDonDat.valueOf(rs.getString("trangThaiHoaDon"));
//                CaLamViec caLamViec = new CaLamViec(rs.getString("maCaLamViec"));
//                KhachHang khachHang = new KhachHang(rs.getString("maKhachHangMua"));
//                HoaDonBanVe hoaDon = new HoaDonBanVe(maHoaDon, thoiGianLap, tongTien, tongTienDatCoc, trangThaiHoaDon, caLamViec, khachHang);
//                hoaDonList.add(hoaDon);
//            }
//        } catch (Exception e) {
//
//        }
        return hoaDonList;
    }
}
