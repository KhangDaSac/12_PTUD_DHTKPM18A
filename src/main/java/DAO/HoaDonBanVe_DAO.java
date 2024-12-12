package DAO;

import DTO.*;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Connection;

public class HoaDonBanVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static String layMaHoaDonBanVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonLonNhat = null;
        try {
            String query = "select max(maHoaDonBanVe) as maHoaDonBanVe from HoaDonBanVe where maHoaDonBanVe like 'HDBV' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maHoaDonLonNhat = rs.getString("maHoaDonBanVe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoaDonLonNhat;
    }

    public static boolean themHoaDonBanVe(HoaDonBanVe hoaDonBanVe){
        try {
            String query = "insert into HoaDonBanVe values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, hoaDonBanVe.getMaHoaDonBanVe());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(hoaDonBanVe.getThoiGianLap()));
            statement.setString(3, hoaDonBanVe.getCaLamViec().getMaCaLamViec());
            System.out.println(hoaDonBanVe.getCaLamViec().getMaCaLamViec());
            statement.setString(4, hoaDonBanVe.getKhachHangMuaVe().getMaKhachHang());
            statement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static double[][] getDanhSachHoaDonBanVeTrongCa(CaLamViec caLamViec){
        double[][] dsCacHoaDon = new double[6][2];
        try {
            String query = "EXEC dbo.getSoLuongVaTongTienCacLoaiHoaDonTheoMaCaLamViec ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, caLamViec.getMaCaLamViec());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int stt = rs.getInt("STT");
                dsCacHoaDon[stt - 1][0] = rs.getInt("soLuong");
                dsCacHoaDon[stt - 1][1] = rs.getDouble("tongTien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCacHoaDon;
    }
public static HoaDonBanVe getHoaDonBanVeTheoMa(String maHoaDon){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "select * from HoaDonBanVe where maHoaDonBanVe = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                HoaDonBanVe hoaDonBanVe = new HoaDonBanVe();
                hoaDonBanVe.setMaHoaDonBanVe(rs.getString("maHoaDonBanVe"));
                hoaDonBanVe.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDonBanVe.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDonBanVe.setKhachHangMuaVe(KhachHang_DAO.getKhachHangTheoMaKhachHang(rs.getString("maKhachHangMuaVe")));
                hoaDonBanVe.setDanhSachVe(Ve_DAO.xuatDanhSachVeTheoMaHoaDonBanVe(rs.getString("maHoaDonBanVe")));
                return hoaDonBanVe;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }



}
