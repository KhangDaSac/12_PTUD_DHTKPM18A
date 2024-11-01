package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Connection;

import static connectDB.ConnectDB.con;

public class HoaDon_DAO {
    public ArrayList<HoaDon> getDanhSachHoaDon() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {// Kết nối trước khi lấy kết nối
            Connection con = ConnectDB.getInstance().getConnection();
            if (con == null) {
                throw new SQLException("Failed to establish a database connection.");
            }
            String query = "SELECT * FROM HoaDon";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
                hoaDon.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDon.setTongTien(rs.getDouble("tongTien"));
                hoaDon.setTongTienDaDatCoc(rs.getDouble("tongTienDaDatCoc"));
                hoaDon.setTongTienKhachHangTra(rs.getDouble("tongTienKhachHangTra"));
                hoaDon.setTrangThaiHoaDon(TrangThaiHoaDon.valueOf(rs.getString("trangThaiHoaDon")));
                hoaDon.setLoaiHoaDon(LoaiHoaDon.valueOf(rs.getString("loaiHoaDon")));
                hoaDon.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDon.setKhachHangMua(new KhachHang(rs.getString("maKhachhangMua")));
                dsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public HoaDon getHoaDonTheoMa(String ma){
        Connection con = ConnectDB.getInstance().getConnection();
        HoaDon hoaDon = new HoaDon();
        try{
            String query = "SELECT * FROM HoaDon WHERE maHoaDon = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,ma);
            ResultSet rs = statement.executeQuery();
            statement.execute();
            while (rs.next()){
                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
                hoaDon.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDon.setTongTien(rs.getDouble("tongTien"));
                hoaDon.setTongTienDaDatCoc(rs.getDouble("tongTienDaDatCoc"));
                hoaDon.setTongTienKhachHangTra(rs.getDouble("tongTienKhachHangTra"));
                hoaDon.setTrangThaiHoaDon(TrangThaiHoaDon.valueOf(rs.getString("trangThaiHoaDon")));
                hoaDon.setLoaiHoaDon(LoaiHoaDon.valueOf(rs.getString("loaiHoaDon")));
                hoaDon.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDon.setKhachHangMua(new KhachHang(rs.getString("maKhachhangMua")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return hoaDon;
    }
    public ArrayList<HoaDon> getDSHoaDonTheoCacTieuChi(String maHoaDon,String maKhachHang, String maCaLam, String trangThai, String loaiHoaDon, LocalDateTime thoiGianLap){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        try{
            String query = "exec timHoaDonTheoCacTieuChi ?,?,?,?,?,?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            statement.setString(2,maKhachHang);
            statement.setTimestamp(3, Timestamp.valueOf(thoiGianLap));
            statement.setString(4,trangThai);
            statement.setString(5,loaiHoaDon);
            statement.setString(6,maCaLam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
                hoaDon.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDon.setTongTien(rs.getDouble("tongTien"));
                hoaDon.setTongTienDaDatCoc(rs.getDouble("tongTienDaDatCoc"));
                hoaDon.setTongTienKhachHangTra(rs.getDouble("tongTienKhachHangTra"));
                hoaDon.setTrangThaiHoaDon(TrangThaiHoaDon.valueOf(rs.getString("trangThaiHoaDon")));
                hoaDon.setLoaiHoaDon(LoaiHoaDon.valueOf(rs.getString("loaiHoaDon")));
                hoaDon.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDon.setKhachHangMua(new KhachHang(rs.getString("maKhachhangMua")));
                dsHoaDon.add(hoaDon);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
}
