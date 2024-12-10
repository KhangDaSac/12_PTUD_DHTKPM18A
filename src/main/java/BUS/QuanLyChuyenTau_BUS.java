package BUS;

import DAO.*;
import DTO.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuanLyChuyenTau_BUS {
    public static ArrayList<ChuyenTau> getDanhSachChuyenTau(String maGaDi, String maGaDen, LocalDate ngayDi) throws Exception {
        if(!maGaDi.matches("[A-Z]{3}")){
            throw new Exception("Mã ga đi không hợp lệ");
        }else if(!maGaDi.matches("[A-Z]{3}")){
            throw new Exception("Mã ga đến không hợp lệ");
        }if(ngayDi.isBefore(LocalDate.now())){
            System.out.println(ngayDi);
            throw new Exception("Ngày đi không hợp lệ");
        }
        return ChuyenTau_DAO.getDanhSachChuyenTau(maGaDi, maGaDen, ngayDi);
    }

    public static ArrayList<ToaTau> getDanhSachToaTau(String maChuyenTau, String maGaDi, String maGaDen){
        ArrayList<ToaTau> toaTauList = ToaTau_DAO.getDanhSachToaTauTheoChuyen(maChuyenTau, maGaDi, maGaDen);
        return toaTauList;
    }


    public static ArrayList<GaTau> getDanhSachGaTau(){
        GaTau_DAO gaTau_DAO = new GaTau_DAO();
        ArrayList<GaTau> gaTauList = gaTau_DAO.xuatDanhSachGaTau();
        return gaTauList;
    }

    public static ArrayList<Cho> getDanhSachChoTheoMaToaTau(String maToaTau, String maGaDi, String maGaDen){
        return Cho_DAO.getDanhSachChoTheoMaToaTau(maToaTau, maGaDi, maGaDen);
    }

    public static ChiTietChuyenTau getChiTietTuyenTauTheoChuyenTauVaGaTau(ChuyenTau chuyenTau, GaTau gaTau){
        ChiTietChuyenTau_DAO chiTietChuyenTau_DAO = new ChiTietChuyenTau_DAO();
        ChiTietChuyenTau chiTietChuyenTau = chiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(chuyenTau.getMaChuyenTau(), gaTau.getMaGaTau());
        return chiTietChuyenTau;
    }
    public static ToaTau timToaTauTheoMaChuyenTau(String maChuyenTau){
        ToaTau_DAO toaTauDao= new ToaTau_DAO();
        ToaTau toaTau = toaTauDao.timToaTauTheoMaChuyenTau(maChuyenTau);
        return  toaTau;
    }

    public static ArrayList<ChuyenTau> getDanhSachChuyenTauTheo_MaChuyen_MaTuyen_NgayDi(String maChuyenTau, String maTuyenTau, LocalDate ngayKhoiHanh) throws Exception {

        if((maChuyenTau == null || maChuyenTau.isEmpty())
            && maTuyenTau == null || maTuyenTau.isEmpty()
            && ngayKhoiHanh == null)
            throw new Exception("Vui lòng nhập thông tin tìm kiếm");

        maChuyenTau = maChuyenTau == null ? "" : maChuyenTau;
        maTuyenTau = maTuyenTau == null ? "" : maTuyenTau;
        return ChuyenTau_DAO.getDanhSachChuyenTauTheo_MaChuyen_MaTuyen_NgayDi(maChuyenTau, maTuyenTau, ngayKhoiHanh);
    }

    public static ArrayList<ChiTietChuyenTau> getLichTrinhTheoMaChuyenTau(String maChuyenTau){
        return ChiTietChuyenTau_DAO.getLichTrinhTheoMaChuyenTau(maChuyenTau);
    }
}
