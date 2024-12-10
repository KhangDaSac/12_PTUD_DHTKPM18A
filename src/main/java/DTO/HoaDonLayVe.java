package DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDonLayVe {
    private String maHoaDonLayVe;
    private LocalDateTime thoiGianLayVe;
    private KhachHang khachHangLayVe;
    private CaLamViec caLamViec;

    private ArrayList<ChiTietHoaDonLayVe> danhSachChiTietHoaDonLayVe;

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

    public KhachHang getKhachHangLayVe() {
        return khachHangLayVe;
    }

    public void setKhachHangLayVe(KhachHang khachHangLayVe) {
        this.khachHangLayVe = khachHangLayVe;
    }

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }

    public ArrayList<ChiTietHoaDonLayVe> getDanhSachChiTietHoaDonLayVe() {
        return danhSachChiTietHoaDonLayVe;
    }

    public void setDanhSachChiTietHoaDonLayVe(ArrayList<ChiTietHoaDonLayVe> danhSachChiTietHoaDonLayVe) {
        this.danhSachChiTietHoaDonLayVe = danhSachChiTietHoaDonLayVe;
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

    public HoaDonLayVe(String maHoaDonLayVe) {
        this.maHoaDonLayVe = maHoaDonLayVe;
    }

    public double tongTienCuoi(){
        double tongTienCuoi = 0;
        for (ChiTietHoaDonLayVe chiTietHoaDonLayVe : danhSachChiTietHoaDonLayVe){
            tongTienCuoi += chiTietHoaDonLayVe.thanhTien();
        }
        return tongTienCuoi;
    }

    public HoaDonLayVe(String maHoaDonLayVe, LocalDateTime thoiGianLayVe, KhachHang khachHangLayVe, CaLamViec caLamViec, ArrayList<ChiTietHoaDonLayVe> danhSachChiTietHoaDonLayVe) {
        this.maHoaDonLayVe = maHoaDonLayVe;
        this.thoiGianLayVe = thoiGianLayVe;
        this.khachHangLayVe = khachHangLayVe;
        this.caLamViec = caLamViec;
        this.danhSachChiTietHoaDonLayVe = new ArrayList<>();
    }
    public void addDanhSachChiTietHoaDonLayVe(ChiTietHoaDonLayVe chiTietHoaDonLayVe){
        this.danhSachChiTietHoaDonLayVe.add(chiTietHoaDonLayVe);
    }

    public HoaDonLayVe() {
    }
}
