package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyKhachHang_BUS;
import DTO.*;
import GUI.controllers.ThongTinDatVe_GUI_Items.VeDat_ThongTinDatVe_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ThongTinDatVe_GUI_Controller implements Initializable {
    private HoaDonDatVe hoaDonDatVe;
    private Main_Controller main_controller;
    private KhachHang khachHang;

    private ArrayList<VeDat_ThongTinDatVe_Controller> veDat_thongTinDatVe_controller_list = new ArrayList<VeDat_ThongTinDatVe_Controller>();


    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    public HoaDonDatVe getHoaDonDatVe() {
        return hoaDonDatVe;
    }

    public void setHoaDonDatVe(HoaDonDatVe hoaDonDatVe) {
        this.hoaDonDatVe = hoaDonDatVe;
    }

    private ArrayList<ChiTietVeDat> danhSachChiTietVeDatDangChon = new ArrayList<ChiTietVeDat>();

    @FXML
    private JFXButton btnBanVe;

    @FXML
    private JFXButton btnBoChonTatCaCho;

    @FXML
    private JFXButton btnChonTatCaCho;

    @FXML
    private JFXButton btnQuayLai;

    @FXML
    private JFXButton btnThemNguoiDiTau;

    @FXML
    private JFXButton btnThemNguoiDat;

    @FXML
    private JFXButton btnTimKiemKhachHang;

    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtLoaiKhachHang;

    @FXML
    private TextField txtMaKhachHang;

    @FXML
    private TextField txtSoDienThoai;

    @FXML
    private TextField txtTenKhachHang;

    @FXML
    private TextField txtTongTien;

    @FXML
    private TextField txtTongTienCoc;

    @FXML
    private VBox vboxGioVeDat;

    @FXML
    private Label lblCCCDKhachHangMua;

    @FXML
    private Label lblMaKhachHangMua;

    @FXML
    private Label lblSoDienThoaiKhachHangMua;

    @FXML
    private Label lblTenKhachHangMua;

    @FXML
    void btnDatVeOnAction(ActionEvent event) {
        hoaDonDatVe.setThoiGianLap(LocalDateTime.now());
        try {
            if(QuanLyHoaDon_BUS.themHoaDon(hoaDonDatVe)){
//                CreatePDF.taoHoaDonBanVe(hoaDonDatVe);
//                for (Ve ve : hoaDonDatVe.getDanhSachVe()){
//                    CreatePDF.taoVe(ve);
//                }
                main_controller.showMessagesDialog("Đặt vé thành công");
                main_controller.quayLaiTrangDatVe();
            }else{
                main_controller.showMessagesDialog("Đặt vé thất bại");
            }
        } catch (Exception e) {
            main_controller.showMessagesDialog(e.getMessage());
        }
    }

    @FXML
    void btnQuayLaiOnAction(ActionEvent event) {
        main_controller.quayLaiTrangDatVe(hoaDonDatVe);
    }

    @FXML
    void btnThemNguoiDiTauOnAction(ActionEvent event) {
        themThongTinNguoiDiTau();
    }

    @FXML
    void btnThemNguoiDatOnAction(ActionEvent event) {
        themThongTinNguoiMua();
    }

    @FXML
    void btnTimKiemKhachHangOnAction(ActionEvent event) {
        timKhachHang();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            hienThiTongTienVaTongTienCoc();
        });
    }

    public void khoiTao(){
        try {
            capNhatGioVe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void capNhatGioVe() throws IOException {
        vboxGioVeDat.getChildren().clear();
        veDat_thongTinDatVe_controller_list.clear();
        int length = hoaDonDatVe.getDanhSachVeDat().size();
        if (length == 0){
            return;
        }
        for(int i = 0; i < length; i++){
            VeDat veDat = hoaDonDatVe.getDanhSachVeDat().get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinDatVe_GUI_Items/VeDat_ThongTinDatVe.fxml"));
            Parent anchorPane = loader.load();
            VeDat_ThongTinDatVe_Controller controller = loader.getController();
            veDat_thongTinDatVe_controller_list.add(controller);
            controller.setThongTinDatVe_gui_controller(this);

            controller.setVeDat(veDat);
            controller.setSoThuTu(i);
            controller.khoiTao();

            vboxGioVeDat.getChildren().add(anchorPane);
        }
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
        txtLoaiKhachHang.setText(khachHang.getLoaiKhachHang().getTenLoaiKhachHang());
        txtSoDienThoai.setText(khachHang.getSoDienThoai());
    }

    public void xoaThongTinKhachHang(){
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtLoaiKhachHang.setText("");
        txtSoDienThoai.setText("");
        khachHang = null;
    }

    @FXML
    void txtCCCDOnMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            txtCCCD.setEditable(true);
            txtCCCD.selectAll();
            xoaThongTinKhachHang();
        }
    }

    public void themThongTinNguoiMua(){
        if(khachHang == null)
            return;
        hoaDonDatVe.setKhachHangDatVe(khachHang);
        lblCCCDKhachHangMua.setText(hoaDonDatVe.getKhachHangDatVe().getCCCD());
        lblTenKhachHangMua.setText(hoaDonDatVe.getKhachHangDatVe().getTenKhachHang());
        lblSoDienThoaiKhachHangMua.setText(hoaDonDatVe.getKhachHangDatVe().getSoDienThoai());
        lblMaKhachHangMua.setText(hoaDonDatVe.getKhachHangDatVe().getMaKhachHang());
    }

    public void themThongTinNguoiDiTau(){
        if (khachHang == null)
            return;

        for(ChiTietVeDat chiTietVeDat : danhSachChiTietVeDatDangChon){
            chiTietVeDat.setKhachHang(khachHang);
        }

        danhSachChiTietVeDatDangChon.clear();

        try {
            capNhatGioVe();
            hienThiTongTienVaTongTienCoc();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void hienThiTongTienVaTongTienCoc(){
        txtTongTien.setText(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienCuoi()));
        txtTongTienCoc.setText(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienDatCoc()));
    }

    @FXML
    void btnBoChonTatCaChoOnAction(ActionEvent event) {
        danhSachChiTietVeDatDangChon.clear();
        try {
            capNhatGioVe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnChonTatCaChoOnAction(ActionEvent event) {
        danhSachChiTietVeDatDangChon.clear();
        for(VeDat veDat : hoaDonDatVe.getDanhSachVeDat()){
            for(ChiTietVeDat chiTietVeDat : veDat.getDanhSachChiTietVeDat()){
                danhSachChiTietVeDatDangChon.add(chiTietVeDat);
            }
        }
        try {
            capNhatGioVe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void chonCho(ChiTietVeDat chiTietVeDat){
        if(danhSachChiTietVeDatDangChon.contains(chiTietVeDat))
            danhSachChiTietVeDatDangChon.remove(chiTietVeDat);
        else
            danhSachChiTietVeDatDangChon.add(chiTietVeDat);
    }

    public boolean kiemTraChiTietVeDuocChon(ChiTietVeDat chiTietVeDat){
        return danhSachChiTietVeDatDangChon.contains(chiTietVeDat);
    }

    public void boChonTatCaVe(){
        for(VeDat_ThongTinDatVe_Controller controller : veDat_thongTinDatVe_controller_list){
            controller.khongChonVeDat();
        }
    }

}
