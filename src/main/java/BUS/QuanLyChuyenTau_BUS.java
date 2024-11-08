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
        ChuyenTau_DAO chuyenTau_DAO = new ChuyenTau_DAO();
        ArrayList<ChuyenTau> chuyenTauList = chuyenTau_DAO.getDanhSachChuyenTau(maGaDi, maGaDen, ngayDi);
        return chuyenTauList;
    }

    public static ArrayList<ToaTau> getDanhSachToaTau(String maChuyenTau, String maGaDi, String maGaDen){
        ToaTau_DAO toaTau_DAO = new ToaTau_DAO();
        ArrayList<ToaTau> toaTauList = toaTau_DAO.getDanhSachToaTauTheoChuyen(maChuyenTau, maGaDi, maGaDen);
        return toaTauList;
    }


    public static ArrayList<GaTau> getDanhSachGaTau(){
        GaTau_DAO gaTau_DAO = new GaTau_DAO();
        ArrayList<GaTau> gaTauList = gaTau_DAO.xuatDanhSachGaTau();
        return gaTauList;
    }

    public static ArrayList<Cho> getDanhSachChoTheoMaToaTau(String maToaTau, String maGaDi, String maGaDen){
        Cho_DAO cho_DAO = new Cho_DAO();
        ArrayList<Cho> choList = cho_DAO.getDanhSachChoTheoMaToaTau(maToaTau, maGaDi, maGaDen);
        return choList;
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


//    public static LocalDateTime getThoiGianDi(String maChuyenTau, String maGaDi){
//        ChuyenTau_DAO chuyenTau_DAO = new ChuyenTau_DAO();
//        LocalDateTime thoiGianDi
//    }
}
