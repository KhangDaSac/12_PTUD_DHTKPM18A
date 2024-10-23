package DTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private LocalDateTime thoiGianLap;
	private double tongTien;
	private double tongTienDaDatCoc ;
	private double tongTienKhachHangTra ;
	private TrangThaiHoaDon trangThaiHoaDon ;
	private LoaiHoaDon loaiHoaDon;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public HoaDon(NhanVien nhanVien, String maHoaDon, LocalDateTime thoiGianLap, double tongTien, double tongTienDaDatCoc, double tongTienKhachHangTra, TrangThaiHoaDon trangThaiHoaDon, LoaiHoaDon loaiHoaDon, KhachHang khachHang) {
		this.nhanVien = nhanVien;
		this.maHoaDon = maHoaDon;
		this.thoiGianLap = thoiGianLap;
		this.tongTien = tongTien;
		this.tongTienDaDatCoc = tongTienDaDatCoc;
		this.tongTienKhachHangTra = tongTienKhachHangTra;
		this.trangThaiHoaDon = trangThaiHoaDon;
		this.loaiHoaDon = loaiHoaDon;
		this.khachHang = khachHang;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDateTime getThoiGianLap() {
		return thoiGianLap;
	}

	public void setThoiGianLap(LocalDateTime thoiGianLap) {
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

	public double getTongTienKhachHangTra() {
		return tongTienKhachHangTra;
	}

	public void setTongTienKhachHangTra(double tongTienKhachHangTra) {
		this.tongTienKhachHangTra = tongTienKhachHangTra;
	}

	public TrangThaiHoaDon getTrangThaiHoaDon() {
		return trangThaiHoaDon;
	}

	public void setTrangThaiHoaDon(TrangThaiHoaDon trangThaiHoaDon) {
		this.trangThaiHoaDon = trangThaiHoaDon;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HoaDon hoaDon = (HoaDon) o;
		return Objects.equals(maHoaDon, hoaDon.maHoaDon);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maHoaDon);
	}

	@Override
	public String toString() {
		return "HoaDon{" +
				"maHoaDon='" + maHoaDon + '\'' +
				", thoiGianLap=" + thoiGianLap +
				", tongTien=" + tongTien +
				", tongTienDaDatCoc=" + tongTienDaDatCoc +
				", tongTienKhachHangTra=" + tongTienKhachHangTra +
				", trangThaiHoaDon=" + trangThaiHoaDon +
				", loaiHoaDon=" + loaiHoaDon +
				", nhanVien=" + nhanVien +
				", khachHang=" + khachHang +
				'}';
	}
}
