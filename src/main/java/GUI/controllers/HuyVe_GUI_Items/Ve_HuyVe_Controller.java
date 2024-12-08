package GUI.controllers.HuyVe_GUI_Items;

import DTO.LoaiVe;
import DTO.TrangThaiVe;
import DTO.Ve;
import GUI.controllers.HuyVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class Ve_HuyVe_Controller implements Initializable {

    @FXML
    private AnchorPane anpChonPhieuDatVe;

    @FXML
    private AnchorPane anpPhieuDatVe;

    @FXML
    private HBox hboxGiaVeCuoi;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

    @FXML
    private ImageView imvChonPhieuDatVe;

    @FXML
    private Label lblGiaVe;

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
    private Label lblTienCoc;

    @FXML
    private VBox vboxDanhSachThongTin;

    @FXML
    private ImageView imvTrangThai;

    private HuyVe_GUI_Controller huyVe_gui_controller;

    private Ve ve;
    private int soThuTu;
    private boolean dangChon;
    private boolean chonHuyVe;

    public boolean isChonHuyVe() {
        return chonHuyVe;
    }

    public void setChonLayVe(boolean chonHuyVe) {
        this.chonHuyVe = chonHuyVe;
    }

    public HuyVe_GUI_Controller getHuyVe_gui_controller() {
        return huyVe_gui_controller;
    }

    public void setHuyVe_gui_controller(HuyVe_GUI_Controller huyVe_gui_controller) {
        this.huyVe_gui_controller = huyVe_gui_controller;
    }



    public boolean isDangChon() {
        return dangChon;
    }

    public void setDangChon(boolean dangChon) {
        this.dangChon = dangChon;
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
    }

    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        if (huyVe_gui_controller != null) {
            huyVe_gui_controller.getDanhSachChiTietPhieuDatVeTheoMaHoaDon(ve.getHoaDonBanVe().getMaHoaDonBanVe());
            huyVe_gui_controller.hienThiDanhSachChiTietVe(ve);
            chonPhieuDatVe();
            if (ve.getTrangThaiVe().equals(TrangThaiVe.DANGSUDUNG)) {
                if (chonHuyVe) {
                    boChonHuyVe();
                } else {
                    chonLayVe();
                }
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic if needed
    }

    public void khoiTao() {
        lblSTT.setText(String.valueOf(soThuTu + 1));
        lblMaChuyenTau.setText(ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(ve.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(ve.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVe.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
        anpPhieuDatVe.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/PhieuDatVe_LayVe.css").toExternalForm());
        anpChonPhieuDatVe.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/PhieuDatVe_LayVe.css").toExternalForm());

        if (ve.getLoaiVe().equals(LoaiVe.VECANHAN)) {
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            vboxDanhSachThongTin.getChildren().remove(hboxGiaVeCuoi);
            anpChonPhieuDatVe.getStyleClass().add("phieuDatVe-left-caNhan");
        } else if (ve.getLoaiVe().equals(LoaiVe.VETAPTHE)) {

            anpChonPhieuDatVe.getStyleClass().add("phieuDatVe-left-tapThe");
        }

        if (ve.getTrangThaiVe().equals(TrangThaiVe.DANGSUDUNG)) {
            imvTrangThai.setImage(new Image(getClass().getResourceAsStream("/images/LayVe_GUI/TrangThaiPhieuDatVe/ChoLayVe.png")));
        } else if (ve.getTrangThaiVe().equals(TrangThaiVe.DADOI)) {
            imvTrangThai.setImage(new Image(getClass().getResourceAsStream("/images/LayVe_GUI/TrangThaiPhieuDatVe/DaLayVe.png")));
        } else if (ve.getTrangThaiVe().equals(TrangThaiVe.DAHUY)) {
            imvTrangThai.setImage(new Image(getClass().getResourceAsStream("/images/HuyVe_GUI/TrangThaiPhieuDatVe/DaHuy.png")));
        }

        imvChonPhieuDatVe.setVisible(false);
    }

    public void chonPhieuDatVe() {
        huyVe_gui_controller.boChonTatCaPhieuDatVe();

        dangChon = true;
        anpPhieuDatVe.getStyleClass().removeAll("phieuDatVeKhongChon");
        anpPhieuDatVe.getStyleClass().add("phieuDatVeDangChon");
    }

    public void boChonPhieuDatVe() {
        dangChon = false;
        anpPhieuDatVe.getStyleClass().removeAll("phieuDatVeDangChon");
        anpPhieuDatVe.getStyleClass().add("phieuDatVeKhongChon");
    }

    public void chonLayVe() {
        if (ve.getTrangThaiVe().equals(TrangThaiVe.DANGSUDUNG)) {
            chonHuyVe = true;
            imvChonPhieuDatVe.setVisible(true);
            if (huyVe_gui_controller != null) {
                huyVe_gui_controller.tinhTongTienLayVe();
            }
        }
    }

    public void boChonHuyVe() {
        chonHuyVe = false;
        imvChonPhieuDatVe.setVisible(false);
        if (huyVe_gui_controller != null) {
            huyVe_gui_controller.tinhTongTienLayVe();
        }
    }
}