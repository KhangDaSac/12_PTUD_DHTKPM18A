package BUS;

import DAO.KhachHang_DAO;
import DTO.KhachHang;

import java.util.ArrayList;

public class QuanLyKhachHang_BUS {
    KhachHang_DAO kH_DAO = new KhachHang_DAO();
    ArrayList <KhachHang> dsKH =kH_DAO.xuatDanhSachKhachHang();

    public ArrayList<KhachHang> timKhachHangTheosdt(String sdt){
        ArrayList<KhachHang> dsKH_theoDST = new ArrayList<>();
        for (KhachHang kh: dsKH){
            if (kh.getSoDienThoai().equals(sdt)){
                dsKH_theoDST.add(kh);
            }
        }
        return  dsKH_theoDST;
    }

}

