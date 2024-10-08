package ENTITY;

import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String CCCD;
	private String tenNhanVien;
	private String diaChi;
	private TaiKhoan taiKhoan;
	private LoaiNhanVien loaiNhanVien;
	private TrangThaiNhanVien trangThaiNhanVien;
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String maNhanVien, String cCCD, String tenNhanVien, String diaChi, TaiKhoan taiKhoan,
			LoaiNhanVien loaiNhanVien, TrangThaiNhanVien trangThaiNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
		CCCD = cCCD;
		this.tenNhanVien = tenNhanVien;
		this.diaChi = diaChi;
		this.taiKhoan = taiKhoan;
		this.loaiNhanVien = loaiNhanVien;
		this.trangThaiNhanVien = trangThaiNhanVien;
	}
	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public LoaiNhanVien getLoaiNhanVien() {
		return loaiNhanVien;
	}
	public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
		this.loaiNhanVien = loaiNhanVien;
	}
	public TrangThaiNhanVien getTrangThaiNhanVien() {
		return trangThaiNhanVien;
	}
	public void setTrangThaiNhanVien(TrangThaiNhanVien trangThaiNhanVien) {
		this.trangThaiNhanVien = trangThaiNhanVien;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", CCCD=" + CCCD + ", tenNhanVien=" + tenNhanVien + ", diaChi="
				+ diaChi + ", taiKhoan=" + taiKhoan + ", loaiNhanVien=" + loaiNhanVien + ", trangThaiNhanVien="
				+ trangThaiNhanVien + "]";
	}
	
}
