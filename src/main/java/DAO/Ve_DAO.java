package DAO;

import BUS.QuanLyVe_BUS;
import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ve_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static String layDuoiMaVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maVeLonNhat = null;
        try {
            String query = "select max(SUBSTRING(maVe, LEN(maVe) - 7, 8)) as duoiMaVe from Ve where maVe like 'VE[A-Z][A-Z]' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maVeLonNhat = rs.getString("duoiMaVe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maVeLonNhat;
    }

    public static boolean themDanhSachVe(ArrayList<Ve> danhSachVe){
        String query = "insert into Ve values (?, ?, ?, ?, ?, ?, ?, ?)";
        for(Ve ve : danhSachVe){
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, ve.getMaVe());
                statement.setString(2, ve.getHoaDonBanVe().getMaHoaDonBanVe());
                statement.setString(3, ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
                statement.setString(4, ve.getThongTinGaTauDi().getGaTau().getMaGaTau());
                statement.setString(5, ve.getThongTinGaTauDen().getGaTau().getMaGaTau());
                statement.setDouble(6, ve.getPhanTramGiamGiaVeTapThe());
                statement.setString(7, ve.getLoaiVe().toString());
                statement.setString(8, ve.getTrangThaiVe().toString());
                statement.execute();

            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean capNhatTrangThaiVe(String maVe, TrangThaiVe trangThai) {
        String sql = "UPDATE Ve SET TrangThaiVe = ? WHERE MaVe = ?";
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, trangThai.name()); // Sử dụng tên enum làm giá trị
            ps.setString(2, maVe);

            return ps.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu xảy ra lỗi
        }
    }

    public static ArrayList<Ve> getDanhSachVeTheoMaHoaDon(String maHoaDon) {
        ArrayList<Ve> dsVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "SELECT \n" +
                "    v.maVe,\n" +
                "    v.maHoaDonBanVe,\n" +
                "    v.maChuyenTau,\n" +
                "    v.maGaDi,\n" +
                "    v.maGaDen,\n" +
                "    v.loaiVe,\n" +
                "    v.trangThaiVe\n" +
                "FROM \n" +
                "    Ve v\n" +
                "JOIN \n" +
                "    HoaDonBanVe hd ON v.maHoaDonBanVe = hd.maHoaDonBanVe\n" +
                "WHERE\n" +
                "    hd.maHoaDonBanVe = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ChiTietChuyenTau thongTinGaDi = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"), rs.getString("maGaDi"));
                ChiTietChuyenTau thongTinGaDen = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"), rs.getString("maGaDen"));
                ArrayList<ChiTietVe> dsChiTietVe = ChiTietVe_DAO.xuatDanhSachChiTietVeTheoMaVe(rs.getString("maVe"));
                Ve ve = new Ve(rs.getString("maVe"), new HoaDonBanVe(rs.getString("maHoaDonBanVe")), thongTinGaDi, thongTinGaDen, LoaiVe.valueOf(rs.getString("loaiVe")), TrangThaiVe.valueOf(rs.getString("trangThaiVe")));
                ve.setDanhSachChiTietVe(dsChiTietVe);
                dsVe.add(ve);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsVe;
    }

    public static Ve getVeTheoMaVe_HuyVe(String maVe){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "select * from Ve where maVe = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVe);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                ChiTietChuyenTau thongTinGaDi = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"),rs.getString("maGaDi"));
                ChiTietChuyenTau thongTinGaDen = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"),rs.getString("maGaDen"));
                Ve ve = new Ve(rs.getString("maVe"),new HoaDonBanVe(rs.getString("maHoaDonBanVe")),thongTinGaDi,thongTinGaDen,LoaiVe.valueOf(rs.getString("loaiVe")),TrangThaiVe.valueOf(rs.getString("trangThaiVe")));
                ve.setDanhSachChiTietVe(ChiTietVe_DAO.xuatDanhSachChiTietVeTheoMaVe(maVe));
                return ve;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
