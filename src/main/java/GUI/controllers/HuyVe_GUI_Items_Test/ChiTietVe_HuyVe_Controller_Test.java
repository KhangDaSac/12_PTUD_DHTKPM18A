package GUI.controllers.HuyVe_GUI_Items_Test;

import DTO.ChiTietHoaDonHuyVe;
import DTO.ChiTietVe;
import DTO.KhachHang;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;
import javafx.scene.control.Label;


public class ChiTietVe_HuyVe_Controller_Test {
    @FXML
    private AnchorPane anpChiTietVe;

    @FXML
    private Label lblCCCD;

    @FXML
    private Label lblCho;

    @FXML
    private Label lblGiaCho;

    @FXML
    private Label lblGiamGiaLoaiKhachHang;

    @FXML
    private Label lblLoaiCho;

    @FXML
    private Label lblLoaiKhachHang;

    @FXML
    private Label lblLoaiToa;

    @FXML
    private Label lblTenKhachHang;

    @FXML
    private Label lblToa;

    private ChiTietVe chiTietVe;

    public ChiTietVe getChiTietVe() {
        return chiTietVe;
    }

    public void setChiTietVe(ChiTietVe chiTietVe) {
        this.chiTietVe = chiTietVe;
    }

    private ChiTietHoaDonHuyVe chiTietHoaDonHuyVe;

    public void khoiTao(){
        lblCho.setText(String.valueOf(chiTietVe.getCho().getSoCho()));
        lblToa.setText(String.valueOf(chiTietVe.getCho().getToaTau().getThuTuToa()));
        lblLoaiToa.setText(chiTietVe.getCho().getToaTau().getLoaiToaTau().getTenLoaiToa());
        lblLoaiCho.setText(chiTietVe.getCho().getLoaiCho().getTenLoaiCho());
        lblGiaCho.setText(CurrencyFormat.currencyFormat(chiTietVe.getGiaCho()));
        KhachHang khachHang = chiTietVe.getKhachHang();
        if(khachHang != null){
            lblCCCD.setText(khachHang.getCCCD());
            lblTenKhachHang.setText(khachHang.getTenKhachHang());
            lblLoaiKhachHang.setText(khachHang.getLoaiKhachHang().getTenLoaiKhachHang());
            lblGiamGiaLoaiKhachHang.setText(CurrencyFormat.currencyFormat(chiTietVe.giamGia()));
        }else{
            lblGiamGiaLoaiKhachHang.setText(CurrencyFormat.currencyFormat(0));
        }
    }

    public void chiTietVeCuoi(){
        anpChiTietVe.setStyle("-fx-border-width: 1 0 1 0;" +
                "-fx-border-color:  #000;");
    }

    public void khoiTaoChiTietHoaDon(ChiTietHoaDonHuyVe chiTietHoaDonHuyVe) {
        if (chiTietHoaDonHuyVe == null) {
            System.out.println("ChiTietHoaDonHuyVe không hợp lệ.");
            return;
        }
        this.chiTietHoaDonHuyVe = chiTietHoaDonHuyVe;
    }

    // Lấy lệ phí
    public double layLePhi() {
        if (chiTietHoaDonHuyVe != null) {
            return chiTietHoaDonHuyVe.lePhi();
        } else {
            System.out.println("ChiTietHoaDonHuyVe lePhi chưa được khởi tạo.");
            return 0;
        }
    }

    // Lấy tổng tiền trả
    public double layTongTienTra() {
        if (chiTietHoaDonHuyVe != null) {
            return chiTietHoaDonHuyVe.soTienHoanLai();
        } else {
            System.out.println("ChiTietHoaDonHuyVe tinhTien chưa được khởi tạo.");
            return 0;
        }
    }
}