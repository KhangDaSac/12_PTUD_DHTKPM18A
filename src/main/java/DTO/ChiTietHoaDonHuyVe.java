package DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChiTietHoaDonHuyVe {
    private HoaDonHuyVe hoaDonHuyVe;
    private Ve ve;
    private double phanTramLePhi;

    public ChiTietHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe, Ve ve) {
        this.hoaDonHuyVe = hoaDonHuyVe;
        this.ve = ve;
        long soGioConLai = thoiGianConLai().toHours();
        if(ve.getLoaiVe().equals(LoaiVe.VECANHAN)){
            if(soGioConLai >= 48){
                this.phanTramLePhi = 0.1;
            }else if(soGioConLai >= 4){
                this.phanTramLePhi = 0.2;
            }
        }else if(ve.getLoaiVe().equals(LoaiVe.VETAPTHE)){
            if(soGioConLai >= 72){
                this.phanTramLePhi = 0.2;
            }else if(soGioConLai >= 24){
                this.phanTramLePhi = 0.3;
            }
        }
    }

    public ChiTietHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe, Ve ve, double phanTramLePhi) {
        this.hoaDonHuyVe = hoaDonHuyVe;
        this.ve = ve;
        this.phanTramLePhi = phanTramLePhi;
    }

    public HoaDonHuyVe getHoaDonHuyVe() {
        return hoaDonHuyVe;
    }

    public void setHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe) {
        this.hoaDonHuyVe = hoaDonHuyVe;
    }

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
    }

    public double getPhanTramLePhi() {
        return phanTramLePhi;
    }

    public void setPhanTramLePhi(double phanTramLePhi) {
        this.phanTramLePhi = phanTramLePhi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDonHuyVe that = (ChiTietHoaDonHuyVe) o;
        return Objects.equals(ve, that.ve);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ve);
    }

    public Duration thoiGianConLai() {
        if (hoaDonHuyVe == null || ve == null || ve.getThongTinGaTauDi() == null) {
            return Duration.ZERO;
        }
        return Duration.between(hoaDonHuyVe.getThoiGianHuyVe(), ve.getThongTinGaTauDi().getThoiGianDi());
    }

    public ChiTietHoaDonHuyVe() {
        this.ve = this.ve != null ? this.ve : new Ve(); // Khởi tạo nếu null
        this.hoaDonHuyVe = this.hoaDonHuyVe != null ? this.hoaDonHuyVe : new HoaDonHuyVe(); // Khởi tạo nếu null
        long soGioConLai = thoiGianConLai().toHours();
        if(ve.getLoaiVe() == LoaiVe.VECANHAN){
            if(soGioConLai >= 48){
                this.phanTramLePhi = 0.1;
            }else if(soGioConLai >= 4){
                this.phanTramLePhi = 0.2;
            }
        }else if(ve.getLoaiVe() == LoaiVe.VETAPTHE){
            if(soGioConLai >= 72){
                this.phanTramLePhi = 0.2;
            }else if(soGioConLai >= 24){
                this.phanTramLePhi = 0.3;
            }
        }
    }

    public double lePhi(){
        return ve.tienVeCuoi() * phanTramLePhi;
    }

    public double soTienHoanLai(){
        return ve.tienVeCuoi() * (1 - phanTramLePhi);
    }


}
