package GUI.controllers;

import BUS.QuanLyChuyenTau_BUS;
import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import GUI.controllers.QuanLyChuyenTau_GUI_Items.ChuyenTau_QuanLyChuyenTau_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuanLyChuyenTau_GUI_Controller implements Initializable {

    @FXML
    private JFXButton btnTimChuyenTau;

    @FXML
    private DatePicker dapNgayKhoiHanh;

    @FXML
    private HBox hboxDanhSachChuyenTau;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblMaTuyenTau;

    @FXML
    private Label lblMoTaTuyenTau;

    @FXML
    private Label lblSoCho;

    @FXML
    private Label lblSoGaDiQua;

    @FXML
    private Label lblSoVe;

    @FXML
    private Label lblTenTuyenTau;

    @FXML
    private Label lblThoiGianDenGaCuoi;

    @FXML
    private Label lblThoiGianKhoiHanh;

    @FXML
    private Label lblSoVeDat;

    @FXML
    private ScrollPane scpDanhSachChuyenTau;

    @FXML
    private TextField txtMaChuyenTau;

    @FXML
    private TextField txtMaTuyenTau;

    private Main_Controller main_controller;

    public QuanLyChuyenTau_GUI_Controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    private ArrayList<ChuyenTau_QuanLyChuyenTau_Controller> quanLyChuyenTau_gui_controller_list = new ArrayList<ChuyenTau_QuanLyChuyenTau_Controller>();

    private ArrayList<ChuyenTau> danhSachChuyenTau = new ArrayList<>();

    private ChuyenTau chuyenTau;
    private ArrayList<ChiTietChuyenTau> chiTietChuyenTauList = new ArrayList<ChiTietChuyenTau>();

    @FXML
    void btnTimChuyenTauOnAction(ActionEvent event) {
        try {
            getDanhSachChuyenTau();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getDanhSachChuyenTau() throws IOException {
        String maChuyenTau = txtMaChuyenTau.getText();
        String maTuyenTau = txtMaTuyenTau.getText();
        LocalDate ngayKhoiHanh = dapNgayKhoiHanh.getValue();
        danhSachChuyenTau = QuanLyChuyenTau_BUS.getDanhSachChuyenTauTheo_MaChuyen_MaTuyen_NgayDi(maChuyenTau, maTuyenTau, ngayKhoiHanh);
        System.out.println(danhSachChuyenTau);
        hienThiDanhSachChuyenTau();
    }


    private void hienThiDanhSachChuyenTau() throws IOException {
        hboxDanhSachChuyenTau.getChildren().clear();
        if(danhSachChuyenTau.isEmpty()){
            return;
        }
        for(ChuyenTau chuyenTauHT : danhSachChuyenTau){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyChuyenTau_GUI_Items/ChuyenTau_QuanLyChuyenTau.fxml"));
            Parent anchorPane = loader.load();
            ChuyenTau_QuanLyChuyenTau_Controller controller = loader.getController();
            quanLyChuyenTau_gui_controller_list.add(controller);
            controller.setQuanLyChuyenTau_gui_controller(this);
            controller.setChuyenTau(chuyenTauHT);
            controller.khoiTao();

            hboxDanhSachChuyenTau.getChildren().add(anchorPane);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public QuanLyChuyenTau_GUI_Controller() {
    }
}
