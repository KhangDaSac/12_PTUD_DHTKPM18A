package GUI.controllers;

import DAO.*;
import DTO.*;
import GUI.controllers.QuanLyHoaDon_GUI_Items.VeDat_QuanLyHoaDon_Controller;
import GUI.controllers.QuanLyHoaDon_GUI_Items.Ve_QuanLyHoaDon_Controller;
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
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import utils.CreatePDF;
import utils.CurrencyFormat;
import utils.ShowMessagesDialog;
import utils.TimeFormat;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
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
    private VBox vboxDanhSachVe;

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    private Main_Controller main_controller;
    @FXML
    void btnBoChonTatCaOnAction(ActionEvent event) {
        if (selectedHoaDon instanceof HoaDonDatVe){
            veDatChon_list.clear();
            hienThiDanhSachVeDatHoaDonDatVe((HoaDonDatVe) selectedHoaDon);
        }
        if (selectedHoaDon instanceof HoaDonBanVe){
            veChon_list.clear();
            hienThiDanhSachVeHoaDonBanVe((HoaDonBanVe) selectedHoaDon);
        }
        if (selectedHoaDon instanceof HoaDonLayVe){
            veChon_list.clear();
            hienThiDanhSachHoaDonLayVe((HoaDonLayVe) selectedHoaDon);
        }
        if (selectedHoaDon instanceof HoaDonHuyVe){
            veChon_list.clear();
            hienThiDanhSachVeHoaDonHuyVe((HoaDonHuyVe) selectedHoaDon);
        }
        if (selectedHoaDon instanceof HoaDonDoiVe){
            veChon_list.clear();
            hienThiDanhSachVeHoaDonDoiVe((HoaDonDoiVe) selectedHoaDon);
        }
        if (selectedHoaDon instanceof HoaDonHuyDatVe){
            veDatChon_list.clear();
            hienThiDanhSachVeHoaDonHuyDatVe((HoaDonHuyDatVe) selectedHoaDon);
        }

    }

    @FXML
    void btnChonTatCaOnACtion(ActionEvent event) {
      if (selectedHoaDon instanceof HoaDonDatVe){
          veDatChon_list.clear();
          veDatChon_list.addAll(((HoaDonDatVe) selectedHoaDon).getDanhSachVeDat());
          hienThiDanhSachVeDatHoaDonDatVe((HoaDonDatVe) selectedHoaDon);
      }
        if (selectedHoaDon instanceof HoaDonBanVe){
            veChon_list.clear();
            veChon_list.addAll(((HoaDonBanVe) selectedHoaDon).getDanhSachVe());
            hienThiDanhSachVeHoaDonBanVe((HoaDonBanVe) selectedHoaDon);
        }
        if (selectedHoaDon instanceof HoaDonLayVe){
            veChon_list.clear();
            for(ChiTietHoaDonLayVe chiTietHoaDonLayVe : ((HoaDonLayVe) selectedHoaDon).getDanhSachChiTietHoaDonLayVe()){
                veChon_list.add(chiTietHoaDonLayVe.getVe());
            }
            hienThiDanhSachHoaDonLayVe((HoaDonLayVe) selectedHoaDon);
        }
        if (selectedHoaDon instanceof HoaDonHuyVe){
            veChon_list.clear();
            for(ChiTietHoaDonHuyVe chiTietHoaDonHuyVe : ((HoaDonHuyVe) selectedHoaDon).getDanhSachChiTietHoaDonHuyVe()){
                veChon_list.add(chiTietHoaDonHuyVe.getVe());
            }
            hienThiDanhSachVeHoaDonHuyVe((HoaDonHuyVe) selectedHoaDon);
        }
        if (selectedHoaDon instanceof HoaDonDoiVe){
            veChon_list.clear();
            veChon_list.add(((HoaDonDoiVe) selectedHoaDon).getVeMoi());
            veChon_list.add(((HoaDonDoiVe) selectedHoaDon).getVeCu());
            hienThiDanhSachVeHoaDonDoiVe((HoaDonDoiVe) selectedHoaDon);
        }
        if (selectedHoaDon instanceof HoaDonHuyDatVe){
            veDatChon_list.clear();
            for(ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe : ((HoaDonHuyDatVe) selectedHoaDon).getDanhSachChiTietHoaDonHuyDatVe()){
                veDatChon_list.add(chiTietHoaDonHuyDatVe.getVeDat());
            }
            hienThiDanhSachVeHoaDonHuyDatVe((HoaDonHuyDatVe) selectedHoaDon);
        }

    }

    @FXML
    void btnInHoaDonOnAcTion(ActionEvent event) {

        if(selectedHoaDon == null ){
            main_controller.showMessagesDialog("Bạn chưa chọn hóa đơn nào");
        }
        else {
            if (selectedHoaDon instanceof HoaDonDatVe){
                CreatePDF.taoHoaDonDatVe((HoaDonDatVe) selectedHoaDon);
            }
//            if (selectedHoaDon instanceof HoaDonBanVe){
//                CreatePDF.taoHoaDonBanVe((HoaDonBanVe) selectedHoaDon);
//            }
            if (selectedHoaDon instanceof HoaDonHuyVe){
                CreatePDF.taoHoaDonHuyVe((HoaDonHuyVe) selectedHoaDon);
            }
            if (selectedHoaDon instanceof HoaDonDoiVe){
                CreatePDF.taoHoaDonDoiVe((HoaDonDoiVe) selectedHoaDon,((HoaDonDoiVe) selectedHoaDon).getVeCu().getDanhSachChiTietVe().get(0));
            }
            if (selectedHoaDon instanceof HoaDonHuyDatVe){
                CreatePDF.taoHoaDonHuyDatVe((HoaDonHuyDatVe) selectedHoaDon);
            }
        }
    }

    @FXML
    void btnInVeOnAction(ActionEvent event) {
        if(veChon_list.isEmpty()&&veDatChon_list.isEmpty()){
            main_controller.showMessagesDialog("Bạn chưa chọn vé nào");
            return;
        } else if (veChon_list.isEmpty()&&!veDatChon_list.isEmpty()) {
            main_controller.showMessagesDialog("Vé đặt không được in");
        }else {
            for (Ve ve : veChon_list) {
                if(ve.getTrangThaiVe().equals(TrangThaiVe.DAHUY)){
                    main_controller.showMessagesDialog("Vé đã hủy không được in");
                }
                else {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle("Chọn thư mục lưu file");
                    File selectedDirectory = directoryChooser.showDialog(txtCCCD.getScene().getWindow());

                    CreatePDF.taoVe(ve, selectedDirectory.getAbsolutePath());
                }

            }
        }
    }

    @FXML
    void btnTimHoaDonOnAction(ActionEvent event) {
        String maHoaDon = txtMaHoaDon.getText();
        String loaiHoaDon = cmbLoaiHoaDon.getValue();
        if (loaiHoaDon == null) {
            loaiHoaDon = "";
        }
        String maCaLam = txtMaCaLam.getText();
        LocalDate thoiGianLap = dapThoiGianLap.getValue();
        String CCCD = txtCCCD.getText();
        timHoaDonTheoCacTieuChi(maHoaDon, loaiHoaDon, maCaLam, thoiGianLap, CCCD);
        if (dsHoaDon.isEmpty()){
            main_controller.showMessagesDialog("Không tìm thấy hóa đơn");
            get10HoaDonGanNhat();
        }
        tableView.setItems(dsHoaDon);
        hienThiDanhSachHoaDon();
    }
    private ArrayList<VeDat> danhSachDatVe = new ArrayList<>();
    private ArrayList<HoaDonDatVe> danhSachHoaDonDatVe = new ArrayList<>();
    private ArrayList<VeDat_QuanLyHoaDon_Controller> veDat_quanLyHoaDon_controllers_list = new ArrayList<>();
    private ObservableList<Object> dsHoaDon = FXCollections.observableArrayList();
    private Object selectedHoaDon;
    private ArrayList<VeDat> veDatChon_list = new ArrayList<VeDat>();
    private ArrayList<Ve_QuanLyHoaDon_Controller> ve_quanLyHoaDon_controllers_list = new ArrayList<>();
    private ArrayList<Ve> veChon_list = new ArrayList<Ve>();

    public ArrayList<Ve> getVeChon_list() {
        return veChon_list;
    }

    public void setVeChon_list(ArrayList<Ve> veChon_list) {
        this.veChon_list = veChon_list;
    }

    public ArrayList<Ve_QuanLyHoaDon_Controller> getVe_quanLyHoaDon_controllers_list() {
        return ve_quanLyHoaDon_controllers_list;
    }

    public void setVe_quanLyHoaDon_controllers_list(ArrayList<Ve_QuanLyHoaDon_Controller> ve_quanLyHoaDon_controllers_list) {
        this.ve_quanLyHoaDon_controllers_list = ve_quanLyHoaDon_controllers_list;
    }

    public QuanLyHoaDon_GUI_Controller getQuanLyHoaDon_gui_controller() {
        return quanLyHoaDon_gui_controller;
    }
    public ArrayList<VeDat> getVeDatChon_list() {
        return veDatChon_list;
    }

    public void setVeDatChon_list(ArrayList<VeDat> veDatChon_list) {
        this.veDatChon_list = veDatChon_list;
    }
    public void setQuanLyHoaDon_gui_controller(QuanLyHoaDon_GUI_Controller quanLyHoaDon_gui_controller) {
        this.quanLyHoaDon_gui_controller = quanLyHoaDon_gui_controller;
    }

    private QuanLyHoaDon_GUI_Controller quanLyHoaDon_gui_controller;
    private HoaDonDatVe hoaDonDatVeDangChon;

    public HoaDonBanVe getHoaDonBanVeDangChon() {
        return hoaDonBanVeDangChon;
    }

    public void setHoaDonBanVeDangChon(HoaDonBanVe hoaDonBanVeDangChon) {
        this.hoaDonBanVeDangChon = hoaDonBanVeDangChon;
    }

    private HoaDonBanVe hoaDonBanVeDangChon;
    private HoaDonLayVe hoaDonLayVeDangChon;
    private HoaDonHuyVe hoaDonHuyVeDangChon;
    private HoaDonHuyDatVe hoaDonHuyDatVeDangChon;
    private HoaDonDoiVe hoaDonDoiVeDangChon;

    public HoaDonDoiVe getHoaDonDoiVeDangChon() {
        return hoaDonDoiVeDangChon;
    }

    public void setHoaDonDoiVeDangChon(HoaDonDoiVe hoaDonDoiVeDangChon) {
        this.hoaDonDoiVeDangChon = hoaDonDoiVeDangChon;
    }

    public HoaDonHuyDatVe getHoaDonHuyDatVeDangChon() {
        return hoaDonHuyDatVeDangChon;
    }

    public void setHoaDonHuyDatVeDangChon(HoaDonHuyDatVe hoaDonHuyDatVeDangChon) {
        this.hoaDonHuyDatVeDangChon = hoaDonHuyDatVeDangChon;
    }

    public HoaDonLayVe getHoaDonLayVeDangChon() {
        return hoaDonLayVeDangChon;
    }

    public void setHoaDonLayVeDangChon(HoaDonLayVe hoaDonLayVeDangChon) {
        this.hoaDonLayVeDangChon = hoaDonLayVeDangChon;
    }

    public HoaDonHuyVe getHoaDonHuyVeDangChon() {
        return hoaDonHuyVeDangChon;
    }

    public void setHoaDonHuyVeDangChon(HoaDonHuyVe hoaDonHuyVeDangChon) {
        this.hoaDonHuyVeDangChon = hoaDonHuyVeDangChon;
    }

    public HoaDonDatVe getHoaDonDatVeDangChon() {
        return hoaDonDatVeDangChon;
    }

    public void setHoaDonDatVeDangChon(HoaDonDatVe hoaDonDatVeDangChon) {
        this.hoaDonDatVeDangChon = hoaDonDatVeDangChon;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initableComboBox();
        get10HoaDonGanNhat();
       hienThiDanhSachHoaDon();
    }
    public void initableComboBox() {
        ObservableList<String> loaiHoaDon = FXCollections.observableArrayList("Hóa đơn đặt vé", "Hóa đơn bán vé", "Hóa đơn hủy vé","Hóa đơn đổi vé","Hóa đơn hủy đặt vé","Hóa đơn lấy vé");
        cmbLoaiHoaDon.setItems(loaiHoaDon);
    }
    public void timHoaDonTheoCacTieuChi(String maHoaDon, String loaiHoaDon, String maCaLam, LocalDate thoiGianLap, String CCCD){
        dsHoaDon.clear();
        Connection con = ConnectDB.getInstance().getConnection();
        String query ="exec timHoaDonTheoCacTieuChi ?,?,?,?,?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            statement.setString(2,CCCD);
            if (thoiGianLap != null) {
                statement.setDate(3, Date.valueOf(thoiGianLap));
            } else {
                statement.setNull(3, Types.DATE);
            }
            String loaiHD="" ;
            if(loaiHoaDon.equals("Hóa đơn đặt vé")){
                loaiHD = "HOADONDATVE";
            } else if(loaiHoaDon.equals("Hóa đơn bán vé")){
                loaiHD = "HOADONBANVE";
            } else if(loaiHoaDon.equals("Hóa đơn hủy vé")){
                loaiHD = "HOADONHUYVE";
            } else if(loaiHoaDon.equals("Hóa đơn đổi vé")){
                loaiHD = "HOADONDOIVE";
            } else if(loaiHoaDon.equals("Hóa đơn hủy đặt vé")){
                loaiHD = "HOADONHUYDATVE";
            } else {
                loaiHD = "HOADONLAYVE";
            }
            statement.setString(4,loaiHD);
            statement.setString(5,maCaLam);
            ResultSet rs = statement.executeQuery();
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
    public void get10HoaDonGanNhat(){
        Connection con = ConnectDB.getInstance().getConnection();
        String query ="exec tim10HoaDonGanNhat";
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
    public void hienThiDanhSachVeDatHoaDonDatVe(HoaDonDatVe hoaDonDatVe) {
        veDat_quanLyHoaDon_controllers_list.clear();
        vboxDanhSachVe.getChildren().clear();
        if (hoaDonDatVe == null || hoaDonDatVe.getDanhSachVeDat().isEmpty()) {
            return;
        }
        int length = hoaDonDatVe.getDanhSachVeDat().size();
        for (int i = 0; i < length; i++) {
            VeDat veDat = hoaDonDatVe.getDanhSachVeDat().get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/VeDat_QuanLyHoaDon.fxml"));
                Parent anchorPane = loader.load();
                VeDat_QuanLyHoaDon_Controller controller = loader.getController();
                veDat_quanLyHoaDon_controllers_list.add(controller);
                controller.setQuanLyHoaDon_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setVeDat(veDat);
                controller.khoiTaoQuanLiHoaDon();
                vboxDanhSachVe.getChildren().add(anchorPane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void hienThiDanhSachVeHoaDonBanVe(HoaDonBanVe hoaDonBanVe) {
        ve_quanLyHoaDon_controllers_list.clear();
        vboxDanhSachVe.getChildren().clear();
        if (hoaDonBanVe == null || hoaDonBanVe.getDanhSachVe().isEmpty()) {
            return;
        }
        int length = hoaDonBanVe.getDanhSachVe().size();
        for (int i = 0; i < length; i++) {
            Ve ve = hoaDonBanVe.getDanhSachVe().get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/Ve_QuanLyHoaDon.fxml"));
                Parent anchorPane = loader.load();
                Ve_QuanLyHoaDon_Controller controller = loader.getController();
                ve_quanLyHoaDon_controllers_list.add(controller);
                controller.setQuanLyHoaDon_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setVe(ve);
                controller.khoiTaoVeCuaHoaDonBanVe();
                vboxDanhSachVe.getChildren().add(anchorPane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        for (Ve ve : veChon_list){
            System.out.println("thong tin ve "+ve.getMaVe());
        }
    }
    public void hienThiDanhSachHoaDonLayVe(HoaDonLayVe hoaDonLayVe){
        ve_quanLyHoaDon_controllers_list.clear();
        vboxDanhSachVe.getChildren().clear();
        if (hoaDonLayVe == null || hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().isEmpty()) {
            return;
        }
        int length = hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().size();
        for (int i = 0; i < length; i++) {
            ChiTietHoaDonLayVe chiTietHoaDonLayVe = hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/Ve_QuanLyHoaDon.fxml"));
                Parent anchorPane = loader.load();
                Ve_QuanLyHoaDon_Controller controller = loader.getController();
                ve_quanLyHoaDon_controllers_list.add(controller);
                controller.setQuanLyHoaDon_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setChiTietHoaDonLayVe(chiTietHoaDonLayVe);
                controller.khoiTaoVeCuaHoaDonLayVe();
                vboxDanhSachVe.getChildren().add(anchorPane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void hienThiDanhSachVeHoaDonHuyVe(HoaDonHuyVe hoaDonHuyVe){
        ve_quanLyHoaDon_controllers_list.clear();
        vboxDanhSachVe.getChildren().clear();
        if (hoaDonHuyVe == null || hoaDonHuyVe.getDanhSachChiTietHoaDonHuyVe().isEmpty()) {
            return;
        }
        int length = hoaDonHuyVe.getDanhSachChiTietHoaDonHuyVe().size();
        for (int i = 0; i < length; i++) {

            ChiTietHoaDonHuyVe chiTietHoaDonHuyVe = hoaDonHuyVe.getDanhSachChiTietHoaDonHuyVe().get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/Ve_QuanLyHoaDon.fxml"));
                Parent anchorPane = loader.load();
                Ve_QuanLyHoaDon_Controller controller = loader.getController();
                ve_quanLyHoaDon_controllers_list.add(controller);
                controller.setQuanLyHoaDon_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setChiTietHoaDonHuyVe(chiTietHoaDonHuyVe);
                controller.khoiTaoVeCuaHoaDonHuyVe();
                vboxDanhSachVe.getChildren().add(anchorPane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void hienThiDanhSachVeHoaDonHuyDatVe(HoaDonHuyDatVe hoaDonHuyDatVe){
        veDat_quanLyHoaDon_controllers_list.clear();
        vboxDanhSachVe.getChildren().clear();
        if (hoaDonHuyDatVe == null || hoaDonHuyDatVe.getDanhSachChiTietHoaDonHuyDatVe().isEmpty()) {
            return;
        }
        int length = hoaDonHuyDatVe.getDanhSachChiTietHoaDonHuyDatVe().size();
        for (int i = 0; i < length; i++) {
            ChiTietHoaDonHuyDatVe chiTietHoaDonHuyDatVe = hoaDonHuyDatVe.getDanhSachChiTietHoaDonHuyDatVe().get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/VeDat_QuanLyHoaDon.fxml"));
                Parent anchorPane = loader.load();
                VeDat_QuanLyHoaDon_Controller controller = loader.getController();
                veDat_quanLyHoaDon_controllers_list.add(controller);
                controller.setQuanLyHoaDon_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setChiTietHoaDonHuyDatVe(chiTietHoaDonHuyDatVe);
                controller.khoiTaoVeDatHoaDonHuyDatVe();
                vboxDanhSachVe.getChildren().add(anchorPane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void hienThiDanhSachVeHoaDonDoiVe(HoaDonDoiVe hoaDonDoiVe){
        ve_quanLyHoaDon_controllers_list.clear();
        vboxDanhSachVe.getChildren().clear();
        if (hoaDonDoiVe == null ||hoaDonDoiVe.getVeMoi().getDanhSachChiTietVe().isEmpty()) {
            return;
        }
       Ve veMoi = hoaDonDoiVe.getVeMoi();
       Ve veCu = hoaDonDoiVe.getVeCu();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/Ve_QuanLyHoaDon.fxml"));
                Parent anchorPane = loader.load();
                Ve_QuanLyHoaDon_Controller controller = loader.getController();
                ve_quanLyHoaDon_controllers_list.add(controller);
                controller.setQuanLyHoaDon_gui_controller(this);
                controller.setSoThuTu(1);
                controller.setVe(veMoi);
                controller.khoiTaoVeCuaHoaDonDoi();
                vboxDanhSachVe.getChildren().add(anchorPane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI_Items/Ve_QuanLyHoaDon.fxml"));
            Parent anchorPane = loader.load();
            Ve_QuanLyHoaDon_Controller controller = loader.getController();
            ve_quanLyHoaDon_controllers_list.add(controller);
            controller.setQuanLyHoaDon_gui_controller(this);
            controller.setSoThuTu(1);
            controller.setVe(veCu);
            controller.khoiTaoVeCuaHoaDonDoi();
            vboxDanhSachVe.getChildren().add(anchorPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }

    public void xuLiSuKienClick() {
        veDatChon_list.clear();
        veChon_list.clear();
        if (selectedHoaDon instanceof HoaDonDatVe) {
            hoaDonHuyVeDangChon = null;
            hoaDonHuyDatVeDangChon = null;
            hoaDonDoiVeDangChon = null;
            hoaDonLayVeDangChon = null;
            hoaDonBanVeDangChon = null;
            HoaDonDatVe hoaDonDatVe = (HoaDonDatVe) selectedHoaDon;
            hoaDonDatVeDangChon = hoaDonDatVe;
            hienThiDanhSachVeDatHoaDonDatVe(hoaDonDatVeDangChon);
        }
        if (selectedHoaDon instanceof HoaDonBanVe) {
            hoaDonDatVeDangChon = null;
            hoaDonHuyVeDangChon = null;
            hoaDonHuyDatVeDangChon = null;
            hoaDonDoiVeDangChon = null;
            hoaDonLayVeDangChon = null;
            HoaDonBanVe hoaDonBanVe = (HoaDonBanVe) selectedHoaDon;
            hoaDonBanVeDangChon = hoaDonBanVe;
            hienThiDanhSachVeHoaDonBanVe(hoaDonBanVeDangChon);
        }
        if (selectedHoaDon instanceof HoaDonLayVe) {
            hoaDonDatVeDangChon = null;
            hoaDonHuyVeDangChon = null;
            hoaDonHuyDatVeDangChon = null;
            hoaDonDoiVeDangChon = null;
            hoaDonBanVeDangChon = null;
            HoaDonLayVe hoaDonLayVe = (HoaDonLayVe) selectedHoaDon;
            hoaDonLayVeDangChon = hoaDonLayVe;
            hienThiDanhSachHoaDonLayVe(hoaDonLayVe);
        }
        if (selectedHoaDon instanceof HoaDonHuyVe) {
            hoaDonDatVeDangChon = null;
            hoaDonBanVeDangChon = null;
            hoaDonHuyDatVeDangChon = null;
            hoaDonDoiVeDangChon = null;
            hoaDonLayVeDangChon = null;
            HoaDonHuyVe hoaDonHuyVe = (HoaDonHuyVe) selectedHoaDon;
            hoaDonHuyVeDangChon = hoaDonHuyVe;
            hienThiDanhSachVeHoaDonHuyVe(hoaDonHuyVe);
        }
        if (selectedHoaDon instanceof HoaDonHuyDatVe) {
            hoaDonDatVeDangChon = null;
            hoaDonBanVeDangChon = null;
            hoaDonHuyVeDangChon = null;
            hoaDonDoiVeDangChon = null;
            hoaDonLayVeDangChon = null;
            HoaDonHuyDatVe hoaDonHuyDatVe = (HoaDonHuyDatVe) selectedHoaDon;
            hoaDonHuyDatVeDangChon = hoaDonHuyDatVe;
            hienThiDanhSachVeHoaDonHuyDatVe(hoaDonHuyDatVe);
        }
        if (selectedHoaDon instanceof HoaDonDoiVe) {
            hoaDonDatVeDangChon = null;
            hoaDonBanVeDangChon = null;
            hoaDonHuyVeDangChon = null;
            hoaDonHuyDatVeDangChon = null;
            hoaDonLayVeDangChon = null;
            HoaDonDoiVe hoaDonDoiVe = (HoaDonDoiVe) selectedHoaDon;
            hoaDonDoiVeDangChon = hoaDonDoiVe;
            hienThiDanhSachVeHoaDonDoiVe(hoaDonDoiVe);
        }
    }


}
