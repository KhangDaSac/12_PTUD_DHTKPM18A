package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonDoiVe {
	private String maLichSuDoi;
	private LocalDateTime thoiGianDoiVe;
	private double lePhi;
	private Ve veCu;
	private Ve veMoi;
	private CaLamViec caLamViec;

	private final double LEPHIDOIVE = 20000;

	public String getMaLichSuDoi() {
		return maLichSuDoi;
	}

	public void setMaLichSuDoi(String maLichSuDoi) {
		this.maLichSuDoi = maLichSuDoi;
	}

	public LocalDateTime getThoiGianDoiVe() {
		return thoiGianDoiVe;
	}

	public void setThoiGianDoiVe(LocalDateTime thoiGianDoiVe) {
		this.thoiGianDoiVe = thoiGianDoiVe;
	}

	public double getLePhi() {
		return lePhi;
	}

	public void setLePhi(double lePhi) {
		this.lePhi = lePhi;
	}

	public Ve getVeCu() {
		return veCu;
	}

	public void setVeCu(Ve veCu) {
		this.veCu = veCu;
	}

	public Ve getVeMoi() {
		return veMoi;
	}

	public void setVeMoi(Ve veMoi) {
		this.veMoi = veMoi;
	}

	public CaLamViec getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(CaLamViec caLamViec) {
		this.caLamViec = caLamViec;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HoaDonDoiVe that = (HoaDonDoiVe) o;
		return Objects.equals(maLichSuDoi, that.maLichSuDoi);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maLichSuDoi);
	}

	public HoaDonDoiVe(String maLichSuDoi) {
		this.maLichSuDoi = maLichSuDoi;
	}

	public double tongTienCuoi(){
		return veMoi.tienVeCuoi() - veCu.tienVeCuoi() - lePhi;
	}
}
