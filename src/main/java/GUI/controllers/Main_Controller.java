package GUI.controllers;

import DTO.ChiTietVe;
import DTO.HoaDonBanVe;
import DTO.NhanVien;
import DTO.Ve;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.ShowMessagesDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Main_Controller implements Initializable {
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
    private Button btnTimPhieuDatVe;

    @FXML
    private Button btnTimVe;

    @FXML
    private Label lblMaNhanVienDangNhap;

    @FXML
    private Label lblTenNhanVienDanNhap;

    @FXML
    private Label lblTieuDeTrang;

    @FXML
    private StackPane stpKhung;

    @FXML
    private VBox vboxLuaChonQuanLyVe;

    @FXML
    private VBox vboxQuanLyBaoCaoVaThongKe;

    @FXML
    private VBox vboxQuanLyPhieuDatVe;

    private Stage stage;

    private NhanVien nhanVien;

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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




    public void chuyenTrangThongTinBanVe(HoaDonBanVe hoaDon, ArrayList<Ve> danhSachVe, ArrayList<ChiTietVe> danhSachChiTietVe){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinBanVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ThongTinBanVe_GUI_Controller thongTinBanVe_gui_controller = loader.getController();
        thongTinBanVe_gui_controller.setMain_controller(this);
        thongTinBanVe_gui_controller.setHoaDon(hoaDon);
        thongTinBanVe_gui_controller.setDanhSachVe(danhSachVe);
        thongTinBanVe_gui_controller.setDanhSachChiTietVe(danhSachChiTietVe);
        thongTinBanVe_gui_controller.khoiTao();
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
        lblTieuDeTrang.setText("BÁN VÉ");
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

        lblTieuDeTrang.setText("ĐỔI VÉ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DoiVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DoiVe_GUI_Controller controller = loader.getController();
        controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    @FXML
    void btnHuyDatVeOnAction(ActionEvent event) {
        lblTieuDeTrang.setText("HỦY DẶT VÉ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyDatVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HuyDatVe_GUI_Controller controller = loader.getController();
        controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    @FXML
    void btnHuyVeOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HuyVe_GUI_Controller controller = loader.getController();
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    @FXML
    void btnLayVeOnAction(ActionEvent event) {
        lblTieuDeTrang.setText("LẤY VÉ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LayVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LayVe_GUI_Controller controller = loader.getController();
        controller.setMain_controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    @FXML
    void btnQuanLyChuyenTauOnAction(ActionEvent event) {

    }

    @FXML
    void btnQuanLyHoaDonOnAction(ActionEvent event) {
        lblTieuDeTrang.setText("Quản lý hóa đơn");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        QuanLyHoaDon_GUI_Controller controller = loader.getController();
        controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    @FXML
    void btnQuanLyKhachHangOnAction(ActionEvent event) {
        lblTieuDeTrang.setText("QUẢN LÝ KHÁCH HÀNG");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyKhachHang_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        QuanLyKhachHang_GUI_Controller controller = loader.getController();
        controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    @FXML
    void btnQuanLyLichSuOnAction(ActionEvent event) {

    }

    @FXML
    void btnQuanLyPhieuDatVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnThongKeOnAction(ActionEvent event) {
        lblTieuDeTrang.setText("Thống kê");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BaoCaoThongKe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BaoCaoThongKe_GUI_Controller controller = loader.getController();
        controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyNhanVien_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        QuanLyNhanVien_GUI_Controller controller = loader.getController();
        controller.setMain_controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    @FXML
    void btnThongTinUngDungOnAction(ActionEvent event) {

    }

    public void quayLaiTrangBanVe(HoaDonBanVe hoaDon, ArrayList<Ve> danhSachVe, ArrayList<ChiTietVe> danhSachChiTietVe){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BanVe_GUI_Controller banVe_gui_controller = loader.getController();
        banVe_gui_controller.setMain_Controller(this);
        banVe_gui_controller.setHoaDon(hoaDon);
        banVe_gui_controller.setDanhSachVe(danhSachVe);
        banVe_gui_controller.setDanhSachChiTietVe(danhSachChiTietVe);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
    }

    public void showMessagesDialog(String messages){
        ShowMessagesDialog.showDialog(stpKhung, "Thông báo", messages, "OK");
    }

    public boolean showLoadingDialog(){
        return ShowMessagesDialog.showDialogWithLoading(stpKhung, "Loanding", "Loading");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            if(nhanVien != null){
                lblMaNhanVienDangNhap.setText(nhanVien.getMaNhanVien());
                lblTenNhanVienDanNhap.setText(nhanVien.getTenNhanVien());
            }
        });
    }


    @FXML
    void btnQuanLyPhieuDatVeEntered(MouseEvent event) {
        vboxQuanLyPhieuDatVe.setVisible(true);
    }

    @FXML
    void btnQuanLyPhieuDatVeExited(MouseEvent event) {
        vboxQuanLyPhieuDatVe.setVisible(false);
    }


    @FXML
    void vboxQuanLyPhieuDatVeEntered(MouseEvent event) {
        vboxQuanLyPhieuDatVe.setVisible(true);
    }

    @FXML
    void vboxQuanLyPhieuDatVeExtied(MouseEvent event) {
        vboxQuanLyPhieuDatVe.setVisible(false);
    }

    @FXML
    void btnTimPhieuDatVeOnAction(ActionEvent event) {

    }

    @FXML
    void btnTimVeOnAction(ActionEvent event) {

    }
}
