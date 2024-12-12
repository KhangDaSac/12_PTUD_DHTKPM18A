package DAO;

import DTO.HoaDonDoiVe;
import DTO.Ve;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HoaDonDoiVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static HoaDonDoiVe getHoaDonDoiVeTheoMa(String maHoaDonDoiVe) {
        String query = "select * from HoaDonDoiVe where maHoaDonDoiVe = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDonDoiVe);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                HoaDonDoiVe hoaDonDoiVe = new HoaDonDoiVe();
                hoaDonDoiVe.setMaHoaDonDoiVe(rs.getString("maHoaDonDoiVe"));
                hoaDonDoiVe.setThoiGianDoiVe(rs.getTimestamp("thoiGianDoiVe ").toLocalDateTime());
                hoaDonDoiVe.setLePhi(rs.getDouble("lePhi"));
                ArrayList<Ve>  dsVeDoi = new ArrayList<>();
                dsVeDoi= Ve_DAO.getDanhSachVeTheoMaHoaDonDoi(rs.getString("maHoaDonDoiVe"));
                hoaDonDoiVe.setVeMoi(dsVeDoi.get(0));
                hoaDonDoiVe.setVeCu(dsVeDoi.get(1));
                hoaDonDoiVe.setCaLamViec(CaLamViec_DAO.getCaLamViecTheoMa(rs.getString("maCaLamViec")));
                return hoaDonDoiVe;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
