package GUI.controllers;

import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyKhachHang_BUS;
import BUS.QuanLyVe_BUS;
import DTO.*;
import GUI.controllers.LayVe_GUI_Items.HoaDonDatVe_LayVe_Controller;
import GUI.controllers.LayVe_GUI_Items.VeDat_LayVe_Controller;
import GUI.controllers.LayVe_GUI_Items.Ve_LayVe_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LayVe_GUI_Controller implements Initializable {

    @FXML
    private JFXButton btnBoChonTatCa;

    @FXML
    private JFXButton btnChonTatCa;

    @FXML
    private JFXButton btnChonVeLay;

    @FXML
    private JFXButton btnLayVe;

    @FXML
    private JFXButton btnTimKiemKhachHang;

    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtSoDienThoai;

    @FXML
    private TextField txtTenKhachHang;

    @FXML
    private TextField txtTongTien;

    @FXML
    private VBox vboxDanhSachHoaDonDat;

    @FXML
    private VBox vboxDanhSachVeDat;

    @FXML
    private VBox vboxDanhSachVeLay;

    @FXML
    private JFXButton btnXoaTatCaVe;

    private Main_Controller main_controller;

    private KhachHang khachHang;

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    private ArrayList<HoaDonDatVe_LayVe_Controller> hoaDonDatVe_layVe_controller_list = new ArrayList<HoaDonDatVe_LayVe_Controller>();
    private ArrayList<VeDat_LayVe_Controller> veDat_layVe_controller_list = new ArrayList<VeDat_LayVe_Controller>();
    private ArrayList<Ve_LayVe_Controller> ve_layVe_controller_list = new ArrayList<>();

    private ArrayList<HoaDonDatVe> hoaDonDatVe_list;
    private ArrayList<VeDat> veDatChon_list = new ArrayList<VeDat>();

    public ArrayList<VeDat> getVeDatChon_list() {
        return veDatChon_list;
    }

    public void setVeDatChon_list(ArrayList<VeDat> veDatChon_list) {
        this.veDatChon_list = veDatChon_list;
    }

    private HoaDonDatVe hoaDonDatVeDangChon;
    private HoaDonLayVe hoaDonLayVe;

    public HoaDonLayVe getHoaDonLayVe() {
        return hoaDonLayVe;
    }

    public void setHoaDonLayVe(HoaDonLayVe hoaDonLayVe) {
        this.hoaDonLayVe = hoaDonLayVe;
    }

    public HoaDonDatVe getHoaDonDatVeDangChon() {
        return hoaDonDatVeDangChon;
    }

    public void setHoaDonDatVeDangChon(HoaDonDatVe hoaDonDatVeDangChon) {
        this.hoaDonDatVeDangChon = hoaDonDatVeDangChon;
    }

    @FXML
    void btnBoChonTatCaOnAction(ActionEvent event) {
        veDatChon_list.clear();
        hienThiDanhSachVeDat(hoaDonDatVeDangChon);
    }

    @FXML
    void btnChonTatCaOnAction(ActionEvent event) {
        veDatChon_list.clear();
        veDatChon_list.addAll(hoaDonDatVeDangChon.getDanhSachVeDat());
        hienThiDanhSachVeDat(hoaDonDatVeDangChon);
    }

    @FXML
    void btnLayVeOnAction(ActionEvent event) {
        layVe();
    }

    @FXML
    void btnTimKiemKhachHangOnAction(ActionEvent event) {
        timKhachHang();
        if(khachHang != null)
            layDanhSachHoaDonDatTheoKhachHang();
    }

    public void timKhachHang(){
        String cccd = txtCCCD.getText();
        try {
            khachHang = QuanLyKhachHang_BUS.getKhachHangTheoCCCD(cccd);
            if(khachHang != null){
                txtCCCD.setEditable(false);
            }
            hienThiThongTinKhachHang();
            hoaDonLayVe.setKhachHangLayVe(khachHang);
        } catch (Exception e) {
            main_controller.showMessagesDialog(e.getMessage());
        }
    }

    public void hienThiThongTinKhachHang(){
        txtTenKhachHang.setText(khachHang.getTenKhachHang());
        txtSoDienThoai.setText(khachHang.getSoDienThoai());
    }

    @FXML
    void txtCCCDOnMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            txtCCCD.setEditable(true);
            txtCCCD.selectAll();
            xoaThongTinKhachHang();
            hoaDonDatVe_list.clear();
            hienThiDanhSachHoaDonDat();
            hienThiDanhSachVeDat(null);
            hoaDonLayVe.setKhachHangLayVe(null);
            hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().clear();
            hienThiDanhSachVeLay();
        }
    }

    public void xoaThongTinKhachHang(){
        txtTenKhachHang.setText("");
        txtSoDienThoai.setText("");
        khachHang = null;
    }

    public void layDanhSachHoaDonDatTheoKhachHang(){
        hoaDonDatVe_list = QuanLyHoaDon_BUS.getDanhSachHoaDonDatTheoMaKhachHang(khachHang.getMaKhachHang());
        hienThiDanhSachHoaDonDat();
    }

    public void hienThiDanhSachHoaDonDat(){
        vboxDanhSachHoaDonDat.getChildren().clear();
        if(hoaDonDatVe_list.isEmpty())
            return;
        int length = hoaDonDatVe_list.size();
        for(int i = 0; i < length; i++){
            HoaDonDatVe hoaDonDatVe = hoaDonDatVe_list.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/HoaDonDatVe_LayVe.fxml"));
                Parent anchorPane = loader.load();
                HoaDonDatVe_LayVe_Controller controller = loader.getController();
                hoaDonDatVe_layVe_controller_list.add(controller);
                controller.setLayVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setHoaDonDatVe(hoaDonDatVe);
                controller.khoiTao();

                vboxDanhSachHoaDonDat.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            hoaDonDatVeDangChon = hoaDonDatVe_list.getFirst();
            hienThiDanhSachVeDat(hoaDonDatVe_list.getFirst());
        }
    }

    public void hienThiDanhSachVeDat(HoaDonDatVe hoaDonDatVe){
        veDat_layVe_controller_list.clear();
        vboxDanhSachVeDat.getChildren().clear();
        if(hoaDonDatVe == null || hoaDonDatVe.getDanhSachVeDat().isEmpty()){
            return;
        }

        int length = hoaDonDatVe.getDanhSachVeDat().size();
        for(int i = 0; i < length; i++){
            VeDat veDat = hoaDonDatVe.getDanhSachVeDat().get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/VeDat_LayVe.fxml"));
                Parent anchorPane = loader.load();
                VeDat_LayVe_Controller controller = loader.getController();
                veDat_layVe_controller_list.add(controller);
                controller.setLayVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setVeDat(veDat);
                controller.khoiTao();

               vboxDanhSachVeDat.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void chonVeLay(){
        for(VeDat veDat : veDatChon_list){
            if(!hoaDonLayVe.getDanhSachChiTietHoaDonLayVe()
                    .stream()
                    .anyMatch(chiTietHoaDonLayVe -> chiTietHoaDonLayVe.getVeDat().equals(veDat))
            ){

                String maVeMoi = hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().isEmpty()
                        ? QuanLyVe_BUS.taoMaVeMoi(veDat.getLoaiVe())
                        : QuanLyVe_BUS.taoMaVeTiepTheo(hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().getLast().getVe(), veDat.getLoaiVe());
                Ve ve = new Ve(
                        maVeMoi,
                        null,
                        veDat.getThongTinGaTauDi(),
                        veDat.getThongTinGaTauDen(),
                        veDat.getLoaiVe(),
                        TrangThaiVe.DANGSUDUNG,
                        veDat.getPhanTramGiamGiaVeTapThe()
                );
                ArrayList<ChiTietVe> danhSachChiTietVe = new ArrayList<ChiTietVe>();
                for(ChiTietVeDat chiTietVeDat : veDat.getDanhSachChiTietVeDat()){
                    ChiTietVe chiTietVe = new ChiTietVe(
                            ve,
                            chiTietVeDat.getCho(),
                            chiTietVeDat.getGiaCho(),
                            chiTietVeDat.getKhachHang(),
                            chiTietVeDat.getPhanTramGiamGia()
                    );
                    danhSachChiTietVe.add(chiTietVe);
                }
                ve.setDanhSachChiTietVe(danhSachChiTietVe);

                ChiTietHoaDonLayVe chiTietHoaDonLayVe = new ChiTietHoaDonLayVe(
                        ve,
                        veDat,
                        hoaDonLayVe
                );

                hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().add(chiTietHoaDonLayVe);

            }
        }
        veDatChon_list.clear();
    }

    public void hienThiDanhSachVeLay() {
        vboxDanhSachVeLay.getChildren().clear();
        if (hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().isEmpty())
            return;
        int length = hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().size();
        for (int i = 0; i < length; i++) {
            ChiTietHoaDonLayVe chiTietHoaDonLayVe = hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/Ve_LayVe.fxml"));
                Parent anchorPane = loader.load();
                Ve_LayVe_Controller controller = loader.getController();
                ve_layVe_controller_list.add(controller);
                controller.setLayVe_gui_controller(this);
                controller.setSoThuTu(i);
                controller.setChiTietHoaDonLayVe(chiTietHoaDonLayVe);
                controller.khoiTao();

                vboxDanhSachVeLay.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void hienThiTongTien(){
        txtTongTien.setText(CurrencyFormat.currencyFormat(hoaDonLayVe.tongTienCuoi()));
    }

    private void layVe(){
        if(hoaDonLayVe == null || hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().isEmpty()){
            return;
        }
        hoaDonLayVe.setThoiGianLayVe(LocalDateTime.now());
        if(QuanLyHoaDon_BUS.themHoaDonLayVe(hoaDonLayVe)){
            main_controller.showMessagesDialog("Lấy vé thành công");
            String maHoaDonLayVe = QuanLyHoaDon_BUS.layHoaDonLayVeTiepTheo();
            hoaDonLayVe = new HoaDonLayVe(maHoaDonLayVe);
            hoaDonLayVe.setDanhSachChiTietHoaDonLayVe(new ArrayList<ChiTietHoaDonLayVe>());
            hoaDonLayVe.setCaLamViec(new CaLamViec("CLV13122024C"));
            layDanhSachHoaDonDatTheoKhachHang();
        }else{
            main_controller.showMessagesDialog("Lấy vé thất bại");
        }

    }

    @FXML
    void btnChonVeLayOnAction(ActionEvent event) {
        chonVeLay();
        hienThiDanhSachVeDat(hoaDonDatVeDangChon);
        hienThiDanhSachVeLay();
        hienThiTongTien();
    }


    @FXML
    void btnXoaTatCaVeOnAction(ActionEvent event) {
        hoaDonLayVe.getDanhSachChiTietHoaDonLayVe().clear();
        hienThiDanhSachVeDat(hoaDonDatVeDangChon);
        hienThiDanhSachVeLay();
        hienThiTongTien();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String maHoaDonLayVe = QuanLyHoaDon_BUS.layHoaDonLayVeTiepTheo();
        hoaDonLayVe = new HoaDonLayVe(maHoaDonLayVe);
        hoaDonLayVe.setDanhSachChiTietHoaDonLayVe(new ArrayList<ChiTietHoaDonLayVe>());
        hoaDonLayVe.setCaLamViec(new CaLamViec("CLV13122024C"));

    }
}
