package DTO;

public class ChiTietPhieuDatVe {
	private double giaCho;
	private Cho cho;
	private PhieuDatVe phieuDatVe;
	private KhachHang khachHang;
	private double soTienGiamGia ;
	private double thanhTien ;
	public ChiTietPhieuDatVe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietPhieuDatVe(double giaCho, Cho cho, PhieuDatVe phieuDatVe, KhachHang khachHang, double soTienGiamGia, double thanhTien) {
		this.giaCho = giaCho;
		this.cho = cho;
		this.phieuDatVe = phieuDatVe;
		this.khachHang = khachHang;
		this.soTienGiamGia = soTienGiamGia;
		this.thanhTien = thanhTien;
	}

	public ChiTietPhieuDatVe(PhieuDatVe phieuDatVe) {
		this.phieuDatVe = phieuDatVe;
	}

	public double getGiaCho() {
		return giaCho;
	}

	public void setGiaCho(double giaCho) {
		this.giaCho = giaCho;
	}

	public Cho getCho() {
		return cho;
	}

	public void setCho(Cho cho) {
		this.cho = cho;
	}

	public PhieuDatVe getPhieuDatVe() {
		return phieuDatVe;
	}

	public void setPhieuDatVe(PhieuDatVe phieuDatVe) {
		this.phieuDatVe = phieuDatVe;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public double getSoTienGiamGia() {
		return soTienGiamGia;
	}

	public void setSoTienGiamGia(double soTienGiamGia) {
		this.soTienGiamGia = soTienGiamGia;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	@Override
	public String toString() {
		return "ChiTietPhieuDatVe{" +
				"giaCho=" + giaCho +
				", cho=" + cho +
				", phieuDatVe=" + phieuDatVe +
				", khachHang=" + khachHang +
				", soTienGiamGia=" + soTienGiamGia +
				", thanhTien=" + thanhTien +
				'}';
	}
}
