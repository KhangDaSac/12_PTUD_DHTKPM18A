package ENTITY;

import java.util.Objects;

public class Ve {
	private String maVe;
	private double tongGiaVe;
	private HoaDon hoaDon;
	private ChuyenTau chuyenTau;
	private GaTau gaTau;
	public Ve() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ve(String maVe, double tongGiaVe, HoaDon hoaDon, ChuyenTau chuyenTau, GaTau gaTau) {
		super();
		this.maVe = maVe;
		this.tongGiaVe = tongGiaVe;
		this.hoaDon = hoaDon;
		this.chuyenTau = chuyenTau;
		this.gaTau = gaTau;
	}
	public Ve(String maVe) {
		super();
		this.maVe = maVe;
	}
	public String getMaVe() {
		return maVe;
	}
	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}
	public double getTongGiaVe() {
		return tongGiaVe;
	}
	public void setTongGiaVe(double tongGiaVe) {
		this.tongGiaVe = tongGiaVe;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(maVe);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ve other = (Ve) obj;
		return Objects.equals(maVe, other.maVe);
	}
	@Override
	public String toString() {
		return "Ve [maVe=" + maVe + ", tongGiaVe=" + tongGiaVe + ", hoaDon=" + hoaDon + ", chuyenTau=" + chuyenTau
				+ ", gaTau=" + gaTau + "]";
	}
	
}
