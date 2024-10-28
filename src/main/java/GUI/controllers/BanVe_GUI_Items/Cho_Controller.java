package GUI.controllers.BanVe_GUI_Items;

import DTO.Cho;
import DTO.TrangThaiCho;
import GUI.controllers.BanVe_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Cho_Controller implements Initializable {
    @FXML
    private JFXButton btnCho;

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
        if(cho.getTrangThaiCho() == TrangThaiCho.CONTRONG){
            if(!banVe_GUI_controller.getChoChonList().contains(cho)){
                banVe_GUI_controller.getChoChonList().add(cho);
            }else{
                banVe_GUI_controller.getChoChonList().remove(cho);
            }
            banVe_GUI_controller.capNhatCacChoDaChon();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(){
        btnCho.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Cho.css").toExternalForm());
        btnCho.setText(String.valueOf(cho.getSoCho()));
        chuyenMauMacDinh();
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
    }
}
