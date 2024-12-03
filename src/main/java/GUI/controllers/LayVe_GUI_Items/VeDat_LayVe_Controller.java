package GUI.controllers.LayVe_GUI_Items;

import DTO.*;
import GUI.controllers.BanVe_GUI_Items.ChiTietVe_BanVe_Controller;
import GUI.controllers.LayVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class VeDat_LayVe_Controller implements Initializable {
    @FXML
    private ImageView imvVeDatLay;

    @FXML
    private AnchorPane anpVeDat;

    @FXML
    private AnchorPane anpVeDatLay;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

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


    private LayVe_GUI_Controller layVe_gui_controller;
    private ArrayList<ChiTietVeDat_LayVe_Controller> chiTietVeDat_layVe_controller_list = new ArrayList<ChiTietVeDat_LayVe_Controller>();

    private VeDat veDat;
    private int soThuTu;

    public LayVe_GUI_Controller getLayVe_gui_controller() {
        return layVe_gui_controller;
    }

    public void setLayVe_gui_controller(LayVe_GUI_Controller layVe_gui_controller) {
        this.layVe_gui_controller = layVe_gui_controller;
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
            //layVe_gui_controller.getDanhSachChiTietPhieuDatVeTheoMaHoaDon(veDat.getHoaDonDatVe().getMaHoaDonDatVe());
            //layVe_gui_controller.hienThiDanhSachChiTietPhieuDatVe(veDat);
            chonPhieuDatVe();
            if (veDat.getTrangThaiVeDat().equals(TrangThaiVeDat.CHOLAYVE)) {

            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic if needed
    }

    public void khoiTao() throws IOException {
        lblSTT.setText(String.valueOf(soThuTu + 1));
        lblMaChuyenTau.setText(veDat.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(veDat.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(veDat.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(veDat.getThongTinGaTauDi().getThoiGianDi()));
        // anpPhieuDatVe.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/PhieuDatVe_LayVe.css").toExternalForm());
        anpVeDatLay.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/VeDat_LayVe.css").toExternalForm());

        if (veDat.getLoaiVe().equals(LoaiVe.VECANHAN)) {
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpVeDatLay.getStyleClass().add("phieuDatVe-left-caNhan");
        } else if (veDat.getLoaiVe().equals(LoaiVe.VETAPTHE)) {

            anpVeDatLay.getStyleClass().add("phieuDatVe-left-tapThe");
        }

        //

        anpVeDat.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/VeDat_LayVe.css").toExternalForm());
        lblMaChuyenTau.setText(veDat.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(veDat.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(veDat.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(veDat.getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        lblTienDaCoc.setText(CurrencyFormat.currencyFormat(veDat.tienDatCoc()));
        lblSTT.setText(String.valueOf(soThuTu + 1));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        for(ChiTietVeDat chiTietVeDat : veDat.getDanhSachChiTietVeDat()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/ChiTietVeDat_LayVe.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVeDat_LayVe_Controller controller = loader.getController();
            chiTietVeDat_layVe_controller_list.add(controller);
            controller.setChiTietVeDat(chiTietVeDat);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVeDat_layVe_controller_list.getLast().chiTietVeCuoi();

        if(veDat.getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThongTin.setMinHeight(390);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpVeDatLay.getStyleClass().add("ve-left-veCaNhan");

        }else if(veDat.getLoaiVe() == LoaiVe.VETAPTHE){
            vboxDanhSachThongTin.setMinHeight(180 + veDat.getDanhSachChiTietVeDat().size() * 260);
            anpVeDatLay.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(veDat.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        }

        imvVeDatLay.setVisible(false);
    }

    public void chonPhieuDatVe() {
        layVe_gui_controller.boChonTatCaPhieuDatVe();

        anpVeDat.getStyleClass().removeAll("phieuDatVeKhongChon");
        anpVeDat.getStyleClass().add("phieuDatVeDangChon");
    }

    public void boChonPhieuDatVe() {
        anpVeDat.getStyleClass().removeAll("phieuDatVeDangChon");
        anpVeDat.getStyleClass().add("phieuDatVeKhongChon");
    }

    public void chonLayVe() {
        if (veDat.getTrangThaiVeDat().equals(TrangThaiVeDat.CHOLAYVE)) {
            imvVeDatLay.setVisible(true);
            if (layVe_gui_controller != null) {
                layVe_gui_controller.tinhTongTienLayVe();
            }
        }
    }

    public void boChonLayVe() {
        imvVeDatLay.setVisible(false);
        if (layVe_gui_controller != null) {
            layVe_gui_controller.tinhTongTienLayVe();
        }
    }
}