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
	private ChiTietChuyenTau chiTietChuyenTauDi;
	private ChiTietChuyenTau chiTietChuyenTauDen;
	public PhieuDatVe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhieuDatVe(String maPhieuDatVe) {
		this.maPhieuDatVe = maPhieuDatVe;
	}


	public ChiTietChuyenTau getChiTietChuyenTauDi() {
		return chiTietChuyenTauDi;
	}

	public void setChiTietChuyenTauDi(ChiTietChuyenTau chiTietChuyenTauDi) {
		this.chiTietChuyenTauDi = chiTietChuyenTauDi;
	}

	public ChiTietChuyenTau getChiTietChuyenTauDen() {
		return chiTietChuyenTauDen;
	}

	public void setChiTietChuyenTauDen(ChiTietChuyenTau chiTietChuyenTauDen) {
		this.chiTietChuyenTauDen = chiTietChuyenTauDen;
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

	public PhieuDatVe(String maPhieuDatVe, HoaDon hoaDon, ChiTietChuyenTau chiTietChuyenTauDi, ChiTietChuyenTau chiTietChuyenTauDen, double giamGiaVeTapThe, double tongTienVe, double tongTienDatCoc, TrangThaiPhieuDatVe trangThaiPhieuDatVe, LoaiPhieuDatVe loaiPhieuDatVe) {
		this.maPhieuDatVe = maPhieuDatVe;
		this.hoaDon = hoaDon;
		this.chiTietChuyenTauDi = chiTietChuyenTauDi;
		this.chiTietChuyenTauDen = chiTietChuyenTauDen;
		this.giamGiaVeTapThe = giamGiaVeTapThe;
		this.tongTienVe = tongTienVe;
		this.tongTienDatCoc = tongTienDatCoc;
		this.trangThaiPhieuDatVe = trangThaiPhieuDatVe;
		this.loaiPhieuDatVe = loaiPhieuDatVe;
	}

	public PhieuDatVe(String maPhieuDatVe, LoaiPhieuDatVe loaiPhieuDatVe) {
		this.maPhieuDatVe = maPhieuDatVe;
		this.loaiPhieuDatVe = loaiPhieuDatVe;
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
				", chiTietChuyenTauDi=" + chiTietChuyenTauDi +
				", chiTietChuyenTauDen=" + chiTietChuyenTauDen +
				'}';
	}

	public double tinhGiamGiaVeTapThe(){
		if(loaiPhieuDatVe == LoaiPhieuDatVe.PHIEUDATTAPTHE){
			giamGiaVeTapThe = tongTienVe * 0.1;
		}else if(loaiPhieuDatVe == LoaiPhieuDatVe.PHIEUDATCANHAN){
			giamGiaVeTapThe = 0;
		}
		return giamGiaVeTapThe;
	}

	public double tinhTongTienVeCuoi(){
		return tongTienVe - tinhGiamGiaVeTapThe();
	}

	public double tinhTienCoc(){
		return tinhTongTienVeCuoi() * 0.2;
	}

}
