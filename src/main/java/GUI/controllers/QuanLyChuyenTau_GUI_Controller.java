package GUI.controllers;

import BUS.QuanLyChuyenTau_BUS;
import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import GUI.controllers.QuanLyChuyenTau_GUI_Items.ChiTietChuyenTau_QuanLyChuyenTau_Controller;
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
import javafx.scene.layout.VBox;
import utils.TimeFormat;

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

    @FXML
    private VBox vboxLichTrinh;


    private Main_Controller main_controller;

    public QuanLyChuyenTau_GUI_Controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    private ArrayList<ChuyenTau_QuanLyChuyenTau_Controller> chuyenTau_quanLyChuyenTau_controller_list = new ArrayList<ChuyenTau_QuanLyChuyenTau_Controller>();
    private ArrayList<ChiTietChuyenTau_QuanLyChuyenTau_Controller> chiTietChuyenTau_quanLyChuyenTau_controller_list = new ArrayList<ChiTietChuyenTau_QuanLyChuyenTau_Controller>();
    private ArrayList<ChuyenTau> danhSachChuyenTau = new ArrayList<>();


    private ChuyenTau chuyenTau;
    public ChuyenTau getChuyenTau() {
        return chuyenTau;
    }

    public void setChuyenTau(ChuyenTau chuyenTau) {
        this.chuyenTau = chuyenTau;
    }

    private ArrayList<ChiTietChuyenTau> chiTietChuyenTauList = new ArrayList<ChiTietChuyenTau>();

    @FXML
    void btnTimChuyenTauOnAction(ActionEvent event) {
        try {
            getDanhSachChuyenTau();
        } catch (Exception e) {

        }
    }

    private void getDanhSachChuyenTau() throws Exception {
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
            chuyenTau_quanLyChuyenTau_controller_list.add(controller);
            controller.setQuanLyChuyenTau_gui_controller(this);
            controller.setChuyenTau(chuyenTauHT);
            controller.khoiTao();

            hboxDanhSachChuyenTau.getChildren().add(anchorPane);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblMaChuyenTau.setText("");
        lblMaTuyenTau.setText("");
        lblSoCho.setText("");
        lblSoVe.setText("");
        lblSoVeDat.setText("");
        lblTenTuyenTau.setText("");
        lblThoiGianKhoiHanh.setText("");
        lblThoiGianDenGaCuoi.setText("");
        lblSoGaDiQua.setText("");
        lblMoTaTuyenTau.setText("");

        hboxDanhSachChuyenTau.getChildren().clear();
        vboxLichTrinh.getChildren().clear();
        scpDanhSachChuyenTau.setOnScroll(event -> {
            double deltaX = event.getDeltaY();
            scpDanhSachChuyenTau.setHvalue(scpDanhSachChuyenTau.getHvalue() - deltaX * 10 / scpDanhSachChuyenTau.getContent().getBoundsInLocal().getWidth());
            event.consume();
        });
    }

    public QuanLyChuyenTau_GUI_Controller() {
    }

    public void boChonTatCaChuyenTau(){
        for(ChuyenTau_QuanLyChuyenTau_Controller controller : chuyenTau_quanLyChuyenTau_controller_list){
            controller.chinhMauKhongChon();
        }
    }

    public void hienThiThongTinChuyenTau(){
        if(chuyenTau == null)
            return;
        chiTietChuyenTauList = QuanLyChuyenTau_BUS.getLichTrinhTheoMaChuyenTau(chuyenTau.getMaChuyenTau());

        lblMaChuyenTau.setText(chuyenTau.getMaChuyenTau());
        lblMaTuyenTau.setText(chuyenTau.getTuyenTau().getMaTuyenTau());
        lblSoCho.setText(String.valueOf(chuyenTau.getSoLuongCho()));
        lblSoVe.setText(String.valueOf(chuyenTau.getSoLuongVe()));
        lblSoVeDat.setText(String.valueOf(chuyenTau.getSoLuongVeDat()));
        lblTenTuyenTau.setText(chuyenTau.getTuyenTau().getTenTuyenTau());
        lblThoiGianKhoiHanh.setText(TimeFormat.formatLocalDateTime(chiTietChuyenTauList.getFirst().getThoiGianDi()));
        lblThoiGianDenGaCuoi.setText(TimeFormat.formatLocalDateTime(chiTietChuyenTauList.getLast().getThoiGianDen()));
        lblSoGaDiQua.setText(String.valueOf(chiTietChuyenTauList.size()));
        lblMoTaTuyenTau.setText(chuyenTau.getTuyenTau().getMoTa());

        try {
            hienThiLichTrinh();
        }catch (Exception e){

        }

    }

    private void hienThiLichTrinh() throws IOException {
        vboxLichTrinh.getChildren().clear();
        chiTietChuyenTau_quanLyChuyenTau_controller_list.clear();
        for(ChiTietChuyenTau chiTietChuyenTau : chiTietChuyenTauList){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyChuyenTau_GUI_Items/ChiTietChuyenTau_QuanLyChuyenTau.fxml"));
            Parent anchorPane = loader.load();
            ChiTietChuyenTau_QuanLyChuyenTau_Controller controller = loader.getController();
            chiTietChuyenTau_quanLyChuyenTau_controller_list.add(controller);
            controller.setQuanLyChuyenTau_gui_controller(this);
            controller.setChiTietChuyenTau(chiTietChuyenTau);
            controller.khoiTao();

            vboxLichTrinh.getChildren().add(anchorPane);
        }

        chiTietChuyenTau_quanLyChuyenTau_controller_list.getLast().gaCuoi();
    }
}
