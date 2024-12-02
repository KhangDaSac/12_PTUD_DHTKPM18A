package BUS;

import DAO.ChiTietVe_DAO;
import DAO.HoaDonBanVe_DAO;
import DAO.HoaDonDatVe_DAO;
import DAO.Ve_DAO;
import DTO.ChiTietVe;
import DTO.HoaDonBanVe;
import DTO.HoaDonDatVe;
import DTO.Ve;
import utils.TimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLyHoaDon_BUS {
    public static String layHoaDonTiepTheo(){
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

//    public static boolean layVe(ArrayList<Ve> danhSachVe, ArrayList<ChiTietVe> danhSachChiTietVe) throws Exception {
//        Ve_DAO ve_dao = new Ve_DAO();
//        ChiTietVe_DAO chiTietVe_dao = new ChiTietVe_DAO();
//
//
//        for(ChiTietVe chiTietVe : danhSachChiTietVe){
//            if(chiTietVe.getKhachHang() == null){
//                throw new Exception("Chưa nhập thông tin người người đi tàu");
//            }
//        }
//
//
//        if(!ve_dao.themDanhSachVe(danhSachVe)){
//            return false;
//        }
//
//        if(!ChiTietVe_DAO.themDanhSachChiTietVe(danhSachChiTietVe)){
//            return false;
//        }
//
//        return true;
//    }

    public static ArrayList<HoaDonDatVe> getDanhSachHoaDonDatTheoMaKhachHang(String maKhachHang){
        return HoaDonDatVe_DAO.getDanhSachHoaDonDatTheoMaKhachHang(maKhachHang);
    }


}
