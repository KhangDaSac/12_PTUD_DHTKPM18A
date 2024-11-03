package GUI.controllers;

import DAO.HoaDon_DAO;
import DTO.*;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import java.awt.*;
import java.awt.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuanLyHoaDon_GUI_Controller implements Initializable {
    @FXML
    private TableColumn<HoaDon, String> colMaHoaDon;
    @FXML
    private TableColumn<HoaDon, LocalDateTime> colThoiGianLap;
    @FXML
    private TableColumn<HoaDon, Double> colTongTien;
    @FXML
    private TableColumn<HoaDon, Double> colTongTienCoc;
    @FXML
    private TableColumn<HoaDon, Double> colTongTienTra;
    @FXML
    private TableColumn<HoaDon, LoaiHoaDon> colLoaiHoaDon;
    @FXML
    private TableColumn<HoaDon, TrangThaiHoaDon> colTrangThaiHoaDon;
    @FXML
    private TableColumn<HoaDon, CaLamViec> colMaCaLamViec;
    @FXML
    private TableColumn<HoaDon, KhachHang> colMaKhachHang;
    @FXML
    private TableView<HoaDon> tableView;
    @FXML
    private TableColumn<Object, Object> colSTT;
    private Main_Controller main_controller;
    @FXML
    private ComboBox<String> cmbTrangThaiHoaDon;
    @FXML
    private ComboBox<String> cmbLoaiHoaDon;
    @FXML
    private TextField txtMaHoaDon;
    @FXML
    private TextField txtCCCD;
    @FXML
    private TextField txtMaCaLamViec;
    @FXML
    private DatePicker dapThoiGianLap;
    @FXML
    private Button btnTimKiemHoaDon;
    @FXML
    private Button btnXuatHoaDon;
     public void initializeComboBoxes(){
         ObservableList<String> trangThaiHoaDon = FXCollections.observableArrayList("Chờ lấy vé","Đã lấy một phần","Đã lấy toàn bộ","Đã hủy hết vé");
         cmbTrangThaiHoaDon.setItems(trangThaiHoaDon);
         ObservableList<String> loaiHoaDon = FXCollections.observableArrayList("Hóa đơn đặt vé","Hóa đơn bán vé");
         cmbLoaiHoaDon.setItems(loaiHoaDon);
     }



    public ObservableList<HoaDon> getHoaDonData() {
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        hoaDonList = new HoaDon_DAO().getDanhSachHoaDon();
        ObservableList<HoaDon> hoaDonData = FXCollections.observableArrayList(hoaDonList);
        System.out.println("Số lượng hóa đơn: " + hoaDonData.size());
        return hoaDonData;
    }

    @FXML
    public void initialize() {
        colSTT.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(column.getValue()) + 1));
        colMaHoaDon.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
        colThoiGianLap.setCellValueFactory(new PropertyValueFactory<>("thoiGianLap"));
        colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        colTongTienCoc.setCellValueFactory(new PropertyValueFactory<>("tongTienDaDatCoc"));
        colTongTienTra.setCellValueFactory(new PropertyValueFactory<>("tongTienKhachHangTra"));
        colLoaiHoaDon.setCellValueFactory(new PropertyValueFactory<>("loaiHoaDon"));
        colTrangThaiHoaDon.setCellValueFactory(new PropertyValueFactory<>("trangThaiHoaDon"));
        colMaCaLamViec.setCellValueFactory(new PropertyValueFactory<HoaDon, CaLamViec>("caLamViec"));
        colMaCaLamViec.setCellFactory(column -> new TableCell<HoaDon, CaLamViec>() {
            @Override
            protected void updateItem(CaLamViec item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMaCaLamViec());
                }
            }
        });

        colMaKhachHang.setCellValueFactory(new PropertyValueFactory<HoaDon, KhachHang>("khachHangMua"));
        colMaKhachHang.setCellFactory(column -> new TableCell<HoaDon, KhachHang>() {
            @Override
            protected void updateItem(KhachHang item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMaKhachHang());
                }
            }
        });

        // Add data to TableView
        tableView.setItems(getHoaDonData());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }

    public void setMain_Controller(Main_Controller mainController) {
        this.main_controller = mainController;
    }

    public void btnTimKiemHoaDonOnAcTion(javafx.event.ActionEvent actionEvent) {
         if(cmbTrangThaiHoaDon.getValue() == null){
             String trangThaiHoaDon = "NULL";
         }
         if (cmbTrangThaiHoaDon.getValue().equals("Chờ lấy vé")) {
             String trangThaiHoaDon = "CHOLAYVE";
         }
            if (cmbTrangThaiHoaDon.getValue().equals("Đã lấy một phần")) {
                String trangThaiHoaDon = "DALAYMOTPHAN";
            }
            if (cmbTrangThaiHoaDon.getValue().equals("Đã lấy toàn bộ")) {
                String trangThaiHoaDon = "DALAYTOANBO";
            }
            if (cmbTrangThaiHoaDon.getValue().equals("Đã hủy hết vé")) {
                String trangThaiHoaDon = "DAHUYHETVE";
            }
            if (cmbLoaiHoaDon.getValue().equals("Hóa đơn đặt vé")) {
                String loaiHoaDon = "HOADONDATVE";
            }
            if (cmbLoaiHoaDon.getValue().equals("Hóa đơn bán vé")) {
                String loaiHoaDon = "HOADONBANVE";
            }
            if(cmbTrangThaiHoaDon.getValue() == null){
                String loaiHoaDon = "NULL";
            }
            if (txtMaHoaDon.getText().isEmpty()) {
                String maHoaDon = "NULL";
            }
            if (txtCCCD.getText().isEmpty()) {
                String cccd = "NULL";
            }
            if (txtMaCaLamViec.getText().isEmpty()) {
                String maCaLamViec = "NULL";
            }
            if (dapThoiGianLap.getValue() == null) {
                LocalDate thoiGianLap = null;
            }
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        hoaDonList = new HoaDon_DAO().getDSHoaDonTheoCacTieuChi(txtMaHoaDon.getText(),txtCCCD.getText(), txtMaCaLamViec.getText(), cmbTrangThaiHoaDon.getValue(), cmbLoaiHoaDon.getValue(), dapThoiGianLap.getValue());
        ObservableList<HoaDon> hoaDonData = FXCollections.observableArrayList(hoaDonList);
        colSTT.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(column.getValue()) + 1));
        colMaHoaDon.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
        colThoiGianLap.setCellValueFactory(new PropertyValueFactory<>("thoiGianLap"));
        colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        colTongTienCoc.setCellValueFactory(new PropertyValueFactory<>("tongTienDaDatCoc"));
        colTongTienTra.setCellValueFactory(new PropertyValueFactory<>("tongTienKhachHangTra"));
        colLoaiHoaDon.setCellValueFactory(new PropertyValueFactory<>("loaiHoaDon"));
        colTrangThaiHoaDon.setCellValueFactory(new PropertyValueFactory<>("trangThaiHoaDon"));
        colMaCaLamViec.setCellValueFactory(new PropertyValueFactory<HoaDon, CaLamViec>("caLamViec"));
        colMaCaLamViec.setCellFactory(column -> new TableCell<HoaDon, CaLamViec>() {
            @Override
            protected void updateItem(CaLamViec item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMaCaLamViec());
                }
            }
        });

        colMaKhachHang.setCellValueFactory(new PropertyValueFactory<HoaDon, KhachHang>("khachHangMua"));
        colMaKhachHang.setCellFactory(column -> new TableCell<HoaDon, KhachHang>() {
            @Override
            protected void updateItem(KhachHang item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMaKhachHang());
                }
            }
        });

        // Add data to TableView
        tableView.setItems(hoaDonData);
    }

    public void btnXuatHoaDonOnAcTion(ActionEvent actionEvent) {
    }
}
