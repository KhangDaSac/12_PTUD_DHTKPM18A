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


    public String layMaHoaDonBanVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonLonNhat = null;
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select max(maHoaDonBanVe) as maHoaDonBanVe from HoaDonBanVe where maHoaDonBanVe like 'HD' + ? + '%'";
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
