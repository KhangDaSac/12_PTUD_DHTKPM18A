package BUS;

import DAO.NhanVien_DAO;
import DTO.LoaiNhanVien;
import DTO.NhanVien;
import DTO.TrangThaiNhanVien;

import java.util.ArrayList;

public class QuanLyNhanVien_BUS {

    public static ArrayList<NhanVien> getEmployees(String maNhanVien, String CCCD, String tenNhanVien, String diaChi, String soDienThoai, LoaiNhanVien loaiNhanVien, TrangThaiNhanVien trangThaiNhanVien) throws Exception {
        if (!maNhanVien.matches("NV\\d{2}\\d{4}")) {
            throw new Exception("Mã nhân viên không hợp lệ");
        } else if (!CCCD.matches("\\d{12}")) {
            throw new Exception("CCCD không hợp lệ");
        } else if (!tenNhanVien.matches("[\\p{L} ]{1,50}")) {
            throw new Exception("Tên nhân viên không hợp lệ");
        } else if (!diaChi.matches("[\\p{L}\\d,\\- ]{5,100}")) {
            throw new Exception("Địa chỉ không hợp lệ");
        } else if (!soDienThoai.matches("0\\d{9}")) {
            throw new Exception("Số điện thoại không hợp lệ");
        } else if (!(loaiNhanVien instanceof LoaiNhanVien)) {
            throw new Exception("Loại nhân viên không hợp lệ");
        } else if (trangThaiNhanVien == null || !(trangThaiNhanVien instanceof TrangThaiNhanVien)){
            throw new Exception("Trạng thái nhân viên không hợp lệ");
        }

        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
        ArrayList<NhanVien> nhanVienList = nhanVien_DAO.getEmployees(maNhanVien, CCCD, tenNhanVien, diaChi, soDienThoai, loaiNhanVien, trangThaiNhanVien);
        return nhanVienList;
    }


}
