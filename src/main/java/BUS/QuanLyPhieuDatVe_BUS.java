package BUS;

import DAO.PhieuDatVe_DAO;
import DTO.PhieuDatVe;

import java.util.ArrayList;

public class QuanLyPhieuDatVe_BUS {
    public static ArrayList<PhieuDatVe> getDanhSachPhieuDatVeTheoMaHoaDon(String maHoaDon){
        return PhieuDatVe_DAO.getDanhSachPhieuDatVeTheoMaHoaDon(maHoaDon);
    }
}
