package DTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private LocalDateTime thoiGianLap;
	private double tongTien;
	private double tongTienDaDatCoc;
	private double tongTienKhachHangTra ;
	private TrangThaiHoaDon trangThaiHoaDon ;
	private LoaiHoaDon loaiHoaDon;
	private KhachHang khachHangMua;
	private CaLamViec caLamViec;

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

	public KhachHang getKhachHangMua() {
		return khachHangMua;
	}

	public void setKhachHangMua(KhachHang khachHangMua) {
		this.khachHangMua = khachHangMua;
	}

	public CaLamViec getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(CaLamViec caLamViec) {
		this.caLamViec = caLamViec;
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

	public HoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public HoaDon(String maHoaDon, LocalDateTime thoiGianLap, double tongTien, double tongTienDaDatCoc, double tongTienKhachHangTra, TrangThaiHoaDon trangThaiHoaDon, LoaiHoaDon loaiHoaDon, KhachHang khachHangMua, CaLamViec caLamViec) {
		this.maHoaDon = maHoaDon;
		this.thoiGianLap = thoiGianLap;
		this.tongTien = tongTien;
		this.tongTienDaDatCoc = tongTienDaDatCoc;
		this.tongTienKhachHangTra = tongTienKhachHangTra;
		this.trangThaiHoaDon = trangThaiHoaDon;
		this.loaiHoaDon = loaiHoaDon;
		this.khachHangMua = khachHangMua;
		this.caLamViec = caLamViec;
	}

	public HoaDon() {
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
				", khachHangMua=" + khachHangMua +
				", caLamViec=" + caLamViec +
				'}';
	}
}
