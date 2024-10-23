package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class LichSuDoiVe {
	private String maLichSuDoi;
	private LocalDateTime thoiGianDoi;
	private double lePhi;
	private double soTienThu;
	private Ve veCu;
	private Ve veMoi;
	private CaLamViec caLamViec;

	public LichSuDoiVe(String maLichSuDoi) {
		this.maLichSuDoi = maLichSuDoi;
	}

	public LichSuDoiVe() {
	}

	public LichSuDoiVe(String maLichSuDoi, LocalDateTime thoiGianDoi, double lePhi, double soTienThu, Ve veCu, Ve veMoi, CaLamViec caLamViec) {
		this.maLichSuDoi = maLichSuDoi;
		this.thoiGianDoi = thoiGianDoi;
		this.lePhi = lePhi;
		this.soTienThu = soTienThu;
		this.veCu = veCu;
		this.veMoi = veMoi;
		this.caLamViec = caLamViec;
	}

	public String getMaLichSuDoi() {
		return maLichSuDoi;
	}

	public void setMaLichSuDoi(String maLichSuDoi) {
		this.maLichSuDoi = maLichSuDoi;
	}

	public LocalDateTime getThoiGianDoi() {
		return thoiGianDoi;
	}

	public void setThoiGianDoi(LocalDateTime thoiGianDoi) {
		this.thoiGianDoi = thoiGianDoi;
	}

	public double getLePhi() {
		return lePhi;
	}

	public void setLePhi(double lePhi) {
		this.lePhi = lePhi;
	}

	public double getSoTienThu() {
		return soTienThu;
	}

	public void setSoTienThu(double soTienThu) {
		this.soTienThu = soTienThu;
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
		LichSuDoiVe that = (LichSuDoiVe) o;
		return Double.compare(lePhi, that.lePhi) == 0 && Double.compare(soTienThu, that.soTienThu) == 0 && Objects.equals(maLichSuDoi, that.maLichSuDoi) && Objects.equals(thoiGianDoi, that.thoiGianDoi) && Objects.equals(veCu, that.veCu) && Objects.equals(veMoi, that.veMoi) && Objects.equals(caLamViec, that.caLamViec);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maLichSuDoi);
	}

	@Override
	public String toString() {
		return "LichSuDoiVe{" +
				"maLichSuDoi='" + maLichSuDoi + '\'' +
				", thoiGianDoi=" + thoiGianDoi +
				", lePhi=" + lePhi +
				", soTienThu=" + soTienThu +
				", veCu=" + veCu +
				", veMoi=" + veMoi +
				", caLamViec=" + caLamViec +
				'}';
	}
}
