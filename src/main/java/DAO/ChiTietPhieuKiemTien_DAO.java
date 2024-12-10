package DAO;

import DTO.ChiTietPhieuKiemTien;
import DTO.PhieuKiemTien;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;

public class ChiTietPhieuKiemTien_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static boolean themDanhSachChiTietPhieuKiemTien(ArrayList<ChiTietPhieuKiemTien> dsChiTietPhieuKiemTien){
        try {
            String query = "insert into ChiTietPhieuKiemTien values (?, ?, ?, ?)";
            for (ChiTietPhieuKiemTien chiTietPhieuKiemTien : dsChiTietPhieuKiemTien){
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, chiTietPhieuKiemTien.getMenhGia().toString());
                statement.setDouble(2, chiTietPhieuKiemTien.getGiaTriMenhGia());
                statement.setInt(3, chiTietPhieuKiemTien.getSoLuong());
                statement.setString(4, chiTietPhieuKiemTien.getPhieuKiemTien().getMaPhieuKiemTien());
                statement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
