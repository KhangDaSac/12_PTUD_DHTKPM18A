package BUS;

import DAO.*;
import DTO.*;
import utils.TimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLyHoaDon_BUS {
    private static QuanLyHoaDon_BUS HoaDonLayVe_DAO;

    public static String layHoaDonTiepTheo(){
        HoaDonBanVe_DAO hoaDon_dao = new HoaDonBanVe_DAO();
        String maHoaDonMoi = null;
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String maHoaDonCu = hoaDon_dao.layMaHoaDonBanVeLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(maHoaDonCu == null){
            return "HDBV" + ngayHienTaiString + "000001";
        }

        String phanTruoc = maHoaDonCu.substring(0, maHoaDonCu.length() - 6);
        String phanSau = maHoaDonCu.substring(maHoaDonCu.length() - 6);

        maHoaDonMoi = phanTruoc + String.format( "%06d", Integer.parseInt(phanSau) + 1);

        return maHoaDonMoi;

    }

    public static boolean themHoaDon(HoaDonBanVe hoaDon, ArrayList<Ve> danhSachVe, ArrayList<ChiTietVe> danhSachChiTietVe) throws Exception {
        HoaDonBanVe_DAO hoaDon_dao = new HoaDonBanVe_DAO();
        Ve_DAO ve_dao = new Ve_DAO();
        ChiTietVe_DAO chiTietVe_dao = new ChiTietVe_DAO();

        if(hoaDon.getKhachHangMuaVe() == null)
            throw new Exception("Chưa nhập thông tin người mua");

        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            if(chiTietVe.getKhachHang() == null){
                throw new Exception("Chưa nhập thông tin người người đi tàu");
            }
        }

//        if(!hoaDon_dao.themHoaDon(hoaDon)){
//            return false;
//        }

        if(!ve_dao.themDanhSachVe(danhSachVe)){
            return false;
        }

        if(!chiTietVe_dao.themDanhSachChiTietVe(danhSachChiTietVe)){
            return false;
        }

        return true;
    }

    public static boolean layVe(ArrayList<Ve> danhSachVe, ArrayList<ChiTietVe> danhSachChiTietVe) throws Exception {
        Ve_DAO ve_dao = new Ve_DAO();
        ChiTietVe_DAO chiTietVe_dao = new ChiTietVe_DAO();


        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            if(chiTietVe.getKhachHang() == null){
                throw new Exception("Chưa nhập thông tin người người đi tàu");
            }
        }


        if(!ve_dao.themDanhSachVe(danhSachVe)){
            return false;
        }

        if(!chiTietVe_dao.themDanhSachChiTietVe(danhSachChiTietVe)){
            return false;
        }

        return true;
    }

//    public static String layHoaDonLayVeTiepTheo(){
//        LocalDate ngayHienTai = LocalDate.now();
//        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);
//
//        String maHoaDonCu = DAO.HoaDonLayVe_DAO.layMaHoaDonLayVeLonNhatCuaNgayHienTai(ngayHienTaiString);
//        if(maHoaDonCu == null){
//            return "HDLV" + ngayHienTaiString + "000001";
//        }
//
//        String phanTruoc = maHoaDonCu.substring(0, maHoaDonCu.length() - 6);
//        String phanSau = maHoaDonCu.substring(maHoaDonCu.length() - 6);
//
//        String maHoaDonMoi = phanTruoc + String.format( "%06d", Integer.parseInt(phanSau) + 1);
//
//        return maHoaDonMoi;
//    }
//
//    public static boolean themHoaDonLayVe(HoaDonLayVe hoaDonLayVe){
//        ArrayList<Ve> danhSachVe = new ArrayList<Ve>();
//        ArrayList<VeDat> danhSachVeDat = new ArrayList<VeDat>();
//        for (ChiTietHoaDonLayVe chiTietHoaDonLayVe : hoaDonLayVe.getDanhSachChiTietHoaDonLayVe()){
//            danhSachVe.add(chiTietHoaDonLayVe.getVe());
//            danhSachVeDat.add(chiTietHoaDonLayVe.getVeDat());
//        }
//
//        if(!HoaDonLayVe_DAO.themHoaDonLayVe(hoaDonLayVe)){
//            return false;
//        }
//
//        if(!Ve_DAO.themDanhSachVe(danhSachVe)){
//            return false;
//        }
//
//        for(Ve ve : danhSachVe){
//            if(!ChiTietVe_DAO.themDanhSachChiTietVe(ve.getDanhSachChiTietVe())){
//                return false;
//            }
//        }
//
//        if(!ChiTietHoaDonLayVe_DAO.themDanhSachChiTietHoaDonLayVe(hoaDonLayVe.getDanhSachChiTietHoaDonLayVe())){
//            return false;
//        }
//
//        if(!VeDat_DAO.capNhatTrangThaiVeDat(danhSachVeDat)){
//            return false;
//        }
//
//        return true;
//    }
    public static String layHoaDonHuyDatVeTiepTheo(){
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String maHoaDonCu = HoaDonHuyDatVe_DAO.layMaHoaDonHuyDatVeLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(maHoaDonCu == null){
            return "HDHD" + ngayHienTaiString + "000001";
        }

        String phanTruoc = maHoaDonCu.substring(0, maHoaDonCu.length() - 6);
        String phanSau = maHoaDonCu.substring(maHoaDonCu.length() - 6);

        String maHoaDonMoi = phanTruoc + String.format( "%06d", Integer.parseInt(phanSau) + 1);

        return maHoaDonMoi;
    }
    public static boolean themHoaDonHuyDatVe(HoaDonHuyDatVe hoaDonHuyDatVe){
        ArrayList<VeDat> danhSachVeDat = new ArrayList<VeDat>();
        for (ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe : hoaDonHuyDatVe.getDanhSachChiTietHoaDonHuyDatVe()){
            danhSachVeDat.add(chiTietHoaDonHuyDatVe.getVeDat());
        }

        if(!HoaDonHuyDatVe_DAO.themHoaDonHuyDatVe(hoaDonHuyDatVe)){
            return false;
        }

        if(!VeDat_DAO.capNhatTrangThaiVeDatHuyVe(danhSachVeDat)){
            return false;
        }
        return true;
    }


}
