package GUI.controllers.BanVe_GUI_Items;

import DTO.ChiTietVe;
import DTO.Cho;
import DTO.TrangThaiCho;
import DTO.Ve;
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
        if(!kiemTraVeTrongGio()){
            if(cho.getTrangThaiCho() == TrangThaiCho.CONTRONG){
                if(!kiemTraChoDangChon()){
                    banVe_GUI_controller.getChoChonList().add(cho);
                }else{
                    banVe_GUI_controller.getChoChonList().remove(cho);
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
        cho.setGiaCho(cho.tinhGiaCho(doDaiChang));
        lblGiaCho.setText(CurrencyFormat.currencyFormat(cho.getGiaCho()));
        lblLoaiCho.setText(cho.getLoaiCho().getTenLoaiCho());
        capNhatTrangThai();
    }

    public void chuyenMauDangChon(){
        anpTrangThaiCho.getStyleClass().clear();
        anpTrangThaiCho.getStyleClass().add("choDangChon");
    }

    public void chuyenMauDaThemVaoGioVe(){
        anpTrangThaiCho.getStyleClass().clear();
        anpTrangThaiCho.getStyleClass().add("choDaThemVaoGioVe");
    }

    public boolean kiemTraVeTrongGio(){
        int thuTuGaDi = banVe_GUI_controller.getChuyenTauControllerList()
                .get(banVe_GUI_controller.getThuTuChuyenTauDangChon()).getChiTietChuyenTauDi().getThuTuGa();
        int thuTuGaDen = banVe_GUI_controller.getChuyenTauControllerList()
                .get(banVe_GUI_controller.getThuTuChuyenTauDangChon()).getChiTietChuyenTauDen().getThuTuGa();

        for(Ve ve : banVe_GUI_controller.getHoaDonBanVe().getDanhSachVe()){
            int thuTuGaDiCuaVe = ve.getThongTinGaTauDi().getThuTuGa();
            int thuTuGaDenCuaVe = ve.getThongTinGaTauDen().getThuTuGa();


            for (ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()){
                if(chiTietVe.getCho().equals(cho))
                    if(!(thuTuGaDen <= thuTuGaDiCuaVe || thuTuGaDi >= thuTuGaDenCuaVe)){
                        return true;
                    }
            }
        }

        return false;
    }



    public boolean kiemTraChoDangChon(){
        return banVe_GUI_controller.getChoChonList()
                .stream()
                .anyMatch(c -> c.equals(cho));
    }

    public String trangThai(){

        if(kiemTraVeTrongGio()){
            chuyenMauDaThemVaoGioVe();
            return  "Đã thêm vào giỏ";
        }

        if(kiemTraChoDangChon()){
            chuyenMauDangChon();
            return "Đang chọn";
        }

        switch (cho.getTrangThaiCho()){
            case TrangThaiCho.CONTRONG -> {
                anpTrangThaiCho.getStyleClass().clear();
                anpTrangThaiCho.getStyleClass().add("choTrong");
                return  "Còn trống";
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

    public void capNhatTrangThai(){
        lblTrangThaiCho.setText(trangThai());
    }
}
