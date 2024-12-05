package BUS;

import DAO.*;
import DTO.ChiTietVe;
import DTO.HoaDonBanVe;
import DTO.HoaDonDatVe;
import DTO.Ve;
import utils.TimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLyHoaDon_BUS {
    public static String layHoaDonBanVeTiepTheo(){
        String maHoaDonMoi = null;
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String maHoaDonCu = HoaDonBanVe_DAO.layMaHoaDonBanVeLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(maHoaDonCu == null){
            return "HDBV" + ngayHienTaiString + "000001";
        }

        String phanTruoc = maHoaDonCu.substring(0, maHoaDonCu.length() - 6);
        String phanSau = maHoaDonCu.substring(maHoaDonCu.length() - 6);

        maHoaDonMoi = phanTruoc + String.format( "%06d", Integer.parseInt(phanSau) + 1);

        return maHoaDonMoi;

    }

    public static boolean themHoaDon(HoaDonBanVe hoaDonBanVe) throws Exception {
        if (hoaDonBanVe.getKhachHangMuaVe() == null)
            throw new Exception("Chưa nhập thông tin người mua");

        for (Ve ve : hoaDonBanVe.getDanhSachVe()) {
            for (ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()) {
                if (chiTietVe.getKhachHang() == null) {
                    throw new Exception("Chưa nhập thông tin người người đi tàu");
                }
            }
        }

        System.out.println(hoaDonBanVe.toString());

        if (!HoaDonBanVe_DAO.themHoaDonBanVe(hoaDonBanVe)) {
            return false;
        }

        if (!Ve_DAO.themDanhSachVe(hoaDonBanVe.getDanhSachVe())) {
            return false;
        }

        for (Ve ve : hoaDonBanVe.getDanhSachVe()) {
            if (!ChiTietVe_DAO.themDanhSachChiTietVe(ve.getDanhSachChiTietVe())) {
                return false;
            }
        }

        return true;
    }

    public static ArrayList<HoaDonDatVe> getDanhSachHoaDonDatTheoMaKhachHang(String maKhachHang){
        return HoaDonDatVe_DAO.getDanhSachHoaDonDatTheoMaKhachHang(maKhachHang);
    }

    public static String layHoaDonLayVeTiepTheo(){
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String maHoaDonCu = HoaDonLayVe_DAO.layMaHoaDonLayVeLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(maHoaDonCu == null){
            return "HDLV" + ngayHienTaiString + "000001";
        }

        String phanTruoc = maHoaDonCu.substring(0, maHoaDonCu.length() - 6);
        String phanSau = maHoaDonCu.substring(maHoaDonCu.length() - 6);

        String maHoaDonMoi = phanTruoc + String.format( "%06d", Integer.parseInt(phanSau) + 1);

        return maHoaDonMoi;

    }


}
