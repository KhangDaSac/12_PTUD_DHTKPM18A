package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyVe_BUS;
import DTO.*;
import GUI.controllers.BanVe_GUI_Items.Cho_Controller;
import GUI.controllers.BanVe_GUI_Items.ChuyenTau_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public void hienThiDanhSachPhieu() throws IOException {
        HoaDon hoaDon = tableView.getSelectionModel().getSelectedItem();
        if (hoaDon != null) {

        }
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

                }
            }
        });
    }

}