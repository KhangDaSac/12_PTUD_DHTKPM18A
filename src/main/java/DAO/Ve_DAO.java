package DAO;

import BUS.QuanLyVe_BUS;
import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
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
                if(ve.getHoaDonBanVe() != null)
                    statement.setString(2, ve.getHoaDonBanVe().getMaHoaDonBanVe());
                else
                    statement.setNull(2, Types.VARCHAR);
                statement.setString(3, ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
                statement.setString(4, ve.getThongTinGaTauDi().getGaTau().getMaGaTau());
                statement.setString(5, ve.getThongTinGaTauDen().getGaTau().getMaGaTau());
                statement.setDouble(6, ve.getPhanTramGiamGiaVeTapThe());
                statement.setString(7, ve.getLoaiVe().toStringSQL());
                statement.setString(8, ve.getTrangThaiVe().toString());
                statement.execute();

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Ve> getDanhSachVeTheoHoaDonBanVe(String maHoaDonBanVe){
        ArrayList<Ve> dsVe = new ArrayList<>();
        try {
            String query = "SELECT maVe, phanTramGiamGiaVeTapThe FROM Ve where maHoaDonBanVe = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDonBanVe);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                dsVe.add(new Ve(
                        rs.getString("maVe"),
                        rs.getDouble("phanTramGiamGiaVeTapThe"),
                        ChiTietVe_DAO.getDanhSachChiTietVeTheoMaVe_BaoCao(rs.getString("maVe"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }
}
