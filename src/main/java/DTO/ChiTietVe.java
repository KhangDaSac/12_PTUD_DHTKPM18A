package DTO;

import java.util.Objects;

public class ChiTietVe {
	private double giaCho;
	private double soTienGiamGia;
	private double thnahTien;
	private Ve ve;
	private KhachHang khachHang;
	private Cho cho;
	public ChiTietVe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietVe(Ve ve) {
		this.ve = ve;
	}

	public ChiTietVe(double giaCho, double soTienGiamGia, double thnahTien, Ve ve, KhachHang khachHang, Cho cho) {
		this.giaCho = giaCho;
		this.soTienGiamGia = soTienGiamGia;
		this.thnahTien = thnahTien;
		this.ve = ve;
		this.khachHang = khachHang;
		this.cho = cho;
	}

	public double getGiaCho() {
		return giaCho;
	}

	public void setGiaCho(double giaCho) {
		this.giaCho = giaCho;
	}

	public double getSoTienGiamGia() {
		return soTienGiamGia;
	}

	public void setSoTienGiamGia(double soTienGiamGia) {
		this.soTienGiamGia = soTienGiamGia;
	}

	public double getThnahTien() {
		return thnahTien;
	}

	public void setThnahTien(double thnahTien) {
		this.thnahTien = thnahTien;
	}

	public Ve getVe() {
		return ve;
	}

	public void setVe(Ve ve) {
		this.ve = ve;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Cho getCho() {
		return cho;
	}

	public void setCho(Cho cho) {
		this.cho = cho;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChiTietVe chiTietVe = (ChiTietVe) o;
		return Objects.equals(ve, chiTietVe.ve);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(ve);
	}

	@Override
	public String toString() {
		return "ChiTietVe{" +
				"giaCho=" + giaCho +
				", soTienGiamGia=" + soTienGiamGia +
				", thnahTien=" + thnahTien +
				", ve=" + ve +
				", khachHang=" + khachHang +
				", cho=" + cho +
				'}';
	}
}
