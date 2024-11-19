package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyKhachHang_BUS;
import BUS.QuanLyVeDat_BUS;
import BUS.QuanLyVe_BUS;
import DTO.*;
import GUI.controllers.LayVe_GUI_Items.HoaDonDatVe_LayVe_Controller;
import GUI.controllers.LayVe_GUI_Items.VeDat_LayVe_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;

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

    private ArrayList<HoaDonDatVe_LayVe_Controller> hoaDon_layVe_controllerList = new ArrayList<HoaDonDatVe_LayVe_Controller>();
    private ArrayList<VeDat_LayVe_Controller> phieuDatVeLayVeControllerList = new ArrayList<VeDat_LayVe_Controller>();

    private ArrayList<HoaDonBanVe> hoaDonList;
    private ArrayList<VeDat> phieuDatVeList;
    private ArrayList<ChiTietVeDat> chiTietPhieuDatVeList;
    private ArrayList<VeDat> phieuDatVeDangChon = new ArrayList<VeDat>();


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
        //layVe();
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
        //hoaDonList = QuanLyHoaDon_BUS.getDanhSachHoaDonDatTheoMaKhachHang(khachHang.getMaKhachHang());
        hienThiDanhSachHoaDonDat();
    }

    public void hienThiDanhSachHoaDonDat(){
        vboxDanhSachHoaDonDat.getChildren().clear();
        if(hoaDonList.isEmpty())
            return;
        int length = hoaDonList.size();
        for(int i = 0; i < length; i++){
            HoaDonBanVe hoaDon = hoaDonList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/HoaDon_LayVe.fxml"));
                Parent anchorPane = loader.load();
                HoaDonDatVe_LayVe_Controller controller = loader.getController();
                hoaDon_layVe_controllerList.add(controller);
                controller.setLayVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.khoiTao();

                vboxDanhSachHoaDonDat.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }

    public void boChonTatCaHoaDon(){
        for (HoaDonDatVe_LayVe_Controller controller : hoaDon_layVe_controllerList){
            controller.boChonHoaDon();
        }
    }



    public void getDanhSachChiTietPhieuDatVeTheoMaHoaDon(String maPhieuDatVe){

    }

    public void capNhatDanhSachPhieuDatVe(){
        vboxDanhSachPhieuDatVe.getChildren().clear();
        phieuDatVeLayVeControllerList.clear();
        if(phieuDatVeList.isEmpty())
            return;
        int length = phieuDatVeList.size();
        for(int i = 0; i < length; i++){
            VeDat phieuDatVe = phieuDatVeList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/PhieuDatVe_LayVe.fxml"));
                Parent anchorPane = loader.load();
                VeDat_LayVe_Controller controller = loader.getController();
                phieuDatVeLayVeControllerList.add(controller);
                controller.setLayVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setVeDat(phieuDatVe);
                controller.khoiTao();

                vboxDanhSachPhieuDatVe.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void hienThiDanhSachChiTietPhieuDatVe(VeDat phieuDatVe){
        vboxChiTietPhieuDatVe.getChildren().clear();
        if(phieuDatVe == null)
            return;
        for(ChiTietVeDat chiTietVeDat : chiTietPhieuDatVeList){
            if(chiTietVeDat.getVeDat().equals(phieuDatVe)){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/ChiTietPhieuDatVe_LayVe.fxml"));
                    Parent anchorPane = loader.load();


                    vboxChiTietPhieuDatVe.getChildren().add(anchorPane);
                }catch (IOException e){

                }
            }
        }

    }




    public void boChonTatCaPhieuDatVe() {
        for(VeDat_LayVe_Controller controller : phieuDatVeLayVeControllerList){
            controller.boChonPhieuDatVe();
        }
    }


    public void chonLayVeTatPhieuDatVe(){
        for(VeDat_LayVe_Controller controller : phieuDatVeLayVeControllerList){
            controller.chonLayVe();
        }
    }

    public void boChonLayVeTatPhieuDatVe(){
        for(VeDat_LayVe_Controller controller : phieuDatVeLayVeControllerList){
            controller.boChonLayVe();
        }
    }

//    public void layVe(){
//        int sl = 0;
//        for(VeDat_LayVe_Controller controller : phieuDatVeLayVeControllerList){
//            if(controller.isChonLayVe()){
//                sl++;
//            }
//        }
//        if(sl == 0){
//            main_controller.showMessagesDialog("Chưa chọn vé");
//            return;
//        }
//        ArrayList<Ve> veList = new ArrayList<Ve>();
//        ArrayList<ChiTietVe> chiTietVeList = new ArrayList<ChiTietVe>();
//        for(VeDat_LayVe_Controller controller : phieuDatVeLayVeControllerList){
//            if (controller.isChonLayVe()){
//                String maVeMoi = "";
//                if(veList.isEmpty()){
//                    maVeMoi = QuanLyVe_BUS.taoMaVeMoi();
//                }else{
//                    maVeMoi = QuanLyVe_BUS.taoMaVeTiepTheo(veList.getLast());
//                }
//
//                VeDat phieuDatVe = controller.getVeDat();
//
//                LoaiVe loaiVe = phieuDatVe.getLoaiPhieuDatVe().equals(LoaiPhieuDatVe.PHIEUDATCANHAN) ? LoaiVe.VECANHAN :
//                                phieuDatVe.getLoaiPhieuDatVe().equals(LoaiPhieuDatVe.PHIEUDATTAPTHE) ? LoaiVe.VETAPTHE : null;
//
//                Ve ve = new Ve(
//                        maVeMoi,
//                        phieuDatVe.getHoaDon(),
//                        phieuDatVe.getChiTietChuyenTauDi(),
//                        phieuDatVe.getChiTietChuyenTauDen(),
//                        phieuDatVe.getTongTienVe(),
//                        phieuDatVe.getGiamGiaVeTapThe(),
//                        loaiVe,
//                        TrangThaiVe.DANGSUDUNG
//                );
//                veList.add(ve);
//
//                for(ChiTietPhieuDatVe_LayVe_Controller controller2 : chiTietPhieuDatVeLayVeControllerList){
//                    if(controller.getPhieuDatVe().equals(controller2.getChiTietPhieuDatVe().getPhieuDatVe())){
//                        ChiTietVeDat chiTietPhieuDatVe = controller2.getChiTietPhieuDatVe();
//                        ChiTietVe chiTietVe = new ChiTietVe(
//                                chiTietPhieuDatVe.getGiaCho(),
//                                chiTietPhieuDatVe.getSoTienGiamGia(),
//                                chiTietPhieuDatVe.getThanhTien(),
//                                ve,
//                                chiTietPhieuDatVe.getKhachHang(),
//                                chiTietPhieuDatVe.getCho()
//                        );
//                        System.out.println(chiTietVe);
//                        chiTietVeList.add(chiTietVe);
//                    }
//                }
//            }
//        }
//
//        try {
//            if(QuanLyHoaDon_BUS.layVe(veList, chiTietVeList)){
//                if(QuanLyVeDat_BUS.capNhatTrangThaiPhieuDatVe(phieuDatVeList, "DALAYVE")){
//                    main_controller.showMessagesDialog("Lấy vé thành công");
//                    phieuDatVeList.clear();
//                    chiTietPhieuDatVeList.clear();
//                    chiTietPhieuDatVeLayVeControllerList.clear();
//                    phieuDatVeList.clear();
//                    capNhatDanhSachPhieuDatVe();
//                    hienThiDanhSachChiTietPhieuDatVe(null);
//                    hienThiDanhSachHoaDonDat();
//                    tinhTongTienLayVe();
//                }
//            }else{
//                main_controller.showMessagesDialog("Lấy vé thất bại");
//            }
//        } catch (Exception e) {
//            main_controller.showMessagesDialog(e.getMessage());
//        }
//    }

    public double tinhTongTienLayVe(){
//        double tongTien = 0;
//        double tongTienCoc = 0;
//        for(VeDat_LayVe_Controller controller : phieuDatVeLayVeControllerList){
//            if(controller.isChonLayVe()){
//                tongTien += controller.getPhieuDatVe().getTongTienVe();
//                tongTienCoc += controller.getPhieuDatVe().getTongTienDatCoc();
//            }
//        }
//
//        txtTongTien.setText(CurrencyFormat.currencyFormat(tongTien - tongTienCoc));
//        return tongTien - tongTienCoc;
        return 0;
    }




}
