package ENTITY;

import java.util.Objects;

public class LoaiCho {
	private String maLoaiCho;
	private String tenLoaiCho;
	private double doDaiChang;
	private double heSoGiaCho;
	public LoaiCho(String maLoaiCho, String tenLoaiCho, double doDaiChang, double heSoGiaCho) {
		super();
		this.maLoaiCho = maLoaiCho;
		this.tenLoaiCho = tenLoaiCho;
		this.doDaiChang = doDaiChang;
		this.heSoGiaCho = heSoGiaCho;
	}
	public LoaiCho() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiCho(String maLoaiCho) {
		super();
		this.maLoaiCho = maLoaiCho;
	}
	public String getMaLoaiCho() {
		return maLoaiCho;
	}
	public void setMaLoaiCho(String maLoaiCho) {
		this.maLoaiCho = maLoaiCho;
	}
	public String getTenLoaiCho() {
		return tenLoaiCho;
	}
	public void setTenLoaiCho(String tenLoaiCho) {
		this.tenLoaiCho = tenLoaiCho;
	}
	public double getDoDaiChang() {
		return doDaiChang;
	}
	public void setDoDaiChang(double doDaiChang) {
		this.doDaiChang = doDaiChang;
	}
	public double getHeSoGiaCho() {
		return heSoGiaCho;
	}
	public void setHeSoGiaCho(double heSoGiaCho) {
		this.heSoGiaCho = heSoGiaCho;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maLoaiCho);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiCho other = (LoaiCho) obj;
		return Objects.equals(maLoaiCho, other.maLoaiCho);
	}
	@Override
	public String toString() {
		return "LoaiCho [maLoaiCho=" + maLoaiCho + ", tenLoaiCho=" + tenLoaiCho + ", doDaiChang=" + doDaiChang
				+ ", heSoGiaCho=" + heSoGiaCho + "]";
	}
	
}
