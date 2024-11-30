package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyKhachHang_BUS;
import DTO.*;
import GUI.controllers.ThongTinBanVe_GUI_Items.ChiTietVe_ThongTinBanVe_Controller;
import GUI.controllers.ThongTinBanVe_GUI_Items.Ve_ThongTinBanVe_Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;

public class ThongTinBanVe_GUI_Controller implements Initializable {
    private HoaDonBanVe hoaDonBanVe;
    private Main_Controller main_controller;
    private KhachHang khachHang;

    private ArrayList<Ve_ThongTinBanVe_Controller> ve_thongTinBanVe_controller_list = new ArrayList<Ve_ThongTinBanVe_Controller>();

    public HoaDonBanVe getHoaDonBanVe() {
        return hoaDonBanVe;
    }

    public void setHoaDonBanVe(HoaDonBanVe hoaDonBanVe) {
        this.hoaDonBanVe = hoaDonBanVe;
    }

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    private ArrayList<ChiTietVe> danhSachChiTietVeDangChon = new ArrayList<ChiTietVe>();

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
    private JFXButton btnThemNguoiMua;

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
    private VBox vboxGioVe;

    @FXML
    private Label lblCCCDKhachHangMua;

    @FXML
    private Label lblMaKhachHangMua;

    @FXML
    private Label lblSoDienThoaiKhachHangMua;

    @FXML
    private Label lblTenKhachHangMua;


    private int veDangChon;

    public int getVeDangChon() {
        return veDangChon;
    }

    public void setVeDangChon(int veDangChon) {
        this.veDangChon = veDangChon;
    }

    @FXML
    void btnBanVeOnAction(ActionEvent event) {
        hoaDonBanVe.setThoiGianLap(LocalDateTime.now());
        hoaDonBanVe.setCaLamViec(new CaLamViec("CLV010125C"));
        try {
            if(QuanLyHoaDon_BUS.themHoaDon(hoaDonBanVe)){
                hoaDonBanVe = null;
                main_controller.showMessagesDialog("Bán vé thành công");
                main_controller.quayLaiTrangBanVe(hoaDonBanVe);
            }else{
                main_controller.showMessagesDialog("Bán vé thất bại");
            }
        } catch (Exception e) {
            main_controller.showMessagesDialog(e.getMessage());
        }
    }

    @FXML
    void btnQuayLaiOnAction(ActionEvent event) {
        hoaDonBanVe.setKhachHangMuaVe(null);
        main_controller.quayLaiTrangBanVe(hoaDonBanVe);
    }

    @FXML
    void btnThemNguoiDiTauOnAction(ActionEvent event) {
        themThongTinNguoiDiTau();
    }

    @FXML
    void btnThemNguoiMuaOnAction(ActionEvent event) {
        themThongTinNguoiMua();
    }

    @FXML
    void btnTimKiemKhachHangOnAction(ActionEvent event) {
        timKhachHang();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            hienThiTongTien();
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
        vboxGioVe.getChildren().clear();
        ve_thongTinBanVe_controller_list.clear();
        int length = hoaDonBanVe.getDanhSachVe().size();
        if (length == 0){
            return;
        }
        for(int i = 0; i < length; i++){
            Ve ve = hoaDonBanVe.getDanhSachVe().get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinBanVe_GUI_Items/Ve_ThongTinBanVe.fxml"));
            Parent anchorPane = loader.load();
            Ve_ThongTinBanVe_Controller controller = loader.getController();
            ve_thongTinBanVe_controller_list.add(controller);
            controller.setThongTinBanVe_gui_controller(this);

            //controller.setDuThongTinNguoiDiTau(kiemTraDaThemDayDuThongTinNguoiDiTauCuaVe(ve));

            controller.setVe(ve);
            controller.setSoThuTu(i);
            controller.khoiTao();

            vboxGioVe.getChildren().add(anchorPane);
        }
        ve_thongTinBanVe_controller_list.get(veDangChon).chonVe();
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
        hoaDonBanVe.setKhachHangMuaVe(khachHang);
        lblCCCDKhachHangMua.setText(hoaDonBanVe.getKhachHangMuaVe().getCCCD());
        lblTenKhachHangMua.setText(hoaDonBanVe.getKhachHangMuaVe().getTenKhachHang());
        lblSoDienThoaiKhachHangMua.setText(hoaDonBanVe.getKhachHangMuaVe().getSoDienThoai());
        lblMaKhachHangMua.setText(hoaDonBanVe.getKhachHangMuaVe().getMaKhachHang());
    }

    public void themThongTinNguoiDiTau(){
        if (khachHang == null)
            return;

        for(ChiTietVe chiTietVe : danhSachChiTietVeDangChon){
            chiTietVe.setKhachHang(khachHang);
        }

        danhSachChiTietVeDangChon.clear();

        try {
            capNhatGioVe();
            hienThiTongTien();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void hienThiTongTien(){
        txtTongTien.setText(CurrencyFormat.currencyFormat(hoaDonBanVe.tongTienCuoi()));
    }

    public boolean kiemTraDaThemDayDuThongTinNguoiDiTauCuaVe(Ve ve){
        for(ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()){
            if(chiTietVe.getVe().equals(ve)){
                if(chiTietVe.getKhachHang() == null)
                    return false;
            }
        }
        return true;
    }

    @FXML
    void btnBoChonTatCaChoOnAction(ActionEvent event) {
        danhSachChiTietVeDangChon.clear();
        try {
            capNhatGioVe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnChonTatCaChoOnAction(ActionEvent event) {
        danhSachChiTietVeDangChon.clear();
        for(Ve ve : hoaDonBanVe.getDanhSachVe()){
            for(ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()){
                danhSachChiTietVeDangChon.add(chiTietVe);
            }
        }
        try {
            capNhatGioVe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void chonCho(ChiTietVe chiTietVe){
        if(danhSachChiTietVeDangChon.contains(chiTietVe))
            danhSachChiTietVeDangChon.remove(chiTietVe);
        else
            danhSachChiTietVeDangChon.add(chiTietVe);
    }

    public boolean kiemTraChiTietVeDuocChon(ChiTietVe chiTietVe){
        return danhSachChiTietVeDangChon.contains(chiTietVe);
    }

    public void boChonTatCaVe(){
        for(Ve_ThongTinBanVe_Controller controller : ve_thongTinBanVe_controller_list){
            controller.khongChonVe();
        }
    }

}
