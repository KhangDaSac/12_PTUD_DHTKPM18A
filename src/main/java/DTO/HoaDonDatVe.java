package DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDonDatVe {
    private String maHoaDonDatVe;
    private LocalDateTime thoiGianLap;
    private KhachHang khachHangDatVe;
    private CaLamViec caLamViec;

    private ArrayList<VeDat> danhSachVeDat;

    public HoaDonDatVe() {

    }

    public String getMaHoaDonDatVe() {
        return maHoaDonDatVe;
    }

    public void setMaHoaDonDatVe(String maHoaDonDatVe) {
        this.maHoaDonDatVe = maHoaDonDatVe;
    }

    public LocalDateTime getThoiGianLap() {
        return thoiGianLap;
    }

    public void setThoiGianLap(LocalDateTime thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public KhachHang getKhachHangDatVe() {
        return khachHangDatVe;
    }

    public void setKhachHangDatVe(KhachHang khachHangDatVe) {
        this.khachHangDatVe = khachHangDatVe;
    }

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }


    public ArrayList<VeDat> getDanhSachVeDat() {
        return danhSachVeDat;
    }

    public void setDanhSachVeDat(ArrayList<VeDat> danhSachVeDat) {
        this.danhSachVeDat = danhSachVeDat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoaDonDatVe that = (HoaDonDatVe) o;
        return Objects.equals(maHoaDonDatVe, that.maHoaDonDatVe);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maHoaDonDatVe);
    }

    public HoaDonDatVe(String maHoaDonDatVe) {
        this.maHoaDonDatVe = maHoaDonDatVe;
    }

    public HoaDonDatVe(String maHoaDonDatVe, LocalDateTime thoiGianLap, CaLamViec caLamViec) {
        this.maHoaDonDatVe = maHoaDonDatVe;
        this.thoiGianLap = thoiGianLap;
        this.caLamViec = caLamViec;
    }

    public double tongTienCuoi(){
        double tongTienCuoi = 0;
        for (VeDat veDat : danhSachVeDat){
            tongTienCuoi += veDat.tienVeCuoi();
        }
        return tongTienCuoi;
    }

    public double tongTienDatCoc(){
        double tongTienDatCoc = 0;
        for (VeDat veDat : danhSachVeDat){
            tongTienDatCoc += veDat.tienDatCoc();
        }
        return tongTienDatCoc;
    }

}
