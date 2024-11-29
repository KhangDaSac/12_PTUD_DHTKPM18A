package GUI.controllers.BanVe_GUI_Items;

import DTO.ToaTau;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.DoiVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ToaTau_Controller {

    @FXML
    private ImageView imvToaTau;

    @FXML
    private Label lblThuTuTau;


    @FXML
    private AnchorPane anpToaTau;


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

    private ToaTau toaTau;

    private Tooltip tooltip;

    public ToaTau getToaTau() {
        return toaTau;
    }

    public void setToaTau(ToaTau toaTau) {
        this.toaTau = toaTau;
    }

    private BanVe_GUI_Controller banVe_GUI_Controller;
    private DoiVe_GUI_Controller doiVe_gui_controller;
    private int soThuTu;
    private static String trang;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public BanVe_GUI_Controller getBanVe_GUI_Controller() {
        return banVe_GUI_Controller;
    }

    public void setBanVe_GUI_Controller(BanVe_GUI_Controller banVe_GUI_Controller) {
        this.banVe_GUI_Controller = banVe_GUI_Controller;
    }

    public DoiVe_GUI_Controller getDoiVe_gui_controller() {return doiVe_gui_controller;}

    public void setDoiVe_gui_controller(DoiVe_GUI_Controller doiVe_gui_controller) {
        this.doiVe_gui_controller = doiVe_gui_controller;
    }
    public static void loaiTrang(String link){
        trang = link;
    }

    @FXML
    void anpToaTauOnMousrClicked(MouseEvent event) {
        if(trang.equals("BanVe_GUI.fxml")){
            chonToaTau();}
        else if(trang.equals("DoiVe_GUI.fxml")){
            chonToaTau_DoiVe();
            doiVe_gui_controller.setLblToaTau_Moi(toaTau.getMaToaTau());
            doiVe_gui_controller.setLblCho_Moi(0);
            doiVe_gui_controller.setLblGiaCho_Moi(0.0);

        }else {
            System.out.printf("trang không xác định");
        }
    }

    public void khoiTao(){
        lblThuTuTau.setText(String.valueOf(toaTau.getThuTuToa()));
        if(toaTau.getSoLuongChoTrongTrong() >= 0){
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-gray.png")));
        }else{
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-red.png")));
        }

        tooltip = new Tooltip(toaTau.getLoaiToaTau().getTenLoaiToa());
        tooltip.setShowDelay(Duration.millis(0)); // Hiển thị ngay lập tức khi hover
        tooltip.setHideDelay(Duration.millis(0));
        tooltip.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-background-color: white; " +
                        "-fx-text-fill: black; "
        );
        Tooltip.install(anpToaTau, tooltip);
    }

    public void chinhMauKhongChon(){
        if(toaTau.getSoLuongChoTrongTrong() >= 0){
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-gray.png")));
        }else{
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-red.png")));
        }
    }

    public void chonToaTau(){
        banVe_GUI_Controller.timDanhSachCho(toaTau.getMaToaTau());
        banVe_GUI_Controller.setToaTauDangChon(soThuTu);
        banVe_GUI_Controller.boChonTatCaToaTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-green.png"));
        imvToaTau.setImage(image);
    }
    public void chonToaTau_DoiVe(){
        doiVe_gui_controller.timDanhSachCho(toaTau.getMaToaTau()); // chỗ này sai
        doiVe_gui_controller.setToaTauDangChon(soThuTu);
        doiVe_gui_controller.boChonTatCaToaTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-green.png"));
        imvToaTau.setImage(image);
    }
}package GUI.controllers.BanVe_GUI_Items;

import DTO.ToaTau;
import GUI.controllers.BanVe_GUI_Controller;
import GUI.controllers.DoiVe_GUI_Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ToaTau_Controller {

    @FXML
    private ImageView imvToaTau;

    @FXML
    private Label lblThuTuTau;


    @FXML
    private AnchorPane anpToaTau;


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

    private ToaTau toaTau;

    private Tooltip tooltip;

    public ToaTau getToaTau() {
        return toaTau;
    }

    public void setToaTau(ToaTau toaTau) {
        this.toaTau = toaTau;
    }

    private BanVe_GUI_Controller banVe_GUI_Controller;
    private DoiVe_GUI_Controller doiVe_gui_controller;
    private int soThuTu;
    private static String trang;

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public BanVe_GUI_Controller getBanVe_GUI_Controller() {
        return banVe_GUI_Controller;
    }

    public void setBanVe_GUI_Controller(BanVe_GUI_Controller banVe_GUI_Controller) {
        this.banVe_GUI_Controller = banVe_GUI_Controller;
    }

    public DoiVe_GUI_Controller getDoiVe_gui_controller() {return doiVe_gui_controller;}

    public void setDoiVe_gui_controller(DoiVe_GUI_Controller doiVe_gui_controller) {
        this.doiVe_gui_controller = doiVe_gui_controller;
    }
    public static void loaiTrang(String link){
        trang = link;
    }

    @FXML
    void anpToaTauOnMousrClicked(MouseEvent event) {
        if(trang.equals("BanVe_GUI.fxml")){
            chonToaTau();}
        else if(trang.equals("DoiVe_GUI.fxml")){
            chonToaTau_DoiVe();
            doiVe_gui_controller.setLblToaTau_Moi(toaTau.getMaToaTau());
            doiVe_gui_controller.setLblCho_Moi(0);
            doiVe_gui_controller.setLblGiaCho_Moi(0.0);

        }else {
            System.out.printf("trang không xác định");
        }
    }

    public void khoiTao(){
        lblThuTuTau.setText(String.valueOf(toaTau.getThuTuToa()));
        if(toaTau.getSoLuongChoTrongTrong() >= 0){
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-gray.png")));
        }else{
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-red.png")));
        }

        tooltip = new Tooltip(toaTau.getLoaiToaTau().getTenLoaiToa());
        tooltip.setShowDelay(Duration.millis(0)); // Hiển thị ngay lập tức khi hover
        tooltip.setHideDelay(Duration.millis(0));
        tooltip.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-background-color: white; " +
                        "-fx-text-fill: black; "
        );
        Tooltip.install(anpToaTau, tooltip);
    }

    public void chinhMauKhongChon(){
        if(toaTau.getSoLuongChoTrongTrong() >= 0){
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-gray.png")));
        }else{
            imvToaTau.setImage(new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-red.png")));
        }
    }

    public void chonToaTau(){
        banVe_GUI_Controller.timDanhSachCho(toaTau.getMaToaTau());
        banVe_GUI_Controller.setToaTauDangChon(soThuTu);
        banVe_GUI_Controller.boChonTatCaToaTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-green.png"));
        imvToaTau.setImage(image);
    }
    public void chonToaTau_DoiVe(){
        doiVe_gui_controller.timDanhSachCho(toaTau.getMaToaTau()); // chỗ này sai
        doiVe_gui_controller.setToaTauDangChon(soThuTu);
        doiVe_gui_controller.boChonTatCaToaTau();
        Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-green.png"));
        imvToaTau.setImage(image);
    }
}