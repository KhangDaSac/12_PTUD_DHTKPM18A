package DAO;

import DTO.CaLamViec;
import DTO.NhanVien;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CaLamViec_DAO {
    public ArrayList<CaLamViec> xuatDanhSachCaLamViec (){
        ArrayList<CaLamViec> dsCaLamViec = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        try{
            String query = "select * from CaLamViec";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                String maCaLamViec = rs.getString(1);
                LocalDateTime thoiGianBatDau = rs.getTimestamp(2).toLocalDateTime();
                LocalDateTime thoiGianKetThuc =rs.getTimestamp(3).toLocalDateTime();
                NhanVien nhanVien = new NhanVien(rs.getString(4));
                CaLamViec caLamViec = new CaLamViec(maCaLamViec,thoiGianBatDau,thoiGianKetThuc,nhanVien);
                dsCaLamViec.add(caLamViec);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsCaLamViec;
    }


}
