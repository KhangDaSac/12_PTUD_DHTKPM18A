package GUI.controllers;

import DAO.HoaDon_DAO;
import DTO.*;
import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HoaDon_GUI_controller extends Application {
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


    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(HoaDon_GUI_controller.class.getResource("/view/HoaDon_GUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hóa đơn");
        primaryStage.setScene(scene);
        primaryStage.show();
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
                    setText(item.getMaCaLamViec()); // Giả sử phương thức getter là getMaCa()
                }
            }
        });

// Thiết lập PropertyValueFactory cho colMaKhachHang
        colMaKhachHang.setCellValueFactory(new PropertyValueFactory<HoaDon, KhachHang>("khachHang"));
        colMaKhachHang.setCellFactory(column -> new TableCell<HoaDon, KhachHang>() {
            @Override
            protected void updateItem(KhachHang item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMaKhachHang()); // Giả sử phương thức getter là getMaKhachHang()
                }
            }
        });

        // Add data to TableView
        tableView.setItems(getHoaDonData());
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void timDanhSachHoaDon(){

    }
}
