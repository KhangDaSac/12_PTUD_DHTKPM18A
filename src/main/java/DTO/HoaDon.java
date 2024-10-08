package ENTITY;

import java.util.Date;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private Date thoiGianLap;
	private double tongTien;
	private double tongTienDaDatCoc;
	private LoaiHoaDon loaiHoaDon;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HoaDon(String maHoaDon, Date thoiGianLap, double tongTien, double tongTienDaDatCoc, LoaiHoaDon loaiHoaDon,
			NhanVien nhanVien, KhachHang khachHang) {
		super();
		this.maHoaDon = maHoaDon;
		this.thoiGianLap = thoiGianLap;
		this.tongTien = tongTien;
		this.tongTienDaDatCoc = tongTienDaDatCoc;
		this.loaiHoaDon = loaiHoaDon;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
	}
	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public Date getThoiGianLap() {
		return thoiGianLap;
	}
	public void setThoiGianLap(Date thoiGianLap) {
		this.thoiGianLap = thoiGianLap;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public double getTongTienDaDatCoc() {
		return tongTienDaDatCoc;
	}
	public void setTongTienDaDatCoc(double tongTienDaDatCoc) {
		this.tongTienDaDatCoc = tongTienDaDatCoc;
	}
	public LoaiHoaDon getLoaiHoaDon() {
		return loaiHoaDon;
	}
	public void setLoaiHoaDon(LoaiHoaDon loaiHoaDon) {
		this.loaiHoaDon = loaiHoaDon;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", thoiGianLap=" + thoiGianLap + ", tongTien=" + tongTien
				+ ", tongTienDaDatCoc=" + tongTienDaDatCoc + ", loaiHoaDon=" + loaiHoaDon + ", nhanVien=" + nhanVien
				+ ", khachHang=" + khachHang + "]";
	}
}
