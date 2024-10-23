package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChiTietChuyenTau {
	private ChuyenTau chuyenTau;
	private GaTau daTau;
	private LocalDateTime thoiGianDen;
	private LocalDateTime thoiGianDi;
	private double soKm;
	private int  thuTuGa;

	public ChiTietChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	public ChiTietChuyenTau(ChuyenTau chuyenTau, GaTau daTau, LocalDateTime thoiGianDen, LocalDateTime thoiGianDi, double soKm, int thuTuGa) {
		this.chuyenTau = chuyenTau;
		this.daTau = daTau;
		this.thoiGianDen = thoiGianDen;
		this.thoiGianDi = thoiGianDi;
		this.soKm = soKm;
		this.thuTuGa = thuTuGa;
	}

	public ChiTietChuyenTau(ChuyenTau chuyenTau, GaTau gaTau, LocalDateTime thoiGianDen, LocalDateTime thoiGianDi, int thuTuGa, double soKm) {
	}

	public ChuyenTau getChuyenTau() {
		return chuyenTau;
	}

	public void setChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	public GaTau getDaTau() {
		return daTau;
	}

	public void setDaTau(GaTau daTau) {
		this.daTau = daTau;
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
		return Objects.equals(chuyenTau, that.chuyenTau) && Objects.equals(daTau, that.daTau);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chuyenTau, daTau);
	}

	@Override
	public String toString() {
		return "ChiTietChuyenTau{" +
				"chuyenTau=" + chuyenTau +
				", daTau=" + daTau +
				", thoiGianDen=" + thoiGianDen +
				", thoiGianDi=" + thoiGianDi +
				", soKm=" + soKm +
				", thuTuGa=" + thuTuGa +
				'}';
	}
}
