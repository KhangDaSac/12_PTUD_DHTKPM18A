package ENTITY;

public class ChiTietPhieuDatVe {
	private double giaCho;
	private Cho cho;
	private PhieuDatVe phieuDatVe;
	private KhachHang khachHang;
	public ChiTietPhieuDatVe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiTietPhieuDatVe(double giaCho, Cho cho, PhieuDatVe phieuDatVe, KhachHang khachHang) {
		super();
		this.giaCho = giaCho;
		this.cho = cho;
		this.phieuDatVe = phieuDatVe;
		this.khachHang = khachHang;
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
	@Override
	public String toString() {
		return "ChiTietPhieuDatVe [giaCho=" + giaCho + ", cho=" + cho + ", phieuDatVe=" + phieuDatVe + ", khachHang="
				+ khachHang + "]";
	}
	
}
