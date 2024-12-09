package BUS;

import DAO.VeDat_DAO;
import DTO.LoaiVe;
import DTO.VeDat;
import utils.TimeFormat;

import java.time.LocalDate;

public class QuanLyVeDat_BUS {
    public static String taoMaVeDatMoi(LoaiVe loaiVe){
        String loaiVeString = loaiVe.equals(LoaiVe.VECANHAN) ? "CN" : "TT";
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String phanSau = VeDat_DAO.layDuoiMaVeDatLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(phanSau == null){
            return "VD" + loaiVeString + ngayHienTaiString + "00000001";
        }

        String phanTruoc = "VD" + loaiVeString + ngayHienTaiString;

        String maVeDatMoi = phanTruoc + String.format("%08d", Integer.parseInt(phanSau) + 1);

        return maVeDatMoi;
    }

    public static String taoMaVeDatTiepTheo(VeDat veDat, LoaiVe loaiVe){
        String maVeDatCu = veDat.getMaVeDat();
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);
        String loaiVeString = loaiVe.equals(LoaiVe.VECANHAN) ? "CN" : "TT";
        String phanTruoc = "VD" + loaiVeString + ngayHienTaiString;
        String phanSau = maVeDatCu.substring(maVeDatCu.length() - 8);
        String maVeDatMoi = phanTruoc + String.format("%08d", Integer.parseInt(phanSau) + 1);
        return maVeDatMoi;
    }
}
