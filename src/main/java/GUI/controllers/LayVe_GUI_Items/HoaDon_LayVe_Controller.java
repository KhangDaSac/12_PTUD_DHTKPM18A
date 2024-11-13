package GUI.controllers.LayVe_GUI_Items;

import DTO.HoaDonBanVe;
import DTO.TrangThaiHoaDonDat;
import GUI.controllers.LayVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.CurrencyFormat;
import utils.TimeFormat;

public class HoaDon_LayVe_Controller {

    @FXML
    private AnchorPane anpHoaDon;

    @FXML
    private Label lblSoThuTu;

    @FXML
    private Label lblMaCaLam;

    @FXML
    private Label lblMaHoaDon;

    @FXML
    private Label lblThoiGianLap;

    @FXML
    private Label lblTienCoc;

    @FXML
    private Label lblTongTien;

    @FXML
    private Label lblTrangThai;

    private HoaDonBanVe hoaDon;
    private LayVe_GUI_Controller layVe_gui_controller;
    private int soThuTu;
    private boolean dangChon;

    public boolean isDangChon() {
        return dangChon;
    }

    public void setDangChon(boolean dangChon) {
        this.dangChon = dangChon;
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public LayVe_GUI_Controller getLayVe_gui_controller() {
        return layVe_gui_controller;
    }

    public void setLayVe_gui_controller(LayVe_GUI_Controller layVe_gui_controller) {
        this.layVe_gui_controller = layVe_gui_controller;
    }

    public HoaDonBanVe getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDonBanVe hoaDon) {
        this.hoaDon = hoaDon;
    }


    public void khoiTao(){
        lblMaHoaDon.setText(hoaDon.getMaHoaDon());
        lblThoiGianLap.setText(TimeFormat.formatLocalDateTime(hoaDon.getThoiGianLap()));
        lblMaCaLam.setText(hoaDon.getCaLamViec().getMaCaLamViec());
        lblTongTien.setText(CurrencyFormat.currencyFormat(hoaDon.getTongTien()));
        lblTienCoc.setText(CurrencyFormat.currencyFormat(hoaDon.getTongTienDaDatCoc()));
        lblSoThuTu.setText(String.valueOf(soThuTu + 1));
        dangChon = false;
        if(hoaDon.getTrangThaiHoaDon().equals(TrangThaiHoaDonDat.CHOLAYVE)){
            lblTrangThai.setText("Chờ lấy vé");
        }
        anpHoaDon.getStylesheets().add(getClass().getResource("/css/LayVe_GUI.css").toExternalForm());
    }


    @FXML
    void anpHoaDonOnMouseClicked(MouseEvent event) {
        layVe_gui_controller.boChonTatCaHoaDon();
        chonHoaDon();

        layVe_gui_controller.getDanhSachPhieuDatVeTheoMaHoaDon(hoaDon.getMaHoaDon());
        layVe_gui_controller.getDanhSachChiTietPhieuDatVeTheoMaHoaDon(hoaDon.getMaHoaDon());
        layVe_gui_controller.capNhatDanhSachPhieuDatVe();
    }

    public void chonHoaDon(){
        dangChon = true;
        anpHoaDon.getStyleClass().removeAll("hoaDonKhongChon");
        anpHoaDon.getStyleClass().add("hoaDonChon");
    }

    public void boChonHoaDon(){
        dangChon = false;
        anpHoaDon.getStyleClass().removeAll("hoaDonChon");
        anpHoaDon.getStyleClass().add("hoaDonKhongChon");
    }

}
