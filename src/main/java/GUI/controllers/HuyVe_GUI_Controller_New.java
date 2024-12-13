package GUI.controllers;

import DAO.Ve_DAO;
import DTO.*;
import GUI.controllers.HuyVe_GUI_Items_Test.Ve_TimVe_HuyVe_Controller_Test;
import GUI.controllers.HuyVe_GUI_Items_Test.Ve_HuyVe_HuyVe_Controller_Test;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HuyVe_GUI_Controller_New implements Initializable {

    @FXML
    private JFXButton btnTimVe;

    @FXML
    private JFXButton btnThemVeHuy;

    @FXML
    private JFXButton btnXoaTatCaVe;

    @FXML
    private JFXButton btnChonVeHuy;

    @FXML
    private JFXButton btnTimKiemKhachHang;

    @FXML
    private JFXButton btnHuyVe;


    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtLePhi;

    @FXML
    private TextField txtMaKhachHang;

    @FXML
    private TextField txtMaVe;

    @FXML
    private TextField txtSoDienThoai;

    @FXML
    private TextField txtTenKhachHang;

    @FXML
    private TextField txtTongTienHoanTra;

    @FXML
    private VBox vboxVeHuy;

    @FXML
    private VBox vboxVeTim;

    private Ve_TimVe_HuyVe_Controller_Test ve_timVe_huyVe_controller_test;
    private Ve_HuyVe_HuyVe_Controller_Test ve_huyVe_huyVe_controller_test;
    private ArrayList<Ve_HuyVe_HuyVe_Controller_Test> ve_huyVe_controllers_list = new ArrayList<>();



    private HoaDonHuyVe hoaDonHuyVe = new HoaDonHuyVe();

    private ChiTietHoaDonHuyVe chiTietHoaDonHuyVe = new ChiTietHoaDonHuyVe();

    public ChiTietHoaDonHuyVe getChiTietHoaDonHuyVe() {
        return chiTietHoaDonHuyVe;
    }

    public void setChiTietHoaDonHuyVe(ChiTietHoaDonHuyVe chiTietHoaDonHuyVe) {
        this.chiTietHoaDonHuyVe = chiTietHoaDonHuyVe;
    }

    public Ve veTim = new Ve();

    public Ve getVeTim() {
        return veTim;
    }

    public void setVeTim(Ve veTim) {
        this.veTim = veTim;
    }

    public Ve veHuy = new Ve();

    public Ve getVeHuy() {
        return veTim;
    }

    public void setVeHuy(Ve veHuy) {
        this.veHuy = veHuy;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChiTietHoaDonHuyVe chiTietHoaDonHuyVe = new ChiTietHoaDonHuyVe();

    }

    public void timVe(String maVe){
        veTim = Ve_DAO.getVeTheoMaVe_HuyVe(maVe);
    }

    public void loadVeDaTim(){
        vboxVeTim.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyVe_GUI_Items_Test/Ve_HuyVe.fxml"));
            Parent anchorPane = loader.load();
            Ve_HuyVe_HuyVe_Controller_Test controller = loader.getController();
            ve_huyVe_huyVe_controller_test = (controller);
            controller.setHuyVe_gui_controller_new(this);
            controller.setVe(veTim);
            controller.khoiTao();
            vboxVeTim.getChildren().add(anchorPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void hienThiVeTim(Ve ve) {
        ve_huyVe_controllers_list.clear();
        vboxVeTim.getChildren().clear();
        if (ve == null ) {
            System.out.println("Ve rong");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyVe_GUI_Items_Test/Ve_TimVe.fxml"));
            Parent anchorPane = loader.load();
            Ve_TimVe_HuyVe_Controller_Test controller = loader.getController();
            ve_timVe_huyVe_controller_test = (controller);
            controller.setHuyVe_gui_controller_new(this);
            controller.setVe(ve);
            controller.khoiTao();
            vboxVeTim.getChildren().add(anchorPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void hienThiVeHuy(Ve ve) {
        ve_huyVe_controllers_list.clear();
        vboxVeHuy.getChildren().clear();
        if (ve == null ) {
            System.out.println("Ve rong");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyVe_GUI_Items_Test/Ve_HuyVe.fxml"));
            Parent anchorPane = loader.load();
            Ve_HuyVe_HuyVe_Controller_Test controller = loader.getController();
            ve_huyVe_controllers_list.add(controller);
            controller.setHuyVe_gui_controller_new(this);
            controller.setVe(ve);
            controller.khoiTao();
            vboxVeHuy.getChildren().add(anchorPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void hienThiLePhi(){
        txtLePhi.setText(String.valueOf(chiTietHoaDonHuyVe.lePhi()));
    }

    public void hienThiTienTraKhach(){
        txtTongTienHoanTra.setText(String.valueOf(chiTietHoaDonHuyVe.soTienHoanLai()));
    }

    public void xoaTienTraKhach(){
        txtTongTienHoanTra.setText("");
    }

    public void createDanhSachVeList(){
        ve_huyVe_controllers_list.remove(veHuy);
    }

    @FXML
    void btnTimVeOnAction(ActionEvent event) {
        String maVe = txtMaVe.getText();
        veTim = Ve_DAO.getVeTheoMaVe_HuyVe(maVe);
        hienThiVeTim(veTim);
    }

    @FXML
    void btnThemVeHuyOnAction(ActionEvent event) {
        if (veTim == null || !veTim.isSelected()) {
            System.out.println("Vé chưa được chọn hoặc không tồn tại!");
            return;
        }
        hienThiVeHuy(veTim);
    }

    @FXML
    void btnXoaTatCaVeOnAction(ActionEvent event) {
        vboxVeHuy.getChildren().clear();
    }

    @FXML
    void btnChonVeHuyOnAction(ActionEvent event) {

        hienThiLePhi();
        hienThiTienTraKhach();
    }


    @FXML
    void txtCCCDOnMouseClicked(MouseEvent event) {
        String CCCD = txtCCCD.getText();

    }

    @FXML
    void btnTimKiemKhachHangOnAction(ActionEvent event) {

    }

    @FXML
    void btnHuyVeOnAction(ActionEvent event) {
        
    }


}
