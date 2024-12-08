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

    public static boolean doiMatKhau(String maNhanVien, String matKhauMoi, String matKhauMoiNhapLai) throws Exception {
        if (!matKhauMoi.equals(matKhauMoiNhapLai)) {
            throw new Exception("Mật khẩu nhập lại không trùng khớp");
        }

        if (matKhauMoi.length() < 8) {
            throw new Exception("Mật khẩu mới phải dài từ 8 ký tự");
        }

        if (!matKhauMoi.matches(".*[A-Z].*")) {
            throw new Exception("Mật khẩu phải chứa ít nhất một ký tự in hoa");
        }

        if (!matKhauMoi.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            throw new Exception("Mật khẩu phải chứa ít nhất một ký tự đặc biệt");
        }

        if (!matKhauMoi.matches(".*\\d.*")) {
            throw new Exception("Mật khẩu phải chứa ít nhất một ký tự số");
        }

        String maKhauMoiBam = HashPassword.hashPassword(matKhauMoi);
        return TaiKhoan_DAO.doiMatKhau(maNhanVien, maKhauMoiBam);
    }
}
