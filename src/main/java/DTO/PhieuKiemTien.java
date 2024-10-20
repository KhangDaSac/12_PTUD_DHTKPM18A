package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class PhieuKiemTien {
    private String maPhieuKiemTien;
    private double tongTien;
    private LocalDateTime thoiGianKiemTien;
    private NhanVien nhanVienQuanSat;
    private NhanVien nhanVienKiemTien;
    private LoaiPhieuKiemTien loaiPhieuKiemTien;
    private PhieuKetToan phieuKetToan;

    public PhieuKiemTien() {
    }

    public PhieuKiemTien(String maPhieuKiemTien) {
        this.maPhieuKiemTien = maPhieuKiemTien;
    }

    public PhieuKiemTien(String maPhieuKiemTien, double tongTien, LocalDateTime thoiGianKiemTien, NhanVien nhanVienQuanSat, NhanVien nhanVienKiemTien, LoaiPhieuKiemTien loaiPhieuKiemTien, PhieuKetToan phieuKetToan) {
        this.maPhieuKiemTien = maPhieuKiemTien;
        this.tongTien = tongTien;
        this.thoiGianKiemTien = thoiGianKiemTien;
        this.nhanVienQuanSat = nhanVienQuanSat;
        this.nhanVienKiemTien = nhanVienKiemTien;
        this.loaiPhieuKiemTien = loaiPhieuKiemTien;
        this.phieuKetToan = phieuKetToan;
    }

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

    public LoaiPhieuKiemTien getLoaiPhieuKiemTien() {
        return loaiPhieuKiemTien;
    }

    public void setLoaiPhieuKiemTien(LoaiPhieuKiemTien loaiPhieuKiemTien) {
        this.loaiPhieuKiemTien = loaiPhieuKiemTien;
    }

    public PhieuKetToan getPhieuKetToan() {
        return phieuKetToan;
    }

    public void setPhieuKetToan(PhieuKetToan phieuKetToan) {
        this.phieuKetToan = phieuKetToan;
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

    @Override
    public String toString() {
        return "PhieuKiemTien{" +
                "maPhieuKiemTien='" + maPhieuKiemTien + '\'' +
                ", tongTien=" + tongTien +
                ", thoiGianKiemTien=" + thoiGianKiemTien +
                ", nhanVienQuanSat=" + nhanVienQuanSat +
                ", nhanVienKiemTien=" + nhanVienKiemTien +
                ", loaiPhieuKiemTien=" + loaiPhieuKiemTien +
                ", phieuKetToan=" + phieuKetToan +
                '}';
    }
}
