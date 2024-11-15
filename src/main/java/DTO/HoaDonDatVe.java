package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonDatVe {
    private String maHoaDonDatVe;
    private LocalDateTime thoiGianLap;
    private KhachHang khachHangDatVe;
    private CaLamViec caLamViec;
    private double tongTienCuoi;
    private double tongTienDatCoc;

    public String getMaHoaDonDatVe() {
        return maHoaDonDatVe;
    }

    public void setMaHoaDonDatVe(String maHoaDonDatVe) {
        this.maHoaDonDatVe = maHoaDonDatVe;
    }

    public LocalDateTime getThoiGianLap() {
        return thoiGianLap;
    }

    public void setThoiGianLap(LocalDateTime thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public KhachHang getKhachHangDatVe() {
        return khachHangDatVe;
    }

    public void setKhachHangDatVe(KhachHang khachHangDatVe) {
        this.khachHangDatVe = khachHangDatVe;
    }

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }

    public double getTongTienCuoi() {
        return tongTienCuoi;
    }

    public void setTongTienCuoi(double tongTienCuoi) {
        this.tongTienCuoi = tongTienCuoi;
    }

    public double getTongTienDatCoc() {
        return tongTienDatCoc;
    }

    public void setTongTienDatCoc(double tongTienDatCoc) {
        this.tongTienDatCoc = tongTienDatCoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoaDonDatVe that = (HoaDonDatVe) o;
        return Objects.equals(maHoaDonDatVe, that.maHoaDonDatVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHoaDonDatVe);
    }

    public HoaDonDatVe(String maHoaDonDatVe) {
        this.maHoaDonDatVe = maHoaDonDatVe;
    }

}
