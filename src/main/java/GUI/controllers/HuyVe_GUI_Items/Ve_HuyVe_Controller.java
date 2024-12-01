package GUI.controllers.HuyVe_GUI_Items;

import DTO.*;
import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Ve_HuyVe_Controller implements Initializable {
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

    private ArrayList<ChiTietVe_HuyVe_Controller> chiTietVe_banVe_controller_list = new ArrayList<ChiTietVe_HuyVe_Controller>();

    public void setBanVe_GUI_Controller(BanVe_GUI_Controller banVe_GUI_Controller) {
        this.banVe_GUI_Controller = banVe_GUI_Controller;
    }

    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        chonVe();
    }

    @FXML
    void anpXoaVeOnMouseClicked(MouseEvent event) {
        if(banVe_GUI_Controller != null){
            banVe_GUI_Controller.xoaVe(ve);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void khoiTao() throws IOException {

        anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve_BanVe.css").toExternalForm());
        lblMaChuyenTau.setText(ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(ve.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(ve.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
        lblSTT.setText(String.valueOf(soThuTu + 1));

        vboxDanhDachChoVeTapThe.getChildren().clear();
        for(ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ChiTietVe_BanVe.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVe_HuyVe_Controller controller = loader.getController();
            chiTietVe_banVe_controller_list.add(controller);
            controller.setChiTietVe(chiTietVe);
            controller.khoiTao();
            vboxDanhDachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVe_banVe_controller_list.getLast().chiTietVeCuoi();

        if(ve.getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThongTin.setMinHeight(330);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVe.getStyleClass().add("ve-left-veCaNhan");

        }else if(ve.getLoaiVe() == LoaiVe.VETAPTHE){
            vboxDanhSachThongTin.setMinHeight(180 + ve.getDanhSachChiTietVe().size() * 200);
            anpXoaVe.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(ve.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
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

    public boolean isChonLayVe() {
        return false;
    }
}