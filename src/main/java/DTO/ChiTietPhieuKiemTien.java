package DTO;

import java.util.Objects;

public class ChiTietPhieuKiemTien {
    private PhieuKiemTien phieuKiemTien;
    private MenhGia menhGia;
    private double giaTriMenhGia;
    private int soLuong;


    public PhieuKiemTien getPhieuKiemTien() {
        return phieuKiemTien;
    }

    public void setPhieuKiemTien(PhieuKiemTien phieuKiemTien) {
        this.phieuKiemTien = phieuKiemTien;
    }

    public MenhGia getMenhGia() {
        return menhGia;
    }

    public void setMenhGia(MenhGia menhGia) {
        this.menhGia = menhGia;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietPhieuKiemTien that = (ChiTietPhieuKiemTien) o;
        return Objects.equals(phieuKiemTien, that.phieuKiemTien) && menhGia == that.menhGia;
    }


    public ChiTietPhieuKiemTien(MenhGia menhGia, double giaTriMenhGia, int soLuong, PhieuKiemTien phieuKiemTien) {
        this.menhGia = menhGia;
        this.giaTriMenhGia = giaTriMenhGia;
        this.soLuong = soLuong;
        this.phieuKiemTien = phieuKiemTien;
    }

    public ChiTietPhieuKiemTien(MenhGia menhGia, double giaTriMenhGia, int soLuong) {
        this.menhGia = menhGia;
        this.giaTriMenhGia = giaTriMenhGia;
        this.soLuong = soLuong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phieuKiemTien, menhGia);
    }

    public double thanhTien(){
        return soLuong * giaTriMenhGia;
    }
}
