package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Ve_DAO {
    public String layMaVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maVeLonNhat = null;
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select max(maVe) as maVe from Ve where maVe like 'V' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maVeLonNhat = rs.getString("maVe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maVeLonNhat;
    }

    public boolean themDanhSachVe(ArrayList<Ve> danhSachVe){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "insert into Ve values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        for(Ve ve : danhSachVe){
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, ve.getMaVe());
                statement.setString(2, ve.getHoaDon().getMaHoaDon());
                statement.setString(3, ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
                statement.setString(4, ve.getThongTinGaTauDi().getGaTau().getMaGaTau());
                statement.setString(5, ve.getThongTinGaTauDen().getGaTau().getMaGaTau());
                statement.setDouble(6, ve.getGiamGiaVeTapThe());
                statement.setDouble(7, ve.getTongTienVe());
                statement.setString(8, ve.getLoaiVe().toString());
                statement.setString(9, ve.getTrangThaiVe().toString());
                statement.execute();

            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
