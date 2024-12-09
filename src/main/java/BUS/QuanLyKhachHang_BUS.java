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
        KhachHang_DAO khachHangDao = new KhachHang_DAO();
        KhachHang khachHang = khachHangDao.getKhachHangTheoCCCD(cccd);

        if(khachHang == null)
            throw new Exception("Không tìm thấy khách hàng");

        return khachHang;
    }
    public ArrayList<KhachHang> timKhachHangTheosdt(String sdt){
        KhachHang_DAO kH_DAO = new KhachHang_DAO();
        ArrayList <KhachHang> dsKH =kH_DAO.xuatDanhSachKhachHang();
        ArrayList<KhachHang> dsKH_theoDST = new ArrayList<>();
        for (KhachHang kh: dsKH){
            if (kh.getSoDienThoai().equals(sdt)){
                dsKH_theoDST.add(kh);
            }
        }
        return  dsKH_theoDST;
    }

}
