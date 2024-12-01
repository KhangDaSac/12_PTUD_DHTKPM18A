package DAO;

import DTO.ChuyenTau;
import DTO.TuyenTau;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChuyenTau_DAO {


    public static ChuyenTau timChuyenTauTheoMaVe(String maVe){
        Connection con = ConnectDB.getInstance().getConnection();
        ChuyenTau chuyenTau = null;
        try {
            String query = "select * from ChuyenTau ct\n" +
                    "join Ve v on v.maChuyenTau = ct.maChuyenTau\n" +
                    "where v.maVe = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVe);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String maChuyenTau = rs.getString("maChuyenTau");
                TuyenTau tuyenTau = new TuyenTau(rs.getString("maTuyenTau"));
                //int soLuongCho = rs.getInt("soLuongCho");
                chuyenTau = new ChuyenTau(maChuyenTau);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chuyenTau;
    }

    public void suaThoiGianKhoiHanh (LocalDateTime thoiGianKhoiHanh, String maChuyen){
        Connection con = ConnectDB.getInstance().getConnection();
        try{
            String query = "update ChuyenTau set ngayKhoiHanh = ? where maChuyenTau = ?";
            PreparedStatement statement = con.prepareStatement(query);
            Timestamp thoiGian = Timestamp.valueOf(thoiGianKhoiHanh);
            statement.setTimestamp(1,thoiGian);
            statement.setString(2,maChuyen);
            statement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<ChuyenTau> getDanhSachChuyenTau(String maGaDi, String maGaDen, LocalDate ngayDi){
        ArrayList<ChuyenTau> dsChuyenTau= new ArrayList<ChuyenTau>();
        Connection con = ConnectDB.getInstance().getConnection();
        try {
            String query ="exec dbo.UDP_TimDanhSachChuyenTau ?, ?, ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maGaDi);
            statement.setString(2, maGaDen);
            statement.setString(3, TimeFormat.formatLocalDateSQL(ngayDi));
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String maChuyenTau =rs.getString("maChuyenTau");
                int soLuongCho = rs.getInt("soLuongCho");
                int soLuongChoDaDatVaBan = rs.getInt("soLuongChoDaDatVaBan");
                int soLuongChoDanhChoChanDaiHon = rs.getInt("soLuongChoDanhChoChanDaiHon");
                int soLuongChoTrong = rs.getInt("soLuongChoTrong");
                ChuyenTau chuyenTau = new ChuyenTau(
                        maChuyenTau,
                        soLuongCho,
                        soLuongChoDaDatVaBan,
                        soLuongChoDanhChoChanDaiHon,
                        soLuongChoTrong);
                dsChuyenTau.add(chuyenTau);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dsChuyenTau;
    }

}
