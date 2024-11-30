package DAO;

import BUS.QuanLyVe_BUS;
import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Ve_DAO {
    public String layMaVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maVeLonNhat = null;
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select max(maVe) as maVe from Ve where maVe like 'V' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maVeLonNhat = rs.getString("maVe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maVeLonNhat;
    }

    public boolean themDanhSachVe(ArrayList<Ve> danhSachVe){
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "insert into Ve values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String maVeMoi = QuanLyVe_BUS.taoMaVeMoi();
        for(Ve ve : danhSachVe){
            ve.setMaVe(maVeMoi);
            maVeMoi = QuanLyVe_BUS.taoMaVeTiepTheo(maVeMoi);
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, ve.getMaVe());
                statement.setString(2, ve.getHoaDonBanVe().getMaHoaDonBanVe());
                statement.setString(3, ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
                statement.setString(4, ve.getThongTinGaTauDi().getGaTau().getMaGaTau());
                statement.setString(5, ve.getThongTinGaTauDen().getGaTau().getMaGaTau());
                statement.setDouble(6, ve.getPhanTramGiamGiaVeTapThe());
                statement.setString(8, ve.getLoaiVe().toString());
                statement.setString(9, ve.getTrangThaiVe().toString());
                statement.execute();

            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    public static ArrayList<Ve> xuatDanhSachVeTheoMaHoaDonBanVe(String maHoaDon){
        ArrayList<Ve> danhSachVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query= "select * from ChiTietVe as ctv join Ve as v on ctv.maVe=v.maVe join HoaDonBanVe as hdbv on v.maHoaDonBanVe= hdbv.maHoaDonBanVe where hdbv.maHoaDonBanVe = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                ChiTietChuyenTau thongTinGaDi = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),new GaTau(rs.getString("maGaDi")));
                ChiTietChuyenTau thongTinGaDen = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),new GaTau(rs.getString("maGaDen")));
                ChiTietVe chiTietVe = new ChiTietVe(new Ve(rs.getString("maVe")), new Cho(rs.getString("maCho")),rs.getDouble("giaCho"));
                Ve ve = null;
                for(Ve v: danhSachVe){
                    if(v.getMaVe().equals(rs.getString("maVe"))){
                        ve=v;
                    }
                }
                    if(ve==null){
                        ve = new Ve(rs.getString("maVe"),new HoaDonBanVe(rs.getString("maHoaDonBanVe")),thongTinGaDi, thongTinGaDen,LoaiVe.valueOf(rs.getString("loaiVe")),TrangThaiVe.valueOf(rs.getString("trangThaiVe")));
                        danhSachVe.add(ve);
                    }
                ve.addChiTietVe(chiTietVe);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return danhSachVe;
    }
   

}
