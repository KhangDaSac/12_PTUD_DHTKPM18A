package GUI.controllers;

import BUS.QuanLyKhachHang_BUS;
import DTO.ChiTietVe;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.Ve;
import GUI.controllers.ThongTinBanVe_GUI_Items.ChiTietVe_ThongTinBanVe_Controller;
import GUI.controllers.ThongTinBanVe_GUI_Items.Ve_ThongTinBanVe_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;

public class ThongTinBanVe_GUI_Controller implements Initializable {
    private HoaDon hoaDon;
    private ArrayList<Ve> danhSachVe;
    private ArrayList<ChiTietVe> danhSachChiTietVe;
    private Main_Controller main_controller;
    private KhachHang khachHang;


    private ArrayList<Ve_ThongTinBanVe_Controller> veControllerList = new ArrayList<Ve_ThongTinBanVe_Controller>();
    private ArrayList<ChiTietVe_ThongTinBanVe_Controller> chiTietVeControllerList = new ArrayList<ChiTietVe_ThongTinBanVe_Controller>();

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ArrayList<Ve> getDanhSachVe() {
        return danhSachVe;
    }

    public void setDanhSachVe(ArrayList<Ve> danhSachVe) {
        this.danhSachVe = danhSachVe;
    }

    public ArrayList<ChiTietVe> getDanhSachChiTietVe() {
        return danhSachChiTietVe;
    }

    public void setDanhSachChiTietVe(ArrayList<ChiTietVe> danhSachChiTietVe) {
        this.danhSachChiTietVe = danhSachChiTietVe;
    }

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    @FXML
    private JFXButton btnBanVe;

    @FXML
    private JFXButton btnQuayLai;

    @FXML
    private JFXButton btnThemKhachHang;

    @FXML
    private JFXButton btnThemNguoiDiTau;

    @FXML
    private JFXButton btnThemNguoiMua;

    @FXML
    private JFXButton btnTimKiemKhachHang;

    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtCCCDKhachHangMuaVe;

    @FXML
    private TextField txtLoaiKhachHang;

    @FXML
    private TextField txtMaKhachHang;

    @FXML
    private TextField txtMaKhachHangMuaVe;

    @FXML
    private TextField txtSoDienThoai;

    @FXML
    private TextField txtSoDienThoaiKhachHangMuaVe;

    @FXML
    private TextField txtTenKhachHang;

    @FXML
    private TextField txtTenKhachHangMuaVe;

    @FXML
    private TextField txtTongTien;

    @FXML
    private VBox vboxChiTietVe;

    @FXML
    private VBox vboxGioVe;

    @FXML
    void btnBanVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnQuayLaiOnAction(ActionEvent event) {

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
        hoaDon = new HoaDon();
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
        veControllerList.clear();
        int length = danhSachVe.size();
        for(int i = 0; i < length; i++){
            Ve ve = danhSachVe.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinBanVe_GUI_Items/Ve_ThongTinBanVe.fxml"));
            Parent anchorPane = loader.load();
            Ve_ThongTinBanVe_Controller controller = loader.getController();
            veControllerList.add(controller);
            controller.setThongTinBanVe_gui_controller(this);

            controller.setVe(ve);
            controller.setSoThuTu(i);
            controller.khoiTao();

            vboxGioVe.getChildren().add(anchorPane);
        }

        veControllerList.getFirst().chonVe();
    }

    public void capNhatChiTietVe(Ve ve) throws IOException {
        vboxChiTietVe.getChildren().clear();
        chiTietVeControllerList.clear();
        if(ve == null)
            return;
        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            if(chiTietVe.getVe().equals(ve)){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinBanVe_GUI_Items/ChiTietVe_ThongTinBanVe.fxml"));
                Parent anchorPane = loader.load();
                ChiTietVe_ThongTinBanVe_Controller controller = loader.getController();
                chiTietVeControllerList.add(controller);
                controller.setThongTinBanVe_gui_controller(this);

                controller.setChiTietVe(chiTietVe);
                controller.khoiTao();

                vboxChiTietVe.getChildren().add(anchorPane);
            }
        }
    }

    public void boChonTatCaVe(){
        for(Ve_ThongTinBanVe_Controller ve_controller : veControllerList){
            ve_controller.khongChonVe();
        }
    }

    public void boChonTatCaChiTietVe(){
        for(ChiTietVe_ThongTinBanVe_Controller chiTietVe_controller : chiTietVeControllerList){
            chiTietVe_controller.khongChonChiTietVe();
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(e.getMessage());
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();

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
    void btnThemKhachHang(ActionEvent event) {

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
        hoaDon.setKhachHangMua(khachHang);
        txtCCCDKhachHangMuaVe.setText(hoaDon.getKhachHangMua().getCCCD());
        txtTenKhachHangMuaVe.setText(hoaDon.getKhachHangMua().getTenKhachHang());
        txtSoDienThoaiKhachHangMuaVe.setText(hoaDon.getKhachHangMua().getSoDienThoai());
        txtMaKhachHangMuaVe.setText(hoaDon.getKhachHangMua().getMaKhachHang());
    }

    public void themThongTinNguoiDiTau(){
        if (khachHang == null)
            return;
        for(ChiTietVe_ThongTinBanVe_Controller controller : chiTietVeControllerList){
            if(controller.isDangChon()){
                controller.getChiTietVe().setKhachHang(khachHang);
                controller.capNhatLaiThongTinKhachHang();
            }
        }
        Ve.tinhTienCacVe(danhSachVe, danhSachChiTietVe);
        try {
            capNhatGioVe();
            tinhTongTienHoaDon();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double tinhTongTienHoaDon(){
        double tongTienHoaDon = 0;
        for (Ve ve: danhSachVe){
            tongTienHoaDon += ve.tinhTongTienVeCuoi();
        }
        txtTongTien.setText(CurrencyFormat.currencyFormat(tongTienHoaDon));
        return tongTienHoaDon;
    }


}
