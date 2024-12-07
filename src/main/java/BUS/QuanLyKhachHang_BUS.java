package BUS;

import DAO.KhachHang_DAO;
import DTO.KhachHang;

import DAO.KhachHang_DAO;
import DTO.KhachHang;

import java.util.ArrayList;

public class QuanLyKhachHang_BUS {
    public static KhachHang getKhachHangTheoCCCD(String cccd) throws Exception {
        if(!cccd.matches("\\d{12}")){
            throw new Exception("Số CCCD không hợp lệ");
        }
        KhachHang khachHang = KhachHang_DAO.getKhachHangTheoCCCD(cccd);

        if(khachHang == null)
            throw new Exception("Không tìm thấy khách hàng");

        return khachHang;
    }

}
