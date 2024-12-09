package DAO;

import DTO.CaLamViec;
import DTO.HoaDonBanVe;
import DTO.HoaDonDatVe;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HoaDonDatVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static ArrayList<HoaDonDatVe> getDanhSachHoaDonDatTheoMaKhachHang(String maKhachHang){
        ArrayList<HoaDonDatVe> danhSachHoaDonDat = new ArrayList<HoaDonDatVe>();
        try {
            String query = "exec UDP_GetDanhSachHoaDonDatTheoMaKhachHang ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maKhachHang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                HoaDonDatVe hoaDonDatVe = new HoaDonDatVe(
                        rs.getString("maHoaDonDatVe"),
                        rs.getTimestamp("thoiGianLap").toLocalDateTime(),
                        new CaLamViec(rs.getString("maCaLamViec"))
                );
                hoaDonDatVe.setDanhSachVeDat(VeDat_DAO.getDanhSachPhieuDatVeTheoMaHoaDonDatVe(hoaDonDatVe.getMaHoaDonDatVe()));
                danhSachHoaDonDat.add(hoaDonDatVe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachHoaDonDat;
    }

}
