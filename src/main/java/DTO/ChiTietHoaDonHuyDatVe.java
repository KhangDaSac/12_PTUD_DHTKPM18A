package DTO;

import java.time.Duration;
import java.util.Objects;

public class ChiTietHoaDonHuyDatVe {
    private HoaDonHuyDatVe hoaDonHuyDatVe;
    private VeDat veDat;
    private double lePhi;

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

    public double lePhi(){
        long soGioConLai = thoiGianConLai().toHours();
        lePhi = 0;

        if(veDat.getLoaiVe().equals(LoaiVe.VECANHAN)){
            if(soGioConLai >= 48){
                lePhi = veDat.tienDatCoc() * 0.1;
            }else if(soGioConLai >= 4){
                lePhi = veDat.tienDatCoc() * 0.2;
            }
        }else if(veDat.getLoaiVe().equals(LoaiVe.VETAPTHE)){
            if(soGioConLai >= 72){
                lePhi = veDat.tienDatCoc() * 0.2;
            }else if(soGioConLai >= 24){
                lePhi = veDat.tienDatCoc() * 0.3;
            }
        }
        return lePhi;
    }

    public double soTienHoanLai(){
        return veDat.tienDatCoc() - lePhi;
    }
}
