package DTO;

public class ChiTietHoaDonLayVe {
    private VeDat veDat;
    private Ve ve;
    private HoaDonLayVe hoaDonLayVe;

    public VeDat getVeDat() {
        return veDat;
    }

    public void setVeDat(VeDat veDat) {
        this.veDat = veDat;
    }

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
    }

    public HoaDonLayVe getHoaDonLayVe() {
        return hoaDonLayVe;
    }

    public void setHoaDonLayVe(HoaDonLayVe hoaDonLayVe) {
        this.hoaDonLayVe = hoaDonLayVe;
    }

    public double thanhTien(){
        return veDat.tienVeCuoi() - veDat.tienDatCoc();
    }

    public ChiTietHoaDonLayVe(VeDat veDat, Ve ve, HoaDonLayVe hoaDonLayVe) {
        this.veDat = veDat;
        this.ve = ve;
        this.hoaDonLayVe = hoaDonLayVe;
    }
}
