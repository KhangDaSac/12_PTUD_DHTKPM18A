package GUI.controllers.QuanLyChuyenTau_GUI_Items;

import DTO.ChuyenTau;
import GUI.controllers.QuanLyChuyenTau_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChuyenTau_QuanLyChuyenTau_Controller {

    @FXML
    private ImageView imvChuyenTau;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblMaTuyenTau;

    @FXML
    private Label lblSoCho;

    @FXML
    private Label lblSoVe;

    @FXML
    private Label lblSoVeDat;

    private ChuyenTau chuyenTau;

    public ChuyenTau getChuyenTau() {
        return chuyenTau;
    }

    public void setChuyenTau(ChuyenTau chuyenTau) {
        this.chuyenTau = chuyenTau;
    }

    private QuanLyChuyenTau_GUI_Controller quanLyChuyenTau_gui_controller;

    public QuanLyChuyenTau_GUI_Controller getQuanLyChuyenTau_gui_controller() {
        return quanLyChuyenTau_gui_controller;
    }

    public void setQuanLyChuyenTau_gui_controller(QuanLyChuyenTau_GUI_Controller quanLyChuyenTau_gui_controller) {
        this.quanLyChuyenTau_gui_controller = quanLyChuyenTau_gui_controller;
    }

    @FXML
    void anpChuyenTauOnMouseClicked(MouseEvent event) {
        chonChuyenTau();
    }

    public void khoiTao(){
        lblMaChuyenTau.setText(chuyenTau.getMaChuyenTau());
        lblMaTuyenTau.setText(chuyenTau.getTuyenTau().getMaTuyenTau());
        lblSoCho.setText(String.valueOf(chuyenTau.getSoLuongCho()));
        lblSoVe.setText(String.valueOf(chuyenTau.getSoLuongVe()));
        lblSoVeDat.setText(String.valueOf(chuyenTau.getSoLuongVeDat()));
    }

    public void chinhMauKhongChon(){
        if(chuyenTau.getSoLuongChoTrongTrong() >= 0){
            imvChuyenTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-gray.png")));
        }else{
            imvChuyenTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-red.png")));
        }
    }

    public void chonChuyenTau(){
        if(quanLyChuyenTau_gui_controller.getChuyenTau() != null && quanLyChuyenTau_gui_controller.getChuyenTau().equals(chuyenTau))
            return;

        quanLyChuyenTau_gui_controller.setChuyenTau(chuyenTau);
        quanLyChuyenTau_gui_controller.boChonTatCaChuyenTau();
        quanLyChuyenTau_gui_controller.hienThiThongTinChuyenTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-green.png"));
        imvChuyenTau.setImage(image);
    }

}
