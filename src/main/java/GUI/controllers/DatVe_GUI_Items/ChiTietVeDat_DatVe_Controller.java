package GUI.controllers.DatVe_GUI_Items;

import DTO.ChiTietVe;
import DTO.ChiTietVeDat;
import DTO.KhachHang;
import GUI.controllers.DatVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class ChiTietVeDat_DatVe_Controller implements Initializable {

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
    private Label lblLoaiKhachHang;

    @FXML
    private Label lblTenKhachHang;

    @FXML
    private Label lblToa;

    @FXML
    private Label lblLoaiToa;

    @FXML
    private Label lblLoaiCho;

    @FXML
    private VBox vboxDanhDachChoVeTapThe;

    private ChiTietVeDat chiTietVeDat;
    private DatVe_GUI_Controller datVe_gui_controller;

    public DatVe_GUI_Controller getDatVe_gui_controller() {
        return datVe_gui_controller;
    }

    public void setDatVe_gui_controller(DatVe_GUI_Controller datVe_gui_controller) {
        this.datVe_gui_controller = datVe_gui_controller;
    }

    public ChiTietVeDat getChiTietVeDat() {
        return chiTietVeDat;
    }

    public void setChiTietVeDat(ChiTietVeDat chiTietVeDat) {
        this.chiTietVeDat = chiTietVeDat;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        anpChiTietVe.setStyle("-fx-border-width: 1 0 1 0;" +
                "-fx-border-color:  #000;");
    }
}
