package DTO;

import java.util.ArrayList;
import java.util.Objects;


public class ChuyenTau {
	private String maChuyenTau;
	private TuyenTau tuyenTau;

	private int soLuongChoDaBan;
	private int soLuongChoDaDat;
	private int soLuongChoChangDaiHon;
	private int soLuongChoTrongTrong;
	private ChiTietChuyenTau thongTinGaTauDi;
	private ChiTietChuyenTau thongTinGaTauDen;

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

	public ChiTietChuyenTau getThongTinGaTauDi() {
		return thongTinGaTauDi;
	}

	public void setThongTinGaTauDi(ChiTietChuyenTau thongTinGaTauDi) {
		this.thongTinGaTauDi = thongTinGaTauDi;
	}

	public ChiTietChuyenTau getThongTinGaTauDen() {
		return thongTinGaTauDen;
	}

	public void setThongTinGaTauDen(ChiTietChuyenTau thongTinGaTauDen) {
		this.thongTinGaTauDen = thongTinGaTauDen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChuyenTau chuyenTau = (ChuyenTau) o;
		return Objects.equals(maChuyenTau, chuyenTau.maChuyenTau);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maChuyenTau);
	}

	public ChuyenTau(String maChuyenTau, TuyenTau tuyenTau, int soLuongChoDaBan, int soLuongChoDaDat, int soLuongChoChangDaiHon, int soLuongChoTrongTrong) {
		this.maChuyenTau = maChuyenTau;
		this.tuyenTau = tuyenTau;
		this.soLuongChoDaBan = soLuongChoDaBan;
		this.soLuongChoDaDat = soLuongChoDaDat;
		this.soLuongChoChangDaiHon = soLuongChoChangDaiHon;
		this.soLuongChoTrongTrong = soLuongChoTrongTrong;
	}

	public ChuyenTau(String maChuyenTau, int soLuongChoDaBan, int soLuongChoDaDat, int soLuongChoChangDaiHon, int soLuongChoTrongTrong) {
		this.maChuyenTau = maChuyenTau;
		this.soLuongChoDaBan = soLuongChoDaBan;
		this.soLuongChoDaDat = soLuongChoDaDat;
		this.soLuongChoChangDaiHon = soLuongChoChangDaiHon;
		this.soLuongChoTrongTrong = soLuongChoTrongTrong;
	}

	public ChuyenTau(String maChuyenTau, int soLuongChoDaBan, int soLuongChoDaDat, int soLuongChoChangDaiHon, int soLuongChoTrongTrong, ChiTietChuyenTau thongTinGaTauDi, ChiTietChuyenTau thongTinGaTauDen) {
		this.maChuyenTau = maChuyenTau;
		this.soLuongChoDaBan = soLuongChoDaBan;
		this.soLuongChoDaDat = soLuongChoDaDat;
		this.soLuongChoChangDaiHon = soLuongChoChangDaiHon;
		this.soLuongChoTrongTrong = soLuongChoTrongTrong;
		this.thongTinGaTauDi = thongTinGaTauDi;
		this.thongTinGaTauDen = thongTinGaTauDen;
	}

	public ChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}
}