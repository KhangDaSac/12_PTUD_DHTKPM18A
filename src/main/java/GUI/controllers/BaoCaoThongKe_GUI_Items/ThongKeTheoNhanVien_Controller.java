package GUI.controllers.BaoCaoThongKe_GUI_Items;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeTheoNhanVien_Controller implements Initializable {
    @FXML
    private BarChart<?, ?> barChar;

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
}
