package GUI.controllers.BanVe_GUI_Items;

import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.DoiVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.TimeFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class ChuyenTau_Controller implements Initializable {
    public ImageView getImvChuyenTau() {
        return imvChuyenTau;
    }

    public void setImvChuyenTau(ImageView imvChuyenTau) {
        this.imvChuyenTau = imvChuyenTau;
    }

    public Label getLblChangDaiHon() {
        return lblChangDaiHon;
    }

    public void setLblChangDaiHon(Label lblChangDaiHon) {
        this.lblChangDaiHon = lblChangDaiHon;
    }

    public Label getLblConTrong() {
        return lblConTrong;
    }

    public void setLblConTrong(Label lblConTrong) {
        this.lblConTrong = lblConTrong;
    }

    public Label getLblDaDatVeBan() {
        return lblDaDatVeBan;
    }

    public void setLblDaDatVeBan(Label lblDaDatVeBan) {
        this.lblDaDatVeBan = lblDaDatVeBan;
    }

    public Label getLblMaChuyenTau() {
        return lblMaChuyenTau;
    }

    public void setLblMaChuyenTau(Label lblMaChuyenTau) {
        this.lblMaChuyenTau = lblMaChuyenTau;
    }

    public Label getLblThoiGianDen() {
        return lblThoiGianDen;
    }

    public void setLblThoiGianDen(Label lblThoiGianDen) {
        this.lblThoiGianDen = lblThoiGianDen;
    }

    public Label getLblThoiGianDi() {
        return lblThoiGianDi;
    }

    public void setLblThoiGianDi(Label lblThoiGianDi) {
        this.lblThoiGianDi = lblThoiGianDi;
    }

    public static String trang;

    public BanVe_GUI_Controller getBanVe_GUI_Controller() {
        return banVe_GUI_Controller;
    }

    public void setBanVe_GUI_Controller(BanVe_GUI_Controller banVe_GUI_Controller) {
        this.banVe_GUI_Controller = banVe_GUI_Controller;
    }

    public  DoiVe_GUI_Controller getDoiVe_gui_controller() {return  doiVe_gui_controller;}
    public void setDoiVe_gui_controller(DoiVe_GUI_Controller doiVe_gui_controller) {
        this.doiVe_gui_controller = doiVe_gui_controller;
    }

    public static void loaiTrang(String link){
        trang = link;
    }

    @FXML
    private ImageView imvChuyenTau;

    @FXML
    private Label lblChangDaiHon;

    @FXML
    private Label lblConTrong;

    @FXML
    private Label lblDaDatVeBan;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblThoiGianDen;

    @FXML
    private Label lblThoiGianDi;

    private BanVe_GUI_Controller banVe_GUI_Controller;
    private DoiVe_GUI_Controller doiVe_gui_controller;
    private ChuyenTau chuyenTau;
    private ChiTietChuyenTau chiTietChuyenTauDi;
    private ChiTietChuyenTau chiTietChuyenTauDen;

    public ChiTietChuyenTau getChiTietChuyenTauDi() {
        return chiTietChuyenTauDi;
    }

    public void setChiTietChuyenTauDi(ChiTietChuyenTau chiTietChuyenTauDi) {
        this.chiTietChuyenTauDi = chiTietChuyenTauDi;
    }

    public ChiTietChuyenTau getChiTietChuyenTauDen() {
        return chiTietChuyenTauDen;
    }

    public void setChiTietChuyenTauDen(ChiTietChuyenTau chiTietChuyenTauDen) {
        this.chiTietChuyenTauDen = chiTietChuyenTauDen;
    }

    private int soThuTu;

    public ChuyenTau getChuyenTau() {
        return chuyenTau;
    }

    public void setChuyenTau(ChuyenTau chuyenTau) {
        this.chuyenTau = chuyenTau;

    }


    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    @FXML
    void anpChuyenTauOnMouseClicked(MouseEvent event) {
        if(trang.equals("BanVe_GUI.fxml")){
            chonChuyenTau();}
        else if(trang.equals("DoiVe_GUI.fxml")){
            chon1ChuyenTau();
        }else {
            System.out.printf("trang không xác định");
        }
    }

    public void chinhMauKhongChon(){
        if(chuyenTau.getSoLuongChoTrongTrong() >= 0){
            imvChuyenTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-gray.png")));
        }else{
            imvChuyenTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-red.png")));
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(){
        if(chuyenTau.getSoLuongChoTrongTrong() >= 0){
            imvChuyenTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-gray.png")));
        }else{
            imvChuyenTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-red.png")));
        }
        lblMaChuyenTau.setText(chuyenTau.getMaChuyenTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(chiTietChuyenTauDi.getThoiGianDi()));
        lblThoiGianDen.setText(TimeFormat.formatLocalDateTime(chiTietChuyenTauDen.getThoiGianDen()));
        lblDaDatVeBan.setText(String.valueOf(chuyenTau.getSoLuongChoDaBanVaDat()));
        lblChangDaiHon.setText(String.valueOf(chuyenTau.getSoLuongChoChangDaiHon()));
        lblConTrong.setText(String.valueOf(chuyenTau.getSoLuongChoTrongTrong()));
    }

    public void chonChuyenTau(){
        banVe_GUI_Controller.setChuyenTauDangChon(soThuTu);
        banVe_GUI_Controller.timDanhSachToaTau(lblMaChuyenTau.getText());
        banVe_GUI_Controller.boChonTatCaChuyenTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-green.png"));
        imvChuyenTau.setImage(image);
    }
    public void chon1ChuyenTau(){
        if (doiVe_gui_controller != null && lblMaChuyenTau.getText() != null) {
            doiVe_gui_controller.timDanhSachToaTau(lblMaChuyenTau.getText());
            imvChuyenTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-green.png")));
        } else {
            System.err.println("Không thể chọn chuyến tàu - thông tin thiếu!");
        }
    }

}
