package DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Ve {
	private String maVe;
	private HoaDonBanVe hoaDonBanVe;
	private ChiTietChuyenTau thongTinGaTauDi;
	private ChiTietChuyenTau thongTinGaTauDen;
	private LoaiVe loaiVe ;
	private TrangThaiVe trangThaiVe;
	private double phanTramGiamGiaVeTapThe;

	private ArrayList<ChiTietVe> danhSachChiTietVe;

	private final double PHANTRAMGIAMGIAVETAPTHE = 0.1;

	public String getMaVe() {
		return maVe;
	}

	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	public HoaDonBanVe getHoaDonBanVe() {
		return hoaDonBanVe;
	}

	public void setHoaDonBanVe(HoaDonBanVe hoaDonBanVe) {
		this.hoaDonBanVe = hoaDonBanVe;
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

	public LoaiVe getLoaiVe() {
		return loaiVe;
	}

	public void setLoaiVe(LoaiVe loaiVe) {
		this.loaiVe = loaiVe;
		if(loaiVe.equals(LoaiVe.VETAPTHE)){
			this.phanTramGiamGiaVeTapThe = 0.1;
		}else if(loaiVe.equals(LoaiVe.VECANHAN)){
			this.phanTramGiamGiaVeTapThe = 0;
		}
	}

	public TrangThaiVe getTrangThaiVe() {
		return trangThaiVe;
	}

	public void setTrangThaiVe(TrangThaiVe trangThaiVe) {
		this.trangThaiVe = trangThaiVe;
	}

	public double getPhanTramGiamGiaVeTapThe() {
		return phanTramGiamGiaVeTapThe;
	}

	public void setPhanTramGiamGiaVeTapThe(double phanTramGiamGiaVeTapThe) {
		this.phanTramGiamGiaVeTapThe = phanTramGiamGiaVeTapThe;
	}

	public ArrayList<ChiTietVe> getDanhSachChiTietVe() {
		return danhSachChiTietVe;
	}

	public void setDanhSachChiTietVe(ArrayList<ChiTietVe> danhSachChiTietVe) {
		this.danhSachChiTietVe = danhSachChiTietVe;
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

	public Ve() {
	}

	public Ve(String maVe, HoaDonBanVe hoaDonBanVe, ChiTietChuyenTau thongTinGaTauDi, ChiTietChuyenTau thongTinGaTauDen, LoaiVe loaiVe, TrangThaiVe trangThaiVe) {
		this.maVe = maVe;
		this.hoaDonBanVe = hoaDonBanVe;
		this.thongTinGaTauDi = thongTinGaTauDi;
		this.thongTinGaTauDen = thongTinGaTauDen;
		this.loaiVe = loaiVe;
		this.trangThaiVe = trangThaiVe;
		if(loaiVe.equals(LoaiVe.VETAPTHE)){
			this.phanTramGiamGiaVeTapThe = PHANTRAMGIAMGIAVETAPTHE;
		}else{
			this.phanTramGiamGiaVeTapThe = 0;
		}
	}

	public Ve(String maVe) {
		this.maVe = maVe;
	}

	public Ve(String maVe, HoaDonBanVe hoaDonBanVe, ChiTietChuyenTau thongTinGaTauDi, ChiTietChuyenTau thongTinGaTauDen) {
		this.maVe = maVe;
		this.hoaDonBanVe = hoaDonBanVe;
		this.thongTinGaTauDi = thongTinGaTauDi;
		this.thongTinGaTauDen = thongTinGaTauDen;
	}

	public double tienVe(){
		double tienVe = 0;
		for (ChiTietVe chiTietVe : danhSachChiTietVe){
			tienVe += chiTietVe.thanhTienChiTietVe();
		}
		return tienVe;
	}

	public double giamGiaVeTapThe(){
		return tienVe() * phanTramGiamGiaVeTapThe;
	}

	public double tienVeCuoi(){
		return tienVe() * (1 - phanTramGiamGiaVeTapThe);
	}

	@Override
	public String toString() {
		return "Ve{" +
				"maVe='" + maVe + '\'' +
				", thongTinGaTauDi=" + thongTinGaTauDi +
				", thongTinGaTauDen=" + thongTinGaTauDen +
				", loaiVe=" + loaiVe +
				", trangThaiVe=" + trangThaiVe +
				", phanTramGiamGiaVeTapThe=" + phanTramGiamGiaVeTapThe +
				", danhSachChiTietVe=" + danhSachChiTietVe +
				", PHANTRAMGIAMGIAVETAPTHE=" + PHANTRAMGIAMGIAVETAPTHE +
				'}';
	}
}