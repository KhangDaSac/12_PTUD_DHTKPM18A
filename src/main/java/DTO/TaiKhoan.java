package DTO;

import java.util.Date;
import java.util.Objects;

public class TaiKhoan {
	private NhanVien nhanVien;
	private String matKhau;
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaiKhoan(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public TaiKhoan(NhanVien nhanVien, String matKhau) {
		this.nhanVien = nhanVien;
		this.matKhau = matKhau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TaiKhoan taiKhoan = (TaiKhoan) o;
		return Objects.equals(nhanVien, taiKhoan.nhanVien);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(nhanVien);
	}

	@Override
	public String toString() {
		return "TaiKhoan{" +
				"nhanVien=" + nhanVien +
				", matKhau='" + matKhau + '\'' +
				'}';
	}
}
