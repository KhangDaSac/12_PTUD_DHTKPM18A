package BUS;

import DAO.NhanVien_DAO;
import DAO.TaiKhoan_DAO;
import DTO.NhanVien;
import utils.HashPassword;

public class QuanLyNhanVien_BUS {
    public static NhanVien dangNhap(String tenDangNhap, String maKhau) throws Exception {
        if(tenDangNhap == null || tenDangNhap.isEmpty()){
            throw new Exception("Tên đăng nhập không được rỗng");
        }

        if(maKhau == null || maKhau.isEmpty()){
            throw new Exception("Mật khẩu không được rỗng");
        }

        String maKhauBam = HashPassword.hashPassword(maKhau);

        return TaiKhoan_DAO.dangNhap(tenDangNhap, maKhauBam);
    }

    public static NhanVien getNhanVienTheoMaNhanVien(String maNhanVien) throws Exception {
        if(!maNhanVien.matches("NV\\d{10}")){
            throw new Exception("Mã nhân viên không hợp lệ");
        }
        return NhanVien_DAO.getNhanVienTheoMaNhanVien(maNhanVien);
    }
}
