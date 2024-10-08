package ENTITY;

import java.util.Objects;

public class LoaiKhachHang {
	private String maLoaiKhachHang;
	private String tenLoaiKhachHang;
	private double phanTramGiamGia;
	public LoaiKhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiKhachHang(String maLoaiKhachHang, String tenLoaiKhachHang, double phanTramGiamGia) {
		super();
		this.maLoaiKhachHang = maLoaiKhachHang;
		this.tenLoaiKhachHang = tenLoaiKhachHang;
		this.phanTramGiamGia = phanTramGiamGia;
	}
	public LoaiKhachHang(String maLoaiKhachHang) {
		super();
		this.maLoaiKhachHang = maLoaiKhachHang;
	}
	public String getMaLoaiKhachHang() {
		return maLoaiKhachHang;
	}
	public void setMaLoaiKhachHang(String maLoaiKhachHang) {
		this.maLoaiKhachHang = maLoaiKhachHang;
	}
	public String getTenLoaiKhachHang() {
		return tenLoaiKhachHang;
	}
	public void setTenLoaiKhachHang(String tenLoaiKhachHang) {
		this.tenLoaiKhachHang = tenLoaiKhachHang;
	}
	public double getPhanTramGiamGia() {
		return phanTramGiamGia;
	}
	public void setPhanTramGiamGia(double phanTramGiamGia) {
		this.phanTramGiamGia = phanTramGiamGia;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maLoaiKhachHang);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiKhachHang other = (LoaiKhachHang) obj;
		return Objects.equals(maLoaiKhachHang, other.maLoaiKhachHang);
	}
	@Override
	public String toString() {
		return "LoaiKhachHang [maLoaiKhachHang=" + maLoaiKhachHang + ", tenLoaiKhachHang=" + tenLoaiKhachHang
				+ ", phanTramGiamGia=" + phanTramGiamGia + "]";
	}
	
}
