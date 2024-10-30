package GUI.controllers.BanVe_GUI_Items;

import DTO.ChiTietVe;
import DTO.LoaiVe;
import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;

public class ChiTietVe_Controller {

    @FXML
    private AnchorPane anpChiTietVe;

    @FXML
    private Label lblCCCD;

    @FXML
    private Label lblCho;

    @FXML
    private Label lblGiaCho;

    @FXML
    private Label lblGiamGia;

    @FXML
    private Label lblLoaiKhachHang;

    @FXML
    private Label lblTenKhachHang;

    @FXML
    private Label lblThanhTien;

    @FXML
    private Label lblToa;

    @FXML
    private AnchorPane anpLoaiVe;

    private ChiTietVe chiTietVe;

    private BanVe_GUI_Controller banVe_gui_controller;

    public BanVe_GUI_Controller getBanVe_gui_controller() {
        return banVe_gui_controller;
    }

    public void setBanVe_gui_controller(BanVe_GUI_Controller banVe_gui_controller) {
        this.banVe_gui_controller = banVe_gui_controller;
    }

    public ChiTietVe getChiTietVe() {
        return chiTietVe;
    }

    public void setChiTietVe(ChiTietVe chiTietVe) {
        this.chiTietVe = chiTietVe;
    }

    @FXML
    void anpChiTietVeOnMouseClicked(MouseEvent event) {

    }

    public void khoiTao(){
        lblCho.setText(String.valueOf(chiTietVe.getCho().getSoCho()));
        lblToa.setText(String.valueOf(chiTietVe.getCho().getToaTau().getThuTuToa()));
        lblGiaCho.setText(CurrencyFormat.currencyFormat(chiTietVe.getGiaCho()));
        lblGiamGia.setText(CurrencyFormat.currencyFormat(chiTietVe.getSoTienGiamGia()));
        lblThanhTien.setText(CurrencyFormat.currencyFormat(chiTietVe.getThanhTien()));
        anpChiTietVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/ChiTietVe.css").toExternalForm());

        if(chiTietVe.getVe().getLoaiVe() == LoaiVe.VECANHAN){
            anpLoaiVe.getStyleClass().add("ve-left-veCaNhan");
        }else if(chiTietVe.getVe().getLoaiVe() == LoaiVe.VETAPTHE){
            anpLoaiVe.getStyleClass().add("ve-left-veTapThe");
        }
    }

}
