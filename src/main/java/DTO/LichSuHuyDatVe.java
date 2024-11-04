package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class LichSuHuyDatVe {
    private String maLichSuHuyDatVe;
    private LocalDateTime thoiGianHuy;
    private double lePhi;
    private float soTienHoanTra;
    private PhieuDatVe phieuDatVe;
    private CaLamViec caLamViec;

    public LichSuHuyDatVe() {
    }

    public LichSuHuyDatVe(String maLichSuHuyDatVe) {
        this.maLichSuHuyDatVe = maLichSuHuyDatVe;
    }

    public LichSuHuyDatVe(String maLichSuHuyDatVe, LocalDateTime thoiGianHuy, double lePhi, float soTienHoanTra, PhieuDatVe phieuDatVe, CaLamViec caLamViec) {
        this.maLichSuHuyDatVe = maLichSuHuyDatVe;
        this.thoiGianHuy = thoiGianHuy;
        this.lePhi = lePhi;
        this.soTienHoanTra = soTienHoanTra;
        this.phieuDatVe = phieuDatVe;
        this.caLamViec = caLamViec;
    }

    public String getMaLichSuHuyDatVe() {
        return maLichSuHuyDatVe;
    }

    public void setMaLichSuHuyDatVe(String maLichSuHuyDatVe) {
        this.maLichSuHuyDatVe = maLichSuHuyDatVe;
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

    public float getSoTienHoanTra() {
        return soTienHoanTra;
    }

    public void setSoTienHoanTra(float soTienHoanTra) {
        this.soTienHoanTra = soTienHoanTra;
    }

    public PhieuDatVe getPhieuDatVe() {
        return phieuDatVe;
    }

    public void setPhieuDatVe(PhieuDatVe phieuDatVe) {
        this.phieuDatVe = phieuDatVe;
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
        LichSuHuyDatVe that = (LichSuHuyDatVe) o;
        return Double.compare(lePhi, that.lePhi) == 0 && Float.compare(soTienHoanTra, that.soTienHoanTra) == 0 && Objects.equals(maLichSuHuyDatVe, that.maLichSuHuyDatVe) && Objects.equals(thoiGianHuy, that.thoiGianHuy) && Objects.equals(phieuDatVe, that.phieuDatVe) && Objects.equals(caLamViec, that.caLamViec);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maLichSuHuyDatVe);
    }

    @Override
    public String toString() {
        return "LichSuHuyDatVe{" +
                "maLichSuHuyDatVe='" + maLichSuHuyDatVe + '\'' +
                ", thoiGianHuy=" + thoiGianHuy +
                ", lePhi=" + lePhi +
                ", soTienHoanTra=" + soTienHoanTra +
                ", phieuDatVe=" + phieuDatVe +
                ", caLamViec=" + caLamViec +
                '}';
    }
    public Double tinhtienHoanTra(LocalDateTime thoiGianHuy,LocalDateTime thoiGianKhoiHanh){
        return (double) (soTienHoanTra - lePhi);
    }
}
