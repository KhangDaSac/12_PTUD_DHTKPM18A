package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Ve_DAO {
    public static Connection con = ConnectDB.getInstance().getConnection();
    public String layMaVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maVeLonNhat = null;
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

    public static boolean themVeMoi(Ve ve,String maCT){
        try{
            String query ="insert into Ve values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,ve.getMaVe());
            statement.setString(2, null);
            statement.setString(3,maCT);
            statement.setString(4,ve.getThongTinGaTauDi().getGaTau().getMaGaTau());
            statement.setString(5,ve.getThongTinGaTauDen().getGaTau().getMaGaTau());
            statement.setDouble(6,0);
            statement.setString(7,ve.getLoaiVe().toString());
            statement.setString(8,ve.getTrangThaiVe().toString());
            statement.execute();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean capNhatTrangThaiHuyChoVeDoi(String maVe){
        try{
            String query = "UPDATE Ve SET trangThaiVe = 'DAHUY' WHERE maVe =?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maVe);
            statement.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String layMaVeCaNhanLonNhatCuaNgayHienTai(String ngayHienTai){
        String maVeLonNhat = null;
        try {
            String query = "select max(maVe) as maVe from Ve where maVe like 'VECN' + ? + '%'";
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

    public static Ve getVeTheoMa(String maVe){
        Ve veTim = new Ve();
        try{
            String query = "select*from Ve v join ChiTietChuyenTau ct on ct.maChuyenTau = v.maChuyenTau where  maVe=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVe);
            ResultSet rs = statement.executeQuery();
            while( rs.next()){
                String ma = rs.getString("maVe");
                HoaDonBanVe maHoaHon = new HoaDonBanVe(rs.getString("maHoaDonBanVe")) ;
                ChuyenTau maChuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
                GaTau gaDi = new GaTau(rs.getString("maGaDi"));
                GaTau gaDen= new GaTau(rs.getString("maGaDen"));
                ChiTietChuyenTau thongTinDi = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),
                        gaDi,
                        rs.getTimestamp("thoiGianDi").toLocalDateTime());
                ChiTietChuyenTau thongTinDen= new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),
                        gaDen,
                        rs.getTimestamp("thoiGianDen").toLocalDateTime());
                LoaiVe loai = LoaiVe.valueOf(rs.getString("loaiVe"));
                TrangThaiVe trangThai = TrangThaiVe.valueOf(rs.getString("trangThaiVe"));
                veTim = new Ve(ma,maHoaHon,thongTinDi,thongTinDen,loai,trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veTim;
    }
}
