package GUI.controllers.BaoCao_GUI_Items;

import BUS.QuanLyCaLamViec_BUS;
import DTO.CaLamViec;
import DTO.PhieuKetToan;
import DTO.PhieuKiemTien;
import GUI.controllers.BaoCao_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PhieuKiemTien_BaoCao_Controller implements Initializable {

    @FXML
    private JFXButton btnChonNhanVien1;

    @FXML
    private JFXButton btnChonNhanVien2;

    @FXML
    private JFXButton btnHoanThanh;

    @FXML
    private JFXButton btnThoat;

    @FXML
    private Label lblTenNhanVien1;

    @FXML
    private Label lblTenNhanVien2;

    @FXML
    private Label lblTongTien;

    @FXML
    private TextField txtSoLuongTo1000;

    @FXML
    private TextField txtSoLuongTo10000;

    @FXML
    private TextField txtSoLuongTo100000;

    @FXML
    private TextField txtSoLuongTo2000;

    @FXML
    private TextField txtSoLuongTo20000;

    @FXML
    private TextField txtSoLuongTo200000;

    @FXML
    private TextField txtSoLuongTo5000;

    @FXML
    private TextField txtSoLuongTo50000;

    @FXML
    private TextField txtSoLuongTo500000;

    @FXML
    private TextField txtmaNhanVien1;

    @FXML
    private TextField txtmaNhanVien2;

    private String loaiPhieuKiemTien;

    private PhieuKiemTien phieuKiemTien;
    private BaoCao_GUI_Controller baoCao_gui_controller;

    public BaoCao_GUI_Controller getBaoCao_gui_controller() {
        return baoCao_gui_controller;
    }

    public void setBaoCao_gui_controller(BaoCao_GUI_Controller baoCao_gui_controller) {
        this.baoCao_gui_controller = baoCao_gui_controller;
    }

    @FXML
    void btnHoanThanhOnAction(ActionEvent event) {

    }
    @FXML
    void btnThoatOnAction(ActionEvent event) {

    }

    private void taoPhieuKiemTienDauCa(){
        String maCaLamViec = QuanLyCaLamViec_BUS.taoMaCaLamViecMoi();
        CaLamViec caLamViec = new CaLamViec(maCaLamViec, LocalDateTime.now(), null, baoCao_gui_controller.getMain_controller().getNhanVien());
        System.out.println(caLamViec.getMaCaLamViec());

        PhieuKetToan phieuKetToan = new PhieuKetToan("PKTO" + maCaLamViec.substring(3), caLamViec, LocalDateTime.now());
        phieuKetToan.setPhieuKiemTienDauCa(phieuKiemTien);
        if(!QuanLyCaLamViec_BUS.taoCaLamViecMoi(phieuKetToan))
            return;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtSoLuongTo1000.setText("0");
        txtSoLuongTo1000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo1000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo1000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo1000.setText(trimmedValue);
            }
        });


        txtSoLuongTo2000.setText("0");
        txtSoLuongTo2000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo2000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo2000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo2000.setText(trimmedValue);
            }
        });

        txtSoLuongTo5000.setText("0");
        txtSoLuongTo5000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo5000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo5000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo5000.setText(trimmedValue);
            }
        });


        txtSoLuongTo10000.setText("0");
        txtSoLuongTo10000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo10000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo10000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo10000.setText(trimmedValue);
            }
        });


        txtSoLuongTo20000.setText("0");
        txtSoLuongTo20000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo20000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo20000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo20000.setText(trimmedValue);
            }
        });


        txtSoLuongTo50000.setText("0");
        txtSoLuongTo50000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo50000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo50000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo50000.setText(trimmedValue);
            }
        });


        txtSoLuongTo100000.setText("0");
        txtSoLuongTo100000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo100000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo100000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo100000.setText(trimmedValue);
            }
        });


        txtSoLuongTo200000.setText("0");
        txtSoLuongTo200000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo200000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo200000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo200000.setText(trimmedValue);
            }
        });


        txtSoLuongTo500000.setText("0");
        txtSoLuongTo500000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo500000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo500000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo500000.setText(trimmedValue);
            }
        });
    }
}
