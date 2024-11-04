package GUI.controllers;

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
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class BaoCaoThongKe_GUI_Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
    }

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


    public void thongKeDoanhThuTheoThang(int nam) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String query = "exec thongKeDoanhThuTheoThangTrongNam ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, nam);
            ResultSet rs = statement.executeQuery();

            Double tongDoanhThu = 0.0;
            int soVeDat = 0;
            int soVeHuy = 0;
            int soVeDoi = 0;
            XYChart.Series<String, Double> series_01 = new XYChart.Series<>();
            series_01.getData().add(new XYChart.Data<>("Jan", 0.0));
            series_01.getData().add(new XYChart.Data<>("Feb", 0.0));
            series_01.getData().add(new XYChart.Data<>("Mar", 0.0));
            series_01.getData().add(new XYChart.Data<>("Apr", 0.0));
            series_01.getData().add(new XYChart.Data<>("May", 0.0));
            series_01.getData().add(new XYChart.Data<>("Jun", 0.0));
            series_01.getData().add(new XYChart.Data<>("Jul", 0.0));
            series_01.getData().add(new XYChart.Data<>("Aug", 0.0));
            series_01.getData().add(new XYChart.Data<>("Sep", 0.0));
            series_01.getData().add(new XYChart.Data<>("Oct", 0.0));
            series_01.getData().add(new XYChart.Data<>("Nov", 0.0));
            series_01.getData().add(new XYChart.Data<>("Dec", 0.0));

            while (rs.next()) {
                String monthName = switch (rs.getInt("thang")) {
                    case 1 -> "Jan";
                    case 2 -> "Feb";
                    case 3 -> "Mar";
                    case 4 -> "Apr";
                    case 5 -> "May";
                    case 6 -> "Jun";
                    case 7 -> "Jul";
                    case 8 -> "Aug";
                    case 9 -> "Sep";
                    case 10 -> "Oct";
                    case 11 -> "Nov";
                    case 12 -> "Dec";
                    default -> null;
                };
                if (monthName != null) {
                    series_01.getData().removeIf(data -> data.getXValue().equals(monthName));
                    series_01.getData().add(new XYChart.Data<>(monthName, rs.getDouble("monthly_revenue")));

                    tongDoanhThu += rs.getDouble("monthly_revenue");
                    soVeDat += rs.getInt("soVeBanDuoc");
                    soVeHuy += rs.getInt("soVeHuy");
                    soVeDoi += rs.getInt("soVeDoi");
                }
            }
            chart.getData().clear();
            chart.setLegendVisible(false);
            chart.getData().add(series_01);

            txtDoanhThu.setText(String.format("%.2fM", tongDoanhThu / 1_000_000));
            txtSoVeBan.setText(String.valueOf(soVeDat));
            txtSoVeHuy.setText(String.valueOf(soVeHuy));
            txtSoVeDoi.setText(String.valueOf(soVeDoi));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
public void thongKeDoanhThuTheoNgay(int nam, int thang){
   try{
         Connection con = ConnectDB.getInstance().getConnection();
         String query = "exec thongKeDoanhThuTheoNgayTrongThang ?,?";
         PreparedStatement statement = con.prepareStatement(query);
         statement.setInt(1, nam);
         statement.setInt(2, thang);
         ResultSet rs = statement.executeQuery();

         Double tongDoanhThu = 0.0;
         int soVeDat = 0;
         int soVeHuy = 0;
         int soVeDoi = 0;
         XYChart.Series<String, Double> series_01 = new XYChart.Series<>();

         // Khởi tạo dữ liệu mặc định cho các ngày với giá trị bằng 0
         for (int i = 1; i <= 31; i++) {
              series_01.getData().add(new XYChart.Data<>(String.valueOf(i), 0.0));
         }

         // Cập nhật dữ liệu từ ResultSet
         while (rs.next()) {
              int day = rs.getInt("ngay");
              series_01.getData().removeIf(data -> data.getXValue().equals(String.valueOf(day)));
              series_01.getData().add(new XYChart.Data<>(String.valueOf(day), rs.getDouble("daily_revenue")));
             System.out.println("Ngày : " +  day + ", Doanh thu: " + rs.getDouble("daily_revenue"));
             System.out.println("Ngày: " + day + ", Doanh thu: " + rs.getDouble("daily_revenue"));
              tongDoanhThu += rs.getDouble("daily_revenue");
              soVeDat += rs.getInt("soVeBanDuoc");
              soVeHuy += rs.getInt("soVeHuy");
              soVeDoi += rs.getInt("soVeDoi");
         }
         chart.getData().clear();
         chart.setLegendVisible(false);
         chart.getData().add(series_01);

         txtDoanhThu.setText(String.format("%.2fM", tongDoanhThu / 1_000_000));
         txtSoVeBan.setText(String.valueOf(soVeDat));
         txtSoVeHuy.setText(String.valueOf(soVeHuy));
         txtSoVeDoi.setText(String.valueOf(soVeDoi));
   }catch (Exception e){
       throw new RuntimeException(e);
   }
}
public void thongKeDoanhThuTheoNgay(LocalDate ngayBatDau, LocalDate ngayKetThuc){
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}

    public void initializeComboBoxes(){
        ObservableList<String> loaiThoiGian = FXCollections.observableArrayList("Ngày","Tháng","Năm");
        cmbLoaiThoiGian.setItems(loaiThoiGian);
        ObservableList<String> loaiLoc = FXCollections.observableArrayList("Lọc theo ngày","Lọc theo tháng","Lọc theo năm");
        cmbLoc.setItems(loaiLoc);
        ObservableList<String> thang = FXCollections.observableArrayList("Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5","Tháng 6","Tháng 7","Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12");
        cmbChonThang.setItems(thang);
        ObservableList<String> nam = FXCollections.observableArrayList("2021","2022","2023","2024");
        cmbChonNam.setItems(nam);
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
                chart.getData().clear();
                thongKeDoanhThuTheoNgay(nam,thang);
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
