package ENTITY;

public class ChiTietVe {
	private double giaCho;
	private Ve ve;
	private KhachHang khachHang;
	private Cho cho;
	public ChiTietVe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiTietVe(double giaCho, Ve ve, KhachHang khachHang, Cho cho) {
		super();
		this.giaCho = giaCho;
		this.ve = ve;
		this.khachHang = khachHang;
		this.cho = cho;
	}
	public ChiTietVe(double giaCho) {
		super();
		this.giaCho = giaCho;
	}
	public double getGiaCho() {
		return giaCho;
	}
	public void setGiaCho(double giaCho) {
		this.giaCho = giaCho;
	}
	public Ve getVe() {
		return ve;
	}
	public void setVe(Ve ve) {
		this.ve = ve;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public Cho getCho() {
		return cho;
	}
	public void setCho(Cho cho) {
		this.cho = cho;
	}
	@Override
	public String toString() {
		return "ChiTietVe [giaCho=" + giaCho + ", ve=" + ve + ", khachHang=" + khachHang + ", cho=" + cho + "]";
	}
	
}
