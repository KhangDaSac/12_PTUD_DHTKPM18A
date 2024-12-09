package GUI.controllers.LayVe_GUI_Items;

import DTO.HoaDonBanVe;
import DTO.HoaDonDatVe;
import DTO.TrangThaiVeDat;
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
    private Label lblMaCaLam;

    @FXML
    private Label lblMaHoaDon;

    @FXML
    private Label lblThoiGianLap;

    @FXML
    private Label lblTienCoc;

    @FXML
    private Label lblTongTien;

    private HoaDonDatVe hoaDonDatVe;
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

    public HoaDonDatVe getHoaDonDatVe() {
        return hoaDonDatVe;
    }

    public void setHoaDonDatVe(HoaDonDatVe hoaDonDatVe) {
        this.hoaDonDatVe = hoaDonDatVe;
    }

    public void khoiTao(){
        lblMaHoaDon.setText(hoaDonDatVe.getMaHoaDonDatVe());
        lblThoiGianLap.setText(TimeFormat.formatLocalDateTime(hoaDonDatVe.getThoiGianLap()));
        lblMaCaLam.setText(hoaDonDatVe.getCaLamViec().getMaCaLamViec());
        lblTongTien.setText(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienCuoi()));
        lblTienCoc.setText(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienDatCoc()));
        anpHoaDon.getStylesheets().add(getClass().getResource("/css/LayVe_GUI.css").toExternalForm());
        if(layVe_gui_controller.getHoaDonDatVeDangChon() != null && layVe_gui_controller.getHoaDonDatVeDangChon().equals(hoaDonDatVe)){
            chonHoaDon();
        }else{
            boChonHoaDon();
        }
    }

    @FXML
    void anpHoaDonOnMouseClicked(MouseEvent event) {
        layVe_gui_controller.hienThiDanhSachVeDat(hoaDonDatVe);
        layVe_gui_controller.setHoaDonDatVeDangChon(hoaDonDatVe);
        layVe_gui_controller.hienThiDanhSachHoaDonDat();
    }

    public void chonHoaDon(){
        anpHoaDon.getStyleClass().removeAll("hoaDonKhongChon");
        anpHoaDon.getStyleClass().add("hoaDonChon");
    }

    public void boChonHoaDon(){
        anpHoaDon.getStyleClass().removeAll("hoaDonChon");
        anpHoaDon.getStyleClass().add("hoaDonKhongChon");
    }

}
