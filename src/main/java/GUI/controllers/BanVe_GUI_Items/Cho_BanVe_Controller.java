package GUI.controllers.BanVe_GUI_Items;

import DTO.Cho;
import DTO.TrangThaiCho;
import GUI.controllers.BanVe_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import utils.CurrencyFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class Cho_BanVe_Controller implements Initializable {
    @FXML
    private AnchorPane anpTrangThaiCho;

    @FXML
    private JFXButton btnCho;

    @FXML
    private Label lblGiaCho;

    @FXML
    private Label lblLoaiCho;

    @FXML
    private Label lblSoCho;

    @FXML
    private Label lblTrangThaiCho;

    private Cho cho;
    private BanVe_GUI_Controller banVe_GUI_controller;
    private boolean daThemVaoGio;
    private boolean dangChon;

    public boolean isDangChon() {
        return dangChon;
    }

    public void setDangChon(boolean dangChon) {
        this.dangChon = dangChon;
    }


    public boolean isDaThemVaoGio() {
        return daThemVaoGio;
    }

    public void setDaThemVaoGio(boolean daThemVaoGio) {
        this.daThemVaoGio = daThemVaoGio;
    }

    public Cho getCho() {
        return cho;
    }

    public void setCho(Cho cho) {
        this.cho = cho;
    }

    public BanVe_GUI_Controller getBanVe_GUI_controller() {
        return banVe_GUI_controller;
    }

    public void setBanVe_GUI_controller(BanVe_GUI_Controller banVe_GUI_controller) {
        this.banVe_GUI_controller = banVe_GUI_controller;
    }


    @FXML
    void btnChoOnAction(ActionEvent event) {
        if(!daThemVaoGio){
            if(cho.getTrangThaiCho() == TrangThaiCho.CONTRONG){
                if(!banVe_GUI_controller.getChoChonList().contains(cho)){
                    banVe_GUI_controller.getChoChonList().add(cho);
                    dangChon = true;
                }else{
                    banVe_GUI_controller.getChoChonList().remove(cho);
                    dangChon = false;
                }
                capNhatTrangThai();
                banVe_GUI_controller.capNhatCacChoDaChon();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(double doDaiChang){
        anpTrangThaiCho.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Cho_BanVe.css").toExternalForm());
        lblSoCho.setText(String.valueOf(cho.getSoCho()));
        chuyenMauMacDinh();
        cho.setGiaCho(cho.tinhGiaCho(doDaiChang));
        lblGiaCho.setText(CurrencyFormat.currencyFormat(cho.getGiaCho()));
        lblLoaiCho.setText(cho.getLoaiCho().getTenLoaiCho());
        capNhatTrangThai();
    }

    public void chuyenMauMacDinh(){
        switch (cho.getTrangThaiCho()){
            case DABAN -> {
                anpTrangThaiCho.getStyleClass().clear();
                anpTrangThaiCho.getStyleClass().add("choDaBan");
            }
            case DADAT -> {
                anpTrangThaiCho.getStyleClass().clear();
                anpTrangThaiCho.getStyleClass().add("choDaDat");
            }
            case DANHCHOCHANGDAIHON -> {
                anpTrangThaiCho.getStyleClass().clear();
                anpTrangThaiCho.getStyleClass().add("choDanhChoChanDaiHon");
            }
            case CONTRONG -> {
                anpTrangThaiCho.getStyleClass().clear();
                anpTrangThaiCho.getStyleClass().add("choTrong");
            }
        }
    }

    public void chuyenMauDangChon(){
        anpTrangThaiCho.getStyleClass().clear();
        anpTrangThaiCho.getStyleClass().add("choDangChon");
        dangChon = true;
        capNhatTrangThai();
    }

    public void chuyenMauDaThemVaoGioVe(){
        anpTrangThaiCho.getStyleClass().clear();
        anpTrangThaiCho.getStyleClass().add("choDaThemVaoGioVe");
        daThemVaoGio = true;
        capNhatTrangThai();
    }

    public void capNhatTrangThai(){
        String trangThaiCho = "";
        if(daThemVaoGio){
            trangThaiCho = "Đã thêm vào giỏ";
        }else if(dangChon){
            trangThaiCho = "Đang chọn";
        }else{
            switch (cho.getTrangThaiCho()){
                case TrangThaiCho.CONTRONG -> {
                    trangThaiCho = "Còn trống";
                }
                case TrangThaiCho.DABAN -> {
                    trangThaiCho = "Đã bán";
                }
                case DADAT -> {
                    trangThaiCho = "Đã đặt";
                }
                case TrangThaiCho.DANHCHOCHANGDAIHON -> {
                    trangThaiCho = "Chặng dài hơn";
                }
            }
        }
        lblTrangThaiCho.setText(trangThaiCho);
    }
}
