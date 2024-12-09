package DTO;

import java.time.Duration;
import java.util.Objects;

public class ChiTietHoaDonHuyDatVe {
    private HoaDonHuyDatVe hoaDonHuyDatVe;
    private VeDat veDat;
    private double phanTramLePhi;

    public HoaDonHuyDatVe getHoaDonHuyDatVe() {
        return hoaDonHuyDatVe;
    }

    public void setHoaDonHuyDatVe(HoaDonHuyDatVe hoaDonHuyDatVe) {
        this.hoaDonHuyDatVe = hoaDonHuyDatVe;
    }

    public VeDat getVeDat() {
        return veDat;
    }

    public void setVeDat(VeDat veDat) {
        this.veDat = veDat;
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
        ChiTietHoaDonHuyDatVe that = (ChiTietHoaDonHuyDatVe) o;
        return Objects.equals(veDat, that.veDat);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(veDat);
    }

    public Duration thoiGianConLai() {
        Duration thoiGianConLai = Duration.between(hoaDonHuyDatVe.getThoiGianHuy(), veDat.getThongTinGaTauDi().getThoiGianDi());
        return thoiGianConLai;
    }

    public ChiTietHoaDonHuyDatVe() {
        long soGioConLai = thoiGianConLai().toHours();
        if(veDat.getLoaiVe().equals(LoaiVe.VECANHAN)){
            if(soGioConLai >= 48){
                this.phanTramLePhi = 0.1;
            }else if(soGioConLai >= 4){
                this.phanTramLePhi = 0.2;
            }
        }else if(veDat.getLoaiVe().equals(LoaiVe.VETAPTHE)){
            if(soGioConLai >= 72){
                this.phanTramLePhi = 0.2;
            }else if(soGioConLai >= 24){
                this.phanTramLePhi = 0.3;
            }
        }
    }

    public double lePhi(){
        return veDat.tienDatCoc() * phanTramLePhi;
    }

    public double soTienHoanLai(){
        return veDat.tienDatCoc() * (1 - phanTramLePhi);
    }

    public ChiTietHoaDonHuyDatVe(HoaDonHuyDatVe hoaDonHuyDatVe, VeDat veDat, double phanTramLePhi) {
        this.hoaDonHuyDatVe = hoaDonHuyDatVe;
        this.veDat = veDat;
        this.phanTramLePhi = phanTramLePhi;
    }

    public ChiTietHoaDonHuyDatVe(HoaDonHuyDatVe hoaDonHuyDatVe, VeDat veDat) {
        this.hoaDonHuyDatVe = hoaDonHuyDatVe;
        this.veDat = veDat;
        long soGioConLai = thoiGianConLai().toHours();
        if(veDat.getLoaiVe().equals(LoaiVe.VECANHAN)){
            if(soGioConLai >= 48){
                this.phanTramLePhi = 0.1;
            }else if(soGioConLai >= 4){
                this.phanTramLePhi = 0.2;
            }
        }else if(veDat.getLoaiVe().equals(LoaiVe.VETAPTHE)){
            if(soGioConLai >= 72){
                this.phanTramLePhi = 0.2;
            }else if(soGioConLai >= 24){
                this.phanTramLePhi = 0.3;
            }
        }
    }
}
