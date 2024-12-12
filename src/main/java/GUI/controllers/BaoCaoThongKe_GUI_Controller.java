package GUI.controllers;

import DAO.*;
import DTO.*;
import connectDB.ConnectDB;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.CurrencyFormat;

import java.lang.reflect.Array;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import static javafx.application.Application.launch;

public class BaoCaoThongKe_GUI_Controller implements Initializable {
    @FXML
    private  BarChart<String,Double> chart;
    private BaoCaoThongKe_GUI_Controller controller;

    @FXML
    private DatePicker dapNgayBatDau;
    @FXML
    private DatePicker dapNgayKetThuc;
    @FXML
    private TextField txtDoanhThu;
    @FXML
    private ComboBox cmbLoaiThoiGian;
    @FXML
    private ComboBox cmbLoc;
    @FXML
    private ComboBox cmbChonThang;
    @FXML
    private ComboBox cmbChonNam;
    @FXML
    private TextField txtSoVeBan;
    @FXML
    private TextField txtSoVeHuy;
    @FXML
    private TextField txtSoVeDoi;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
    }



    ArrayList<Double> dsHoaDonBanVe = new ArrayList<>(Collections.nCopies(31, 0.0));
    ArrayList<Double> dsHoaDonDatVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> dsHoaDonHuyVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> dsHoaDonDoiVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> dsHoaDonLayVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> dsHoaDonHuyDatVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> doanhThuTheoNgay = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> doanhThuTheoThang= new ArrayList<>(Collections.nCopies(12,0.0));
    int soVeBan = 0;
    int soVeDoi = 0;
    int soVeHuy = 0;
    int soVeBanThang = 0;
    int soVeDoiThang = 0;
    int soVeHuyThang = 0;


    public void hienThiDoanhThuNgay(int nam, int thang) {
        thongKeDoanhThuTheoNgay(nam,thang);
        XYChart.Series<String,Double> series_01= new XYChart.Series<>();
        for(int i= 1; i<=31;i++){
            series_01.getData().add(new XYChart.Data<>(String.valueOf(i),doanhThuTheoNgay.get(i-1)));
        }
        txtDoanhThu.setText(CurrencyFormat.currencyFormat(doanhThuTheoNgay.stream().mapToDouble(Double::doubleValue).sum()));
        txtSoVeBan.setText(String.valueOf(soVeBan));
        txtSoVeHuy.setText(String.valueOf(soVeHuy));
        txtSoVeDoi.setText(String.valueOf(soVeDoi));
        chart.getData().clear();
        chart.setLegendVisible(false);
        chart.getData().add(series_01);

    }

    public ArrayList<Double> thongKeDoanhThuTheoNgay(int nam, int thang) {
        dsHoaDonBanVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonDatVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonDoiVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonHuyDatVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonHuyVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonLayVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        doanhThuTheoNgay = new ArrayList<>(Collections.nCopies(31, 0.0));
        soVeDoi = 0;
        soVeBan = 0;
        soVeHuy = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
                String query = "EXEC thongKeDoanhThuTheoNgayTrongThang ?,?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, thang);
            statement.setInt(2, nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ArrayList<Ve> danhSachVe= new ArrayList<>();
                if ((rs.getString("maHoaDon")).contains("HDBV")) {
                    HoaDonBanVe hdbv = new HoaDonBanVe(rs.getString("maHoaDon"));
                    danhSachVe = Ve_DAO.xuatDanhSachVeTheoMaHoaDonBanVe(hdbv.getMaHoaDonBanVe());
                    soVeBan = soVeBan+danhSachVe.size();
                    for(Ve v:danhSachVe){
                        System.out.println(v.getMaVe());
                    }
                    hdbv.setDanhSachVe(danhSachVe);
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i+1) {
                            dsHoaDonBanVe.set(i, dsHoaDonBanVe.get(i) + hdbv.tongTienCuoi());
                        }
                        else dsHoaDonBanVe.set(i,dsHoaDonBanVe.get(i)+0);
                    }
                } else if ((rs.getString("maHoaDon")).contains("HDDV")) {
                    HoaDonDatVe hddv = new HoaDonDatVe(rs.getString("maHoaDon"));
                    ArrayList<VeDat> dsVeDat = new ArrayList<>();
                    dsVeDat = VeDat_DAO.getDanhSachVeDatTheoMaHoaDonDat(hddv.getMaHoaDonDatVe());
                    soVeBan = soVeBan+ dsVeDat.size();
                    hddv.setDanhSachVeDat(dsVeDat);
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i+1) {
                            dsHoaDonDatVe.set(i, dsHoaDonDatVe.get(i) + hddv.tongTienDatCoc());
                        }
                         else dsHoaDonDatVe.set(i,dsHoaDonDatVe.get(i)+0);
                    }
                } else if ((rs.getString("maHoaDon")).contains("HDHV")) {
                    HoaDonHuyVe hdhv = new HoaDonHuyVe(rs.getString("maHoaDon"));
                    ArrayList<ChiTietHoaDonHuyVe> dsChiTietHoaDonHuyVe= ChiTietHoaDonHuyVe_DAO.getDanhSachChiTietHoaDonHuyVeTheoMaHoaDonHuyVe(hdhv.getMaHoaDonHuyVe());
                    soVeHuy = soVeHuy+ dsChiTietHoaDonHuyVe.size();
                    hdhv.setDanhSachChiTietHoaDonHuyVe(dsChiTietHoaDonHuyVe);
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i+1) {
                            dsHoaDonHuyVe.set(i, dsHoaDonHuyVe.get(i) + hdhv.tongTienCuoi());
                        }
                         else dsHoaDonHuyVe.set(i,dsHoaDonHuyVe.get(i)+0);
                    }
                } else if ((rs.getString("maHoaDon")).contains("HDHD")) {
                    HoaDonHuyDatVe hdhd = new HoaDonHuyDatVe(rs.getString("maHoaDon"));
                    ArrayList<ChiTietHoaDonHuyDatVe> dsChiTietHoaDonHuyDat = new ArrayList<>();
                    dsChiTietHoaDonHuyDat = ChiTietHoaDonHuyDatVe_DAO.getDanhSachChiTietHoaDonHuyDatVeTheoMaHoaDonHuyDatVe(hdhd.getMaHoaDonHuyDatVe());
                    soVeHuy = soVeHuy+ dsChiTietHoaDonHuyDat.size();
                    hdhd.setDanhSachChiTietHoaDonHuyDatVe(dsChiTietHoaDonHuyDat);
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i+1) {
                            dsHoaDonHuyDatVe.set(i, dsHoaDonHuyDatVe.get(i) + hdhd.tongTienCuoi());
                        }
                        else dsHoaDonHuyDatVe.set(i,dsHoaDonHuyDatVe.get(i)+0);
                    }
                } else if ((rs.getString("maHoaDon")).contains("HDLV")) {
                    HoaDonLayVe hdlv = new HoaDonLayVe(rs.getString("maHoaDon"));
                    ArrayList<ChiTietHoaDonLayVe> dsChiTietHoaDonLayVe = ChiTietHoaDonLayVe_DAO.getDanhSachChiTietHoaDonLayVeTheoMaHoaDonLayVe(hdlv.getMaHoaDonLayVe());
                    hdlv.setDanhSachChiTietHoaDonLayVe(dsChiTietHoaDonLayVe);
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i+1) {
                            dsHoaDonLayVe.set(i, dsHoaDonLayVe.get(i) + hdlv.tongTienCuoi());
                        }
                        else dsHoaDonLayVe.set(i,dsHoaDonLayVe.get(i)+0);
                    }
                } else if ((rs.getString("maHoaDon")).contains("HDDO")) {
                    HoaDonDoiVe hddv = new HoaDonDoiVe(rs.getString("maHoaDon"));
                    ArrayList<Ve> dsVe = new ArrayList<>();
                    dsVe = Ve_DAO.getDanhSachVeTheoMaHoaDonDoi(hddv.getMaHoaDonDoiVe());
                    soVeDoi ++;
                    hddv.setVeMoi(dsVe.get(0));
                    hddv.setVeCu(dsVe.get(1));
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i + 1) {
                            dsHoaDonDoiVe.set(i, dsHoaDonDoiVe.get(i) + hddv.tongTienCuoi());
                        } else dsHoaDonDoiVe.set(i, dsHoaDonDoiVe.get(i) + 0);
                    }
                }
            }
            for (int i=0; i<=30 ; i++){
                doanhThuTheoNgay.set(i,dsHoaDonBanVe.get(i)+dsHoaDonDatVe.get(i)+dsHoaDonDoiVe.get(i)+dsHoaDonHuyVe.get(i)+dsHoaDonHuyDatVe.get(i)+dsHoaDonLayVe.get(i));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return doanhThuTheoNgay;
    }


    public ArrayList<Double> thongKeDoanhThuTheoThang(int nam) {
        doanhThuTheoThang = new ArrayList<>(Collections.nCopies(12, 0.0));
        soVeBanThang = 0;
        soVeDoiThang = 0;
        soVeHuyThang = 0;
       for (int i = 0; i <= 11; i++) {
            doanhThuTheoThang.set(i, thongKeDoanhThuTheoNgay(nam,i+1).stream().mapToDouble(Double::doubleValue).sum());
            soVeBanThang = soVeBanThang + soVeBan;
            soVeDoiThang = soVeDoiThang + soVeDoi;
            soVeHuyThang = soVeHuyThang + soVeHuy;
        }
        return doanhThuTheoThang;
    }
    public void hienThiDoanhThuThang(int nam) {
        doanhThuTheoThang = new ArrayList<>(Collections.nCopies(12, 0.0));
        thongKeDoanhThuTheoThang(nam);
        XYChart.Series<String,Double> series_01= new XYChart.Series<>();
        for(int i= 1; i<=12;i++){
            if (i==1){
                series_01.getData().add(new XYChart.Data<>("Jan",doanhThuTheoThang.get(i-1)));
            }
            else if (i==2){
                series_01.getData().add(new XYChart.Data<>("Feb",doanhThuTheoThang.get(i-1)));
            }
            else if (i==3){
                series_01.getData().add(new XYChart.Data<>("Mar",doanhThuTheoThang.get(i-1)));
            }
            else if (i==4){
                series_01.getData().add(new XYChart.Data<>("Apr",doanhThuTheoThang.get(i-1)));
            }
            else if (i==5){
                series_01.getData().add(new XYChart.Data<>("May",doanhThuTheoThang.get(i-1)));
            }
            else if (i==6){
                series_01.getData().add(new XYChart.Data<>("Jun",doanhThuTheoThang.get(i-1)));
            }
            else if (i==7){
                series_01.getData().add(new XYChart.Data<>("Jul",doanhThuTheoThang.get(i-1)));
            }
            else if (i==8){
                series_01.getData().add(new XYChart.Data<>("Aug",doanhThuTheoThang.get(i-1)));
            }
            else if (i==9){
                series_01.getData().add(new XYChart.Data<>("Sep",doanhThuTheoThang.get(i-1)));
            }
            else if (i==10){
                series_01.getData().add(new XYChart.Data<>("Oct",doanhThuTheoThang.get(i-1)));
            }
            else if (i==11){
                series_01.getData().add(new XYChart.Data<>("Nov",doanhThuTheoThang.get(i-1)));
            }
            else if (i==12){
                series_01.getData().add(new XYChart.Data<>("Dec",doanhThuTheoThang.get(i-1)));
            }
        }
        for (int i=0; i<=11 ; i++){
            System.out.println(doanhThuTheoThang.get(i));
        }
        txtDoanhThu.setText(CurrencyFormat.currencyFormat(doanhThuTheoThang.stream().mapToDouble(Double::doubleValue).sum()));
        txtSoVeBan.setText(String.valueOf(soVeBanThang));
        txtSoVeHuy.setText(String.valueOf(soVeHuyThang));
        txtSoVeDoi.setText(String.valueOf(soVeDoiThang));
        chart.getData().clear();
        chart.setLegendVisible(false);
        chart.getData().add(series_01);
    }

    public void initializeComboBoxes(){
        ObservableList<String> loaiThoiGian = FXCollections.observableArrayList("Ngày","Tháng");
        cmbLoaiThoiGian.setItems(loaiThoiGian);
        ObservableList<String> thang = FXCollections.observableArrayList("Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5","Tháng 6","Tháng 7","Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12");
        cmbChonThang.setItems(thang);
        ObservableList<String> nam = FXCollections.observableArrayList("2021","2022","2023","2024");
        cmbChonNam.setItems(nam);
        cmbLoaiThoiGian.setValue("Ngày");
        cmbChonThang.setValue("Tháng 4");
        cmbChonNam.setValue("2024");
    }
    public void btnThongKeOnAction(ActionEvent actionEvent) {
        if(cmbLoaiThoiGian.getValue().equals("Tháng")){
            int nam = Integer.parseInt(cmbChonNam.getSelectionModel().getSelectedItem().toString());
            hienThiDoanhThuThang(nam);

        }
        else if(cmbLoaiThoiGian.getValue().equals("Ngày")){
                int nam = Integer.parseInt(cmbChonNam.getSelectionModel().getSelectedItem().toString());
                int thang = cmbChonThang.getSelectionModel().getSelectedIndex() + 1;
                hienThiDoanhThuNgay(nam,thang);
        }
    }

//    public void setMain_Controller(Main_Controller mainController) {
//    }
}
