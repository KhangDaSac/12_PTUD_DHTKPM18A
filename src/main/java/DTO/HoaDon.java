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
	private CaLamViec caLamViec;
	private KhachHang khachHang;
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public HoaDon(String maHoaDon, LocalDateTime thoiGianLap, double tongTien, double tongTienDaDatCoc,
				  double tongTienKhachHangTra, LoaiHoaDon loaiHoaDon, TrangThaiHoaDon trangThaiHoaDon,
				  CaLamViec caLamViec, KhachHang khachHang) {
		this.maHoaDon = maHoaDon;
		this.thoiGianLap = thoiGianLap;
		this.tongTien = tongTien;
		this.tongTienDaDatCoc = tongTienDaDatCoc;
		this.tongTienKhachHangTra = tongTienKhachHangTra;
		this.loaiHoaDon = loaiHoaDon;
		this.trangThaiHoaDon = trangThaiHoaDon;
		this.caLamViec = caLamViec;
		this.khachHang = khachHang;
	}

	public HoaDon(String maHoaDon, KhachHang khachHang) {
		this.maHoaDon=maHoaDon;
		this.khachHang=khachHang;
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

	public CaLamViec getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(CaLamViec caLamViec) {
		this.caLamViec = caLamViec;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
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
				", caLamViec=" + caLamViec +
				", khachHang=" + khachHang +
				'}';
	}
}
