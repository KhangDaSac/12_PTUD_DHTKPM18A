package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChiTietChuyenTau {
	private ChuyenTau chuyenTau;
	private GaTau gaTau;
	private LocalDateTime thoiGianDen;
	private LocalDateTime thoiGianDi;
	private double soKm;
	private int  thuTuGa;

	public ChiTietChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	public ChiTietChuyenTau(ChuyenTau chuyenTau, GaTau gaTau, LocalDateTime thoiGianDen, LocalDateTime thoiGianDi, int thuTuGa, double soKm) {
		this.chuyenTau = chuyenTau;
		this.gaTau = gaTau;
		this.thoiGianDen = thoiGianDen;
		this.thoiGianDi = thoiGianDi;
		this.soKm = soKm;
		this.thuTuGa = thuTuGa;
	}



	public ChiTietChuyenTau(ChuyenTau chuyenTau, GaTau gaTau) {
		this.chuyenTau = chuyenTau;
		this.gaTau = gaTau;
	}

	public ChiTietChuyenTau(ChuyenTau chuyenTau, GaTau gaTau, LocalDateTime thoiGianDi) {
		this.chuyenTau = chuyenTau;
		this.gaTau = gaTau;
		this.thoiGianDi = thoiGianDi;
	}

	public ChiTietChuyenTau() {

	}

	public ChiTietChuyenTau(String thoiGianDi) {
	}

	public ChuyenTau getChuyenTau() {
		return chuyenTau;
	}

	public void setChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	public GaTau getGaTau() {
		return gaTau;
	}

	public void setGaTau(GaTau gaTau) {
		this.gaTau = gaTau;
	}

	public LocalDateTime getThoiGianDen() {
		return thoiGianDen;
	}

	public void setThoiGianDen(LocalDateTime thoiGianDen) {
		this.thoiGianDen = thoiGianDen;
	}

	public LocalDateTime getThoiGianDi() {
		return thoiGianDi;
	}

	public void setThoiGianDi(LocalDateTime thoiGianDi) {
		this.thoiGianDi = thoiGianDi;
	}

	public double getSoKm() {
		return soKm;
	}

	public void setSoKm(double soKm) {
		this.soKm = soKm;
	}

	public int getThuTuGa() {
		return thuTuGa;
	}

	public void setThuTuGa(int thuTuGa) {
		this.thuTuGa = thuTuGa;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChiTietChuyenTau that = (ChiTietChuyenTau) o;
		return Objects.equals(chuyenTau, that.chuyenTau) && Objects.equals(gaTau, that.gaTau);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chuyenTau, gaTau);
	}

	@Override
	public String toString() {
		return "ChiTietChuyenTau{" +
				"chuyenTau=" + chuyenTau +
				", gaTau=" + gaTau +
				", thoiGianDen=" + thoiGianDen +
				", thoiGianDi=" + thoiGianDi +
				", soKm=" + soKm +
				", thuTuGa=" + thuTuGa +
				'}';
	}
}
