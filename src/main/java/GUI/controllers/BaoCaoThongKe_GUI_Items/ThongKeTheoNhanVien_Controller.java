package GUI.controllers.BaoCaoThongKe_GUI_Items;

import DAO.*;
import DTO.*;
import connectDB.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;

import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ThongKeTheoNhanVien_Controller implements Initializable {
    @FXML
    private BarChart<Double, String> barChar;

    @FXML
    private Button btnThongKe;

    @FXML
    private ComboBox<String> cmbChonNam;

    @FXML
    private ComboBox<String> cmbLoaiThoiGian;

    @FXML
    private ComboBox<String> cmbLocTheo;

    @FXML
    private ComboBox<String> cmbChonThang;

    @FXML
    private TextField txtMaNhanVien;

    @FXML
    void btnThongKeOnAction(ActionEvent event) {
        hienThiThongKeTheoNhanVien(txtMaNhanVien.getText(),cmbChonThang.getSelectionModel().getSelectedIndex()+1,Integer.parseInt(cmbChonNam.getSelectionModel().getSelectedItem()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeComboBoxes();
    }
    public void initializeComboBoxes(){
        ObservableList<String> loaiThoiGian = FXCollections.observableArrayList("Tháng","Năm");
        cmbLoaiThoiGian.setItems(loaiThoiGian);
        ObservableList<String> thang = FXCollections.observableArrayList("Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5","Tháng 6","Tháng 7","Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12");
        cmbChonThang.setItems(thang);
        ObservableList<String> nam = FXCollections.observableArrayList("2021","2022","2023","2024");
        cmbChonNam.setItems(nam);
        cmbLoaiThoiGian.setValue("Tháng");
        cmbChonThang.setValue("Tháng 12");
        cmbChonNam.setValue("2024");
        ObservableList<String> locTheo = FXCollections.observableArrayList("Một nhân viên","Top 5 nhân viên");
        cmbLocTheo.setItems(locTheo);

    }

    public static Map<String,ArrayList<Object>> thongKeTheoNhanVien(String maNhanVien,int thang, int nam){
        Map<String,ArrayList<Object>> thongKeTheoNhanVien = new HashMap<>();
        ArrayList<Object> danhSachHoaDon = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query ="exec thongKeDoanhThuTheoNhanVienTheoThang ? , ? , ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maNhanVien);
            statement.setInt(2,thang);
            statement.setInt(3,nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                if(rs.getString("maHoaDon").contains("HDBV")){
                    HoaDonBanVe hoaDonBanVe = HoaDonBanVe_DAO.getHoaDonBanVeTheoMa(rs.getString("maHoaDon"));
                    danhSachHoaDon.add(hoaDonBanVe);
                }
                else if(rs.getString("maHoaDon").contains("HDHV")){
                    HoaDonHuyVe hoaDonHuyVe = HoaDonHuyVe_DAO.getHoaDonHuyVeTheoMa(rs.getString("maHoaDon"));
                    danhSachHoaDon.add(hoaDonHuyVe);
                } else if (rs.getString("maHoaDon").contains("HDDO")) {
                    HoaDonDoiVe hoaDonDoiVe = HoaDonDoiVe_DAO.getHoaDonDoiVeTheoMa(rs.getString("maHoaDon"));
                    danhSachHoaDon.add(hoaDonDoiVe);
                } else if (rs.getString("maHoaDon").contains("HDDV")) {
                    HoaDonDatVe hoaDonDatVe = HoaDonDatVe_DAO.getHoaDonDatVeTheoMa(rs.getString("maHoaDon"));
                    danhSachHoaDon.add(hoaDonDatVe);
                } else if (rs.getString("maHoaDon").contains("HDHD")) {
                    HoaDonHuyDatVe hoaDonHuyDatVe = HoaDonHuyDatVe_DAO.getHoaDonHuyDatVeTheoMa(rs.getString("maHoaDon"));
                    danhSachHoaDon.add(hoaDonHuyDatVe);
                }else if (rs.getString("maHoaDon").contains("HDLV")){
                    HoaDonLayVe hoaDonLayVe = HoaDonLayVe_DAO.getHoaDonLayVeTheoMa(rs.getString("maHoaDon"));
                    danhSachHoaDon.add(hoaDonLayVe);
                }

            }
            thongKeTheoNhanVien.put(maNhanVien,danhSachHoaDon);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return thongKeTheoNhanVien;
    }
    public void hienThiThongKeTheoNhanVien(String maNhanVien,int thang, int nam) {
        Map<String, ArrayList<Object>> thongKeTheoNhanVien = thongKeTheoNhanVien(maNhanVien, thang, nam);
        XYChart.Series<Double, String> series_01 = new XYChart.Series<>();
        for (Map.Entry<String, ArrayList<Object>> entry : thongKeTheoNhanVien.entrySet()) {
           series_01.getData().add(new XYChart.Data<>(tinhTongDoanhThuTheoNhanVien(entry.getValue()),NhanVien_DAO.getNhanvienTheoMa(entry.getKey()).getTenNhanVien()) ) ;
        }
        barChar.getData().clear();
        barChar.getData().add(series_01);
        series_01.getData().forEach(data -> {
            Node node = data.getNode();

            if (node instanceof Region) {

                Region bar = (Region) node;
                bar.setPrefWidth(30);
                bar.setStyle("-fx-background-color: #4CAF50;");
            }
            node.setOnMouseClicked(event -> {
                double doanhThu =  data.getXValue();  // Lấy doanh thu
                String tenNhanVien = data.getYValue();        // Lấy tên nhân viên

                // Tạo Label và thiết lập vị trí hiển thị trên cột
                Text label = new Text("Doanh thu: " + doanhThu + "\nNhân viên: " + tenNhanVien);
                label.setFill(Color.BLACK);  // Màu chữ
                label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

                // Xác định vị trí của label dựa trên cột
                StackPane labelContainer = new StackPane(label);
                labelContainer.setLayoutX(node.getLayoutX() + node.getBoundsInParent().getWidth() / 2);
                labelContainer.setLayoutY(node.getLayoutY() - 20); // Đặt label phía trên cột

                // Thêm label vào BarChart
                barChar.getParent().getChildrenUnmodifiable().add(labelContainer);
            });
        });
    }
    public Double tinhTongDoanhThuTheoNhanVien(ArrayList<Object> thongKeTheoNhanVien) {
        Double tongDoanhThu = 0.0;
        for (Object obj : thongKeTheoNhanVien){
            if (obj instanceof HoaDonBanVe){
                HoaDonBanVe hoaDonBanVe = (HoaDonBanVe) obj;
              tongDoanhThu+=  hoaDonBanVe.tongTienCuoi();
            }
            else if (obj instanceof HoaDonHuyVe){
                HoaDonHuyVe hoaDonHuyVe = (HoaDonHuyVe) obj;
                tongDoanhThu+= hoaDonHuyVe.tongTienCuoi();
            }
            else if (obj instanceof HoaDonDoiVe){
                HoaDonDoiVe hoaDonDoiVe = (HoaDonDoiVe) obj;
                tongDoanhThu+= hoaDonDoiVe.tongTienCuoi();
            }
            else if (obj instanceof HoaDonDatVe){
                HoaDonDatVe hoaDonDatVe = (HoaDonDatVe) obj;
                tongDoanhThu+= hoaDonDatVe.tongTienCuoi();
            }
            else if (obj instanceof HoaDonHuyDatVe){
                HoaDonHuyDatVe hoaDonHuyDatVe = (HoaDonHuyDatVe) obj;
                tongDoanhThu+= hoaDonHuyDatVe.tongTienCuoi();
            }
            else if (obj instanceof HoaDonLayVe){
                HoaDonLayVe hoaDonLayVe = (HoaDonLayVe) obj;
                tongDoanhThu+= hoaDonLayVe.tongTienCuoi();
            }
        }
        System.out.println(" "+ tongDoanhThu);
        return tongDoanhThu;

    }

}
