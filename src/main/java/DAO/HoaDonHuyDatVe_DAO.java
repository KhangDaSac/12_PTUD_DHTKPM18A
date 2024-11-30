package DAO;

import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class HoaDonHuyDatVe_DAO {

    public static void themLichSuHuyDatVe(String maLichSuHuyDatVe, LocalDateTime thoiGianHuy, Double lePhi, Double soTienHoanTra, String maPhieuDatVe, String maCaLamViec){
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query = "insert into LichSuHuyDatVe values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maLichSuHuyDatVe);
            statement.setString(2, Timestamp.valueOf(thoiGianHuy).toString());
            statement.setFloat(3, lePhi.floatValue());
            statement.setFloat(4, soTienHoanTra.floatValue());
            statement.setString(5, maPhieuDatVe);
            statement.setString(6, maCaLamViec);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
