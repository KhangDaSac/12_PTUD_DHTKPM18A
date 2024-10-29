package GUI.controllers.BanVe_GUI_Items;

import DTO.ChiTietVe;
import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    }

}
