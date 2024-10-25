package GUI.controllers;

import BUS.QuanLyChuyenTau_BUS;
import DTO.ChuyenTau;
import DTO.GaTau;
import DTO.ToaTau;
import GUI.controllers.BanVe_GUI_Items.ChuyenTau_Controller;
import GUI.controllers.BanVe_GUI_Items.ToaTau_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import utils.TimeFormat;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BanVe_GUI_Controller implements Initializable {
    @FXML
    private AnchorPane anpChuyenTauSau;

    @FXML
    private AnchorPane anpChuyenTauTruoc;

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

    private ArrayList<GaTau> gaTauList;

    private ArrayList<ChuyenTau> chuyenTauList;

    private int trangGaTauHienTai;

    @FXML
    void btnTimChuyenTauOnAction(ActionEvent event) {
            timDanhSachChuyenTau();
    }

    @FXML
    void btnChonCaToaOnAction(ActionEvent event) {

    }

    @FXML
    void anpChuyenTauSauOnMouseClicked(MouseEvent event) {
        int lengthList = chuyenTauList.size();
        if(trangGaTauHienTai < Math.ceil(lengthList/4.0)){
            trangGaTauHienTai++;
            try {
                int batDau = Math.max(4*(trangGaTauHienTai - 1), 0);
                int ketThuc = Math.min(4 * trangGaTauHienTai, lengthList);
                hienThiDanhSachChuyenTau(chuyenTauList, batDau, ketThuc);
                anpChuyenTauSau.setVisible(lengthList > ketThuc);
                anpChuyenTauTruoc.setVisible(batDau > 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void anpChuyenTauTruocOnMouseClicked(MouseEvent event) {
        int lengthList = chuyenTauList.size();
        if(trangGaTauHienTai > 1){
            trangGaTauHienTai--;
            try {
                int batDau = Math.max(4*(trangGaTauHienTai - 1), 0);
                int ketThuc = Math.min(4 * trangGaTauHienTai, lengthList);
                hienThiDanhSachChuyenTau(chuyenTauList, batDau, ketThuc);
                anpChuyenTauSau.setVisible(lengthList > ketThuc);
                anpChuyenTauTruoc.setVisible(batDau > 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timDanhSachChuyenTau(){
        if(cmbGaTauDi.getSelectionModel().getSelectedIndex() < 0){
            System.out.println("Ga tàu đi không hợp lệ");
            return;
        }
        if(cmbGaTauDen.getSelectionModel().getSelectedIndex() < 0){
            System.out.println("Ga tàu đến không hợp lệ");
            return;
        }
        GaTau gaTauDi = gaTauList.get(cmbGaTauDi.getSelectionModel().getSelectedIndex());
        GaTau gaTauDen = gaTauList.get(cmbGaTauDen.getSelectionModel().getSelectedIndex());

        LocalDate ngayDi = dapNgayKhoiHanh.getValue();
        try {
            chuyenTauList = QuanLyChuyenTau_BUS.getDanhSachChuyenTau(gaTauDi.getMaGaTau(), gaTauDen.getMaGaTau(), ngayDi);
            System.out.println(chuyenTauList);
            trangGaTauHienTai = 1;
            anpChuyenTauTruoc.setVisible(false);
            anpChuyenTauSau.setVisible(chuyenTauList.size() > 4);
            hienThiDanhSachChuyenTau(chuyenTauList, 0, 4);
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

    public void hienThiDanhSachChuyenTau(ArrayList<ChuyenTau> chuyenTauList, int batDau, int ketThuc) throws IOException {
        hboxDanhSachChuyenTau.getChildren().clear();
        for (int i = batDau; i < ketThuc; i++){
            ChuyenTau chuyenTau = chuyenTauList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ChuyenTau.fxml"));
            Parent anchorPane = loader.load();
            ChuyenTau_Controller controller = loader.getController();
            controller.setBanVe_GUI_Controller(this);
            controller.getLblMaChuyenTau().setText(chuyenTau.getMaChuyenTau());
            controller.getLblThoiGianDi().setText(TimeFormat.formatLocalDateTime(chuyenTau.getThoiGianDi()));
            controller.getLblThoiGianDen().setText(TimeFormat.formatLocalDateTime(chuyenTau.getThoiGianDen()));
            controller.getLblDaDatVeBan().setText(String.valueOf(chuyenTau.getSoLuongChoDaBanVaDat()));
            controller.getLblChangDaiHon().setText(String.valueOf(chuyenTau.getSoLuongChoChangDaiHon()));
            controller.getLblConTrong().setText(String.valueOf(chuyenTau.getSoLuongChoTrongTrong()));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hello");
        gaTauList = QuanLyChuyenTau_BUS.getDanhSachGaTau();
        cmbGaTauDen.getItems().clear();
        cmbGaTauDi.getItems().clear();
        for(GaTau gaTau : gaTauList){
            cmbGaTauDen.getItems().add(gaTau.getTenGaTau());
            cmbGaTauDi.getItems().add(gaTau.getTenGaTau());
        }

        cmbGaTauDen.getSelectionModel().select(51);
        cmbGaTauDi.getSelectionModel().select(125);
        dapNgayKhoiHanh.setValue(LocalDate.of(2024, 10, 30));


        hboxDanhSachToaTau.getChildren().clear();
        hboxDanhSachChuyenTau.getChildren().clear();

        cmbGaTauDi.getEditor().setOnKeyTyped(event -> {

        });
    }
}
