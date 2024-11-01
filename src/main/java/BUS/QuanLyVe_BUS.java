package BUS;

import DAO.ChuyenTau_DAO;
import DAO.Ve_DAO;
import DTO.ChuyenTau;
import DTO.Ve;
import utils.TimeFormat;

import java.time.LocalDate;

public class QuanLyVe_BUS {
    public static String taoMaVeMoi(){
        Ve_DAO ve_DAO = new Ve_DAO();
        String maVeMoi = null;
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateTimeNumber(ngayHienTai);

        String maVeCu = ve_DAO.layMaVeLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(maVeCu == null){
            return "V" + ngayHienTaiString + "0000000";
        }

        String phanTruoc = maVeCu.substring(0, maVeCu.length() - 6);
        String phanSau = maVeCu.substring(maVeCu.length() - 6);

        maVeMoi = phanTruoc + String.valueOf(Integer.parseInt(phanSau) + 1);

        return maVeMoi;
    }

    public static String taoMaVeTiepTheo(Ve ve){
        String maVeCu = ve.getMaVe();
        String phanTruoc = maVeCu.substring(0, maVeCu.length() - 6);
        String phanSau = maVeCu.substring(maVeCu.length() - 6);
        String maVeMoi = phanTruoc + String.valueOf(Integer.parseInt(phanSau) + 1);
        return maVeMoi;
    }
    public static Ve getVeTheoMa(String maVe){
        Ve_DAO veDao = new Ve_DAO();
        Ve ve =veDao.getVeTheoMa(maVe);
        return ve;
    }
    public static ChuyenTau getChuyenTauTheoMa(String maCT){
        ChuyenTau_DAO chuyenTauDao= new ChuyenTau_DAO();
        ChuyenTau ct = chuyenTauDao.timChuyenTauTheoMa(maCT);
        return ct;
    }


}
