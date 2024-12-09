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

                ChuyenTau chuyenTau = new ChuyenTau(
                        maChuyenTau,
                        soLuongChoDaBan,
                        soLuongChoDaDat,
                        soLuongChoDanhChoChanDaiHon,
                        soLuongChoTrong
                );
                dsChuyenTau.add(chuyenTau);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dsChuyenTau;
    }

    public static ChuyenTau timChuyenTauTheoMaVe(String maVe){
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


    public static ArrayList<ChuyenTau> getDanhSachChuyenTauTheo_MaChuyen_MaTuyen_NgayDi(String maChuyenTau, String maTuyenTau, LocalDate ngayKhoiHanh){
        ArrayList<ChuyenTau> danhSachChuyenTau = new ArrayList<ChuyenTau>();

        String query ="exec dbo.UDP_GetDanhSachChuyenTauTheo_MaChuyen_MaTuyen_NgayKhoiHanh ?, ?, ?";
        System.out.println(maChuyenTau);
        System.out.println(maTuyenTau);
        System.out.println(ngayKhoiHanh);

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maChuyenTau);
            statement.setString(2, maTuyenTau);
            statement.setString(3, TimeFormat.formatLocalDateSQL(ngayKhoiHanh));
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                danhSachChuyenTau.add(new ChuyenTau(
                        rs.getString("maChuyenTau"),
                        new TuyenTau(
                                rs.getString("maTuyenTau"),
                                rs.getString("tenTuyenTau"),
                                rs.getString("moTa")
                        ),
                        rs.getInt("soLuongCho"),
                        rs.getInt("soLuongVe"),
                        rs.getInt("soLuongVeDat")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return danhSachChuyenTau;
    }

}
