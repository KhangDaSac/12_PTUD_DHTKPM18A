package DTO;

import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String CCCD;
	private String tenNhanVien;
	private String diaChi;
	private String soDienThoai ;
	private LoaiNhanVien loaiNhanVien;
	private TrangThaiNhanVien trangThaiNhanVien;
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public NhanVien(String maNhanVien, String CCCD, String tenNhanVien, String diaChi, String soDienThoai, LoaiNhanVien loaiNhanVien, TrangThaiNhanVien trangThaiNhanVien) {
		this.maNhanVien = maNhanVien;
		this.CCCD = CCCD;
		this.tenNhanVien = tenNhanVien;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.loaiNhanVien = loaiNhanVien;
		this.trangThaiNhanVien = trangThaiNhanVien;
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

	public void setCCCD(String CCCD) {
		this.CCCD = CCCD;
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

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NhanVien nhanVien = (NhanVien) o;
		return Objects.equals(maNhanVien, nhanVien.maNhanVien);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maNhanVien);
	}

	@Override
	public String toString() {
		return "NhanVien{" +
				"maNhanVien='" + maNhanVien + '\'' +
				", CCCD='" + CCCD + '\'' +
				", tenNhanVien='" + tenNhanVien + '\'' +
				", diaChi='" + diaChi + '\'' +
				", soDienThoai='" + soDienThoai + '\'' +
				", loaiNhanVien=" + loaiNhanVien +
				", trangThaiNhanVien=" + trangThaiNhanVien +
				'}';
	}
}
