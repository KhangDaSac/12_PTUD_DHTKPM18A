package GUI.controllers.QuanLyHoaDon_GUI_Items;
import DTO.*;
import GUI.controllers.QuanLyHoaDon_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Ve_QuanLyHoaDon_Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
        private AnchorPane anpVe;

        @FXML
        private AnchorPane anpXoaVe;

        @FXML
        private HBox hboxGiamGiaVeTapThe;

        @FXML
        private Label lblDaCoc;
        @FXML
        private HBox hboxConLai;

        @FXML
        private HBox hboxDaCoc;

        @FXML
        private Label lblGiaVeCuoi;

        @FXML
        private Label lblGiamGiaVeTapThe;

        @FXML
        private Label lblMaChuyenTau;

        @FXML
        private Label lblSTT;

        @FXML
        private Label lblTenGaDen;

        @FXML
        private Label lblTenGaDi;

        @FXML
        private Label lblThoiGianDi;

        @FXML
        private Label lblTienConLai;

        @FXML
        private VBox vboxDanhSachChoVeTapThe;

        @FXML
        private VBox vboxDanhSachThongTin;

        private ChiTietHoaDonLayVe chiTietHoaDonLayVe;
        private ChiTietHoaDonHuyVe chiTietHoaDonHuyVe;
        private Ve veMoi;
        private Ve  veCu;

    public Ve getVeMoi() {
        return veMoi;
    }

    public void setVeMoi(Ve veMoi) {
        this.veMoi = veMoi;
    }

    public Ve getVeCu() {
        return veCu;
    }

    public void setVeCu(Ve veCu) {
        this.veCu = veCu;
    }

    public ChiTietHoaDonHuyVe getChiTietHoaDonHuyVe() {
        return chiTietHoaDonHuyVe;
    }

    public void setChiTietHoaDonHuyVe(ChiTietHoaDonHuyVe chiTietHoaDonHuyVe) {
        this.chiTietHoaDonHuyVe = chiTietHoaDonHuyVe;
    }

    private Ve ve;

        public Ve getVe() {
            return ve;
        }

    public void setVe(Ve ve) {
        this.ve = ve;
    }

    private QuanLyHoaDon_GUI_Controller quanLyHoaDon_gui_controller;


    public QuanLyHoaDon_GUI_Controller getQuanLyHoaDon_gui_controller() {
        return quanLyHoaDon_gui_controller;
    }

    public void setQuanLyHoaDon_gui_controller(QuanLyHoaDon_GUI_Controller quanLyHoaDon_gui_controller) {
        this.quanLyHoaDon_gui_controller = quanLyHoaDon_gui_controller;
    }

    public ChiTietHoaDonLayVe getChiTietHoaDonLayVe() {
            return chiTietHoaDonLayVe;
        }

        public void setChiTietHoaDonLayVe(ChiTietHoaDonLayVe chiTietHoaDonLayVe) {
            this.chiTietHoaDonLayVe = chiTietHoaDonLayVe;
        }

        private int soThuTu;

        public int getSoThuTu() {
            return soThuTu;
        }

        public void setSoThuTu(int soThuTu) {
            this.soThuTu = soThuTu;
        }

        private ArrayList<ChiTietVe_QuanLyHoaDon_Controller> chiTietVe_quanLyHoaDon_controller_list = new ArrayList<ChiTietVe_QuanLyHoaDon_Controller>();


        @FXML
        void anpVeOnMouseClicked(MouseEvent event) {
                if(quanLyHoaDon_gui_controller.getVeDatChon_list().contains(ve)){
                    quanLyHoaDon_gui_controller.getVeDatChon_list().remove(ve);
                    khongChonVe();
                }else{
                    quanLyHoaDon_gui_controller.getVeChon_list().add(ve);
                    chonVe();
                }
                if (quanLyHoaDon_gui_controller.getHoaDonHuyVeDangChon()!=null){
                    quanLyHoaDon_gui_controller.hienThiDanhSachVeHoaDonHuyVe(quanLyHoaDon_gui_controller.getHoaDonHuyVeDangChon());
                }
                if (quanLyHoaDon_gui_controller.getHoaDonLayVeDangChon()!=null){
                    quanLyHoaDon_gui_controller.hienThiDanhSachHoaDonLayVe(quanLyHoaDon_gui_controller.getHoaDonLayVeDangChon());
                }
                if (quanLyHoaDon_gui_controller.getHoaDonBanVeDangChon()!=null){
                    quanLyHoaDon_gui_controller.hienThiDanhSachVeHoaDonBanVe(quanLyHoaDon_gui_controller.getHoaDonBanVeDangChon());
                }
                if (quanLyHoaDon_gui_controller.getHoaDonDoiVeDangChon()!=null){
                    quanLyHoaDon_gui_controller.hienThiDanhSachVeHoaDonDoiVe(quanLyHoaDon_gui_controller.getHoaDonDoiVeDangChon());
                }

        }

        @FXML
        void anpXoaVeOnMouseClicked(MouseEvent event) {

        }



        public void khoiTaoVeCuaHoaDonBanVe() throws IOException {
            anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve_BanVe.css").toExternalForm());
            lblMaChuyenTau.setText(ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
            lblTenGaDi.setText(ve.getThongTinGaTauDi().getGaTau().getTenGaTau());
            lblTenGaDen.setText(ve.getThongTinGaTauDen().getGaTau().getTenGaTau());
            lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
            vboxDanhSachThongTin.getChildren().remove(hboxDaCoc);
            vboxDanhSachThongTin.getChildren().remove(hboxConLai);
            lblSTT.setText(String.valueOf(soThuTu + 1));

            vboxDanhSachChoVeTapThe.getChildren().clear();
            for(ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/ChiTietVe_QuanLyHoaDon.fxml"));
                Parent anchorPane = loader.load();
                ChiTietVe_QuanLyHoaDon_Controller controller = loader.getController();
                chiTietVe_quanLyHoaDon_controller_list.add(controller);
                controller.setChiTietVe(chiTietVe);
                controller.khoiTao();
                vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
            }

            chiTietVe_quanLyHoaDon_controller_list.getLast().chiTietVeCuoi();

            if(ve.getLoaiVe() == LoaiVe.VECANHAN){
                vboxDanhSachThongTin.setMinHeight(450);
                vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
                anpXoaVe.getStyleClass().add("ve-left-veCaNhan");

            }else if(ve.getLoaiVe() == LoaiVe.VETAPTHE){
                vboxDanhSachThongTin.setMinHeight(240 + ve.getDanhSachChiTietVe().size() * 260);
                anpXoaVe.getStyleClass().add("ve-left-veTapThe");
                lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(ve.giamGiaVeTapThe()));
                lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
            }
        }
    public void khoiTaoVeCuaHoaDonLayVe() throws IOException {
        anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve_BanVe.css").toExternalForm());
        lblMaChuyenTau.setText(chiTietHoaDonLayVe.getVe().getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(chiTietHoaDonLayVe.getVe().getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(chiTietHoaDonLayVe.getVe().getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(chiTietHoaDonLayVe.getVe().getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVe().tienVeCuoi()));
        lblDaCoc.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVeDat().tienDatCoc()));
        lblTienConLai.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.thanhTien()));
        lblSTT.setText(String.valueOf(soThuTu + 1));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        for(ChiTietVe chiTietVe : chiTietHoaDonLayVe.getVe().getDanhSachChiTietVe()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/ChiTietVe_QuanLyHoaDon.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVe_QuanLyHoaDon_Controller controller = loader.getController();
            chiTietVe_quanLyHoaDon_controller_list.add(controller);
            controller.setChiTietVe(chiTietVe);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVe_quanLyHoaDon_controller_list.getLast().chiTietVeCuoi();

        if(chiTietHoaDonLayVe.getVe().getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThongTin.setMinHeight(450);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVe.getStyleClass().add("ve-left-veCaNhan");

        }else if(chiTietHoaDonLayVe.getVe().getLoaiVe() == LoaiVe.VETAPTHE){
            vboxDanhSachThongTin.setMinHeight(240 + chiTietHoaDonLayVe.getVe().getDanhSachChiTietVe().size() * 260);
            anpXoaVe.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVe().giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVe().tienVeCuoi()));
        }
    }
    public void khoiTaoVeCuaHoaDonHuyVe() throws IOException {
        anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve_BanVe.css").toExternalForm());
        lblMaChuyenTau.setText(chiTietHoaDonHuyVe.getVe().getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(chiTietHoaDonHuyVe.getVe().getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(chiTietHoaDonHuyVe.getVe().getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(chiTietHoaDonHuyVe.getVe().getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(chiTietHoaDonHuyVe.getVe().tienVeCuoi()));
        lblSTT.setText(String.valueOf(soThuTu + 1));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        for (ChiTietVe chiTietVe : chiTietHoaDonHuyVe.getVe().getDanhSachChiTietVe()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/ChiTietVe_QuanLyHoaDon.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVe_QuanLyHoaDon_Controller controller = loader.getController();
            chiTietVe_quanLyHoaDon_controller_list.add(controller);
            controller.setChiTietVe(chiTietVe);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVe_quanLyHoaDon_controller_list.getLast().chiTietVeCuoi();

        if (chiTietHoaDonHuyVe.getVe().getLoaiVe() == LoaiVe.VECANHAN) {
            vboxDanhSachThongTin.setMinHeight(450);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVe.getStyleClass().add("ve-left-veCaNhan");

        } else if (chiTietHoaDonHuyVe.getVe().getLoaiVe() == LoaiVe.VETAPTHE) {
            vboxDanhSachThongTin.setMinHeight(240 + chiTietHoaDonHuyVe.getVe().getDanhSachChiTietVe().size() * 260);
            anpXoaVe.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(chiTietHoaDonHuyVe.getVe().giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(chiTietHoaDonHuyVe.getVe().tienVeCuoi()));
        }
    }
    public void khoiTaoVeCuaHoaDonDoi() throws IOException {
        anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve_BanVe.css").toExternalForm());
        lblMaChuyenTau.setText(ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(ve.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(ve.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
        lblSTT.setText(String.valueOf(soThuTu + 1));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        for (ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/ChiTietVe_QuanLyHoaDon.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVe_QuanLyHoaDon_Controller controller = loader.getController();
            chiTietVe_quanLyHoaDon_controller_list.add(controller);
            controller.setChiTietVe(chiTietVe);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVe_quanLyHoaDon_controller_list.getLast().chiTietVeCuoi();

        if (ve.getLoaiVe() == LoaiVe.VECANHAN) {
            vboxDanhSachThongTin.setMinHeight(450);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVe.getStyleClass().add("ve-left-veCaNhan");
        }else if (ve.getLoaiVe() == LoaiVe.VETAPTHE) {
            vboxDanhSachThongTin.setMinHeight(240 + ve.getDanhSachChiTietVe().size() * 260);
            anpXoaVe.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(ve.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
        }
    }
        public void chonVe(){
            anpVe.getStyleClass().add("veDangChon");
            anpVe.getStyleClass().removeAll("veKhongChon");

        }

        public void khongChonVe(){
            anpVe.getStyleClass().removeAll("veDangChon");
            anpVe.getStyleClass().add("veKhongChon");
        }

    }
