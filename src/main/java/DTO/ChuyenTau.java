package ENTITY;

import java.util.Date;
import java.util.Objects;

public class ChuyenTau {
	private String maChuyenTau;
	private TuyenTau tuyenTau;
	private int soLuongCho;
	private int soLuongChoDaDat;
	private int soLuongChoDaBan;
	private Date ngayKhoiHanh;
	public ChuyenTau() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChuyenTau(String maChuyenTau, TuyenTau tuyenTau, int soLuongCho, int soLuongChoDaDat, int soLuongChoDaBan,
			Date ngayKhoiHanh) {
		super();
		this.maChuyenTau = maChuyenTau;
		this.tuyenTau = tuyenTau;
		this.soLuongCho = soLuongCho;
		this.soLuongChoDaDat = soLuongChoDaDat;
		this.soLuongChoDaBan = soLuongChoDaBan;
		this.ngayKhoiHanh = ngayKhoiHanh;
	}
	public ChuyenTau(String maChuyenTau) {
		super();
		this.maChuyenTau = maChuyenTau;
	}
	public String getMaChuyenTau() {
		return maChuyenTau;
	}
	public void setMaChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}
	public TuyenTau getTuyenTau() {
		return tuyenTau;
	}
	public void setTuyenTau(TuyenTau tuyenTau) {
		this.tuyenTau = tuyenTau;
	}
	public int getSoLuongCho() {
		return soLuongCho;
	}
	public void setSoLuongCho(int soLuongCho) {
		this.soLuongCho = soLuongCho;
	}
	public int getSoLuongChoDaDat() {
		return soLuongChoDaDat;
	}
	public void setSoLuongChoDaDat(int soLuongChoDaDat) {
		this.soLuongChoDaDat = soLuongChoDaDat;
	}
	public int getSoLuongChoDaBan() {
		return soLuongChoDaBan;
	}
	public void setSoLuongChoDaBan(int soLuongChoDaBan) {
		this.soLuongChoDaBan = soLuongChoDaBan;
	}
	public Date getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}
	public void setNgayKhoiHanh(Date ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maChuyenTau);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChuyenTau other = (ChuyenTau) obj;
		return Objects.equals(maChuyenTau, other.maChuyenTau);
	}
	@Override
	public String toString() {
		return "ChuyenTau [maChuyenTau=" + maChuyenTau + ", tuyenTau=" + tuyenTau + ", soLuongCho=" + soLuongCho
				+ ", soLuongChoDaDat=" + soLuongChoDaDat + ", soLuongChoDaBan=" + soLuongChoDaBan + ", ngayKhoiHanh="
				+ ngayKhoiHanh + "]";
	}
	
	
}