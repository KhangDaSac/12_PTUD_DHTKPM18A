package DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


public class ChuyenTau {
	private String maChuyenTau;
	private TuyenTau tuyenTau;
	private int soLuongCho;
	private int soLuongChoDaBanVaDat;
	private int soLuongChoChangDaiHon;
	private int soLuongChoTrongTrong;

	public ChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}

	public ChuyenTau() {
	}

	public String getMaChuyenTau() {
		return maChuyenTau;
	}

	public void setMaChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}

	public TuyenTau getTuyenTau() {
		return tuyenTau;
	}

	public void setTuyenTau(TuyenTau tuyenTau) {
		this.tuyenTau = tuyenTau;
	}

	public int getSoLuongCho() {
		return soLuongCho;
	}

	public void setSoLuongCho(int soLuongCho) {
		this.soLuongCho = soLuongCho;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChuyenTau chuyenTau = (ChuyenTau) o;
		return Objects.equals(maChuyenTau, chuyenTau.maChuyenTau);
	}

	public ChuyenTau(String maChuyenTau, int soLuongCho) {
		this.maChuyenTau = maChuyenTau;
		this.soLuongCho = soLuongCho;
	}


	public ChuyenTau(String maChuyenTau, TuyenTau tuyenTau, int soLuongCho) {
		this.maChuyenTau = maChuyenTau;
		this.tuyenTau = tuyenTau;
		this.soLuongCho = soLuongCho;
	}


	public ChuyenTau(String maChuyenTau, int soLuongCho, int soLuongChoDaBanVaDat, int soLuongChoChangDaiHon, int soLuongChoTrongTrong) {
		this.maChuyenTau = maChuyenTau;
		this.soLuongCho = soLuongCho;
		this.soLuongChoDaBanVaDat = soLuongChoDaBanVaDat;
		this.soLuongChoChangDaiHon = soLuongChoChangDaiHon;
		this.soLuongChoTrongTrong = soLuongChoTrongTrong;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maChuyenTau);
	}


}