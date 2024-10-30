package GUI.controllers;

import DTO.ChiTietVe;
import DTO.Ve;
import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ThongTinBanVe_GUI_Controller implements Initializable {
    private ArrayList<Ve> danhSachVe;
    private ArrayList<ChiTietVe> danhSachChiTietVe;
    private BanVe_GUI_Controller controller;

    public void setDanhSachVe(ArrayList<Ve> danhSachVe){
        this.danhSachVe = danhSachVe;
    }

    public void setController(BanVe_GUI_Controller controller) {
        this.controller = controller;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() ->{

        });
    }
}
