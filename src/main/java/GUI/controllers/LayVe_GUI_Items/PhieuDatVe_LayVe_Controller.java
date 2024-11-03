package GUI.controllers.LayVe_GUI_Items;

import DTO.LoaiPhieuDatVe;
import DTO.PhieuDatVe;
import DTO.TrangThaiPhieuDatVe;
import GUI.controllers.LayVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class PhieuDatVe_LayVe_Controller implements Initializable {

    @FXML
    private AnchorPane anpChonPhieuDatVe;

    @FXML
    private AnchorPane anpPhieuDatVe;

    @FXML
    private HBox hboxGiaVeCuoi;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

    @FXML
    private ImageView imvChonPhieuDatVe;

    @FXML
    private Label lblGiaVe;

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
    private Label lblTienCoc;

    @FXML
    private VBox vboxDanhSachThongTin;

    @FXML
    private ImageView imvTrangThai;

    private LayVe_GUI_Controller layVe_gui_controller;


    private PhieuDatVe phieuDatVe;
    private int soThuTu;
    private boolean dangChon;
    private boolean chonLayVe;

    public LayVe_GUI_Controller getLayVe_gui_controller() {
        return layVe_gui_controller;
    }

    public void setLayVe_gui_controller(LayVe_GUI_Controller layVe_gui_controller) {
        this.layVe_gui_controller = layVe_gui_controller;
    }

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

    public PhieuDatVe getPhieuDatVe() {
        return phieuDatVe;
    }

    public void setPhieuDatVe(PhieuDatVe phieuDatVe) {
        this.phieuDatVe = phieuDatVe;
    }

    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        layVe_gui_controller.hienThiDanhSachChiTietPhieuDatVe(phieuDatVe);
        chonPhieuDatVe();
        if(phieuDatVe.getTrangThaiPhieuDatVe().equals(TrangThaiPhieuDatVe.CHOLAYVE)){
            if(chonLayVe){
                chonLayVe();
            }else{
                boChonLayVe();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(){
        lblSTT.setText(String.valueOf(soThuTu + 1));
        lblMaChuyenTau.setText(phieuDatVe.getChiTietChuyenTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(phieuDatVe.getChiTietChuyenTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(phieuDatVe.getChiTietChuyenTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(phieuDatVe.getChiTietChuyenTauDi().getThoiGianDi()));
        lblGiaVe.setText(CurrencyFormat.currencyFormat(phieuDatVe.getTongTienVe()));
        lblTienCoc.setText(CurrencyFormat.currencyFormat(phieuDatVe.getTongTienDatCoc()));
        anpPhieuDatVe.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/PhieuDatVe_LayVe.css").toExternalForm());
        anpChonPhieuDatVe.getStylesheets().add(getClass().getResource("/css/LayVe_GUI_Items/PhieuDatVe_LayVe.css").toExternalForm());


        if(phieuDatVe.getLoaiPhieuDatVe().equals(LoaiPhieuDatVe.PHIEUDATCANHAN)){
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            vboxDanhSachThongTin.getChildren().remove(hboxGiaVeCuoi);
            anpChonPhieuDatVe.getStyleClass().add("phieuDatVe-left-caNhan");
        }else if(phieuDatVe.getLoaiPhieuDatVe().equals(LoaiPhieuDatVe.PHIEUDATTAPTHE)){
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(phieuDatVe.tinhGiamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(phieuDatVe.tinhTongTienVeCuoi()));
            anpChonPhieuDatVe.getStyleClass().add("phieuDatVe-left-tapThe");
        }

        if(phieuDatVe.getTrangThaiPhieuDatVe().equals(TrangThaiPhieuDatVe.CHOLAYVE)){
            imvTrangThai.setImage(new Image(getClass().getResourceAsStream("/images/LayVe_GUI/TrangThaiPhieuDatVe/ChoLayVe.png")));
        }else if(phieuDatVe.getTrangThaiPhieuDatVe().equals(TrangThaiPhieuDatVe.DALAYVE)){
            imvTrangThai.setImage(new Image(getClass().getResourceAsStream("/images/LayVe_GUI/TrangThaiPhieuDatVe/DaLayVe.png")));
        }else if(phieuDatVe.getTrangThaiPhieuDatVe().equals(TrangThaiPhieuDatVe.DAHUY)){
            imvTrangThai.setImage(new Image(getClass().getResourceAsStream("/images/LayVe_GUI/TrangThaiPhieuDatVe/DaHuy.png")));
        }

        imvChonPhieuDatVe.setVisible(false);
    }

    public void chonPhieuDatVe(){
        layVe_gui_controller.boChonTatCaPhieuDatVe();
        dangChon = true;
        anpPhieuDatVe.getStyleClass().removeAll("phieuDatVeKhongChon");
        anpPhieuDatVe.getStyleClass().add("phieuDatVeDangChon");
    }

    public void boChonPhieuDatVe(){
        dangChon = false;
        anpPhieuDatVe.getStyleClass().removeAll("phieuDatVeDangChon");
        anpPhieuDatVe.getStyleClass().add("phieuDatVeKhongChon");
    }

    public void chonLayVe(){
        if(phieuDatVe.getTrangThaiPhieuDatVe().equals(TrangThaiPhieuDatVe.CHOLAYVE)){
            chonLayVe = true;
            imvChonPhieuDatVe.setVisible(true);
        }
    }

    public void boChonLayVe(){
        chonLayVe = false;
        imvChonPhieuDatVe.setVisible(false);
    }
}
