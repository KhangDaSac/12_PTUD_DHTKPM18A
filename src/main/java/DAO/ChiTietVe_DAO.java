package DAO;

import DTO.ChiTietVe;
import DTO.Ve;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChiTietVe_DAO {
        public static Connection con = ConnectDB.getInstance().getConnection();
        public static ChiTietVe getCTVeTheoMaVe(String maVe){
            ChiTietVe ctVe = null;
            try{
                String query = "select * from ChiTietVe ct \n" +
                        "join Ve v on v.maVe = ct.maVe\n" +
                        "join Cho c on c.maCho = ct.maCho\n" +
                        "JOIN KhachHang kh ON kh.maKhachHang = ct.maKhachHang\n " +
                        "join LoaiKhachHang l on l.maLoaiKhachHang = kh.maLoaiKhachHang \n"+
                        "where v.maVe = ? ";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, maVe);
                ResultSet rs = statement.executeQuery();
                while( rs.next()){
                    Ve ve = new Ve(rs.getString("maVe"));
                    Cho cho = new Cho(new ToaTau(
                            rs.getString("maToaTau")),
                            rs.getString("maCho"),
                            rs.getInt("soCho"));

                    double giaCho = rs.getDouble("giaCho");
                    double phanTramGiamGia = rs.getDouble("phanTramGiamGia");
                    KhachHang khachHang = new KhachHang(
                            rs.getString("maKhachHang"),
                            rs.getString("tenKhachHang"),
                            new LoaiKhachHang(
                                    rs.getString("maLoaiKhachHang"),
                                    rs.getString("tenLoaiKhachHang"),
                                    rs.getDouble("phanTramGiamGia")
                            ),
                            rs.getString("CCCD"));

                    ctVe = new ChiTietVe(ve,cho,khachHang,giaCho,phanTramGiamGia);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ctVe;
        }
        public void doiChoTuMaVeMaGheCuMaGheMoi(String maVe,String maGheCu,String maGheMoi,double giaCho){
            try{
                String query = "UPDATE ChiTietVe SET maCho = ?,giaCho =? WHERE maVe =?  AND maCho = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1,maGheMoi);
                statement.setDouble(2,giaCho);
                statement.setString(3,maVe);
                statement.setString(4,maGheCu);
                statement.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    public static boolean themChiTietVeMoi(ChiTietVe chiTietVe){
        try{
            String query ="insert into ChiTietVe values (?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,chiTietVe.getVe().getMaVe());
            statement.setString(2,chiTietVe.getCho().getMaCho());
            statement.setString(3,chiTietVe.getKhachHang().getMaKhachHang());
            statement.setDouble(4,chiTietVe.getGiaCho());
            statement.setDouble(5,chiTietVe.getPhanTramGiamGia());
            statement.execute();
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
