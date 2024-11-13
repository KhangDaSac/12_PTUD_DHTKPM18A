package DTO;

import org.apache.bcel.generic.PUSH;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonLayVe {
    private String maHoaDonLayVe;
    private LocalDateTime thoiGianLayVe;
    private HoaDonDatVe hoaDonDatVe;
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
        HoaDonLayVe that = (HoaDonLayVe) o;
        return Objects.equals(maHoaDonLayVe, that.maHoaDonLayVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHoaDonLayVe);
    }


    public HoaDonLayVe(String maHoaDonLayVe, LocalDateTime thoiGianLayVe, HoaDonDatVe hoaDonDatVe, CaLamViec caLamViec) {
        this.maHoaDonLayVe = maHoaDonLayVe;
        this.thoiGianLayVe = thoiGianLayVe;
        this.hoaDonDatVe = hoaDonDatVe;
        this.caLamViec = caLamViec;
    }

    public HoaDonLayVe(String maHoaDonLayVe) {
        this.maHoaDonLayVe = maHoaDonLayVe;
    }

    public double tongTienCuoi(){
        return hoaDonDatVe.getTongTien() - hoaDonDatVe.getTongTienDatCoc();
    }
}
