package GUI.controllers;

import BUS.QuanLyNhanVien_BUS;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DangNhap_GUI_Controller {

    @FXML
    private JFXButton btnDangNhap;

    @FXML
    private CheckBox chkHienThiMatKhau;

    @FXML
    private Label lblQuenMatKhau;

    @FXML
    private PasswordField txtMatKhau;

    @FXML
    private TextField txtTenDangNhap;

    @FXML
    private Label lblThongBao;

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void btnDangNhapOnAction(ActionEvent event) {
        NhanVien nhanVien = null;
        try {
            nhanVien = dangNhap();
            if(nhanVien != null){
                FXMLLoader fxmlLoaderKhungGiaoDien = new FXMLLoader(Run.class.getResource("/view/KhungGiaoDien.fxml"));
                Scene sceneKhungGiaoDien = new Scene(fxmlLoaderKhungGiaoDien.load());
                Main_Controller main_controller = fxmlLoaderKhungGiaoDien.getController();
                main_controller.setStage(stage);
                main_controller.setNhanVien(nhanVien);
                stage.setResizable(true);
                stage.setMaximized(true);
                stage.setScene(sceneKhungGiaoDien);
            }
        } catch (Exception e) {
            lblThongBao.setText(e.getMessage());
        }
    }

    @FXML
    void chkHienThiMatKhauOnAction(ActionEvent event) {

    }

    @FXML
    void lblQuenMatKhauOnMouseClicked(MouseEvent event) {
        chuyenTrangKhoiPhucMatKhau();
    }
    public void chuyenTrangKhoiPhucMatKhau(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("/view/KhoiPhucMatKhau_GUI.fxml"));
            Parent root = fxmlLoader.load();
            Scene sceneKhungGiaoDien = new Scene(root);

            KhoiPhucMatKhau_GUI_Controller controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.setScene(sceneKhungGiaoDien);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public NhanVien dangNhap() throws Exception {
        String tenDangNhap = txtTenDangNhap.getText();
        String matKhau = txtMatKhau.getText();
        NhanVien nhanVien = QuanLyNhanVien_BUS.dangNhap(tenDangNhap, matKhau);
        if(nhanVien != null)
            return nhanVien;
        else
            throw new Exception("Tài khoản hoặc mật khẩu không chính xác");
    }
}
