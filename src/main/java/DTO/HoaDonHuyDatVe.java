package DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDonHuyDatVe {
    private String maHoaDonHuyDatVe;
    private LocalDateTime thoiGianHuy;
    private KhachHang khachHangHuyDatVe;
    private CaLamViec caLamViec;

    private ArrayList<ChiTietHoaDonHuyDatVe> danhSachChiTietHoaDonHuyDatVe;

    public String getMaHoaDonHuyDatVe() {
        return maHoaDonHuyDatVe;
    }

    public void setMaHoaDonHuyDatVe(String maHoaDonHuyDatVe) {
        this.maHoaDonHuyDatVe = maHoaDonHuyDatVe;
    }

    public LocalDateTime getThoiGianHuy() {
        return thoiGianHuy;
    }

    public void setThoiGianHuy(LocalDateTime thoiGianHuy) {
        this.thoiGianHuy = thoiGianHuy;
    }

    public KhachHang getKhachHangHuyDatVe() {
        return khachHangHuyDatVe;
    }

    public void setKhachHangHuyDatVe(KhachHang khachHangHuyDatVe) {
        this.khachHangHuyDatVe = khachHangHuyDatVe;
    }

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }

    public ArrayList<ChiTietHoaDonHuyDatVe> getDanhSachChiTietHoaDonHuyDatVe() {
        return danhSachChiTietHoaDonHuyDatVe;
    }

    public void setDanhSachChiTietHoaDonHuyDatVe(ArrayList<ChiTietHoaDonHuyDatVe> danhSachChiTietHoaDonHuyDatVe) {
        this.danhSachChiTietHoaDonHuyDatVe = danhSachChiTietHoaDonHuyDatVe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoaDonHuyDatVe that = (HoaDonHuyDatVe) o;
        return Objects.equals(maHoaDonHuyDatVe, that.maHoaDonHuyDatVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHoaDonHuyDatVe);
    }

    public double tongTienCuoi(){
        double tongTienCuoi = 0;
        for(ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe : danhSachChiTietHoaDonHuyDatVe){
            tongTienCuoi += chiTietHoaDonHuyDatVe.soTienHoanLai();
        }
        return tongTienCuoi;
    }

    public HoaDonHuyDatVe(String maHoaDonHuyDatVe) {
        this.maHoaDonHuyDatVe = maHoaDonHuyDatVe;
    }
}
