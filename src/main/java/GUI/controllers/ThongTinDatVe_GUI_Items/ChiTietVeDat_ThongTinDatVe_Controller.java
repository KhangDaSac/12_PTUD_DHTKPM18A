package GUI.controllers.ThongTinDatVe_GUI_Items;

import DTO.ChiTietVe;
import DTO.ChiTietVeDat;
import DTO.KhachHang;
import GUI.controllers.ThongTinBanVe_GUI_Controller;
import GUI.controllers.ThongTinDatVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class ChiTietVeDat_ThongTinDatVe_Controller implements Initializable {
    @FXML
    private AnchorPane anpChiTietVe;

    @FXML
    private AnchorPane anpChonChiTietVe;

    @FXML
    private ImageView imvDaCoKhachHang;

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
    private ThongTinDatVe_GUI_Controller thongTinDatVe_gui_controller;

    public ThongTinDatVe_GUI_Controller getThongTinDatVe_gui_controller() {
        return thongTinDatVe_gui_controller;
    }

    public void setThongTinDatVe_gui_controller(ThongTinDatVe_GUI_Controller thongTinDatVe_gui_controller) {
        this.thongTinDatVe_gui_controller = thongTinDatVe_gui_controller;
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
            imvDaCoKhachHang.setVisible(false);
        }
        capNhatTrangThaiChiTietVe();
    }

    public void chiTietVeCuoi(){
        anpChiTietVe.setStyle("-fx-border-width: 1 0 1 0;" +
                "-fx-border-color:  #000;");
    }

    @FXML
    void anpChiTietVeClicked(MouseEvent event) {
        thongTinDatVe_gui_controller.chonCho(chiTietVeDat);
        capNhatTrangThaiChiTietVe();
    }

    public void capNhatTrangThaiChiTietVe(){
        if(thongTinDatVe_gui_controller.kiemTraChiTietVeDuocChon(chiTietVeDat))
            anpChonChiTietVe.setStyle("-fx-background-color: #000;" +
                    "-fx-opacity: 0.1;");
        else
            anpChonChiTietVe.setStyle("");

    }
}
