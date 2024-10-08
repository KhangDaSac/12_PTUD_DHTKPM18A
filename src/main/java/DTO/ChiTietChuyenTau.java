package ENTITY;

import java.util.Date;

public class ChiTietChuyenTau {
	private ChuyenTau chuyenTau;
	private GaTau daTau;
	private Date thoiGianDen;
	private Date thoiGianDi;
	private double soKm;
	public ChiTietChuyenTau(ChuyenTau chuyenTau, GaTau daTau, Date thoiGianDen, Date thoiGianDi, double soKm) {
		super();
		this.chuyenTau = chuyenTau;
		this.daTau = daTau;
		this.thoiGianDen = thoiGianDen;
		this.thoiGianDi = thoiGianDi;
		this.soKm = soKm;
	}
	public ChiTietChuyenTau() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiTietChuyenTau(ChuyenTau chuyenTau) {
		super();
		this.chuyenTau = chuyenTau;
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
	public Date getThoiGianDen() {
		return thoiGianDen;
	}
	public void setThoiGianDen(Date thoiGianDen) {
		this.thoiGianDen = thoiGianDen;
	}
	public Date getThoiGianDi() {
		return thoiGianDi;
	}
	public void setThoiGianDi(Date thoiGianDi) {
		this.thoiGianDi = thoiGianDi;
	}
	public double getSoKm() {
		return soKm;
	}
	public void setSoKm(double soKm) {
		this.soKm = soKm;
	}
	
	@Override
	public String toString() {
		return "ChiTietChuyenTau [chuyenTau=" + chuyenTau + ", daTau=" + daTau + ", thoiGianDen=" + thoiGianDen
				+ ", thoiGianDi=" + thoiGianDi + ", soKm=" + soKm + "]";
	}
	
}
