package GUI.controllers;

import BUS.QuanLyChuyenTau_BUS;
import DTO.ChuyenTau;
import DTO.ToaTau;
import GUI.controllers.BanVe_GUI_Items.ChuyenTau_Controller;
import GUI.controllers.BanVe_GUI_Items.ToaTau_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.time.LocalDate;
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
    private HBox hboxDanhSachToaTau;

    @FXML
    void btnTimChuyenTauOnAction(ActionEvent event) {
            timDanhSachChuyenTau();
    }

    @FXML
    void btnChonCaToaOnAction(ActionEvent event) {

    }

    public void timDanhSachChuyenTau(){
        String gaTauDi = cmbGaTauDi.getValue();
        String gaTauDen = cmbGaTauDen.getValue();
        LocalDate ngayDi = dapNgayKhoiHanh.getValue();
        ArrayList<ChuyenTau> chuyenTauList;
        try {
            chuyenTauList = QuanLyChuyenTau_BUS.getDanhSachChuyenTau(gaTauDi, gaTauDen, ngayDi);
            System.out.println(chuyenTauList);
            hienThiDanhSachChuyenTau(chuyenTauList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void timDanhSachToaTau(String maChuyenTau){
        ArrayList<ToaTau> toaTauList;
        try {
            toaTauList = QuanLyChuyenTau_BUS.getDanhSachToaTau(maChuyenTau);
            hienThiDanhSachToa(toaTauList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void hienThiDanhSachChuyenTau(ArrayList<ChuyenTau> chuyenTauList) throws IOException {
        hboxDanhSachChuyenTau.getChildren().clear();
        for (int i = 0; i < 4; i++){
            ChuyenTau chuyenTau = chuyenTauList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ChuyenTau.fxml"));
            Parent anchorPane = loader.load();
            ChuyenTau_Controller controller = loader.getController();
            controller.setBanVe_GUI_Controller(this);
            controller.getLblMaChuyenTau().setText(chuyenTau.getMaChuyenTau());
            controller.getLblThoiGianDi().setText("");
            controller.getLblThoiGianDen().setText("");



            hboxDanhSachChuyenTau.getChildren().add(anchorPane);
        }
    }



    public void hienThiDanhSachToa(ArrayList<ToaTau> toaTauList) throws IOException {
        hboxDanhSachToaTau.getChildren().clear();
        for(int i = 0; i < 9; i++){
            ToaTau toaTau = toaTauList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ToaTau.fxml"));
            Parent anchorPane = loader.load();
            ToaTau_Controller controller = loader.getController();
            controller.getLblThuTuTau().setText(String.valueOf(toaTau.getThuTuToa()));

            hboxDanhSachToaTau.getChildren().add(anchorPane);
        }
    }

    @FXML
    public void initialize() {
        hboxDanhSachToaTau.getChildren().clear();
        hboxDanhSachChuyenTau.getChildren().clear();

    }




}
