package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.Ve;
import GUI.controllers.HuyVe_GUI_Items.ChiTietVe_HuyVe_Controller;
import GUI.controllers.HuyVe_GUI_Items.Ve_HuyVe_Controller;
import GUI.controllers.LayVe_GUI_Items.PhieuDatVe_LayVe_Controller;
import com.jfoenix.controls.JFXButton;
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

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class HuyVe_GUI_Controller implements Initializable {

    @FXML
    private Button btnChonTatCaPhieuDatVe;

    @FXML
    private JFXButton btnTimHoaDon;

    @FXML
    private TableColumn<HoaDon, String> colMaHoaDon;

    @FXML
    private TableColumn<HoaDon, KhachHang> colTenKhachHang;

    @FXML
    private TableColumn<HoaDon, String> colNgayThanhToan;

    @FXML
    private TableColumn<HoaDon, Ve> colLoaiVe;

    @FXML
    private TableColumn<HoaDon, Double> colTongTien;

    @FXML
    private DatePicker dapNgayThanhToan;

    @FXML
    private TableView<HoaDon> tableView;

    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtTongTienTraKhach;

    @FXML
    private VBox vBoxDanhSachChiTietVe;

    @FXML
    private VBox vboxDanhSachVe;

    @FXML
    private Main_Controller main_Controller;

    private ArrayList<Ve_HuyVe_Controller> veHuyVeControllerList = new ArrayList<>();
    private ArrayList<ChiTietVe_HuyVe_Controller> chiTietVeHuyVeControllers = new ArrayList<>();

    public Main_Controller getMain_Controller() {
        return main_Controller;
    }

    public void setMain_Controller(Main_Controller main_Controller) {
        this.main_Controller = main_Controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDuLieuLenTable();
//        setupTableSelectionHandler(tableView);
    }

    public ObservableList<HoaDon> getHoaDonData() {
        ArrayList<HoaDon> hoaDonList;
        if (dapNgayThanhToan.getValue() == null && txtCCCD.getText().isEmpty()) {
            hoaDonList = new QuanLyHoaDon_BUS().getDanhSachHoaDonDat();
        } else {
            hoaDonList = new QuanLyHoaDon_BUS().getHoaDonTheoMaKhachHangVaThoiGianLap(txtCCCD.getText(), dapNgayThanhToan.getValue());
        }
        // In ra số lượng hóa đơn nhận được để kiểm tra
        System.out.println("Số lượng hóa đơn nhận được: " + hoaDonList.size());
        return FXCollections.observableArrayList(hoaDonList);
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
        colLoaiVe.setCellFactory(column -> new TableCell<HoaDon, Ve>() {
            @Override
            protected void updateItem(Ve item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getLoaiVe().toString());
                }
            }
        });
        colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        tableView.setItems(getHoaDonData());
    }

    public void btnTimHoaDonOnAction(ActionEvent actionEvent) {
        loadDuLieuLenTable();
    }

    public static String phatSinhMaLichSuHuyVe() {
        String prefix = "HDHV";
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        String datePart = now.format(formatter);
        Random random = new Random();
        int numberPart = 100000 + random.nextInt(900000);
        return prefix + datePart + String.format("%06d", numberPart);
    }

    @FXML
    void btnHuyVeOnAction(ActionEvent event) {
//        double tongTienTraKhach = tinhTongTienHuyDatVe();
//        if (tongTienTraKhach <= 0) {
//            JOptionPane.showMessageDialog(null, "Không có vé nào được chọn");
//            return;
//        }
//
//        int dialogButton = JOptionPane.YES_NO_OPTION;
//        int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy vé?", "Warning", dialogButton);
//        if (dialogResult != JOptionPane.YES_OPTION) {
//            return;
//        }
//
//        chonLayVeTatPhieuDatVe();
//        for (Ve_HuyVe_Controller controller : veHuyVeControllerList) {
//            if (!controller.isChonLayVe()) {
//                continue;
//            }
//            Ve ve = controller.getVe();
//            if (phieuDatVe.getTongTienDatCoc() > 0) {
//                LocalDateTime thoiGianDi = phieuDatVe.getChiTietChuyenTauDi().getThoiGianDi();
//                long gioCanHuy = phieuDatVe.getLoaiPhieuDatVe().equals("PHIEUDATCANHAN") ? 48 : 72;
//
//                if (thoiGianDi.minusHours(gioCanHuy).isAfter(LocalDateTime.now())) {
//                    QuanLyPhieuDatVe_BUS.huyPhieuDatVe(phieuDatVe.getMaPhieuDatVe());
//                    if (phieuDatVe.getLoaiPhieuDatVe().equals("PHIEUDATCANHAN")) {
//                        LichSuHuyDatVe_DAO.themLichSuHuyDatVe(
//                                phatSinhMaLichSuHuyDatVe(),
//                                LocalDateTime.now(),
//                                phieuDatVe.getTongTienDatCoc() - tongTienTraKhach,
//                                tongTienTraKhach,
//                                phieuDatVe.getMaPhieuDatVe(),
//                                CaLamViec_DAO.timCaLamViec(LocalDate.now()).getMaCaLamViec()
//                        );
//                    }
//                    JOptionPane.showMessageDialog(null, "Hủy đặt vé thành công");
//                } else {
//                    JOptionPane.showMessageDialog(null, "Hủy đặt vé không thành công");
//                }
//            } else {
//                QuanLyPhieuDatVe_BUS.huyPhieuDatVe(phieuDatVe.getMaPhieuDatVe());
//            }
//        }
        loadDuLieuLenTable();
    }
//
//    private void setupTableSelectionHandler(TableView<HoaDon> tableView) {
//        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HoaDon>() {
//            @Override
//            public void changed(ObservableValue<? extends HoaDon> observable, HoaDon oldSelection, HoaDon newSelection) {
//                if (newSelection != null) {
//                    getDanhSachPhieuDatVeTheoHoaDon();
//                    hienThiDanhSachPhieu();
//                }
//            }
//        });
//    }
//
//    public void getDanhSachVeTheoHoaDon() {
//        danhSachVe = QuanLyPhieuDatVe_BUS.getDanhSachPhieuDatVeTheoMaHoaDon(tableView.getSelectionModel().getSelectedItem().getMaHoaDon());
//    }
//
//    public void hienThiDanhSachVe() {
//        vboxDanhSachVe.getChildren().clear();
//        if (tableView.getSelectionModel().getSelectedItem() == null)
//            return;
//        int length = hienThiDanhSachVe.size();
//        for (int i = 0; i < length; i++) {
//            Ve ve = danhSachPhieuDatVe.get(i);
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyVe_GUI_Items/Ve_HuyVe.fxml.fxml"));
//                Parent anchorPane = loader.load();
//                PhieuDatVe_LayVe_Controller controller = loader.getController();
//                phieuDatVeLayVeControllerList.add(controller);
//                controller.setHuyDatVe_gui_controller(this);
//                controller.setSoThuTu(i);
//                controller.setPhieuDatVe(phieuDatVe);
//                controller.khoiTao();
//
//                vboxDanhSachPhieuDatVe.getChildren().add(anchorPane);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public void hienThiDanhSachChiTietPhieuDatVe(PhieuDatVe phieuDatVe) {
//        vBoxDanhSachChiTietPhieuDatVe.getChildren().clear();
//        chiTietPhieuDatVeLayVeControllerList.clear();
//        if (phieuDatVe == null)
//            return;
//        for (ChiTietPhieuDatVe chiTietPhieuDatVe : chiTietPhieuDatVeList) {
//            if (chiTietPhieuDatVe.getPhieuDatVe().equals(phieuDatVe)) {
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/ChiTietPhieuDatVe_LayVe.fxml"));
//                    Parent anchorPane = loader.load();
//                    ChiTietPhieuDatVe_LayVe_Controller controller = loader.getController();
//                    chiTietPhieuDatVeLayVeControllerList.add(controller);
//                    controller.setHuyDatVe_gui_controller(this);
//
//                    controller.setChiTietPhieuDatVe(chiTietPhieuDatVe);
//                    controller.khoiTao();
//
//                    vBoxDanhSachChiTietPhieuDatVe.getChildren().add(anchorPane);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
//
//    public void getDanhSachChiTietPhieuDatVeTheoPhieuDatVe(String PDV) {
//        chiTietPhieuDatVeList = QuanLyPhieuDatVe_BUS.getDanhSachChiTietPhieuDatVeTheoMaPhieuDatVe(PDV);
//    }
//
//    public void chonLayVeTatPhieuDatVe(){
//        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
//            controller.chonLayVe();
//        }
//    }
//
    @FXML
    void btnChonTatCaOnAction(ActionEvent event) {
//        chonLayVeTatPhieuDatVe();
    }
//
//    public double tinhTongTienHuyDatVe(){
//        double tongTienCoc = 0;
//        double lePhiHuy = 0;
//        boChonTatCaPhieuDatVe();
//        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
//            if(controller.isChonLayVe()){
//                tongTienCoc += controller.getPhieuDatVe().getTongTienDatCoc();
//                if (controller.getPhieuDatVe().getTongTienDatCoc() > 0) {
//                    if (controller.getPhieuDatVe().getLoaiPhieuDatVe().equals("PHIEUDATCANHAN")) {
//                        if(controller.getPhieuDatVe().getChiTietChuyenTauDi().getThoiGianDi().minusHours(48).isAfter(LocalDateTime.now())){
//                            lePhiHuy += controller.getPhieuDatVe().getTongTienVe() * 0.1;
//                        } else if(controller.getPhieuDatVe().getChiTietChuyenTauDi().getThoiGianDi().minusHours(4).isAfter(LocalDateTime.now())){
//                            lePhiHuy += controller.getPhieuDatVe().getTongTienVe() * 0.2;
//                        }
//                    } else {
//                        if(controller.getPhieuDatVe().getChiTietChuyenTauDi().getThoiGianDi().minusHours(72).isAfter(LocalDateTime.now())){
//                            lePhiHuy += controller.getPhieuDatVe().getTongTienVe() * 0.2;
//                        } else if(controller.getPhieuDatVe().getChiTietChuyenTauDi().getThoiGianDi().minusHours(24).isAfter(LocalDateTime.now())){
//                            lePhiHuy += controller.getPhieuDatVe().getTongTienVe() * 0.3;
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("Da tinh tien");
//        txtTongTienTraKhach.setText(CurrencyFormat.currencyFormat(tongTienCoc-lePhiHuy));
//        return tongTienCoc-lePhiHuy;
//    }
//
//    public void boChonTatCaPhieuDatVe() {
//        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
//            controller.boChonPhieuDatVe();
//        }
//    }
//
//    public double tinhTongTienHuyDatVe(){
//        double tongTienCoc = 0;
//        double lePhiHuy = 0;
//        boChonTatCaPhieuDatVe();
//        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
//            if(controller.isChonLayVe()){
//                tongTienCoc += controller.getPhieuDatVe().getTongTienDatCoc();
//                if (controller.getPhieuDatVe().getTongTienDatCoc() > 0) {
//                    if (controller.getPhieuDatVe().getLoaiPhieuDatVe().equals("PHIEUDATCANHAN")) {
//                        if(controller.getPhieuDatVe().getChiTietChuyenTauDi().getThoiGianDi().minusHours(48).isAfter(LocalDateTime.now())){
//                            lePhiHuy += controller.getPhieuDatVe().getTongTienVe() * 0.1;
//                        } else if(controller.getPhieuDatVe().getChiTietChuyenTauDi().getThoiGianDi().minusHours(4).isAfter(LocalDateTime.now())){
//                            lePhiHuy += controller.getPhieuDatVe().getTongTienVe() * 0.2;
//                        }
//                    } else {
//                        if(controller.getPhieuDatVe().getChiTietChuyenTauDi().getThoiGianDi().minusHours(72).isAfter(LocalDateTime.now())){
//                            lePhiHuy += controller.getPhieuDatVe().getTongTienVe() * 0.2;
//                        } else if(controller.getPhieuDatVe().getChiTietChuyenTauDi().getThoiGianDi().minusHours(24).isAfter(LocalDateTime.now())){
//                            lePhiHuy += controller.getPhieuDatVe().getTongTienVe() * 0.3;
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("Da tinh tien");
//        txtTongTienTraKhach.setText(CurrencyFormat.currencyFormat(tongTienCoc-lePhiHuy));
//        return tongTienCoc-lePhiHuy;
//    }
//
//    public void boChonTatCaPhieuDatVe() {
//        for(PhieuDatVe_LayVe_Controller controller : phieuDatVeLayVeControllerList){
//            controller.boChonPhieuDatVe();
//        }
//    }



}
