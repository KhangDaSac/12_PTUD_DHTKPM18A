package DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDonBanVe {
	private String maHoaDonBanVe;
	private LocalDateTime thoiGianLap;
	private KhachHang khachHangMuaVe;
	private CaLamViec caLamViec;

	private ArrayList<Ve> danhSachVe ;
	public String getMaHoaDonBanVe() {
		return maHoaDonBanVe;
	}

	public void setMaHoaDonBanVe(String maHoaDonBanVe) {
		this.maHoaDonBanVe = maHoaDonBanVe;
	}

	public LocalDateTime getThoiGianLap() {
		return thoiGianLap;
	}

	public void setThoiGianLap(LocalDateTime thoiGianLap) {
		this.thoiGianLap = thoiGianLap;
	}

	public KhachHang getKhachHangMuaVe() {
		return khachHangMuaVe;
	}

	public void setKhachHangMuaVe(KhachHang khachHangMuaVe) {
		this.khachHangMuaVe = khachHangMuaVe;
	}

	public CaLamViec getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(CaLamViec caLamViec) {
		this.caLamViec = caLamViec;
	}

	public ArrayList<Ve> getDanhSachVe() {
		return danhSachVe;
	}

	public void setDanhSachVe(ArrayList<Ve> danhSachVe) {
		this.danhSachVe = danhSachVe;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HoaDonBanVe that = (HoaDonBanVe) o;
		return Objects.equals(maHoaDonBanVe, that.maHoaDonBanVe);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maHoaDonBanVe);
	}

	public HoaDonBanVe(String maHoaDonBanVe) {
		this.maHoaDonBanVe = maHoaDonBanVe;
	}

	public double tongTienCuoi(){
		double tongTienCuoi = 0;
		for (Ve ve : danhSachVe){
			tongTienCuoi += ve.tienVeCuoi();
		}
		return tongTienCuoi;
	}

	public HoaDonBanVe() {
	}
}
