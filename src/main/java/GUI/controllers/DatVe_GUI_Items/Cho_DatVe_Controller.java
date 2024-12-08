package GUI.controllers.DatVe_GUI_Items;

import DTO.*;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.DatVe_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class Cho_DatVe_Controller implements Initializable {
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

    public Cho getCho() {
        return cho;
    }

    public void setCho(Cho cho) {
        this.cho = cho;
    }

    private DatVe_GUI_Controller datVe_gui_controller;

    public DatVe_GUI_Controller getDatVe_gui_controller() {
        return datVe_gui_controller;
    }

    public void setDatVe_gui_controller(DatVe_GUI_Controller datVe_gui_controller) {
        this.datVe_gui_controller = datVe_gui_controller;
    }

    @FXML
    void btnChoOnAction(ActionEvent event) {
        if(!kiemTraVeTrongGio()){
            if(cho.getTrangThaiCho() == TrangThaiCho.CONTRONG){
                if(!kiemTraChoDangChon()){
                    datVe_gui_controller.getChoChonList().add(cho);
                }else{
                    datVe_gui_controller.getChoChonList().remove(cho);
                }
                capNhatTrangThai();
                datVe_gui_controller.capNhatCacChoDaChon();
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
        int thuTuGaDi = datVe_gui_controller.getChuyenTauControllerList()
                .get(datVe_gui_controller.getThuTuChuyenTauDangChon()).getChiTietChuyenTauDi().getThuTuGa();
        int thuTuGaDen = datVe_gui_controller.getChuyenTauControllerList()
                .get(datVe_gui_controller.getThuTuChuyenTauDangChon()).getChiTietChuyenTauDen().getThuTuGa();

        for(VeDat veDat : datVe_gui_controller.getHoaDonDatVe().getDanhSachVeDat()){
            int thuTuGaDiCuaVe = veDat.getThongTinGaTauDi().getThuTuGa();
            int thuTuGaDenCuaVe = veDat.getThongTinGaTauDen().getThuTuGa();


            for (ChiTietVeDat chiTietVeDat : veDat.getDanhSachChiTietVeDat()){
                if(chiTietVeDat.getCho().equals(cho))
                    if(!(thuTuGaDen <= thuTuGaDiCuaVe || thuTuGaDi >= thuTuGaDenCuaVe)){
                        return true;
                    }
            }
        }

        return false;
    }



    public boolean kiemTraChoDangChon(){
        return datVe_gui_controller.getChoChonList()
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
