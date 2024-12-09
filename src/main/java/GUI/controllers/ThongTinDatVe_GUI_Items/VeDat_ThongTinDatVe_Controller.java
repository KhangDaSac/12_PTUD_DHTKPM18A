package GUI.controllers.ThongTinDatVe_GUI_Items;

import DTO.ChiTietVeDat;
import DTO.LoaiVe;
import DTO.VeDat;
import GUI.controllers.ThongTinDatVe_GUI_Controller;
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

public class VeDat_ThongTinDatVe_Controller implements Initializable {
    @FXML
    private AnchorPane anpVeDat;

    @FXML
    private AnchorPane anpXoaVeDat;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

    @FXML
    private Label lblGiaVeCuoi;

    @FXML
    private Label lblTienDatCoc;

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
    private VBox vboxDanhSachChoVeTapThe;

    @FXML
    private VBox vboxDanhSachThongTin;

    private int soThuTu;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    private VeDat veDat;
    private ThongTinDatVe_GUI_Controller thongTinDatVe_gui_controller;

    public ThongTinDatVe_GUI_Controller getThongTinDatVe_gui_controller() {
        return thongTinDatVe_gui_controller;
    }

    public void setThongTinDatVe_gui_controller(ThongTinDatVe_GUI_Controller thongTinDatVe_gui_controller) {
        this.thongTinDatVe_gui_controller = thongTinDatVe_gui_controller;
    }

    public VeDat getVeDat() {
        return veDat;
    }

    public void setVeDat(VeDat veDat) {
        this.veDat = veDat;
    }

    private ArrayList<ChiTietVeDat_ThongTinDatVe_Controller> chiTietVeDat_thongTinDatVe_controller_list = new ArrayList<ChiTietVeDat_ThongTinDatVe_Controller>();


    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        chonVeDat();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void khoiTao() throws IOException {
        anpVeDat.getStylesheets().add(getClass().getResource("/css/ThongTinDatVe_GUI_Items/VeDat_ThongTinDatVe.css").toExternalForm());
        lblMaChuyenTau.setText(veDat.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(veDat.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(veDat.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(veDat.getThongTinGaTauDi().getThoiGianDi()));
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        lblTienDatCoc.setText(CurrencyFormat.currencyFormat(veDat.tienDatCoc()));
        lblSTT.setText(String.valueOf(soThuTu + 1));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        for(ChiTietVeDat chiTietVeDat : veDat.getDanhSachChiTietVeDat()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinDatVe_GUI_Items/ChiTietVeDat_ThongTinDatVe.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVeDat_ThongTinDatVe_Controller controller = loader.getController();
            chiTietVeDat_thongTinDatVe_controller_list.add(controller);
            controller.setThongTinDatVe_gui_controller(thongTinDatVe_gui_controller);
            controller.setChiTietVeDat(chiTietVeDat);
            controller.khoiTao();
            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        chiTietVeDat_thongTinDatVe_controller_list.getLast().chiTietVeCuoi();

        if(veDat.getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThongTin.setMinHeight(420);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVeDat.getStyleClass().add("veDat-left-veCaNhan");

        }else if(veDat.getLoaiVe() == LoaiVe.VETAPTHE){
            vboxDanhSachThongTin.setMinHeight(210 + veDat.getDanhSachChiTietVeDat().size() * 260);
            anpXoaVeDat.getStyleClass().add("veDat-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(veDat.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(veDat.tienVeCuoi()));
        }
    }

    public void chonVeDat(){
        thongTinDatVe_gui_controller.boChonTatCaVe();
        anpVeDat.getStyleClass().add("veDatDangChon");
        anpVeDat.getStyleClass().removeAll("veDatKhongChon");
    }

    public void khongChonVeDat(){
        anpVeDat.getStyleClass().removeAll("veDatDangChon");
        anpVeDat.getStyleClass().add("veDatKhongChon");
    }

}
