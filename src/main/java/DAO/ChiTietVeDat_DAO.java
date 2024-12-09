package DAO;

import DTO.ChiTietVeDat;
import DTO.Cho;
import DTO.KhachHang;
import DTO.VeDat;
import connectDB.ConnectDB;

import java.sql.*;
import java.util.ArrayList;

public class ChiTietVeDat_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static ArrayList<ChiTietVeDat> getDanhSachChiTietVeDatTheoMaVeDat(String maVeDat)  {
        ArrayList<ChiTietVeDat> dsChiTietVeDat = new ArrayList<>();
        String query = "select CTVD.*" +
                "from ChiTietVeDat as CTVD JOIN VeDat AS VD ON CTVD.maVeDat = VD.maVeDat " +
                "WHERE VD.maVeDat = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVeDat);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Cho cho = Cho_DAO.getChoTheoMaCho(rs.getString("maCho"));
                KhachHang khachHang =  KhachHang_DAO.getKhachHangTheoMaKhachHang(rs.getString("maKhachHang"));
                ChiTietVeDat chiTietVeDat = new ChiTietVeDat(cho,new VeDat(rs.getString("maVeDat")),rs.getDouble("giaCho"),khachHang,rs.getDouble("phanTramGiamGia"));
                dsChiTietVeDat.add(chiTietVeDat);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsChiTietVeDat;
    }
}
