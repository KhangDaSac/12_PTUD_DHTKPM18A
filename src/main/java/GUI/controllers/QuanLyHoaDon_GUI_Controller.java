package GUI.controllers;


import DAO.*;
import DTO.*;
import GUI.controllers.HuyDatVe_GUI_Items.VeDat_HuyDatVe_Controller;
import connectDB.ConnectDB;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class QuanLyHoaDon_GUI_Controller implements Initializable {
    @FXML
    private TableView<Object> tableView;
    @FXML
    private Button btnBoChonTatCa;

    @FXML
    private Button btnChonTatCa;

    @FXML
    private Button btnInHoaDon;

    @FXML
    private Button btnInVe;

    @FXML
    private Button btnTimHoaDon;

    @FXML
    private ComboBox<String> cmbLoaiHoaDon;

    @FXML
    private DatePicker dapThoiGianLap;

    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtMaCaLam;

    @FXML
    private TextField txtMaHoaDon;

    @FXML
    private TableColumn<Object, String> colMaHoaDon;

    @FXML
    private TableColumn<Object, String> colMaKhachHang;

    @FXML
    private TableColumn<Object, String> colThoiGianLap;

    @FXML
    private TableColumn<Object, String> colMaCaLam;

    @FXML
    private TableColumn<Object, String> colTongTienCuoi;
    @FXML
    void btnBoChonTatCaOnAction(ActionEvent event) {

    }

    @FXML
    void btnChonTatCaOnACtion(ActionEvent event) {

    }

    @FXML
    void btnInHoaDonOnAcTion(ActionEvent event) {

    }

    @FXML
    void btnInVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnTimHoaDonOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initableComboBox();
        get15HoaDonGanNhat();
        hienThiDanhSachHoaDon();
    }
    private ArrayList<HoaDonDoiVe> dsHoaDonDoiVe = new ArrayList<>();
    private ArrayList<HoaDonHuyVe> dsHoaDonHuyVe = new ArrayList<>();
    private ArrayList<HoaDonHuyDatVe> dsHoaDonHuyDatVe = new ArrayList<>();
    private ArrayList<HoaDonLayVe> dsHoaDonLayVe = new ArrayList<>();
    private ArrayList<HoaDonDatVe> dsHoaDonDatVe = new ArrayList<>();
    private ArrayList<HoaDonBanVe> dsHoaDonBanVe = new ArrayList<>();
    public void initableComboBox() {
        ObservableList<String> loaiHoaDon = FXCollections.observableArrayList("Hóa đơn đặt vé", "Hóa đơn bán vé", "Hóa đơn hủy vé","Hóa đơn đổi vé","Hóa đơn hủy đặt vé","Hóa đơn lấy vé");
        cmbLoaiHoaDon.setItems(loaiHoaDon);
    }
    private ObservableList<Object> dsHoaDon = FXCollections.observableArrayList();
    private Object selectedHoaDon;
    public void get15HoaDonGanNhat(){
        Connection con = ConnectDB.getInstance().getConnection();
        String query ="exec tim15HoaDonGanNhat";
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                if(rs.getString("loaiHoaDon").equals("HOADONDATVE")){
                    HoaDonDatVe hoaDonDatVe = HoaDonDatVe_DAO.getHoaDonDatVeTheoMa(rs.getString("maHoaDon"));
                    dsHoaDon.add(hoaDonDatVe);
                }
                if (rs.getString("loaiHoaDon").equals("HOADONBANVE")){
                    HoaDonBanVe hoaDonBanVe = HoaDonBanVe_DAO.getHoaDonBanVeTheoMa(rs.getString("maHoaDon"));
                    dsHoaDon.add(hoaDonBanVe);
                }
                if(rs.getString("loaiHoaDon").equals("HOADONHUYVE")){
                    HoaDonHuyVe hoaDonHuyVe = HoaDonHuyVe_DAO.getHoaDonHuyVeTheoMa(rs.getString("maHoaDon"));
                    dsHoaDon.add(hoaDonHuyVe);
                }
                if(rs.getString("loaiHoaDon").equals("HOADONDOIVE")){
                    HoaDonDoiVe hoaDonDoiVe = HoaDonDoiVe_DAO.getHoaDonDoiVeTheoMa(rs.getString("maHoaDon"));
                    dsHoaDon.add(hoaDonDoiVe);
                }
                if(rs.getString("loaiHoaDon").equals("HOADONHUYDATVE")){
                    HoaDonHuyDatVe hoaDonHuyDatVe = HoaDonHuyDatVe_DAO.getHoaDonHuyDatVeTheoMa(rs.getString("maHoaDon"));
                    dsHoaDon.add(hoaDonHuyDatVe);
                }
                if(rs.getString("loaiHoaDon").equals("HOADONLAYVE")){
                    HoaDonLayVe hoaDonLayVe = HoaDonLayVe_DAO.getHoaDonLayVeTheoMa(rs.getString("maHoaDon"));
                    dsHoaDon.add(hoaDonLayVe);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void hienThiDanhSachHoaDon(){
        colMaHoaDon.setCellValueFactory(cellData -> {
            Object hoaDon = cellData.getValue();
            if (hoaDon instanceof HoaDonDatVe) {
                return new SimpleStringProperty(((HoaDonDatVe) hoaDon).getMaHoaDonDatVe());
            } else if (hoaDon instanceof HoaDonBanVe) {
                return new SimpleStringProperty(((HoaDonBanVe) hoaDon).getMaHoaDonBanVe());
            } else if (hoaDon instanceof HoaDonHuyVe) {
                return new SimpleStringProperty(((HoaDonHuyVe) hoaDon).getMaHoaDonHuyVe());
            } else if (hoaDon instanceof HoaDonDoiVe) {
                return new SimpleStringProperty(((HoaDonDoiVe) hoaDon).getMaHoaDonDoiVe());
            } else if (hoaDon instanceof HoaDonHuyDatVe) {
                return new SimpleStringProperty(((HoaDonHuyDatVe) hoaDon).getMaHoaDonHuyDatVe());
            } else if (hoaDon instanceof HoaDonLayVe) {
                return new SimpleStringProperty(((HoaDonLayVe) hoaDon).getMaHoaDonLayVe());
            }
            return new SimpleStringProperty("");
        });
        colMaCaLam.setCellValueFactory(cellData -> {
            Object hoaDon = cellData.getValue();
            if (hoaDon instanceof HoaDonDatVe) {
                return new SimpleStringProperty(((HoaDonDatVe) hoaDon).getCaLamViec().getMaCaLamViec());
            } else if (hoaDon instanceof HoaDonBanVe) {
                return new SimpleStringProperty(((HoaDonBanVe) hoaDon).getCaLamViec().getMaCaLamViec());
            } else if (hoaDon instanceof HoaDonHuyVe) {
                return new SimpleStringProperty(((HoaDonHuyVe) hoaDon).getCaLamViec().getMaCaLamViec());
            } else if (hoaDon instanceof HoaDonDoiVe) {
                return new SimpleStringProperty(((HoaDonDoiVe) hoaDon).getCaLamViec().getMaCaLamViec());
            } else if (hoaDon instanceof HoaDonHuyDatVe) {
                return new SimpleStringProperty(((HoaDonHuyDatVe) hoaDon).getCaLamViec().getMaCaLamViec());
            } else if (hoaDon instanceof HoaDonLayVe) {
                return new SimpleStringProperty(((HoaDonLayVe) hoaDon).getCaLamViec().getMaCaLamViec());
            }
            return new SimpleStringProperty("");
        });

        colThoiGianLap.setCellValueFactory(cellData -> {
            Object hoaDon = cellData.getValue();
            if (hoaDon instanceof HoaDonDatVe) {
                return new SimpleStringProperty(TimeFormat.formatLocalDateTime(((HoaDonDatVe) hoaDon).getThoiGianLap()));
            } else if (hoaDon instanceof HoaDonBanVe) {
                return new SimpleStringProperty(TimeFormat.formatLocalDateTime(((HoaDonBanVe) hoaDon).getThoiGianLap()));
            } else if (hoaDon instanceof HoaDonHuyVe) {
                return new SimpleStringProperty(TimeFormat.formatLocalDateTime(((HoaDonHuyVe) hoaDon).getThoiGianHuyVe()));
            } else if (hoaDon instanceof HoaDonDoiVe) {
                return new SimpleStringProperty(TimeFormat.formatLocalDateTime(((HoaDonDoiVe) hoaDon).getThoiGianDoiVe()));
            } else if (hoaDon instanceof HoaDonHuyDatVe) {
                return new SimpleStringProperty(TimeFormat.formatLocalDateTime(((HoaDonHuyDatVe) hoaDon).getThoiGianHuy()));
            } else if (hoaDon instanceof HoaDonLayVe) {
                return new SimpleStringProperty(TimeFormat.formatLocalDateTime(((HoaDonLayVe) hoaDon).getThoiGianLayVe()));
            }
            return new SimpleStringProperty("");
        });
       colTongTienCuoi.setCellValueFactory(cellData -> {
           Object hoaDon = cellData.getValue();
           if (hoaDon instanceof HoaDonDatVe) {
               return new SimpleStringProperty(CurrencyFormat.currencyFormat(((HoaDonDatVe) hoaDon).tongTienCuoi()));
           } else if (hoaDon instanceof HoaDonBanVe) {
                return new SimpleStringProperty(CurrencyFormat.currencyFormat(((HoaDonBanVe) hoaDon).tongTienCuoi()));
           } else if (hoaDon instanceof HoaDonHuyVe) {
                return new SimpleStringProperty(CurrencyFormat.currencyFormat(((HoaDonHuyVe) hoaDon).tongTienCuoi()));
              } else if (hoaDon instanceof HoaDonDoiVe) {
                return new SimpleStringProperty(CurrencyFormat.currencyFormat(((HoaDonDoiVe) hoaDon).tongTienCuoi()));
              } else if (hoaDon instanceof HoaDonHuyDatVe) {
                return new SimpleStringProperty(CurrencyFormat.currencyFormat(((HoaDonHuyDatVe) hoaDon).tongTienCuoi()));
              } else if (hoaDon instanceof HoaDonLayVe) {
                return new SimpleStringProperty(CurrencyFormat.currencyFormat(((HoaDonLayVe) hoaDon).tongTienCuoi()));
                }
           return new SimpleStringProperty("");
         });
        colMaKhachHang.setCellValueFactory(cellData -> {
            Object hoaDon = cellData.getValue();
            if (hoaDon instanceof HoaDonDatVe) {
                return new SimpleStringProperty(((HoaDonDatVe) hoaDon).getKhachHangDatVe().getMaKhachHang());
            } else if (hoaDon instanceof HoaDonBanVe) {
                return new SimpleStringProperty(((HoaDonBanVe) hoaDon).getKhachHangMuaVe().getMaKhachHang());
            } else if (hoaDon instanceof HoaDonHuyVe) {
                return new SimpleStringProperty(((HoaDonHuyVe) hoaDon).getKhachHangHuyVe().getMaKhachHang());
            } else if (hoaDon instanceof HoaDonDoiVe) {
                return new SimpleStringProperty(((HoaDonDoiVe) hoaDon).getVeCu().getDanhSachChiTietVe().get(0).getKhachHang().getMaKhachHang());
            } else if (hoaDon instanceof HoaDonHuyDatVe) {
                return new SimpleStringProperty(((HoaDonHuyDatVe) hoaDon).getKhachHangHuyDatVe().getMaKhachHang());
            } else if (hoaDon instanceof HoaDonLayVe) {
                return new SimpleStringProperty(((HoaDonLayVe) hoaDon).getKhachHangLayVe().getMaKhachHang());
            }
            return new SimpleStringProperty("");
        });
        tableView.setItems(dsHoaDon);
        tableView.setOnMouseClicked(this::onTableClick);
    }
    public void onTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            selectedHoaDon = tableView.getSelectionModel().getSelectedItem();
            if (selectedHoaDon != null) {
                xuLiSuKienClick();
            }
        }
    }
//    public void hienThiDanhSachVeDat(HoaDonDatVe hoaDonDatVe) {
//        veDat_huyDatVe_controllers_list.clear();
//        vboxDanhSachVeDat.getChildren().clear();
//        if (hoaDonDatVe == null || hoaDonDatVe.getDanhSachVeDat().isEmpty()) {
//            return;
//        }
//        int length = hoaDonDatVe.getDanhSachVeDat().size();
//        for (int i = 0; i < length; i++) {
//            VeDat veDat = hoaDonDatVe.getDanhSachVeDat().get(i);
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyDatVe_GUI_Items/VeDat_HuyDatVe.fxml"));
//                Parent anchorPane = loader.load();
//                VeDat_HuyDatVe_Controller controller = loader.getController();
//                veDat_huyDatVe_controllers_list.add(controller);
//                controller.setHuyDatVe_gui_controller(this);
//                controller.setSoThuTu(i);
//                controller.setVeDat(veDat);
//                controller.khoiTao();
//                vboxDanhSachVeDat.getChildren().add(anchorPane);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
    public void xuLiSuKienClick() {

    }

}
