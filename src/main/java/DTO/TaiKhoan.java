package ENTITY;

import java.util.Date;
import java.util.Objects;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private Date ngayTao;
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(String tenDangNhap, String matKhau, Date ngayTao) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.ngayTao = ngayTao;
	}
	public TaiKhoan(String tenDangNhap) {
		super();
		this.tenDangNhap = tenDangNhap;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public Date getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(tenDangNhap);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(tenDangNhap, other.tenDangNhap);
	}
	@Override
	public String toString() {
		return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", ngayTao=" + ngayTao + "]";
	}
	
}
