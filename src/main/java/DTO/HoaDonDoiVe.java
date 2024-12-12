package DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDonDoiVe {
	private String maHoaDonDoiVe;
	private LocalDateTime thoiGianDoiVe;
	private double lePhi;
	private Ve veCu;
	private Ve veMoi;
	private CaLamViec caLamViec;
	private ArrayList<Ve> danhSachVeDoi;

	public ArrayList<Ve> getDanhSachVeDoi() {
		return danhSachVeDoi;
	}

	public void setDanhSachVeDoi(ArrayList<Ve> danhSachVeDoi) {
		this.danhSachVeDoi = danhSachVeDoi;
	}

	private final double LEPHIDOIVE = 20000;

	public String getMaHoaDonDoiVe() {
		return maHoaDonDoiVe;
	}

	public void setMaHoaDonDoiVe(String maHoaDonDoiVe) {
		this.maHoaDonDoiVe = maHoaDonDoiVe;
	}

	public LocalDateTime getThoiGianDoiVe() {
		return thoiGianDoiVe;
	}

	public void setThoiGianDoiVe(LocalDateTime thoiGianDoiVe) {
		this.thoiGianDoiVe = thoiGianDoiVe;
	}

	public double getLePhi() {
		return lePhi;
	}

	public void setLePhi(double lePhi) {
		this.lePhi = lePhi;
	}

	public Ve getVeCu() {
		return veCu;
	}

	public void setVeCu(Ve veCu) {
		this.veCu = veCu;
	}

	public Ve getVeMoi() {
		return veMoi;
	}

	public void setVeMoi(Ve veMoi) {
		this.veMoi = veMoi;
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
		HoaDonDoiVe that = (HoaDonDoiVe) o;
		return Objects.equals(maHoaDonDoiVe, that.maHoaDonDoiVe);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maHoaDonDoiVe);
	}

	public HoaDonDoiVe(String maLichSuDoi) {
		this.maHoaDonDoiVe = maLichSuDoi;
	}

	public HoaDonDoiVe(String maHoaDonDoiVe, LocalDateTime thoiGianDoiVe, Ve veCu, Ve veMoi, CaLamViec caLamViec) {
		this.maHoaDonDoiVe = maHoaDonDoiVe;
		this.thoiGianDoiVe = thoiGianDoiVe;
		this.lePhi = LEPHIDOIVE;
		this.veCu = veCu;
		this.veMoi = veMoi;
		this.caLamViec = caLamViec;
	}

	public double tongTienCuoi(){
		return veMoi.tienVeCuoi() - veCu.tienVeCuoi() + lePhi;
	}
}
