package GUI.controllers;

import DTO.PhieuKetToan;
import GUI.applications.Run;
import GUI.controllers.BaoCao_GUI_Items.PhieuKiemTien_BaoCao_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        hienThiPhieuKiemTienMoi();
    }

    @FXML
    void btnXuatFileVaKetCaOnAction(ActionEvent event) {

    }

    public void hienThiPhieuKiemTienMoi(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("/view/BaoCao_GUI_Items/PhieuKiemTien_BaoCao.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            JFXButton button = (JFXButton)anchorPane.lookup("#btnThoat");
            JFXButton button2 = (JFXButton)anchorPane.lookup("#btnHoanThanh");
            PhieuKiemTien_BaoCao_Controller controller = fxmlLoader.getController();
            controller.setBaoCao_gui_controller(this);
            main_controller.showWindowDialog(anchorPane, "Phiếu kiểm tiền", button);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
