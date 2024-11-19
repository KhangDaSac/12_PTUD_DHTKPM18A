package GUI.controllers.BanVe_GUI_Items;

import DTO.ChiTietVe;
import DTO.KhachHang;
import DTO.LoaiVe;
import DTO.Ve;
import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class Ve_BanVe_Controller implements Initializable {
    @FXML
    private AnchorPane anpVe;

    @FXML
    private AnchorPane anpXoaVe;

    @FXML
    private HBox hboxGiaVeCuoi;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

    @FXML
    private Label lblCCCD;

    @FXML
    private Label lblCho;

    @FXML
    private Label lblGiaCho;

    @FXML
    private Label lblGiaVeCuoi;

    @FXML
    private Label lblGiamGiaLoaiKhachHang;

    @FXML
    private Label lblGiamGiaVeTapThe;

    @FXML
    private Label lblLoaiKhachHang;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblSTT;

    @FXML
    private Label lblTenGaDen;

    @FXML
    private Label lblTenGaDi;

    @FXML
    private Label lblTenKhachHang;

    @FXML
    private Label lblThoiGianDi;

    @FXML
    private Label lblToa;

    @FXML
    private VBox vboxDanhDachChoVeTapThe;

    @FXML
    private VBox vboxDanhSachThongTin;
    private Ve ve;

    private int soThuTu;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    private BanVe_GUI_Controller banVe_GUI_Controller;

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
    }

    public BanVe_GUI_Controller getBanVe_GUI_Controller() {
        return banVe_GUI_Controller;
    }

    public void setBanVe_GUI_Controller(BanVe_GUI_Controller banVe_GUI_Controller) {
        this.banVe_GUI_Controller = banVe_GUI_Controller;
    }

    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        chonVe();
    }

    @FXML
    void anpXoaVeOnMouseCliced(MouseEvent event) {
        if(banVe_GUI_Controller != null)
            banVe_GUI_Controller.xoaVe(ve);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void khoiTao(){

        anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve.css").toExternalForm());
        lblMaChuyenTau.setText(ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(ve.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(ve.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));

        if(ve.getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVe.getStyleClass().add("ve-left-veCaNhan");

            lblCho.setText(String.valueOf(ve.getDanhSachChiTietVe().getFirst().getCho().getSoCho()));
            lblToa.setText(String.valueOf(ve.getDanhSachChiTietVe().getFirst().getCho().getToaTau().getThuTuToa()));
            lblGiaCho.setText(CurrencyFormat.currencyFormat(ve.getDanhSachChiTietVe().getFirst().getGiaCho()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));

            if(ve.getDanhSachChiTietVe().getFirst().getKhachHang() != null){
                KhachHang khachHang = ve.getDanhSachChiTietVe().getFirst().getKhachHang();
                lblCCCD.setText(khachHang.getCCCD());
                lblTenKhachHang.setText(khachHang.getTenKhachHang());
                lblLoaiKhachHang.setText(khachHang.getLoaiKhachHang().getTenLoaiKhachHang());
                lblGiamGiaLoaiKhachHang.setText(CurrencyFormat.currencyFormat(ve.getDanhSachChiTietVe().getFirst().giamGia()));
            }

            lblSTT.setText(String.valueOf(soThuTu + 1));

        }else if(ve.getLoaiVe() == LoaiVe.VETAPTHE){
            anpVe.setMinHeight(210);
            anpXoaVe.getStyleClass().add("ve-left-veTapThe");

        }
    }

    public void chonVe(){
            banVe_GUI_Controller.boChonTatCaVe();
            anpVe.getStyleClass().add("veDangChon");
            anpVe.getStyleClass().removeAll("veKhongChon");

    }

    public void khongChonVe(){
        anpVe.getStyleClass().removeAll("veDangChon");
        anpVe.getStyleClass().add("veKhongChon");
    }

}
