package GUI.controllers.BanVe_GUI_Items;

import DTO.ChiTietVe;
import DTO.KhachHang;
import DTO.LoaiVe;
import DTO.Ve;
import GUI.controllers.BanVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Ve_BanVe_Controller implements Initializable {
    @FXML
    private AnchorPane anpVe;

    @FXML
    private AnchorPane anpXoaVe;

    @FXML
    private HBox hboxGiaVeCuoi;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

    @FXML
    private Label lblCCCD;

    @FXML
    private Label lblCho;

    @FXML
    private Label lblGiaCho;

    @FXML
    private Label lblGiaVeCuoi;

    @FXML
    private Label lblGiamGiaLoaiKhachHang;

    @FXML
    private Label lblGiamGiaVeTapThe;

    @FXML
    private Label lblLoaiKhachHang;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblSTT;

    @FXML
    private Label lblTenGaDen;

    @FXML
    private Label lblTenGaDi;

    @FXML
    private Label lblTenKhachHang;

    @FXML
    private Label lblThoiGianDi;

    @FXML
    private Label lblToa;

    @FXML
    private VBox vboxDanhDachChoVeTapThe;

    @FXML
    private VBox vboxDanhSachThongTin;
    private Ve ve;

    private int soThuTu;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    private BanVe_GUI_Controller banVe_GUI_Controller;

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
    }

    public BanVe_GUI_Controller getBanVe_GUI_Controller() {
        return banVe_GUI_Controller;
    }

    public void setBanVe_GUI_Controller(BanVe_GUI_Controller banVe_GUI_Controller) {
        this.banVe_GUI_Controller = banVe_GUI_Controller;
    }

    @FXML
    void anpVeOnMouseClicked(MouseEvent event) {
        chonVe();
    }

    @FXML
    void anpXoaVeOnMouseCliced(MouseEvent event) {
        if(banVe_GUI_Controller != null){
            banVe_GUI_Controller.xoaVe(ve);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void khoiTao(){

        anpVe.getStylesheets().add(getClass().getResource("/css/BanVe_GUI_Items/Ve.css").toExternalForm());
        lblMaChuyenTau.setText(ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(ve.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(ve.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));

        if(ve.getLoaiVe() == LoaiVe.VECANHAN){
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpXoaVe.getStyleClass().add("ve-left-veCaNhan");

            lblCho.setText(String.valueOf(ve.getDanhSachChiTietVe().getFirst().getCho().getSoCho()));
            lblToa.setText(String.valueOf(ve.getDanhSachChiTietVe().getFirst().getCho().getToaTau().getThuTuToa()));
            lblGiaCho.setText(CurrencyFormat.currencyFormat(ve.getDanhSachChiTietVe().getFirst().getGiaCho()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
            KhachHang khachHang = ve.getDanhSachChiTietVe().getFirst().getKhachHang();
            if(khachHang != null){
                lblCCCD.setText(khachHang.getCCCD());
                lblTenKhachHang.setText(khachHang.getTenKhachHang());
                lblLoaiKhachHang.setText(khachHang.getLoaiKhachHang().getTenLoaiKhachHang());
                lblGiamGiaLoaiKhachHang.setText(CurrencyFormat.currencyFormat(ve.getDanhSachChiTietVe().getFirst().giamGia()));
            }else{
                lblGiamGiaLoaiKhachHang.setText("0 VNĐ");
            }

            lblSTT.setText(String.valueOf(soThuTu + 1));

        }else if(ve.getLoaiVe() == LoaiVe.VETAPTHE){
            vboxDanhDachChoVeTapThe.getChildren().clear();
            HBox hbox0 = new HBox();
            Label lblDuongKeTren = new Label("---------------------------------------------");
            hbox0.getChildren().add(lblDuongKeTren);
            lblDuongKeTren.setFont(Font.font("System", 20));
            vboxDanhDachChoVeTapThe.getChildren().add(hbox0);
            for(ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()){
                // HBox 1: Chỗ và Toa
                HBox hbox1 = new HBox();
                hbox1.setAlignment(Pos.CENTER_LEFT);
                Label lblChoLabel = new Label("Chỗ:");
                lblChoLabel.setPrefSize(80, 30);
                lblChoLabel.getStyleClass().add("tieuDe");

                Label lblCho = new Label(String.valueOf(chiTietVe.getCho().getSoCho()));
                lblCho.setPrefSize(100, 30);
                lblCho.getStyleClass().add("noiDung");

                Label lblToaLabel = new Label("Toa:");
                lblToaLabel.setPrefSize(80, 30);
                lblToaLabel.getStyleClass().add("tieuDe");

                Label lblToa = new Label(String.valueOf(chiTietVe.getCho().getToaTau().getThuTuToa()));
                lblToa.setPrefSize(100, 30);
                lblToa.getStyleClass().add("noiDung");

                hbox1.getChildren().addAll(lblChoLabel, lblCho, lblToaLabel, lblToa);

                // HBox 2: Giá chỗ
                HBox hbox2 = new HBox();
                hbox2.setAlignment(Pos.CENTER_LEFT);
                Label lblGiaChoLabel = new Label("Giá chỗ:");
                lblGiaChoLabel.setPrefSize(170, 30);
                lblGiaChoLabel.getStyleClass().add("tieuDe");

                Label lblGiaCho = new Label(CurrencyFormat.currencyFormat(chiTietVe.getGiaCho()));
                lblGiaCho.setPrefSize(250, 30);
                lblGiaCho.getStyleClass().add("noiDung");

                hbox2.getChildren().addAll(lblGiaChoLabel, lblGiaCho);

                KhachHang khachHang = ve.getDanhSachChiTietVe().getFirst().getKhachHang();

                // HBox 3: Tên khách hàng
                HBox hbox3 = new HBox();
                hbox3.setAlignment(Pos.CENTER_LEFT);
                Label lblTenKhachHangLabel = new Label("Tên khách hàng:");
                lblTenKhachHangLabel.setPrefSize(170, 30);
                lblTenKhachHangLabel.getStyleClass().add("tieuDe");

                Label lblTenKhachHang = new Label(khachHang != null ? khachHang.getTenKhachHang() : "");
                lblTenKhachHang.setPrefSize(250, 30);
                lblTenKhachHang.getStyleClass().add("noiDung");

                hbox3.getChildren().addAll(lblTenKhachHangLabel, lblTenKhachHang);

                // HBox 4: Số CCCD
                HBox hbox4 = new HBox();
                hbox4.setAlignment(Pos.CENTER_LEFT);
                Label lblCCCDLabel = new Label("Số CCCD:");
                lblCCCDLabel.setPrefSize(170, 30);
                lblCCCDLabel.getStyleClass().add("tieuDe");

                Label lblCCCD = new Label(khachHang != null ? khachHang.getCCCD() : "");
                lblCCCD.setPrefSize(250, 30);
                lblCCCD.getStyleClass().add("noiDung");

                hbox4.getChildren().addAll(lblCCCDLabel, lblCCCD);

                // HBox 5: Loại khách hàng
                HBox hbox5 = new HBox();
                hbox5.setAlignment(Pos.CENTER_LEFT);
                Label lblLoaiKhachHangLabel = new Label("Loại khách hàng:");
                lblLoaiKhachHangLabel.setPrefSize(170, 30);
                lblLoaiKhachHangLabel.getStyleClass().add("tieuDe");

                Label lblLoaiKhachHang = new Label(khachHang != null ? khachHang.getLoaiKhachHang().getTenLoaiKhachHang() : "");
                lblLoaiKhachHang.setPrefSize(250, 30);
                lblLoaiKhachHang.getStyleClass().add("noiDung");

                hbox5.getChildren().addAll(lblLoaiKhachHangLabel, lblLoaiKhachHang);

                // HBox 6: Giảm giá loại khách hàng
                HBox hbox6 = new HBox();
                hbox6.setAlignment(Pos.CENTER_LEFT);
                Label lblGiamGiaLoaiKhachHangLabel = new Label("Giảm giá loại KH:");
                lblGiamGiaLoaiKhachHangLabel.setPrefSize(170, 30);
                lblGiamGiaLoaiKhachHangLabel.getStyleClass().add("tieuDe");

                Label lblGiamGiaLoaiKhachHang = new Label(khachHang != null ? CurrencyFormat.currencyFormat(chiTietVe.giamGia()) : "0 VNĐ");
                lblGiamGiaLoaiKhachHang.setPrefSize(250, 30);
                lblGiamGiaLoaiKhachHang.getStyleClass().add("noiDung");


                hbox6.getChildren().addAll(lblGiamGiaLoaiKhachHangLabel, lblGiamGiaLoaiKhachHang);

                HBox hbox7 = new HBox();
                Label lblDuongKeDuoi = new Label("---------------------------------------------");
                hbox7.getChildren().add(lblDuongKeDuoi);
                lblDuongKeDuoi.setFont(Font.font("System", 20));

                // Thêm tất cả HBox vào VBox
                vboxDanhDachChoVeTapThe.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7);


            }
            anpVe.setMinHeight(220 + ve.getDanhSachChiTietVe().size() * 180);
            anpXoaVe.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(ve.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
        }
    }

    public void chonVe(){
            banVe_GUI_Controller.boChonTatCaVe();
            anpVe.getStyleClass().add("veDangChon");
            anpVe.getStyleClass().removeAll("veKhongChon");

    }

    public void khongChonVe(){
        anpVe.getStyleClass().removeAll("veDangChon");
        anpVe.getStyleClass().add("veKhongChon");
    }

}
