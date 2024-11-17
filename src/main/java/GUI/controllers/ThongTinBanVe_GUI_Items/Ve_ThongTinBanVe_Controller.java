package GUI.controllers.ThongTinBanVe_GUI_Items;

import DTO.LoaiVe;
import DTO.Ve;
import GUI.controllers.ThongTinBanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ve_ThongTinBanVe_Controller implements Initializable {
    @FXML
    private AnchorPane anpVe;

    @FXML
    private AnchorPane anpXoaVe;

    @FXML
    private HBox hboxGiaVeCuoi;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

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
    private VBox vboxDanhSachThoiTin;

    @FXML
    private ImageView imvDungThongTinNguoiDiTau;

    private Ve ve;

    private int soThuTu;

    private boolean duThongTinNguoiDiTau;



    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    private ThongTinBanVe_GUI_Controller thongTinBanVe_gui_controller;

    public ThongTinBanVe_GUI_Controller getThongTinBanVe_gui_controller() {
        return thongTinBanVe_gui_controller;
    }

    public void setThongTinBanVe_gui_controller(ThongTinBanVe_GUI_Controller thongTinBanVe_gui_controller) {
        this.thongTinBanVe_gui_controller = thongTinBanVe_gui_controller;
    }

    public boolean isDuThongTinNguoiDiTau() {
        return duThongTinNguoiDiTau;
    }

    public void setDuThongTinNguoiDiTau(boolean duThongTinNguoiDiTau) {
        this.duThongTinNguoiDiTau = duThongTinNguoiDiTau;
    }

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
    }


    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        chonVe();
    }

    @FXML
    void anpXoaVeOnMouseCliced(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void khoiTao(){
        lblMaChuyenTau.setText(ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(ve.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(ve.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));

        lblSTT.setText(String.valueOf(soThuTu + 1));
        anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve.css").toExternalForm());



        if(ve.getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThoiTin.getChildren().remove(hboxGiaVeCuoi);
            vboxDanhSachThoiTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVe.getStyleClass().add("ve-left-veCaNhan");
            //lblGiaVe.setText(CurrencyFormat.currencyFormat(ve.tinhTongTienVeCuoi()));

        }else if(ve.getLoaiVe() == LoaiVe.VETAPTHE){
            anpVe.setMinHeight(210);
            anpXoaVe.getStyleClass().add("ve-left-veTapThe");
            //lblGiaVe.setText(CurrencyFormat.currencyFormat(ve.getTongTienVe()));
            //lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(ve.getGiamGiaVeTapThe()));
            //lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tinhTongTienVeCuoi()));
        }

        imvDungThongTinNguoiDiTau.setVisible(duThongTinNguoiDiTau);
    }

    public void chonVe(){
        thongTinBanVe_gui_controller.setVeDangChon(soThuTu);
        anpVe.getStyleClass().add("veDangChon");
        anpVe.getStyleClass().removeAll("veKhongChon");

    }

    public void khongChonVe(){
        anpVe.getStyleClass().removeAll("veDangChon");
        anpVe.getStyleClass().add("veKhongChon");
    }

}
