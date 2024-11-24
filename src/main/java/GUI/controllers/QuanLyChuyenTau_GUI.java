package GUI.controllers;

import DTO.ChiTietChuyenTau;
import DTO.ChuyenTau;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class QuanLyChuyenTau_GUI {

    @FXML
    private JFXButton btnTimChuyenTau;

    @FXML
    private DatePicker dapNgayKhoiHanh;

    @FXML
    private HBox hboxDanhSachChuyenTau;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblMaTuyenTau;

    @FXML
    private Label lblMoTaTuyenTau;

    @FXML
    private Label lblSoCho;

    @FXML
    private Label lblSoGaDiQua;

    @FXML
    private Label lblSoVe;

    @FXML
    private Label lblTenTuyenTau;

    @FXML
    private Label lblThoiGianDenGaCuoi;

    @FXML
    private Label lblThoiGianKhoiHanh;

    @FXML
    private Label lblVeDat;

    @FXML
    private ScrollPane scpDanhSachChuyenTau;

    @FXML
    private TextField txtMaChuyenTau;

    @FXML
    private TextField txtMaTuyenTau;

    private ChuyenTau chuyenTau;
    private ArrayList<ChiTietChuyenTau> chiTietChuyenTauList = new ArrayList<ChiTietChuyenTau>();

    @FXML
    void btnTimChuyenTauOnAction(ActionEvent event) {

    }

}
