package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietVeDat_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static ArrayList<ChiTietVeDat> getDanhSachPhieuDatVeTheoMaHoaDonDatVe(String maVeDat) {
        ArrayList<ChiTietVeDat> chiTietVeDat_list = new ArrayList<ChiTietVeDat>();
        try {
            String query = "exec UDP_GetDanhSachChiTietVeDatTheoMaVeDat ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVeDat);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ChiTietVeDat chiTietVeDat = new ChiTietVeDat(
                    new VeDat(rs.getString("maVeDat")),
                    new Cho(
                            rs.getString("maCho"),
                            rs.getInt("soCho"),
                            new ToaTau(
                                    rs.getString("maToaTau"),
                                    rs.getInt("thuTuToa"),
                                     new LoaiToaTau(
                                             rs.getString("maLoaiToa"),
                                             rs.getString("tenLoaiToa")
                                     )
                            ),
                            new LoaiCho(
                                    rs.getString("maLoaiCho"),
                                    rs.getString("tenLoaiCho")
                            )
                    ),
                    new KhachHang(
                            rs.getString("maKhachHang"),
                            rs.getString("CCCD"),
                            rs.getString("tenKhachHang"),
                            new LoaiKhachHang(
                                    rs.getString("maLoaiKhachHang"),
                                    rs.getString("tenLoaiKhachHang")
                            )
                    ),
                    rs.getDouble("giaCho"),
                    rs.getDouble("phanTramGiamGia")
                );
                chiTietVeDat_list.add(chiTietVeDat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietVeDat_list;
    }

    public static boolean themDanhSachChiTietVeDat(ArrayList<ChiTietVeDat> danhSachChiTietVeDat){
        try {
            String query = "insert into ChiTietVeDat values (?, ?, ?, ?, ?)";
            for(ChiTietVeDat chiTietVeDat : danhSachChiTietVeDat){
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, chiTietVeDat.getVeDat().getMaVeDat());
                statement.setString(2, chiTietVeDat.getCho().getMaCho());
                statement.setString(3, chiTietVeDat.getKhachHang().getMaKhachHang());
                statement.setDouble(4, chiTietVeDat.getGiaCho());
                statement.setDouble(5, chiTietVeDat.getPhanTramGiamGia());
                statement.execute();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
