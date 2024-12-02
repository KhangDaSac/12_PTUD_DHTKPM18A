package DTO;

import java.util.Objects;

public class Cho {
    private String maCho;
    private int soCho;
    private double doDaiChangToiThieu;
    private double giaCho;
    private ToaTau toaTau;
    private LoaiCho loaiCho;
    private TrangThaiCho trangThaiCho;

    public Cho() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Cho(ToaTau toaTau, String maCho, int soCho) {
        this.toaTau = toaTau;
        this.maCho = maCho;
        this.soCho = soCho;
    }

    public Cho(String maCho, int soCho, double doDaiChangToiThieu, ToaTau toaTau, LoaiCho loaiCho) {
        super();
        this.maCho = maCho;
        this.soCho = soCho;
        this.doDaiChangToiThieu = doDaiChangToiThieu;
        this.toaTau = toaTau;
        this.loaiCho = loaiCho;
    }

    public Cho(String maCho, int soCho, double doDaiChangToiThieu, double giaCho, ToaTau toaTau, LoaiCho loaiCho, TrangThaiCho trangThaiCho) {
        this.maCho = maCho;
        this.soCho = soCho;
        this.doDaiChangToiThieu = doDaiChangToiThieu;
        this.giaCho = giaCho;
        this.toaTau = toaTau;
        this.loaiCho = loaiCho;
        this.trangThaiCho = trangThaiCho;
    }

    public Cho(String maCho, int soCho, ToaTau toaTau) {
        this.maCho = maCho;
        this.soCho = soCho;
        this.toaTau = toaTau;
    }



    public Cho(String maCho, int soCho, ToaTau toaTau, LoaiCho loaiCho, TrangThaiCho trangThaiCho) {
        this.maCho = maCho;
        this.soCho = soCho;
        this.toaTau = toaTau;
        this.loaiCho = loaiCho;
        this.trangThaiCho = trangThaiCho;
    }

    public Cho(String maCho) {
        super();
        this.maCho = maCho;
    }

    public String getMaCho() {
        return maCho;
    }

    public void setMaCho(String maCho) {
        this.maCho = maCho;
    }

    public int getSoCho() {
        return soCho;
    }

    public void setSoCho(int soCho) {
        this.soCho = soCho;
    }

    public double getDoDaiChangToiThieu() {
        return doDaiChangToiThieu;
    }

    public void setDoDaiChangToiThieu(double doDaiChangToiThieu) {
        this.doDaiChangToiThieu = doDaiChangToiThieu;
    }

    public double getGiaCho() {
        return giaCho;
    }

    public void setGiaCho(double giaCho) {
        this.giaCho = giaCho;
    }

    public ToaTau getToaTau() {
        return toaTau;
    }

    public void setToaTau(ToaTau toaTau) {
        this.toaTau = toaTau;
    }

    public LoaiCho getLoaiCho() {
        return loaiCho;
    }

    public void setLoaiCho(LoaiCho loaiCho) {
        this.loaiCho = loaiCho;
    }

    public TrangThaiCho getTrangThaiCho() {
        return trangThaiCho;
    }

    public void setTrangThaiCho(TrangThaiCho trangThaiCho) {
        this.trangThaiCho = trangThaiCho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cho cho = (Cho) o;
        return Objects.equals(maCho, cho.maCho);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(maCho);
    }

    public Cho(String maCho, int soCho, double doDaiChangToiThieu, double giaCho, ToaTau toaTau, LoaiCho loaiCho) {
        this.maCho = maCho;
        this.soCho = soCho;
        this.doDaiChangToiThieu = doDaiChangToiThieu;
        this.giaCho = giaCho;
        this.toaTau = toaTau;
        this.loaiCho = loaiCho;
    }

    @Override
    public String toString() {
        return "Cho{" +
                "maCho='" + maCho + '\'' +
                ", soCho=" + soCho +
                ", doDaiChangToiThieu=" + doDaiChangToiThieu +
                ", toaTau=" + toaTau +
                ", loaiCho=" + loaiCho +
                '}';
    }

    public double tinhGiaCho(double doDaiChang){
        double gia = 0;
        if(doDaiChang <= 100){
            gia += doDaiChang * 1200;
            return  gia;
        }

        gia += 100 * 1000;

        if(doDaiChang <= 500){
            gia += (doDaiChang - 100) * 900;
            return gia;
        }

        gia += (500 - 100) * 600;

        gia += (doDaiChang - 500) * 400;

        gia = gia * toaTau.getLoaiToaTau().getHeSoGia() * loaiCho.getHeSoGiaCho();
        return gia;
    }
}