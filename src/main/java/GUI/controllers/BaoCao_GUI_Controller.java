package GUI.controllers;

import DTO.CaLamViec;
import DTO.NhanVien;
import GUI.applications.Run;
import GUI.controllers.BaoCao_GUI_Items.PhieuKiemTien_BaoCao_Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class BaoCao_GUI_Controller implements Initializable {

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

    private JFXDialog dialog;

    private Main_Controller main_controller;

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    @FXML
    void btnLapPhieuKiemTienCuoiCaOnAction(ActionEvent event) {

    }

    @FXML
    void btnLapPhieuKiemTienDauCaOnAction(ActionEvent event) {
        if(main_controller.getPhieuKetToan() != null && main_controller.getPhieuKetToan().getCaLamViec() != null){
            main_controller.showMessagesDialog("Ca làm việc đã có phiếu kiểm tiền đầu ca");
            return;
        }
        hienThiPhieuKiemTienDauCa();
    }

    @FXML
    void btnXuatFileVaKetCaOnAction(ActionEvent event) {

    }

    public void hienThiPhieuKiemTienDauCa(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("/view/BaoCao_GUI_Items/PhieuKiemTien_BaoCao.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            PhieuKiemTien_BaoCao_Controller controller = fxmlLoader.getController();
            controller.setBaoCao_gui_controller(this);
            dialog = main_controller.showWindowDialog(anchorPane, "Phiếu kiểm tiền");
            controller.setDialog(dialog);
            controller.setNhanVienKiemTien(main_controller.getNhanVien());
            controller.setLoaiPhieuKiemTien("DC");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void capNhatThongTinCaLamViec(){
        if(main_controller.getPhieuKetToan() != null && main_controller.getPhieuKetToan().getCaLamViec() != null){
            CaLamViec caLamViec = main_controller.getPhieuKetToan().getCaLamViec();
            lblMaCaLamViec.setText(caLamViec.getMaCaLamViec());
            lblThoiGianBatDau.setText(TimeFormat.formatLocalDateTime(caLamViec.getThoiGianBatDau()));
            lblThoiGianKetThuc.setText(caLamViec.getThoiGianKetThuc() != null
                    ? TimeFormat.formatLocalDateTime(caLamViec.getThoiGianKetThuc())
                    : "Chưa có");

        }else{
            lblMaCaLamViec.setText("Chưa có");
            lblThoiGianBatDau.setText("Chưa có");
            lblThoiGianKetThuc.setText("Chưa có");
        }

        if(main_controller.getPhieuKetToan() != null
                && main_controller.getPhieuKetToan().getCaLamViec() != null
                && main_controller.getPhieuKetToan().getCaLamViec().getNhanVien() != null){
            NhanVien nhanVien = main_controller.getPhieuKetToan().getCaLamViec().getNhanVien();
            lblMaNhanVien.setText(nhanVien.getMaNhanVien());
            lblTenNhanVien.setText(nhanVien.getTenNhanVien());
        }else {
            lblMaNhanVien.setText("Chưa có");
            lblTenNhanVien.setText("Chưa có");
        }
    }

    public void capTongTienDauCa(){
        lblTongTienBatDauCa.setText(CurrencyFormat.currencyFormat(main_controller.getPhieuKetToan().getPhieuKiemTienDauCa().tongTien()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            capNhatThongTinCaLamViec();
        });
    }
}
