package DTO;

import java.util.Objects;

public class ChiTietPhieuKiemTien {
    private int soThuTu;
    private String tenMenhGia;
    private double giaTriMenhGia;
    private int soLuong;
    private double thanhTien;
    private PhieuKiemTien phieuKiemTien;

    public ChiTietPhieuKiemTien(int soThuTu, String tenMenhGia, double giaTriMenhGia, int soLuong, double thanhTien, PhieuKiemTien phieuKiemTien) {
        this.soThuTu = soThuTu;
        this.tenMenhGia = tenMenhGia;
        this.giaTriMenhGia = giaTriMenhGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.phieuKiemTien = phieuKiemTien;
    }

    public ChiTietPhieuKiemTien() {
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public String getTenMenhGia() {
        return tenMenhGia;
    }

    public void setTenMenhGia(String tenMenhGia) {
        this.tenMenhGia = tenMenhGia;
    }

    public double getGiaTriMenhGia() {
        return giaTriMenhGia;
    }

    public void setGiaTriMenhGia(double giaTriMenhGia) {
        this.giaTriMenhGia = giaTriMenhGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public PhieuKiemTien getPhieuKiemTien() {
        return phieuKiemTien;
    }

    public void setPhieuKiemTien(PhieuKiemTien phieuKiemTien) {
        this.phieuKiemTien = phieuKiemTien;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuKiemTien{" +
                "soThuTu=" + soThuTu +
                ", tenMenhGia='" + tenMenhGia + '\'' +
                ", giaTriMenhGia=" + giaTriMenhGia +
                ", soLuong=" + soLuong +
                ", thanhTien=" + thanhTien +
                ", phieuKiemTien=" + phieuKiemTien +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietPhieuKiemTien that = (ChiTietPhieuKiemTien) o;
        return Objects.equals(phieuKiemTien, that.phieuKiemTien);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(phieuKiemTien);
    }
}
