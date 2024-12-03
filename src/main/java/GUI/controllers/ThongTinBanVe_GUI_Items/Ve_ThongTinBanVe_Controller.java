package GUI.controllers.ThongTinBanVe_GUI_Items;

import DTO.*;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.ThongTinBanVe_GUI_Controller;
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

    private ThongTinBanVe_GUI_Controller thongTinBanVe_gui_controller;

    public ThongTinBanVe_GUI_Controller getThongTinBanVe_gui_controller() {
        return thongTinBanVe_gui_controller;
    }

    public void setThongTinBanVe_gui_controller(ThongTinBanVe_GUI_Controller thongTinBanVe_gui_controller) {
        this.thongTinBanVe_gui_controller = thongTinBanVe_gui_controller;
    }

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
    }

    private ArrayList<ChiTietVe_ThongTinBanVe_Controller> chiTietVe_thongTinBanVe_controller_list= new ArrayList<ChiTietVe_ThongTinBanVe_Controller>();



    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        chonVe();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinBanVe_GUI_Items/ChiTietVe_ThongTinBanVe.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVe_ThongTinBanVe_Controller controller = loader.getController();
            chiTietVe_thongTinBanVe_controller_list.add(controller);
            controller.setThongTinBanVe_gui_controller(thongTinBanVe_gui_controller);
            controller.setChiTietVe(chiTietVe);
            controller.khoiTao();
            vboxDanhDachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVe_thongTinBanVe_controller_list.getLast().chiTietVeCuoi();

        if(ve.getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThongTin.setMinHeight(390);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVe.getStyleClass().add("ve-left-veCaNhan");

        }else if(ve.getLoaiVe() == LoaiVe.VETAPTHE){
            vboxDanhSachThongTin.setMinHeight(180 + ve.getDanhSachChiTietVe().size() * 260);
            anpXoaVe.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(ve.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
        }
    }

    public void chonVe(){
        thongTinBanVe_gui_controller.boChonTatCaVe();
        anpVe.getStyleClass().add("veDangChon");
        anpVe.getStyleClass().removeAll("veKhongChon");
    }

    public void khongChonVe(){
        anpVe.getStyleClass().removeAll("veDangChon");
        anpVe.getStyleClass().add("veKhongChon");
    }

}
