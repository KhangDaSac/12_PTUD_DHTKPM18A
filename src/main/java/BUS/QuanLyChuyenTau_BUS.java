package BUS;

import DAO.ChuyenTau_DAO;
import DAO.ToaTau_DAO;
import DTO.ChuyenTau;
import DTO.ToaTau;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class QuanLyChuyenTau_BUS {
    public static ArrayList<ChuyenTau> getDanhSachChuyenTau(String maGaDi, String maGaDen, LocalDate ngayDi) throws Exception {
        if(!maGaDi.matches("[A-Z]{3}")){
            throw new Exception("Mã ga đi không hợp lệ");
        }else if(!maGaDi.matches("[A-Z]{3}")){
            throw new Exception("Mã ga đến không hợp lệ");
        }if(!ngayDi.isAfter(LocalDate.now())){
            System.out.println(ngayDi);
            throw new Exception("Ngày đi không hợp lệ");
        }
        ChuyenTau_DAO chuyenTau_DAO = new ChuyenTau_DAO();
        ArrayList<ChuyenTau> chuyenTauList = chuyenTau_DAO.getDanhSachChuyenTau(maGaDi, maGaDen, ngayDi);
        return chuyenTauList;
    }

    public static ArrayList<ToaTau> getDanhSachToaTau(String maChuyenTau){
        ToaTau_DAO toaTau_DAO = new ToaTau_DAO();
        ArrayList<ToaTau> toaTauList = toaTau_DAO.xuatDanhSachToaTauTheoChuyen(maChuyenTau);
        return toaTauList;
    }

    public static double tinhKhoangCachHaiGa(String maGaDi, String maGaDen, String maChuyenTau){
        double khoangCach = 0;
        return khoangCach;
    }
}
