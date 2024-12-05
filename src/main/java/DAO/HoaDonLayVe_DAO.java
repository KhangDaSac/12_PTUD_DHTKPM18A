package DAO;

import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HoaDonLayVe_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static String layMaHoaDonLayVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maHoaDonLonNhat = null;
        try {
            String query = "select max(maHoaDonLayVe) as maHoaDonLayVe from HoaDonLayVe where maHoaDonLayVe like 'HDLV' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maHoaDonLonNhat = rs.getString("maHoaDonLayVe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHoaDonLonNhat;
    }
}
