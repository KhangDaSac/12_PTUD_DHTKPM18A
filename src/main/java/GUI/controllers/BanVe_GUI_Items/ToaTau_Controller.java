package GUI.controllers.BanVe_GUI_Items;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ToaTau_Controller {

    @FXML
    private ImageView imvToaTau;

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

    @FXML
    private Label lblThuTuTau;



    @FXML
    void anpToaTauOnMousrClicked(MouseEvent event) {

    }

}
