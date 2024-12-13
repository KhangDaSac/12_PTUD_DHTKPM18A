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
import java.util.*;
import java.util.stream.Collectors;

import static DAO.NhanVien_DAO.*;

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
        if (cmbLocTheo.getValue().equals("Một nhân viên")) {
            if (cmbLoaiThoiGian.getValue().equals("Tháng")) {
                int thang = cmbChonThang.getSelectionModel().getSelectedIndex() + 1;
                Map<String, ArrayList<Object>> thongKeTheoNhanVien = thongKeTheoNhanVien(txtMaNhanVien.getText(), thang, Integer.parseInt(cmbChonNam.getValue()));
                hienThiThongKeTheoNhanVien(thongKeTheoNhanVien);
            } else if (cmbLoaiThoiGian.getValue().equals("Năm")) {
                int nam = Integer.parseInt(cmbChonNam.getValue());
                Map<String, ArrayList<Object>> thongKeTheoNhanVien = thongKeTheoNhanVienTheoNam(txtMaNhanVien.getText(), nam);
                hienThiThongKeTheoNhanVien(thongKeTheoNhanVien);
            }
        } else if (cmbLocTheo.getValue().equals("Top 5 nhân viên")) {
                if (cmbLoaiThoiGian.getValue().equals("Tháng")) {
                    int thang = cmbChonThang.getSelectionModel().getSelectedIndex() + 1;
                    System.out.println("khong vao day");
                    Map<String, ArrayList<Object>> thongKeTheoNhanVien = thongKeTop5NhanVienTheoThang(thang, Integer.parseInt(cmbChonNam.getValue()));
                    hienThiThongKeTheoNhanVien(thongKeTheoNhanVien);
                }
                else if (cmbLoaiThoiGian.getValue().equals("Năm")) {
                    int nam = Integer.parseInt(cmbChonNam.getValue());
                    Map<String, ArrayList<Object>> thongKeTheoNhanVien = thongKeTop5NhanVienTheoNam(nam);
                    hienThiThongKeTheoNhanVien(thongKeTheoNhanVien);
                }
            }
    }

        @Override
        public void initialize (URL location, ResourceBundle resources){
            initializeComboBoxes();
        }
        public void initializeComboBoxes () {
            ObservableList<String> loaiThoiGian = FXCollections.observableArrayList("Tháng", "Năm");
            cmbLoaiThoiGian.setItems(loaiThoiGian);
            ObservableList<String> thang = FXCollections.observableArrayList("Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12");
            cmbChonThang.setItems(thang);
            ObservableList<String> nam = FXCollections.observableArrayList("2021", "2022", "2023", "2024");
            cmbChonNam.setItems(nam);
            cmbLoaiThoiGian.setValue("Tháng");
            cmbChonThang.setValue("Tháng 12");
            cmbChonNam.setValue("2024");
            ObservableList<String> locTheo = FXCollections.observableArrayList("Một nhân viên", "Top 5 nhân viên");
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
        public static Map<String,ArrayList<Object>> thongKeTheoNhanVienTheoNam(String maNhanVien,int nam){
        Map<String,ArrayList<Object>> thongKeTheoNhanVien = new HashMap<>();
        ArrayList<Object> danhSachHoaDon = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query ="exec thongKeDoanhThuTheoNhanVienTheoNam ? , ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maNhanVien);
            statement.setInt(2,nam);
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
    public static Map<String,ArrayList<Object>> thongKeTop5NhanVienTheoThang(int thang, int nam){
        Map<String,ArrayList<Object>> thongKeTheoNhanVien = new HashMap<>();

        Connection con = ConnectDB.getInstance().getConnection();
        String query ="exec thongKeTop5NhanVienCoDoanhThuCaoNhatTrongThang ?, ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,thang);
            statement.setInt(2,nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String tenNhanVien = NhanVien_DAO.getNhanvienTheoMa(rs.getString("maNhanVien")).getTenNhanVien();
                if(rs.getString("maHoaDon").contains("HDBV")){
                    HoaDonBanVe hoaDonBanVe = HoaDonBanVe_DAO.getHoaDonBanVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonBanVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonBanVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                }
                else if(rs.getString("maHoaDon").contains("HDHV")){
                    HoaDonHuyVe hoaDonHuyVe = HoaDonHuyVe_DAO.getHoaDonHuyVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonHuyVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonHuyVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                } else if (rs.getString("maHoaDon").contains("HDDO")) {
                    HoaDonDoiVe hoaDonDoiVe = HoaDonDoiVe_DAO.getHoaDonDoiVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonDoiVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonDoiVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                } else if (rs.getString("maHoaDon").contains("HDDV")) {
                    HoaDonDatVe hoaDonDatVe = HoaDonDatVe_DAO.getHoaDonDatVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonDatVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonDatVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                } else if (rs.getString("maHoaDon").contains("HDHD")) {
                    HoaDonHuyDatVe hoaDonHuyDatVe = HoaDonHuyDatVe_DAO.getHoaDonHuyDatVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonHuyDatVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonHuyDatVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                }else if (rs.getString("maHoaDon").contains("HDLV")){
                    HoaDonLayVe hoaDonLayVe = HoaDonLayVe_DAO.getHoaDonLayVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonLayVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonLayVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return thongKeTheoNhanVien;
    }

    public Map<String,ArrayList<Object>> thongKeTop5NhanVienTheoNam(int nam){
        Map<String,ArrayList<Object>> thongKeTheoNhanVien = new HashMap<>();
        ArrayList<Object> danhSachHoaDon = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query ="exec thongKeTop5NhanVienCoDoanhThuCaoNhatTrongNam ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,nam);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String tenNhanVien = NhanVien_DAO.getNhanvienTheoMa(rs.getString("maNhanVien")).getTenNhanVien();
                if (rs.getString("maHoaDon").contains("HDBV")) {
                    HoaDonBanVe hoaDonBanVe = HoaDonBanVe_DAO.getHoaDonBanVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonBanVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonBanVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                } else if (rs.getString("maHoaDon").contains("HDHV")) {
                    HoaDonHuyVe hoaDonHuyVe = HoaDonHuyVe_DAO.getHoaDonHuyVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonHuyVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonHuyVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                } else if (rs.getString("maHoaDon").contains("HDDO")) {
                    HoaDonDoiVe hoaDonDoiVe = HoaDonDoiVe_DAO.getHoaDonDoiVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonDoiVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonDoiVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                } else if (rs.getString("maHoaDon").contains("HDDV")) {
                    HoaDonDatVe hoaDonDatVe = HoaDonDatVe_DAO.getHoaDonDatVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonDatVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonDatVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                } else if (rs.getString("maHoaDon").contains("HDHD")) {
                    HoaDonHuyDatVe hoaDonHuyDatVe = HoaDonHuyDatVe_DAO.getHoaDonHuyDatVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonHuyDatVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonHuyDatVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                } else if (rs.getString("maHoaDon").contains("HDLV")) {
                    HoaDonLayVe hoaDonLayVe = HoaDonLayVe_DAO.getHoaDonLayVeTheoMa(rs.getString("maHoaDon"));
                    if (thongKeTheoNhanVien.containsKey(tenNhanVien)) {
                        ArrayList<Object> danhSachCu = thongKeTheoNhanVien.get(tenNhanVien);
                        danhSachCu.add(hoaDonLayVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachCu);
                    } else {
                        ArrayList<Object> danhSachMoi = new ArrayList<>();
                        danhSachMoi.add(hoaDonLayVe);
                        thongKeTheoNhanVien.put(tenNhanVien, danhSachMoi);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return thongKeTheoNhanVien;
    }
    public void hienThiThongKeTheoNhanVien(Map<String, ArrayList<Object>> thongKeTheoNhanVien) {
        barChar.getData().clear();
        XYChart.Series<Double, String> series_01 = new XYChart.Series<>();
        Map<String,Double> thongKeTheoNhanVienSapXep = new HashMap<>();
        for (Map.Entry<String, ArrayList<Object>> entry : thongKeTheoNhanVien.entrySet()) {
            thongKeTheoNhanVienSapXep.put(entry.getKey(),tinhTongDoanhThuTheoNhanVien(entry.getValue()));
        }
        thongKeTheoNhanVienSapXep = thongKeTheoNhanVienSapXep.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        int i =0;
        for (Map.Entry<String, Double> entry : thongKeTheoNhanVienSapXep.entrySet()) {
            i++;
            series_01.getData().add(new XYChart.Data<>(entry.getValue(), entry.getKey()));
            if (i == 5) {
                break;
            }
        }

        barChar.getData().add(series_01);

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
