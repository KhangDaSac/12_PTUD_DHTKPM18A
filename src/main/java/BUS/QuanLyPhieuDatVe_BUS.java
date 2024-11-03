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
}
