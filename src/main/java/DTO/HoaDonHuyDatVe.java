package DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonHuyDatVe {
    private String maHoaDonHuyDatVe;
    private LocalDateTime thoiGianHuy;
    private double lePhi;
    private HoaDonDatVe hoaDonDatVe;
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

    public double getLePhi() {
        return lePhi;
    }

    public void setLePhi(double lePhi) {
        this.lePhi = lePhi;
    }

    public HoaDonDatVe getHoaDonDatVe() {
        return hoaDonDatVe;
    }

    public void setHoaDonDatVe(HoaDonDatVe hoaDonDatVe) {
        this.hoaDonDatVe = hoaDonDatVe;
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

    public double tongTienCuoi(){
        return hoaDonDatVe.getTongTienDatCoc() - lePhi;
    }
}
