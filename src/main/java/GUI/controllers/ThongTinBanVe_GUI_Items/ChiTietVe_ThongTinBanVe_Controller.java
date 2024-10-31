package GUI.controllers.ThongTinBanVe_GUI_Items;

import DTO.ChiTietVe;
import DTO.LoaiVe;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.ThongTinBanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;

public class ChiTietVe_ThongTinBanVe_Controller {

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
    private boolean dangChon;

    public boolean isDangChon() {
        return dangChon;
    }

    public void setDangChon(boolean dangChon) {
        this.dangChon = dangChon;
    }

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

    @FXML
    void anpChiTietVeOnMouseClicked(MouseEvent event) {
        chonChiTietVe();
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

    public void chonChiTietVe(){
        thongTinBanVe_gui_controller.boChonTatCaChiTietVe();
        anpChiTietVe.getStyleClass().removeAll("chiTietVeKhongChon");
        anpChiTietVe.getStyleClass().add("chiTietVeDangChon");
        dangChon = true;
    }


    public void khongChonChiTietVe(){
        dangChon = false;
        anpChiTietVe.getStyleClass().removeAll("chiTietVeDangChon");
        anpChiTietVe.getStyleClass().add("chiTietVeKhongChon");
    }

    public void capNhatLaiThongTinKhachHang(){
        System.out.println(chiTietVe.getKhachHang());
        lblTenKhachHang.setText(chiTietVe.getKhachHang().getTenKhachHang());
        System.out.println(lblTenKhachHang.getText());
        lblCCCD.setText(chiTietVe.getKhachHang().getCCCD());
        chiTietVe.setSoTienGiamGia(chiTietVe.tinhTienGiamGia());
        chiTietVe.setThanhTien(chiTietVe.tinhThanhTien());
        lblGiamGia.setText(CurrencyFormat.currencyFormat(chiTietVe.getSoTienGiamGia()));
        lblThanhTien.setText(CurrencyFormat.currencyFormat(chiTietVe.getThanhTien()));
        lblCho.setText(CurrencyFormat.currencyFormat(chiTietVe.getGiaCho()));
    }

}
