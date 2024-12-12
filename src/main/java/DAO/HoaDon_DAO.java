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
    public static Connection con = ConnectDB.getInstance().getConnection();
    public String layMaHoaDonLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonLonNhat = null;
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


}
