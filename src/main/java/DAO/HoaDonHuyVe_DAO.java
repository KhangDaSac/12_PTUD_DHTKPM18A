package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.*;


public class HoaDonHuyVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static boolean themHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe){
        try{
            String query ="insert into HoaDonDoiVe values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,hoaDonHuyVe.getMaHoaDonHuyVe());
            statement.setTimestamp(2, Timestamp.valueOf(hoaDonHuyVe.getThoiGianHuyVe()));
            statement.setString(6,hoaDonHuyVe.getCaLamViec().getMaCaLamViec());
            statement.execute();
        }catch (Exception e){
            return false;
        }
        return true;
    }

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
            e.printStackTrace();
        }
        return maHoaDonLonNhat;
    }
}
