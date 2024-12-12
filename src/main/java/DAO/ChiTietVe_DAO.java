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
import java.util.Collections;

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
                        "join ToaTau tt on tt.maToaTau =c.maToaTau\n"+
                        "join LoaiToaTau ltt on ltt.maLoaiToa = tt.maLoaiToa\n"+
                        "join LoaiCho lc on lc.maLoaiCho = c.maLoaiCho\n"+
                        "where v.maVe = ? ";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, maVe);
                ResultSet rs = statement.executeQuery();
                while( rs.next()){
                    Ve ve = new Ve(rs.getString("maVe"));
                    Cho cho = new Cho(
                            new ToaTau(
                                    rs.getString("maToaTau"),
                                    rs.getInt("thuTuToa"),
                                            new LoaiToaTau(
                                                    rs.getString("maLoaiToa"),
                                                    rs.getString("tenLoaiToa")
                                            )
                            ),
                            rs.getString("maCho"),
                            rs.getInt("soCho"),
                            new LoaiCho(
                                    rs.getString("maLoaiCho"),
                                    rs.getString("tenLoaiCho")
                            )
                    );

                    double giaCho = rs.getDouble("giaCho");
                    double phanTramGiamGia = rs.getDouble("phanTramGiamGia");
                    KhachHang khachHang = new KhachHang(
                            rs.getString("maKhachHang"),
                            rs.getString("CCCD"),
                            rs.getString("tenKhachHang"),
                            rs.getString("soDienThoai"),
                            new LoaiKhachHang(
                                    rs.getString("maLoaiKhachHang"),
                                    rs.getString("tenLoaiKhachHang"),
                                    rs.getDouble("phanTramGiamGia")
                            )
                            );

                    ctVe = new ChiTietVe(ve, cho, khachHang, giaCho, phanTramGiamGia);
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
    public static boolean themDanhSachChiTietVe(ArrayList<ChiTietVe> danhSachChiTietVe){
        String query = "insert into ChiTietVe values (?, ?, ?, ?, ?)";
        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, chiTietVe.getVe().getMaVe());
                statement.setString(2, chiTietVe.getCho().getMaCho());
                statement.setString(3, chiTietVe.getKhachHang().getMaKhachHang());
                statement.setDouble(4, chiTietVe.getGiaCho());
                statement.setDouble(5, chiTietVe.getPhanTramGiamGia());
                statement.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public static ArrayList<ChiTietVe> getDanhSachChiTietVeTheoMaVe_BaoCao(String maVe){
        ArrayList<ChiTietVe> dsChiTietVe = new ArrayList<>();
        String query = "SELECT maVe, maCho, giaCho, phanTramGiamGia FROM ChiTietVe where maVe = ?";
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, maVe);

                ResultSet rs = statement.executeQuery();
                while (rs.next()){
                    dsChiTietVe.add(new ChiTietVe(
                            new Ve(rs.getString("maVe")),
                            new Cho(rs.getString("maCho")),
                            rs.getDouble("giaCho"),
                            rs.getDouble("phanTramGiamGia")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        return dsChiTietVe;
    }
    public static ArrayList<ChiTietVe> xuatDanhSachChiTietVeTheoMaVe(String maVe){
        ArrayList<ChiTietVe> danhSachChiTietVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "select * from ChiTietVe where maVe = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVe);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Ve ve = new Ve(rs.getString("maVe"));
                Cho cho = Cho_DAO.getChoTheoMaCho(rs.getString("maCho"));
                KhachHang khachHang = KhachHang_DAO.getKhachHangTheoMaKhachHang(rs.getString("maKhachHang"));
                double giaCho = rs.getDouble("giaCho");
                ChiTietVe chiTietVe = new ChiTietVe(ve, cho, khachHang, giaCho,rs.getDouble("phanTramGiamGia"));
                danhSachChiTietVe.add(chiTietVe);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return danhSachChiTietVe;
    }

}
