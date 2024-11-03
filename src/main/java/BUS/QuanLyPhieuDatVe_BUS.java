package BUS;

import DAO.ChiTietPhieuDatVe_DAO;
import DAO.PhieuDatVe_DAO;
import DTO.ChiTietPhieuDatVe;
import DTO.PhieuDatVe;

import java.util.ArrayList;

public class QuanLyPhieuDatVe_BUS {
    public static ArrayList<PhieuDatVe> getDanhSachPhieuDatVeTheoMaHoaDon(String maHoaDon){
        return PhieuDatVe_DAO.getDanhSachPhieuDatVeTheoMaHoaDon(maHoaDon);
    }

    public static ArrayList<ChiTietPhieuDatVe> getDanhSachChiTietPhieuDatVeTheoMaHoaDon(String maHD){
        return ChiTietPhieuDatVe_DAO.getDanhSachChiTietPhieuDatVeTheoMaHoaDon(maHD);
    }
    public static ArrayList<ChiTietPhieuDatVe> getDanhSachChiTietPhieuDatVeTheoMaPhieuDatVe(String maPDV){
        return ChiTietPhieuDatVe_DAO.getDanhSachChiTietPhieuDatVeTheoMaPhieuDatVe(maPDV);
    }
    public static void huyPhieuDatVe(String maPhieuDatVe){
        PhieuDatVe_DAO.huyPhieuDatVe(maPhieuDatVe);
    }
}
