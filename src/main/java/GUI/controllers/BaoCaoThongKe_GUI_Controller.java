package GUI.controllers;

import DAO.Ve_DAO;
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
    public ArrayList<Double> thongKeDoanhThuTheoThang(int nam) {
        for (int i = 0; i <= 11; i++) {
            doanhThuTheoThang.set(i, thongKeDoanhThuTheoNgay(nam,i+1).stream().mapToDouble(Double::doubleValue).sum());
        }
        return doanhThuTheoThang;
    }
    public void hienThiDoanhThuNgay(int nam, int thang) {
        dsHoaDonBanVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonDatVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonDoiVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonHuyDatVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonHuyVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        dsHoaDonLayVe = new ArrayList<>(Collections.nCopies(31, 0.0));
        doanhThuTheoNgay = new ArrayList<>(Collections.nCopies(31, 0.0));
        thongKeDoanhThuTheoNgay(nam,thang);
        XYChart.Series<String,Double> series_01= new XYChart.Series<>();
        for(int i= 1; i<=31;i++){
            series_01.getData().add(new XYChart.Data<>(String.valueOf(i),doanhThuTheoNgay.get(i-1)));
        }
        chart.getData().clear();
        chart.setLegendVisible(false);
        chart.getData().add(series_01);

    }
//            while (rs.next()) {
//                String monthName = switch (rs.getInt("thang")) {
//                    case 1 -> "Jan";
//                    case 2 -> "Feb";
//                    case 3 -> "Mar";
//                    case 4 -> "Apr";
//                    case 5 -> "May";
//                    case 6 -> "Jun";
//                    case 7 -> "Jul";
//                    case 8 -> "Aug";
//                    case 9 -> "Sep";
//                    case 10 -> "Oct";
//                    case 11 -> "Nov";
//                    case 12 -> "Dec";
//                    default -> null;
//                };
//                if (monthName != null) {
//                    series_01.getData().removeIf(data -> data.getXValue().equals(monthName));
//                    series_01.getData().add(new XYChart.Data<>(monthName, rs.getDouble("monthly_revenue")));
//
//                    tongDoanhThu += rs.getDouble("monthly_revenue");
//                    soVeDat += rs.getInt("soVeBanDuoc");
//                    soVeHuy += rs.getInt("soVeHuy");
//                    soVeDoi += rs.getInt("soVeDoi");
//                }
//            }
//            chart.getData().clear();
//            chart.setLegendVisible(false);
//            chart.getData().add(series_01);
//
//            txtDoanhThu.setText(String.format("%.2fM", tongDoanhThu / 1_000_000));
//            txtSoVeBan.setText(String.valueOf(soVeDat));
//            txtSoVeHuy.setText(String.valueOf(soVeHuy));
//            txtSoVeDoi.setText(String.valueOf(soVeDoi));
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public ArrayList<Double> thongKeDoanhThuTheoNgay(int nam, int thang) {
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
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i+1) {
                            dsHoaDonDatVe.set(i, dsHoaDonDatVe.get(i) + hddv.tongTienDatCoc());
                        }
                         else dsHoaDonDatVe.set(i,dsHoaDonDatVe.get(i)+0);
                    }
                } else if ((rs.getString("maHoaDon")).contains("HDHV")) {
                    HoaDonHuyVe hdhv = new HoaDonHuyVe(rs.getString("maHoaDon"));
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i+1) {
                            dsHoaDonHuyVe.set(i, dsHoaDonHuyVe.get(i) + hdhv.tongTienCuoi());
                        }
                         else dsHoaDonHuyVe.set(i,dsHoaDonHuyVe.get(i)+0);
                    }
                } else if ((rs.getString("maHoaDon")).contains("HDHD")) {
                    HoaDonHuyDatVe hdhd = new HoaDonHuyDatVe(rs.getString("maHoaDon"));
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i+1) {
                            dsHoaDonHuyDatVe.set(i, dsHoaDonHuyDatVe.get(i) + hdhd.tongTienCuoi());
                        }
                        else dsHoaDonHuyDatVe.set(i,dsHoaDonHuyDatVe.get(i)+0);
                    }
                } else if ((rs.getString("maHoaDon")).contains("HDLV")) {
                    HoaDonLayVe hdlv = new HoaDonLayVe(rs.getString("maHoaDon"));
                    for (int i = 0; i <= 30; i++) {
                        if (rs.getInt("ngay") == i+1) {
                            dsHoaDonLayVe.set(i, dsHoaDonLayVe.get(i) + hdlv.tongTienCuoi());
                        }
                        else dsHoaDonLayVe.set(i,dsHoaDonLayVe.get(i)+0);
                    }
                } else if ((rs.getString("maHoaDon")).contains("HDDO")) {
                    HoaDonDoiVe hddv = new HoaDonDoiVe(rs.getString("maHoaDon"));
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



//            XYChart.Series<String, Double> series_01 = new XYChart.Series<>();
//
//            // Khởi tạo dữ liệu mặc định cho các ngày với giá trị bằng 0
//            for (int i = 1; i <= 31; i++) {
//                series_01.getData().add(new XYChart.Data<>(String.valueOf(i), 0.0));
//            }
//
//            // Cập nhật dữ liệu từ ResultSet
//            while (rs.next()) {
//                int day = rs.getInt("ngay");
//                series_01.getData().removeIf(data -> data.getXValue().equals(String.valueOf(day)));
//                series_01.getData().add(new XYChart.Data<>(String.valueOf(day), rs.getDouble("daily_revenue")));
//                System.out.println("Ngày : " +  day + ", Doanh thu: " + rs.getDouble("daily_revenue"));
//                System.out.println("Ngày: " + day + ", Doanh thu: " + rs.getDouble("daily_revenue"));
//                tongDoanhThu += rs.getDouble("daily_revenue");
//                soVeDat += rs.getInt("soVeBanDuoc");
//                soVeHuy += rs.getInt("soVeHuy");
//                soVeDoi += rs.getInt("soVeDoi");
//            }
//            chart.getData().clear();
//            chart.setLegendVisible(false);
//            chart.getData().add(series_01);
//
//            txtDoanhThu.setText(String.format("%.2fM", tongDoanhThu / 1_000_000));
//            txtSoVeBan.setText(String.valueOf(soVeDat));
//            txtSoVeHuy.setText(String.valueOf(soVeHuy));
//            txtSoVeDoi.setText(String.valueOf(soVeDoi));
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//    public void thongKeDoanhThuTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc){
//        try {
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
    public void initializeComboBoxes(){
        ObservableList<String> loaiThoiGian = FXCollections.observableArrayList("Ngày","Tháng","Năm");
        cmbLoaiThoiGian.setItems(loaiThoiGian);
        ObservableList<String> loaiLoc = FXCollections.observableArrayList("Lọc theo ngày","Lọc theo tháng","Lọc theo năm");
        cmbLoc.setItems(loaiLoc);
        ObservableList<String> thang = FXCollections.observableArrayList("Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5","Tháng 6","Tháng 7","Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12");
        cmbChonThang.setItems(thang);
        ObservableList<String> nam = FXCollections.observableArrayList("2021","2022","2023","2024");
        cmbChonNam.setItems(nam);
        cmbLoaiThoiGian.setValue("Ngày");
        cmbLoc.setValue("Lọc theo tháng");
        cmbChonThang.setValue("Tháng 4");
        cmbChonNam.setValue("2024");
    }
    public void btnThongKeOnAction(ActionEvent actionEvent) {
        if(cmbLoaiThoiGian.getValue().equals("Tháng")){
            int nam = Integer.parseInt(cmbChonNam.getSelectionModel().getSelectedItem().toString());
            chart.getData().clear();
            thongKeDoanhThuTheoThang(nam);

        }
        else if(cmbLoaiThoiGian.getValue().equals("Ngày")){
            if(cmbLoc.getValue().equals("Lọc theo tháng")){
                int nam = Integer.parseInt(cmbChonNam.getSelectionModel().getSelectedItem().toString());
                int thang = cmbChonThang.getSelectionModel().getSelectedIndex() + 1;
                hienThiDoanhThuNgay(nam,thang);
                for(int i=0;i<30;i++){
                    System.out.println(dsHoaDonBanVe.get(i));
                }

            }
        }
        else if(cmbLoaiThoiGian.getValue().equals("Ngày")){
            if(cmbLoc.getValue().equals("Lọc theo ngày")){
                LocalDate ngayBatDau = dapNgayBatDau.getValue();
                LocalDate ngayKetThuc = dapNgayKetThuc.getValue();
                chart.getData().clear();

            }
        }

    }

    public void setMain_Controller(Main_Controller mainController) {
    }
}
