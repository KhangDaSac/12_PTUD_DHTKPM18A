package GUI.controllers.BanVe_GUI_Items;

import DTO.Ve;
import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.TimeFormat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ve_Controller implements Initializable {
    @FXML
    private AnchorPane anpVe;

    @FXML
    private AnchorPane anpXoaVe;


    @FXML
    private Label lblSTT;


    @FXML
    private Label lblGiaVe;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblTenGaDen;

    @FXML
    private Label lblTenGaDi;

    @FXML
    private Label lblThoiGianDi;

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
        banVe_GUI_Controller.xoaVe(ve);
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
    }

    public void chonVe(){
        try {
            banVe_GUI_Controller.capNhatChiTietVe(ve);
            banVe_GUI_Controller.boChonTatCaVe();
            anpVe.getStyleClass().add("veDangChon");
            anpVe.getStyleClass().removeAll("veKhongChon");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void khongChonVe(){
        anpVe.getStyleClass().removeAll("veDangChon");
        anpVe.getStyleClass().add("veKhongChon");
    }

}
