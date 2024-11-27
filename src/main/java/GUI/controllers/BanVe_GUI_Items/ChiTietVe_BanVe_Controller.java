package GUI.controllers.BanVe_GUI_Items;

import DTO.ChiTietVe;
import DTO.KhachHang;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class ChiTietVe_BanVe_Controller implements Initializable {

    @FXML
    private Label lblCCCD;

    @FXML
    private Label lblCho;

    @FXML
    private Label lblGiaCho;

    @FXML
    private Label lblGiamGiaLoaiKhachHang;

    @FXML
    private Label lblLoaiKhachHang;

    @FXML
    private Label lblTenKhachHang;

    @FXML
    private Label lblToa;

    @FXML
    private VBox vboxDanhDachChoVeTapThe;

    private ChiTietVe chiTietVe;
    private Ve_BanVe_Controller ve_banVe_controller;

    public Ve_BanVe_Controller getVe_banVe_controller() {
        return ve_banVe_controller;
    }

    public void setVe_banVe_controller(Ve_BanVe_Controller ve_banVe_controller) {
        this.ve_banVe_controller = ve_banVe_controller;
    }

    public ChiTietVe getChiTietVe() {
        return chiTietVe;
    }

    public void setChiTietVe(ChiTietVe chiTietVe) {
        this.chiTietVe = chiTietVe;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(){
        lblCho.setText(String.valueOf(chiTietVe.getCho().getSoCho()));
        lblToa.setText(String.valueOf(chiTietVe.getCho().getToaTau().getThuTuToa()));
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
}
