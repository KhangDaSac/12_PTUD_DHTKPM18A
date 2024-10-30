package GUI.controllers;

import connectDB.ConnectDB;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
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

public class BaoCaoThongKe_GUI_Controller extends Application{
    @FXML
    private  BarChart<String,Double> chart;
    private BaoCaoThongKe_GUI_Controller controller;

    @FXML
    private DatePicker dapNgayBatDau;
    @FXML
    private DatePicker dapNgayKetThuc;
    @FXML
    private TextField txtDoanhThu;
    private  void taiDuLieuLenBieuDo (){
        XYChart.Series<String,Double> series_01= new XYChart.Series<>();
        series_01.getData().add(new XYChart.Data("Jan",500));
        series_01.getData().add(new XYChart.Data("Feb",600));
        series_01.getData().add(new XYChart.Data("Mar",700));
        series_01.getData().add(new XYChart.Data("Apr",800));
        series_01.getData().add(new XYChart.Data("May",900));
        series_01.getData().add(new XYChart.Data("Jun",1000));
        series_01.getData().add(new XYChart.Data("Jul",1100));
        series_01.getData().add(new XYChart.Data("Aug",1200));
        series_01.getData().add(new XYChart.Data("Sep",1300));
        series_01.getData().add(new XYChart.Data("Oct",1400));
        series_01.getData().add(new XYChart.Data("Nov", 1500));
        series_01.getData().add(new XYChart.Data("Dec", 1600));
        chart.setLegendVisible(false);
        chart.getData().add(series_01);
    }


    public void start(Stage primaryStage) throws Exception {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BaoCaoThongKe_GUI.fxml"));
            Parent root = loader.load();
            controller = loader.getController(); // Initialize the controller
            Scene scene = new Scene(root);
            primaryStage.setTitle("Hóa đơn");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    public static void main(String[] args) {
        launch(args);
       
    }
double tongDoanhThu=0;
    // thong ke doanh thu theo thang
    public void thongKeDoanhThuTheoThang (LocalDate ngayBatDau, LocalDate ngayKetThuc, String dieuKienLoc) {
        if (dieuKienLoc.equals("tất cả")) {
            try {
                ConnectDB.getInstance().connect();
                Connection con = ConnectDB.getConnection();
                String query = "exec thongKeDoanhThuTheoThang ?,?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setDate(1, Date.valueOf(ngayBatDau));
                statement.setDate(2, Date.valueOf(ngayKetThuc));
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    XYChart.Series<String, Double> series_01 = new XYChart.Series<>();
                    switch (rs.getInt("thang")) {
                        case 1 -> series_01.getData().add(new XYChart.Data("Jan", rs.getDouble("monthly_revenue")));
                        case 2 -> series_01.getData().add(new XYChart.Data("Feb", rs.getDouble("monthly_revenue")));
                        case 3 -> series_01.getData().add(new XYChart.Data("Mar", rs.getDouble("monthly_revenue")));
                        case 4 -> series_01.getData().add(new XYChart.Data("Apr", rs.getDouble("monthly_revenue")));
                        case 5 -> series_01.getData().add(new XYChart.Data("May", rs.getDouble("monthly_revenue")));
                        case 6 -> series_01.getData().add(new XYChart.Data("Jun", rs.getDouble("monthly_revenue")));
                        case 7 -> series_01.getData().add(new XYChart.Data("Jul", rs.getDouble("monthly_revenue")));
                        case 8 -> series_01.getData().add(new XYChart.Data("Aug", rs.getDouble("monthly_revenue")));
                        case 9 -> series_01.getData().add(new XYChart.Data("Sep", rs.getDouble("monthly_revenue")));
                        case 10 -> series_01.getData().add(new XYChart.Data("Oct", rs.getDouble("monthly_revenue")));
                        case 11 -> series_01.getData().add(new XYChart.Data("Nov", rs.getDouble("monthly_revenue")));
                        case 12 -> series_01.getData().add(new XYChart.Data("Dec", rs.getDouble("monthly_revenue")));
                    }
                    tongDoanhThu = tongDoanhThu + rs.getDouble("monthly_revenue");
                    chart.setLegendVisible(false);
                    chart.getData().add(series_01);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Handle other conditions
        }

    }

    public void btnThongKeOnAction(ActionEvent actionEvent) {
        LocalDate ngayBatDau = dapNgayBatDau.getValue();
        LocalDate ngayKetThuc = dapNgayKetThuc.getValue();
        chart.getData().clear();
        thongKeDoanhThuTheoThang(ngayBatDau, ngayKetThuc, "tất cả");
        txtDoanhThu.setText(String.valueOf(tongDoanhThu/1000000)+"M");
    }
}
