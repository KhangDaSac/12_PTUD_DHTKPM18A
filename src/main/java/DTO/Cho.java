package ENTITY;

import java.util.Objects;

public class Cho {
	private String maCho;
	private int soCho;
	private ToaTau toaTau;
	private LoaiCho loaiCho;
	public Cho() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cho(String maCho, int soCho, ToaTau toaTau, LoaiCho loaiCho) {
		super();
		this.maCho = maCho;
		this.soCho = soCho;
		this.toaTau = toaTau;
		this.loaiCho = loaiCho;
	}
	public Cho(String maCho) {
		super();
		this.maCho = maCho;
	}
	public String getMaCho() {
		return maCho;
	}
	public void setMaCho(String maCho) {
		this.maCho = maCho;
	}
	public int getSoCho() {
		return soCho;
	}
	public void setSoCho(int soCho) {
		this.soCho = soCho;
	}
	public ToaTau getToaTau() {
		return toaTau;
	}
	public void setToaTau(ToaTau toaTau) {
		this.toaTau = toaTau;
	}
	public LoaiCho getLoaiCho() {
		return loaiCho;
	}
	public void setLoaiCho(LoaiCho loaiCho) {
		this.loaiCho = loaiCho;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maCho);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cho other = (Cho) obj;
		return Objects.equals(maCho, other.maCho);
	}
	@Override
	public String toString() {
		return "Cho [maCho=" + maCho + ", soCho=" + soCho + ", toaTau=" + toaTau + ", loaiCho=" + loaiCho + "]";
	}
	
}
