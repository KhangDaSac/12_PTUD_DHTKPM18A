package ENTITY;

import java.util.Date;
import java.util.Objects;

public class LichSuHuy {
	private String maLichSuHuy;
	private Date thoiGianHuy;
	private double lePhi;
	private double soTienHoanTra;
	private LoaiLichSuHuy loaiLichSuHuy;
	private Ve ve;
	private PhieuDatVe phieuDatVe;
	public LichSuHuy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LichSuHuy(String maLichSuHuy, Date thoiGianHuy, double lePhi, double soTienHoanTra,
			LoaiLichSuHuy loaiLichSuHuy, Ve ve, PhieuDatVe phieuDatVe) {
		super();
		this.maLichSuHuy = maLichSuHuy;
		this.thoiGianHuy = thoiGianHuy;
		this.lePhi = lePhi;
		this.soTienHoanTra = soTienHoanTra;
		this.loaiLichSuHuy = loaiLichSuHuy;
		this.ve = ve;
		this.phieuDatVe = phieuDatVe;
	}
	public LichSuHuy(String maLichSuHuy) {
		super();
		this.maLichSuHuy = maLichSuHuy;
	}
	public String getMaLichSuHuy() {
		return maLichSuHuy;
	}
	public void setMaLichSuHuy(String maLichSuHuy) {
		this.maLichSuHuy = maLichSuHuy;
	}
	public Date getThoiGianHuy() {
		return thoiGianHuy;
	}
	public void setThoiGianHuy(Date thoiGianHuy) {
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
	public LoaiLichSuHuy getLoaiLichSuHuy() {
		return loaiLichSuHuy;
	}
	public void setLoaiLichSuHuy(LoaiLichSuHuy loaiLichSuHuy) {
		this.loaiLichSuHuy = loaiLichSuHuy;
	}
	public Ve getVe() {
		return ve;
	}
	public void setVe(Ve ve) {
		this.ve = ve;
	}
	public PhieuDatVe getPhieuDatVe() {
		return phieuDatVe;
	}
	public void setPhieuDatVe(PhieuDatVe phieuDatVe) {
		this.phieuDatVe = phieuDatVe;
	}
	@Override
	public String toString() {
		return "LichSuHuy [maLichSuHuy=" + maLichSuHuy + ", thoiGianHuy=" + thoiGianHuy + ", lePhi=" + lePhi
				+ ", soTienHoanTra=" + soTienHoanTra + ", loaiLichSuHuy=" + loaiLichSuHuy + ", ve=" + ve
				+ ", phieuDatVe=" + phieuDatVe + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLichSuHuy);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LichSuHuy other = (LichSuHuy) obj;
		return Objects.equals(maLichSuHuy, other.maLichSuHuy);
	}
	
}
