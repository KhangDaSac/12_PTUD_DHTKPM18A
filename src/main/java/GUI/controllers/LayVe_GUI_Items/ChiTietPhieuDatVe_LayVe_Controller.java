package GUI.controllers.LayVe_GUI_Items;

import DTO.ChiTietPhieuDatVe;
import DTO.LoaiPhieuDatVe;
import GUI.controllers.HuyDatVe_GUI_Controller;
import GUI.controllers.LayVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;

public class ChiTietPhieuDatVe_LayVe_Controller {

    @FXML
    private AnchorPane anpChiTietPhieuDatVe;

    @FXML
    private AnchorPane anpLoaiPhieuDatVe;

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

    private ChiTietPhieuDatVe chiTietPhieuDatVe;
    private LayVe_GUI_Controller layVe_gui_controller;
    private HuyDatVe_GUI_Controller huyDatVe_gui_controller;
    private boolean dangChon;

    public boolean isDangChon() {
        return dangChon;
    }

    public void setDangChon(boolean dangChon) {
        this.dangChon = dangChon;
    }

    public LayVe_GUI_Controller getLayVe_gui_controller() {
        return layVe_gui_controller;
    }

    public void setLayVe_gui_controller(LayVe_GUI_Controller layVe_gui_controller) {
        this.layVe_gui_controller = layVe_gui_controller;
    }
    public void setHuyDatVe_gui_controller(HuyDatVe_GUI_Controller huyDatVe_gui_controller) {
        this.huyDatVe_gui_controller = huyDatVe_gui_controller;
    }

    public ChiTietPhieuDatVe getChiTietPhieuDatVe() {
        return chiTietPhieuDatVe;
    }

    public void setChiTietPhieuDatVe(ChiTietPhieuDatVe chiTietPhieuDatVe) {
        this.chiTietPhieuDatVe = chiTietPhieuDatVe;
    }

    @FXML
    void anpChiTietPhieuDatVeOnMouseClicked(MouseEvent event) {
        if (layVe_gui_controller!=null){
            layVe_gui_controller.boChonTatCaChiTietPhieuDatVe();
            chonChiTietPhieuDatVe();
        }

    }

    public void khoiTao(){
        lblCho.setText(String.valueOf(chiTietPhieuDatVe.getCho().getMaCho()));
        lblToa.setText(String.valueOf(chiTietPhieuDatVe.getCho().getToaTau().getThuTuToa()));
        lblGiaCho.setText(CurrencyFormat.currencyFormat(chiTietPhieuDatVe.getGiaCho()));
        lblGiamGia.setText(CurrencyFormat.currencyFormat(chiTietPhieuDatVe.getSoTienGiamGia()));
        lblThanhTien.setText(CurrencyFormat.currencyFormat(chiTietPhieuDatVe.getThanhTien()));
        lblTenKhachHang.setText(chiTietPhieuDatVe.getKhachHang().getTenKhachHang());
        lblLoaiKhachHang.setText(chiTietPhieuDatVe.getKhachHang().getLoaiKhachHang().getTenLoaiKhachHang());
        lblCCCD.setText(chiTietPhieuDatVe.getKhachHang().getCCCD());
        anpChiTietPhieuDatVe.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/ChiTietPhieuDatVe_LayVe.css").toExternalForm());
        anpLoaiPhieuDatVe.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/ChiTietPhieuDatVe_LayVe.css").toExternalForm());
        if(chiTietPhieuDatVe.getPhieuDatVe().getLoaiPhieuDatVe() == LoaiPhieuDatVe.PHIEUDATCANHAN){
            anpLoaiPhieuDatVe.getStyleClass().add("chiTietphieuDatVeCaNhan");
        }else if(chiTietPhieuDatVe.getPhieuDatVe().getLoaiPhieuDatVe() == LoaiPhieuDatVe.PHIEUDATTAPTHE){
            anpLoaiPhieuDatVe.getStyleClass().add("chiTietphieuDatVeTapThe");
        }
    }

    public void chonChiTietPhieuDatVe(){
        layVe_gui_controller.boChonTatCaChiTietPhieuDatVe();
        anpChiTietPhieuDatVe.getStyleClass().removeAll("chiTietVeKhongChon");
        anpChiTietPhieuDatVe.getStyleClass().add("chiTietPhieuDatVeKhongChon");
    }


    public void khongChonChiTietPhieuDatVe(){
        anpChiTietPhieuDatVe.getStyleClass().removeAll("chiTietPhieuDatVeDangChon");
        anpChiTietPhieuDatVe.getStyleClass().add("chiTietPhieuDatVeKhongChon");
    }

}
