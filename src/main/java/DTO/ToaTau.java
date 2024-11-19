package DTO;

import java.util.Objects;

public class ToaTau {
	private String maToaTau;
	private int thuTuToa;
	private LoaiToaTau loaiToaTau;
	private ChuyenTau chuyenTau;
	private int soLuongChoDaBan;
	private int soLuongChoDaDat;
	private int soLuongChoChangDaiHon;
	private int soLuongChoTrongTrong;



	public String getMaToaTau() {
		return maToaTau;
	}

	public void setMaToaTau(String maToaTau) {
		this.maToaTau = maToaTau;
	}

	public int getThuTuToa() {
		return thuTuToa;
	}

	public void setThuTuToa(int thuTuToa) {
		this.thuTuToa = thuTuToa;
	}

	public LoaiToaTau getLoaiToaTau() {
		return loaiToaTau;
	}

	public void setLoaiToaTau(LoaiToaTau loaiToaTau) {
		this.loaiToaTau = loaiToaTau;
	}

	public ChuyenTau getChuyenTau() {
		return chuyenTau;
	}

	public void setChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	public int getSoLuongChoDaBan() {
		return soLuongChoDaBan;
	}

	public void setSoLuongChoDaBan(int soLuongChoDaBan) {
		this.soLuongChoDaBan = soLuongChoDaBan;
	}

	public int getSoLuongChoDaDat() {
		return soLuongChoDaDat;
	}

	public void setSoLuongChoDaDat(int soLuongChoDaDat) {
		this.soLuongChoDaDat = soLuongChoDaDat;
	}

	public int getSoLuongChoChangDaiHon() {
		return soLuongChoChangDaiHon;
	}

	public void setSoLuongChoChangDaiHon(int soLuongChoChangDaiHon) {
		this.soLuongChoChangDaiHon = soLuongChoChangDaiHon;
	}

	public int getSoLuongChoTrongTrong() {
		return soLuongChoTrongTrong;
	}

	public void setSoLuongChoTrongTrong(int soLuongChoTrongTrong) {
		this.soLuongChoTrongTrong = soLuongChoTrongTrong;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ToaTau toaTau = (ToaTau) o;
		return Objects.equals(maToaTau, toaTau.maToaTau);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maToaTau);
	}

	public ToaTau(String maToaTau, int thuTuToa, LoaiToaTau loaiToaTau, ChuyenTau chuyenTau, int soLuongChoDaBan, int soLuongChoDaDat, int soLuongChoChangDaiHon, int soLuongChoTrongTrong) {
		this.maToaTau = maToaTau;
		this.thuTuToa = thuTuToa;
		this.loaiToaTau = loaiToaTau;
		this.chuyenTau = chuyenTau;
		this.soLuongChoDaBan = soLuongChoDaBan;
		this.soLuongChoDaDat = soLuongChoDaDat;
		this.soLuongChoChangDaiHon = soLuongChoChangDaiHon;
		this.soLuongChoTrongTrong = soLuongChoTrongTrong;
	}

	public ToaTau(String maToaTau, ChuyenTau chuyenTau, int thuTuToa, LoaiToaTau loaiToaTau) {
		this.maToaTau = maToaTau;
		this.thuTuToa = thuTuToa;
		this.loaiToaTau = loaiToaTau;
		this.chuyenTau = chuyenTau;
	}

	public ToaTau(String maToaTau, int thuTuToa, LoaiToaTau loaiToaTau) {
		this.maToaTau = maToaTau;
		this.thuTuToa = thuTuToa;
		this.loaiToaTau = loaiToaTau;
	}

	public ToaTau(String maToaTau, int thuTuToa, LoaiToaTau loaiToaTau, ChuyenTau chuyenTau) {
		this.maToaTau = maToaTau;
		this.thuTuToa = thuTuToa;
		this.loaiToaTau = loaiToaTau;
		this.chuyenTau = chuyenTau;
	}

	public ToaTau(String maToaTau) {
		this.maToaTau = maToaTau;
	}
}
