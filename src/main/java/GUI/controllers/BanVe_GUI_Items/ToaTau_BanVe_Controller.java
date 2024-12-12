package GUI.controllers.BanVe_GUI_Items;

import DTO.ToaTau;
import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ToaTau_BanVe_Controller {

    @FXML
    private ImageView imvToaTau;

    @FXML
    private Label lblThuTuTau;

    @FXML
    private Label lblLoaiToaTau;

    @FXML
    private AnchorPane anpToaTau;


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

    private Tooltip tooltip;

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
        chonToaTau();
    }

    public void khoiTao(){
        lblThuTuTau.setText(String.valueOf(toaTau.getThuTuToa()));
        lblLoaiToaTau.setText(toaTau.getLoaiToaTau().getMaLoaiToa());

        switch (toaTau.getLoaiToaTau().getMaLoaiToa()){
            case "MEM" -> {
                lblLoaiToaTau.getStyleClass().add("toaNgoiMem");
            }
            case "GN6"  -> {
                lblLoaiToaTau.getStyleClass().add("toaGiuongNamKhoang6");
            }
            case "GN4"  -> {
                lblLoaiToaTau.getStyleClass().add("toaGiuongNamKhoang4");
            }
            case "GN2"  -> {
                lblLoaiToaTau.getStyleClass().add("toaGiuongNamKhoang2");
            }
        }

        if(toaTau.getSoLuongChoTrongTrong() >= 0){
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-gray.png")));
        }else{
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-red.png")));
        }

        tooltip = new Tooltip(toaTau.getLoaiToaTau().getTenLoaiToa());
        tooltip.setShowDelay(Duration.millis(0)); // Hiển thị ngay lập tức khi hover
        tooltip.setHideDelay(Duration.millis(0));
        tooltip.setStyle(
                "-fx-font-size: 16px; " +
                "-fx-background-color: white; " +
                "-fx-text-fill: black; "
        );
        Tooltip.install(anpToaTau, tooltip);
    }

    public void chinhMauKhongChon(){
        if(toaTau.getSoLuongChoTrongTrong() >= 0){
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-gray.png")));
        }else{
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-red.png")));
        }
    }

    public void chonToaTau(){
        banVe_GUI_Controller.setThuTuToaTauDangChon(soThuTu);
        banVe_GUI_Controller.timDanhSachCho(toaTau.getMaToaTau());
        banVe_GUI_Controller.boChonTatCaToaTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-green.png"));
        imvToaTau.setImage(image);
    }
}
