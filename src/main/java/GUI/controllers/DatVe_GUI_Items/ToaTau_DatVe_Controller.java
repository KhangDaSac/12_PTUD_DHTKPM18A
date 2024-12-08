package GUI.controllers.DatVe_GUI_Items;

import DTO.ToaTau;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.DatVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ToaTau_DatVe_Controller {

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

    private DatVe_GUI_Controller datVe_gui_controller;
    private int soThuTu;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public DatVe_GUI_Controller getDatVe_gui_controller() {
        return datVe_gui_controller;
    }

    public void setDatVe_gui_controller(DatVe_GUI_Controller datVe_gui_controller) {
        this.datVe_gui_controller = datVe_gui_controller;
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
        datVe_gui_controller.setThuTuToaTauDangChon(soThuTu);
        datVe_gui_controller.timDanhSachCho(toaTau.getMaToaTau());
        datVe_gui_controller.boChonTatCaToaTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-green.png"));
        imvToaTau.setImage(image);
    }
}
