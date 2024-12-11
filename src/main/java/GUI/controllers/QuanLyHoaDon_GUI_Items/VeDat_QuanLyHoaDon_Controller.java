package GUI.controllers.QuanLyHoaDon_GUI_Items;

import DTO.*;
import GUI.controllers.QuanLyHoaDon_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import utils.TimeFormat;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class VeDat_QuanLyHoaDon_Controller {
    @FXML
    private AnchorPane anpVeDat;

    @FXML
    private AnchorPane anpVeDatLay;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

    @FXML
    private ImageView imvVeDatLay;

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
    private Label lblTienDaCoc;

    @FXML
    private VBox vboxDanhSachChoVeTapThe;

    @FXML
    private VBox vboxDanhSachThongTin;

    private QuanLyHoaDon_GUI_Controller quanLyHoaDon_gui_controller;
    private ArrayList<ChiTietVeDat_QuanLyHoaDon_Controller> chiTietVeDat_quanLyHoaDon_controller_list = new ArrayList<>();
    private int soThuTu;
    private VeDat veDat;
    private ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe;

    public ChiTietHoaDonHuyDatVe getChiTietHoaDonHuyDatVe() {
        return chiTietHoaDonHuyDatVe;
    }

    public void setChiTietHoaDonHuyDatVe(ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe) {
        this.chiTietHoaDonHuyDatVe = chiTietHoaDonHuyDatVe;
    }

    public QuanLyHoaDon_GUI_Controller getQuanLyHoaDon_gui_controller(QuanLyHoaDon_GUI_Controller quanLyHoaDonGuiController) {
        return quanLyHoaDon_gui_controller;
    }

    public void setQuanLyHoaDon_gui_controller(QuanLyHoaDon_GUI_Controller quanLyHoaDon_gui_controller) {
        this.quanLyHoaDon_gui_controller = quanLyHoaDon_gui_controller;
    }


    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public VeDat getVeDat() {
        return veDat;
    }

    public void setVeDat(VeDat veDat) {
        this.veDat = veDat;
    }
    public void khoiTaoQuanLiHoaDon() throws Exception {
        lblSTT.setText(String.valueOf(soThuTu + 1));
        lblMaChuyenTau.setText(veDat.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(veDat.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(veDat.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(veDat.getThongTinGaTauDi().getThoiGianDi()));
        anpVeDatLay.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/VeDat_LayVe.css").toExternalForm());

        if (veDat.getLoaiVe().equals(LoaiVe.VECANHAN)) {
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpVeDatLay.getStyleClass().add("phieuDatVe-left-caNhan");
        } else if (veDat.getLoaiVe().equals(LoaiVe.VETAPTHE)) {
            anpVeDatLay.getStyleClass().add("phieuDatVe-left-tapThe");
        }

        anpVeDat.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/VeDat_LayVe.css").toExternalForm());
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        lblTienDaCoc.setText(CurrencyFormat.currencyFormat(veDat.tienDatCoc()));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        for (ChiTietVeDat chiTietVeDat : veDat.getDanhSachChiTietVeDat()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/ChiTietVeDat_QuanLyHoaDon.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVeDat_QuanLyHoaDon_Controller controller = loader.getController();
            chiTietVeDat_quanLyHoaDon_controller_list.add(controller);
            controller.setChiTietVeDat(chiTietVeDat);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVeDat_quanLyHoaDon_controller_list.get(chiTietVeDat_quanLyHoaDon_controller_list.size() - 1).chiTietVeCuoi();

        if (veDat.getLoaiVe() == LoaiVe.VECANHAN) {
            vboxDanhSachThongTin.setMinHeight(390);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpVeDatLay.getStyleClass().add("ve-left-veCaNhan");
        } else if (veDat.getLoaiVe() == LoaiVe.VETAPTHE) {
            vboxDanhSachThongTin.setMinHeight(210 + veDat.getDanhSachChiTietVeDat().size() * 260);
            anpVeDatLay.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(veDat.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        }

        if (quanLyHoaDon_gui_controller.getVeDatChon_list().contains(veDat)) {
            chonVeDat();
        } else {
            boChonVeDat();
        }
    }
    public void khoiTaoVeDatHoaDonHuyDatVe() throws Exception {
        lblSTT.setText(String.valueOf(soThuTu + 1));
        lblMaChuyenTau.setText(chiTietHoaDonHuyDatVe.getVeDat().getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(chiTietHoaDonHuyDatVe.getVeDat().getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(chiTietHoaDonHuyDatVe.getVeDat().getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(chiTietHoaDonHuyDatVe.getVeDat().getThongTinGaTauDi().getThoiGianDi()));
        anpVeDatLay.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/VeDat_LayVe.css").toExternalForm());

        if (chiTietHoaDonHuyDatVe.getVeDat().getLoaiVe().equals(LoaiVe.VECANHAN)) {
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpVeDatLay.getStyleClass().add("phieuDatVe-left-caNhan");
        } else if (chiTietHoaDonHuyDatVe.getVeDat().getLoaiVe().equals(LoaiVe.VETAPTHE)) {
            anpVeDatLay.getStyleClass().add("phieuDatVe-left-tapThe");
        }

        anpVeDat.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/VeDat_LayVe.css").toExternalForm());
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(chiTietHoaDonHuyDatVe.getVeDat().tienVeCuoi()));
        lblTienDaCoc.setText(CurrencyFormat.currencyFormat(chiTietHoaDonHuyDatVe.getVeDat().tienDatCoc()));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        for (ChiTietVeDat chiTietVeDat : chiTietHoaDonHuyDatVe.getVeDat().getDanhSachChiTietVeDat()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/ChiTietVeDat_QuanLyHoaDon.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVeDat_QuanLyHoaDon_Controller controller = loader.getController();
            chiTietVeDat_quanLyHoaDon_controller_list.add(controller);
            controller.setChiTietVeDat(chiTietVeDat);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVeDat_quanLyHoaDon_controller_list.get(chiTietVeDat_quanLyHoaDon_controller_list.size() - 1).chiTietVeCuoi();

        if (chiTietHoaDonHuyDatVe.getVeDat().getLoaiVe() == LoaiVe.VECANHAN) {
            vboxDanhSachThongTin.setMinHeight(390);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpVeDatLay.getStyleClass().add("ve-left-veCaNhan");
        } else if (chiTietHoaDonHuyDatVe.getVeDat().getLoaiVe() == LoaiVe.VETAPTHE) {
            vboxDanhSachThongTin.setMinHeight(210 + chiTietHoaDonHuyDatVe.getVeDat().getDanhSachChiTietVeDat().size() * 260);
            anpVeDatLay.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(chiTietHoaDonHuyDatVe.getVeDat().giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(chiTietHoaDonHuyDatVe.getVeDat().tienVeCuoi()));
        }

        if (quanLyHoaDon_gui_controller.getVeDatChon_list().contains(veDat)) {
            chonVeDat();
        } else {
            boChonVeDat();
        }
    }
    public void chonVeDat() {
        anpVeDat.getStyleClass().removeAll("veDatKhongChon");
        anpVeDat.getStyleClass().add("veDatDangChon");
    }

    public void boChonVeDat() {
        anpVeDat.getStyleClass().removeAll("veDatDangChon");
        anpVeDat.getStyleClass().add("veDatKhongChon");
    }

    public void anpVeOnMouseClicked(MouseEvent mouseEvent) {
        if(quanLyHoaDon_gui_controller.getVeDatChon_list().contains(veDat)){
            quanLyHoaDon_gui_controller.getVeDatChon_list().remove(veDat);
            boChonVeDat();
        } else {
            quanLyHoaDon_gui_controller.getVeDatChon_list().add(veDat);
            chonVeDat();
        }
        if (quanLyHoaDon_gui_controller.getHoaDonDatVeDangChon() != null) {
            quanLyHoaDon_gui_controller.hienThiDanhSachVeDatHoaDonDatVe(quanLyHoaDon_gui_controller.getHoaDonDatVeDangChon());
        }
        if (quanLyHoaDon_gui_controller.getHoaDonHuyDatVeDangChon() != null) {
            quanLyHoaDon_gui_controller.hienThiDanhSachVeHoaDonHuyDatVe(quanLyHoaDon_gui_controller.getHoaDonHuyDatVeDangChon());
        }
    }
}
