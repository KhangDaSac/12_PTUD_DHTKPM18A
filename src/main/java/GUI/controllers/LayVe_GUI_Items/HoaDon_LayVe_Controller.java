package GUI.controllers.LayVe_GUI_Items;

import DTO.HoaDon;
import DTO.TrangThaiHoaDon;
import GUI.controllers.LayVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;
import utils.TimeFormat;

public class HoaDon_LayVe_Controller {

    @FXML
    private AnchorPane anpVe;

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

    private HoaDon hoaDon;
    private LayVe_GUI_Controller layVe_gui_controller;
    private int soThuTu;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public LayVe_GUI_Controller getLayVe_gui_controller() {
        return layVe_gui_controller;
    }

    public void setLayVe_gui_controller(LayVe_GUI_Controller layVe_gui_controller) {
        this.layVe_gui_controller = layVe_gui_controller;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }


    public void khoiTao(){
        lblMaHoaDon.setText(hoaDon.getMaHoaDon());
        lblThoiGianLap.setText(TimeFormat.formatLocalDateTime(hoaDon.getThoiGianLap()));
        lblMaCaLam.setText(hoaDon.getCaLamViec().getMaCaLamViec());
        lblTongTien.setText(CurrencyFormat.currencyFormat(hoaDon.getTongTien()));
        lblTienCoc.setText(CurrencyFormat.currencyFormat(hoaDon.getTongTienDaDatCoc()));
        if(hoaDon.getTrangThaiHoaDon().equals(TrangThaiHoaDon.CHOLAYVE)){
            lblTrangThai.setText("Chờ lấy vé");
        }
    }


    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void anpXoaVeOnMouseCliced(MouseEvent event) {

    }

}
