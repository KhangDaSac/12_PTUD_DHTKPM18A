package DTO;

public class ChiTietVeDat {

	private Cho cho;
	private VeDat veDat;
	private double giaCho;
	private KhachHang khachHang;
	private double phanTramGiamGia;

	public Cho getCho() {
		return cho;
	}

	public void setCho(Cho cho) {
		this.cho = cho;
	}

	public VeDat getVeDat() {
		return veDat;
	}

	public void setVeDat(VeDat veDat) {
		this.veDat = veDat;
	}

	public double getGiaCho() {
		return giaCho;
	}

	public void setGiaCho(double giaCho) {
		this.giaCho = giaCho;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
		this.phanTramGiamGia = khachHang.getLoaiKhachHang().getPhanTramGiamGia();
	}

	public double getPhanTramGiamGia() {
		return phanTramGiamGia;
	}

	public void setPhanTramGiamGia(double phanTramGiamGia) {
		this.phanTramGiamGia = phanTramGiamGia;
	}

	public ChiTietVeDat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietVeDat(VeDat veDat, Cho cho, KhachHang khachHang, double giaCho, double phanTramGiamGia) {
		this.veDat = veDat;
		this.cho = cho;
		this.khachHang = khachHang;
		this.giaCho = giaCho;
		this.phanTramGiamGia = phanTramGiamGia;
	}

	public ChiTietVeDat(VeDat veDat, Cho cho, double giaCho) {
		this.veDat = veDat;
		this.cho = cho;
		this.giaCho = giaCho;
	}

	public double giamGia(){
		return giaCho * phanTramGiamGia;
	}

	public double thanhTienChiTietVeDat(){
		return giaCho * (1 - phanTramGiamGia);
	}


}
