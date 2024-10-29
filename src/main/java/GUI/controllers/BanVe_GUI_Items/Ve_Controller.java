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

        try {
            banVe_GUI_Controller.capNhatChiTietVe(ve);
            banVe_GUI_Controller.boChonTatCaVe();
            chonVe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void khoiTao(){
        lblMaChuyenTau.setText(ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(ve.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(ve.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));
        anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve.css").toExternalForm());
    }

    public void chonVe(){
        anpVe.getStyleClass().add("veDangChon");
        anpVe.getStyleClass().removeAll("veKhongChon");
    }

    public void khongChonVe(){
        anpVe.getStyleClass().removeAll("veDangChon");
        anpVe.getStyleClass().add("veKhongChon");
    }
}