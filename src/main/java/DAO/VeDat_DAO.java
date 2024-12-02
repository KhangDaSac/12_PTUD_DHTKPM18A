package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VeDat_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static ArrayList<VeDat> getDanhSachPhieuDatVeTheoMaHoaDonDatVe(String maHoaDonDatVe) {
        ArrayList<VeDat> veDat_list = new ArrayList<VeDat>();

        try {
            String query = "exec UDP_TimDanhSachPhieuDatVeTheoMaHoaDonDatVe ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDonDatVe);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                VeDat veDat = new VeDat(
                        rs.getString("maVeDat"),
                        new HoaDonDatVe(rs.getString("maHoaDonDatVe")),
                        new ChiTietChuyenTau(
                                new ChuyenTau(rs.getString("maChuyenTau")),
                                new GaTau(
                                        rs.getString("maGaTauDi"),
                                        rs.getString("tenGaTauDi")
                                ),
                                rs.getTimestamp("thoiGianDi").toLocalDateTime()
                        ),
                        new ChiTietChuyenTau(
                                new ChuyenTau(rs.getString("maChuyenTau")),
                                new GaTau(
                                        rs.getString("maGaTauDi"),
                                        rs.getString("tenGaTauDen")
                                )
                        ),
                        TrangThaiVeDat.valueOf(rs.getString("trangThaiVeDat")),
                        LoaiVe.valueOf(rs.getString("loaiVe")),
                        rs.getDouble("phanTramGiamGiaVeTapThe"),
                        rs.getDouble("phanTramDatCoc")
                );
                veDat_list.add(veDat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veDat_list;
    }
}
