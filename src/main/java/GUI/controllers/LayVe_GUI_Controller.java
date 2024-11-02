package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyKhachHang_BUS;
import DTO.HoaDon;
import DTO.KhachHang;
import GUI.controllers.LayVe_GUI_Items.HoaDon_LayVe_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

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

    private KhachHang khachHang;

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    private ArrayList<HoaDon_LayVe_Controller> hoaDon_layVe_controllerList = new ArrayList<HoaDon_LayVe_Controller>();


    private ArrayList<HoaDon> hoaDonList;

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
        timKhachHang();
        if(khachHang != null)
            layDanhSachHoaDonDatTheoKhachHang();
    }

    public void timKhachHang(){
        String cccd = txtCCCD.getText();
        try {
            khachHang = QuanLyKhachHang_BUS.getKhachHangTheoCCCD(cccd);
            if(khachHang != null){
                txtCCCD.setEditable(false);
            }
            hienThiThongTinKhachHang();
        } catch (Exception e) {
            main_controller.showMessagesDialog(e.getMessage());
        }
    }

    public void hienThiThongTinKhachHang(){
        txtMaKhachHang.setText(khachHang.getMaKhachHang());
        txtTenKhachHang.setText(khachHang.getTenKhachHang());
        txtSoDienThoai.setText(khachHang.getSoDienThoai());
    }

    @FXML
    void txtCCCDOnMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            txtCCCD.setEditable(true);
            txtCCCD.selectAll();
            xoaThongTinKhachHang();
            hoaDonList = new ArrayList<HoaDon>();
            hienThiDanhSachHoaDonDat();
        }
    }

    public void xoaThongTinKhachHang(){
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSoDienThoai.setText("");
        khachHang = null;
    }

    public void layDanhSachHoaDonDatTheoKhachHang(){
        System.out.println(khachHang.getMaKhachHang());
        hoaDonList = QuanLyHoaDon_BUS.getDanhSachHoaDonDatTheoMaKhachHang(khachHang.getMaKhachHang());
        hienThiDanhSachHoaDonDat();
    }

    public void hienThiDanhSachHoaDonDat(){
        vboxDanhSachHoaDonDat.getChildren().clear();
        if(hoaDonList.isEmpty())
            return;
        int length = hoaDonList.size();
        for(int i = 0; i < length; i++){
            HoaDon hoaDon = hoaDonList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/HoaDon_LayVe.fxml"));
                Parent anchorPane = loader.load();
                HoaDon_LayVe_Controller controller = loader.getController();
                hoaDon_layVe_controllerList.add(controller);
                controller.setLayVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setHoaDon(hoaDon);
                controller.khoiTao();

                vboxDanhSachHoaDonDat.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }

}
