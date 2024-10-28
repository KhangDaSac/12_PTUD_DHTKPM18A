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
            banVe_GUI_controller.getCacChoDangChon()[cho.getSoCho() - 1] = !banVe_GUI_controller.getCacChoDangChon()[cho.getSoCho() - 1];
            banVe_GUI_controller.capNhatCacChoDaChon();
            System.out.println(banVe_GUI_controller.getCacChoDangChon()[cho.getSoCho() - 1]);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(){
        btnCho.setText(String.valueOf(cho.getSoCho()));
        chuyenMauMacDinh();

    }

    public void chuyenMauMacDinh(){
        btnCho.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Cho.css").toExternalForm());
        switch (cho.getTrangThaiCho()){
            case DADATHOACBAN -> {
                btnCho.getStyleClass().remove("choDangChon");
                btnCho.getStyleClass().add("choDaDatHoacBan");
            }
            case DANHCHOCHANGDAIHON -> {
                btnCho.getStyleClass().remove("choDangChon");
                btnCho.getStyleClass().add("choDanhChoChanDaiHon");
            }
            case CONTRONG -> {
                btnCho.getStyleClass().remove("choDangChon");
                btnCho.getStyleClass().add("choTrong");
            }
        }
    }

    public void chuyenMauDangChon(){
        btnCho.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Cho.css").toExternalForm());
        btnCho.getStyleClass().remove("choTrong");
        btnCho.getStyleClass().add("choDangChon");
    }
}
