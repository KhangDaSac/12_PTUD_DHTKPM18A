package GUI.controllers;

import BUS.QuanLyCaLamViec_BUS;
import BUS.QuanLyNhanVien_BUS;
import DTO.CaLamViec;
import DTO.NhanVien;
import GUI.applications.Run;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.ShowMessagesDialog;

import java.time.LocalDateTime;

public class DangNhap_GUI_Controller {

    @FXML
    private StackPane stpDangNhap;

    @FXML
    private JFXButton btnDangNhap;

    @FXML
    private CheckBox chkHienThiMatKhau;

    @FXML
    private Label lblQuenMatKhau;

    @FXML
    private PasswordField txtMatKhau;

    @FXML
    private TextField txtMatKhauHienThi;

    @FXML
    private TextField txtTenDangNhap;

    @FXML
    private Label lblThongBao;

    @FXML
    void btnDangNhapOnAction(ActionEvent event) {
        NhanVien nhanVien = null;
        try {
            nhanVien = dangNhap();
            if(nhanVien != null){
                Window window = stpDangNhap.getScene().getWindow();
                Stage stage = (Stage) window;
                FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("/view/KhungGiaoDien.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Main_Controller main_controller = fxmlLoader.getController();
                main_controller.setNhanVien(nhanVien);
                stage.setScene(scene);
                stage.centerOnScreen();
                main_controller.hienThiPhieuKiemTienDauCa();
            }
        } catch (Exception e) {
            lblThongBao.setText(e.getMessage());
        }
    }

    @FXML
    void chkHienThiMatKhauOnAction(ActionEvent event) {
        txtMatKhauHienThi.setVisible(chkHienThiMatKhau.isSelected());
        txtMatKhau.setVisible(!chkHienThiMatKhau.isSelected());
    }

    @FXML
    void lblQuenMatKhauOnMouseClicked(MouseEvent event) {
        chuyenTrangKhoiPhucMatKhau();
    }
    public void chuyenTrangKhoiPhucMatKhau(){
        try {
            Window window = stpDangNhap.getScene().getWindow();
            Stage stage = (Stage) window;
            FXMLLoader fxmlLoaderDangNhap = new FXMLLoader(Run.class.getResource("/view/KhoiPhucMatKhau_GUI.fxml"));
            Scene scene = new Scene(fxmlLoaderDangNhap.load());
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public NhanVien dangNhap() throws Exception {
        String tenDangNhap = txtTenDangNhap.getText();
        String matKhau = txtMatKhau.getText();
        return QuanLyNhanVien_BUS.dangNhap(tenDangNhap, matKhau);
    }

    public void showMessagesDialog(String messages){
        ShowMessagesDialog.showDialog(stpDangNhap, "Thông báo", messages, "OK");
    }

    @FXML
    void txtMatKhauHienThiOnKeyType(KeyEvent event) {
        txtMatKhau.setText(txtMatKhauHienThi.getText());
    }

    @FXML
    void txtMatKhauOnKeyType(KeyEvent event) {
        txtMatKhauHienThi.setText(txtMatKhau.getText());
    }
}
