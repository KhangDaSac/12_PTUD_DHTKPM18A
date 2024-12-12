package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietHoaDonHuyDatVe_DAO {
    public static ArrayList<ChiTietHoaDonHuyDatVe> getDanhSachChiTietHoaDonHuyDatVeTheoMaHoaDonHuyDatVe(String maHoaDon){
        ArrayList<ChiTietHoaDonHuyDatVe> dsChiTietHoaDonHuyDatVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "EXEC timDanhSachChiTietHoaDonHuyDatVeTheoMaHoaDonHuyDatVe ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                HoaDonHuyDatVe hoaDonHuyDatVe = new HoaDonHuyDatVe(rs.getString("maHoaDonHuyDatVe"), rs.getTimestamp("thoiGianHuyDatVe").toLocalDateTime(), new CaLamViec(rs.getString("maCaLamViec")), new KhachHang(rs.getString("maKhachHangHuyDatVe")));
                 VeDat veDat = VeDat_DAO.getVeDatTheoMa(rs.getString("maVeDat"));
                 ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe = new ChiTietHoaDonHuyDatVe(hoaDonHuyDatVe,veDat);
                    dsChiTietHoaDonHuyDatVe.add(chiTietHoaDonHuyDatVe);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dsChiTietHoaDonHuyDatVe;
    }
    public static void themDanhsachChiTietHoaDonHuyDatVe(ArrayList<ChiTietHoaDonHuyDatVe> dsChiTietHoaDonHuyDatVe){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "insert into ChiTietHoaDonHuyDatVe\n" +
                "values( ?,?,?)";
        try{
            for(ChiTietHoaDonHuyDatVe cthd:dsChiTietHoaDonHuyDatVe){
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1,cthd.getVeDat().getMaVeDat());
                    statement.setString(2,cthd.getHoaDonHuyDatVe().getMaHoaDonHuyDatVe());
                    statement.setDouble(3,cthd.getPhanTramLePhi());
                    statement.execute();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
