package DTO;

import java.time.LocalDateTime;
import java.util.Objects;


public class ChuyenTau {
	private String maChuyenTau;
	private TuyenTau tuyenTau;
	private int soLuongCho;
	private LocalDateTime ngayKhoiHanh;

	public ChuyenTau(String maChuyenTau, TuyenTau tuyenTau, int soLuongCho, LocalDateTime ngayKhoiHanh) {
		this.maChuyenTau = maChuyenTau;
		this.tuyenTau = tuyenTau;
		this.soLuongCho = soLuongCho;
		this.ngayKhoiHanh = ngayKhoiHanh;
	}

	public ChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}
	public ChuyenTau(String maChuyenTau, int soLuongCho, LocalDateTime thoiGianDi, LocalDateTime thoiGianDen) {

	}

	public ChuyenTau(String maChuyenTau, TuyenTau tuyenTau, int soLuongCho) {
	}




	public String getMaChuyenTau() {
		return maChuyenTau;
	}

	public TuyenTau getTuyenTau() {
		return tuyenTau;
	}

	public int getSoLuongCho() {
		return soLuongCho;
	}

	public LocalDateTime getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}

	public void setMaChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}

	public void setTuyenTau(TuyenTau tuyenTau) {
		this.tuyenTau = tuyenTau;
	}

	public void setSoLuongCho(int soLuongCho) {
		this.soLuongCho = soLuongCho;
	}

	public void setNgayKhoiHanh(LocalDateTime ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChuyenTau chuyenTau = (ChuyenTau) o;
		return Objects.equals(maChuyenTau, chuyenTau.maChuyenTau);
	}

	public ChuyenTau(String maChuyenTau, int soLuongCho) {
		this.maChuyenTau = maChuyenTau;
		this.soLuongCho = soLuongCho;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(maChuyenTau);
	}

	@Override
	public String toString() {
		return "ChuyenTau{" +
				"maChuyenTau='" + maChuyenTau + '\'' +
				", tuyenTau=" + tuyenTau +
				", soLuongCho=" + soLuongCho +
				", ngayKhoiHanh=" + ngayKhoiHanh +
				'}';
	}
}