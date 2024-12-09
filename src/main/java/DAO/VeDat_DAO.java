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
                ChiTietChuyenTau thongTinGaDi = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")), new GaTau(rs.getString("maGaDi")), rs.getTimestamp("thoiGianDi").toLocalDateTime());
                ChiTietChuyenTau thongTinGaDen = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")), new GaTau(rs.getString("maGaDen")), rs.getTimestamp("thoiGianDen").toLocalDateTime());
                LoaiCho loaiCho = LoaiCho_DAO.getLoaiChoTheoMa(rs.getString("maLoaiCho"));
                ToaTau toaTau = new ToaTau(rs.getString("maToaTau"), rs.getInt("thuTuToa"), new LoaiToaTau(rs.getString("maLoaiToa"), rs.getString("tenLoaiToa")));
                Cho cho = new Cho(rs.getString("maCho"), rs.getInt("soCho"), loaiCho, toaTau);
                LoaiKhachHang loaiKhachHang = LoaiKhachHang_DAO.getLoaiKhachHangTheoMaKhachHang(rs.getString("maKhachHang"));
                ChiTietVeDat chiTietVeDat = new ChiTietVeDat(cho, new VeDat(rs.getString("maVeDat")), rs.getDouble("giaCho"), new KhachHang(rs.getString("maKhachHang"),rs.getString("CCCD"),rs.getString("tenKhachHang"),loaiKhachHang), rs.getDouble("phanTramGiamGia"));
                VeDat veDat = null;
                for (VeDat vd : dsVeDat) {
                    if (vd.getMaVeDat().equals(rs.getString("maVeDat"))) {
                        veDat = vd;
                    }
                }
                if (veDat == null) {
                    veDat = new VeDat(rs.getString("maVeDat"), new HoaDonDatVe(rs.getString("maHoaDonDatVe")), thongTinGaDi, thongTinGaDen, TrangThaiVeDat.valueOf(rs.getString("trangThaiVeDat")), LoaiVe.valueOf(rs.getString("loaiVe")));
                    dsVeDat.add(veDat);
                }
                veDat.addChiTietVeDat(chiTietVeDat);
                    System.out.println("thu tu toa------" + cho.getMaCho());
//
                System.out.println("-----------------------" + veDat.getDanhSachChiTietVeDat().getFirst().getCho().getMaCho());
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


}
