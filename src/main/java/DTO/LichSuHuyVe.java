package DTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class LichSuHuyVe {
	private String maLichSuHuy;
	private LocalDateTime thoiGianHuy;
	private double lePhi;
	private double soTienHoanTra;
	private Ve ve;
	private CaLamViec caLamViec;
	public LichSuHuyVe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LichSuHuyVe(String maLichSuHuy) {
		this.maLichSuHuy = maLichSuHuy;
	}

	public LichSuHuyVe(String maLichSuHuy, LocalDateTime thoiGianHuy, double lePhi, double soTienHoanTra, Ve ve, CaLamViec caLamViec) {
		this.maLichSuHuy = maLichSuHuy;
		this.thoiGianHuy = thoiGianHuy;
		this.lePhi = lePhi;
		this.soTienHoanTra = soTienHoanTra;
		this.ve = ve;
		this.caLamViec = caLamViec;
	}

	public String getMaLichSuHuy() {
		return maLichSuHuy;
	}

	public void setMaLichSuHuy(String maLichSuHuy) {
		this.maLichSuHuy = maLichSuHuy;
	}

	public LocalDateTime getThoiGianHuy() {
		return thoiGianHuy;
	}

	public void setThoiGianHuy(LocalDateTime thoiGianHuy) {
		this.thoiGianHuy = thoiGianHuy;
	}

	public double getLePhi() {
		return lePhi;
	}

	public void setLePhi(double lePhi) {
		this.lePhi = lePhi;
	}

	public double getSoTienHoanTra() {
		return soTienHoanTra;
	}

	public void setSoTienHoanTra(double soTienHoanTra) {
		this.soTienHoanTra = soTienHoanTra;
	}

	public Ve getVe() {
		return ve;
	}

	public void setVe(Ve ve) {
		this.ve = ve;
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
		LichSuHuyVe that = (LichSuHuyVe) o;
		return Double.compare(lePhi, that.lePhi) == 0 && Double.compare(soTienHoanTra, that.soTienHoanTra) == 0 && Objects.equals(maLichSuHuy, that.maLichSuHuy) && Objects.equals(thoiGianHuy, that.thoiGianHuy) && Objects.equals(ve, that.ve) && Objects.equals(caLamViec, that.caLamViec);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maLichSuHuy);
	}

	@Override
	public String toString() {
		return "LichSuHuyVe{" +
				"maLichSuHuy='" + maLichSuHuy + '\'' +
				", thoiGianHuy=" + thoiGianHuy +
				", lePhi=" + lePhi +
				", soTienHoanTra=" + soTienHoanTra +
				", ve=" + ve +
				", caLamViec=" + caLamViec +
				'}';
	}
}
