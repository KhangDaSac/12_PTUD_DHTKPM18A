package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDonHuyVe {
	private String maHoaDonHuyVe;
	private LocalDateTime thoiGianHuyVe;
	private KhachHang khachHangHuyVe;
	private double tongTienCuoi;
	private CaLamViec caLamViec;

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

	public double getTongTienCuoi() {
		return tongTienCuoi;
	}

	public void setTongTienCuoi(double tongTienCuoi) {
		this.tongTienCuoi = tongTienCuoi;
	}

	public CaLamViec getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(CaLamViec caLamViec) {
		this.caLamViec = caLamViec;
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
}
