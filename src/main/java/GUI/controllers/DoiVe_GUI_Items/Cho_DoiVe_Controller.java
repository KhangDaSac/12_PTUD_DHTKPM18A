package GUI.controllers.DoiVe_GUI_Items;

import DTO.Cho;
import DTO.TrangThaiCho;
import GUI.controllers.DoiVe_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import utils.CurrencyFormat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Cho_DoiVe_Controller implements Initializable {
    @FXML
    private JFXButton btnCho;

    private static String trang;
    private Cho cho;
    private DoiVe_GUI_Controller doiVe_gui_controller;
    private boolean dangChon;

    public boolean isDangChon() {
        return dangChon;
    }

    public void setDangChon(boolean dangChon) {
        this.dangChon = dangChon;
    }

    private Tooltip tooltip;
    String giaCho;


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
        if (cho.getTrangThaiCho()== TrangThaiCho.CONTRONG){
                if(!doiVe_gui_controller.getChoChon().equals(cho)){
                        doiVe_gui_controller.setChoChon(cho);
                        doiVe_gui_controller.capNhatCacChoDaChon();
                }
                capNhatTrangThai();
                doiVe_gui_controller.capNhatCacChoDaChon();
                doiVe_gui_controller.setLblCho_Moi(cho.getSoCho());
                doiVe_gui_controller.setLblGiaCho_Moi(cho.getGiaCho());
                doiVe_gui_controller.setLblToaTau_Moi(cho.getToaTau().getMaToaTau());
                doiVe_gui_controller.tinhTongTien();
                dangChon =false;
            } else {
            System.out.printf("trạng thái không xác định\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(double doDaiChang){
        btnCho.getStylesheets().add(getClass().getResource("/css/DoiVe_GUI_Items/Cho_DoiVe.css").toExternalForm());
        btnCho.setText(String.valueOf(cho.getSoCho()));
        chuyenMauMacDinh();
        tooltip = new Tooltip();
        cho.setGiaCho(cho.tinhGiaCho(doDaiChang));
        capNhatTrangThai();
        btnCho.setTooltip(tooltip);
        tooltip.setShowDelay(Duration.millis(0)); // Hiển thị ngay lập tức khi hover
        tooltip.setHideDelay(Duration.millis(0));
    }

    public void chuyenMauMacDinh(){
        switch (cho.getTrangThaiCho()){
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

    public void chuyenMauDangChon(){
        btnCho.getStyleClass().clear();
        btnCho.getStyleClass().add("choDangChon");
        dangChon = true;
        capNhatTrangThai();
    }



    public void capNhatTrangThai(){
        String trangThaiCho = "";
        giaCho = CurrencyFormat.currencyFormat(cho.getGiaCho());
        if(dangChon){
            trangThaiCho = "Đang chọn";
        }else{
            switch (cho.getTrangThaiCho()){
                case TrangThaiCho.CONTRONG -> {
                    trangThaiCho = "Còn trống";
                }
                case TrangThaiCho.DADAT -> {
                    trangThaiCho = "Đã đặt";
                }
                case TrangThaiCho.DABAN -> {
                    trangThaiCho = "Đã bán";
                }
                case TrangThaiCho.DANHCHOCHANGDAIHON -> {
                    trangThaiCho = "Dành cho chặng dài hơn";
                }
            }
        }

        tooltip.setStyle(
                "-fx-font-size: 16px; " +
                "-fx-background-color: white; " +
                "-fx-font-weight: normal; " +
                "-fx-text-fill: black; "
        );

        tooltip.setText(cho.getLoaiCho().getTenLoaiCho() + "\n" + trangThaiCho + "\n" + giaCho);
    }
}
