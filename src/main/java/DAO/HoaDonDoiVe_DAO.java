package DAO;

import DTO.HoaDonDoiVe;
import DTO.Ve;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
                hoaDonDoiVe.setThoiGianDoiVe(rs.getTimestamp("thoiGianDoiVe").toLocalDateTime());
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
    public static String layMaHoaDonDoiLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonDoiLonNhat = null;
        try {
            String query = "select max(maHoaDonDoiVe) as maHoaDon from HoaDonDoiVe where maHoaDonDoiVe like 'HDDO' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maHoaDonDoiLonNhat = rs.getString("maHoaDon");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoaDonDoiLonNhat;
    }

    public static boolean themHoaDonDoiVe(HoaDonDoiVe hoaDonDoiVe){
        try{
            String query ="insert into HoaDonDoiVe values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,hoaDonDoiVe.getMaHoaDonDoiVe());
            statement.setTimestamp(2, Timestamp.valueOf(hoaDonDoiVe.getThoiGianDoiVe()));
            statement.setDouble(3,hoaDonDoiVe.getLePhi());
            statement.setString(4,hoaDonDoiVe.getVeCu().getMaVe());
            statement.setString(5,hoaDonDoiVe.getVeMoi().getMaVe());
            statement.setString(6,hoaDonDoiVe.getCaLamViec().getMaCaLamViec());
            statement.execute();
            System.out.println("DA DPOO");
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
