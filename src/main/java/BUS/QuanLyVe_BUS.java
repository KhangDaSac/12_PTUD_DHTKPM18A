package BUS;

import DAO.Ve_DAO;
import DTO.Ve;
import utils.TimeFormat;

import java.time.LocalDate;

public class QuanLyVe_BUS {
    public static String taoMaVeMoi(){
        Ve_DAO ve_DAO = new Ve_DAO();
        String maVeMoi = null;
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String maVeCu = ve_DAO.layMaVeLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(maVeCu == null){
            return "V" + ngayHienTaiString + "000001";
        }

        String phanTruoc = maVeCu.substring(0, maVeCu.length() - 6);
        String phanSau = maVeCu.substring(maVeCu.length() - 6);

        maVeMoi = phanTruoc + String.format("%06d", Integer.parseInt(phanSau) + 1);

        return maVeMoi;
    }

    public static String taoMaVeTiepTheo(Ve ve){
        String maVeCu = ve.getMaVe();
        String phanTruoc = maVeCu.substring(0, maVeCu.length() - 6);
        String phanSau = maVeCu.substring(maVeCu.length() - 6);
        String maVeMoi = phanTruoc + String.format("%06d", Integer.parseInt(phanSau) + 1);
        return maVeMoi;
    }
}
