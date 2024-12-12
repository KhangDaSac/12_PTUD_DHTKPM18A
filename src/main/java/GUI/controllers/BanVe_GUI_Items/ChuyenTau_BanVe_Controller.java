package GUI.controllers.BanVe_GUI_Items;

import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.TimeFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class ChuyenTau_BanVe_Controller implements Initializable {
    public BanVe_GUI_Controller getBanVe_GUI_Controller() {
        return banVe_GUI_Controller;
    }

    public void setBanVe_GUI_Controller(BanVe_GUI_Controller banVe_GUI_Controller) {
        this.banVe_GUI_Controller = banVe_GUI_Controller;
    }

    @FXML
    private ImageView imvChuyenTau;

    @FXML
    private Label lblChangDaiHon;

    @FXML
    private Label lblConTrong;

    @FXML
    private Label lblDaBan;

    @FXML
    private Label lblDaDat;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblThoiGianDen;

    @FXML
    private Label lblThoiGianDi;

    private BanVe_GUI_Controller banVe_GUI_Controller;
    private ChuyenTau chuyenTau;
    private ChiTietChuyenTau chiTietChuyenTauDi;
    private ChiTietChuyenTau chiTietChuyenTauDen;
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

    public ChiTietChuyenTau getChiTietChuyenTauDen() {
        return chiTietChuyenTauDen;
    }

    public void setChiTietChuyenTauDen(ChiTietChuyenTau chiTietChuyenTauDen) {
        this.chiTietChuyenTauDen = chiTietChuyenTauDen;
    }

    public ChiTietChuyenTau getChiTietChuyenTauDi() {
        return chiTietChuyenTauDi;
    }

    public void setChiTietChuyenTauDi(ChiTietChuyenTau chiTietChuyenTauDi) {
        this.chiTietChuyenTauDi = chiTietChuyenTauDi;
    }

    @FXML
    void anpChuyenTauOnMouseClicked(MouseEvent event) {
        chonChuyenTau();
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
        lblDaBan.setText(String.valueOf(chuyenTau.getSoLuongChoDaBan()));
        lblDaDat.setText(String.valueOf(chuyenTau.getSoLuongChoDaDat()));
        lblChangDaiHon.setText(String.valueOf(chuyenTau.getSoLuongChoChangDaiHon()));
        lblConTrong.setText(String.valueOf(chuyenTau.getSoLuongChoTrongTrong()));
    }

    public void chonChuyenTau(){
        banVe_GUI_Controller.setThuTuChuyenTauDangChon(soThuTu);
        banVe_GUI_Controller.timDanhSachToaTau(lblMaChuyenTau.getText());
        banVe_GUI_Controller.boChonTatCaChuyenTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-green.png"));
        imvChuyenTau.setImage(image);
    }
}
