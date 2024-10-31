package GUI.controllers;

import DTO.ChiTietVe;
import DTO.HoaDon;
import DTO.Ve;
import GUI.controllers.BanVe_GUI_Items.ChiTietVe_Controller;
import GUI.controllers.BanVe_GUI_Items.Ve_Controller;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ThongTinBanVe_GUI_Controller implements Initializable {
    private HoaDon hoaDon;
    private ArrayList<Ve> danhSachVe;
    private ArrayList<ChiTietVe> danhSachChiTietVe;
    private Main_Controller main_controller;


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
    private JFXButton btnThemNguoiDiTau;

    @FXML
    private JFXButton btnThemNguoiMua;

    @FXML
    private JFXButton btnTimKiemKhachHang;

    @FXML
    private TextField tftCCCD;

    @FXML
    private TextField tftCCCDKhachHangMuaVe;

    @FXML
    private TextField tftLoaiKhachHang;

    @FXML
    private TextField tftMaKhachHang;

    @FXML
    private TextField tftMaKhachHangMuaVe;

    @FXML
    private TextField tftSoDienThoai;

    @FXML
    private TextField tftSoDienThoaiKhachHangMuaVe;

    @FXML
    private TextField tftTenKhachHang;

    @FXML
    private TextField tftTenKhachHangMuaVe;

    @FXML
    private TextField tftTongTien;

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

    }

    @FXML
    void btnThemNguoiMuaOnAction(ActionEvent event) {

    }

    @FXML
    void btnTimKiemKhachHangOnAction(ActionEvent event) {

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
            if(i == 0){
                controller.chonVe();
            }

            vboxGioVe.getChildren().add(anchorPane);
        }
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

}
