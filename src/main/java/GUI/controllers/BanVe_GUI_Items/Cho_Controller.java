package GUI.controllers.BanVe_GUI_Items;

import DTO.Cho;
import DTO.TrangThaiCho;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.DoiVe_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import utils.CurrencyFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class Cho_Controller implements Initializable {
    @FXML
    private JFXButton btnCho;

    private static String trang;
    private Cho cho;
    private BanVe_GUI_Controller banVe_GUI_controller;
    private DoiVe_GUI_Controller doiVe_gui_controller;
    private boolean daThemVaoGio;
    private boolean dangChon;

    public boolean isDangChon() {
        return dangChon;
    }

    public void setDangChon(boolean dangChon) {
        this.dangChon = dangChon;
    }

    private Tooltip tooltip;
    String giaCho;

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

    public DoiVe_GUI_Controller getDoiVe_gui_controller() {
        return doiVe_gui_controller;
    }

    public void setDoiVe_gui_controller(DoiVe_GUI_Controller doiVe_gui_controller) {
        this.doiVe_gui_controller = doiVe_gui_controller;
    }
    public static void loaiTrang(String link){
        trang = link;
    }

    @FXML
    void btnChoOnAction(ActionEvent event) {
        if(trang.equals("BanVe_GUI.fxml")){
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
        }else if (trang.equals("DoiVe_GUI.fxml")) {
            System.out.printf("đã vào trang đổi vé");
        }else {
            System.out.printf("trang không xác định");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(double doDaiChang){
        btnCho.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Cho.css").toExternalForm());
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
            case DADATHOACBAN -> {
                btnCho.getStyleClass().clear();
                btnCho.getStyleClass().add("choDaDatHoacBan");
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

    public void chuyenMauDaThemVaoGioVe(){
        btnCho.getStyleClass().clear();
        btnCho.getStyleClass().add("choDaThemVaoGioVe");
        daThemVaoGio = true;
        capNhatTrangThai();
    }

    public void capNhatTrangThai(){
        String trangThaiCho = "";
        giaCho = CurrencyFormat.currencyFormat(cho.getGiaCho());
        if(daThemVaoGio){
            trangThaiCho = "Đã thêm vào giỏ vé";
        }else if(dangChon){
            trangThaiCho = "Đang chọn";
        }else{
            switch (cho.getTrangThaiCho()){
                case TrangThaiCho.CONTRONG -> {
                    trangThaiCho = "Còn trống";
                }
                case TrangThaiCho.DADATHOACBAN -> {
                    trangThaiCho = "Đã đặt hoặc bán";
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
