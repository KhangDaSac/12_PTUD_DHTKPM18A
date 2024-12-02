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

	public double giamGia(){
		return giaCho * phanTramGiamGia;
	}

	public double thanhTienChiTietVeDat(){
		return giaCho * (1 - phanTramGiamGia);
	}


}
