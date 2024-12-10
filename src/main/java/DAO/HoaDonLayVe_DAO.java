package DAO;

import DTO.HoaDonLayVe;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HoaDonLayVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static String layMaHoaDonLayVeLonNhatCuaNgayHienTai (String ngayHienTai){
        String maHoaDonLonNhat = null;
        try {
            String query = "select max(maHoaDonLayVe) as maHoaDonLayVe from HoaDonLayVe where maHoaDonLayVe like 'HDLV' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maHoaDonLonNhat = rs.getString("maHoaDonLayVe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoaDonLonNhat;
    }

    public static boolean themHoaDonLayVe(HoaDonLayVe hoaDonLayVe){
        try {
            String query = "insert into HoaDonLayVe values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, hoaDonLayVe.getMaHoaDonLayVe());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(hoaDonLayVe.getThoiGianLayVe()));
            statement.setString(3, hoaDonLayVe.getCaLamViec().getMaCaLamViec());
            statement.setString(4, hoaDonLayVe.getKhachHangLayVe().getMaKhachHang());

            statement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public static HoaDonLayVe getHoaDonLayVeTheoMa(String maHoaDon){
        String query = "select * from HoaDonLayVe where maHoaDonLayVe = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                HoaDonLayVe hoaDonLayVe = new HoaDonLayVe();
                hoaDonLayVe.setMaHoaDonLayVe(rs.getString("maHoaDonLayVe"));
                hoaDonLayVe.setThoiGianLayVe(rs.getTimestamp("thoiGianLayVe").toLocalDateTime());
                hoaDonLayVe.setCaLamViec(new CaLamViec_DAO().getCaLamViecTheoMa(rs.getString("maCaLamViec")));
                hoaDonLayVe.setKhachHangLayVe(new KhachHang_DAO().getKhachHangTheoMaKhachHang(rs.getString("maKhachHangLayVe")));
                return hoaDonLayVe;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
