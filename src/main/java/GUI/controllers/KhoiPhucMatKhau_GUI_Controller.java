package GUI.controllers;

import DTO.NhanVien;
import GUI.controllers.KhoiPhucMatKhau_GUI_Items.NhapMaOTP_KhoiPhucMatKhau_Controller;
import GUI.controllers.KhoiPhucMatKhau_GUI_Items.NhapMatKhauMoi_KhoiPhucMatKhau_Controller;
import GUI.controllers.KhoiPhucMatKhau_GUI_Items.NhapNhanVien_KhoiPhucMatKhau_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class KhoiPhucMatKhau_GUI_Controller implements Initializable {

    @FXML
    private AnchorPane anpNoiDung;

    private NhanVien nhanVien;

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chuyenTrangNhapNhanVien();
    }

    public void chuyenTrangNhapMaOTP(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/KhoiPhucMatKhau_GUI_Items/NhapMaOTP_KhoiPhucMatKhau.fxml"));
            Parent anchorPane = loader.load();
            NhapMaOTP_KhoiPhucMatKhau_Controller controller = loader.getController();
            controller.setKhoiPhucMatKhau_gui_controller(this);
            anpNoiDung.getChildren().clear();
            anpNoiDung.getChildren().add(anchorPane);
            controller.khoiTao();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void chuyenTrangNhapNhanVien(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/KhoiPhucMatKhau_GUI_Items/NhapNhanVien_KhoiPhucMatKhau.fxml"));
            Parent anchorPane = loader.load();
            NhapNhanVien_KhoiPhucMatKhau_Controller controller = loader.getController();
            controller.setKhoiPhucMatKhau_gui_controller(this);
            anpNoiDung.getChildren().clear();
            anpNoiDung.getChildren().add(anchorPane);
        }catch (Exception e){

        }
    }

    public void chuyenTrangNhapMatKhauMoi(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/KhoiPhucMatKhau_GUI_Items/NhapMatKhauMoi_KhoiPhucMatKhau.fxml"));
            Parent anchorPane = loader.load();
            NhapMatKhauMoi_KhoiPhucMatKhau_Controller controller = loader.getController();
            controller.setKhoiPhucMatKhau_gui_controller(this);
            anpNoiDung.getChildren().clear();
            anpNoiDung.getChildren().add(anchorPane);
        }catch (Exception e){

        }
    }

    public KhoiPhucMatKhau_GUI_Controller() {
    }
}
