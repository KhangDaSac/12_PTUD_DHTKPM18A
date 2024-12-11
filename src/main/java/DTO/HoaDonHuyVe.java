package DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDonHuyVe {
	private String maHoaDonHuyVe;
	private LocalDateTime thoiGianHuyVe;
	private KhachHang khachHangHuyVe;
	private CaLamViec caLamViec;

	private ArrayList<ChiTietHoaDonHuyVe> danhSachChiTietHoaDonHuyVe;

	public String getMaHoaDonHuyVe() {
		return maHoaDonHuyVe;
	}

	public void setMaHoaDonHuyVe(String maHoaDonHuyVe) {
		this.maHoaDonHuyVe = maHoaDonHuyVe;
	}

	public LocalDateTime getThoiGianHuyVe() {
		return thoiGianHuyVe;
	}

	public void setThoiGianHuyVe(LocalDateTime thoiGianHuyVe) {
		this.thoiGianHuyVe = thoiGianHuyVe;
	}

	public KhachHang getKhachHangHuyVe() {
		return khachHangHuyVe;
	}

	public void setKhachHangHuyVe(KhachHang khachHangHuyVe) {
		this.khachHangHuyVe = khachHangHuyVe;
	}

	public CaLamViec getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(CaLamViec caLamViec) {
		this.caLamViec = caLamViec;
	}


	public ArrayList<ChiTietHoaDonHuyVe> getDanhSachChiTietHoaDonHuyVe() {
		return danhSachChiTietHoaDonHuyVe;
	}

	public void setDanhSachChiTietHoaDonHuyVe(ArrayList<ChiTietHoaDonHuyVe> danhSachChiTietHoaDonHuyVe) {
		this.danhSachChiTietHoaDonHuyVe = danhSachChiTietHoaDonHuyVe;
	}

	public HoaDonHuyVe(String maHoaDonHuyVe,  LocalDateTime thoiGianHuyVe,CaLamViec caLamViec, KhachHang khachHangHuyVe) {
		this.maHoaDonHuyVe = maHoaDonHuyVe;
		this.caLamViec = caLamViec;
		this.khachHangHuyVe = khachHangHuyVe;
		this.thoiGianHuyVe = thoiGianHuyVe;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HoaDonHuyVe that = (HoaDonHuyVe) o;
		return Objects.equals(maHoaDonHuyVe, that.maHoaDonHuyVe);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maHoaDonHuyVe);
	}

	public HoaDonHuyVe(String maHoaDonHuyVe) {
		this.maHoaDonHuyVe = maHoaDonHuyVe;
	}

	public double tongTienCuoi(){
		double tongTienCuoi = 0;
		for(ChiTietHoaDonHuyVe chiTietHoaDonHuyVe : danhSachChiTietHoaDonHuyVe){
			tongTienCuoi += chiTietHoaDonHuyVe.soTienHoanLai();
		}
		return tongTienCuoi;
	}

	public HoaDonHuyVe() {
	}
}
