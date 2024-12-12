package GUI.controllers;

import DTO.*;
import GUI.applications.Run;
import GUI.controllers.BaoCao_GUI_Items.PhieuKiemTien_BaoCao_Controller;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.ShowMessagesDialog;

import java.io.IOException;
import java.net.URL;
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
    private Button btnQuanLyNhanVien;

    @FXML
    private Button btnQuanLyVe;

    @FXML
    private Button btnQuanLyVeDat;

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
    private StackPane stpKhung;

    @FXML
    private StackPane stpThongBaoKhongHoatDong;

    @FXML
    private VBox vboxQuanLyVe;

    @FXML
    private VBox vboxQuanLyBaoCaoVaThongKe;

    @FXML
    private VBox vboxQuanLyVeDat;

    private PhieuKetToan phieuKetToan;
    private NhanVien nhanVien;

    public PhieuKetToan getPhieuKetToan() {
        return phieuKetToan;
    }

    public void setPhieuKetToan(PhieuKetToan phieuKetToan) {
        this.phieuKetToan = phieuKetToan;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    //Thong ke va bao cao

    @FXML
    void vboxQuanLyBaoCaoVaThongKeMouseEntered(MouseEvent event) {
        vboxQuanLyBaoCaoVaThongKe.setVisible(true);
    }

    @FXML
    void vboxQuanLyBaoCaoVaThongKeMouseExited(MouseEvent event) {
        vboxQuanLyBaoCaoVaThongKe.setVisible(false);

    }

    @FXML
    void btnBaoCaoVaThongKeMouseEnterd(MouseEvent event) {
        vboxQuanLyBaoCaoVaThongKe.setVisible(true);

    }

    @FXML
    void btnBaoCaoVaThongKeOnActionExieted(MouseEvent event) {
        vboxQuanLyBaoCaoVaThongKe.setVisible(false);

    }

    //QL Ve
    @FXML
    void btnQuanLyVeEnterd(MouseEvent event) {
        vboxQuanLyVe.setVisible(true);
    }

    @FXML
    void btnQuanLyVeExited(MouseEvent event) {
        vboxQuanLyVe.setVisible(false);
    }


    @FXML
    void vboxQuanLyVeEntered(MouseEvent event) {
        vboxQuanLyVe.setVisible(true);
    }

    @FXML
    void vboxQuanLyVeExited(MouseEvent event) {
        vboxQuanLyVe.setVisible(false);
    }

    //QL Ve Dat

    @FXML
    void btnQuanLyVeDatEntered(MouseEvent event) {
        vboxQuanLyVeDat.setVisible(true);
    }

    @FXML
    void btnQuanLyVeDatExited(MouseEvent event) {
        vboxQuanLyVeDat.setVisible(false);
    }

    @FXML
    void vboxQuanLyVeDatEntered(MouseEvent event) {
        vboxQuanLyVeDat.setVisible(true);
    }

    @FXML
    void vboxQuanLyVeDatExtied(MouseEvent event) {
        vboxQuanLyVeDat.setVisible(false);
    }

    public JFXDialog dialog;

    public void hienThiPhieuKiemTienDauCa(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("/view/BaoCao_GUI_Items/PhieuKiemTien_BaoCao.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            PhieuKiemTien_BaoCao_Controller controller = fxmlLoader.getController();
            controller.setMain_controller(this);
            dialog = this.showWindowDialog(anchorPane, "Phiếu kiểm tiền");
            controller.setDialog(dialog);
            controller.setNhanVienKiemTien(nhanVien);
            controller.setLoaiPhieuKiemTien("DC");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void btnTrangChuOnAction(ActionEvent event) {
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
        if(dialog != null) dialog.close();
    }

    public void chuyenTrangThongTinBanVe(HoaDonBanVe hoaDonBanVe){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinBanVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ThongTinBanVe_GUI_Controller thongTinBanVe_gui_controller = loader.getController();
        thongTinBanVe_gui_controller.setMain_controller(this);
        thongTinBanVe_gui_controller.setHoaDonBanVe(hoaDonBanVe);
        thongTinBanVe_gui_controller.khoiTao();
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();
    }

    public void chuyenTrangThongTinDatVe(HoaDonDatVe hoaDonDatVe){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ThongTinDatVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ThongTinDatVe_GUI_Controller thongTinBanVe_gui_controller = loader.getController();
        thongTinBanVe_gui_controller.setMain_controller(this);
        thongTinBanVe_gui_controller.setHoaDonDatVe(hoaDonDatVe);
        thongTinBanVe_gui_controller.khoiTao();
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();
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
        if(dialog != null) dialog.close();
    }

    @FXML
    void btnBaoCaoOnAction(ActionEvent event) {
        lblTieuDeTrang.setText("BÁO CÁO");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BaoCao_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BaoCao_GUI_Controller controller = loader.getController();
        controller.setMain_controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();
    }

    @FXML
    void btnDangXuatOnAction(ActionEvent event) {
        dangXuat();
    }

    public void dangXuat(){
        try {
            Window window = anpNoiDungTrang.getScene().getWindow();
            Stage stage = (Stage) window;
            FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("/view/DangNhap_GUI.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {

        }
    }
    @FXML
    void btnDatVeOnAction(ActionEvent event) {
        lblTieuDeTrang.setText("ĐẶT VÉ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DatVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DatVe_GUI_Controller controller = loader.getController();
        controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();
    }

    @FXML
    void btnDoiVeOnAction(ActionEvent event) {
        chuyenTrangDoiVe();
    }

    public void chuyenTrangDoiVe(){
        lblTieuDeTrang.setText("ĐỔI VÉ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DoiVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (Exception e) {
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
        if(dialog != null) dialog.close();
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
        controller.setMain_controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();
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
//        HuyVe_GUI_Controller controller = loader.getController();
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();
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
        if(dialog != null) dialog.close();
    }

    @FXML
    void btnQuanLyChuyenTauOnAction(ActionEvent event) {
        lblTieuDeTrang.setText("QUẢN LÝ CHUYẾN TÀU");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyChuyenTau_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        QuanLyChuyenTau_GUI_Controller controller = loader.getController();
        controller.setMain_controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();
    }

    @FXML
    void btnQuanLyHoaDonOnAction(ActionEvent event) {
        lblTieuDeTrang.setText("Quản lý hóa đơn");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/QuanLyHoaDon_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuanLyHoaDon_GUI_Controller controller = loader.getController();
        controller.setMain_controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();
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
        if(dialog != null) dialog.close();
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

//        BaoCaoThongKe_GUI_Controller controller = loader.getController();
//        controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();
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
//        QuanLyNhanVien_GUI_Controller controller = loader.getController();
//        controller.setMain_controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();

    }

    @FXML
    void btnThongTinUngDungOnAction(ActionEvent event) {

    }

    public void quayLaiTrangBanVe(HoaDonBanVe hoaDonBanVe){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BanVe_GUI_Controller banVe_gui_controller = loader.getController();
        banVe_gui_controller.setMain_Controller(this);
        banVe_gui_controller.setHoaDonBanVe(hoaDonBanVe);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();

    }

    public void quayLaiTrangBanVe(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BanVe_GUI_Controller banVe_gui_controller = loader.getController();
        banVe_gui_controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();

    }

    public void quayLaiTrangDatVe(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DatVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DatVe_GUI_Controller datVe_gui_controller = loader.getController();
        datVe_gui_controller.setMain_Controller(this);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();

    }

    public void quayLaiTrangDatVe(HoaDonDatVe hoaDonDatVe){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DatVe_GUI.fxml"));
        Parent trangMoi = null;
        try {
            trangMoi = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DatVe_GUI_Controller datVe_gui_controller = loader.getController();
        datVe_gui_controller.setMain_Controller(this);
        datVe_gui_controller.setHoaDonDatVe(hoaDonDatVe);
        anpNoiDungTrang.getChildren().clear();
        anpNoiDungTrang.getChildren().add(trangMoi);
        AnchorPane.setTopAnchor(trangMoi, 0.0);
        AnchorPane.setBottomAnchor(trangMoi, 0.0);
        AnchorPane.setLeftAnchor(trangMoi, 0.0);
        AnchorPane.setRightAnchor(trangMoi, 0.0);
        if(dialog != null) dialog.close();

    }

    public void showMessagesDialog(String messages){
        ShowMessagesDialog.showDialog(stpKhung, "Thông báo", messages, "OK");
    }

    public JFXDialog showWindowDialog(AnchorPane anchorPane, String title){
        return ShowMessagesDialog.showDialog(stpKhung, title, anchorPane);
    }

    public void thongBaoKhongHoatDong(String messages){
        dialog = ShowMessagesDialog.showDialog(stpThongBaoKhongHoatDong, "Thông báo", messages);
    }

    public void khoiTao(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            if(phieuKetToan != null && phieuKetToan.getCaLamViec() != null){
                lblMaNhanVienDangNhap.setText(phieuKetToan.getCaLamViec().getNhanVien().getMaNhanVien());
                lblTenNhanVienDanNhap.setText(phieuKetToan.getCaLamViec().getNhanVien().getTenNhanVien());
            }
        });
    }
}
