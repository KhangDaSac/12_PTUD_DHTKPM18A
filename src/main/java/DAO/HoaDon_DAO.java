package DAO;

import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
