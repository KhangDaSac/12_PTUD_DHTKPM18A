package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import DTO.CaLamViec;
import DTO.NhanVien;
import GUI.applications.Run;
import GUI.controllers.BaoCao_GUI_Items.PhieuKiemTien_BaoCao_Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class BaoCao_GUI_Controller implements Initializable {

    @FXML
    private JFXButton btnLapPhieuKiemTienCuoiCa;

    @FXML
    private JFXButton btnLapPhieuKiemTienDauCa;

    @FXML
    private JFXButton btnXuatFileVaKetCa;

    @FXML
    private Label lblMaCaLamViec;

    @FXML
    private Label lblMaNhanVien;

    @FXML
    private Label lblSoLuongHoaDonBanVe;

    @FXML
    private Label lblSoLuongHoaDonDatVe;

    @FXML
    private Label lblSoLuongHoaDonDoiVe;

    @FXML
    private Label lblSoLuongHoaDonHuyDatVe;

    @FXML
    private Label lblSoLuongHoaDonHuyVe;

    @FXML
    private Label lblSoLuongHoaDonLayVe;

    @FXML
    private Label lblTenNhanVien;

    @FXML
    private Label lblThoiGianBatDau;

    @FXML
    private Label lblThoiGianKetThuc;

    @FXML
    private Label lblTongTienBatDauCa;

    @FXML
    private Label lblTongTienChenhLech;

    @FXML
    private Label lblTongTienHoaDonBanVe;

    @FXML
    private Label lblTongTienHoaDonDatVe;

    @FXML
    private Label lblTongTienHoaDonDoiVe;

    @FXML
    private Label lblTongTienHoaDonHuyDatVe;

    @FXML
    private Label lblTongTienHoaDonHuyVe;

    @FXML
    private Label lblTongTienHoaDonLayVe;

    @FXML
    private Label lblTongTienKetThucCa;

    @FXML
    private Label lblTongTienTrongCa;

    private JFXDialog dialog;

    private Main_Controller main_controller;

    private double[][] dsHoaDon;

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    @FXML
    void btnLapPhieuKiemTienCuoiCaOnAction(ActionEvent event) {
        if(main_controller.getPhieuKetToan() == null
                || main_controller.getPhieuKetToan().getCaLamViec() == null
                || main_controller.getPhieuKetToan().getPhieuKiemTienDauCa() == null){
            main_controller.showMessagesDialog("Chưa có phiếu kiểm tiền đầu ca");
            return;
        }
        if(main_controller.getPhieuKetToan().getPhieuKiemTienCuoiCa() != null){
            main_controller.showMessagesDialog("Đã có phiếu kiểm tiền cuối ca");
            return;
        }
        hienThiPhieuKiemTienCuoiCa();
    }

    @FXML
    void btnLapPhieuKiemTienDauCaOnAction(ActionEvent event) {
        if(main_controller.getPhieuKetToan() != null && main_controller.getPhieuKetToan().getCaLamViec() != null){
            main_controller.showMessagesDialog("Ca làm việc đã có phiếu kiểm tiền đầu ca");
            return;
        }
        hienThiPhieuKiemTienDauCa();
    }

    @FXML
    void btnXuatFileVaKetCaOnAction(ActionEvent event) {

    }

    public void hienThiPhieuKiemTienDauCa(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("/view/BaoCao_GUI_Items/PhieuKiemTien_BaoCao.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            PhieuKiemTien_BaoCao_Controller controller = fxmlLoader.getController();
            controller.setBaoCao_gui_controller(this);
            dialog = main_controller.showWindowDialog(anchorPane, "Phiếu kiểm tiền");
            controller.setDialog(dialog);
            controller.setNhanVienKiemTien(main_controller.getNhanVien());
            controller.setLoaiPhieuKiemTien("DC");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void hienThiPhieuKiemTienCuoiCa(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("/view/BaoCao_GUI_Items/PhieuKiemTien_BaoCao.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            PhieuKiemTien_BaoCao_Controller controller = fxmlLoader.getController();
            controller.setBaoCao_gui_controller(this);
            dialog = main_controller.showWindowDialog(anchorPane, "Phiếu kiểm tiền");
            controller.setDialog(dialog);
            controller.setNhanVienKiemTien(main_controller.getNhanVien());
            controller.setLoaiPhieuKiemTien("CC");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void capNhatThongTinCaLamViec(){
        if(main_controller.getPhieuKetToan() != null && main_controller.getPhieuKetToan().getCaLamViec() != null){
            CaLamViec caLamViec = main_controller.getPhieuKetToan().getCaLamViec();
            lblMaCaLamViec.setText(caLamViec.getMaCaLamViec());
            lblThoiGianBatDau.setText(TimeFormat.formatLocalDateTime(caLamViec.getThoiGianBatDau()));
            lblThoiGianKetThuc.setText(caLamViec.getThoiGianKetThuc() != null
                    ? TimeFormat.formatLocalDateTime(caLamViec.getThoiGianKetThuc())
                    : "Chưa có");

        }else{
            lblMaCaLamViec.setText("Chưa có");
            lblThoiGianBatDau.setText("Chưa có");
            lblThoiGianKetThuc.setText("Chưa có");
        }

        if(main_controller.getPhieuKetToan() != null
                && main_controller.getPhieuKetToan().getCaLamViec() != null
                && main_controller.getPhieuKetToan().getCaLamViec().getNhanVien() != null){
            NhanVien nhanVien = main_controller.getPhieuKetToan().getCaLamViec().getNhanVien();
            lblMaNhanVien.setText(nhanVien.getMaNhanVien());
            lblTenNhanVien.setText(nhanVien.getTenNhanVien());
        }else {
            lblMaNhanVien.setText("Chưa có");
            lblTenNhanVien.setText("Chưa có");
        }
    }

    public void capTongTienDauCa(){
        if(main_controller.getPhieuKetToan() == null){
            return;
        }
        lblTongTienBatDauCa.setText(CurrencyFormat.currencyFormat(main_controller.getPhieuKetToan().getPhieuKiemTienDauCa().tongTien()));
    }

    public void capTongTienCuoiCa(){
        if(main_controller.getPhieuKetToan() == null
                || main_controller.getPhieuKetToan().getPhieuKiemTienCuoiCa() == null) {
            return;
        }
        lblTongTienKetThucCa.setText(CurrencyFormat.currencyFormat(main_controller.getPhieuKetToan().getPhieuKiemTienCuoiCa().tongTien()));
    }

    public void capNhatTienTrongCa(){
        if(main_controller.getPhieuKetToan() == null
                || main_controller.getPhieuKetToan().getCaLamViec() == null) {
            return;
        }
        dsHoaDon = QuanLyHoaDon_BUS.getDanhSachHoaDonBanVeTheoMaCa(main_controller.getPhieuKetToan().getCaLamViec());

        lblSoLuongHoaDonBanVe.setText(String.valueOf((int)dsHoaDon[0][0]));
        lblTongTienHoaDonBanVe.setText(CurrencyFormat.currencyFormat(dsHoaDon[0][1]));

        lblSoLuongHoaDonDatVe.setText(String.valueOf((int)dsHoaDon[1][0]));
        lblTongTienHoaDonDatVe.setText(CurrencyFormat.currencyFormat(dsHoaDon[1][1]));

        lblSoLuongHoaDonLayVe.setText(String.valueOf((int)dsHoaDon[2][0]));
        lblTongTienHoaDonLayVe.setText(CurrencyFormat.currencyFormat(dsHoaDon[2][1]));

        lblSoLuongHoaDonHuyVe.setText(String.valueOf((int)dsHoaDon[3][0]));
        lblTongTienHoaDonHuyVe.setText(CurrencyFormat.currencyFormat(dsHoaDon[3][1]));

        lblSoLuongHoaDonHuyDatVe.setText(String.valueOf((int)dsHoaDon[4][0]));
        lblTongTienHoaDonHuyDatVe.setText(CurrencyFormat.currencyFormat(dsHoaDon[4][1]));

        lblSoLuongHoaDonDoiVe.setText(String.valueOf((int)dsHoaDon[5][0]));
        lblTongTienHoaDonDoiVe.setText(CurrencyFormat.currencyFormat(dsHoaDon[5][1]));

        main_controller.getPhieuKetToan().setDoanhThuThongKe(tongTienTrongCa());
    }

    public double tongTienTrongCa(){
        if(dsHoaDon == null){
            return 0;
        }
        double tongTien = 0;
        for (int i = 0; i < 6; i++){
            tongTien += dsHoaDon[i][1];
        }
        lblTongTienTrongCa.setText(CurrencyFormat.currencyFormat(tongTien));
        return tongTien;
    }

    public double capNhatTienChenhLech(){
        if(main_controller.getPhieuKetToan() == null
                || main_controller.getPhieuKetToan().getPhieuKiemTienDauCa() == null
                || main_controller.getPhieuKetToan().getPhieuKiemTienCuoiCa() == null) {
            return 0;
        }
        double tienChenhLech = main_controller.getPhieuKetToan().doanhThuThucTe() - tongTienTrongCa();
        lblTongTienChenhLech.setText(CurrencyFormat.currencyFormat(tienChenhLech));
        return tienChenhLech;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            capNhatThongTinCaLamViec();
            capTongTienDauCa();
            capNhatTienTrongCa();
            capTongTienCuoiCa();
            capNhatTienChenhLech();
        });
    }
}
