package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HoaDon_DAO {
    public ArrayList<HoaDon> getDanhSachHoaDon() {
        ArrayList<HoaDon> dsHoaDon= new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        try{
            String query = "SELECT * FROM HoaDon";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
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
                hoaDon.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
                dsHoaDon.add(hoaDon);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
                hoaDon.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return hoaDon;
    }
    public HoaDon getHoaDonTheoKhachHang(String ma){
        Connection con = ConnectDB.getInstance().getConnection();
        HoaDon hoaDon = new HoaDon();
        try{
            String query = "SELECT * FROM HoaDon WHERE maKhachHang = ?";
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
                hoaDon.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return hoaDon;
    }

    public HoaDon getHoaDonTheoLoai(String loai){
        Connection con = ConnectDB.getInstance().getConnection();
        HoaDon hoaDon = new HoaDon();
        try{
            String query = "SELECT * FROM HoaDon WHERE loaiHoaDon = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,loai);
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
                hoaDon.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return hoaDon;
    }
    public HoaDon getHoaDonTheoTrangThai(String trangThai){
        Connection con = ConnectDB.getInstance().getConnection();
        HoaDon hoaDon = new HoaDon();
        try{
            String query = "SELECT * FROM HoaDon WHERE trangThaiHoaDon = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,trangThai);
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
                hoaDon.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return hoaDon;
    }
    public HoaDon getHoaDonTheoCaLamViec(String maCa){
        Connection con = ConnectDB.getInstance().getConnection();
        HoaDon hoaDon = new HoaDon();
        try{
            String query = "SELECT * FROM HoaDon WHERE maCaLamViec = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maCa);
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
                hoaDon.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return hoaDon;
    }

    public HoaDon getHoaDonTheoThoiGian(LocalDateTime thoiGian){
        Connection con = ConnectDB.getInstance().getConnection();
        HoaDon hoaDon = new HoaDon();
        try{
            String query = "SELECT * FROM HoaDon WHERE thoiGianLap = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setTimestamp(1, Timestamp.valueOf(thoiGian));
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
                hoaDon.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hoaDon;
    }

}
