package DAO;

import DTO.CaLamViec;
import DTO.NhanVien;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CaLamViec_DAO {
    public static Connection con = ConnectDB.getInstance().getConnection();
    public static String layDuoiMaCaLamViecLonNhatCuaNgayHienTai(String ngayHienTai){
        String maVeLonNhat = null;
        try {
            String query = "select max(SUBSTRING(maCaLamViec, LEN(maCaLamViec) - 2, 3)) as duoiMaCaLamViec from CaLamViec where maCaLamViec like 'CLV' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maVeLonNhat = rs.getString("duoiMaCaLamViec");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maVeLonNhat;
    }

    public static boolean themCaLamViec(CaLamViec caLamViec){
        try {
            String query = "insert into CaLamViec values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, caLamViec.getMaCaLamViec());
            statement.setString(2, TimeFormat.formatLocalDateTimeSQL(caLamViec.getThoiGianBatDau()));
            if(caLamViec.getThoiGianKetThuc() != null){
                statement.setString(3, TimeFormat.formatLocalDateTimeSQL(caLamViec.getThoiGianKetThuc()));
            }else{
                statement.setNull(3, Types.DATE);
            }
            statement.setString(4, caLamViec.getNhanVien().getMaNhanVien());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}