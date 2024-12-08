package BUS;

import DAO.Ve_DAO;
import DTO.HoaDonHuyVe;
import DTO.LoaiVe;
import DTO.TrangThaiVe;
import DTO.Ve;
import utils.TimeFormat;

import java.time.LocalDate;

public class QuanLyVe_BUS {
    public static String taoMaVeMoi(LoaiVe loaiVe){
        String maVeMoi = null;
        String loaiVeString = loaiVe.equals(LoaiVe.VECANHAN) ? "CN" : "TT";
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String phanSau = Ve_DAO.layDuoiMaVeLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(phanSau == null){
            return "VE" + loaiVeString + ngayHienTaiString + "00000001";
        }

        String phanTruoc = "VE" + loaiVeString + ngayHienTaiString;

        maVeMoi = phanTruoc + String.format("%08d", Integer.parseInt(phanSau) + 1);

        return maVeMoi;
    }

    public static String taoMaVeTiepTheo(Ve veCu, LoaiVe loaiVe){
        String maVeCu = veCu.getMaVe();
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);
        String loaiVeString = loaiVe.equals(LoaiVe.VECANHAN) ? "CN" : "TT";
        String phanTruoc = "VE" + loaiVeString + ngayHienTaiString;
        String phanSau = maVeCu.substring(maVeCu.length() - 8);
        String maVeMoi = phanTruoc + String.format("%08d", Integer.parseInt(phanSau) + 1);
        return maVeMoi;
    }

    public static boolean capNhatTrangThaiVe(String maVe, TrangThaiVe trangThai){
        Ve_DAO.capNhatTrangThaiVe(maVe, trangThai);return true;
    }
}
