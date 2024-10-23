package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class LichSuLayVe {
    private String maLichSuLayVe;
    private LocalDateTime thoiGianLayVe;
    private double soTienThu;
    private PhieuDatVe phieuDatVe;
    private Ve ve;
    private CaLamViec caLamViec;

    public LichSuLayVe() {
    }

    public LichSuLayVe(String maLichSuLayVe) {
        this.maLichSuLayVe = maLichSuLayVe;
    }

    public LichSuLayVe(String maLichSuLayVe, LocalDateTime thoiGianLayVe, double soTienThu, PhieuDatVe phieuDatVe, Ve ve, CaLamViec caLamViec) {
        this.maLichSuLayVe = maLichSuLayVe;
        this.thoiGianLayVe = thoiGianLayVe;
        this.soTienThu = soTienThu;
        this.phieuDatVe = phieuDatVe;
        this.ve = ve;
        this.caLamViec = caLamViec;
    }

    public String getMaLichSuLayVe() {
        return maLichSuLayVe;
    }

    public void setMaLichSuLayVe(String maLichSuLayVe) {
        this.maLichSuLayVe = maLichSuLayVe;
    }

    public LocalDateTime getThoiGianLayVe() {
        return thoiGianLayVe;
    }

    public void setThoiGianLayVe(LocalDateTime thoiGianLayVe) {
        this.thoiGianLayVe = thoiGianLayVe;
    }

    public double getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(double soTienThu) {
        this.soTienThu = soTienThu;
    }

    public PhieuDatVe getPhieuDatVe() {
        return phieuDatVe;
    }

    public void setPhieuDatVe(PhieuDatVe phieuDatVe) {
        this.phieuDatVe = phieuDatVe;
    }

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
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
        LichSuLayVe that = (LichSuLayVe) o;
        return Double.compare(soTienThu, that.soTienThu) == 0 && Objects.equals(maLichSuLayVe, that.maLichSuLayVe) && Objects.equals(thoiGianLayVe, that.thoiGianLayVe) && Objects.equals(phieuDatVe, that.phieuDatVe) && Objects.equals(ve, that.ve) && Objects.equals(caLamViec, that.caLamViec);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLichSuLayVe);
    }

    @Override
    public String toString() {
        return "LichSuLayVe{" +
                "maLichSuLayVe='" + maLichSuLayVe + '\'' +
                ", thoiGianLayVe=" + thoiGianLayVe +
                ", soTienThu=" + soTienThu +
                ", phieuDatVe=" + phieuDatVe +
                ", ve=" + ve +
                ", caLamViec=" + caLamViec +
                '}';
    }
}
