package GUI.controllers.KhoiPhucMatKhau_GUI_Items;

import BUS.QuanLyNhanVien_BUS;
import DTO.NhanVien;
import GUI.controllers.KhoiPhucMatKhau_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NhapNhanVien_KhoiPhucMatKhau_Controller implements Initializable {

    @FXML
    private JFXButton btnTiepTuc;

    @FXML
    private Label lblThongBao;

    @FXML
    private TextField txtMaNhanVien;

    private KhoiPhucMatKhau_GUI_Controller khoiPhucMatKhau_gui_controller;

    public KhoiPhucMatKhau_GUI_Controller getKhoiPhucMatKhau_gui_controller() {
        return khoiPhucMatKhau_gui_controller;
    }

    public void setKhoiPhucMatKhau_gui_controller(KhoiPhucMatKhau_GUI_Controller khoiPhucMatKhau_gui_controller) {
        this.khoiPhucMatKhau_gui_controller = khoiPhucMatKhau_gui_controller;
    }

    private void getNhanVien(){
        try {
            String maNhanVien = txtMaNhanVien.getText();
            NhanVien nhanVien = QuanLyNhanVien_BUS.getNhanVienTheoMaNhanVien(maNhanVien);
            khoiPhucMatKhau_gui_controller.setNhanVien(nhanVien);
        } catch (Exception e) {
            lblThongBao.setText(e.getMessage());
        }
    }

    @FXML
    void btnTiepTucOnAction(ActionEvent event) {
        getNhanVien();
        if(khoiPhucMatKhau_gui_controller.getNhanVien() == null)
            return;
        khoiPhucMatKhau_gui_controller.chuyenTrangNhapMaOTP();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
