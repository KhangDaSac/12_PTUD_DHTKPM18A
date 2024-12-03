package GUI.controllers.LayVe_GUI_Items;

import DTO.ChiTietVeDat;
import DTO.KhachHang;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;

public class ChiTietVeDat_LayVe_Controller {

    @FXML
    private AnchorPane anpChiTietVeDat;

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

    private ChiTietVeDat chiTietVeDat;

    public ChiTietVeDat getChiTietVeDat() {
        return chiTietVeDat;
    }

    public void setChiTietVeDat(ChiTietVeDat chiTietVeDat) {
        this.chiTietVeDat = chiTietVeDat;
    }

    public void khoiTao(){
        lblCho.setText(String.valueOf(chiTietVeDat.getCho().getSoCho()));
        lblToa.setText(String.valueOf(chiTietVeDat.getCho().getToaTau().getThuTuToa()));
        lblLoaiToa.setText(chiTietVeDat.getCho().getToaTau().getLoaiToaTau().getTenLoaiToa());
        lblLoaiCho.setText(chiTietVeDat.getCho().getLoaiCho().getTenLoaiCho());
        lblGiaCho.setText(CurrencyFormat.currencyFormat(chiTietVeDat.getGiaCho()));
        KhachHang khachHang = chiTietVeDat.getKhachHang();
        if(khachHang != null){
            lblCCCD.setText(khachHang.getCCCD());
            lblTenKhachHang.setText(khachHang.getTenKhachHang());
            lblLoaiKhachHang.setText(khachHang.getLoaiKhachHang().getTenLoaiKhachHang());
            lblGiamGiaLoaiKhachHang.setText(CurrencyFormat.currencyFormat(chiTietVeDat.giamGia()));
        }else{
            lblGiamGiaLoaiKhachHang.setText(CurrencyFormat.currencyFormat(0));
        }
    }

    public void chiTietVeCuoi(){
        anpChiTietVeDat.setStyle("-fx-border-width: 1 0 1 0;" +
                "-fx-border-color:  #000;");
    }
}
