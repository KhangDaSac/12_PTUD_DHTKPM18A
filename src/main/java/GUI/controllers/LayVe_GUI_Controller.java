package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyKhachHang_BUS;
import BUS.QuanLyPhieuDatVe_BUS;
import BUS.QuanLyVe_BUS;
import DTO.*;
import GUI.controllers.LayVe_GUI_Items.ChiTietPhieuDatVe_LayVe_Controller;
import GUI.controllers.LayVe_GUI_Items.HoaDon_LayVe_Controller;
import GUI.controllers.LayVe_GUI_Items.PhieuDatVe_LayVe_Controller;
import GUI.controllers.ThongTinBanVe_GUI_Items.ChiTietVe_ThongTinBanVe_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import utils.ShowMessagesDialog;

import java.io.IOException;
import java.util.ArrayList;

public class LayVe_GUI_Controller {

    @FXML
    private JFXButton btnBoChonTatCa;

    @FXML
    private JFXButton btnChonTatCa;

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
    private ArrayList<PhieuDatVe_LayVe_Controller> phieuDatVeLayVeControllerList = new ArrayList<PhieuDatVe_LayVe_Controller>();
    private ArrayList<ChiTietPhieuDatVe_LayVe_Controller> chiTietPhieuDatVeLayVeControllerList = new ArrayList<ChiTietPhieuDatVe_LayVe_Controller>();

    private ArrayList<HoaDon> hoaDonList;
    private ArrayList<PhieuDatVe> phieuDatVeList;
    private ArrayList<ChiTietPhieuDatVe> chiTietPhieuDatVeList;
    private ArrayList<PhieuDatVe> phieuDatVeDangChon = new ArrayList<PhieuDatVe>();


    @FXML
    void btnBoChonTatCaOnAction(ActionEvent event) {
        boChonLayVeTatPhieuDatVe();
    }

    @FXML
    void btnChonTatCaOnAction(ActionEvent event) {
        chonLayVeTatPhieuDatVe();
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
            hoaDonList.clear();

            phieuDatVeList.clear();
            chiTietPhieuDatVeList.clear();
            capNhatDanhSachPhieuDatVe();
            hienThiDanhSachChiTietPhieuDatVe(null);
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

    public void boChonTatCaHoaDon(){
        for (HoaDon_LayVe_Controller controller : hoaDon_layVe_controllerList){
            controller.boChonHoaDon();
        }
    }

    public void getDanhSachPhieuDatVeTheoMaHoaDon(String maHoaDon){
        phieuDatVeList = QuanLyPhieuDatVe_BUS.getDanhSachPhieuDatVeTheoMaHoaDon(maHoaDon);
    }

    public void getDanhSachChiTietPhieuDatVeTheoMaHoaDon(String maPhieuDatVe){
        chiTietPhieuDatVeList = QuanLyPhieuDatVe_BUS.getDanhSachChiTietPhieuDatVeTheoMaHoaDon(maPhieuDatVe);
    }

    public void capNhatDanhSachPhieuDatVe(){
        vboxDanhSachPhieuDatVe.getChildren().clear();
        if(phieuDatVeList.isEmpty())
            return;
        int length = phieuDatVeList.size();
        for(int i = 0; i < length; i++){
            PhieuDatVe phieuDatVe = phieuDatVeList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/PhieuDatVe_LayVe.fxml"));
                Parent anchorPane = loader.load();
                PhieuDatVe_LayVe_Controller controller = loader.getController();
                phieuDatVeLayVeControllerList.add(controller);
                controller.setLayVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setPhieuDatVe(phieuDatVe);
                controller.khoiTao();

                vboxDanhSachPhieuDatVe.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void hienThiDanhSachChiTietPhieuDatVe(PhieuDatVe phieuDatVe){
        vboxChiTietPhieuDatVe.getChildren().clear();
        chiTietPhieuDatVeLayVeControllerList.clear();
        if(phieuDatVe == null)
            return;
        for(ChiTietPhieuDatVe chiTietPhieuDatVe : chiTietPhieuDatVeList){
            if(chiTietPhieuDatVe.getPhieuDatVe().equals(phieuDatVe)){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/ChiTietPhieuDatVe_LayVe.fxml"));
                    Parent anchorPane = loader.load();
                    ChiTietPhieuDatVe_LayVe_Controller controller = loader.getController();
                    chiTietPhieuDatVeLayVeControllerList.add(controller);
                    controller.setLayVe_gui_controller(this);

                    controller.setChiTietPhieuDatVe(chiTietPhieuDatVe);
                    controller.khoiTao();

                    vboxChiTietPhieuDatVe.getChildren().add(anchorPane);
                }catch (IOException e){

                }
            }
        }
        chiTietPhieuDatVeLayVeControllerList.getFirst();
    }


    public void boChonTatCaChiTietPhieuDatVe() {
        for(ChiTietPhieuDatVe_LayVe_Controller controller : chiTietPhieuDatVeLayVeControllerList){
            controller.khongChonChiTietPhieuDatVe();
        }
    }

    public void boChonTatCaPhieuDatVe() {
        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
            controller.boChonPhieuDatVe();
        }
    }


    public void chonLayVeTatPhieuDatVe(){
        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
            controller.chonLayVe();
        }
    }

    public void boChonLayVeTatPhieuDatVe(){
        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
            controller.boChonLayVe();
        }
    }

    public void layVe(){
        if(phieuDatVeList.isEmpty()){
            main_controller.showMessagesDialog("Chưa chọn vé");
            return;
        }
        ArrayList<Ve> veList = new ArrayList<Ve>();
        ArrayList<ChiTietVe> chiTietVeList = new ArrayList<ChiTietVe>();
        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
            if (controller.isChonLayVe()){
                String maVeMoi = "";
                if(veList.isEmpty()){
                    maVeMoi = QuanLyVe_BUS.taoMaVeMoi();
                }else{
                    maVeMoi = QuanLyVe_BUS.taoMaVeTiepTheo(veList.getLast());
                }

                PhieuDatVe phieuDatVe = controller.getPhieuDatVe();

                LoaiVe loaiVe = phieuDatVe.getLoaiPhieuDatVe().equals(LoaiPhieuDatVe.PHIEUDATCANHAN) ? LoaiVe.VECANHAN :
                                phieuDatVe.getLoaiPhieuDatVe().equals(LoaiPhieuDatVe.PHIEUDATTAPTHE) ? LoaiVe.VETAPTHE : null;

                Ve ve = new Ve(
                        maVeMoi,
                        phieuDatVe.getHoaDon(),
                        phieuDatVe.getChiTietChuyenTauDi(),
                        phieuDatVe.getChiTietChuyenTauDen(),
                        phieuDatVe.getTongTienVe(),
                        phieuDatVe.getGiamGiaVeTapThe(),
                        loaiVe,
                        TrangThaiVe.DANGSUDUNG
                );
                veList.add(ve);

                for(ChiTietPhieuDatVe_LayVe_Controller controller2 : chiTietPhieuDatVeLayVeControllerList){
                    if(controller.getPhieuDatVe().equals(controller2.getChiTietPhieuDatVe().getPhieuDatVe())){
                        ChiTietPhieuDatVe chiTietPhieuDatVe = controller2.getChiTietPhieuDatVe();
                        ChiTietVe chiTietVe = new ChiTietVe(

                        );
                        chiTietVeList.add(chiTietVe);
                    }
                }
            }
        }



    }

    public double tinhTongTienLayVe(){
        double tongTien = 0;
        double tongTienCoc = 0;
        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
            if(controller.isChonLayVe()){
                tongTien += controller.getPhieuDatVe().getTongTienVe();
                tongTienCoc += controller.getPhieuDatVe().getTongTienDatCoc();
            }
        }

        System.out.println("Da tinh tien");

        txtTongTien.setText(CurrencyFormat.currencyFormat(tongTien - tongTienCoc));
        return tongTien - tongTienCoc;
    }


}
