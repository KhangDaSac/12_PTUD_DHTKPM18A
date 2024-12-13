package GUI.controllers;

import DAO.*;
import DTO.*;
import GUI.controllers.BaoCaoThongKe_GUI_Items.ThongKeDoanhThuTheoThoiGian_Controller;
import GUI.controllers.BaoCaoThongKe_GUI_Items.ThongKeTheoNhanVien_Controller;
import connectDB.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import utils.CurrencyFormat;


import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import static javafx.application.Application.launch;


public class BaoCaoThongKe_GUI_Controller implements Initializable {
    @FXML
    private  BarChart<String,Double> chart;
    @FXML
    private AnchorPane achoPane;
    private BaoCaoThongKe_GUI_Controller controller;
    @FXML
    private Button btnChuyenTau;
    @FXML
    private Button btnInBaoCao;
    @FXML
    private Button btnNhanVien;

    @FXML
    private Button btnThoiGian;

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
    private Node node;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        node = vboxThongKe.getChildren().get(0);
    }

    @FXML
    private VBox vboxThongKe;

    @FXML
    void btnChuyenTauOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/views/BaoCaoThongKe_GUI_Items/ThongKeTheoChuyenTau.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private ThongKeTheoNhanVien_Controller thongKeTheoNhanVien_controller;
    private ArrayList<ThongKeTheoNhanVien_Controller> thongKeTheoNhanVien_Controllers_list;

    public ThongKeTheoNhanVien_Controller getThongKeTheoNhanVien_controller() {
        return thongKeTheoNhanVien_controller;
    }

    public void setThongKeTheoNhanVien_controller(ThongKeTheoNhanVien_Controller thongKeTheoNhanVien_controller) {
        this.thongKeTheoNhanVien_controller = thongKeTheoNhanVien_controller;
    }

    @FXML
    void btnNhanVienOnAction(ActionEvent event) {
       try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BaoCaoThongKe_GUI_Items/ThongKeDoanhThuTheoNhanVien.fxml"));
           Parent anchopane = loader.load();
           ThongKeTheoNhanVien_Controller controller = loader.getController();
           vboxThongKe.getChildren().clear();
           vboxThongKe.getChildren().add(anchopane);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @FXML
    void btnThoiGianOnAction(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BaoCaoThongKe_GUI_Items/ThongKeDoanhThuTheoThoiGian.fxml"));
            Parent archorpane = loader.load();
            ThongKeDoanhThuTheoThoiGian_Controller controller = loader.getController();
            vboxThongKe.getChildren().clear();
            vboxThongKe.getChildren().add(archorpane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    ArrayList<Double> dsHoaDonBanVe = new ArrayList<>(Collections.nCopies(31, 0.0));
    ArrayList<Double> dsHoaDonDatVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> dsHoaDonHuyVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> dsHoaDonDoiVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> dsHoaDonLayVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> dsHoaDonHuyDatVe = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> doanhThuTheoNgay = new ArrayList<>(Collections.nCopies(31,0.0));
    ArrayList<Double> doanhThuTheoThang= new ArrayList<>(Collections.nCopies(12,0.0));
    ArrayList<Integer> demSoVeBan = new ArrayList<>(Collections.nCopies(31,0));
    ArrayList<Integer> demSoVeHuy = new ArrayList<>(Collections.nCopies(31,0));
    ArrayList<Integer> demSoVeDoi = new ArrayList<>(Collections.nCopies(31,0));
    ArrayList<Integer> demSoVeBanThang = new ArrayList<>(Collections.nCopies(12,0));
    ArrayList<Integer> demSoVeHuyThang = new ArrayList<>(Collections.nCopies(12,0));
    ArrayList<Integer> demSoVeDoiThang = new ArrayList<>(Collections.nCopies(12,0));
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
        demSoVeBan = new ArrayList<>(Collections.nCopies(31, 0));
        demSoVeHuy = new ArrayList<>(Collections.nCopies(31, 0));
        demSoVeDoi = new ArrayList<>(Collections.nCopies(31, 0));
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
                            demSoVeBan.add(i,demSoVeBan.get(i)+1);
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
                            demSoVeBan.set(i,demSoVeBan.get(i)+1);
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
                            demSoVeHuy.set(i,demSoVeHuy.get(i)+1);
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
                            demSoVeHuy.set(i,demSoVeHuy.get(i)+1);
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
                            demSoVeDoi.set(i,demSoVeDoi.get(i)+1);
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
        demSoVeBanThang = new ArrayList<>(Collections.nCopies(12, 0));
        demSoVeHuyThang = new ArrayList<>(Collections.nCopies(12, 0));
        demSoVeDoiThang = new ArrayList<>(Collections.nCopies(12, 0));
        soVeBanThang = 0;
        soVeDoiThang = 0;
        soVeHuyThang = 0;
        for (int i = 0; i <= 11; i++) {
            doanhThuTheoThang.set(i, thongKeDoanhThuTheoNgay(nam,i+1).stream().mapToDouble(Double::doubleValue).sum());
            demSoVeBanThang.set(i,demSoVeBan.stream().mapToInt(Integer::intValue).sum());
            demSoVeHuyThang.set(i,demSoVeHuy.stream().mapToInt(Integer::intValue).sum());
            demSoVeDoiThang.set(i,demSoVeDoi.stream().mapToInt(Integer::intValue).sum());
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
    @FXML
    public void btnThongKeOnAction(ActionEvent actionEvent) {
        if (cmbLoaiThoiGian.getValue().equals("Tháng")) {
            int nam = Integer.parseInt(cmbChonNam.getSelectionModel().getSelectedItem().toString());
            hienThiDoanhThuThang(nam);

        } else if (cmbLoaiThoiGian.getValue().equals("Ngày")) {
            int nam = Integer.parseInt(cmbChonNam.getSelectionModel().getSelectedItem().toString());
            int thang = cmbChonThang.getSelectionModel().getSelectedIndex() + 1;
            hienThiDoanhThuNgay(nam, thang);
        }
    }

        public static void exportToExcel(String filePath, int month,
                                         ArrayList<Integer> cancelledTickets,
                                         ArrayList<Integer> soldTickets,
                                         ArrayList<Integer> exchangedTickets,
                                         ArrayList<Double> revenues) {

            // Tạo workbook và sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Thống kê doanh thu");

            // Tạo font và style cho tiêu đề
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());

            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);

            // Tạo style cho các header
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);

            // Tạo style cho dữ liệu
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);

            // Thêm tiêu đề
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Thống kê doanh thu tháng " + month);
            titleCell.setCellStyle(titleStyle);

            // Merge các ô tiêu đề
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 4));

            // Thêm header
            Row headerRow = sheet.createRow(1);
            String[] headers = {"Ngày", "Số vé hủy", "Số vé bán", "Số vé đổi", "Doanh thu"};
            for (int i = 0; i < headers.length; i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(headers[i]);
                headerCell.setCellStyle(headerStyle);
            }

            // Thêm dữ liệu
            for (int i = 0; i < 31; i++) {
                Row dataRow = sheet.createRow(i + 2);

                // Ngày
                Cell dayCell = dataRow.createCell(0);
                dayCell.setCellValue(i + 1);
                dayCell.setCellStyle(dataStyle);

                // Số vé hủy
                Cell cancelledCell = dataRow.createCell(1);
                cancelledCell.setCellValue(cancelledTickets.get(i));
                cancelledCell.setCellStyle(dataStyle);

                // Số vé bán
                Cell soldCell = dataRow.createCell(2);
                soldCell.setCellValue(soldTickets.get(i));
                soldCell.setCellStyle(dataStyle);

                // Số vé đổi
                Cell exchangedCell = dataRow.createCell(3);
                exchangedCell.setCellValue(exchangedTickets.get(i));
                exchangedCell.setCellStyle(dataStyle);

                // Doanh thu
                Cell revenueCell = dataRow.createCell(4);
                revenueCell.setCellValue(revenues.get(i));
                revenueCell.setCellStyle(dataStyle);
            }

            // Tự động điều chỉnh kích thước cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi dữ liệu ra file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Xuất file Excel thành công tại: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Mở file ngay sau khi tạo
            try {
                File file = new File(filePath);
                if (Desktop.isDesktopSupported() && file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Không thể mở file. Kiểm tra ứng dụng hỗ trợ mở file Excel.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void taoFileExcelTheoThang(String filePath,int nam,ArrayList<Integer> demSoVeBanThang,ArrayList<Integer> demSoVeDoiThang,ArrayList<Integer> demSoVeHuyThang ,ArrayList<Double> doanhThuTheoThang) {
        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Thống kê doanh thu theo tháng trong năm"+nam);
        // Tạo font và style cho tiêu đề
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);


        // Tạo style cho các header
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);

        // Tạo style cho dữ liệu
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);

        // Thêm tiêu đề
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Thống kê doanh thu năm " + nam);
        titleCell.setCellStyle(titleStyle);

        // Merge các ô tiêu đề
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 4));

        // Thêm header
        Row headerRow = sheet.createRow(1);
        String[] headers = {"Tháng", "Số vé bán", "Số vé hủy", "Số vé đổi", "Doanh thu"};
        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headers[i]);
            headerCell.setCellStyle(headerStyle);
        }

        // Thêm dữ liệu
        for (int i = 0; i < 12; i++) {
            Row dataRow = sheet.createRow(i + 2);

            // Tháng
            Cell monthCell = dataRow.createCell(0);
            monthCell.setCellValue(i + 1);
            monthCell.setCellStyle(dataStyle);

            // Số vé bán
            Cell revenueCell = dataRow.createCell(1);
            revenueCell.setCellValue(demSoVeBanThang.get(i));
            revenueCell.setCellStyle(dataStyle);

            // Số vé hủy
            Cell cancelledCell = dataRow.createCell(2);
            cancelledCell.setCellValue(demSoVeHuyThang.get(i));
            cancelledCell.setCellStyle(dataStyle);

            // Số vé đổi
            Cell exchangedCell = dataRow.createCell(3);
            exchangedCell.setCellValue(demSoVeDoiThang.get(i));
            exchangedCell.setCellStyle(dataStyle);

            // Doanh thu
            Cell revenueCell1 = dataRow.createCell(4);
            revenueCell1.setCellValue(doanhThuTheoThang.get(i));
            revenueCell1.setCellStyle(dataStyle);

        }
        // Tự động điều chỉnh kích thước cột
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Ghi dữ liệu ra file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Xuất file Excel thành công tại: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Mở file ngay sau khi tạo
        try {
            File file = new File(filePath);
            if (Desktop.isDesktopSupported() && file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("Không thể mở file. Kiểm tra ứng dụng hỗ trợ mở file Excel.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void btnInBaoCao(ActionEvent event) {
        if(cmbLoaiThoiGian.getValue().equals("Tháng")){
        int nam = Integer.parseInt(cmbChonNam.getSelectionModel().getSelectedItem().toString());
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("fileThongTinKhachHang");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
            File file = fileChooser.showSaveDialog(cmbChonNam.getScene().getWindow());
        taoFileExcelTheoThang(file.getAbsolutePath()    ,cmbChonThang.getSelectionModel().getSelectedIndex()+1,demSoVeBanThang,demSoVeDoiThang,demSoVeHuyThang,doanhThuTheoThang);
    }
         if(cmbLoaiThoiGian.getValue().equals("Ngày")){
            int nam = Integer.parseInt(cmbChonNam.getSelectionModel().getSelectedItem().toString());
            int thang = cmbChonThang.getSelectionModel().getSelectedIndex() + 1;
             FileChooser fileChooser = new FileChooser();
             fileChooser.setTitle("fileThongTinKhachHang");
             fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
             File file = fileChooser.showSaveDialog(cmbChonThang.getScene().getWindow());
            exportToExcel(file.getAbsolutePath(),thang,demSoVeHuy,demSoVeBan,demSoVeDoi, doanhThuTheoNgay);
        }
    }
}
