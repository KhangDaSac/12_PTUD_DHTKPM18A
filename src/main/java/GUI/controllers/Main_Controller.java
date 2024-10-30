package GUI.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Objects;

public class Main_Controller {
    @FXML
    private AnchorPane anpNoiDungTrang;

    @FXML
    private Button btnBanVe;

    @FXML
    private Button btnBaoCao;

    @FXML
    private Button btnBaoCaoVaThongKe;

    @FXML
    private Button btnDangXuat;

    @FXML
    private Button btnDatVe;

    @FXML
    private Button btnDoiVe;

    @FXML
    private Button btnHuyDatVe;

    @FXML
    private Button btnHuyVe;

    @FXML
    private Button btnLayVe;

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

    @FXML
    private VBox vboxQuanLyBaoCaoVaThongKe;

    @FXML
    private VBox vboxLuaChonQuanLyVe;



    @FXML
    void chuyenTrangChu(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TrangChu_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }




    public void chuyenTrangThongTinBanVe(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinBanVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }



    @FXML
    void btnQuanLyVeMouseEnterd(MouseEvent event) {
        vboxLuaChonQuanLyVe.setVisible(true);
    }

    @FXML
    void btnQuanLyVeMouseExited(MouseEvent event) {
        vboxLuaChonQuanLyVe.setVisible(false);
    }


    @FXML
    void vboxLuaChonQuanLyVeMouseEntered(MouseEvent event) {
        vboxLuaChonQuanLyVe.setVisible(true);
    }

    @FXML
    void vboxLuaChonQuanLyVeMouseExited(MouseEvent event) {
        vboxLuaChonQuanLyVe.setVisible(false);
    }

    @FXML
    void btnBanVeOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BanVe_GUI_Controller controller = loader.getController();
        controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    @FXML
    void btnBaoCaoOnAction(ActionEvent event) {

    }

    @FXML
    void btnDangXuatOnAction(ActionEvent event) {

    }

    @FXML
    void btnDatVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnDoiVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnHuyDatVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnHuyVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnLayVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnQuanLyChuyenTauOnAction(ActionEvent event) {

    }

    @FXML
    void btnQuanLyHoaDonOnAction(ActionEvent event) {

    }

    @FXML
    void btnQuanLyKhachHangOnAction(ActionEvent event) {

    }

    @FXML
    void btnQuanLyLichSuOnAction(ActionEvent event) {

    }

    @FXML
    void btnQuanLyPhieuDatVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnThongKeOnAction(ActionEvent event) {

    }


    @FXML
    void vboxQuanLyBaoCaoVaThongKeMouseEntered(MouseEvent event) {
        vboxQuanLyBaoCaoVaThongKe.setVisible(true);
    }

    @FXML
    void vboxQuanLyBaoCaoVaThongKeMouseExited(MouseEvent event) {
        vboxQuanLyBaoCaoVaThongKe.setVisible(false);

    }

    @FXML
    void btnThongTinUngDungMouseEnterd(MouseEvent event) {
        vboxQuanLyBaoCaoVaThongKe.setVisible(true);
    }

    @FXML
    void btnThongTinUngDungOnActionExieted(MouseEvent event) {
        vboxQuanLyBaoCaoVaThongKe.setVisible(false);

    }

    @FXML
    void btnQuanLyNhanVienOnAction(ActionEvent event) {

    }

    @FXML
    void btnThongTinUngDungOnAction(ActionEvent event) {

    }
}
