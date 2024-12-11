package GUI.controllers.BaoCao_GUI_Items;

import BUS.QuanLyCaLamViec_BUS;
import BUS.QuanLyNhanVien_BUS;
import DTO.*;
import GUI.controllers.BaoCao_GUI_Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import utils.CurrencyFormat;
import utils.ShowMessagesDialog;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PhieuKiemTien_BaoCao_Controller implements Initializable {

    @FXML
    private JFXButton btnChonNhanVienKiemTien;

    @FXML
    private JFXButton btnChonNhanVienGiamSat;

    @FXML
    private JFXButton btnHoanThanh;

    @FXML
    private JFXButton btnThoat;

    @FXML
    private Label lblTenNhanVienKiemTien;

    @FXML
    private Label lblTenNhanVienGiamSat;

    @FXML
    private Label lblTongTien;

    @FXML
    private TextField txtSoLuongTo1000;

    @FXML
    private TextField txtSoLuongTo10000;

    @FXML
    private TextField txtSoLuongTo100000;

    @FXML
    private TextField txtSoLuongTo2000;

    @FXML
    private TextField txtSoLuongTo20000;

    @FXML
    private TextField txtSoLuongTo200000;

    @FXML
    private TextField txtSoLuongTo5000;

    @FXML
    private TextField txtSoLuongTo50000;

    @FXML
    private TextField txtSoLuongTo500000;

    @FXML
    private TextField txtMaNhanVienKiemTien;

    @FXML
    private TextField txtMaNhanVienGiamSat;

    @FXML
    private StackPane stpThongBao;

    private String loaiPhieuKiemTien;

    private PhieuKiemTien phieuKiemTien;
    private BaoCao_GUI_Controller baoCao_gui_controller;
    private JFXDialog dialog;

    public JFXDialog getDialog() {
        return dialog;
    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }

    public BaoCao_GUI_Controller getBaoCao_gui_controller() {
        return baoCao_gui_controller;
    }

    public void setBaoCao_gui_controller(BaoCao_GUI_Controller baoCao_gui_controller) {
        this.baoCao_gui_controller = baoCao_gui_controller;
    }

    public String getLoaiPhieuKiemTien() {
        return loaiPhieuKiemTien;
    }

    public void setLoaiPhieuKiemTien(String loaiPhieuKiemTien) {
        this.loaiPhieuKiemTien = loaiPhieuKiemTien;
    }


    @FXML
    void btnChonNhanVienGiamSatOnAction(ActionEvent event) {
        try{
            String maNhanVien = txtMaNhanVienGiamSat.getText();
            NhanVien nhanVien = QuanLyNhanVien_BUS.getNhanVienTheoMaNhanVien(maNhanVien);
            phieuKiemTien.setNhanVienGiamSat(nhanVien);
            phieuKiemTien.setNhanVienGiamSat(nhanVien);
            if(nhanVien != null){
                lblTenNhanVienGiamSat.setText(nhanVien.getTenNhanVien());
                txtMaNhanVienGiamSat.setEditable(false);
            }else{
                lblTenNhanVienGiamSat.setText("");
            }
        } catch (Exception e) {
            lblTenNhanVienGiamSat.setText("");
        }
    }

    @FXML
    void btnChonNhanVienKiemTienOnAction(ActionEvent event) {
        try{
            String maNhanVien = txtMaNhanVienKiemTien.getText();
            NhanVien nhanVien = QuanLyNhanVien_BUS.getNhanVienTheoMaNhanVien(maNhanVien);
            phieuKiemTien.setNhanVienKiemTien(nhanVien);
            if(nhanVien != null){
                lblTenNhanVienKiemTien.setText(nhanVien.getTenNhanVien());
                txtMaNhanVienKiemTien.setEditable(false);
            }else{
                lblTenNhanVienKiemTien.setText("");
            }

        } catch (Exception e) {
            lblTenNhanVienKiemTien.setText("");
            e.printStackTrace();
        }
    }

    @FXML
    void txtMaNhanVienGiamSatOnMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            txtMaNhanVienGiamSat.setEditable(true);
            txtMaNhanVienGiamSat.selectAll();
            lblTenNhanVienGiamSat.setText("");
        }
    }

    @FXML
    void txtMaNhanVienKiemTienOnMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            txtMaNhanVienKiemTien.setEditable(true);
            txtMaNhanVienKiemTien.selectAll();
            lblTenNhanVienKiemTien.setText("");
        }
    }

    @FXML
    void btnHoanThanhOnAction(ActionEvent event) {
        taoPhieuKiemTien();
    }
    @FXML
    void btnThoatOnAction(ActionEvent event) {
        if(dialog != null) dialog.close();
    }

    public void setNhanVienKiemTien(NhanVien nhanVien){
        phieuKiemTien.setNhanVienKiemTien(nhanVien);
        if(nhanVien != null){
            txtMaNhanVienKiemTien.setText(nhanVien.getMaNhanVien());
            lblTenNhanVienKiemTien.setText(nhanVien.getTenNhanVien());
            txtMaNhanVienKiemTien.setEditable(false);
        }else{
            txtMaNhanVienKiemTien.setEditable(true);
            lblTenNhanVienKiemTien.setText("");
            txtMaNhanVienKiemTien.setText("");
        }

    }



    private void taoPhieuKiemTien(){
        System.out.println(loaiPhieuKiemTien);
        if(phieuKiemTien.getNhanVienKiemTien() == null){
            ShowMessagesDialog.showDialog(stpThongBao, "Thông báo", "Thiếu thông tin nhân viên kiểm tiền", "OK");
            return;
        }
        if(phieuKiemTien.getNhanVienGiamSat() == null){
            ShowMessagesDialog.showDialog(stpThongBao, "Thông báo", "Thiếu thông tin nhân viên giám sát", "OK");
            return;
        }
        if(loaiPhieuKiemTien.equals("DC")){
            String maCaLamViec = QuanLyCaLamViec_BUS.taoMaCaLamViecMoi();
            CaLamViec caLamViec = new CaLamViec(maCaLamViec, LocalDateTime.now(), null, baoCao_gui_controller.getMain_controller().getNhanVien());
            System.out.println(caLamViec.getMaCaLamViec());

            PhieuKetToan phieuKetToan = new PhieuKetToan("PKTO" + maCaLamViec.substring(3), caLamViec, LocalDateTime.now());
            phieuKiemTien.setMaPhieuKiemTien("PKTI" + maCaLamViec.substring(3) + loaiPhieuKiemTien);
            phieuKiemTien.setThoiGianKiemTien(LocalDateTime.now());
            phieuKetToan.setPhieuKiemTienDauCa(phieuKiemTien);
            if(!QuanLyCaLamViec_BUS.taoCaLamViecMoi(phieuKetToan))
                return;

            baoCao_gui_controller.getMain_controller().setPhieuKetToan(phieuKetToan);
            baoCao_gui_controller.capNhatThongTinCaLamViec();
            baoCao_gui_controller.capTongTienDauCa();
            baoCao_gui_controller.getMain_controller().showMessagesDialog("Tạo phiếu kiểm tiền đầu ca thành công");
        }

        if(loaiPhieuKiemTien.equals("CC")){
            PhieuKetToan phieuKetToan = baoCao_gui_controller.getMain_controller().getPhieuKetToan();
            String maCaLamViec = phieuKetToan.getCaLamViec().getMaCaLamViec();
            phieuKiemTien.setMaPhieuKiemTien("PKTI" + maCaLamViec.substring(3) + loaiPhieuKiemTien);
            phieuKiemTien.setThoiGianKiemTien(LocalDateTime.now());
            phieuKetToan.setPhieuKiemTienCuoiCa(phieuKiemTien);
            if(!QuanLyCaLamViec_BUS.capNhatPhieuKetToan_CuoiCa(phieuKetToan))
                return;


            baoCao_gui_controller.getMain_controller().setPhieuKetToan(phieuKetToan);
            baoCao_gui_controller.capNhatThongTinCaLamViec();
            baoCao_gui_controller.capTongTienCuoiCa();
            baoCao_gui_controller.capNhatTienChenhLech();
            baoCao_gui_controller.getMain_controller().showMessagesDialog("Tạo phiếu kiểm tiền cuối ca thành công");
        }

        if(dialog != null) dialog.close();
    }



    private void khoiTaoPhieuKiemTien(){
        ArrayList<ChiTietPhieuKiemTien> dsChiTietPhieuKiemTien = new ArrayList<>();

        ChiTietPhieuKiemTien chiTietPhieuKiemTienTo1000 = new ChiTietPhieuKiemTien(
                MenhGia.T1000VND,
                1000,
                Integer.valueOf(txtSoLuongTo1000.getText()),
                phieuKiemTien
        );
        dsChiTietPhieuKiemTien.add(chiTietPhieuKiemTienTo1000);

        ChiTietPhieuKiemTien chiTietPhieuKiemTienTo2000 = new ChiTietPhieuKiemTien(
                MenhGia.T2000VND,
                2000,
                Integer.valueOf(txtSoLuongTo2000.getText()),
                phieuKiemTien
        );
        dsChiTietPhieuKiemTien.add(chiTietPhieuKiemTienTo2000);

        ChiTietPhieuKiemTien chiTietPhieuKiemTienTo5000 = new ChiTietPhieuKiemTien(
                MenhGia.T5000VND,
                5000,
                Integer.valueOf(txtSoLuongTo5000.getText()),
                phieuKiemTien
        );
        dsChiTietPhieuKiemTien.add(chiTietPhieuKiemTienTo5000);

        ChiTietPhieuKiemTien chiTietPhieuKiemTienTo10000 = new ChiTietPhieuKiemTien(
                MenhGia.T10000VND,
                10000,
                Integer.valueOf(txtSoLuongTo10000.getText()),
                phieuKiemTien
        );
        dsChiTietPhieuKiemTien.add(chiTietPhieuKiemTienTo10000);

        ChiTietPhieuKiemTien chiTietPhieuKiemTienTo20000 = new ChiTietPhieuKiemTien(
                MenhGia.T20000VND,
                20000,
                Integer.valueOf(txtSoLuongTo20000.getText()),
                phieuKiemTien
        );
        dsChiTietPhieuKiemTien.add(chiTietPhieuKiemTienTo20000);

        ChiTietPhieuKiemTien chiTietPhieuKiemTienTo50000 = new ChiTietPhieuKiemTien(
                MenhGia.T50000VND,
                50000,
                Integer.valueOf(txtSoLuongTo50000.getText()),
                phieuKiemTien
        );
        dsChiTietPhieuKiemTien.add(chiTietPhieuKiemTienTo50000);

        ChiTietPhieuKiemTien chiTietPhieuKiemTienTo100000 = new ChiTietPhieuKiemTien(
                MenhGia.T100000VND,
                100000,
                Integer.valueOf(txtSoLuongTo100000.getText()),
                phieuKiemTien
        );
        dsChiTietPhieuKiemTien.add(chiTietPhieuKiemTienTo100000);

        ChiTietPhieuKiemTien chiTietPhieuKiemTienTo200000 = new ChiTietPhieuKiemTien(
                MenhGia.T200000VND,
                200000,
                Integer.valueOf(txtSoLuongTo200000.getText()),
                phieuKiemTien
        );
        dsChiTietPhieuKiemTien.add(chiTietPhieuKiemTienTo200000);

        ChiTietPhieuKiemTien chiTietPhieuKiemTienTo500000 = new ChiTietPhieuKiemTien(
                MenhGia.T500000VND,
                500000,
                Integer.valueOf(txtSoLuongTo500000.getText()),
                phieuKiemTien
        );
        dsChiTietPhieuKiemTien.add(chiTietPhieuKiemTienTo500000);

        phieuKiemTien.setDanhSachChiTietPhieuKiemTien(dsChiTietPhieuKiemTien);
    }

    private void hienThiTongTienPhieuKiemTien(){
        lblTongTien.setText(CurrencyFormat.currencyFormat(phieuKiemTien.tongTien()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        phieuKiemTien = new PhieuKiemTien();
        phieuKiemTien.setDanhSachChiTietPhieuKiemTien(new ArrayList<>());
        txtSoLuongTo1000.setText("0");
        txtSoLuongTo1000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo1000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo1000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo1000.setText(trimmedValue);
            }
            try{
                int soLuong = Integer.valueOf(txtSoLuongTo1000.getText());
                phieuKiemTien.getDanhSachChiTietPhieuKiemTien().stream()
                        .filter(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.getMenhGia().equals(MenhGia.T1000VND))
                        .forEach(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.setSoLuong(soLuong));
                hienThiTongTienPhieuKiemTien();
            } catch (Exception e) {

            }
        });


        txtSoLuongTo2000.setText("0");
        txtSoLuongTo2000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo2000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo2000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo2000.setText(trimmedValue);
            }
            try{
                int soLuong = Integer.valueOf(txtSoLuongTo2000.getText());
                phieuKiemTien.getDanhSachChiTietPhieuKiemTien().stream()
                        .filter(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.getMenhGia().equals(MenhGia.T2000VND))
                        .forEach(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.setSoLuong(soLuong));
                hienThiTongTienPhieuKiemTien();
            } catch (Exception e) {

            }

        });

        txtSoLuongTo5000.setText("0");
        txtSoLuongTo5000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo5000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo5000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo5000.setText(trimmedValue);
            }
            try{

            } catch (Exception e) {
                int soLuong = Integer.valueOf(txtSoLuongTo5000.getText());
                phieuKiemTien.getDanhSachChiTietPhieuKiemTien().stream()
                        .filter(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.getMenhGia().equals(MenhGia.T5000VND))
                        .forEach(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.setSoLuong(soLuong));
                hienThiTongTienPhieuKiemTien();
            }

        });


        txtSoLuongTo10000.setText("0");
        txtSoLuongTo10000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo10000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo10000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo10000.setText(trimmedValue);
            }
            try{
                int soLuong = Integer.valueOf(txtSoLuongTo10000.getText());
                phieuKiemTien.getDanhSachChiTietPhieuKiemTien().stream()
                        .filter(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.getMenhGia().equals(MenhGia.T10000VND))
                        .forEach(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.setSoLuong(soLuong));
                hienThiTongTienPhieuKiemTien();
            } catch (Exception e) {

            }

        });


        txtSoLuongTo20000.setText("0");
        txtSoLuongTo20000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo20000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo20000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo20000.setText(trimmedValue);
            }
            try{
                int soLuong = Integer.valueOf(txtSoLuongTo20000.getText());
                phieuKiemTien.getDanhSachChiTietPhieuKiemTien().stream()
                        .filter(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.getMenhGia().equals(MenhGia.T20000VND))
                        .forEach(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.setSoLuong(soLuong));
                hienThiTongTienPhieuKiemTien();
            } catch (Exception e) {

            }

        });


        txtSoLuongTo50000.setText("0");
        txtSoLuongTo50000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo50000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo50000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo50000.setText(trimmedValue);
            }
            try{
                int soLuong = Integer.valueOf(txtSoLuongTo50000.getText());
                phieuKiemTien.getDanhSachChiTietPhieuKiemTien().stream()
                        .filter(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.getMenhGia().equals(MenhGia.T50000VND))
                        .forEach(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.setSoLuong(soLuong));
                hienThiTongTienPhieuKiemTien();
            } catch (Exception e) {

            }

        });


        txtSoLuongTo100000.setText("0");
        txtSoLuongTo100000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo100000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo100000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo100000.setText(trimmedValue);
            }
            try{
                int soLuong = Integer.valueOf(txtSoLuongTo100000.getText());
                phieuKiemTien.getDanhSachChiTietPhieuKiemTien().stream()
                        .filter(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.getMenhGia().equals(MenhGia.T100000VND))
                        .forEach(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.setSoLuong(soLuong));
                hienThiTongTienPhieuKiemTien();
            } catch (Exception e) {

            }

        });


        txtSoLuongTo200000.setText("0");
        txtSoLuongTo200000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo200000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo200000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo200000.setText(trimmedValue);
            }
            try{
                int soLuong = Integer.valueOf(txtSoLuongTo200000.getText());
                phieuKiemTien.getDanhSachChiTietPhieuKiemTien().stream()
                        .filter(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.getMenhGia().equals(MenhGia.T200000VND))
                        .forEach(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.setSoLuong(soLuong));
                hienThiTongTienPhieuKiemTien();
            } catch (Exception e) {

            }

        });


        txtSoLuongTo500000.setText("0");
        txtSoLuongTo500000.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSoLuongTo500000.setText(oldValue);
                return;
            }
            String trimmedValue = newValue.replaceFirst("^0+", "");
            if (trimmedValue.isEmpty()) {
                trimmedValue = "0";
            }
            if (newValue.isEmpty()) {
                txtSoLuongTo500000.setText("0");
                return;
            }
            if (!newValue.equals(trimmedValue)) {
                txtSoLuongTo500000.setText(trimmedValue);
            }
            try{
                int soLuong = Integer.valueOf(txtSoLuongTo500000.getText());
                phieuKiemTien.getDanhSachChiTietPhieuKiemTien().stream()
                        .filter(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.getMenhGia().equals(MenhGia.T500000VND))
                        .forEach(chiTietPhieuKiemTien -> chiTietPhieuKiemTien.setSoLuong(soLuong));
                hienThiTongTienPhieuKiemTien();
            } catch (Exception e) {

            }

        });

        khoiTaoPhieuKiemTien();
        hienThiTongTienPhieuKiemTien();
    }
}
