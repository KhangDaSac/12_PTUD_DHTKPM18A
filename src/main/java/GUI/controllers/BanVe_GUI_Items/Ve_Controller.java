package GUI.controllers.BanVe_GUI_Items;

import DTO.Ve;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Ve_Controller implements Initializable {
    @FXML
    private AnchorPane anpVe;

    @FXML
    private Label lblGiaVe;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblTenGaDen;

    @FXML
    private Label lblTenGaDi;

    @FXML
    private Label lblThoiGianDi;

    private Ve ve;

    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void khoiTao(){

    }


}
