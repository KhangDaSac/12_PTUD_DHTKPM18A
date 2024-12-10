package DAO;

import DTO.CaLamViec;
import DTO.NhanVien;
import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CaLamViec_DAO {
    public ArrayList<CaLamViec> xuatDanhSachCaLamViec() {
        ArrayList<CaLamViec> dsCaLamViec = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select * from CaLamViec";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String maCaLamViec = rs.getString(1);
                LocalDateTime thoiGianBatDau = rs.getTimestamp(2).toLocalDateTime();
                LocalDateTime thoiGianKetThuc = rs.getTimestamp(3).toLocalDateTime();
                NhanVien nhanVien = new NhanVien(rs.getString(4));
                CaLamViec caLamViec = new CaLamViec(maCaLamViec, thoiGianBatDau, thoiGianKetThuc, nhanVien);
                dsCaLamViec.add(caLamViec);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsCaLamViec;
    }

    public static CaLamViec timCaLamViec(LocalDate thoiGian) {
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "select * from CaLamViec where ThoiGianBatDau = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setDate(1, Date.valueOf(thoiGian));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String maCaLamViec = rs.getString(1);
                LocalDateTime thoiGianBatDau = rs.getTimestamp(2).toLocalDateTime();
                LocalDateTime thoiGianKetThuc = rs.getTimestamp(3).toLocalDateTime();
                NhanVien nhanVien = new NhanVien(rs.getString(4));
                CaLamViec caLamViec = new CaLamViec(maCaLamViec, thoiGianBatDau, thoiGianKetThuc, nhanVien);
                return caLamViec;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static CaLamViec getCaLamViecTheoMa(String maCaLamViec) {
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "select * from CaLamViec where maCaLamViec = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maCaLamViec);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                LocalDateTime thoiGianBatDau = rs.getTimestamp("thoiGianBatDau").toLocalDateTime();
                LocalDateTime thoiGianKetThuc = rs.getTimestamp("thoiGianKetThuc").toLocalDateTime();
                NhanVien nhanVien = new NhanVien(rs.getString("maNhanVien"));
                CaLamViec caLamViec = new CaLamViec(maCaLamViec, thoiGianBatDau, thoiGianKetThuc, nhanVien);
                return caLamViec;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
