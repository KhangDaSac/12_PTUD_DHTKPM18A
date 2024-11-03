package DTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class PhieuDatVe {
	private String maPhieuDatVe;
	private double tongTienVe;
	private double tongTienDatCoc;
	private double giamGiaVeTapThe ;
	private LocalDateTime ngayLayVe;
	private TrangThaiPhieuDatVe trangThaiPhieuDatVe;
	private LoaiPhieuDatVe loaiPhieuDatVe;
	private HoaDon hoaDon;
	private ChuyenTau chuyenTau;
	private GaTau gaTauDi;
	private GaTau gaTauDen;
	private HoaDon maHoaDon;
	public PhieuDatVe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhieuDatVe(String maPhieuDatVe, double tongTienVe, double tongTienDatCoc, double giamGiaVeTapThe, LocalDateTime ngayLayVe, TrangThaiPhieuDatVe trangThaiPhieuDatVe, LoaiPhieuDatVe loaiPhieuDatVe, HoaDon hoaDon, ChuyenTau chuyenTau, GaTau gaTauDi, GaTau gaTauDen, HoaDon maHoaDon) {
		this.maPhieuDatVe = maPhieuDatVe;
		this.tongTienVe = tongTienVe;
		this.tongTienDatCoc = tongTienDatCoc;
		this.giamGiaVeTapThe = giamGiaVeTapThe;
		this.ngayLayVe = ngayLayVe;
		this.trangThaiPhieuDatVe = trangThaiPhieuDatVe;
		this.loaiPhieuDatVe = loaiPhieuDatVe;
		this.hoaDon = hoaDon;
		this.chuyenTau = chuyenTau;
		this.gaTauDi = gaTauDi;
		this.gaTauDen = gaTauDen;
		this.maHoaDon = maHoaDon;
	}

	public PhieuDatVe(String maPhieuDatVe) {
		this.maPhieuDatVe = maPhieuDatVe;
	}

	public String getMaPhieuDatVe() {
		return maPhieuDatVe;
	}

	public void setMaPhieuDatVe(String maPhieuDatVe) {
		this.maPhieuDatVe = maPhieuDatVe;
	}

	public double getTongTienVe() {
		return tongTienVe;
	}

	public void setTongTienVe(double tongTienVe) {
		this.tongTienVe = tongTienVe;
	}

	public double getTongTienDatCoc() {
		return tongTienDatCoc;
	}

	public void setTongTienDatCoc(double tongTienDatCoc) {
		this.tongTienDatCoc = tongTienDatCoc;
	}

	public double getGiamGiaVeTapThe() {
		return giamGiaVeTapThe;
	}

	public void setGiamGiaVeTapThe(double giamGiaVeTapThe) {
		this.giamGiaVeTapThe = giamGiaVeTapThe;
	}

	public LocalDateTime getNgayLayVe() {
		return ngayLayVe;
	}

	public void setNgayLayVe(LocalDateTime ngayLayVe) {
		this.ngayLayVe = ngayLayVe;
	}

	public TrangThaiPhieuDatVe getTrangThaiPhieuDatVe() {
		return trangThaiPhieuDatVe;
	}

	public void setTrangThaiPhieuDatVe(TrangThaiPhieuDatVe trangThaiPhieuDatVe) {
		this.trangThaiPhieuDatVe = trangThaiPhieuDatVe;
	}

	public LoaiPhieuDatVe getLoaiPhieuDatVe() {
		return loaiPhieuDatVe;
	}

	public void setLoaiPhieuDatVe(LoaiPhieuDatVe loaiPhieuDatVe) {
		this.loaiPhieuDatVe = loaiPhieuDatVe;
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

	public HoaDon getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(HoaDon maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PhieuDatVe that = (PhieuDatVe) o;
		return Objects.equals(maPhieuDatVe, that.maPhieuDatVe);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maPhieuDatVe);
	}

	@Override
	public String toString() {
		return "PhieuDatVe{" +
				"maPhieuDatVe='" + maPhieuDatVe + '\'' +
				", tongTienVe=" + tongTienVe +
				", tongTienDatCoc=" + tongTienDatCoc +
				", giamGiaVeTapThe=" + giamGiaVeTapThe +
				", ngayLayVe=" + ngayLayVe +
				", trangThaiPhieuDatVe=" + trangThaiPhieuDatVe +
				", loaiPhieuDatVe=" + loaiPhieuDatVe +
				", hoaDon=" + hoaDon +
				", chuyenTau=" + chuyenTau +
				", gaTauDi=" + gaTauDi +
				", gaTauDen=" + gaTauDen +
				", maHoaDon=" + maHoaDon +
				'}';
	}
}
