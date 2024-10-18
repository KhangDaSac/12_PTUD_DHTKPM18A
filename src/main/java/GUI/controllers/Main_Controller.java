package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Objects;

public class Main_Controller {

    @FXML
    private AnchorPane anpNoiDungTrang;

    @FXML
    private Button btnDangXuat;

    @FXML
    private Button btnQuanLyChuyenTau;

    @FXML
    private Button btnQuanLyHoaDon;

    @FXML
    private Button btnQuanLyKhachHang;

    @FXML
    private Button btnQuanLyLichSu;

    @FXML
    private Button btnQuanLyNhanVien;

    @FXML
    private Button btnQuanLyPhieuDatVe;

    @FXML
    private Button btnQuanLyVe;

    @FXML
    private Button btnThongKe;

    @FXML
    private Button btnThongTinTaiKhoan;

    @FXML
    private Button btnThongTinUngDung;

    @FXML
    private Button btnTrangChu;

    @FXML
    private Label lblMaNhanVienDangNhap;

    @FXML
    private Label lblTenNhanVienDanNhap;

    @FXML
    private Label lblTieuDeTrang;

    public void chuyenTrangChu() throws IOException {
       chuyenTrang("/view/TrangChu_GUI.fxml");
    }

    private void chuyenTrang(String trangMoiPath) throws IOException {
        Parent trangMoi = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(trangMoiPath)));
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    public void chuyenBanVe() throws IOException {
        chuyenTrang("/view/BanVe_GUI.fxml");
    }

}
