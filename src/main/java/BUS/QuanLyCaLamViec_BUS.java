package BUS;

import DAO.CaLamViec_DAO;
import DAO.ChiTietPhieuKiemTien_DAO;
import DAO.PhieuKetToan_DAO;
import DAO.PhieuKiemTien_DAO;
import DTO.CaLamViec;
import DTO.PhieuKetToan;
import DTO.PhieuKiemTien;
import utils.TimeFormat;

import java.time.LocalDate;

public class QuanLyCaLamViec_BUS {
    public static String taoMaCaLamViecMoi(){
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String phanSau = CaLamViec_DAO.layDuoiMaCaLamViecLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(phanSau == null){
            return "CLV"  + ngayHienTaiString + "C001";
        }

        String phanTruoc = "CLV"  + ngayHienTaiString + "C";
        String maCaLamViecMoi = phanTruoc + String.format("%03d", Integer.parseInt(phanSau) + 1);

        return maCaLamViecMoi;
    }

    public static boolean taoCaLamViecMoi(PhieuKetToan phieuKetToan){
        if(!CaLamViec_DAO.themCaLamViec(phieuKetToan.getCaLamViec()))
            return false;

        if(!PhieuKiemTien_DAO.themPhieuKiemTien(phieuKetToan.getPhieuKiemTienDauCa()))
            return false;

        if(!ChiTietPhieuKiemTien_DAO.themDanhSachChiTietPhieuKiemTien(phieuKetToan.getPhieuKiemTienDauCa().getDanhSachChiTietPhieuKiemTien()))
            return false;

        if(!PhieuKetToan_DAO.themPhieuKetToan(phieuKetToan))
            return false;

        return true;
    }
}
