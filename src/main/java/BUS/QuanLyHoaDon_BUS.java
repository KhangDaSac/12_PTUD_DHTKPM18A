package BUS;

import DAO.HoaDon_DAO;
import DAO.Ve_DAO;
import DTO.Ve;
import utils.TimeFormat;

import java.time.LocalDate;

public class QuanLyHoaDon_BUS {
    public static String layHoaDonTiepTheo(){
        HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
        String maHoaDonMoi = null;
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateTimeNumber(ngayHienTai);

        String maHoaDonCu = hoaDon_dao.layMaHoaDonLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(maHoaDonCu == null){
            return "HD" + ngayHienTaiString + "0000000";
        }

        String phanTruoc = maHoaDonCu.substring(0, maHoaDonCu.length() - 6);
        String phanSau = maHoaDonCu.substring(maHoaDonCu.length() - 6);

        maHoaDonMoi = phanTruoc + String.valueOf(Integer.parseInt(phanSau) + 1);

        return maHoaDonMoi;

    }
}
