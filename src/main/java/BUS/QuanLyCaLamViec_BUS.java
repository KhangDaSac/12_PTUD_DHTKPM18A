package BUS;

import DAO.CaLamViec_DAO;
import DTO.CaLamViec;
import utils.TimeFormat;

import java.time.LocalDate;

public class QuanLyCaLamViec_BUS {
    public static String taoMaCaLamViecMoi(){
        LocalDate ngayHienTai = LocalDate.now();
        String ngayHienTaiString = TimeFormat.formatLocalDateNumber(ngayHienTai);

        String phanSau = CaLamViec_DAO.layDuoiMaCaLamViecLonNhatCuaNgayHienTai(ngayHienTaiString);
        if(phanSau == null){
            return "CLV"  + ngayHienTaiString + "C01";
        }

        String phanTruoc = "CLV"  + ngayHienTaiString + "C";
        String maCaLamViecMoi = phanTruoc + String.format("%02d", Integer.parseInt(phanSau) + 1);

        return maCaLamViecMoi;
    }

    public static boolean themCaLamViec(CaLamViec caLamViec){
        return CaLamViec_DAO.themCaLamViec(caLamViec);
    }
}
