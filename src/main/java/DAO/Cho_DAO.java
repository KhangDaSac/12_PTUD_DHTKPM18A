package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Cho_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static ArrayList<Cho> getDanhSachChoTheoMaToaTau(String maToaTau, String gaDi, String gaDen){
        ArrayList<Cho> danhSachCho = new ArrayList<Cho>();

        try {
            String query = "exec dbo.UDP_GetDanhSachChoTheo_GaDi_GaDen_MaToa ?, ?, ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, gaDi);
            statement.setString(2, gaDen);
            statement.setString(3, maToaTau);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maCho = rs.getString("maCho");
                int soCho = rs.getInt("soCho");
                LoaiCho loaiCho = new LoaiCho(
                        rs.getString("maLoaiCho"),
                        rs.getString("tenLoaiCho"),
                        rs.getDouble("heSoGiaCho")
                );
                LoaiToaTau loaiToaTau = new LoaiToaTau(
                        rs.getString("maLoaiToa"),
                        rs.getString("tenLoaiToa"),
                        rs.getDouble("heSoGiaToaTau")
                );

                ChuyenTau chuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));

                ToaTau toaTau = new ToaTau(
                        rs.getString("maToaTau"),
                        chuyenTau,
                        rs.getInt("thuTuToa"),
                        loaiToaTau
                );
                TrangThaiCho trangThaiCho = TrangThaiCho.values()[rs.getInt("trangThaiCho")];
                Cho cho = new Cho(maCho, soCho, toaTau, loaiCho, trangThaiCho);
                danhSachCho.add(cho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachCho;
    }
    public static Cho getChoTheoMaCho(String maCho){
        String query = "select * from Cho where maCho = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maCho);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                LoaiCho loaiCho = LoaiCho_DAO.getLoaiChoTheoMa(rs.getString("maLoaiCho"));
                ToaTau toaTau = ToaTau_DAO.getToaTauTheoMa(rs.getString("maToaTau"));
                Cho cho = new Cho(rs.getString("maCho"), rs.getInt("soCho"),rs.getDouble("doDaiChanToiThieu"), toaTau, loaiCho);
                System.out.println("giá chỗ "+cho.getGiaCho());
                return cho;

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
