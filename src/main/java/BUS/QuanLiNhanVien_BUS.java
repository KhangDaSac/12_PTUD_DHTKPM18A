package BUS;

import DAO.TaiKhoan_DAO;
import DTO.NhanVien;
import utils.HashPassword;

public class QuanLiNhanVien_BUS {
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
}
