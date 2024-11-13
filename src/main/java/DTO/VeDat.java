package DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class VeDat {
	private String maVeDat;
	private HoaDonDatVe hoaDonDatVe;
	private ChiTietChuyenTau thongTinGaTauDi;
	private ChiTietChuyenTau thongTinGaTauDen;
	private TrangThaiVeDat trangThaiVeDat;
	private LoaiVe loaiVe;
	private double tienVe;
	private double phanTramGiamGiaVeTapThe;
	private double tienDatCoc;

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

	public double getTienVe() {
		return tienVe;
	}

	public void setTienVe(double tienVe) {
		this.tienVe = tienVe;
	}

	public double getPhanTramGiamGiaVeTapThe() {
		return phanTramGiamGiaVeTapThe;
	}

	public void setPhanTramGiamGiaVeTapThe(double phanTramGiamGiaVeTapThe) {
		this.phanTramGiamGiaVeTapThe = phanTramGiamGiaVeTapThe;
	}

	public double getTienDatCoc() {
		return tienDatCoc;
	}

	public void setTienDatCoc(double tienDatCoc) {
		this.tienDatCoc = tienDatCoc;
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

	public VeDat(String maVeDat, HoaDonDatVe hoaDonDatVe, ChiTietChuyenTau thongTinGaTauDi, ChiTietChuyenTau thongTinGaTauDen, TrangThaiVeDat trangThaiVeDat, LoaiVe loaiVe, double tienVe, double tienDatCoc) {
		this.maVeDat = maVeDat;
		this.hoaDonDatVe = hoaDonDatVe;
		this.thongTinGaTauDi = thongTinGaTauDi;
		this.thongTinGaTauDen = thongTinGaTauDen;
		this.trangThaiVeDat = trangThaiVeDat;
		this.loaiVe = loaiVe;
		this.tienVe = tienVe;
		this.tienDatCoc = tienDatCoc;
		this.phanTramGiamGiaVeTapThe = PHANTRAMGIAMGIAVETAPTHE;
	}

	public VeDat(String maVeDat, HoaDonDatVe hoaDonDatVe, ChiTietChuyenTau thongTinGaTauDi, ChiTietChuyenTau thongTinGaTauDen, TrangThaiVeDat trangThaiVeDat, LoaiVe loaiVe, double tienVe, double phanTramGiamGiaVeTapThe, double tienDatCoc) {
		this.maVeDat = maVeDat;
		this.hoaDonDatVe = hoaDonDatVe;
		this.thongTinGaTauDi = thongTinGaTauDi;
		this.thongTinGaTauDen = thongTinGaTauDen;
		this.trangThaiVeDat = trangThaiVeDat;
		this.loaiVe = loaiVe;
		this.tienVe = tienVe;
		this.phanTramGiamGiaVeTapThe = phanTramGiamGiaVeTapThe;
		this.tienDatCoc = tienDatCoc;
	}

	public VeDat(String maVeDat) {
		this.maVeDat = maVeDat;
	}

	public double tienVeCuoi(){
		return tienVe * (1 - phanTramGiamGiaVeTapThe);
	}

	public double tienDatCoc(){
		tienDatCoc = tienVeCuoi() * PHANTRAMDATCOC;
		return tienDatCoc;
	}

	public double lePhiHuyDatVe(){
		Duration thoiGianConLai = Duration.between(LocalDateTime.now(), thongTinGaTauDi.getThoiGianDi());
		long soGioConLai = thoiGianConLai.toHours();

		if(loaiVe.equals(LoaiVe.VECANHAN)){
			if(soGioConLai >= 48){
				return tienDatCoc() * 0.1;
			}else if(soGioConLai >= 4){
				return tienDatCoc() * 0.2;
			}
		}

		if(loaiVe.equals(LoaiVe.VETAPTHE)){
			if(soGioConLai >= 72){
				return tienDatCoc() * 0.2;
			}else if(soGioConLai >= 24){
				return tienDatCoc() * 0.3;
			}
		}
		return 0;
	}
}
