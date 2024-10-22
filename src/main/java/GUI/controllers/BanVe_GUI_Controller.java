package GUI.controllers;

import BUS.QuanLyChuyenTau_BUS;
import DTO.ChuyenTau;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BanVe_GUI_Controller {

    @FXML
    private JFXButton btnTiepTuc;

    @FXML
    private JFXButton btnTimChuyenTau;


    @FXML
    private ComboBox<String> cmbGaTauDen;

    @FXML
    private ComboBox<String> cmbGaTauDi;

    @FXML
    private DatePicker dapNgayKhoiHanh;

    @FXML
    private GridPane grpDanhSachCho;

    @FXML
    private HBox hboxDanhSachChuyenTau;

    @FXML
    void btnTimChuyenTauOnAction(ActionEvent event) {
        hienThiDanhSachChuyenTau();
    }

    @FXML
    void btnChonCaToaOnAction(ActionEvent event) {

    }

    public void timDanhSachChuyenTau(){
        String gaTauDi = cmbGaTauDi.getValue();
        String gaTauDen = cmbGaTauDen.getValue();
        LocalDateTime ngayDi = dapNgayKhoiHanh.getValue().atTime(0, 0, 0);
        ArrayList<ChuyenTau> chuyenTauList = QuanLyChuyenTau_BUS.getDanhSachChuyenTau(gaTauDi, gaTauDen, ngayDi);

    }

    public void hienThiDanhSachChuyenTau(){


    }


}
