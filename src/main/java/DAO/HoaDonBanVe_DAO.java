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

    public static ArrayList<HoaDonBanVe> get10HoaDonDatGanNhat(){
        ArrayList<HoaDonBanVe> dsHoaDonBanVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        if (con == null) {
            throw new RuntimeException("Không thể kết nối đến cơ sở dữ liệu.");
        }

        String query = "SELECT TOP 10 \n" +
                "    hdbv.maHoaDonBanVe,\n" +
                "    hdbv.thoiGianLap,\n" +
                "\tkh.maKhachHang,\n" +
                "    kh.tenKhachHang,\n" +
                "    hdbv.maCaLamViec\n" +
                "FROM HoaDonBanVe hdbv\n" +
                "JOIN KhachHang kh ON hdbv.maKhachHangMuaVe = kh.maKhachHang\n" +
                "ORDER BY hdbv.thoiGianLap DESC;";
        System.out.println("HoaDonBanVe");
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                HoaDonBanVe hoaDonBanVe = new HoaDonBanVe();
                hoaDonBanVe.setMaHoaDonBanVe(rs.getString("maHoaDonBanVe"));
                hoaDonBanVe.setThoiGianLap(rs.getTimestamp("thoiGianLap").toLocalDateTime());
                hoaDonBanVe.setKhachHangMuaVe(new KhachHang(rs.getString("maKhachHang"),rs.getString("tenKhachHang")));
                hoaDonBanVe.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDonBanVe.setDanhSachVe(Ve_DAO.getDanhSachVeTheoMaHoaDon(rs.getString("maHoaDonBanVe")));
                dsHoaDonBanVe.add(hoaDonBanVe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi truy vấn dữ liệu HoaDonBanVe", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi không xác định", e);
        }
        System.out.println("Số lượng hóa đơn trả về: " + dsHoaDonBanVe.size());
        return dsHoaDonBanVe;
    }

    public static ArrayList<HoaDonBanVe> getDanhSachHoaDonBanVeTheoCCCD(String CCCD, LocalDate thoigianLap) {
        ArrayList<HoaDonBanVe> dsHoaDonBanVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "SELECT \n" +
                "\thd.maHoaDonBanVe, \n" +
                "    hd.thoiGianLap, \n" +
                "    kh.maKhachHang, \n" +
                "    kh.tenKhachHang, \n" +
                "    clv.maCaLamViec\n" +
                "FROM \n" +
                "    HoaDonBanVe hd\n" +
                "JOIN \n" +
                "    KhachHang kh ON hd.maKhachHangMuaVe = kh.maKhachHang\n" +
                "JOIN \n" +
                "    CaLamViec clv ON hd.maCaLamViec = clv.maCaLamViec\n" +
                "where maKhachHang = ?\n" +
                "AND (CAST(hd.thoiGianLap AS DATE) = ? \n" +
                "             OR hd.thoiGianLap IS NULL)";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, CCCD);
            if (thoigianLap != null) {
                statement.setDate(2, Date.valueOf(thoigianLap)); // Truyền giá trị ngày nếu có
            } else {
                statement.setNull(2, Types.DATE); // Truyền NULL nếu không có giá trị
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                HoaDonBanVe hoaDonBanVe = new HoaDonBanVe();
                hoaDonBanVe.setMaHoaDonBanVe(rs.getString("maHoaDonBanVe"));
                hoaDonBanVe.setThoiGianLap(rs.getTimestamp("thoiGianLap") != null
                        ? rs.getTimestamp("thoiGianLap").toLocalDateTime()
                        : null);
                hoaDonBanVe.setKhachHangMuaVe(new KhachHang(rs.getString("maKhachHang"),rs.getString("tenKhachHang")));
                hoaDonBanVe.setCaLamViec(new CaLamViec(rs.getString("maCaLamViec")));
                hoaDonBanVe.setDanhSachVe(Ve_DAO.getDanhSachVeTheoMaHoaDon(rs.getString("maHoaDonDatVe")));
                dsHoaDonBanVe.add(hoaDonBanVe);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsHoaDonBanVe;
    }
}
