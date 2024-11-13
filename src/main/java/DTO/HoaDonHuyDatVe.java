package DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonHuyDatVe {
    private String maHoaDonHuyDatVe;
    private LocalDateTime thoiGianHuy;
    private KhachHang khachHangHuyDatVe;
    private double tongTienCuoi;
    private CaLamViec caLamViec;

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

    public double getTongTienCuoi() {
        return tongTienCuoi;
    }

    public void setTongTienCuoi(double tongTienCuoi) {
        this.tongTienCuoi = tongTienCuoi;
    }

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
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

}
