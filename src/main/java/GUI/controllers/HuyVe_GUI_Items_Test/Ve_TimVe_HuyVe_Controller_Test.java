package GUI.controllers.HuyVe_GUI_Items_Test;

import DTO.ChiTietVe;
import DTO.LoaiVe;
import DTO.Ve;
import GUI.controllers.HuyVe_GUI_Controller_New;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.CurrencyFormat;
import utils.TimeFormat;

import java.util.ArrayList;


public class Ve_TimVe_HuyVe_Controller_Test {
    @FXML
    private AnchorPane anpVeTim;

    @FXML
    private AnchorPane anpVeTim_Left;

    @FXML
    private Label lblMaChuyenTau;

    @FXML
    private Label lblSTT;

    @FXML
    private Label lblTenGaDen;

    @FXML
    private Label lblTenGaDi;

    @FXML
    private Label lblThoiGianDi;

    @FXML
    private HBox hboxGiamGiaVeTapThe;

    @FXML
    private Label lblGiamGiaVeTapThe;

    @FXML
    private Label lblGiaVeCuoi;

    @FXML
    private VBox vboxDanhSachChoVeTapThe;

    @FXML
    private VBox vboxDanhSachThongTin;

    private ArrayList<ChiTietVe_HuyVe_Controller_Test> chiTietVe_huyVe_controllers_list = new ArrayList<>();

    public ArrayList<ChiTietVe_HuyVe_Controller_Test> getChiTietVe_huyVe_controllers_list() {
        return chiTietVe_huyVe_controllers_list;
    }

    public void setChiTietVe_huyVe_controllers_list(ArrayList<ChiTietVe_HuyVe_Controller_Test> chiTietVe_huyVe_controllers_list) {
        this.chiTietVe_huyVe_controllers_list = chiTietVe_huyVe_controllers_list;
    }

    private boolean isSelected = false; // Trạng thái đã chọn hay chưa

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @FXML
//    void anpVeTimOnMouseClicked(MouseEvent event) {
//        huyVe_gui_controller_new.getVeTim().setSelected(false); // Reset trạng thái của vé cũ
//        this.ve.setSelected(true); // Đánh dấu vé hiện tại là được chọn
//        huyVe_gui_controller_new.setVeTim(this.ve); // Cập nhật vé được chọn
//    }

    private HuyVe_GUI_Controller_New huyVe_gui_controller_new;
    private int soThuTu;

    private Ve ve;

    public Ve getVe() {
        return ve;
    }

    public void setVe(Ve ve) {
        this.ve = ve;
    }

    public HuyVe_GUI_Controller_New getHuyVe_gui_controller_new() {
        return huyVe_gui_controller_new;
    }

    public void setHuyVe_gui_controller_new(HuyVe_GUI_Controller_New huyVe_gui_controller_new) {
        this.huyVe_gui_controller_new = huyVe_gui_controller_new;
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public void khoiTao() throws Exception {
        lblSTT.setText(String.valueOf(soThuTu + 1));
        lblMaChuyenTau.setText(ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
        lblTenGaDi.setText(ve.getThongTinGaTauDi().getGaTau().getTenGaTau());
        lblTenGaDen.setText(ve.getThongTinGaTauDen().getGaTau().getTenGaTau());
        lblThoiGianDi.setText(TimeFormat.formatLocalDateTime(ve.getThongTinGaTauDi().getThoiGianDi()));
        anpVeTim_Left.getStylesheets().add(getClass().getResource("/css/HuyVe_GUI_Items/Ve_HuyVe.css").toExternalForm());

        if (ve.getLoaiVe().equals(LoaiVe.VECANHAN)) {
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpVeTim_Left.getStyleClass().add("ve-left-veCaNhan");
        } else if (ve.getLoaiVe().equals(LoaiVe.VETAPTHE)) {
            anpVeTim_Left.getStyleClass().add("ve-left-veTapThe");
        }

        anpVeTim_Left.getStylesheets().add(getClass().getResource("/css/HuyVe_GUI_Items/Ve_HuyVe.css").toExternalForm());
        lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));

        vboxDanhSachChoVeTapThe.getChildren().clear();
        System.out.println("" + ve.getDanhSachChiTietVe().size());
        for (ChiTietVe chiTietVe : ve.getDanhSachChiTietVe()) {
            System.out.println(chiTietVe.getCho().getSoCho());
            System.out.println("ChiTietVe: " + chiTietVe);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HuyVe_GUI_Items_Test/ChiTietVe_HuyVe.fxml"));
            Parent anchorPane = loader.load();
            ChiTietVe_HuyVe_Controller_Test controller = loader.getController();
            chiTietVe_huyVe_controllers_list.add(controller);

            controller.setChiTietVe(chiTietVe);
            controller.khoiTao();

            vboxDanhSachChoVeTapThe.getChildren().add(anchorPane);
        }

        System.out.println("Số controller sau vòng lặp: " + chiTietVe_huyVe_controllers_list.size());

        chiTietVe_huyVe_controllers_list.get(chiTietVe_huyVe_controllers_list.size() - 1).chiTietVeCuoi();

        if (ve.getLoaiVe().equals(LoaiVe.VECANHAN)) {
            vboxDanhSachThongTin.setMinHeight(390);
            vboxDanhSachThongTin.getChildren().remove(hboxGiamGiaVeTapThe);
            anpVeTim_Left.getStyleClass().add("ve-left-veCaNhan");
        } else if (ve.getLoaiVe().equals(LoaiVe.VETAPTHE)) {
            vboxDanhSachThongTin.setMinHeight(210 + ve.getDanhSachChiTietVe().size() * 260);
            anpVeTim_Left.getStyleClass().add("ve-left-veTapThe");
            lblGiamGiaVeTapThe.setText(CurrencyFormat.currencyFormat(ve.giamGiaVeTapThe()));
            lblGiaVeCuoi.setText(CurrencyFormat.currencyFormat(ve.tienVeCuoi()));
        }

        if (huyVe_gui_controller_new.getVeTim().equals(ve)) {
            chonVe();
        } else {
            boChonVe();
        }
    }

    public void chonVe(){
        anpVeTim.getStyleClass().removeAll("hoaDonKhongChon");
        anpVeTim.getStyleClass().add("hoaDonChon");
    }

    public void boChonVe(){
        anpVeTim.getStyleClass().removeAll("hoaDonChon");
        anpVeTim.getStyleClass().add("hoaDonKhongChon");
    }

    public void anpVeTimOnMouseClicked(MouseEvent mouseEvent) {
    }
}