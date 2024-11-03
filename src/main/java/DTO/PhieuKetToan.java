package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class PhieuKetToan {
    private String maPhieuKetToan;
    private LocalDateTime thoiGianLap;
    private double tongTienBatDauCa;
    private double tongTienKetThucCa;
    private double doanhThuThucTe;
    private double doanhThuThongKe;
    private CaLamViec caLamViec;

    public PhieuKetToan() {
    }

    public PhieuKetToan(String maPhieuKetToan) {
        this.maPhieuKetToan = maPhieuKetToan;
    }

    public PhieuKetToan(String maPhieuKetToan, LocalDateTime thoiGianLap, double tongTienBatDauCa, double tongTienKetThucCa, double doanhThuThucTe, double doanhThuThongKe, CaLamViec caLamViec) {
        this.maPhieuKetToan = maPhieuKetToan;
        this.thoiGianLap = thoiGianLap;
        this.tongTienBatDauCa = tongTienBatDauCa;
        this.tongTienKetThucCa = tongTienKetThucCa;
        this.doanhThuThucTe = doanhThuThucTe;
        this.doanhThuThongKe = doanhThuThongKe;
        this.caLamViec = caLamViec;
    }

    public String getMaPhieuKetToan() {
        return maPhieuKetToan;
    }

    public void setMaPhieuKetToan(String maPhieuKetToan) {
        this.maPhieuKetToan = maPhieuKetToan;
    }

    public LocalDateTime getThoiGianLap() {
        return thoiGianLap;
    }

    public void setThoiGianLap(LocalDateTime thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public double getTongTienBatDauCa() {
        return tongTienBatDauCa;
    }

    public void setTongTienBatDauCa(double tongTienBatDauCa) {
        this.tongTienBatDauCa = tongTienBatDauCa;
    }

    public double getTongTienKetThucCa() {
        return tongTienKetThucCa;
    }

    public void setTongTienKetThucCa(double tongTienKetThucCa) {
        this.tongTienKetThucCa = tongTienKetThucCa;
    }

    public double getDoanhThuThucTe() {
        return doanhThuThucTe;
    }

    public void setDoanhThuThucTe(double doanhThuThucTe) {
        this.doanhThuThucTe = doanhThuThucTe;
    }

    public double getDoanhThuThongKe() {
        return doanhThuThongKe;
    }

    public void setDoanhThuThongKe(double doanhThuThongKe) {
        this.doanhThuThongKe = doanhThuThongKe;
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
        PhieuKetToan that = (PhieuKetToan) o;
        return Objects.equals(maPhieuKetToan, that.maPhieuKetToan);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maPhieuKetToan);
    }

    @Override
    public String toString() {
        return "PhieuKetToan{" +
                "maPhieuKetToan='" + maPhieuKetToan + '\'' +
                ", thoiGianLap=" + thoiGianLap +
                ", tongTienBatDauCa=" + tongTienBatDauCa +
                ", tongTienKetThucCa=" + tongTienKetThucCa +
                ", doanhThuThucTe=" + doanhThuThucTe +
                ", doanhThuThongKe=" + doanhThuThongKe +
                ", caLamViec=" + caLamViec +
                '}';
    }
}
