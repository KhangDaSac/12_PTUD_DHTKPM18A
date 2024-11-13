package DTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ve {
	private String maVe;
	private HoaDonBanVe hoaDonBanVe;
	private HoaDonLayVe hoaDonLayVe;
	private HoaDonHuyVe hoaDonHuyVe;
	private ChiTietChuyenTau thongTinGaTauDi;
	private ChiTietChuyenTau thongTinGaTauDen;
	private LoaiVe loaiVe ;
	private TrangThaiVe trangThaiVe;
	private double tienVe;
	private double phanTramGiamGiaVeTapThe;

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

	public HoaDonLayVe getHoaDonLayVe() {
		return hoaDonLayVe;
	}

	public void setHoaDonLayVe(HoaDonLayVe hoaDonLayVe) {
		this.hoaDonLayVe = hoaDonLayVe;
	}

	public ChiTietChuyenTau getThongTinGaTauDi() {
		return thongTinGaTauDi;
	}

	public HoaDonHuyVe getHoaDonHuyVe() {
		return hoaDonHuyVe;
	}

	public void setHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe) {
		this.hoaDonHuyVe = hoaDonHuyVe;
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
	}

	public TrangThaiVe getTrangThaiVe() {
		return trangThaiVe;
	}

	public void setTrangThaiVe(TrangThaiVe trangThaiVe) {
		this.trangThaiVe = trangThaiVe;
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

	public Ve(String maVe, HoaDonBanVe hoaDonBanVe, HoaDonLayVe hoaDonLayVe, ChiTietChuyenTau thongTinGaTauDi, ChiTietChuyenTau thongTinGaTauDen, LoaiVe loaiVe, TrangThaiVe trangThaiVe, double tienVe) {
		this.maVe = maVe;
		this.hoaDonBanVe = hoaDonBanVe;
		this.hoaDonLayVe = hoaDonLayVe;
		this.thongTinGaTauDi = thongTinGaTauDi;
		this.thongTinGaTauDen = thongTinGaTauDen;
		this.loaiVe = loaiVe;
		this.trangThaiVe = trangThaiVe;
		this.tienVe = tienVe;
		this.phanTramGiamGiaVeTapThe = PHANTRAMGIAMGIAVETAPTHE;
	}

	public Ve(String maVe) {
		this.maVe = maVe;
	}

	public double tienVeCuoi(){
		return tienVe * (1 - phanTramGiamGiaVeTapThe);
	}

	public double lePhiHuyVe(){
		Duration thoiGianConLai = Duration.between(LocalDateTime.now(), thongTinGaTauDi.getThoiGianDi());
		long soGioConLai = thoiGianConLai.toHours();

		if(loaiVe.equals(LoaiVe.VECANHAN)){
			if(soGioConLai >= 48){
				return tienVeCuoi() * 0.1;
			}else if(soGioConLai >= 4){
				return tienVeCuoi() * 0.2;
			}
		}

		if(loaiVe.equals(LoaiVe.VETAPTHE)){
			if(soGioConLai >= 72){
				return tienVeCuoi() * 0.2;
			}else if(soGioConLai >= 24){
				return tienVeCuoi() * 0.3;
			}
		}

		return 0;
	}
}
