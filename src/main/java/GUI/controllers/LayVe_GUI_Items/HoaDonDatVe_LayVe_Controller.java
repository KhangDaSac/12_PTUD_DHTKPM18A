package GUI.controllers.LayVe_GUI_Items;

import DTO.HoaDonDatVe;
import GUI.controllers.HuyVe_GUI_Controller;
import GUI.controllers.LayVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;
import utils.TimeFormat;

public class HoaDonDatVe_LayVe_Controller {

    @FXML
    private AnchorPane anpHoaDon;

    @FXML
    private Label lblSoThuTu;

    @FXML
    private Label lblMaCaLam;

    @FXML
    private Label lblMaHoaDon;

    @FXML
    private Label lblThoiGianLap;

    @FXML
    private Label lblTienCoc;

    @FXML
    private Label lblTongTien;

    @FXML
    private Label lblTrangThai;

    private HoaDonDatVe hoaDonDatVe;
    private LayVe_GUI_Controller layVe_gui_controller;
    private int soThuTu;
    private boolean dangChon;

    public boolean isDangChon() {
        return dangChon;
    }

    public void setDangChon(boolean dangChon) {
        this.dangChon = dangChon;
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public LayVe_GUI_Controller getLayVe_gui_controller() {
        return layVe_gui_controller;
    }

    public void setLayVe_gui_controller(HuyVe_GUI_Controller layVe_gui_controller) {
//        this.layVe_gui_controller = layVe_gui_controller;
    }


    public void khoiTao(){
        lblMaHoaDon.setText(hoaDonDatVe.getMaHoaDonDatVe());
        lblThoiGianLap.setText(TimeFormat.formatLocalDateTime(hoaDonDatVe.getThoiGianLap()));
        lblMaCaLam.setText(hoaDonDatVe.getCaLamViec().getMaCaLamViec());
        lblTongTien.setText(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienCuoi()));
        lblTienCoc.setText(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienDatCoc()));
        lblSoThuTu.setText(String.valueOf(soThuTu + 1));
        dangChon = false;
        anpHoaDon.getStylesheets().add(getClass().getResource("/css/LayVe_GUI.css").toExternalForm());
    }


    @FXML
    void anpHoaDonOnMouseClicked(MouseEvent event) {
        layVe_gui_controller.boChonTatCaHoaDon();
        chonHoaDon();


    }

    public void chonHoaDon(){
        dangChon = true;
        anpHoaDon.getStyleClass().removeAll("hoaDonKhongChon");
        anpHoaDon.getStyleClass().add("hoaDonChon");
    }

    public void boChonHoaDon(){
        dangChon = false;
        anpHoaDon.getStyleClass().removeAll("hoaDonChon");
        anpHoaDon.getStyleClass().add("hoaDonKhongChon");
    }

}
