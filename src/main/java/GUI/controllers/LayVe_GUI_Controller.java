package GUI.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class LayVe_GUI_Controller {

    @FXML
    private JFXButton btnBoChonTatCaVe;

    @FXML
    private JFXButton btnChonTatCaVe;

    @FXML
    private JFXButton btnLayVe;

    @FXML
    private JFXButton btnTimKiemKhachHang;

    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtMaKhachHang;

    @FXML
    private TextField txtSoDienThoai;

    @FXML
    private TextField txtTenKhachHang;

    @FXML
    private TextField txtTongTien;

    @FXML
    private VBox vboxChiTietPhieuDatVe;

    @FXML
    private VBox vboxDanhSachHoaDonDat;

    @FXML
    private VBox vboxDanhSachPhieuDatVe;

    private Main_Controller main_controller;

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    @FXML
    void btnBoChonTatCaVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnChonTatCaVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnLayVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnTimKiemKhachHangOnAction(ActionEvent event) {

    }

    @FXML
    void txtCCCDOnMouseClicked(MouseEvent event) {

    }

}
