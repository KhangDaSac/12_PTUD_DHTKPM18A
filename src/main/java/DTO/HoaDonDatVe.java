package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonDatVe {
    private String maHoaDonDatVe;
    private LocalDateTime thoiGianLap;
    private CaLamViec caLamViec;
    private double tongTien;
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

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
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

    public HoaDonDatVe(String maHoaDonDatVe, LocalDateTime thoiGianLap, CaLamViec caLamViec, double tongTien, double tongTienDatCoc) {
        this.maHoaDonDatVe = maHoaDonDatVe;
        this.thoiGianLap = thoiGianLap;
        this.caLamViec = caLamViec;
        this.tongTien = tongTien;
        this.tongTienDatCoc = tongTienDatCoc;
    }

    public HoaDonDatVe(String maHoaDonDatVe) {
        this.maHoaDonDatVe = maHoaDonDatVe;
    }
}
