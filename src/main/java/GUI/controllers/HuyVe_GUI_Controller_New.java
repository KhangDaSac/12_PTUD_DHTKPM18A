package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import DAO.ChiTietHoaDonHuyVe_DAO;
import DAO.HoaDonHuyVe_DAO;
import DAO.KhachHang_DAO;
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
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
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
    private ArrayList<Ve> dsVeChon= new ArrayList<>();

    private Ve_TimVe_HuyVe_Controller_Test ve_timVe_huyVe_controller_test;
    private Ve_HuyVe_HuyVe_Controller_Test ve_huyVe_huyVe_controller_test;
    private ArrayList<Ve_HuyVe_HuyVe_Controller_Test> ve_huyVe_controllers_list = new ArrayList<>();




    private ArrayList<ChiTietHoaDonHuyVe> dschiTietHoaDonHuyVe = new ArrayList<>();



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
private HoaDonHuyVe hoaDonHuyVe = new HoaDonHuyVe();
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

    public void hienThiVeHuy(ArrayList<Ve> dsVe ) {
        ve_huyVe_controllers_list.clear();
        vboxVeHuy.getChildren().clear();
        if (dsVe.isEmpty() ) {
            System.out.println(" ds Ve rong");
            return;
        }
        for(Ve ve :dsVe){
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

    }



    public void hienThiLePhi(){
        double lephi=0;
        for(ChiTietHoaDonHuyVe chiTietHoaDonHuyVe: hoaDonHuyVe.getDanhSachChiTietHoaDonHuyVe()){
            lephi+= chiTietHoaDonHuyVe.lePhi();
        }
        txtLePhi.setText(CurrencyFormat.currencyFormat(lephi));
    }

    public void hienThiTienTraKhach(){
        txtTongTienHoanTra.setText(CurrencyFormat.currencyFormat(hoaDonHuyVe.tongTienCuoi()));
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
        dsVeChon.add(veTim);
        hienThiVeTim(veTim);
    }

    @FXML
    void btnThemVeHuyOnAction(ActionEvent event) {
        if (veTim == null || !veTim.isSelected()) {
            System.out.println("Vé chưa được chọn hoặc không tồn tại!");
            return;
        }
        if(dsVeChon.isEmpty()){
            dsVeChon.add(veTim);
        }

        String maHoaDonHuyVeMoi = QuanLyHoaDon_BUS.layHoaDonHuyVeTiepTheo();
        hoaDonHuyVe.setMaHoaDonHuyVe(maHoaDonHuyVeMoi);
        KhachHang khachHang =KhachHang_DAO.getKhachHangTheoMaKhachHang(veTim.getDanhSachChiTietVe().get(0).getKhachHang().getMaKhachHang());
        hoaDonHuyVe.setKhachHangHuyVe(khachHang);
        hoaDonHuyVe.setThoiGianHuyVe(LocalDateTime.now());
        hoaDonHuyVe.setCaLamViec(new CaLamViec("CLV01012024C001"));
        for(Ve ve : dsVeChon){
//            if(Duration.between(hoaDonHuyVe.getThoiGianHuyVe(), ve.getThongTinGaTauDi().getThoiGianDi()).toHours()<4){
//                System.out.println("khong huy duoc");
//                return;
//            }
//            else {
                System.out.println("ma ve -----"+ TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));
                ChiTietHoaDonHuyVe chiTietHoaDonHuyVe = new ChiTietHoaDonHuyVe(hoaDonHuyVe,ve);
                dschiTietHoaDonHuyVe.add(chiTietHoaDonHuyVe);
//            }

        }

        if (hoaDonHuyVe== null){
            System.out.println("========= hoa don khong co");
        }

        hoaDonHuyVe.setDanhSachChiTietHoaDonHuyVe(dschiTietHoaDonHuyVe);
        hienThiVeHuy(dsVeChon);

        hienThiLePhi();
        hienThiTienTraKhach();
    }

    @FXML
    void btnXoaTatCaVeOnAction(ActionEvent event) {
        vboxVeHuy.getChildren().clear();
        dsVeChon.clear();;
    }
    public void themChiTietHoaDonHuy(){
        for(Ve ve :dsVeChon){
            chuyenTrangThaiVe(ve);
        }

    ChiTietHoaDonHuyVe_DAO.themDanhsachChiTietHoaDonHuyDatVe(dschiTietHoaDonHuyVe);
    HoaDonHuyVe_DAO.themHoaDonHuyVe(hoaDonHuyVe);
    System.out.println("da them hoa don vo sql");
}
    @FXML
    void btnChonVeHuyOnAction(ActionEvent event) {


    }


    @FXML
    void txtCCCDOnMouseClicked(MouseEvent event) {
        String CCCD = txtCCCD.getText();

    }

    @FXML
    void btnTimKiemKhachHangOnAction(ActionEvent event) {
        if(txtCCCD.getText()== null){
            System.out.println("ban chua nhap cccd");
        }
        else {
            KhachHang khachHang = KhachHang_DAO.getKhachHangTheoCCCD(txtCCCD.getText());
            txtMaKhachHang.setText(khachHang.getMaKhachHang());
            txtTenKhachHang.setText(khachHang.getTenKhachHang());
            txtSoDienThoai.setText(khachHang.getSoDienThoai());
        }

    }
public void chuyenTrangThaiVe (Ve ve ){
        ve.setTrangThaiVe(TrangThaiVe.DAHUY);
        Ve_DAO.capNhatTrangThaiVe(ve.getMaVe(),TrangThaiVe.DAHUY);
}
    @FXML
    void btnHuyVeOnAction(ActionEvent event) {
        themChiTietHoaDonHuy();
    }


}
