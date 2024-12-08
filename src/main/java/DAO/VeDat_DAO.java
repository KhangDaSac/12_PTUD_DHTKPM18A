package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VeDat_DAO {
  public static ArrayList<VeDat> getDanhSachVeDatTheoMaHoaDonDat(String maHoaDon){
      ArrayList<VeDat> dsVeDat = new ArrayList<>();
      Connection con = ConnectDB.getInstance().getConnection();
      String query = "EXEC timDanhSachVeDatTheoMaHoaDonDat ?";
      try{
          PreparedStatement statement = con.prepareStatement(query);
          statement.setString(1,maHoaDon);
          ResultSet rs = statement.executeQuery();
          while(rs.next()){
              ChiTietChuyenTau thongTinGaDi = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),new GaTau(rs.getString("maGaDi")),rs.getTimestamp("thoiGianDi").toLocalDateTime());
              ChiTietChuyenTau thongTinGaDen = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),new GaTau(rs.getString("maGaDen")),rs.getTimestamp("thoiGianDen").toLocalDateTime());
              LoaiCho loaiCho = LoaiCho_DAO.getLoaiChoTheoMa(rs.getString("maLoaiCho"));
              ChiTietVeDat chiTietVeDat = new ChiTietVeDat(new Cho(rs.getString("maCho"),rs.getInt("soCho"),loaiCho ) ,new VeDat(rs.getString("maVeDat")),rs.getDouble("giaCho"),new KhachHang(rs.getString("maKhachHang")),rs.getDouble("phanTramGiamGia"));
              VeDat veDat = null;
              for(VeDat vd: dsVeDat){
                  if(vd.getMaVeDat().equals(rs.getString("maVeDat"))){
                      veDat=vd;
                  }
              }
              if(veDat==null){
                  veDat = new VeDat(rs.getString("maVeDat"),new HoaDonDatVe(rs.getString("maHoaDonDatVe")),thongTinGaDi,thongTinGaDen,TrangThaiVeDat.valueOf(rs.getString("trangThaiVeDat")),LoaiVe.valueOf(rs.getString("loaiVe")));
                  dsVeDat.add(veDat);
              }
              veDat.addChiTietVeDat(chiTietVeDat);
              System.out.println("-----------------------"+ veDat.getDanhSachChiTietVeDat().getFirst().getCho().getMaCho());
          }

      } catch (Exception e) {
          throw new RuntimeException(e);
      }
      return  dsVeDat;
  }

}
