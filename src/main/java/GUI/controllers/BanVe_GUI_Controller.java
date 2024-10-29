package GUI.controllers;

import BUS.QuanLyChuyenTau_BUS;
import BUS.QuanLyVe_BUS;
import DTO.*;
import GUI.controllers.BanVe_GUI_Items.*;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BanVe_GUI_Controller implements Initializable {
    @FXML
    private VBox vboxChiTietVe;

    @FXML
    private VBox vboxGioVe;

    @FXML
    private AnchorPane anpDanhSachCho;

    @FXML
    private AnchorPane anpChuyenTauSau;

    @FXML
    private AnchorPane anpChuyenTauTruoc;

    @FXML
    private AnchorPane anpToaTauSau;

    @FXML
    private AnchorPane anpToaTauTruoc;

    @FXML
    private JFXButton btnTiepTuc;

    @FXML
    private JFXButton btnTimChuyenTau;

    @FXML
    private ComboBox<String> cmbGaTauDen;

    @FXML
    private ComboBox<String> cmbGaTauDi;

    @FXML
    private ComboBox<String> cmbLoaiVe;

    @FXML
    private DatePicker dapNgayKhoiHanh;

    @FXML
    private GridPane grpDanhSachCho;

    @FXML
    private HBox hboxDanhSachChuyenTau;

    @FXML
    private HBox hboxDanhSachToaTau;

    @FXML
    private JFXButton btnBoChonTatCa;

    @FXML
    private JFXButton btnChonCaToa;

    @FXML
    private JFXButton btnThemVe;

    private GaTau gaDi;

    private GaTau gaDen;

    private ArrayList<GaTau> gaTauList;

    private ArrayList<ChuyenTau> chuyenTauList;
    private ArrayList<ToaTau> toaTauList;
    private ArrayList<Cho> choList;

    private ArrayList<ChuyenTau_Controller> chuyenTauControllerList = new ArrayList<ChuyenTau_Controller>();
    private ArrayList<ToaTau_Controller> toaTauControllerList = new ArrayList<ToaTau_Controller>();
    private ArrayList<Cho_Controller> choControllerList = new ArrayList<Cho_Controller>();
    private  ArrayList<Ve_Controller> veControllerList = new ArrayList<Ve_Controller>();
    private ArrayList<ChiTietVe_Controller> chiTietVeControllerList = new ArrayList<ChiTietVe_Controller>();

    private ArrayList<Ve> danhSachVe = new ArrayList<>();
    private ArrayList<ChiTietVe> danhSachChiTietVe = new ArrayList<ChiTietVe>();

    private int chuyenTauDangChon;
    private int toaTauDangChon;

    private ArrayList<Cho> choChonList = new ArrayList<Cho>();

    public ArrayList<Cho> getChoChonList() {
        return choChonList;
    }

    public void setChoChonList(ArrayList<Cho> choChonList) {
        this.choChonList = choChonList;
    }

    public int getToaTauDangChon() {
        return toaTauDangChon;
    }

    public void setToaTauDangChon(int toaTauDangChon) {
        this.toaTauDangChon = toaTauDangChon;
    }


    private int trangChuyenTauHienTai;

    private int trangToaTauHienTai;

    public int getChuyenTauDangChon() {
        return chuyenTauDangChon;
    }

    public void setChuyenTauDangChon(int chuyenTauDangChon) {
        this.chuyenTauDangChon = chuyenTauDangChon;
    }

    @FXML
    void btnTimChuyenTauOnAction(ActionEvent event) {
            timDanhSachChuyenTau();
    }

    @FXML
    void anpChuyenTauSauOnMouseClicked(MouseEvent event) {
        int lengthList = chuyenTauList.size();
        if(trangChuyenTauHienTai < Math.ceil(lengthList/4.0)){
            trangChuyenTauHienTai++;
            try {
                int batDau = Math.max(4*(trangChuyenTauHienTai - 1), 0);
                int ketThuc = Math.min(4 * trangChuyenTauHienTai, lengthList);
                hienThiDanhSachChuyenTau(chuyenTauList, batDau, ketThuc);
                anpChuyenTauSau.setVisible(lengthList > ketThuc);
                anpChuyenTauTruoc.setVisible(batDau > 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void anpChuyenTauTruocOnMouseClicked(MouseEvent event) {
        int lengthList = chuyenTauList.size();
        if(trangChuyenTauHienTai > 1){
            trangChuyenTauHienTai--;
            try {
                int batDau = Math.max(4*(trangChuyenTauHienTai - 1), 0);
                int ketThuc = Math.min(4 * trangChuyenTauHienTai, lengthList);
                hienThiDanhSachChuyenTau(chuyenTauList, batDau, ketThuc);
                anpChuyenTauSau.setVisible(lengthList > ketThuc);
                anpChuyenTauTruoc.setVisible(batDau > 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void anpToaTauSauOnMouseClicked(MouseEvent event) {
        int lengthList = toaTauList.size();
        if(trangToaTauHienTai < Math.ceil(lengthList/9.0)){
            trangToaTauHienTai++;
            try {
                toaTauDangChon++;
                int batDau = Math.max(9 * (trangToaTauHienTai - 1), 0);
                int ketThuc = Math.min(9 * trangToaTauHienTai, lengthList);
                hienThiDanhSachToa(toaTauList, batDau, ketThuc);
                anpToaTauSau.setVisible(lengthList > ketThuc);
                anpToaTauTruoc.setVisible(batDau > 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void anpToaTauTruocOnMouseClicked(MouseEvent event) {
        int lengthList = toaTauList.size();
        if(trangToaTauHienTai > 1){
            trangToaTauHienTai--;
            try {
                int batDau = Math.max(9*(trangToaTauHienTai - 1), 0);
                int ketThuc = Math.min(9 * trangToaTauHienTai, lengthList);
                hienThiDanhSachToa(toaTauList, batDau, ketThuc);
                anpToaTauSau.setVisible(lengthList > ketThuc);
                anpToaTauTruoc.setVisible(batDau > 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnBoChonTatCaOnAction(ActionEvent event) {
        for(Cho_Controller controller : choControllerList){
            choChonList.remove(controller.getCho());
        }
        capNhatCacChoDaChon();
    }

    @FXML
    void btnChonCaToaOnAction(ActionEvent event) {
        for(Cho_Controller controller : choControllerList){
            if(controller.getCho().getTrangThaiCho() == TrangThaiCho.CONTRONG){
                if(!choChonList.contains(controller.getCho())){
                    choChonList.add(controller.getCho());
                }
            }
        }
        capNhatCacChoDaChon();
    }

    @FXML
    void btnThemVeOnAction(ActionEvent event) {
        try {
            themVeVaoGio();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void timDanhSachChuyenTau(){
        if(cmbGaTauDi.getSelectionModel().getSelectedIndex() < 0){
            System.out.println("Ga tàu đi không hợp lệ");
            return;
        }
        if(cmbGaTauDen.getSelectionModel().getSelectedIndex() < 0){
            System.out.println("Ga tàu đến không hợp lệ");
            return;
        }
        GaTau gaTauDi = gaTauList.get(cmbGaTauDi.getSelectionModel().getSelectedIndex());
        GaTau gaTauDen = gaTauList.get(cmbGaTauDen.getSelectionModel().getSelectedIndex());

        LocalDate ngayDi = dapNgayKhoiHanh.getValue();
        try {
            gaDi = gaTauDi;
            gaDen = gaTauDen;
            chuyenTauList = QuanLyChuyenTau_BUS.getDanhSachChuyenTau(gaDi.getMaGaTau(), gaDen.getMaGaTau(), ngayDi);
            trangChuyenTauHienTai = 1;
            anpChuyenTauTruoc.setVisible(false);
            anpChuyenTauSau.setVisible(chuyenTauList.size() > 4);
            hienThiDanhSachChuyenTau(chuyenTauList, 0, 4);



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void timDanhSachToaTau(String maChuyenTau){

        try {
            toaTauList = QuanLyChuyenTau_BUS.getDanhSachToaTau(maChuyenTau, gaDi.getMaGaTau(), gaDen.getMaGaTau());
            anpToaTauTruoc.setVisible(false);
            anpToaTauSau.setVisible(toaTauList.size() > 9);
            trangToaTauHienTai = 1;
            hienThiDanhSachToa(toaTauList, 0, 9);
        } catch (Exception e) {

        }
    }

    public void hienThiDanhSachChuyenTau(ArrayList<ChuyenTau> chuyenTauList, int batDau, int ketThuc) throws IOException {
        hboxDanhSachChuyenTau.getChildren().clear();
        chuyenTauControllerList.clear();
        for (int i = batDau; i < ketThuc; i++){
            ChuyenTau chuyenTau = chuyenTauList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ChuyenTau.fxml"));
            Parent anchorPane = loader.load();
            ChuyenTau_Controller controller = loader.getController();
            chuyenTauControllerList.add(controller);
            controller.setBanVe_GUI_Controller(this);
            controller.setChuyenTau(chuyenTau);
            controller.khoiTao();
            if(chuyenTauDangChon == i){
                Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-green.png"));
                controller.getImvChuyenTau().setImage(image);
            }
            controller.setSoThuTu(i);


            hboxDanhSachChuyenTau.getChildren().add(anchorPane);
        }
    }



    public void hienThiDanhSachToa(ArrayList<ToaTau> toaTauList, int batDau, int ketThuc) throws IOException {
        hboxDanhSachToaTau.getChildren().clear();
        toaTauControllerList.clear();
        for(int i = batDau; i < ketThuc; i++){
            ToaTau toaTau = toaTauList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ToaTau.fxml"));
            Parent anchorPane = loader.load();
            ToaTau_Controller controller = loader.getController();
            toaTauControllerList.add(controller);
            controller.setBanVe_GUI_Controller(this);
            controller.setToaTau(toaTau);
            controller.khoiTao();
            controller.setSoThuTu(i);

            if(toaTauDangChon == i){
                Image image = new Image(getClass().getResourceAsStream("/images/BanVe_GUI/train-car-green.png"));
                controller.getImvToaTau().setImage(image);
            }

            hboxDanhSachToaTau.getChildren().add(anchorPane);
        }
    }

    public void timDanhSachCho(String maToaTau){
        choList = QuanLyChuyenTau_BUS.getDanhSachChoTheoMaToaTau(maToaTau, gaDi.getMaGaTau(), gaDen.getMaGaTau());
        try {
            hienThiDanhSachCho(choList);
            choChonList.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void hienThiDanhSachCho(ArrayList<Cho> choList) throws IOException {
        anpDanhSachCho.setVisible(true);
        grpDanhSachCho.setVisible(true);
        grpDanhSachCho.getChildren().clear();
        int length = choList.size();
        if(length == 64){
            grpDanhSachCho.getColumnConstraints().clear();
            grpDanhSachCho.getRowConstraints().clear();
            for (int j = 0; j < 16; j++) {
                ColumnConstraints column = new ColumnConstraints();
                grpDanhSachCho.getColumnConstraints().add(column);
            }
            for (int j = 0; j < 4; j++) {
                RowConstraints row = new RowConstraints();
                grpDanhSachCho.getRowConstraints().add(row);
            }
        }else if(length == 42){
            grpDanhSachCho.getColumnConstraints().clear();
            grpDanhSachCho.getRowConstraints().clear();
            for (int j = 0; j < 14; j++) {
                ColumnConstraints column = new ColumnConstraints();
                grpDanhSachCho.getColumnConstraints().add(column);
            }
            for (int j = 0; j < 3; j++) {
                RowConstraints row = new RowConstraints();
                grpDanhSachCho.getRowConstraints().add(row);
            }
        }else if(length == 28){
            grpDanhSachCho.getColumnConstraints().clear();
            grpDanhSachCho.getRowConstraints().clear();
            for (int j = 0; j < 14; j++) {
                ColumnConstraints column = new ColumnConstraints();
                grpDanhSachCho.getColumnConstraints().add(column);
            }
            for (int j = 0; j < 2; j++) {
                RowConstraints row = new RowConstraints();
                grpDanhSachCho.getRowConstraints().add(row);
            }
        }else if(length == 14){
            grpDanhSachCho.getColumnConstraints().clear();
            grpDanhSachCho.getRowConstraints().clear();
            for (int j = 0; j < 7; j++) {
                ColumnConstraints column = new ColumnConstraints();
                grpDanhSachCho.getColumnConstraints().add(column);
            }
            for (int j = 0; j < 2; j++) {
                RowConstraints row = new RowConstraints();
                grpDanhSachCho.getRowConstraints().add(row);
            }
        }

        choControllerList.clear();

        for(int i = 0; i < length; i++){
            Cho cho = choList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/Cho.fxml"));
            Parent anchorPane = loader.load();
            Cho_Controller controller = loader.getController();
            controller.setBanVe_GUI_controller(this);
            choControllerList.add(controller);
            controller.setCho(cho);
            controller.khoiTao();

            if(length == 64){
                grpDanhSachCho.add(anchorPane, i/4, i%4);
            }else if(length == 42){
                grpDanhSachCho.add(anchorPane, i/3, i%3);
            }else if(length == 28 || length == 14){
                grpDanhSachCho.add(anchorPane, i/2, i%2);
            }
        }

        capNhatCacChoDaChon();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gaTauList = QuanLyChuyenTau_BUS.getDanhSachGaTau();
        cmbGaTauDen.getItems().clear();
        cmbGaTauDi.getItems().clear();
        for(GaTau gaTau : gaTauList){
            cmbGaTauDen.getItems().add(gaTau.getTenGaTau());
            cmbGaTauDi.getItems().add(gaTau.getTenGaTau());
        }
        cmbGaTauDi.getSelectionModel().select(125);
        cmbGaTauDen.getSelectionModel().select(51);

        dapNgayKhoiHanh.setValue(LocalDate.of(2024, 10, 31));

        anpChuyenTauTruoc.setVisible(false);
        anpChuyenTauSau.setVisible(false);
        anpToaTauTruoc.setVisible(false);
        anpToaTauSau.setVisible(false);

        anpDanhSachCho.setVisible(false);
        grpDanhSachCho.setVisible(false);

        //Combobox loại vé
        cmbLoaiVe.getItems().add("Vé cá nhân");
        cmbLoaiVe.getItems().add("Vé tập thể");
        cmbLoaiVe.getSelectionModel().select(0);

        hboxDanhSachToaTau.getChildren().clear();
        hboxDanhSachChuyenTau.getChildren().clear();

        vboxGioVe.getChildren().clear();
        vboxChiTietVe.getChildren().clear();


        cmbGaTauDi.getEditor().setOnKeyTyped(event -> {
            cmbGaTauDi.getItems().clear();
            String tenGaTau = cmbGaTauDi.getEditor().getText();
            for (GaTau gaTau : gaTauList){
                if(gaTau.getTenGaTau().toLowerCase().startsWith(tenGaTau.toLowerCase())){
                    cmbGaTauDi.show();
                    cmbGaTauDi.getItems().add(gaTau.getTenGaTau());
                    //cmbGaTauDi.getSelectionModel().selectFirst();
                }
            }

        });

        cmbGaTauDi.getEditor().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                cmbGaTauDi.getSelectionModel().selectFirst();
            }
        });


    }

    public void boChonTatCaChuyenTau(){
        for (ChuyenTau_Controller chuyenTau_Controller : chuyenTauControllerList){
            chuyenTau_Controller.chinhMauKhongChon();
        }
    }

    public void boChonTatCaToaTau(){
        for(ToaTau_Controller toaTau_Controller : toaTauControllerList){
            toaTau_Controller.chinhMauKhongChon();
        }
    }

    public void boChonTatCaVe(){
        for(Ve_Controller ve_controller : veControllerList){
            ve_controller.khongChonVe();
        }
    }

    public void capNhatCacChoDaChon(){
        for (Cho_Controller controller : choControllerList) {
            controller.chuyenMauMacDinh();

            boolean daThemVaoGioVe = danhSachChiTietVe.stream().anyMatch(chiTietVe -> chiTietVe.getCho().equals(controller.getCho()));

            if (daThemVaoGioVe) {
                controller.chuyenMauDaThemVaoGioVe();
                continue;
            }

            boolean dangChon = choChonList.stream().anyMatch(cho -> cho.equals(controller.getCho()));

            if (dangChon) {
                controller.chuyenMauDangChon();
            }
        }
    }

    public void themVeVaoGio() throws IOException {

        LoaiVe loaiVe = LoaiVe.values()[cmbLoaiVe.getSelectionModel().getSelectedIndex()];
        if(loaiVe == LoaiVe.VECANHAN){
            for(Cho cho : choChonList){
                ChuyenTau chuyenTau = chuyenTauList.get(chuyenTauDangChon);
                ChiTietChuyenTau chiTietChuyenTauDi = new ChiTietChuyenTau(chuyenTau, gaDi);
                chiTietChuyenTauDi.setThoiGianDi(chuyenTau.getThoiGianDi());
                ChiTietChuyenTau chiTietChuyenTauDen = new ChiTietChuyenTau(chuyenTau, gaDen);
                String maVeMoi = "";
                if(danhSachVe.isEmpty()){
                    maVeMoi = QuanLyVe_BUS.taoMaVeMoi();
                }else{
                    maVeMoi = QuanLyVe_BUS.taoMaVeTiepTheo(danhSachVe.getLast());
                }

                Ve ve = new Ve(maVeMoi, chiTietChuyenTauDi, chiTietChuyenTauDen);
                ve.setLoaiVe(LoaiVe.VECANHAN);
                cho.setToaTau(toaTauList.get(toaTauDangChon));
                ChiTietVe chiTietVe = new ChiTietVe(ve, cho);
                danhSachChiTietVe.add(chiTietVe);
                danhSachVe.add(ve);

                for(Cho_Controller controller : choControllerList){
                    if(controller.getCho().equals(cho)){
                        controller.setDaThemVaoGio(true);
                        break;
                    }
                }
            }
            choChonList.clear();
        }

        capNhatGioVe();
        capNhatCacChoDaChon();
    }

    public void capNhatGioVe() throws IOException {
        vboxGioVe.getChildren().clear();
        veControllerList.clear();
        for(Ve ve : danhSachVe){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/Ve.fxml"));
            Parent anchorPane = loader.load();
            Ve_Controller controller = loader.getController();
            veControllerList.add(controller);
            controller.setBanVe_GUI_Controller(this);

            controller.setVe(ve);
            controller.khoiTao();

            vboxGioVe.getChildren().add(anchorPane);
        }
    }

    public void capNhatChiTietVe(Ve ve) throws IOException {
        vboxChiTietVe.getChildren().clear();
        chiTietVeControllerList.clear();
        for(ChiTietVe chiTietVe : danhSachChiTietVe){
            if(chiTietVe.getVe().equals(ve)){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ChiTietVe.fxml"));
                Parent anchorPane = loader.load();
                ChiTietVe_Controller controller = loader.getController();
                chiTietVeControllerList.add(controller);
                controller.setBanVe_gui_controller(this);

                controller.setChiTietVe(chiTietVe);
                controller.khoiTao();

                vboxChiTietVe.getChildren().add(anchorPane);
            }
        }
    }
}
