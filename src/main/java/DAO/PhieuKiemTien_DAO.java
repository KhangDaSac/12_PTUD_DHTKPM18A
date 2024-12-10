package DAO;

import DTO.PhieuKiemTien;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class PhieuKiemTien_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static boolean themPhieuKiemTien(PhieuKiemTien phieuKiemTien){
        try {
            String query = "insert into PhieuKiemTien values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, phieuKiemTien.getMaPhieuKiemTien());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(phieuKiemTien.getThoiGianKiemTien()));
            if(phieuKiemTien.getNhanVienKiemTien() != null){
                statement.setString(3, phieuKiemTien.getNhanVienKiemTien().getMaNhanVien());
            }else{
                statement.setNull(3, Types.VARCHAR);
            }
            if(phieuKiemTien.getNhanVienGiamSat() != null){
                statement.setString(4, phieuKiemTien.getNhanVienGiamSat().getMaNhanVien());
            }else{
                statement.setNull(4, Types.VARCHAR);
            }
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
