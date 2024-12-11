package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChiTietHoaDonLayVe_DAO {
    public static ArrayList<ChiTietHoaDonLayVe> getDanhSachChiTietHoaDonLayVeTheoMaHoaDonLayVe(String maHoaDon){
        ArrayList<ChiTietHoaDonLayVe> dsChiTietHoaDonLayVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "EXEC timDanhSachChiTietHoaDonLayVeTheoMaHoaDon ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                HoaDonLayVe hoaDonLayVe = new HoaDonLayVe(rs.getString("maHoaDonLayVe"),rs.getTimestamp("thoiGianLayVe").toLocalDateTime(),new KhachHang_DAO().getKhachHangTheoMaKhachHang(rs.getString("maKhachHangLayVe")),new CaLamViec_DAO().getCaLamViecTheoMa(rs.getString("maCaLamViec")));
                VeDat veDat = VeDat_DAO.getVeDatTheoMa(rs.getString("maVeDat"));
                Ve ve = Ve_DAO.getVeTheoMa(rs.getString("maVe"));
                ChiTietHoaDonLayVe chiTietHoaDonLayVe = new ChiTietHoaDonLayVe(veDat,ve,hoaDonLayVe);
                dsChiTietHoaDonLayVe.add(chiTietHoaDonLayVe);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dsChiTietHoaDonLayVe;
    }
}
