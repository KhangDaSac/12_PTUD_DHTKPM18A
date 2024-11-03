package DAO;

import DTO.CaLamViec;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.TrangThaiHoaDon;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HoaDon_DAO {


    public String layMaHoaDonLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonLonNhat = null;
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "select max(maHoaDon) as maHoaDon from HoaDon where maHoaDon like 'HD' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maHoaDonLonNhat = rs.getString("maHoaDon");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoaDonLonNhat;
    }

    public boolean themHoaDon(HoaDon hoaDon){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "insert into HoaDon values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, hoaDon.getMaHoaDon());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(hoaDon.getThoiGianLap()));
            statement.setDouble(3, hoaDon.getTongTien());
            statement.setDouble(4, hoaDon.getTongTienDaDatCoc());
            statement.setDouble(5, hoaDon.getTongTienKhachHangTra());
            statement.setString(6, hoaDon.getLoaiHoaDon().toString());
            statement.setString(7, hoaDon.getTrangThaiHoaDon().toString());
            statement.setString(8, hoaDon.getCaLamViec().getMaCaLamViec());
            statement.setString(9, hoaDon.getKhachHangMua().getMaKhachHang());
            statement.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ArrayList<HoaDon> getDanhSachHoaDonDatTheoMaKhachHang(String maKhachHang){
        Connection con = ConnectDB.getInstance().getConnection();
        ArrayList<HoaDon> hoaDonList = new ArrayList<HoaDon>();
        try{
            String query = "exec UDP_TimDanhSachHoaDonDatTheoMaKhachHang ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maKhachHang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String maHoaDon = rs.getString("maHoaDon");
                LocalDateTime thoiGianLap = rs.getTimestamp("thoiGianLap").toLocalDateTime();
                double tongTien = rs.getDouble("tongTien");
                double tongTienDatCoc = rs.getDouble("tongTienDaDatCoc");
                TrangThaiHoaDon trangThaiHoaDon = TrangThaiHoaDon.valueOf(rs.getString("trangThaiHoaDon"));
                CaLamViec caLamViec = new CaLamViec(rs.getString("maCaLamViec"));
                KhachHang khachHang = new KhachHang(rs.getString("maKhachHangMua"));
                HoaDon hoaDon = new HoaDon(maHoaDon, thoiGianLap, tongTien, tongTienDatCoc, trangThaiHoaDon, caLamViec, khachHang);
                hoaDonList.add(hoaDon);
            }
        } catch (Exception e) {

        }
        return hoaDonList;
    }
}
