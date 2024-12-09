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
                                        rs.getString("maGaTauDen"),
                                        rs.getString("tenGaTauDen")
                                )
                        ),
                        TrangThaiVeDat.valueOf(rs.getString("trangThaiVeDat")),
                        LoaiVe.valueOf(rs.getString("loaiVe")),
                        rs.getDouble("phanTramGiamGiaVeTapThe"),
                        rs.getDouble("phanTramDatCoc")
                );
                veDat.setDanhSachChiTietVeDat(ChiTietVeDat_DAO.getDanhSachPhieuDatVeTheoMaHoaDonDatVe(veDat.getMaVeDat()));
                veDat_list.add(veDat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veDat_list;
    }

    public static boolean capNhatTrangThaiVeDat(ArrayList<VeDat> danhSachVeDat){
        try {
            int length = danhSachVeDat.size();
            String query = "update VeDat set trangThaiVeDat = 'DALAYVE' where maVeDat in ( " + "?, ".repeat(length - 1) +  "?)";
            PreparedStatement statement = con.prepareStatement(query);

            for(int i = 0; i < length; i++){
                VeDat veDat = danhSachVeDat.get(i);
                statement.setString(i + 1, veDat.getMaVeDat());
            }
            statement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static String layDuoiMaVeDatLonNhatCuaNgayHienTai(String ngayHienTai){
        String duoiMaVeDatLonNhat = null;
        try {
            String query = "select max(SUBSTRING(maVeDat, LEN(maVeDat) - 7, 8)) as duoiMaVeDat from VeDat where maVeDat like 'VD[A-Z][A-Z]' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                duoiMaVeDatLonNhat = rs.getString("duoiMaVeDat");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duoiMaVeDatLonNhat;
    }

    public static boolean themDanhSachVeDat(ArrayList<VeDat> danhSachVeDat){
        try {
            String query = "insert into VeDat values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            for(VeDat veDat : danhSachVeDat){
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, veDat.getMaVeDat());
                statement.setString(2, veDat.getHoaDonDatVe().getMaHoaDonDatVe());
                statement.setString(3, veDat.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
                statement.setString(4, veDat.getThongTinGaTauDi().getGaTau().getMaGaTau());
                statement.setString(5, veDat.getThongTinGaTauDen().getGaTau().getMaGaTau());
                statement.setDouble(6, veDat.getPhanTramGiamGiaVeTapThe());
                statement.setDouble(7, veDat.getPhanTramDatCoc());
                statement.setString(8, veDat.getLoaiVe().toStringSQL());
                statement.setString(9, veDat.getTrangThaiVeDat().toString());
                statement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
