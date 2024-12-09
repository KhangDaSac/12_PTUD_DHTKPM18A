package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class CaLamViec {
    private String maCaLamViec;
    private LocalDateTime thoiGianBatDau;
    private LocalDateTime thoiGianKetThuc;
    private NhanVien nhanVien;

    public CaLamViec() {
    }

    public CaLamViec(String maCaLamViec) {
        this.maCaLamViec = maCaLamViec;
    }

    public CaLamViec(String maCaLamViec, LocalDateTime thoiGianBatDat, LocalDateTime thoiGianKetThuc, NhanVien nhanVien) {
        this.maCaLamViec = maCaLamViec;
        this.thoiGianBatDau = thoiGianBatDat;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.nhanVien = nhanVien;
    }

    public String getMaCaLamViec() {
        return maCaLamViec;
    }

    public void setMaCaLamViec(String maCaLamViec) {
        this.maCaLamViec = maCaLamViec;
    }

    public LocalDateTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalDateTime thoiGianBatDat) {
        this.thoiGianBatDau = thoiGianBatDat;
    }

    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaLamViec caLamViec = (CaLamViec) o;
        return Objects.equals(maCaLamViec, caLamViec.maCaLamViec);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maCaLamViec);
    }

    @Override
    public String toString() {
        return "CaLamViec{" +
                "maCaLamViec='" + maCaLamViec + '\'' +
                ", thoiGianBatDat=" + thoiGianBatDau +
                ", thoiGianKetThuc=" + thoiGianKetThuc +
                ", nhanVien=" + nhanVien +
                '}';
    }
}
