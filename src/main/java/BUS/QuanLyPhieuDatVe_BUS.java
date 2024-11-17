package BUS;

import DAO.ChiTietPhieuDatVe_DAO;
import DAO.PhieuDatVe_DAO;
import DTO.ChiTietVeDat;
import DTO.VeDat;

import java.util.ArrayList;

public class QuanLyPhieuDatVe_BUS {
    public static ArrayList<VeDat> getDanhSachPhieuDatVeTheoMaHoaDon(String maHoaDon){
        return PhieuDatVe_DAO.getDanhSachPhieuDatVeTheoMaHoaDon(maHoaDon);
    }

    public static ArrayList<ChiTietVeDat> getDanhSachChiTietPhieuDatVeTheoMaHoaDon(String maHD){
        return ChiTietPhieuDatVe_DAO.getDanhSachChiTietPhieuDatVeTheoMaHoaDon(maHD);
    }

    public static boolean capNhatTrangThaiPhieuDatVe(ArrayList<VeDat> danhSachPhieuDatVe, String trangThai){
        for(VeDat phieuDatVe : danhSachPhieuDatVe){
            if(!PhieuDatVe_DAO.capNhatTrangThaiPhieuDatVe(phieuDatVe.getMaPhieuDatVe(), trangThai)){
                return false;
            }
        }
        return true;
    }
    public static ArrayList<ChiTietVeDat> getDanhSachChiTietPhieuDatVeTheoMaPhieuDatVe(String maPDV){
        return ChiTietPhieuDatVe_DAO.getDanhSachChiTietPhieuDatVeTheoMaPhieuDatVe(maPDV);
    }
    public static void huyPhieuDatVe(String maPhieuDatVe){
        PhieuDatVe_DAO.huyPhieuDatVe(maPhieuDatVe);
    }
}
