package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class KhachHang_DAO {
    Connection con = ConnectDB.getInstance().getConnection();
    ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
    public ArrayList<KhachHang> xuatDanhSachKhachHang (){
        try {
            String query = "select * from KhachHang kh join LoaiKhachHang lkh on lkh.maLoaiKhachHang= kh.maLoaiKhachHang ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String maKhachHang = rs.getString("maKhachHang");
                String cCCD = rs.getString("CCCD");
                String tenKhachHang = rs.getString("tenKhachHang");
                String soDienThoai = rs.getString("soDienThoai");
                LoaiKhachHang maloaiKhachHang = new LoaiKhachHang(rs.getString("maLoaiKhachHang"),rs.getString("tenLoaiKhachHang"),rs.getDouble("phanTramGiamGia"));
                LocalDate ngaySinh = rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null;
                //LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                KhachHang khachHang = new KhachHang(maKhachHang,cCCD, tenKhachHang,soDienThoai,maloaiKhachHang,ngaySinh );
                dsKhachHang.add(khachHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  dsKhachHang;
    }

    public void suaThongTinKhachHang (KhachHang kh){
        try{
            String query = "update KhachHang SET CCCD = ?, tenKhachHang = ?, soDienThoai = ?, maLoaiKhachHang = ?, ngaySinh = ? WHERE maKhachHang = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,kh.getCCCD());
            statement.setString(2,kh.getTenKhachHang());
            //statement.setDate(3,Date.valueOf(kh.getNgaySinh()));
            statement.setString(3,kh.getSoDienThoai());

            statement.setString(4,kh.getLoaiKhachHang().getMaLoaiKhachHang());
            statement.setString(6,kh.getMaKhachHang());
            if (kh.getNgaySinh() != null) {
                statement.setDate(5, Date.valueOf(kh.getNgaySinh()));
            } else {
                statement.setNull(5, java.sql.Types.DATE);
            }
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public  void addKhachHang (KhachHang kh){
        try {
            String query = "insert into KhachHang values(?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            System.out.println(kh.getLoaiKhachHang()+"thêm");
            statement.setString(1,kh.getMaKhachHang());
            statement.setString(2,kh.getCCCD());
            statement.setString(3,kh.getTenKhachHang());
            statement.setDate(4,Date.valueOf(kh.getNgaySinh()));
            statement.setString(5,kh.getSoDienThoai());
            statement.setString(6,kh.getLoaiKhachHang().getMaLoaiKhachHang());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
