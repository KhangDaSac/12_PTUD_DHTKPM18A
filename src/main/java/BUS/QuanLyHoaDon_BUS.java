package BUS;

import DAO.ChiTietVe_DAO;
import DAO.HoaDon_DAO;
import DAO.Ve_DAO;
import DTO.ChiTietVe;
import DTO.HoaDon;
import DTO.Ve;
import utils.TimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLyHoaDon_BUS {
    public static String layHoaDonTiepTheo(){
        HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
        String maHoaDonMoi = null;
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String maHoaDonCu = hoaDon_dao.layMaHoaDonLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(maHoaDonCu == null){
            return "HD" + ngayHienTaiString + "000001";
        }

        String phanTruoc = maHoaDonCu.substring(0, maHoaDonCu.length() - 6);
        String phanSau = maHoaDonCu.substring(maHoaDonCu.length() - 6);

        maHoaDonMoi = phanTruoc + String.format( "%06d", Integer.parseInt(phanSau) + 1);

        return maHoaDonMoi;

    }

    public static boolean themHoaDon(HoaDon hoaDon, ArrayList<Ve> danhSachVe, ArrayList<ChiTietVe> danhSachChiTietVe) throws Exception {
        HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
        Ve_DAO ve_dao = new Ve_DAO();
        ChiTietVe_DAO chiTietVe_dao = new ChiTietVe_DAO();

        if(hoaDon.getKhachHangMua() == null)
            throw new Exception("Chưa nhập thông tin người mua");

        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            if(chiTietVe.getKhachHang() == null){
                throw new Exception("Chưa nhập thông tin người người đi tàu");
            }
        }

        if(!hoaDon_dao.themHoaDon(hoaDon)){
            return false;
        }

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

    public static ArrayList<HoaDon> getHoaDonTheoMaKhachHangVaThoiGianLap(String maKhachHang, LocalDate thoiGianLap) {
        return HoaDon_DAO.getHoaDonTheoMaKhachHangVaThoiGianLap(maKhachHang, thoiGianLap);
    }
    public static ArrayList<HoaDon> getDanhSachHoaDon(){
        return HoaDon_DAO.getDanhSachHoaDon();
    }
    public static ArrayList<HoaDon> getDanhSachHoaDonDatTheoMaKhachHang(String maKhachHang){
        HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
        return hoaDon_dao.getDanhSachHoaDonDatTheoMaKhachHang(maKhachHang);
    }
    public static ArrayList<HoaDon> getDanhSachHoaDonDat(){
        HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
        return hoaDon_dao.getDanhSachHoaDonDat();
    }
}
