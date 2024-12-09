package GUI.controllers.DoiVe_GUI_Items;

import DTO.Cho;
import DTO.TrangThaiCho;
import GUI.controllers.DoiVe_GUI_Controller;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Cho_DoiVe_Controller implements Initializable {
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
    private DoiVe_GUI_Controller doiVe_gui_controller;
    private boolean dangChon;

    public boolean isDangChon() {
        return dangChon;
    }

    public void setDangChon(boolean dangChon) {
        this.dangChon = dangChon;
    }


    public Cho getCho() {
        return cho;
    }

    public void setCho(Cho cho) {
        this.cho = cho;
    }


    public DoiVe_GUI_Controller getDoiVe_gui_controller() {
        return doiVe_gui_controller;
    }

    public void setDoiVe_gui_controller(DoiVe_GUI_Controller doiVe_gui_controller) {
        this.doiVe_gui_controller = doiVe_gui_controller;
    }

    @FXML
    void btnChoOnAction(ActionEvent event) {
        if (cho.getTrangThaiCho() == TrangThaiCho.CONTRONG) {
            if (!doiVe_gui_controller.getChoChon().equals(cho)) {
                doiVe_gui_controller.setChoChon(cho);
                doiVe_gui_controller.capNhatCacChoDaChon();
            }
            capNhatTrangThai();
            doiVe_gui_controller.capNhatCacChoDaChon();
            doiVe_gui_controller.setLblCho_Moi(cho.getSoCho());
            doiVe_gui_controller.setLblGiaCho_Moi(cho.getGiaCho());
            doiVe_gui_controller.setLblToaTau_Moi(cho.getToaTau().getMaToaTau());
            doiVe_gui_controller.tinhTongTien();
            dangChon = false;
        } else {
            System.out.printf("trạng thái không xác định\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(double doDaiChang) {

        anpTrangThaiCho.getStylesheets().add(getClass().getResource("/css/DoiVe_GUI_Items/Cho_DoiVe.css").toExternalForm());
        lblSoCho.setText(String.valueOf(cho.getSoCho()));
        cho.setGiaCho(cho.tinhGiaCho(doDaiChang));
        lblGiaCho.setText(CurrencyFormat.currencyFormat(cho.getGiaCho()));
        lblLoaiCho.setText(cho.getLoaiCho().getTenLoaiCho());
        capNhatTrangThai();
    }

    public void chuyenMauMacDinh() {
        switch (cho.getTrangThaiCho()) {
            case DABAN -> {
                btnCho.getStyleClass().clear();
                btnCho.getStyleClass().add("choDaBan");
            }
            case DADAT -> {
                btnCho.getStyleClass().clear();
                btnCho.getStyleClass().add("choDaDat");
            }
            case DANHCHOCHANGDAIHON -> {
                btnCho.getStyleClass().clear();
                btnCho.getStyleClass().add("choDanhChoChanDaiHon");
            }
            case CONTRONG -> {
                btnCho.getStyleClass().clear();
                btnCho.getStyleClass().add("choTrong");
            }
        }
    }

    public void chuyenMauDangChon() {
        anpTrangThaiCho.getStyleClass().clear();
        anpTrangThaiCho.getStyleClass().add("choDangChon");
    }

    public String trangThai() {


        if (doiVe_gui_controller.getChoChon().equals(cho)) {
            chuyenMauDangChon();
            return "Đang chọn";
        }
        switch (cho.getTrangThaiCho()) {
            case TrangThaiCho.CONTRONG -> {
                anpTrangThaiCho.getStyleClass().clear();
                anpTrangThaiCho.getStyleClass().add("choTrong");
                return "Còn trống";
            }
            case TrangThaiCho.DABAN -> {
                anpTrangThaiCho.getStyleClass().clear();
                anpTrangThaiCho.getStyleClass().add("choDaBan");
                return "Đã bán";
            }
            case DADAT -> {
                anpTrangThaiCho.getStyleClass().clear();
                anpTrangThaiCho.getStyleClass().add("choDaDat");
                return "Đã đặt";
            }
            case TrangThaiCho.DANHCHOCHANGDAIHON -> {
                anpTrangThaiCho.getStyleClass().clear();
                anpTrangThaiCho.getStyleClass().add("choDanhChoChanDaiHon");
                return "Chặng dài hơn";
            }
        }
        return null;
    }

    public void capNhatTrangThai() {
        lblTrangThaiCho.setText(trangThai());
    }


}