package GUI.controllers.HuyDatVe_GUI_Items;

import DTO.*;
import GUI.controllers.HuyDatVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import utils.TimeFormat;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class VeDat_HuyDatVe_Controller {
    private HuyDatVe_GUI_Controller huyDatVe_gui_controller;
    private int soThuTu;
    private VeDat veDat;
    @FXML
    private Label lblMaChuyenTau;
    @FXML
    private Label lblTenGaDi;
    @FXML
    private Label lblTenGaDen;
    @FXML
    private Label lblThoiGianDi;
    @FXML
    private Label lblGiamGiaVeTapThe;
    @FXML
    private Label lblGiaVeCuoi;
    @FXML
    private Label lblTienDaCoc;
    @FXML
    private Label lblSTT;
    @FXML
    private AnchorPane anpVeDatLay;
    @FXML
    private ImageView imvVeDatLay;
    @FXML
    private AnchorPane anpVeDat;
    @FXML
    private VBox vboxDanhSachChoVeTapThe;
    @FXML
    private HBox hboxGiamGiaVeTapThe;
    @FXML
    private VBox vboxDanhSachThongTin;

    private ArrayList<ChiTietVeDat_HuyDatVe_Controller> chiTietVeDat_huyDatVe_controllers_list = new ArrayList<>();

    public HuyDatVe_GUI_Controller getHuyDatVe_gui_controller() {
        return huyDatVe_gui_controller;
    }

    public void setHuyDatVe_gui_controller(HuyDatVe_GUI_Controller huyDatVe_gui_controller) {
        this.huyDatVe_gui_controller = huyDatVe_gui_controller;
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

    public void khoiTao() throws Exception {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyDatVe_GUI_Items/ChiTietVeDat_HuyVeDat.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVeDat_HuyDatVe_Controller controller = loader.getController();
            chiTietVeDat_huyDatVe_controllers_list.add(controller);
            controller.setChiTietVeDat(chiTietVeDat);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVeDat_huyDatVe_controllers_list.get(chiTietVeDat_huyDatVe_controllers_list.size() - 1).chiTietVeCuoi();

        if (veDat.getLoaiVe() == LoaiVe.VECANHAN) {
            vboxDanhSachThongTin.setMinHeight(390);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpVeDatLay.getStyleClass().add("ve-left-veCaNhan");
        } else if (veDat.getLoaiVe() == LoaiVe.VETAPTHE) {
            vboxDanhSachThongTin.setMinHeight(180 + veDat.getDanhSachChiTietVeDat().size() * 260);
            anpVeDatLay.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(veDat.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        }

        if (huyDatVe_gui_controller.getVeDatChon_list().contains(veDat)) {
            chonVeDat();
        } else {
            boChonVeDat();
        }
        if(huyDatVe_gui_controller.getHoaDonHuyDatVe().getDanhSachChiTietHoaDonHuyDatVe().stream().anyMatch(cthdhd -> cthdhd.getVeDat().equals(veDat)))
            imvVeDatLay.setVisible(true);
        else
            imvVeDatLay.setVisible(false);
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
        if (veDat.getTrangThaiVeDat().equals(TrangThaiVeDat.CHOLAYVE)){
            if(huyDatVe_gui_controller.getVeDatChon_list().contains(veDat)){
                huyDatVe_gui_controller.getVeDatChon_list().remove(veDat);
                boChonVeDat();
            } else {
                huyDatVe_gui_controller.getVeDatChon_list().add(veDat);
            }
        }
        huyDatVe_gui_controller.hienThiDanhSachVeDat(huyDatVe_gui_controller.getHoaDonDatVeDangChon());

    }
    public void chonLayVe() {
        if (veDat.getTrangThaiVeDat().equals(TrangThaiVeDat.CHOLAYVE)) {
            imvVeDatLay.setVisible(true);
        }
    }

    public void boChonLayVe() {
        imvVeDatLay.setVisible(false);
        if (huyDatVe_gui_controller != null) {
            //layVe_gui_controller.tinhTongTienLayVe();
        }
    }
}