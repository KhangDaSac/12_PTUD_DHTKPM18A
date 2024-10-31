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

    public static void themHoaDon(HoaDon hoaDon, ArrayList<Ve> danhSachVe, ArrayList<ChiTietVe> danhSachChiTietVe){
        HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
        Ve_DAO ve_dao = new Ve_DAO();
        ChiTietVe_DAO chiTietVe_dao = new ChiTietVe_DAO();


        hoaDon_dao.themHoaDon(hoaDon);
        ve_dao.themDanhSachVe(danhSachVe);
        chiTietVe_dao.themDanhSachChiTietVe(danhSachChiTietVe);


    }
}
