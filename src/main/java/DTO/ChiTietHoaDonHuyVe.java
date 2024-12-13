package DTO;

import java.time.Duration;
import java.util.Objects;

public class ChiTietHoaDonHuyVe {
    private HoaDonHuyVe hoaDonHuyVe;
    private Ve ve;
    private double phanTramLePhi;

    public ChiTietHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe, Ve ve) {
        this.hoaDonHuyVe = hoaDonHuyVe;
        this.ve = ve;
        long soGioConLai = thoiGianConLai().toHours();
        if(this.ve.getLoaiVe().equals(LoaiVe.VECANHAN)){
            if(soGioConLai >= 48){
                this.phanTramLePhi = 0.1;
            }else if(soGioConLai >= 4){
                this.phanTramLePhi = 0.2;
            }
        }else if(this.ve.getLoaiVe().equals(LoaiVe.VETAPTHE)){
            if(soGioConLai >= 72){
                this.phanTramLePhi = 0.2;
            }else if(soGioConLai >= 24){
                this.phanTramLePhi = 0.3;
            }
        }
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
        Duration thoiGianConLai = Duration.between(hoaDonHuyVe.getThoiGianHuyVe(), ve.getThongTinGaTauDi().getThoiGianDi());
        return thoiGianConLai;
    }

    public ChiTietHoaDonHuyVe(HoaDonHuyVe maHoaDonHuyVe, Ve ve, double phanTramLePhi) {
        long soGioConLai = thoiGianConLai().toHours();
        if(this.ve.getLoaiVe().equals(LoaiVe.VECANHAN)){
            if(soGioConLai >= 48){
                this.phanTramLePhi = 0.1;
            }else if(soGioConLai >= 4){
                this.phanTramLePhi = 0.2;
            }
        }else if(this.ve.getLoaiVe().equals(LoaiVe.VETAPTHE)){
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
