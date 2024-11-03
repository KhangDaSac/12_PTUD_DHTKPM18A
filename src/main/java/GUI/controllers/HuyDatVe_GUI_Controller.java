package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyPhieuDatVe_BUS;
import BUS.QuanLyVe_BUS;
import DTO.*;
import GUI.controllers.BanVe_GUI_Items.Cho_Controller;
import GUI.controllers.BanVe_GUI_Items.ChuyenTau_Controller;
import GUI.controllers.LayVe_GUI_Items.PhieuDatVe_LayVe_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HuyDatVe_GUI_Controller implements Initializable {
    @FXML
    private TableView<HoaDon> tableView;
    @FXML
    private TextField txtCCCD;
    @FXML
    private DatePicker dapNgayThanhToan;
    @FXML
    private TableColumn<HoaDon, String> colMaHoaDon;
    @FXML
    private TableColumn<HoaDon, KhachHang> colTenKhachHang;
    @FXML
    private TableColumn<HoaDon, String> colNgayThanhToan;
    @FXML
    private TableColumn<HoaDon, Double> colTongTienCoc;
    @FXML
    private TableColumn<HoaDon, Double> colTongTien;
    @FXML
    private Main_Controller main_Controller;
    @FXML
    private JFXButton btnTimHoaDon;
    @FXML
    private JFXButton btnChonTatCa;
    @FXML
    private JFXButton btnHuyPhieuDat;
    @FXML
    private JFXButton btnLayVe;
    @FXML
    private VBox vboxDanhSachPhieuDatVe;
    @FXML
    private VBox vboxDanhSachChiTietPhieuDatVe;
    private   ArrayList<PhieuDatVe> danhSachPhieuDatVe = new ArrayList<>();
    private ArrayList<PhieuDatVe_LayVe_Controller> phieuDatVeLayVeControllerList = new ArrayList<PhieuDatVe_LayVe_Controller>();

    public Main_Controller getMain_Controller() {
        return main_Controller;
    }

    public void setMain_Controller(Main_Controller main_Controller) {
        this.main_Controller = main_Controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDuLieuLenTable();
    }

    public ObservableList<HoaDon> getHoaDonData() {
        if (dapNgayThanhToan.getValue() == null && txtCCCD.getText().isEmpty()) {
            ArrayList<HoaDon> hoaDonList = new QuanLyHoaDon_BUS().getDanhSachHoaDon();
            return FXCollections.observableArrayList(hoaDonList);
        }
        else{
            LocalDate thoiGianThanhToan = dapNgayThanhToan.getValue();
            ArrayList<HoaDon> hoaDonList = new QuanLyHoaDon_BUS().getHoaDonTheoMaKhachHangVaThoiGianLap(txtCCCD.getText(), thoiGianThanhToan);
            return FXCollections.observableArrayList(hoaDonList);
        }

    }

    public void loadDuLieuLenTable() {
        colMaHoaDon.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
        colTenKhachHang.setCellValueFactory(new PropertyValueFactory<>("khachHangMua"));
        colTenKhachHang.setCellFactory(column -> new TableCell<HoaDon, KhachHang>() {
            @Override
            protected void updateItem(KhachHang item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTenKhachHang());
                }
            }
        });
        colNgayThanhToan.setCellValueFactory(new PropertyValueFactory<>("thoiGianLap"));
        colTongTienCoc.setCellValueFactory(new PropertyValueFactory<>("tongTienDaDatCoc"));
        colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        tableView.setItems(getHoaDonData());
    }
    public void btnTimHoaDonOnAction(ActionEvent actionEvent) {
        loadDuLieuLenTable();
    }

    public void btnHuyPhieuDatOnAction(ActionEvent actionEvent) {
    }
    private void setupTableSelectionHandler(TableView<HoaDon> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HoaDon>() {
            @Override
            public void changed(ObservableValue<? extends HoaDon> observable, HoaDon oldSelection, HoaDon newSelection) {
                if (newSelection != null) {
                    getDanhSachPhieuDatVeTheoHoaDon();
                    hienThiDanhSachPhieu();

                }
            }
        });
    }
    public void getDanhSachPhieuDatVeTheoHoaDon(){
        danhSachPhieuDatVe = QuanLyPhieuDatVe_BUS.getDanhSachPhieuDatVeTheoMaHoaDon(tableView.getSelectionModel().getSelectedItem().getMaHoaDon());
    }
    public void hienThiDanhSachPhieu(){
        vboxDanhSachPhieuDatVe.getChildren().clear();
        if(tableView.getSelectionModel().getSelectedItem() == null)
            return;
        int length = danhSachPhieuDatVe.size();
        for(int i = 0; i<length; i++){
            PhieuDatVe phieuDatVe = danhSachPhieuDatVe.get(i);
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/PhieuDatVe_LayVe.fxml"));
                Parent anchorPane = loader.load();
                PhieuDatVe_LayVe_Controller controller = loader.getController();
                phieuDatVeLayVeControllerList.add(controller);
                controller.setHuyDatVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setPhieuDatVe(phieuDatVe);
                controller.khoiTao();

                vboxDanhSachPhieuDatVe.getChildren().add(anchorPane);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

}