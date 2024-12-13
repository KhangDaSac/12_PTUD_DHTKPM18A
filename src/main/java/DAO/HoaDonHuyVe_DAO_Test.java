package DAO;

import DTO.HoaDonHuyDatVe;
import DTO.HoaDonHuyVe;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class HoaDonHuyVe_DAO_Test {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static String layMaHoaDonHuyVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonLonNhat = null;
        try {
            String query = "select max(maHoaDonHuyVe) as maHoaDonHuyVe from HoaDonHuyVe where maHoaDonHuyVe like 'HDHV' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maHoaDonLonNhat = rs.getString("maHoaDonHuyVe");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return maHoaDonLonNhat;
    }

    public static boolean themHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe){
        try{
            String query = "insert into HoaDonHuyDatVe values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, hoaDonHuyVe.getMaHoaDonHuyVe());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(hoaDonHuyVe.getThoiGianHuyVe()));
            statement.setString(3, hoaDonHuyVe.getCaLamViec().getMaCaLamViec());
            statement.setString(4, hoaDonHuyVe.getKhachHangHuyVe().getMaKhachHang());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}