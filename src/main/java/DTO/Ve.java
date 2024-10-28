package DTO;

import java.util.Objects;

public class Ve {
	private String maVe;
	private HoaDon hoaDon;
	private ChiTietChuyenTau thongTinGaTauDi;
	private ChiTietChuyenTau thongTinGaTauDen;
	private double tongTienVe;
	private double giamGiaVeTapThe;
	private LoaiVe loaiVe ;
	private TrangThaiVe trangThaiVe ;
	public Ve() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ve(String maVe) {
		this.maVe = maVe;
	}


	public Ve(String maVe, HoaDon hoaDon, ChiTietChuyenTau thongTinGaTauDi, ChiTietChuyenTau thongTinGaTauDen, double tongTienVe, double giamGiaVeTapThe, LoaiVe loaiVe, TrangThaiVe trangThaiVe) {
		this.maVe = maVe;
		this.hoaDon = hoaDon;
		this.thongTinGaTauDi = thongTinGaTauDi;
		this.thongTinGaTauDen = thongTinGaTauDen;
		this.tongTienVe = tongTienVe;
		this.giamGiaVeTapThe = giamGiaVeTapThe;
		this.loaiVe = loaiVe;
		this.trangThaiVe = trangThaiVe;
	}

	public String getMaVe() {
		return maVe;
	}

	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	public double getTongTienVe() {
		return tongTienVe;
	}

	public void setTongTienVe(double tongTienVe) {
		this.tongTienVe = tongTienVe;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}


	public ChiTietChuyenTau getThongTinGaTauDi() {
		return thongTinGaTauDi;
	}

	public void setThongTinGaTauDi(ChiTietChuyenTau thongTinGaTauDi) {
		this.thongTinGaTauDi = thongTinGaTauDi;
	}

	public ChiTietChuyenTau getThongTinGaTauDen() {
		return thongTinGaTauDen;
	}

	public void setThongTinGaTauDen(ChiTietChuyenTau thongTinGaTauDen) {
		this.thongTinGaTauDen = thongTinGaTauDen;
	}

	public double getGiamGiaVeTapThe() {
		return giamGiaVeTapThe;
	}

	public void setGiamGiaVeTapThe(double giamGiaVeTapThe) {
		this.giamGiaVeTapThe = giamGiaVeTapThe;
	}

	public LoaiVe getLoaiVe() {
		return loaiVe;
	}

	public void setLoaiVe(LoaiVe loaiVe) {
		this.loaiVe = loaiVe;
	}

	public TrangThaiVe getTrangThaiVe() {
		return trangThaiVe;
	}

	public void setTrangThaiVe(TrangThaiVe trangThaiVe) {
		this.trangThaiVe = trangThaiVe;
	}

	public Ve(ChiTietChuyenTau thongTinGaTauDi, ChiTietChuyenTau thongTinGaTauDen) {
		this.thongTinGaTauDi = thongTinGaTauDi;
		this.thongTinGaTauDen = thongTinGaTauDen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ve ve = (Ve) o;
		return Objects.equals(maVe, ve.maVe);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maVe);
	}

	@Override
	public String toString() {
		return "Ve{" +
				"maVe='" + maVe + '\'' +
				", hoaDon=" + hoaDon +
				", thongTinGaTauDi=" + thongTinGaTauDi +
				", thongTinGaTauDen=" + thongTinGaTauDen +
				", tongTienVe=" + tongTienVe +
				", giamGiaVeTapThe=" + giamGiaVeTapThe +
				", loaiVe=" + loaiVe +
				", trangThaiVe=" + trangThaiVe +
				'}';
	}
}
