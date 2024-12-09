package GUI.controllers.LayVe_GUI_Items;

import DTO.ChiTietHoaDonLayVe;
import DTO.ChiTietVe;
import DTO.LoaiVe;
import DTO.Ve;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.BanVe_GUI_Items.ChiTietVe_BanVe_Controller;
import GUI.controllers.LayVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Ve_LayVe_Controller implements Initializable {
    @FXML
    private AnchorPane anpVe;

    @FXML
    private AnchorPane anpXoaVe;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

    @FXML
    private Label lblDaCoc;

    @FXML
    private Label lblGiaVeCuoi;

    @FXML
    private Label lblGiamGiaVeTapThe;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblSTT;

    @FXML
    private Label lblTenGaDen;

    @FXML
    private Label lblTenGaDi;

    @FXML
    private Label lblThoiGianDi;

    @FXML
    private Label lblTienConLai;

    @FXML
    private VBox vboxDanhSachChoVeTapThe;

    @FXML
    private VBox vboxDanhSachThongTin;

    private ChiTietHoaDonLayVe chiTietHoaDonLayVe;

    private LayVe_GUI_Controller layVe_gui_controller;

    public LayVe_GUI_Controller getLayVe_gui_controller() {
        return layVe_gui_controller;
    }

    public void setLayVe_gui_controller(LayVe_GUI_Controller layVe_gui_controller) {
        this.layVe_gui_controller = layVe_gui_controller;
    }

    public ChiTietHoaDonLayVe getChiTietHoaDonLayVe() {
        return chiTietHoaDonLayVe;
    }

    public void setChiTietHoaDonLayVe(ChiTietHoaDonLayVe chiTietHoaDonLayVe) {
        this.chiTietHoaDonLayVe = chiTietHoaDonLayVe;
    }

    private int soThuTu;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    private ArrayList<ChiTietVe_LayVe_Controller> chiTietVe_layVe_controller_list = new ArrayList<ChiTietVe_LayVe_Controller>();


    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        chonVe();
    }

    @FXML
    void anpXoaVeOnMouseClicked(MouseEvent event) {
        layVe_gui_controller.getHoaDonLayVe().getDanhSachChiTietHoaDonLayVe().removeIf(
                cthdlv -> cthdlv.equals(chiTietHoaDonLayVe)
        );
        layVe_gui_controller.hienThiDanhSachVeLay();
        layVe_gui_controller.hienThiDanhSachVeDat(layVe_gui_controller.getHoaDonDatVeDangChon());
        layVe_gui_controller.hienThiTongTien();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao() throws IOException {
        anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve_BanVe.css").toExternalForm());
        lblMaChuyenTau.setText(chiTietHoaDonLayVe.getVe().getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(chiTietHoaDonLayVe.getVe().getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(chiTietHoaDonLayVe.getVe().getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(chiTietHoaDonLayVe.getVe().getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVe().tienVeCuoi()));
        lblDaCoc.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVeDat().tienDatCoc()));
        lblTienConLai.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.thanhTien()));
        lblSTT.setText(String.valueOf(soThuTu + 1));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        for(ChiTietVe chiTietVe : chiTietHoaDonLayVe.getVe().getDanhSachChiTietVe()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI_Items/ChiTietVe_LayVe.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVe_LayVe_Controller controller = loader.getController();
            chiTietVe_layVe_controller_list.add(controller);
            controller.setChiTietVe(chiTietVe);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVe_layVe_controller_list.getLast().chiTietVeCuoi();

        if(chiTietHoaDonLayVe.getVe().getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThongTin.setMinHeight(450);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVe.getStyleClass().add("ve-left-veCaNhan");

        }else if(chiTietHoaDonLayVe.getVe().getLoaiVe() == LoaiVe.VETAPTHE){
            vboxDanhSachThongTin.setMinHeight(240 + chiTietHoaDonLayVe.getVe().getDanhSachChiTietVe().size() * 260);
            anpXoaVe.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVe().giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(chiTietHoaDonLayVe.getVe().tienVeCuoi()));
        }
    }

    public void chonVe(){
            //banVe_GUI_Controller.boChonTatCaVe();
            anpVe.getStyleClass().add("veDangChon");
            anpVe.getStyleClass().removeAll("veKhongChon");

    }

    public void khongChonVe(){
        anpVe.getStyleClass().removeAll("veDangChon");
        anpVe.getStyleClass().add("veKhongChon");
    }



}
