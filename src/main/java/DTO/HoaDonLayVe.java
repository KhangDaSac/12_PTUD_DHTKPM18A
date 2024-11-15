package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonLayVe {
    private String maHoaDonLayVe;
    private LocalDateTime thoiGianLayVe;
    private KhachHang khachHangLayVe;
    private double tongTienCuoi;
    private CaLamViec caLamViec;

    public String getMaHoaDonLayVe() {
        return maHoaDonLayVe;
    }

    public void setMaHoaDonLayVe(String maHoaDonLayVe) {
        this.maHoaDonLayVe = maHoaDonLayVe;
    }

    public LocalDateTime getThoiGianLayVe() {
        return thoiGianLayVe;
    }

    public void setThoiGianLayVe(LocalDateTime thoiGianLayVe) {
        this.thoiGianLayVe = thoiGianLayVe;
    }

    public KhachHang getKhachHangLayVe() {
        return khachHangLayVe;
    }

    public void setKhachHangLayVe(KhachHang khachHangLayVe) {
        this.khachHangLayVe = khachHangLayVe;
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
        HoaDonLayVe that = (HoaDonLayVe) o;
        return Objects.equals(maHoaDonLayVe, that.maHoaDonLayVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHoaDonLayVe);
    }

    public HoaDonLayVe(String maHoaDonLayVe) {
        this.maHoaDonLayVe = maHoaDonLayVe;
    }
}
