package DAO;

import DTO.CaLamViec;
import DTO.PhieuKetToan;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

public class PhieuKetToan_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static boolean themPhieuKetToan(PhieuKetToan phieuKetToan){
        try {
            String query = "insert into PhieuKetToan values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, phieuKetToan.getMaPhieuKetToan());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(phieuKetToan.getThoiGianLap()));
            statement.setNull(3, Types.DOUBLE);
            statement.setString(4, phieuKetToan.getCaLamViec().getMaCaLamViec());
            statement.setString(5, phieuKetToan.getPhieuKiemTienDauCa().getMaPhieuKiemTien());
            statement.setNull(6, Types.VARCHAR);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
