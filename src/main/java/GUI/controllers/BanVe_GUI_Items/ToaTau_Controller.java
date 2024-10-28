package GUI.controllers.BanVe_GUI_Items;

import DTO.ToaTau;
import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ToaTau_Controller {

    @FXML
    private ImageView imvToaTau;

    @FXML
    private Label lblThuTuTau;

    public Label getLblThuTuTau() {
        return lblThuTuTau;
    }

    public void setLblThuTuTau(Label lblThuTuTau) {
        this.lblThuTuTau = lblThuTuTau;
    }

    public ImageView getImvToaTau() {
        return imvToaTau;
    }

    public void setImvToaTau(ImageView imvToaTau) {
        this.imvToaTau = imvToaTau;
    }

    private ToaTau toaTau;

    private Image defaultImage;

    public ToaTau getToaTau() {
        return toaTau;
    }

    public void setToaTau(ToaTau toaTau) {
        this.toaTau = toaTau;
    }

    private BanVe_GUI_Controller banVe_GUI_Controller;
    private int soThuTu;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public BanVe_GUI_Controller getBanVe_GUI_Controller() {
        return banVe_GUI_Controller;
    }

    public void setBanVe_GUI_Controller(BanVe_GUI_Controller banVe_GUI_Controller) {
        this.banVe_GUI_Controller = banVe_GUI_Controller;
    }

    @FXML
    void anpToaTauOnMousrClicked(MouseEvent event) {
        banVe_GUI_Controller.timDanhSachCho(toaTau.getMaToaTau());
        banVe_GUI_Controller.setToaTauDangChon(soThuTu);
        banVe_GUI_Controller.boChonTatCaToaTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-green.png"));
        imvToaTau.setImage(image);
    }

    public void khoiTao(){
        lblThuTuTau.setText(String.valueOf(toaTau.getThuTuToa()));
        if(toaTau.getSoLuongChoTrongTrong() > 0){
            defaultImage = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-gray.png"));
        }else{
            defaultImage = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-red.png"));
        }
    }

    public void chinhMauKhongChon(){

        imvToaTau.setImage(defaultImage);
    }
}
