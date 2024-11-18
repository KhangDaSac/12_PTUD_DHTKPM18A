package DTO;

import java.util.Objects;

public class ChiTietVe {
	private Ve ve;
	private Cho cho;
	private KhachHang khachHang;
	private double giaCho;
	private double phanTramGiamGia;


	public Ve getVe() {
		return ve;
	}

	public void setVe(Ve ve) {
		this.ve = ve;
	}

	public Cho getCho() {
		return cho;
	}

	public void setCho(Cho cho) {
		this.cho = cho;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public double getGiaCho() {
		return giaCho;
	}

	public void setGiaCho(double giaCho) {
		this.giaCho = giaCho;
	}

	public double getPhanTramGiamGia() {
		return phanTramGiamGia;
	}

	public void setPhanTramGiamGia(double phanTramGiamGia) {
		this.phanTramGiamGia = phanTramGiamGia;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChiTietVe chiTietVe = (ChiTietVe) o;
		return Objects.equals(ve, chiTietVe.ve) && Objects.equals(cho, chiTietVe.cho);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ve, cho);
	}

	public ChiTietVe(Ve ve, Cho cho, double giaCho) {
		this.ve = ve;
		this.cho = cho;
		this.giaCho = giaCho;
	}

	public ChiTietVe(Ve ve) {
		this.ve = ve;
	}

	public double giamGia(){
		return giaCho * phanTramGiamGia;
	}

	public double thanhTienChiTietVe(){
		return giaCho * (1 - phanTramGiamGia);
	}
}
