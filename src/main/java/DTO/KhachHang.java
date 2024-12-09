package DTO;

import java.time.LocalDate;
import java.util.Objects;

public class KhachHang {
	private String maKhachHang;
	private String CCCD;
	private String tenKhachHang;
	private String soDienThoai;
	private LocalDate ngaySinh;
	private LoaiKhachHang loaiKhachHang;
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhachHang(String maKhachHang, String cCCD, String tenKhachHang, String soDienThoai,
			LoaiKhachHang loaiKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
		this.CCCD = cCCD;
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
		this.loaiKhachHang = loaiKhachHang;
	}

	public KhachHang(String maKhachHang, String tenKhachHang, LoaiKhachHang loaiKhachHang, String CCCD) {
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.loaiKhachHang = loaiKhachHang;
		this.CCCD = CCCD;
	}

	public KhachHang(String maKhachHang, String CCCD, String tenKhachHang, LoaiKhachHang loaiKhachHang) {
		this.maKhachHang = maKhachHang;
		this.CCCD = CCCD;
		this.tenKhachHang = tenKhachHang;
		this.loaiKhachHang = loaiKhachHang;
	}

	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}

    public KhachHang(String maKhachHang, String tenKhachHang, String soDienThoai, String maLoaiKhachHang) {
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

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
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

	public KhachHang(String maKhachHang, String CCCD, String tenKhachHang, String soDienThoai, LocalDate ngaySinh, LoaiKhachHang loaiKhachHang) {
		this.maKhachHang = maKhachHang;
		this.CCCD = CCCD;
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.loaiKhachHang = loaiKhachHang;
	}

	public KhachHang(String maKhachHang, String tenKhachHang) {
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
	}



	@Override
	public String toString() {
		return "KhachHang{" +
				"maKhachHang='" + maKhachHang + '\'' +
				", CCCD='" + CCCD + '\'' +
				", tenKhachHang='" + tenKhachHang + '\'' +
				", soDienThoai='" + soDienThoai + '\'' +
				", ngaySinh=" + ngaySinh +
				", loaiKhachHang=" + loaiKhachHang +
				'}';
	}
}
