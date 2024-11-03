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
    public boolean themDanhSachChiTietVe(ArrayList<ChiTietVe> danhSachChiTietVe){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "insert into ChiTietVe values (?, ?, ?, ?, ?, ?)";
        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, chiTietVe.getVe().getMaVe());
                statement.setString(2, chiTietVe.getCho().getMaCho());
                statement.setString(3, chiTietVe.getKhachHang().getMaKhachHang());
                statement.setDouble(4, chiTietVe.getGiaCho());
                statement.setDouble(5, chiTietVe.getSoTienGiamGia());
                statement.setDouble(6, chiTietVe.getThanhTien());

                statement.executeUpdate();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
        public static ChiTietVe getCTVeTheoMaVe(String maVe){
            ChiTietVe ctVe = null;
            Connection con = ConnectDB.getInstance().getConnection();
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
                    KhachHang khachHang = new KhachHang(
                            rs.getString("maKhachHang"),
                            rs.getString("tenKhachHang"),
                            new LoaiKhachHang(
                                    rs.getString("maLoaiKhachHang"),
                                    rs.getString("tenLoaiKhachHang"),
                                    rs.getDouble("phanTramGiamGia")
                            ),
                            rs.getString("CCCD"));

                    ctVe = new ChiTietVe(giaCho,ve,cho,khachHang);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ctVe;
        }
        public void doiChoTuMaVeMaGheCuMaGheMoi(String maVe,String maGheCu,String maGheMoi){
            Connection con = ConnectDB.getInstance().getConnection();
            try{
                String query = "UPDATE ChiTietVe SET maCho = ? WHERE maVe =?  AND maCho = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1,maGheMoi);
                statement.setString(2,maVe);
                statement.setString(3,maGheCu);
                statement.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}
