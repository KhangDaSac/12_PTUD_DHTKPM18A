package DAO;

import DTO.*;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Connection;

import static connectDB.ConnectDB.con;

public class HoaDon_DAO {

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
    public ArrayList<HoaDon> getDSHoaDonTheoCacTieuChi(String maHoaDon,String maKhachHang, String maCaLam, String trangThai, String loaiHoaDon, LocalDate thoiGianLap){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        try{
            String query = "exec timHoaDonTheoCacTieuChi ?,?,?,?,?,?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            statement.setString(2,maKhachHang);
            statement.setDate(3, Date.valueOf(thoiGianLap));
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
    public boolean themHoaDon(HoaDon hoaDon){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "insert into HoaDon values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, hoaDon.getMaHoaDon());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(hoaDon.getThoiGianLap()));
            statement.setDouble(3, hoaDon.getTongTien());
            statement.setDouble(4, hoaDon.getTongTienDaDatCoc());
            statement.setDouble(5, hoaDon.getTongTienKhachHangTra());
            statement.setString(6, hoaDon.getLoaiHoaDon().toString());
            statement.setString(7, hoaDon.getTrangThaiHoaDon().toString());
            statement.setString(8, hoaDon.getCaLamViec().getMaCaLamViec());
            statement.setString(9, hoaDon.getKhachHangMua().getMaKhachHang());
            statement.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
//    CREATE PROCEDURE timHoaDonDatVe
//            @CCCD varchar(12),
//    @thoiGianLap date
//            AS
//    BEGIN
//    SELECT HD.maHoaDon, KH.maKhachHang, HD.thoiGianLap, STRING_AGG(PDV.maPhieuDatVe, ', ') AS MaPhieuDatVe, HD.tongTienDaDatCoc,HD.tongTien
//    FROM HoaDon as HD join KhachHang as KH on HD.maKhachhangMua = KH.maKhachHang join PhieuDatVe as PDV on HD.maHoaDon = PDV.maHoaDon
//    WHERE
//            (@CCCD IS NULL OR @CCCD = '' OR KH.CCCD = @CCCD) AND
//		(@thoiGianLap IS NULL OR (CAST(HD.thoiGianLap AS date) = @thoiGianLap)) AND
//            loaiHoaDon = 'HOADONDAT'
//    group by HD.maHoaDon,KH.maKhachHang, HD.thoiGianLap, HD.tongTienDaDatCoc,HD.tongTien
//            END
//    GO
 public static ArrayList<HoaDon> getHoaDonTheoMaKhachHangVaThoiGianLap(String maKhachHang, LocalDate thoiGianLap) {
     Connection con = ConnectDB.getInstance().getConnection();
     ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
     try {
         String query = "exec timHoaDonDatVe ?,?";
         PreparedStatement statement = con.prepareStatement(query);
         statement.setString(1, maKhachHang);
         statement.setDate(2, Date.valueOf(thoiGianLap));
         ResultSet rs = statement.executeQuery();
         if (rs.next()) {
             HoaDon hoaDon = new HoaDon();
             hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
             hoaDon.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
             hoaDon.setTongTien(rs.getDouble("tongTien"));
             hoaDon.setTongTienDaDatCoc(rs.getDouble("tongTienDaDatCoc"));
             KhachHang khachHang= new KhachHang(rs.getString("maKhachHang"),KhachHang_DAO.getTenKhachHangTheoMa(rs.getString("maKhachHang")));
             hoaDon.setKhachHangMua(khachHang);
             dsHoaDon.add(hoaDon);
         }
     } catch (SQLException e) {

         throw new RuntimeException(e);
     }
     return dsHoaDon;
 }
    public static ArrayList<HoaDon> getDanhSachHoaDon() {
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
                KhachHang khachHang= new KhachHang(rs.getString("maKhachHangMua"),KhachHang_DAO.getTenKhachHangTheoMa(rs.getString("maKhachHangMua")));
                hoaDon.setKhachHangMua(khachHang);
                dsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }
    public ArrayList<HoaDon> getDanhSachHoaDonDatTheoMaKhachHang(String maKhachHang){
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<HoaDon> hoaDonList = new ArrayList<HoaDon>();
        try{
            String query = "exec UDP_TimDanhSachHoaDonDatTheoMaKhachHang ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maKhachHang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String maHoaDon = rs.getString("maHoaDon");
                LocalDateTime thoiGianLap = rs.getTimestamp("thoiGianLap").toLocalDateTime();
                double tongTien = rs.getDouble("tongTien");
                double tongTienDatCoc = rs.getDouble("tongTienDaDatCoc");
                TrangThaiHoaDon trangThaiHoaDon = TrangThaiHoaDon.valueOf(rs.getString("trangThaiHoaDon"));
                CaLamViec caLamViec = new CaLamViec(rs.getString("maCaLamViec"));
                KhachHang khachHang = new KhachHang(rs.getString("maKhachHangMua"));
                HoaDon hoaDon = new HoaDon(maHoaDon, thoiGianLap, tongTien, tongTienDatCoc, trangThaiHoaDon, caLamViec, khachHang);
                hoaDonList.add(hoaDon);
            }
        } catch (Exception e) {

        }
        return hoaDonList;
    }
}
