package GUI.controllers.ThongTinBanVe_GUI_Items;

import DTO.ChiTietVe;
import DTO.KhachHang;
import GUI.controllers.BanVe_GUI_Items.Ve_BanVe_Controller;
import GUI.controllers.ThongTinBanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class ChiTietVe_ThongTinBanVe_Controller implements Initializable {
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
    private VBox vboxDanhDachChoVeTapThe;

    private ChiTietVe chiTietVe;
    private ThongTinBanVe_GUI_Controller thongTinBanVe_gui_controller;

    public ThongTinBanVe_GUI_Controller getThongTinBanVe_gui_controller() {
        return thongTinBanVe_gui_controller;
    }

    public void setThongTinBanVe_gui_controller(ThongTinBanVe_GUI_Controller thongTinBanVe_gui_controller) {
        this.thongTinBanVe_gui_controller = thongTinBanVe_gui_controller;
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
        thongTinBanVe_gui_controller.chonCho(chiTietVe);
        capNhatTrangThaiChiTietVe();
    }

    public void capNhatTrangThaiChiTietVe(){
        if(thongTinBanVe_gui_controller.kiemTraChiTietVeDuocChon(chiTietVe))
            anpChonChiTietVe.setStyle("-fx-background-color: #000;" +
                    "-fx-opacity: 0.1;");
        else
            anpChonChiTietVe.setStyle("");

    }
}
