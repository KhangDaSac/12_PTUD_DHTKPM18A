package GUI.controllers.KhoiPhucMatKhau_GUI_Items;

import GUI.controllers.KhoiPhucMatKhau_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NhapMatKhauMoi_KhoiPhucMatKhau_Controller {

    @FXML
    private JFXButton btnQuayLai;

    @FXML
    private JFXButton btnTiepTuc;

    @FXML
    private Label lblThongBao;

    @FXML
    private Label lblThongBaoGmailGui;

    @FXML
    private TextField txtMaOTP;

    private KhoiPhucMatKhau_GUI_Controller khoiPhucMatKhau_gui_controller;

    public KhoiPhucMatKhau_GUI_Controller getKhoiPhucMatKhau_gui_controller() {
        return khoiPhucMatKhau_gui_controller;
    }

    public void setKhoiPhucMatKhau_gui_controller(KhoiPhucMatKhau_GUI_Controller khoiPhucMatKhau_gui_controller) {
        this.khoiPhucMatKhau_gui_controller = khoiPhucMatKhau_gui_controller;
    }

    @FXML
    void btnQuayLaiOnAction(ActionEvent event) {

    }

    @FXML
    void btnTiepTucOnAction(ActionEvent event) {

    }

}
