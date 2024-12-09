package GUI.controllers.KhoiPhucMatKhau_GUI_Items;

import BUS.QuanLyNhanVien_BUS;
import GUI.controllers.DangNhap_GUI_Controller;
import GUI.controllers.KhoiPhucMatKhau_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import utils.HashPassword;

public class NhapMatKhauMoi_KhoiPhucMatKhau_Controller {

    @FXML
    private JFXButton btnHoanThanh;

    @FXML
    private JFXButton btnQuayLai;

    @FXML
    private CheckBox chkHienThiMatKhau;

    @FXML
    private Label lblThongBao;

    @FXML
    private PasswordField txtMatKhauMoi;

    @FXML
    private TextField txtMatKhauMoiHienThi;

    @FXML
    private PasswordField txtNhapLaiMatKhauMoi;

    @FXML
    private TextField txtNhapLaiMatKhauMoiHienThi;

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
        String matKhauMoi = txtMatKhauMoi.getText();
        String matKhauMoiNhapLai = txtNhapLaiMatKhauMoi.getText();
        try{
            if(QuanLyNhanVien_BUS.doiMatKhau(khoiPhucMatKhau_gui_controller.getNhanVien().getMaNhanVien(), matKhauMoi, matKhauMoiNhapLai)){
                khoiPhucMatKhau_gui_controller.chuyenTrangDangNhap("Đổi mật khẩu thành công");
            }
        } catch (Exception e) {
            lblThongBao.setText(e.getMessage());
        }
    }

    @FXML
    void chkHienThiMatKhauOnAction(ActionEvent event) {
        txtMatKhauMoiHienThi.setVisible(chkHienThiMatKhau.isSelected());
        txtNhapLaiMatKhauMoiHienThi.setVisible(chkHienThiMatKhau.isSelected());

        txtMatKhauMoi.setVisible(!chkHienThiMatKhau.isSelected());
        txtNhapLaiMatKhauMoi.setVisible(!chkHienThiMatKhau.isSelected());
    }

    @FXML
    void txtMatKhauMoiHienThiOnKeyType(KeyEvent event) {
        txtMatKhauMoi.setText(txtMatKhauMoiHienThi.getText());
    }

    @FXML
    void txtMatKhauMoiOnKeyType(KeyEvent event) {
        txtMatKhauMoiHienThi.setText(txtMatKhauMoi.getText());
    }

    @FXML
    void txtNhapLaiMatKhauMoiHienThiOnKeyType(KeyEvent event) {
        txtNhapLaiMatKhauMoi.setText(txtNhapLaiMatKhauMoiHienThi.getText());
    }

    @FXML
    void txtNhapLaiMatKhauMoiOnKeyType(KeyEvent event) {
        txtNhapLaiMatKhauMoiHienThi.setText(txtNhapLaiMatKhauMoi.getText());
    }


}
