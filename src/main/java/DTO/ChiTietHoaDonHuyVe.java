package DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChiTietHoaDonHuyVe {
    private HoaDonHuyVe hoaDonHuyVe;
    private Ve ve;
    private double lePhi;

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

    public double getLePhi() {
        return lePhi;
    }

    public void setLePhi(double lePhi) {
        this.lePhi = lePhi;
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
        Duration thoiGianConLai = Duration.between(hoaDonHuyVe.getThoiGianHuy(), ve.getThongTinGaTauDi().getThoiGianDi());
        return thoiGianConLai;
    }

    public double lePhi(){
        long soGioConLai = thoiGianConLai().toHours();
        lePhi = 0;

        if(ve.getLoaiVe().equals(LoaiVe.VECANHAN)){
            if(soGioConLai >= 48){
                lePhi = ve.tienVeCuoi() * 0.1;
            }else if(soGioConLai >= 4){
                lePhi = ve.tienVeCuoi() * 0.2;
            }
        }else if(ve.getLoaiVe().equals(LoaiVe.VETAPTHE)){
            if(soGioConLai >= 72){
                lePhi = ve.tienVeCuoi() * 0.2;
            }else if(soGioConLai >= 24){
                lePhi = ve.tienVeCuoi() * 0.3;
            }
        }

        return lePhi;
    }

    public double soTienHoanLai(){
        return ve.tienVeCuoi() - lePhi;
    }
}
