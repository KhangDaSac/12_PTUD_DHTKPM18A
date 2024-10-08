package ENTITY;

import java.util.Objects;

public class KhachHang {
	private String maKhachHang;
	private String CCCD;
	private String tenKhachHang;
	private String soDienThoai;
	private LoaiKhachHang loaiKhachHang;
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhachHang(String maKhachHang, String cCCD, String tenKhachHang, String soDienThoai,
			LoaiKhachHang loaiKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
		CCCD = cCCD;
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
		this.loaiKhachHang = loaiKhachHang;
	}
	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public LoaiKhachHang getLoaiKhachHang() {
		return loaiKhachHang;
	}
	public void setLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang, other.maKhachHang);
	}
	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", CCCD=" + CCCD + ", tenKhachHang=" + tenKhachHang
				+ ", soDienThoai=" + soDienThoai + ", loaiKhachHang=" + loaiKhachHang + "]";
	}
	
}
