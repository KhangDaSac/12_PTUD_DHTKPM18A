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

    public static boolean capNhatTrangThaiPhieuDatVe(ArrayList<PhieuDatVe> danhSachPhieuDatVe, String trangThai){
        for(PhieuDatVe phieuDatVe : danhSachPhieuDatVe){
            if(!PhieuDatVe_DAO.capNhatTrangThaiPhieuDatVe(phieuDatVe.getMaPhieuDatVe(), trangThai)){
                return false;
            }
        }
        return true;
    }
}
