package ENTITY;

import java.util.Date;
import java.util.Objects;

public class LichSuDoi {
	private String maLichSuDoi;
	private Date thoiGianDoi;
	private double lePhi;
	private double soTienThu;
	private Ve veCu;
	private Ve veMoi;
	public LichSuDoi(String maLichSuDoi, Date thoiGianDoi, double lePhi, double soTienThu, Ve veCu, Ve veMoi) {
		super();
		this.maLichSuDoi = maLichSuDoi;
		this.thoiGianDoi = thoiGianDoi;
		this.lePhi = lePhi;
		this.soTienThu = soTienThu;
		this.veCu = veCu;
		this.veMoi = veMoi;
	}
	public LichSuDoi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LichSuDoi(String maLichSuDoi) {
		super();
		this.maLichSuDoi = maLichSuDoi;
	}
	public String getMaLichSuDoi() {
		return maLichSuDoi;
	}
	public void setMaLichSuDoi(String maLichSuDoi) {
		this.maLichSuDoi = maLichSuDoi;
	}
	public Date getThoiGianDoi() {
		return thoiGianDoi;
	}
	public void setThoiGianDoi(Date thoiGianDoi) {
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
	
	@Override
	public int hashCode() {
		return Objects.hash(maLichSuDoi);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LichSuDoi other = (LichSuDoi) obj;
		return Objects.equals(maLichSuDoi, other.maLichSuDoi);
	}
	@Override
	public String toString() {
		return "LichSuDoi [maLichSuDoi=" + maLichSuDoi + ", lePhi=" + lePhi + ", soTienThu=" + soTienThu + ", veCu="
				+ veCu + ", veMoi=" + veMoi + "]";
	}
	
	
}
