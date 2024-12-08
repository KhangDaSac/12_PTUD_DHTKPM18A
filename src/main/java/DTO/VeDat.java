package DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class VeDat {
	private String maVeDat;
	private HoaDonDatVe hoaDonDatVe;
	private ChiTietChuyenTau thongTinGaTauDi;
	private ChiTietChuyenTau thongTinGaTauDen;
	private TrangThaiVeDat trangThaiVeDat;
	private LoaiVe loaiVe;
	private double phanTramGiamGiaVeTapThe;
	private double phanTramDatCoc;

	private ArrayList<ChiTietVeDat> danhSachChiTietVeDat;

	private final double PHANTRAMDATCOC = 0.2;
	private final double PHANTRAMGIAMGIAVETAPTHE = 0.1;

	public String getMaVeDat() {
		return maVeDat;
	}

	public void setMaVeDat(String maVeDat) {
		this.maVeDat = maVeDat;
	}

	public HoaDonDatVe getHoaDonDatVe() {
		return hoaDonDatVe;
	}

	public void setHoaDonDatVe(HoaDonDatVe hoaDonDatVe) {
		this.hoaDonDatVe = hoaDonDatVe;
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

	public TrangThaiVeDat getTrangThaiVeDat() {
		return trangThaiVeDat;
	}

	public void setTrangThaiVeDat(TrangThaiVeDat trangThaiVeDat) {
		this.trangThaiVeDat = trangThaiVeDat;
	}

	public LoaiVe getLoaiVe() {
		return loaiVe;
	}

	public void setLoaiVe(LoaiVe loaiVe) {
		this.loaiVe = loaiVe;
	}


	public double getPhanTramGiamGiaVeTapThe() {
		return phanTramGiamGiaVeTapThe;
	}

	public void setPhanTramGiamGiaVeTapThe(double phanTramGiamGiaVeTapThe) {
		this.phanTramGiamGiaVeTapThe = phanTramGiamGiaVeTapThe;
	}

	public double getPhanTramDatCoc() {
		return phanTramDatCoc;
	}

	public void setPhanTramDatCoc(double phanTramDatCoc) {
		this.phanTramDatCoc = phanTramDatCoc;
	}

	public ArrayList<ChiTietVeDat> getDanhSachChiTietVeDat() {
		return danhSachChiTietVeDat;
	}

	public void setDanhSachChiTietVeDat(ArrayList<ChiTietVeDat> danhSachChiTietVeDat) {
		this.danhSachChiTietVeDat = danhSachChiTietVeDat;
	}
	public void addChiTietVeDat(ChiTietVeDat chiTietVeDat){
		this.danhSachChiTietVeDat.add(chiTietVeDat);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VeDat veDat = (VeDat) o;
		return Objects.equals(maVeDat, veDat.maVeDat);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maVeDat);
	}

	public VeDat(String maVeDat, HoaDonDatVe hoaDonDatVe, ChiTietChuyenTau thongTinGaTauDi, ChiTietChuyenTau thongTinGaTauDen, TrangThaiVeDat trangThaiVeDat, LoaiVe loaiVe) {
		this.maVeDat = maVeDat;
		this.hoaDonDatVe = hoaDonDatVe;
		this.thongTinGaTauDi = thongTinGaTauDi;
		this.thongTinGaTauDen = thongTinGaTauDen;
		this.trangThaiVeDat = trangThaiVeDat;
		this.loaiVe = loaiVe;
		if(loaiVe.equals(LoaiVe.VETAPTHE)){
			this.phanTramGiamGiaVeTapThe = PHANTRAMGIAMGIAVETAPTHE;
		}else{
			this.phanTramGiamGiaVeTapThe = 0;
		}
		this.phanTramDatCoc = PHANTRAMDATCOC;
		this.danhSachChiTietVeDat = new ArrayList<>();
	}

	public VeDat(String maVeDat) {
		this.maVeDat = maVeDat;
		this.danhSachChiTietVeDat = new ArrayList<>();
	}

	public double tienVe(){
		double tienVe = 0;
		for (ChiTietVeDat chiTietVeDat : danhSachChiTietVeDat){
			tienVe += chiTietVeDat.thanhTienChiTietVeDat();
		}
		return tienVe;
	}


	public double giamGiaVeTapThe(){
		return tienVe() * phanTramGiamGiaVeTapThe;
	}

	public double tienVeCuoi(){
		return tienVe() * (1 - phanTramGiamGiaVeTapThe);
	}

	public double tienDatCoc(){
		return tienVe() * phanTramDatCoc;
	}
}
