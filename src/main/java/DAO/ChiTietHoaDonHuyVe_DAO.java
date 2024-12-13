
package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ChiTietHoaDonHuyVe_DAO {
    public static ArrayList<ChiTietHoaDonHuyVe> getDanhSachChiTietHoaDonHuyVeTheoMaHoaDonHuyVe(String maHoaDon){
        ArrayList<ChiTietHoaDonHuyVe> dsChiTietHoaDonHuyVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "EXEC timDanhSachChiTietHoaDonHuyVeTheoMaHoaDonHuyVe ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                HoaDonHuyVe hoaDonHuyVe = new HoaDonHuyVe(rs.getString("maHoaDonHuyVe"), rs.getTimestamp("thoiGianHuyVe").toLocalDateTime(), new CaLamViec(rs.getString("maCaLamViec")), new KhachHang(rs.getString("maKhachHangHuyVe")));
                Ve ve = Ve_DAO.getVeTheoMaVe_HuyVe(rs.getString("maVe"));
                ChiTietHoaDonHuyVe chiTietHoaDonHuyVe = new ChiTietHoaDonHuyVe(hoaDonHuyVe,ve,rs.getDouble("phanTramLePhi"));
                dsChiTietHoaDonHuyVe.add(chiTietHoaDonHuyVe);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dsChiTietHoaDonHuyVe;
    }

    public static void themDanhsachChiTietHoaDonHuyDatVe(ArrayList<ChiTietHoaDonHuyVe> dsChiTietHoaDonHuyVe){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "insert into ChiTietHoaDonHuyVe\n" +
                "values( ?,?,?)";
        try{
            for(ChiTietHoaDonHuyVe cthd:dsChiTietHoaDonHuyVe){
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1,cthd.getVe().getMaVe());
                statement.setString(2,cthd.getHoaDonHuyVe().getMaHoaDonHuyVe());
                statement.setDouble(3,cthd.getPhanTramLePhi());
                statement.execute();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean themDanhSachHoaDonHuyVe(ArrayList<HoaDonHuyVe> danhSachHoaDonHuyVe){
        String query = "insert into HoaDonHuyVe values (?, ?, ?, ?)";
        Connection con = ConnectDB.getInstance().getConnection();
        for(HoaDonHuyVe hoaDonHuyVe : danhSachHoaDonHuyVe){
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, hoaDonHuyVe.getMaHoaDonHuyVe());
                statement.setString(2, hoaDonHuyVe.getThoiGianHuyVe().toString());
                statement.setString(3, hoaDonHuyVe.getCaLamViec().getMaCaLamViec());
                statement.setString(4, hoaDonHuyVe.getKhachHangHuyVe().getMaKhachHang());
                statement.execute();

            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
