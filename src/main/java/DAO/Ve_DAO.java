package DAO;

import BUS.QuanLyVe_BUS;
import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            System.out.println(ve);
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
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
}
