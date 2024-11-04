package DTO;

import java.util.Objects;

public class Ve {
	private String maVe;
	private double tongTienVe;
	private HoaDon hoaDon;
	private ChuyenTau chuyenTau;
	private GaTau gaTauDi;
	private GaTau gaTauDen;
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

	public Ve(String maVe, HoaDon hoaDon, ChuyenTau chuyenTau, GaTau gaTauDi, GaTau gaTauDen, double giamGiaVeTapThe, double tongTienVe, LoaiVe loaiVe, TrangThaiVe trangThaiVe) {
		this.maVe = maVe;
		this.hoaDon = hoaDon;
		this.chuyenTau = chuyenTau;
		this.gaTauDi = gaTauDi;
		this.gaTauDen = gaTauDen;
		this.giamGiaVeTapThe = giamGiaVeTapThe;
		this.tongTienVe = tongTienVe;
		this.loaiVe = loaiVe;
		this.trangThaiVe = trangThaiVe;
	}

	public Ve(ChuyenTau maChuyenTau, GaTau maGaDi, GaTau maGaDen) {
	}

	public String getMaVe() {
		return maVe;
	}

	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public ChuyenTau getChuyenTau() {
		return chuyenTau;
	}

	public void setChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	public GaTau getGaTauDi() {
		return gaTauDi;
	}

	public void setGaTauDi(GaTau gaTauDi) {
		this.gaTauDi = gaTauDi;
	}

	public GaTau getGaTauDen() {
		return gaTauDen;
	}

	public void setGaTauDen(GaTau gaTauDen) {
		this.gaTauDen = gaTauDen;
	}

	public double getGiamGiaVeTapThe() {
		return giamGiaVeTapThe;
	}

	public void setGiamGiaVeTapThe(double giamGiaVeTapThe) {
		this.giamGiaVeTapThe = giamGiaVeTapThe;
	}

	public double getTongTienVe() {
		return tongTienVe;
	}

	public void setTongTienVe(double tongTienVe) {
		this.tongTienVe = tongTienVe;
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
				", chuyenTau=" + chuyenTau +
				", gaTauDi=" + gaTauDi +
				", gaTauDen=" + gaTauDen +
				", giamGiaVeTapThe=" + giamGiaVeTapThe +
				", tongTienVe=" + tongTienVe +
				", loaiVe=" + loaiVe +
				", trangThaiVe=" + trangThaiVe +
				'}';
	}
}
