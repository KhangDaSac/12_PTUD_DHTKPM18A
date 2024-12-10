package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class PhieuKetToan {
    private String maPhieuKetToan;
    private CaLamViec caLamViec;
    private LocalDateTime thoiGianLap;
    private double doanhThuThongKe;
    private PhieuKiemTien phieuKiemTienDauCa;
    private PhieuKiemTien phieuKiemTienCuoiCa;

    public String getMaPhieuKetToan() {
        return maPhieuKetToan;
    }

    public void setMaPhieuKetToan(String maPhieuKetToan) {
        this.maPhieuKetToan = maPhieuKetToan;
    }

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }

    public LocalDateTime getThoiGianLap() {
        return thoiGianLap;
    }

    public void setThoiGianLap(LocalDateTime thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public double getDoanhThuThongKe() {
        return doanhThuThongKe;
    }

    public void setDoanhThuThongKe(double doanhThuThongKe) {
        this.doanhThuThongKe = doanhThuThongKe;
    }

    public PhieuKiemTien getPhieuKiemTienDauCa() {
        return phieuKiemTienDauCa;
    }

    public void setPhieuKiemTienDauCa(PhieuKiemTien phieuKiemTienDauCa) {
        this.phieuKiemTienDauCa = phieuKiemTienDauCa;
    }

    public PhieuKiemTien getPhieuKiemTienCuoiCa() {
        return phieuKiemTienCuoiCa;
    }

    public void setPhieuKiemTienCuoiCa(PhieuKiemTien phieuKiemTienCuoiCa) {
        this.phieuKiemTienCuoiCa = phieuKiemTienCuoiCa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhieuKetToan that = (PhieuKetToan) o;
        return Objects.equals(maPhieuKetToan, that.maPhieuKetToan);
    }

    public PhieuKetToan(String maPhieuKetToan, CaLamViec caLamViec, LocalDateTime thoiGianLap) {
        this.maPhieuKetToan = maPhieuKetToan;
        this.caLamViec = caLamViec;
        this.thoiGianLap = thoiGianLap;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maPhieuKetToan);
    }

    public double doanhThuThucTe(){
        return phieuKiemTienCuoiCa.tongTien() - phieuKiemTienDauCa.tongTien();
    }
}
