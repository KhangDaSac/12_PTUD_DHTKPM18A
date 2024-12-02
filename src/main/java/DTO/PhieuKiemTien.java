package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class PhieuKiemTien {
    private String maPhieuKiemTien;
    private double tongTien;
    private LocalDateTime thoiGianKiemTien;
    private NhanVien nhanVienQuanSat;
    private NhanVien nhanVienKiemTien;

    public String getMaPhieuKiemTien() {
        return maPhieuKiemTien;
    }

    public void setMaPhieuKiemTien(String maPhieuKiemTien) {
        this.maPhieuKiemTien = maPhieuKiemTien;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDateTime getThoiGianKiemTien() {
        return thoiGianKiemTien;
    }

    public void setThoiGianKiemTien(LocalDateTime thoiGianKiemTien) {
        this.thoiGianKiemTien = thoiGianKiemTien;
    }

    public NhanVien getNhanVienQuanSat() {
        return nhanVienQuanSat;
    }

    public void setNhanVienQuanSat(NhanVien nhanVienQuanSat) {
        this.nhanVienQuanSat = nhanVienQuanSat;
    }

    public NhanVien getNhanVienKiemTien() {
        return nhanVienKiemTien;
    }

    public void setNhanVienKiemTien(NhanVien nhanVienKiemTien) {
        this.nhanVienKiemTien = nhanVienKiemTien;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieuKiemTien that = (PhieuKiemTien) o;
        return Objects.equals(maPhieuKiemTien, that.maPhieuKiemTien);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maPhieuKiemTien);
    }
}
