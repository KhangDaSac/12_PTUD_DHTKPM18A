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
import javafx.scene.image.Image;
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
    private AnchorPane anpToaTauSau;

    @FXML
    private AnchorPane anpToaTauTruoc;

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

    private String maGaDi;

    private String maGaDen;

    private ArrayList<GaTau> gaTauList;

    private ArrayList<ChuyenTau> chuyenTauList;

    private ArrayList<ChuyenTau_Controller> chuyenTauControllerList = new ArrayList<ChuyenTau_Controller>();

    private int chuyenTauDangChon;



    private ArrayList<ToaTau> toaTauList;

    private int trangChuyenTauHienTai;

    private int trangToaTauHienTai;

    public int getChuyenTauDangChon() {
        return chuyenTauDangChon;
    }

    public void setChuyenTauDangChon(int chuyenTauDangChon) {
        this.chuyenTauDangChon = chuyenTauDangChon;
    }

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
        if(trangChuyenTauHienTai < Math.ceil(lengthList/4.0)){
            trangChuyenTauHienTai++;
            try {
                int batDau = Math.max(4*(trangChuyenTauHienTai - 1), 0);
                int ketThuc = Math.min(4 * trangChuyenTauHienTai, lengthList);
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
        if(trangChuyenTauHienTai > 1){
            trangChuyenTauHienTai--;
            try {
                int batDau = Math.max(4*(trangChuyenTauHienTai - 1), 0);
                int ketThuc = Math.min(4 * trangChuyenTauHienTai, lengthList);
                hienThiDanhSachChuyenTau(chuyenTauList, batDau, ketThuc);
                anpChuyenTauSau.setVisible(lengthList > ketThuc);
                anpChuyenTauTruoc.setVisible(batDau > 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void anpToaTauSauOnMouseClicked(MouseEvent event) {
        int lengthList = toaTauList.size();
        if(trangToaTauHienTai < Math.ceil(lengthList/9.0)){
            trangToaTauHienTai++;
            try {
                int batDau = Math.max(9 * (trangToaTauHienTai - 1), 0);
                int ketThuc = Math.min(9 * trangToaTauHienTai, lengthList);
                hienThiDanhSachToa(toaTauList, batDau, ketThuc);
                anpToaTauSau.setVisible(lengthList > ketThuc);
                anpToaTauTruoc.setVisible(batDau > 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void anpToaTauTruocOnMouseClicked(MouseEvent event) {
        int lengthList = toaTauList.size();
        if(trangToaTauHienTai > 1){
            trangToaTauHienTai--;
            try {
                int batDau = Math.max(9*(trangToaTauHienTai - 1), 0);
                int ketThuc = Math.min(9 * trangToaTauHienTai, lengthList);
                hienThiDanhSachToa(toaTauList, batDau, ketThuc);
                anpToaTauSau.setVisible(lengthList > ketThuc);
                anpToaTauTruoc.setVisible(batDau > 0);
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
            maGaDi = gaTauDi.getMaGaTau();
            maGaDen = gaTauDen.getMaGaTau();
            chuyenTauList = QuanLyChuyenTau_BUS.getDanhSachChuyenTau(maGaDi, maGaDen, ngayDi);
            System.out.println(chuyenTauList);
            trangChuyenTauHienTai = 1;
            anpChuyenTauTruoc.setVisible(false);
            anpChuyenTauSau.setVisible(chuyenTauList.size() > 4);
            hienThiDanhSachChuyenTau(chuyenTauList, 0, 4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void timDanhSachToaTau(String maChuyenTau){

        try {
            toaTauList = QuanLyChuyenTau_BUS.getDanhSachToaTau(maChuyenTau, maGaDi, maGaDen);
            anpToaTauTruoc.setVisible(false);
            anpToaTauSau.setVisible(toaTauList.size() > 9);
            trangToaTauHienTai = 1;
            hienThiDanhSachToa(toaTauList, 0, 9);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void hienThiDanhSachChuyenTau(ArrayList<ChuyenTau> chuyenTauList, int batDau, int ketThuc) throws IOException {
        hboxDanhSachChuyenTau.getChildren().clear();
        chuyenTauControllerList.clear();
        for (int i = batDau; i < ketThuc; i++){
            ChuyenTau chuyenTau = chuyenTauList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ChuyenTau.fxml"));
            Parent anchorPane = loader.load();
            ChuyenTau_Controller controller = loader.getController();
            chuyenTauControllerList.add(controller);
            controller.setBanVe_GUI_Controller(this);
            Image image;
            if(chuyenTau.getSoLuongChoTrongTrong() > 0){
                image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-gray.png"));
            }else{
                image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-red.png"));
            }
            controller.setDefaultImage(image);

            if(chuyenTauDangChon == i){
                image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-green.png"));
            }

            controller.setSoThuTu(i);
            controller.getImvChuyenTau().setImage(image);

            controller.getLblMaChuyenTau().setText(chuyenTau.getMaChuyenTau());
            controller.getLblThoiGianDi().setText(TimeFormat.formatLocalDateTime(chuyenTau.getThoiGianDi()));
            controller.getLblThoiGianDen().setText(TimeFormat.formatLocalDateTime(chuyenTau.getThoiGianDen()));
            controller.getLblDaDatVeBan().setText(String.valueOf(chuyenTau.getSoLuongChoDaBanVaDat()));
            controller.getLblChangDaiHon().setText(String.valueOf(chuyenTau.getSoLuongChoChangDaiHon()));
            controller.getLblConTrong().setText(String.valueOf(chuyenTau.getSoLuongChoTrongTrong()));
            hboxDanhSachChuyenTau.getChildren().add(anchorPane);
        }
    }



    public void hienThiDanhSachToa(ArrayList<ToaTau> toaTauList, int batDau, int ketThuc) throws IOException {
        hboxDanhSachToaTau.getChildren().clear();
        for(int i = batDau; i < ketThuc; i++){
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
            cmbGaTauDi.getItems().clear();
            String tenGaTau = cmbGaTauDi.getEditor().getText();
            for (GaTau gaTau : gaTauList){
                if(gaTau.getTenGaTau().toLowerCase().startsWith(tenGaTau.toLowerCase())){
                    cmbGaTauDi.getItems().add(gaTau.getTenGaTau());
                }
            }
        });


    }

    public void boChonTatCaChuyenTau(){
        for (ChuyenTau_Controller chuyenTau_Controller : chuyenTauControllerList){
            chuyenTau_Controller.chinhMauKhongChon();
        }
    }
}
