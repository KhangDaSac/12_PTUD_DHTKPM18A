package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaiKhoan_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static NhanVien dangNhap(String taiKhoan, String maKhauBam){
        try {
            String query = "select nv.maNhanVien, tenNhanVien, CCCD, soDienThoai, diaChi, trangThaiNhanVien, loaiNhanVien" +
                    " from TaiKhoan tk " +
                    " join NhanVien nv on tk.maNhanVien = nv.maNhanVien" +
                    " where nv.maNhanVien = ? and matKhau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, taiKhoan);
            statement.setString(2, maKhauBam);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                String maNhanVien = rs.getString("maNhanVien");
                String tenNhanVien = rs.getString("tenNhanVien");
                String cccd = rs.getString("CCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                TrangThaiNhanVien trangThaiNhanVien = TrangThaiNhanVien.valueOf(rs.getString("trangThaiNhanVien"));
                LoaiNhanVien loaiNhanVien = LoaiNhanVien.valueOf(rs.getString("loaiNhanVien"));
                return new NhanVien(maNhanVien, cccd, tenNhanVien, soDienThoai, diaChi, loaiNhanVien, trangThaiNhanVien);
            }
        }catch (Exception e){

        }
        return null;
    }

    public static boolean doiMatKhau(String maNhanVien, String matKhauMoi){
        try {
            String query = "update TaiKhoan set matKhau = ? where maNhanVien = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, matKhauMoi);
            statement.setString(2, maNhanVien);
            statement.execute();

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
