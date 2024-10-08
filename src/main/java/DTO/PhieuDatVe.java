package ENTITY;

import java.util.Date;
import java.util.Objects;

public class PhieuDatVe {
	private String maPhieuDatVe;
	private double tongGiaVeDaDat;
	private double tongTienDaDatCoc;
	private Date ngayLayVe;
	private TrangThaiPhieuDatVe trangThaiPhieuDatVe;
	private LoaiPhieuDatVe loaiPhieuDatVe;
	private HoaDon hoaDon;
	private ChuyenTau chuyenTau;
	private GaTau gaTau;
	public PhieuDatVe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhieuDatVe(String maPhieuDatVe, double tongGiaVeDaDat, double tongTienDaDatCoc, Date ngayLayVe,
			TrangThaiPhieuDatVe trangThaiPhieuDatVe, LoaiPhieuDatVe loaiPhieuDatVe, HoaDon hoaDon, ChuyenTau chuyenTau,
			GaTau gaTau) {
		super();
		this.maPhieuDatVe = maPhieuDatVe;
		this.tongGiaVeDaDat = tongGiaVeDaDat;
		this.tongTienDaDatCoc = tongTienDaDatCoc;
		this.ngayLayVe = ngayLayVe;
		this.trangThaiPhieuDatVe = trangThaiPhieuDatVe;
		this.loaiPhieuDatVe = loaiPhieuDatVe;
		this.hoaDon = hoaDon;
		this.chuyenTau = chuyenTau;
		this.gaTau = gaTau;
	}
	public PhieuDatVe(String maPhieuDatVe) {
		super();
		this.maPhieuDatVe = maPhieuDatVe;
	}
	public String getMaPhieuDatVe() {
		return maPhieuDatVe;
	}
	public void setMaPhieuDatVe(String maPhieuDatVe) {
		this.maPhieuDatVe = maPhieuDatVe;
	}
	public double getTongGiaVeDaDat() {
		return tongGiaVeDaDat;
	}
	public void setTongGiaVeDaDat(double tongGiaVeDaDat) {
		this.tongGiaVeDaDat = tongGiaVeDaDat;
	}
	public double getTongTienDaDatCoc() {
		return tongTienDaDatCoc;
	}
	public void setTongTienDaDatCoc(double tongTienDaDatCoc) {
		this.tongTienDaDatCoc = tongTienDaDatCoc;
	}
	public Date getNgayLayVe() {
		return ngayLayVe;
	}
	public void setNgayLayVe(Date ngayLayVe) {
		this.ngayLayVe = ngayLayVe;
	}
	public TrangThaiPhieuDatVe getTrangThaiPhieuDatVe() {
		return trangThaiPhieuDatVe;
	}
	public void setTrangThaiPhieuDatVe(TrangThaiPhieuDatVe trangThaiPhieuDatVe) {
		this.trangThaiPhieuDatVe = trangThaiPhieuDatVe;
	}
	public LoaiPhieuDatVe getLoaiPhieuDatVe() {
		return loaiPhieuDatVe;
	}
	public void setLoaiPhieuDatVe(LoaiPhieuDatVe loaiPhieuDatVe) {
		this.loaiPhieuDatVe = loaiPhieuDatVe;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public ChuyenTau getChuyenTau() {
		return chuyenTau;
	}
	public void setChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}
	public GaTau getGaTau() {
		return gaTau;
	}
	public void setGaTau(GaTau gaTau) {
		this.gaTau = gaTau;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maPhieuDatVe);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDatVe other = (PhieuDatVe) obj;
		return Objects.equals(maPhieuDatVe, other.maPhieuDatVe);
	}
	@Override
	public String toString() {
		return "PhieuDatVe [maPhieuDatVe=" + maPhieuDatVe + ", tongGiaVeDaDat=" + tongGiaVeDaDat + ", tongTienDaDatCoc="
				+ tongTienDaDatCoc + ", ngayLayVe=" + ngayLayVe + ", trangThaiPhieuDatVe=" + trangThaiPhieuDatVe
				+ ", loaiPhieuDatVe=" + loaiPhieuDatVe + ", hoaDon=" + hoaDon + ", chuyenTau=" + chuyenTau + ", gaTau="
				+ gaTau + "]";
	}
	
	
}
