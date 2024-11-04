package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HoaDon_DAO {

//    public static HoaDon getOrderById(String maHoaHon) {
//        Connection con = null;
//        try {
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLBVT;encrypt=false", "sa", "123");
//            if (con == null || con.isClosed()) {
//                throw new SQLException("Không thể thiết lập kết nối với cơ sở dữ liệu.");
//            }
//
//            PreparedStatement sm = con.prepareStatement("select kh.maKhachHang, kh.tenKhachHang, kh.soDienThoai, kh.maLoaiKhachHang, v.maChuyenTau, v.maGaDi, v.maGaDen, ctct.thoiGianDi, v.maVe, hd.maHoaDon, v.loaiVe\n" +
//                    "from HoaDon hd " +
//                    "join Ve v on v.maHoaDon = hd.maHoaDon" +
//                    "join KhachHang kh on hd.maKhachhangMua = kh.maKhachHang" +
//                    "join ChiTietChuyenTau ctct on v.maChuyenTau = ctct.maChuyenTau" +
//                    "where v.maVe = '?'");
//            sm.setString(1, maHoaHon);
//
//            try (ResultSet rs = sm.executeQuery()) {
//                while (rs.next()) {
//                    KhachHang khachHang = new KhachHang(rs.getString("maKhachHang"),
//                            rs.getString("tenKhachHang"),
//                            rs.getString("soDienThoai"),
//                            rs.getString("maLoaiKhachHang"));
//                    String maKhachHang = rs.getString("maKhachHang");
//                    String tenKhachHang = rs.getString("tenKhachHang");
//                    String soDienThoai = rs.getString("soDienThoai");
//                    String maLoaiKhachHang = rs.getString("maLoaiKhachHang");
//
//                    Ve ve = new Ve(new ChuyenTau(rs.getString("maChuyenTau")),
//                            new GaTau(rs.getString("maGaDi")),
//                            new GaTau(rs.getString("maGaDen")));
//                    String maChuyenTau = rs.getString("maChuyenTau");
//                    String maGaDi = rs.getString("maGaDi");
//                    String maGaDen = rs.getString("maGaDen");
//                    LocalDateTime thoiGianDi = rs.getTimestamp("thoiGianDi").toLocalDateTime();
//
//                    String maVe = rs.getString("maVe");
//                    String maHoaDon = rs.getString("maHoaDon");
//                    LoaiVe loaiVe = LoaiVe.valueOf(rs.getString("loaiVe"));
//                    HoaDon hoaDon = new HoaDon(maHoaDon,khachHang);
//
//                }
//            }
//            } catch (Exception e) {
//            e.printStackTrace();
//            }
//        return hoaDon;
//    }
public static HoaDon getOrderById(String maHoaHon) {
    Connection con = null;
    HoaDon hoaDon = null;
    try {
        con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLBVT;encrypt=false", "sa", "123");
        if (con == null || con.isClosed()) {
            throw new SQLException("Không thể thiết lập kết nối với cơ sở dữ liệu.");
        }

        String sql = "select * from HoaDon hd join KhachHang kh on hd.maKhachhangMua = kh.maKhachHang\n" +
                "where hd.maHoaDon = ?";

        PreparedStatement sm = con.prepareStatement(sql);
        sm.setString(1, maHoaHon);

        try (ResultSet rs = sm.executeQuery()) {
            while (rs.next()) {
                // Lấy thông tin KhachHang
                KhachHang khachHang = new KhachHang(
                        rs.getString("maKhachHang"),
                        rs.getString("tenKhachHang"),
                        rs.getString("soDienThoai"),
                        rs.getString("maLoaiKhachHang")
                );
                String maHoaDon = rs.getString("maHoaDon");
                // Lấy thông tin Ve và ChuyenTau
//                ChuyenTau chuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
//                chuyenTau.setNgayKhoiHanh(rs.getTimestamp("thoiGianDi").toLocalDateTime());


                // Tạo đối tượng HoaDon
                hoaDon = new HoaDon(maHoaDon, khachHang);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return hoaDon;
}

}
