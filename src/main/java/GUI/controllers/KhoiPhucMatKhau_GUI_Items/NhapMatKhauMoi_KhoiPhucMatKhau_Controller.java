package GUI.controllers.KhoiPhucMatKhau_GUI_Items;

import BUS.QuanLyNhanVien_BUS;
import GUI.controllers.DangNhap_GUI_Controller;
import GUI.controllers.KhoiPhucMatKhau_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.HashPassword;

public class NhapMatKhauMoi_KhoiPhucMatKhau_Controller {

    @FXML
    private JFXButton btnQuayLai;

    @FXML
    private JFXButton btnHoanThanh;

    @FXML
    private Label lblThongBao;

    @FXML
    private Label lblThongBaoGmailGui;

    @FXML
    private TextField txtMatKhauMoi;

    private KhoiPhucMatKhau_GUI_Controller khoiPhucMatKhau_gui_controller;

    public KhoiPhucMatKhau_GUI_Controller getKhoiPhucMatKhau_gui_controller() {
        return khoiPhucMatKhau_gui_controller;
    }

    public void setKhoiPhucMatKhau_gui_controller(KhoiPhucMatKhau_GUI_Controller khoiPhucMatKhau_gui_controller) {
        this.khoiPhucMatKhau_gui_controller = khoiPhucMatKhau_gui_controller;
    }

    @FXML
    void btnQuayLaiOnAction(ActionEvent event) {
        khoiPhucMatKhau_gui_controller.chuyenTrangNhapNhanVien();
    }

    @FXML
    void btnHoanThanhOnAction(ActionEvent event) {
        String matKhauMoiBam = HashPassword.hashPassword(txtMatKhauMoi.getText());
        if(QuanLyNhanVien_BUS.doiMatKhau(khoiPhucMatKhau_gui_controller.getNhanVien().getMaNhanVien(), matKhauMoiBam)){
            System.out.println("doi thanh cong");
        }
    }

}
