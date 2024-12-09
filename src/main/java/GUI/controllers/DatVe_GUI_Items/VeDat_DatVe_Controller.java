package GUI.controllers.DatVe_GUI_Items;

import DTO.*;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.DatVe_GUI_Controller;
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

public class VeDat_DatVe_Controller implements Initializable {
    @FXML
    private AnchorPane anpVeDat;

    @FXML
    private AnchorPane anpXoaVeDat;

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
    private Label lblTienDatCoc;

    @FXML
    private VBox vboxDanhSachChoVeTapThe;

    @FXML
    private VBox vboxDanhSachThongTin;

    private VeDat veDat;

    private int soThuTu;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    private DatVe_GUI_Controller datVe_gui_controller;

    private ArrayList<ChiTietVeDat_DatVe_Controller> chiTietVe_banVe_controller_list = new ArrayList<ChiTietVeDat_DatVe_Controller>();

    public DatVe_GUI_Controller getDatVe_gui_controller() {
        return datVe_gui_controller;
    }

    public void setDatVe_gui_controller(DatVe_GUI_Controller datVe_gui_controller) {
        this.datVe_gui_controller = datVe_gui_controller;
    }

    public VeDat getVeDat() {
        return veDat;
    }

    public void setVeDat(VeDat veDat) {
        this.veDat = veDat;
    }

    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        chonVe();
    }

    @FXML
    void anpXoaVeOnMouseClicked(MouseEvent event) {
        if(datVe_gui_controller != null){
            datVe_gui_controller.xoaVeDat(veDat);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void khoiTao() throws IOException {
        anpVeDat.getStylesheets().add(getClass().getResource("/css/DatVe_GUI_Items/VeDat_DatVe.css").toExternalForm());
        lblMaChuyenTau.setText(veDat.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(veDat.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(veDat.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(veDat.getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        lblTienDatCoc.setText(CurrencyFormat.currencyFormat(veDat.tienDatCoc()));
        lblSTT.setText(String.valueOf(soThuTu + 1));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        for(ChiTietVeDat chiTietVeDat : veDat.getDanhSachChiTietVeDat()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DatVe_GUI_Items/ChiTietVeDat_DatVe.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVeDat_DatVe_Controller controller = loader.getController();
            chiTietVe_banVe_controller_list.add(controller);
            controller.setChiTietVeDat(chiTietVeDat);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVe_banVe_controller_list.getLast().chiTietVeCuoi();

        if(veDat.getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThongTin.setMinHeight(420);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVeDat.getStyleClass().add("veDat-left-veCaNhan");

        }else if(veDat.getLoaiVe() == LoaiVe.VETAPTHE){
            vboxDanhSachThongTin.setMinHeight(210 + veDat.getDanhSachChiTietVeDat().size() * 260);
            anpXoaVeDat.getStyleClass().add("veDat-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(veDat.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        }
    }

    public void chonVe(){
        datVe_gui_controller.boChonTatCaVe();
        anpVeDat.getStyleClass().add("veDatDangChon");
        anpVeDat.getStyleClass().removeAll("veDatKhongChon");

    }

    public void khongChonVe(){
        anpVeDat.getStyleClass().removeAll("veDatDangChon");
        anpVeDat.getStyleClass().add("veDatKhongChon");
    }

}
