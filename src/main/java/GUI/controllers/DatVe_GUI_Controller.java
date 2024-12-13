package GUI.controllers;

import BUS.QuanLyChuyenTau_BUS;
import BUS.QuanLyHoaDon_BUS;
import BUS.QuanLyVeDat_BUS;
import BUS.QuanLyVe_BUS;
import DTO.*;
import GUI.controllers.BanVe_GUI_Items.Cho_BanVe_Controller;
import GUI.controllers.BanVe_GUI_Items.ChuyenTau_BanVe_Controller;
import GUI.controllers.BanVe_GUI_Items.ToaTau_BanVe_Controller;
import GUI.controllers.BanVe_GUI_Items.Ve_BanVe_Controller;
import GUI.controllers.DatVe_GUI_Items.Cho_DatVe_Controller;
import GUI.controllers.DatVe_GUI_Items.ChuyenTau_DatVe_Controller;
import GUI.controllers.DatVe_GUI_Items.ToaTau_DatVe_Controller;
import GUI.controllers.DatVe_GUI_Items.VeDat_DatVe_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import utils.CurrencyFormat;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DatVe_GUI_Controller implements Initializable {

    @FXML
    private AnchorPane anpDanhSachCho;

    @FXML
    private JFXButton btnBoChonTatCa;

    @FXML
    private JFXButton btnChonCaToa;

    @FXML
    private JFXButton btnThemVeDat;

    @FXML
    private JFXButton btnTiepTuc;

    @FXML
    private JFXButton btnTimChuyenTau;

    @FXML
    private JFXButton btnXoaTatCaVeDatTrongGio;

    @FXML
    private ComboBox<GaTau> cmbGaTauDen;

    @FXML
    private ComboBox<GaTau> cmbGaTauDi;

    @FXML
    private ComboBox<LoaiVe> cmbLoaiVe;

    @FXML
    private DatePicker dapNgayKhoiHanh;

    @FXML
    private GridPane grpDanhSachCho;

    @FXML
    private HBox hboxDanhSachChuyenTau;

    @FXML
    private HBox hboxDanhSachToaTau;

    @FXML
    private ScrollPane scpDanhSachCho;

    @FXML
    private ScrollPane scpDanhSachChuyenTau;

    @FXML
    private ScrollPane scpDanhSachToaTau;

    @FXML
    private TextField txtTongTien;

    @FXML
    private VBox vboxGioVeDat;


    private Main_Controller main_Controller;

    public Main_Controller getMain_Controller() {
        return main_Controller;
    }

    public void setMain_Controller(Main_Controller main_Controller) {
        this.main_Controller = main_Controller;
    }

    private GaTau gaDi;
    private GaTau gaDen;

    private ArrayList<GaTau> gaTauList;

    private ArrayList<ChuyenTau> chuyenTauList;
    private ArrayList<ToaTau> toaTauList;
    private ArrayList<Cho> choList;

    private ArrayList<ChuyenTau_DatVe_Controller> chuyenTauControllerList = new ArrayList<ChuyenTau_DatVe_Controller>();
    private ArrayList<ToaTau_DatVe_Controller> toaTauControllerList = new ArrayList<ToaTau_DatVe_Controller>();
    private ArrayList<Cho_DatVe_Controller> choControllerList = new ArrayList<Cho_DatVe_Controller>();
    private ArrayList<VeDat_DatVe_Controller> veDatControllerList = new ArrayList<VeDat_DatVe_Controller>();

    public ArrayList<ChuyenTau_DatVe_Controller> getChuyenTauControllerList() {
        return chuyenTauControllerList;
    }

    public void setChuyenTauControllerList(ArrayList<ChuyenTau_DatVe_Controller> chuyenTauControllerList) {
        this.chuyenTauControllerList = chuyenTauControllerList;
    }

    private HoaDonDatVe hoaDonDatVe;

    public HoaDonDatVe getHoaDonDatVe() {
        return hoaDonDatVe;
    }

    public void setHoaDonDatVe(HoaDonDatVe hoaDonDatVe) {
        this.hoaDonDatVe = hoaDonDatVe;
    }

    private int thuTuChuyenTauDangChon;
    private int thuTuToaTauDangChon;

    public int getThuTuToaTauDangChon() {
        return thuTuToaTauDangChon;
    }

    public void setThuTuToaTauDangChon(int thuTuToaTauDangChon) {
        this.thuTuToaTauDangChon = thuTuToaTauDangChon;
    }

    public int getThuTuChuyenTauDangChon() {
        return thuTuChuyenTauDangChon;
    }

    public void setThuTuChuyenTauDangChon(int thuTuChuyenTauDangChon) {
        this.thuTuChuyenTauDangChon = thuTuChuyenTauDangChon;
    }

    private ArrayList<Cho> choChonList = new ArrayList<Cho>();

    public ArrayList<Cho> getChoChonList() {
        return choChonList;
    }

    public void setChoChonList(ArrayList<Cho> choChonList) {
        this.choChonList = choChonList;
    }


    @FXML
    void btnTimChuyenTauOnAction(ActionEvent event) {
            timDanhSachChuyenTau();
    }

    @FXML
    void btnBoChonTatCaOnAction(ActionEvent event) {
        choChonList.clear();
        capNhatCacChoDaChon();
    }

    @FXML
    void btnChonCaToaOnAction(ActionEvent event) {
        choChonList.clear();
        choControllerList.stream()
                .filter(controller ->
                        controller.getCho().getTrangThaiCho() == TrangThaiCho.CONTRONG &&
                                !controller.kiemTraVeTrongGio()
                )
                .map(Cho_DatVe_Controller::getCho)
                .forEach(choChonList::add);
        capNhatCacChoDaChon();
    }

    @FXML
    void btnThemVeDatOnAction(ActionEvent event) {
        try {
            themVeVaoGio();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnXoaTatCaVeDatTrongGioOnAction(ActionEvent event) {
        xoaTatCaVe();
    }

    @FXML
    void btnTiepTucOnAction(ActionEvent event) {
        if(hoaDonDatVe.getDanhSachVeDat().isEmpty()){
            main_Controller.showMessagesDialog("Trong giỏ không có vé đặt");
            return;
        }
        main_Controller.chuyenTrangThongTinDatVe(hoaDonDatVe);
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
        GaTau gaTauDi = cmbGaTauDi.getValue();
        GaTau gaTauDen = cmbGaTauDen.getValue();

        LocalDate ngayDi = dapNgayKhoiHanh.getValue();

        try {
            gaDi = gaTauDi;
            gaDen = gaTauDen;
            chuyenTauList = QuanLyChuyenTau_BUS.getDanhSachChuyenTauDatVe(gaDi.getMaGaTau(), gaDen.getMaGaTau(), ngayDi);
            hienThiDanhSachChuyenTau(chuyenTauList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void timDanhSachToaTau(String maChuyenTau){
        try {
            toaTauList = QuanLyChuyenTau_BUS.getDanhSachToaTau(maChuyenTau, gaDi.getMaGaTau(), gaDen.getMaGaTau());
            hienThiDanhSachToa(toaTauList);
        } catch (Exception e) {

        }
    }

    public void hienThiDanhSachChuyenTau(ArrayList<ChuyenTau> chuyenTauList) throws IOException {
        hboxDanhSachChuyenTau.getChildren().clear();
        hboxDanhSachToaTau.getChildren().clear();
        anpDanhSachCho.setVisible(false);
        grpDanhSachCho.setVisible(false);
        if(chuyenTauList.isEmpty()){
            main_Controller.showMessagesDialog("Không tìm thấy chuyến tàu");
            return;
        }
        chuyenTauControllerList.clear();
        int length = chuyenTauList.size();
        for (int i = 0; i < length; i++){
            ChuyenTau chuyenTau = chuyenTauList.get(i);
            ChiTietChuyenTau chiTietChuyenTauDi = QuanLyChuyenTau_BUS.getChiTietTuyenTauTheoChuyenTauVaGaTau(chuyenTau, gaDi);
            ChiTietChuyenTau chiTietChuyenTauDen = QuanLyChuyenTau_BUS.getChiTietTuyenTauTheoChuyenTauVaGaTau(chuyenTau, gaDen);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DatVe_GUI_Items/ChuyenTau_DatVe.fxml"));
            Parent anchorPane = loader.load();
            ChuyenTau_DatVe_Controller controller = loader.getController();
            chuyenTauControllerList.add(controller);
            controller.setDatVe_gui_controller(this);
            controller.setChiTietChuyenTauDi(chiTietChuyenTauDi);
            controller.setChiTietChuyenTauDen(chiTietChuyenTauDen);
            controller.setChuyenTau(chuyenTau);
            controller.khoiTao();



            controller.setSoThuTu(i);

            hboxDanhSachChuyenTau.getChildren().add(anchorPane);
        }
        chuyenTauControllerList.getFirst().chonChuyenTau();
    }

    public void hienThiDanhSachToa(ArrayList<ToaTau> toaTauList) throws IOException {
        hboxDanhSachToaTau.getChildren().clear();
        anpDanhSachCho.setVisible(false);
        grpDanhSachCho.setVisible(false);
        toaTauControllerList.clear();
        int length = toaTauList.size();
        for(int i = 0; i < length; i++){
            ToaTau toaTau = toaTauList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DatVe_GUI_Items/ToaTau_DatVe.fxml"));
            Parent anchorPane = loader.load();
            ToaTau_DatVe_Controller controller = loader.getController();
            toaTauControllerList.add(controller);
            controller.setDatVe_gui_controller(this);
            controller.setToaTau(toaTau);
            controller.khoiTao();
            controller.setSoThuTu(i);
            hboxDanhSachToaTau.getChildren().add(anchorPane);
        }
        thuTuToaTauDangChon = 0;
        toaTauControllerList.getFirst().chonToaTau();
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
        ChuyenTau_DatVe_Controller controller = chuyenTauControllerList.get(thuTuChuyenTauDangChon);
        double doDaiChang = controller.getChiTietChuyenTauDen().getSoKm() - controller.getChiTietChuyenTauDi().getSoKm();
        for(int i = 0; i < length; i++){
            Cho cho = choList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DatVe_GUI_Items/Cho_DatVe.fxml"));
            Parent anchorPane = loader.load();
            Cho_DatVe_Controller cho_controller = loader.getController();
            cho_controller.setDatVe_gui_controller(this);
            choControllerList.add(cho_controller);
            cho_controller.setCho(cho);

            cho_controller.khoiTao(doDaiChang);

            if(length == 64){
                grpDanhSachCho.add(anchorPane, i/4, i%4);
            }else if(length == 42){
                grpDanhSachCho.add(anchorPane, i/3, i%3);
            }else if(length == 28 || length == 14){
                grpDanhSachCho.add(anchorPane, i/2, i%2);
            }
        }
        choChonList.clear();
        capNhatCacChoDaChon();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scpDanhSachChuyenTau.setOnScroll(event -> {
            double deltaX = event.getDeltaY();
            scpDanhSachChuyenTau.setHvalue(scpDanhSachChuyenTau.getHvalue() - deltaX * 10 / scpDanhSachChuyenTau.getContent().getBoundsInLocal().getWidth());

            event.consume();
        });

        scpDanhSachToaTau.setOnScroll(event -> {
            double deltaX = event.getDeltaY();
            scpDanhSachToaTau.setHvalue(scpDanhSachToaTau.getHvalue() - deltaX * 10 / scpDanhSachToaTau.getContent().getBoundsInLocal().getWidth());
            event.consume();
        });

        scpDanhSachCho.setOnScroll(event -> {
            double deltaX = event.getDeltaY();
            scpDanhSachCho.setHvalue(scpDanhSachCho.getHvalue() - deltaX * 10 / scpDanhSachCho.getContent().getBoundsInLocal().getWidth());
            event.consume();
        });

        gaTauList = QuanLyChuyenTau_BUS.getDanhSachGaTau();
        cmbGaTauDen.getItems().clear();
        cmbGaTauDi.getItems().clear();
        for(GaTau gaTau : gaTauList){
            cmbGaTauDen.getItems().add(gaTau);
            cmbGaTauDi.getItems().add(gaTau);
        }
        cmbGaTauDi.getSelectionModel().select(42);
        cmbGaTauDen.getSelectionModel().select(17);

        dapNgayKhoiHanh.setValue(LocalDate.of(2024, 12, 13));

        anpDanhSachCho.setVisible(false);
        grpDanhSachCho.setVisible(false);

        //Combobox loại vé
        cmbLoaiVe.getItems().add(LoaiVe.VECANHAN);
        cmbLoaiVe.getItems().add(LoaiVe.VETAPTHE);
        cmbLoaiVe.getSelectionModel().select(0);

        hboxDanhSachToaTau.getChildren().clear();
        hboxDanhSachChuyenTau.getChildren().clear();

        vboxGioVeDat.getChildren().clear();

        txtTongTien.setText(CurrencyFormat.currencyFormat(0));


        dapNgayKhoiHanh.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                if (date.isBefore(today)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #D3D3D3;");
                }
                if (date.equals(today)) {
                    setStyle("-fx-background-color: #FF6347;");
                }
            }
        });

        cmbGaTauDi.getEditor().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                return;
            }
            cmbGaTauDi.getItems().clear();
            String tenGaTau = cmbGaTauDi.getEditor().getText();
            for (GaTau gaTau : gaTauList){
                if(gaTau.getTenGaTau().toLowerCase().startsWith(tenGaTau.toLowerCase())){
                    cmbGaTauDi.show();
                    cmbGaTauDi.getItems().add(gaTau);
                }
            }
            if (event.getCode() == KeyCode.TAB) {
                cmbGaTauDi.getSelectionModel().selectFirst();
            }

        });

        Platform.runLater(()->{
            try {
                if(main_Controller.getPhieuKetToan() == null || main_Controller.getPhieuKetToan().getCaLamViec() == null){
                    main_Controller.thongBaoKhongHoatDong("Chưa tạo ca làm việc, vui lòng tạo phiếu kiểm tiền đầu ca để tạo ca làm việc\n" +
                            "Vào mục Báo Cáo Và Thống Kê → Báo Cao → Tạo Phiếu Kiểm Tiền Dầu Ca để tạo ca");
                    return;
                }

                if(main_Controller.getPhieuKetToan() != null && main_Controller.getPhieuKetToan().getPhieuKiemTienCuoiCa() != null){
                    main_Controller.thongBaoKhongHoatDong("Ca làm việc đã kế thúc. \nVui lòng tạo ca làm việc mới");
                    return;
                }

                if(hoaDonDatVe == null){
                    String maHoaDon = QuanLyHoaDon_BUS.layMaHoaDonDatVeTiepTheo();
                    hoaDonDatVe = new HoaDonDatVe(maHoaDon);
                    hoaDonDatVe.setCaLamViec(main_Controller.getPhieuKetToan().getCaLamViec());
                    hoaDonDatVe.setDanhSachVeDat(new ArrayList<VeDat>());
                }else{
                    capNhatGioVe();

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void boChonTatCaChuyenTau(){
        for (ChuyenTau_DatVe_Controller chuyenTau_Controller : chuyenTauControllerList){
            chuyenTau_Controller.chinhMauKhongChon();
        }
    }

    public void boChonTatCaToaTau(){
        for(ToaTau_DatVe_Controller toaTau_Controller : toaTauControllerList){
            toaTau_Controller.chinhMauKhongChon();
        }
    }

    public void boChonTatCaVe(){
        for(VeDat_DatVe_Controller ve_controller : veDatControllerList){
            ve_controller.khongChonVe();
        }
    }


    public void capNhatCacChoDaChon(){
        for (Cho_DatVe_Controller controller : choControllerList) {
            controller.capNhatTrangThai();
        }
    }

    public void themVeVaoGio() throws IOException {
        if(choChonList.isEmpty()){
            return;
        }

        LoaiVe loaiVe = cmbLoaiVe.getValue();
        if(loaiVe == LoaiVe.VECANHAN){
            for(Cho cho : choChonList){

                ChuyenTau_DatVe_Controller chuyenTau_Controller = chuyenTauControllerList.get(thuTuChuyenTauDangChon);
                ChiTietChuyenTau chiTietChuyenTauDi = chuyenTau_Controller.getChiTietChuyenTauDi();
                ChiTietChuyenTau chiTietChuyenTauDen = chuyenTau_Controller.getChiTietChuyenTauDen();
                chiTietChuyenTauDi.setGaTau(gaDi);
                chiTietChuyenTauDen.setGaTau(gaDen);

                String maVeDatMoi = hoaDonDatVe.getDanhSachVeDat().isEmpty()
                        ? QuanLyVeDat_BUS.taoMaVeDatMoi(LoaiVe.VECANHAN)
                        : QuanLyVeDat_BUS.taoMaVeDatTiepTheo(hoaDonDatVe.getDanhSachVeDat().getLast(), LoaiVe.VECANHAN);

                VeDat veDat = new VeDat(maVeDatMoi, hoaDonDatVe, chiTietChuyenTauDi, chiTietChuyenTauDen);
                veDat.setLoaiVe(LoaiVe.VECANHAN);
                cho.setToaTau(toaTauList.get(thuTuToaTauDangChon));
                veDat.setTrangThaiVeDat(TrangThaiVeDat.CHOLAYVE);
                ChiTietVeDat chiTietVeDat = new ChiTietVeDat(veDat, cho, cho.getGiaCho());



                ArrayList<ChiTietVeDat> danhSachChiTietVeDat = new ArrayList<ChiTietVeDat>();
                danhSachChiTietVeDat.add(chiTietVeDat);
                veDat.setDanhSachChiTietVeDat(danhSachChiTietVeDat);
                hoaDonDatVe.getDanhSachVeDat().add(veDat);

            }
        }else if(loaiVe == LoaiVe.VETAPTHE){
            if(choChonList.size() < 5){
                main_Controller.showMessagesDialog("Vé tập thể phải từ 5 người trở lên");
                System.out.println("Vé tập thể phải từ 5 người trở lên");
                return;
            }
            ChuyenTau_DatVe_Controller chuyenTau_Controller = chuyenTauControllerList.get(thuTuChuyenTauDangChon);
            ChiTietChuyenTau chiTietChuyenTauDi = chuyenTau_Controller.getChiTietChuyenTauDi();
            ChiTietChuyenTau chiTietChuyenTauDen = chuyenTau_Controller.getChiTietChuyenTauDen();

            String maVeDatMoi = hoaDonDatVe.getDanhSachVeDat().isEmpty()
                    ? QuanLyVeDat_BUS.taoMaVeDatMoi(LoaiVe.VETAPTHE)
                    : QuanLyVeDat_BUS.taoMaVeDatTiepTheo(hoaDonDatVe.getDanhSachVeDat().getLast(), LoaiVe.VETAPTHE);

            VeDat veDat = new VeDat(maVeDatMoi, hoaDonDatVe, chiTietChuyenTauDi, chiTietChuyenTauDen);
            veDat.setLoaiVe(LoaiVe.VETAPTHE);
            veDat.setTrangThaiVeDat(TrangThaiVeDat.CHOLAYVE);

            ArrayList<ChiTietVeDat> danhSachChiTietVeDat = new ArrayList<ChiTietVeDat>();
            for(Cho cho : choChonList){
                ChiTietVeDat chiTietVeDat = new ChiTietVeDat(veDat, cho, cho.getGiaCho());
                danhSachChiTietVeDat.add(chiTietVeDat);
            }
            veDat.setDanhSachChiTietVeDat(danhSachChiTietVeDat);
            hoaDonDatVe.getDanhSachVeDat().add(veDat);
        }
        hienThiTongTienHoaDonBanVe();
        choChonList.clear();
        capNhatGioVe();
        capNhatCacChoDaChon();
    }

    public void capNhatGioVe() throws IOException {
        vboxGioVeDat.getChildren().clear();
        veDatControllerList.clear();
        int i = 0;
        for (VeDat veDat : hoaDonDatVe.getDanhSachVeDat()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DatVe_GUI_Items/VeDat_DatVe.fxml"));
            Parent anchorPane = loader.load();
            VeDat_DatVe_Controller controller = loader.getController();
            veDatControllerList.add(controller);
            controller.setDatVe_gui_controller(this);

            controller.setVeDat(veDat);
            controller.setSoThuTu(i);
            controller.khoiTao();


            vboxGioVeDat.getChildren().add(anchorPane);
            i++;
        }

        if(!veDatControllerList.isEmpty())
            veDatControllerList.getFirst().chonVe();
    }


    public void xoaVeDat(VeDat veDatXoa){
        hoaDonDatVe.getDanhSachVeDat().remove(veDatXoa);
        try {
            capNhatGioVe();
            capNhatCacChoDaChon();
            hienThiTongTienHoaDonBanVe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void xoaTatCaVe(){
        hoaDonDatVe.getDanhSachVeDat().clear();
        try {
            capNhatGioVe();
            capNhatCacChoDaChon();
            hienThiTongTienHoaDonBanVe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void hienThiTongTienHoaDonBanVe(){
        txtTongTien.setText(CurrencyFormat.currencyFormat(hoaDonDatVe.tongTienCuoi()));
    }
}
