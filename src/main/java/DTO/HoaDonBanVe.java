package DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDonBanVe {
	private String maHoaDonBanVe;
	private LocalDateTime thoiGianLap;
	private KhachHang khachHangMua;
	private CaLamViec caLamViec;
	private double tongTien;

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

	public KhachHang getKhachHangMua() {
		return khachHangMua;
	}

	public void setKhachHangMua(KhachHang khachHangMua) {
		this.khachHangMua = khachHangMua;
	}

	public CaLamViec getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(CaLamViec caLamViec) {
		this.caLamViec = caLamViec;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
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

	public HoaDonBanVe(String maHoaDonBanVe, LocalDateTime thoiGianLap, KhachHang khachHangMua, CaLamViec caLamViec, double tongTien) {
		this.maHoaDonBanVe = maHoaDonBanVe;
		this.thoiGianLap = thoiGianLap;
		this.khachHangMua = khachHangMua;
		this.caLamViec = caLamViec;
		this.tongTien = tongTien;
	}
}
