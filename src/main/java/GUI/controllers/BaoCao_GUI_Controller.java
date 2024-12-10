package GUI.controllers;

import DTO.PhieuKetToan;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BaoCao_GUI_Controller {

    @FXML
    private JFXButton btnLapPhieuKiemTienCuoiCa;

    @FXML
    private JFXButton btnLapPhieuKiemTienDauCa;

    @FXML
    private JFXButton btnXuatFileVaKetCa;

    @FXML
    private Label lblMaCaLamViec;

    @FXML
    private Label lblMaNhanVien;

    @FXML
    private Label lblSoLuongHoaDonBanVe;

    @FXML
    private Label lblSoLuongHoaDonDatVe;

    @FXML
    private Label lblSoLuongHoaDonDoiVe;

    @FXML
    private Label lblSoLuongHoaDonHuyDatVe;

    @FXML
    private Label lblSoLuongHoaDonHuyVe;

    @FXML
    private Label lblSoLuongHoaDonLayVe;

    @FXML
    private Label lblTenNhanVien;

    @FXML
    private Label lblThoiGianBatDau;

    @FXML
    private Label lblThoiGianKetThuc;

    @FXML
    private Label lblTongTienBatDauCa;

    @FXML
    private Label lblTongTienChenhLech;

    @FXML
    private Label lblTongTienHoaDonBanVe;

    @FXML
    private Label lblTongTienHoaDonDatVe;

    @FXML
    private Label lblTongTienHoaDonDoiVe;

    @FXML
    private Label lblTongTienHoaDonHuyDatVe;

    @FXML
    private Label lblTongTienHoaDonHuyVe;

    @FXML
    private Label lblTongTienHoaDonLayVe;

    @FXML
    private Label lblTongTienKetThucCa;

    @FXML
    private Label lblTongTienTrongCa;

    private Main_Controller main_controller;
    private PhieuKetToan phieuKetToan;

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    public PhieuKetToan getPhieuKetToan() {
        return phieuKetToan;
    }

    public void setPhieuKetToan(PhieuKetToan phieuKetToan) {
        this.phieuKetToan = phieuKetToan;
    }

    @FXML
    void btnLapPhieuKiemTienCuoiCaOnAction(ActionEvent event) {

    }

    @FXML
    void btnLapPhieuKiemTienDauCaOnAction(ActionEvent event) {

    }

    @FXML
    void btnXuatFileVaKetCaOnAction(ActionEvent event) {

    }

}
