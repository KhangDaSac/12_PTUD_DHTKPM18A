package GUI.controllers.QuanLyChuyenTau_GUI_Items;

import DTO.ChiTietChuyenTau;
import GUI.controllers.QuanLyChuyenTau_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utils.TimeFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class ChiTietChuyenTau_QuanLyChuyenTau_Controller implements Initializable {

    @FXML
    private AnchorPane anpTiepTuc;

    @FXML
    private Label lblDiaChiGa;

    @FXML
    private Label lblSoKm;

    @FXML
    private Label lblThoiGianDen;

    @FXML
    private Label lblThoiGianDi;

    @FXML
    private Label lblTenGa;

    @FXML
    private Label lblThuTuGa;


    private ChiTietChuyenTau chiTietChuyenTau;
    private QuanLyChuyenTau_GUI_Controller quanLyChuyenTau_gui_controller;

    public ChiTietChuyenTau getChiTietChuyenTau() {
        return chiTietChuyenTau;
    }

    public void setChiTietChuyenTau(ChiTietChuyenTau chiTietChuyenTau) {
        this.chiTietChuyenTau = chiTietChuyenTau;
    }

    public QuanLyChuyenTau_GUI_Controller getQuanLyChuyenTau_gui_controller() {
        return quanLyChuyenTau_gui_controller;
    }

    public void setQuanLyChuyenTau_gui_controller(QuanLyChuyenTau_GUI_Controller quanLyChuyenTau_gui_controller) {
        this.quanLyChuyenTau_gui_controller = quanLyChuyenTau_gui_controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(){
        lblThuTuGa.setText(String.format("%02d", chiTietChuyenTau.getThuTuGa()));
        lblTenGa.setText(chiTietChuyenTau.getGaTau().getTenGaTau());
        lblDiaChiGa.setText(chiTietChuyenTau.getGaTau().getDiaChi());
        lblThoiGianDen.setText(TimeFormat.formatLocalDateTime(chiTietChuyenTau.getThoiGianDen()));
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(chiTietChuyenTau.getThoiGianDi()));
        lblSoKm.setText("+" + chiTietChuyenTau.getSoKm() + " Km");
    }

    public void gaCuoi(){
        anpTiepTuc.setVisible(false);
    }
}
