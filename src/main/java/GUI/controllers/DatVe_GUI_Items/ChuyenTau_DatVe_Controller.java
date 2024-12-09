package GUI.controllers.DatVe_GUI_Items;

import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.DatVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.TimeFormat;

import java.net.URL;
import java.util.ResourceBundle;

public class ChuyenTau_DatVe_Controller implements Initializable {
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

    private DatVe_GUI_Controller datVe_gui_controller;
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

    public DatVe_GUI_Controller getDatVe_gui_controller() {
        return datVe_gui_controller;
    }

    public void setDatVe_gui_controller(DatVe_GUI_Controller datVe_gui_controller) {
        this.datVe_gui_controller = datVe_gui_controller;
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
        datVe_gui_controller.setThuTuChuyenTauDangChon(soThuTu);
        datVe_gui_controller.timDanhSachToaTau(lblMaChuyenTau.getText());
        datVe_gui_controller.boChonTatCaChuyenTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-green.png"));
        imvChuyenTau.setImage(image);
    }
}
