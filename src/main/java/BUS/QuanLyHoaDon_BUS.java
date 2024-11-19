package BUS;

import DAO.ChiTietVe_DAO;
import DAO.HoaDonBanVe_DAO;
import DAO.Ve_DAO;
import DTO.ChiTietVe;
import DTO.HoaDonBanVe;
import DTO.Ve;
import utils.TimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLyHoaDon_BUS {
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



}
