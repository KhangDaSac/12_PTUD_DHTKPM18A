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



}
