package DTO;

import java.util.Objects;

public class ToaTau {
	private String maToaTau;
	private int thuTuToa;
	private int soLuongCho;
	private LoaiToaTau loaiToaTau;
	private ChuyenTau chuyenTau;
	private int soLuongChoDaBanVaDat;
	private int soLuongChoChangDaiHon;
	private int soLuongChoTrongTrong;
	public ToaTau() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ToaTau(String maToaTau, int thuTuToa, int soLuongCho, LoaiToaTau loaiToaTau, ChuyenTau chuyenTau) {
		super();
		this.maToaTau = maToaTau;
		this.thuTuToa = thuTuToa;
		this.soLuongCho = soLuongCho;
		this.loaiToaTau = loaiToaTau;
		this.chuyenTau = chuyenTau;
	}
	public ToaTau(String maToaTau) {
		super();
		this.maToaTau = maToaTau;
	}

	public ToaTau(String maToaTau, LoaiToaTau loaiToaTau) {
		this.maToaTau = maToaTau;
		this.loaiToaTau = loaiToaTau;
	}

	public ToaTau(String maToaTau, int thuTuToa, LoaiToaTau loaiToaTau) {
		this.maToaTau = maToaTau;
		this.thuTuToa = thuTuToa;
		this.loaiToaTau = loaiToaTau;
	}

	public ToaTau(String maToaTau, int thuTuToa, int soLuongCho, int soLuongChoDaBanVaDat, int soLuongChoChangDaiHon, int soLuongChoTrongTrong) {
		this.maToaTau = maToaTau;
		this.thuTuToa = thuTuToa;
		this.soLuongCho = soLuongCho;
		this.soLuongChoDaBanVaDat = soLuongChoDaBanVaDat;
		this.soLuongChoChangDaiHon = soLuongChoChangDaiHon;
		this.soLuongChoTrongTrong = soLuongChoTrongTrong;
	}

	public ToaTau(String maToaTau, int thuTuToa, int soLuongCho, LoaiToaTau loaiToaTau, ChuyenTau chuyenTau, int soLuongChoDaBanVaDat, int soLuongChoChangDaiHon, int soLuongChoTrongTrong) {
		this.maToaTau = maToaTau;
		this.thuTuToa = thuTuToa;
		this.soLuongCho = soLuongCho;
		this.loaiToaTau = loaiToaTau;
		this.chuyenTau = chuyenTau;
		this.soLuongChoDaBanVaDat = soLuongChoDaBanVaDat;
		this.soLuongChoChangDaiHon = soLuongChoChangDaiHon;
		this.soLuongChoTrongTrong = soLuongChoTrongTrong;
	}

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
	public int getSoLuongCho() {
		return soLuongCho;
	}
	public void setSoLuongCho(int soLuongCho) {
		this.soLuongCho = soLuongCho;
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

	public int getSoLuongChoDaBanVaDat() {
		return soLuongChoDaBanVaDat;
	}

	public void setSoLuongChoDaBanVaDat(int soLuongChoDaBanVaDat) {
		this.soLuongChoDaBanVaDat = soLuongChoDaBanVaDat;
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
	public int hashCode() {
		return Objects.hash(maToaTau);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToaTau other = (ToaTau) obj;
		return Objects.equals(maToaTau, other.maToaTau);
	}


	@Override
	public String toString() {
		return "ToaTau{" +
				"maToaTau='" + maToaTau + '\'' +
				", thuTuToa=" + thuTuToa +
				", soLuongCho=" + soLuongCho +
				", loaiToaTau=" + loaiToaTau +
				", chuyenTau=" + chuyenTau +
				'}';
	}
}
