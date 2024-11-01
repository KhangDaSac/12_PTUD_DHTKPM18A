package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public Ve getVeTheoMa(String maVe){
        Ve veTim = new Ve();
        Connection con = ConnectDB.getInstance().getConnection();
        try{
            String query = "select * from Ve where maVe = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVe);
            ResultSet rs = statement.executeQuery();
            while( rs.next()){
                String ma = rs.getString("maVe");
                HoaDon maHoaHon = new HoaDon(rs.getString("maHoaDon")) ;
                ChuyenTau maChuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
                GaTau gaDi = new GaTau(rs.getString("maGaDi"));
                GaTau gaDen= new GaTau(rs.getString("maGaDen"));
                ChiTietChuyenTau thongTinDi = new ChiTietChuyenTau(maChuyenTau,gaDi);
                ChiTietChuyenTau thongTinDen= new ChiTietChuyenTau(maChuyenTau,gaDen);
                Double giamGiaTT = rs.getDouble("giamGiaVeTapThe");
                Double tongTien= rs.getDouble("tongTienVe");
                LoaiVe loai = LoaiVe.valueOf(rs.getString("loaiVe"));
                TrangThaiVe trangThai = TrangThaiVe.valueOf(rs.getString("trangThaiVe"));
                veTim = new Ve(maHoaHon,ma,thongTinDi,thongTinDen,tongTien,giamGiaTT,loai,trangThai,maChuyenTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veTim;
    }
}
