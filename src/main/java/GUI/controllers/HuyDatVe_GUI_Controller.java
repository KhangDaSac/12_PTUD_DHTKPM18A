package GUI.controllers;


import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyVe_BUS;
import DAO.HoaDonDatVe_DAO;
import DTO.*;
import GUI.controllers.HuyDatVe_GUI_Items.HoaDonDatVe_HuyDatVe_Controller;
import GUI.controllers.HuyDatVe_GUI_Items.VeDat_HuyDatVe_Controller;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import utils.CreatePDF;
import utils.CurrencyFormat;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class HuyDatVe_GUI_Controller implements Initializable {
    @FXML
    private TextField txtCCCD;
    @FXML
    private DatePicker dapNgayThanhToan;
//    private Main_Controller main_Controller;
    @FXML
    private JFXButton btnTimHoaDon;
    @FXML
    private JFXButton btnChonTatCa;
    @FXML
    private JFXButton btnHuyPhieuDat;
    @FXML
    private TextField txtTongTienTraKhach;
    @FXML
    private VBox vboxDanhSachHoaDonDatVe;
    @FXML
    private VBox vboxDanhSachVeDat;

    private ArrayList<VeDat> danhSachDatVe = new ArrayList<>();
    private ArrayList<HoaDonDatVe> danhSachHoaDonDatVe = new ArrayList<>();
    private ArrayList<HoaDonDatVe_HuyDatVe_Controller> hoaDonDatVe_huyDatVe_controllers_list = new ArrayList<>();
    private ArrayList<VeDat_HuyDatVe_Controller> veDat_huyDatVe_controllers_list = new ArrayList<>();
    private HoaDonDatVe hoaDonDatVeDangChon;
    private HoaDonHuyDatVe hoaDonHuyDatVe;


    private KhachHang khachHang;

    public HoaDonHuyDatVe getHoaDonHuyDatVe() {
        return hoaDonHuyDatVe;
    }

    public void setHoaDonHuyDatVe(HoaDonHuyDatVe hoaDonHuyDatVe) {
        this.hoaDonHuyDatVe = hoaDonHuyDatVe;
    }

    public ArrayList<HoaDonDatVe> getHoaDonDatVe_list() {
        return hoaDonDatVe_list;
    }

    public void setHoaDonDatVe_list(ArrayList<HoaDonDatVe> hoaDonDatVe_list) {
        this.hoaDonDatVe_list = hoaDonDatVe_list;
    }

    public ArrayList<VeDat> getVeDatChon_list() {
        return veDatChon_list;
    }

    public void setVeDatChon_list(ArrayList<VeDat> veDatChon_list) {
        this.veDatChon_list = veDatChon_list;
    }

    private ArrayList<HoaDonDatVe> hoaDonDatVe_list;
    private ArrayList<VeDat> veDatChon_list = new ArrayList<VeDat>();

    public HoaDonDatVe getHoaDonDatVeDangChon() {
        return hoaDonDatVeDangChon;
    }

    public void setHoaDonDatVeDangChon(HoaDonDatVe hoaDonDatVeDangChon) {
        this.hoaDonDatVeDangChon = hoaDonDatVeDangChon;
        hoaDonHuyDatVe = new HoaDonHuyDatVe(QuanLyHoaDon_BUS.layHoaDonHuyDatVeTiepTheo());
        hoaDonHuyDatVe.setDanhSachChiTietHoaDonHuyDatVe(new ArrayList<>());
    }

//    public Main_Controller getMain_Controller() {
//        return main_Controller;
//    }
//
//    public void setMain_Controller(Main_Controller main_Controller) {
//        this.main_Controller = main_Controller;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDanhSachHoaDonDat();
        hienThiDanhSachHoaDonDat();
        String maHoaDonHuyDatVe = QuanLyHoaDon_BUS.layHoaDonHuyDatVeTiepTheo();
        hoaDonHuyDatVe = new HoaDonHuyDatVe(maHoaDonHuyDatVe);
        hoaDonHuyDatVe.setDanhSachChiTietHoaDonHuyDatVe(new ArrayList<ChiTietHoaDonHuyDatVe>());
        hoaDonHuyDatVe.setCaLamViec(new CaLamViec("CLV13122024C"));
    }

    public void loadDanhSachHoaDonDat() {
        danhSachHoaDonDatVe = HoaDonDatVe_DAO.get10HoaDonDatGanNhat();
    }

    public void hienThiDanhSachHoaDonDat() {
        vboxDanhSachHoaDonDatVe.getChildren().clear();
        if (danhSachHoaDonDatVe.isEmpty()) {
            return;
        }
        int length = danhSachHoaDonDatVe.size();
        for (int i = 0; i < length; i++) {
            HoaDonDatVe hoaDonDatVe = danhSachHoaDonDatVe.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyDatVe_GUI_Items/HoaDonDatVe_HuyDatVe.fxml"));
                Parent anchorPane = loader.load();
                HoaDonDatVe_HuyDatVe_Controller controller = loader.getController();
                hoaDonDatVe_huyDatVe_controllers_list.add(controller);
                controller.setHuyDatVe_gui_controller(this);
                controller.setHoaDonDatVe(hoaDonDatVe);
                controller.khoiTao();
                vboxDanhSachHoaDonDatVe.getChildren().add(anchorPane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void btnTimHoaDonOnAction(ActionEvent actionEvent) {

    }



    public void btnChonTatCaOnAction(ActionEvent actionEvent) {
        veDatChon_list.clear();
        veDatChon_list.addAll(hoaDonDatVeDangChon.getDanhSachVeDat());
        hienThiDanhSachVeDat(hoaDonDatVeDangChon);
    }




    public void btnBoChonTatCaOnAction(ActionEvent actionEvent) {
        veDatChon_list.clear();
        hienThiDanhSachVeDat(hoaDonDatVeDangChon);
    }


    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public void huyVeDat(){
        if (hoaDonHuyDatVe == null ||hoaDonHuyDatVe.getDanhSachChiTietHoaDonHuyDatVe().isEmpty()){
            return;
        }
        hoaDonHuyDatVe.setThoiGianHuy(LocalDateTime.now());
        if (QuanLyHoaDon_BUS.themHoaDonHuyDatVe(hoaDonHuyDatVe)) {
//            main_Controller.showMessagesDialog("Hủy đặt vé thành công");
            System.out.println("huy dat ve thanh cong");

            CreatePDF.taoHoaDonHuyDatVe(hoaDonHuyDatVe);
            String maHoaDonHuyDatVe = QuanLyHoaDon_BUS.layHoaDonHuyDatVeTiepTheo();
            hoaDonHuyDatVe = new HoaDonHuyDatVe(maHoaDonHuyDatVe);
            hoaDonHuyDatVe.setDanhSachChiTietHoaDonHuyDatVe(new ArrayList<ChiTietHoaDonHuyDatVe>());
            hoaDonHuyDatVe.setCaLamViec(new CaLamViec("CLV13122024C"));
            hoaDonHuyDatVe.setKhachHangHuyDatVe(khachHang);

            hienThiDanhSachVeDat(null);
        }else {
//            main_Controller.showMessagesDialog("Lấy vé thất bại");
            System.out.println("huy dat ve that bai");
        }

    }

    public void hienThiDanhSachVeDat(HoaDonDatVe hoaDonDatVe) {
        veDat_huyDatVe_controllers_list.clear();
        vboxDanhSachVeDat.getChildren().clear();
        if (hoaDonDatVe == null || hoaDonDatVe.getDanhSachVeDat().isEmpty()) {
            return;
        }
        int length = hoaDonDatVe.getDanhSachVeDat().size();
        for (int i = 0; i < length; i++) {
            VeDat veDat = hoaDonDatVe.getDanhSachVeDat().get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyDatVe_GUI_Items/VeDat_HuyDatVe.fxml"));
                Parent anchorPane = loader.load();
                VeDat_HuyDatVe_Controller controller = loader.getController();
                veDat_huyDatVe_controllers_list.add(controller);
                controller.setHuyDatVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setVeDat(veDat);
                controller.khoiTao();
                vboxDanhSachVeDat.getChildren().add(anchorPane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void chonVeHuy() {
        if(veDatChon_list.isEmpty()){
//            main_Controller.showMessagesDialog("Chưa chọn vé");
            return;
        }
        hoaDonHuyDatVe.getDanhSachChiTietHoaDonHuyDatVe().clear();
        for (VeDat veDat : veDatChon_list) {
            if (!hoaDonHuyDatVe.getDanhSachChiTietHoaDonHuyDatVe()
                    .stream()
                    .anyMatch(chiTietHoaDonHuyDatVe -> chiTietHoaDonHuyDatVe.getVeDat().equals(veDat))
            ) {
                hoaDonHuyDatVe.setThoiGianHuy(LocalDateTime.now());

                System.out.println("------------ hoa don huyy dat ve"+hoaDonHuyDatVe.getThoiGianHuy());
                ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe = new ChiTietHoaDonHuyDatVe(hoaDonHuyDatVe,veDat);
                hoaDonHuyDatVe.getDanhSachChiTietHoaDonHuyDatVe().add(chiTietHoaDonHuyDatVe);
            }
        }
    }
    public void hienThiTienTraKhach(){
        txtTongTienTraKhach.setText(CurrencyFormat.currencyFormat(hoaDonHuyDatVe.tongTienCuoi()));
    }
    public void xoaTienTraKhach(){
        txtTongTienTraKhach.setText("");
    }
    public void btnTinhTienOnAction(ActionEvent actionEvent) {
        chonVeHuy();
        hienThiDanhSachVeDat(hoaDonDatVeDangChon);
        hienThiTienTraKhach();
    }


    public void btnHuyPhieuDatOnAction(ActionEvent actionEvent) {
        chonVeHuy();
        huyVeDat();
    }
}