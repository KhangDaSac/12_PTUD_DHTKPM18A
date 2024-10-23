package GUI.controllers.BanVe_GUI_Items;

import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChuyenTau_Controller {
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
    private Label lblDaDatVeBan;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblThoiGianDen;

    @FXML
    private Label lblThoiGianDi;

    private BanVe_GUI_Controller banVe_GUI_Controller;


    @FXML
    void anpChuyenTauOnMouseClicked(MouseEvent event) {
        banVe_GUI_Controller.timDanhSachToaTau(lblMaChuyenTau.getText());
    }

    @FXML
    public void initialize() {

    }

}
