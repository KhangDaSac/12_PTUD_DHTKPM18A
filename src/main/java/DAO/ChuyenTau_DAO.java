package DAO;

import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import DTO.GaTau;
import DTO.TuyenTau;
import connectDB.ConnectDB;
import utils.TimeFormat;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChuyenTau_DAO {
    public static Connection con = ConnectDB.getInstance().getConnection();
    public static ArrayList<ChuyenTau> getDanhSachChuyenTau(String maGaDi, String maGaDen, LocalDate ngayDi){
        ArrayList<ChuyenTau> dsChuyenTau= new ArrayList<ChuyenTau>();
        try {
            String query ="exec dbo.UDP_GetDanhSachChuyenTauTheo_GaDi_GaDen_NgayDi ?, ?, ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maGaDi);
            statement.setString(2, maGaDen);
            statement.setString(3, TimeFormat.formatLocalDateSQL(ngayDi));
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String maChuyenTau = rs.getString("maChuyenTau");
                int soLuongChoDaDat = rs.getInt("daBan");
                int soLuongChoDaBan = rs.getInt("daDat");
                int soLuongChoDanhChoChanDaiHon = rs.getInt("danhChoChangDaiHon");
                int soLuongChoTrong = rs.getInt("conTrong");
                ChiTietChuyenTau thongTinGaTauDi = new ChiTietChuyenTau(
                        new ChuyenTau(maChuyenTau),
                        new GaTau(rs.getString("maGaDi")),
                        null,
                        rs.getTimestamp("thoiGianDi").toLocalDateTime(),
                        rs.getInt("thuTuGaDi"),
                        rs.getDouble("soKmGaDi")
                );

                ChiTietChuyenTau thongTinGaTauDen = new ChiTietChuyenTau(
                        new ChuyenTau(maChuyenTau),
                        new GaTau(rs.getString("maGaDen")),
                        rs.getTimestamp("thoiGianDen").toLocalDateTime(),
                        null,
                        rs.getInt("thuTuGaDen"),
                        rs.getDouble("soKmGaDen")
                );

                ChuyenTau chuyenTau = new ChuyenTau(
                        maChuyenTau,
                        soLuongChoDaBan,
                        soLuongChoDaDat,
                        soLuongChoDanhChoChanDaiHon,
                        soLuongChoTrong,
                        thongTinGaTauDi,
                        thongTinGaTauDen
                );
                dsChuyenTau.add(chuyenTau);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dsChuyenTau;
    }

}
