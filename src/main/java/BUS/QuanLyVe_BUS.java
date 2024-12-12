package BUS;

import DAO.ChiTietVe_DAO;
import DAO.Ve_DAO;
import DTO.ChiTietVe;
import DTO.ChuyenTau;
import DTO.LoaiVe;
import DTO.Ve;
import utils.TimeFormat;

import java.time.LocalDate;

public class QuanLyVe_BUS {
    public static String taoMaVeMoi(LoaiVe loaiVe){
        String loaiVeString = loaiVe.equals(LoaiVe.VECANHAN) ? "CN" : "TT";
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String phanSau = Ve_DAO.layDuoiMaVeLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(phanSau == null){
            return "VE" + loaiVeString + ngayHienTaiString + "00000001";
        }

        String phanTruoc = "VE" + loaiVeString + ngayHienTaiString;

        String maVeMoi = phanTruoc + String.format("%08d", Integer.parseInt(phanSau) + 1);

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


    public static Ve getVeTheoMa(String maVe){
        Ve_DAO veDao = new Ve_DAO();
        Ve ve =veDao.getVeTheoMa(maVe);
        return ve;
    }
    public static void doiVe(String maVe,String maGheCu,String maGheMoi, double giaCho){
        ChiTietVe_DAO chiTietVeDao= new ChiTietVe_DAO();
        chiTietVeDao.doiChoTuMaVeMaGheCuMaGheMoi(maVe,maGheCu, maGheMoi,giaCho);
        System.out.printf("doi ve thanh cong");
    }
    public static ChiTietVe getCTVeTheoMaVe(String maVe){
        ChiTietVe_DAO chiTietVeDao= new ChiTietVe_DAO();
        ChiTietVe chiTietVe = chiTietVeDao.getCTVeTheoMaVe(maVe);
        return  chiTietVe;
    }

    public static String layMaVeCaNhanLonNhatCuaNgayHienTai(String ngay){
        return Ve_DAO.layMaVeCaNhanLonNhatCuaNgayHienTai(ngay);
    }

    public static boolean themVeMoi(Ve ve,String maChuyenTau){
        if (Ve_DAO.themVeMoi(ve,maChuyenTau)){
            System.out.printf("thêm thành công!");
        }else {
            System.out.printf("thêm vé không thành công");
            return false;
        }
        return true;
    }

    public static boolean themChiTietVeMoi(ChiTietVe ctVe){
        ChiTietVe_DAO.themChiTietVeMoi(ctVe);
        return true;
    }

    public static boolean capNhatTrangThaiHuyChoVeDoi(String maVe){
        Ve_DAO.capNhatTrangThaiHuyChoVeDoi(maVe);
        return true;
    }

}
