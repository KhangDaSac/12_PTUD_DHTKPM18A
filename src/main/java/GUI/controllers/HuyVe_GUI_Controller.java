package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyKhachHang_BUS;
import BUS.QuanLyVe_BUS;
import DTO.*;
import GUI.controllers.HuyVe_GUI_Items.HoaDon_HuyVe_Controller;
import GUI.controllers.HuyVe_GUI_Items.Ve_HuyVe_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HuyVe_GUI_Controller {

    @FXML
    private JFXButton btnBoChonTatCa;

    @FXML
    private JFXButton btnChonTatCa;

    @FXML
    private JFXButton btnHuyVe;

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
    private VBox vboxChiTietVe;

    @FXML
    private VBox vboxDanhSachHoaDon;

    @FXML
    private VBox vboxDanhSachVe;

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

    private ArrayList<HoaDon_HuyVe_Controller> hoaDon_huyVe_controllerList = new ArrayList<HoaDon_HuyVe_Controller>();
    private ArrayList<Ve_HuyVe_Controller> veHuyVeControllerList = new ArrayList<Ve_HuyVe_Controller>();

    private ArrayList<HoaDonBanVe> hoaDonList;
    private ArrayList<Ve> veList;
    private ArrayList<ChiTietVeDat> chiTietPhieuDatVeList;

    private HoaDonHuyVe hoaDonHuyVe;
    private Ve veKhachHang;
    private CaLamViec caLamViec;

    @FXML
    void btnBoChonTatCaOnAction(ActionEvent event) {
        boChonHuyVeTatVe();
    }

    @FXML
    void btnChonTatCaOnAction(ActionEvent event) {
        chonTatCaVe();
    }

    @FXML
    void btnHuyVeOnAction(ActionEvent event) {
        huyVe();
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

            veList.clear();
            chiTietPhieuDatVeList.clear();
            capNhatDanhSachVe();
            hienThiDanhSachChiTietVe(null);
            hienThiDanhSachHoaDon();
        }
    }

    public void xoaThongTinKhachHang(){
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSoDienThoai.setText("");
        khachHang = null;
    }

    public void layDanhSachHoaDonDatTheoKhachHang(){
//        hoaDonList = QuanLyHoaDon_BUS.getDanhSachHoaDonDatTheoMaKhachHang(khachHang.getMaKhachHang());
        hienThiDanhSachHoaDon();
    }

    public void hienThiDanhSachHoaDon(){
        vboxDanhSachHoaDon.getChildren().clear();
        if(hoaDonList.isEmpty())
            return;
        int length = hoaDonList.size();
        for(int i = 0; i < length; i++){
            HoaDonBanVe hoaDon = hoaDonList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyVe_GUI_Items_Test/HoaDon_HuyVe.fxml"));
                Parent anchorPane = loader.load();
                HoaDon_HuyVe_Controller controller = loader.getController();
                hoaDon_huyVe_controllerList.add(controller);
                controller.setLayVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.khoiTao();

                vboxDanhSachHoaDon.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void boChonTatCaHoaDon(){
        for (HoaDon_HuyVe_Controller controller : hoaDon_huyVe_controllerList){
            controller.boChonHoaDon();
        }
    }

    public void getDanhSachChiTietPhieuDatVeTheoMaHoaDon(String maPhieuDatVe){

    }

    public void capNhatDanhSachVe(){
        vboxDanhSachVe.getChildren().clear();
        veHuyVeControllerList.clear();
        if(veList.isEmpty())
            return;
        int length = veList.size();
        for(int i = 0; i < length; i++){
            Ve ve = veList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyVe_GUI_Items_Test/Ve_HuyVe.fxml"));
                Parent anchorPane = loader.load();
                Ve_HuyVe_Controller controller = loader.getController();
                veHuyVeControllerList.add(controller);
                controller.setHuyVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setVe(ve);
                controller.khoiTao();

                vboxDanhSachVe.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void hienThiDanhSachChiTietVe(Ve phieuDatVe){
        vboxChiTietVe.getChildren().clear();
        if(phieuDatVe == null)
            return;
        for(ChiTietVeDat chiTietVeDat : chiTietPhieuDatVeList){
            if(chiTietVeDat.getVeDat().equals(phieuDatVe)){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/ChiTietPhieuDatVe_LayVe.fxml"));
                    Parent anchorPane = loader.load();


                    vboxChiTietVe.getChildren().add(anchorPane);
                }catch (IOException e){

                }
            }
        }

    }

    public void boChonTatCaPhieuDatVe() {
        for(Ve_HuyVe_Controller controller : veHuyVeControllerList){
            controller.boChonPhieuDatVe();
        }
    }

    public void chonTatCaVe(){
        for(Ve_HuyVe_Controller controller : veHuyVeControllerList){
            controller.chonLayVe();
        }
    }

    public void boChonHuyVeTatVe(){
        for(Ve_HuyVe_Controller controller : veHuyVeControllerList){
            controller.boChonHuyVe();
        }
    }

    public void huyVe() {
        LocalDate ngayHienTai1 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String ngayHienTai = ngayHienTai1.format(formatter);

        String maHoaDonHuyMoi;

        String maHoaDonLonNhatNgayHienTai = QuanLyHoaDon_BUS.layMaHoaDonHuyLonNhatCuaNgayHienTai(ngayHienTai);

        if (maHoaDonLonNhatNgayHienTai==null){
            maHoaDonHuyMoi = "HDDO" + ngayHienTai+"000001";
        }
        else {
            String soThuTuHDCu = maHoaDonLonNhatNgayHienTai.substring(maHoaDonLonNhatNgayHienTai.length()-6);
            int soThuTuMoiHD = Integer.parseInt(soThuTuHDCu)+1;
            maHoaDonHuyMoi = "HDDO"+ngayHienTai+ String.format("%06d",soThuTuMoiHD);
        }

        hoaDonHuyVe = new HoaDonHuyVe(maHoaDonHuyMoi, ngayHienTai, khachHang.getTenKhachHang(), caLamViec.getMaCaLamViec());

        veKhachHang.setTrangThaiVe(TrangThaiVe.DAHUY);

        if (QuanLyVe_BUS.capNhatTrangThaiVe(veKhachHang.getMaVe(), TrangThaiVe.DAHUY)) {
            QuanLyHoaDon_BUS.themHoaDonHuyVe(hoaDonHuyVe);
            main_controller.showMessagesDialog("Hủy vé thành công!");
            // CreatePDF.taoHoaDonHuyVe(hoaDonHuyVe); // Tùy chọn tạo file hóa đơn PDF
        } else {
            main_controller.showMessagesDialog("Hủy vé thất bại! Vui lòng thử lại.");
        }
    }

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