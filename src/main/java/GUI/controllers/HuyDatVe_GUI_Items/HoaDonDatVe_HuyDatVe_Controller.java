package GUI.controllers.HuyDatVe_GUI_Items;

import DTO.HoaDonDatVe;
import GUI.controllers.HuyDatVe_GUI_Controller;
import com.jfoenix.controls.JFXBadge;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;
import utils.TimeFormat;
import javafx.scene.control.Label;
import java.awt.*;


public class HoaDonDatVe_HuyDatVe_Controller {
    @FXML
    private AnchorPane anpHoaDon;
    @FXML
    private Label lblMaHoaDon;
    @FXML
    private Label lblThoiGianLap;
    @FXML
    private Label lblMaCaLam;
    @FXML
    private Label lblTongTien;
    @FXML
    private Label lblTienCoc;
    @FXML
    private Label lblMaKhachHang;
    @FXML
    private Label lblTenKhachHang;
    public void anpHoaDonOnMouseClicked(MouseEvent mouseEvent) {
        huyDatVe_gui_controller.hienThiDanhSachVeDat(hoaDonDatVe);
        huyDatVe_gui_controller.setHoaDonDatVeDangChon(hoaDonDatVe);
        huyDatVe_gui_controller.hienThiDanhSachHoaDonDat();
    }
    private HoaDonDatVe hoaDonDatVe;
    private HuyDatVe_GUI_Controller huyDatVe_gui_controller;
    private int soThuTu;

    public HoaDonDatVe getHoaDonDatVe() {
        return hoaDonDatVe;
    }

    public void setHoaDonDatVe(HoaDonDatVe hoaDonDatVe) {
        this.hoaDonDatVe = hoaDonDatVe;
    }

    public HuyDatVe_GUI_Controller getHuyDatVe_gui_controller() {
        return huyDatVe_gui_controller;
    }

    public void setHuyDatVe_gui_controller(HuyDatVe_GUI_Controller huyDatVe_gui_controller) {
        this.huyDatVe_gui_controller = huyDatVe_gui_controller;
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }
    public void khoiTao(){
        lblMaHoaDon.setText(hoaDonDatVe.getMaHoaDonDatVe());
        lblThoiGianLap.setText(TimeFormat.formatLocalDateTime(hoaDonDatVe.getThoiGianLap()));
        lblMaCaLam.setText(hoaDonDatVe.getCaLamViec().getMaCaLamViec());
        lblTongTien.setText(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienCuoi()));
        lblTienCoc.setText(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienDatCoc()));
        lblMaKhachHang.setText(hoaDonDatVe.getKhachHangDatVe().getMaKhachHang());
        lblTenKhachHang.setText(hoaDonDatVe.getKhachHangDatVe().getTenKhachHang());
        anpHoaDon.getStylesheets().add(getClass().getResource("/css/LayVe_GUI.css").toExternalForm());

        if(huyDatVe_gui_controller.getHoaDonDatVeDangChon() != null && huyDatVe_gui_controller.getHoaDonDatVeDangChon().equals(hoaDonDatVe)){
            chonHoaDon();
        }else{
            boChonHoaDon();
        }
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
