package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VeDat_DAO {
    public static ArrayList<VeDat> getDanhSachVeDatTheoMaHoaDonDat(String maHoaDon) {
        ArrayList<VeDat> dsVeDat = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "EXEC timDanhSachVeDatTheoMaHoaDonDat ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ChiTietChuyenTau thongTinGaDi = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"), rs.getString("maGaDi"));
                ChiTietChuyenTau thongTinGaDen = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"), rs.getString("maGaDen"));
                ArrayList<ChiTietVeDat> dsChiTietVeDat = ChiTietVeDat_DAO.getDanhSachChiTietVeDatTheoMaVeDat(rs.getString("maVeDat"));
                VeDat veDat = new VeDat(rs.getString("maVeDat"), new HoaDonDatVe(rs.getString("maHoaDonDatVe")), thongTinGaDi, thongTinGaDen, TrangThaiVeDat.valueOf(rs.getString("trangThaiVeDat")), LoaiVe.valueOf(rs.getString("loaiVe")));
                veDat.setDanhSachChiTietVeDat(dsChiTietVeDat);
                dsVeDat.add(veDat);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsVeDat;
    }

    public static ArrayList<VeDat> getDanhSachVeDatOTrangThaiChoLayVeTheoMaHoaDonDat(String maHoaDon) {
        ArrayList<VeDat> dsVeDat = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "EXEC timDanhSachVeDatCoTrangThaiChoLayVeTheoMaHoaDonDat ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ChiTietChuyenTau thongTinGaDi = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"), rs.getString("maGaDi"));
                ChiTietChuyenTau thongTinGaDen = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"), rs.getString("maGaDen"));
                ArrayList<ChiTietVeDat> dsChiTietVeDat = ChiTietVeDat_DAO.getDanhSachChiTietVeDatTheoMaVeDat(rs.getString("maVeDat"));
               VeDat veDat = new VeDat(rs.getString("maVeDat"), new HoaDonDatVe(rs.getString("maHoaDonDatVe")), thongTinGaDi, thongTinGaDen, TrangThaiVeDat.valueOf(rs.getString("trangThaiVeDat")), LoaiVe.valueOf(rs.getString("loaiVe")));
               veDat.setDanhSachChiTietVeDat(dsChiTietVeDat);
                dsVeDat.add(veDat);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsVeDat;
    }
    public static boolean capNhatTrangThaiVeDatHuyVe(ArrayList<VeDat> danhSachVeDat){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            int length = danhSachVeDat.size();
            String query = "update VeDat set trangThaiVeDat = 'DAHUY' where maVeDat in ( " + "?, ".repeat(length - 1) +  "?)";
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
    public static VeDat getVeDatTheoMa(String maVeDat){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "select * from VeDat where maVeDat = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVeDat);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                ChiTietChuyenTau thongTinGaDi = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"), rs.getString("maGaDi"));
                ChiTietChuyenTau thongTinGaDen = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"), rs.getString("maGaDen"));
                ArrayList<ChiTietVeDat> dsChiTietVeDat = ChiTietVeDat_DAO.getDanhSachChiTietVeDatTheoMaVeDat(rs.getString("maVeDat"));
                VeDat veDat = new VeDat(rs.getString("maVeDat"), new HoaDonDatVe(rs.getString("maHoaDonDatVe")), thongTinGaDi, thongTinGaDen, TrangThaiVeDat.valueOf(rs.getString("trangThaiVeDat")), LoaiVe.valueOf(rs.getString("loaiVe")));
                veDat.setDanhSachChiTietVeDat(dsChiTietVeDat);
                return veDat;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
