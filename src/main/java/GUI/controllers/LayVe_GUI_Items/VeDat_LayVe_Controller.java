package GUI.controllers.LayVe_GUI_Items;

import DTO.LoaiVe;
import DTO.VeDat;
import DTO.TrangThaiVeDat;
import GUI.controllers.LayVe_GUI_Controller;
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

public class VeDat_LayVe_Controller implements Initializable {

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

    private LayVe_GUI_Controller layVe_gui_controller;

    private VeDat veDat;
    private int soThuTu;
    private boolean dangChon;
    private boolean chonLayVe;

    public boolean isChonLayVe() {
        return chonLayVe;
    }

    public void setChonLayVe(boolean chonLayVe) {
        this.chonLayVe = chonLayVe;
    }

    public LayVe_GUI_Controller getLayVe_gui_controller() {
        return layVe_gui_controller;
    }

    public void setLayVe_gui_controller(LayVe_GUI_Controller layVe_gui_controller) {
        this.layVe_gui_controller = layVe_gui_controller;
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

    public VeDat getVeDat() {
        return veDat;
    }

    public void setVeDat(VeDat veDat) {
        this.veDat = veDat;
    }

    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        if (layVe_gui_controller != null) {
            layVe_gui_controller.getDanhSachChiTietPhieuDatVeTheoMaHoaDon(veDat.getHoaDonDatVe().getMaHoaDonDatVe());
            //layVe_gui_controller.hienThiDanhSachChiTietPhieuDatVe(veDat);
            chonPhieuDatVe();
            if (veDat.getTrangThaiVeDat().equals(TrangThaiVeDat.CHOLAYVE)) {
                if (chonLayVe) {
                    boChonLayVe();
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
        lblMaChuyenTau.setText(veDat.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(veDat.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(veDat.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(veDat.getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVe.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        lblTienCoc.setText(CurrencyFormat.currencyFormat(veDat.tienDatCoc()));
        anpPhieuDatVe.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/PhieuDatVe_LayVe.css").toExternalForm());
        anpChonPhieuDatVe.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/PhieuDatVe_LayVe.css").toExternalForm());

        if (veDat.getLoaiVe().equals(LoaiVe.VECANHAN)) {
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            vboxDanhSachThongTin.getChildren().remove(hboxGiaVeCuoi);
            anpChonPhieuDatVe.getStyleClass().add("phieuDatVe-left-caNhan");
        } else if (veDat.getLoaiVe().equals(LoaiVe.VETAPTHE)) {

            anpChonPhieuDatVe.getStyleClass().add("phieuDatVe-left-tapThe");
        }

        if (veDat.getTrangThaiVeDat().equals(TrangThaiVeDat.CHOLAYVE)) {
            imvTrangThai.setImage(new Image(getClass().getResourceAsStream("/images/LayVe_GUI/TrangThaiPhieuDatVe/ChoLayVe.png")));
        } else if (veDat.getTrangThaiVeDat().equals(TrangThaiVeDat.DALAYVE)) {
            imvTrangThai.setImage(new Image(getClass().getResourceAsStream("/images/LayVe_GUI/TrangThaiPhieuDatVe/DaLayVe.png")));
        } else if (veDat.getTrangThaiVeDat().equals(TrangThaiVeDat.DAHUY)) {
            imvTrangThai.setImage(new Image(getClass().getResourceAsStream("/images/LayVe_GUI/TrangThaiPhieuDatVe/DaHuy.png")));
        }

        imvChonPhieuDatVe.setVisible(false);
    }

    public void chonPhieuDatVe() {
        layVe_gui_controller.boChonTatCaPhieuDatVe();

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
        if (veDat.getTrangThaiVeDat().equals(TrangThaiVeDat.CHOLAYVE)) {
            chonLayVe = true;
            imvChonPhieuDatVe.setVisible(true);
            if (layVe_gui_controller != null) {
                layVe_gui_controller.tinhTongTienLayVe();
            }
        }
    }

    public void boChonLayVe() {
        chonLayVe = false;
        imvChonPhieuDatVe.setVisible(false);
        if (layVe_gui_controller != null) {
            layVe_gui_controller.tinhTongTienLayVe();
        }
    }
}