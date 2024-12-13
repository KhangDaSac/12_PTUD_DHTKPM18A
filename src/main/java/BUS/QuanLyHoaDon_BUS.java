package BUS;

import DAO.*;
import DTO.*;
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

    public static boolean themHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe) throws Exception {
        HoaDonHuyVe_DAO.themHoaDonHuyVe(hoaDonHuyVe);
        return true;

    }

    public static String layMaHoaDonHuyLonNhatCuaNgayHienTai(String ngay){
        return HoaDonHuyVe_DAO.layMaHoaDonHuyLonNhatCuaNgayHienTai(ngay);
    }

    public static String layHoaDonHuyVeTiepTheo(){
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String maHoaDonCu = HoaDonHuyVe_DAO_Test.layMaHoaDonHuyVeLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(maHoaDonCu == null){
            return "HDHV" + ngayHienTaiString + "000001";
        }

        String phanTruoc = maHoaDonCu.substring(0, maHoaDonCu.length() - 6);
        String phanSau = maHoaDonCu.substring(maHoaDonCu.length() - 6);

        String maHoaDonMoi = phanTruoc + String.format( "%06d", Integer.parseInt(phanSau) + 1);

        return maHoaDonMoi;
    }

}
