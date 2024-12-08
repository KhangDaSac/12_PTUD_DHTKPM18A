package GUI.controllers;

import BUS.QuanLyChuyenTau_BUS;
import BUS.QuanLyVe_BUS;
import DAO.ChiTietVe_DAO;
import DAO.ChuyenTau_DAO;
import DAO.HoaDon_DAO;
import DAO.Ve_DAO;
import DTO.*;
import GUI.controllers.DoiVe_GUI_Items.Cho_DoiVe_Controller;
import GUI.controllers.DoiVe_GUI_Items.ChuyenTau_DoiVe_Controller;
import GUI.controllers.DoiVe_GUI_Items.ToaTau_DoiVe_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utils.CreatePDF;
import utils.CurrencyFormat;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DoiVe_GUI_Controller implements Initializable {

    @FXML
    private HBox add1ChuyenTau;

    @FXML
    private AnchorPane anpDanhSachCho;

    @FXML
    private AnchorPane anpToaTauSau;

    @FXML
    private AnchorPane anpToaTauTruoc;


    @FXML
    private JFXButton btnDatLaiTrang;

    @FXML
    private JFXButton btnDoiVe;

    @FXML
    private JFXButton btnTimVe;

    @FXML
    private GridPane grpDanhSachCho;

    @FXML
    private HBox hboxDanhSachToaTau;

    @FXML
    private Label lblCCCD;

    @FXML
    private Label lblTongTien;

    @FXML
    private Label lblCho_Cu;

    @FXML
    private Label lblCho_Moi;

    @FXML
    private Label lblGiaCho_Cu;

    @FXML
    private Label lblGiaCho_Moi;

    @FXML
    private Label lblGiaCu_ChiTietVeDoi;

    @FXML
    private Label lblGiaMoi_ChiTietVeDoi;

    @FXML
    private Label lblLoaiKH;

    @FXML
    private Label lblTenKH;

    @FXML
    private Label lblToaTau_Cu;

    @FXML
    private Label lblToaTau_Moi;

    @FXML
    private TextField txtMaVe;

    @FXML
    private TextField txtTongTien;

    private int trangChuyenTauHienTai;

    private GaTau gaDi;
    private GaTau gaDen;
    private Ve veKhachHang = new Ve();
    private ChiTietVe ctVe = new ChiTietVe();
    private Ve veMoi;
    private ChiTietVe ctVeMoi;
    private HoaDonDoiVe hoaDonDoiVe;
    private ChuyenTau_DoiVe_Controller chuyenTauController = new ChuyenTau_DoiVe_Controller();
    private ChuyenTau chuyenTauKH = new ChuyenTau();
    private ToaTau toaTauKH = new ToaTau();
    private Cho choKH = new Cho();
    private Cho choChon = new Cho();

    private ArrayList<GaTau> gaTauList;
    private ArrayList<ToaTau> toaTauList;
    private ArrayList<Cho> choList;

    private ArrayList<ToaTau_DoiVe_Controller> toaTauControllerList = new ArrayList<ToaTau_DoiVe_Controller>();
    private ArrayList<Cho_DoiVe_Controller> choControllerList = new ArrayList<Cho_DoiVe_Controller>();

    private int toaTauDangChon;
    private int trangToaTauHienTai;
    private int chuyenTauDangChon;
    private boolean dangChon;

    private Main_Controller main_Controller;

    public Main_Controller getMain_Controller() {
        return main_Controller;
    }

    public void setMain_Controller(Main_Controller main_Controller) {
        this.main_Controller = main_Controller;
    }

    public Cho getChoChon() {
        return choChon;
    }

    public void setChoChon(Cho choChon) {
        this.choChon = choChon;
    }

    public void setLblToaTau_Moi(String maToaTau) {
        lblToaTau_Moi.setText(maToaTau);
    }

    public void setLblGiaCho_Moi(double giaChoMoi) {
        if (giaChoMoi == 0.0) {
            lblGiaCho_Moi.setText("");
            lblGiaMoi_ChiTietVeDoi.setText("");
        } else {
            lblGiaCho_Moi.setText(CurrencyFormat.currencyFormat(giaChoMoi));
            lblGiaMoi_ChiTietVeDoi.setText(CurrencyFormat.currencyFormat(giaChoMoi));
        }

    }

    public void setLblCho_Moi(int choMoi) {
        if (choMoi == 0) {
            lblCho_Moi.setText("");
        } else {
            lblCho_Moi.setText(String.format("%d", choMoi));
        }

    }

    public void setChuyenTauDangChon(int chuyenTauDangChon) {
        this.chuyenTauDangChon = chuyenTauDangChon;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add1ChuyenTau.setVisible(false);
        anpToaTauTruoc.setVisible(false);
        anpToaTauSau.setVisible(false);
        anpDanhSachCho.setVisible(false);
        grpDanhSachCho.setVisible(false);
        hboxDanhSachToaTau.getChildren().clear();
        txtTongTien.setText(CurrencyFormat.currencyFormat(0));

    }

    public void setToaTauDangChon(int toaTauDangChon) {
        this.toaTauDangChon = toaTauDangChon;
        lblToaTau_Moi.setText(toaTauKH.getMaToaTau());
    }

    @FXML
    void anpToaTauSauOnMouseClicked(MouseEvent event) {
        int lengthList = toaTauList.size();
        if (trangToaTauHienTai < Math.ceil(lengthList / 9.0)) {
            trangToaTauHienTai++;
            try {
                toaTauDangChon++;
                int batDau = Math.max(9 * (trangToaTauHienTai - 1), 0);
                int ketThuc = Math.min(9 * trangToaTauHienTai, lengthList);
                hienThiDanhSachToa(toaTauList, batDau, ketThuc);
                toaTauControllerList.get(0).chonToaTau_DoiVe();
                lblToaTau_Moi.setText(toaTauList.get(toaTauDangChon).getMaToaTau());
                anpToaTauSau.setVisible(lengthList > ketThuc);
                anpToaTauTruoc.setVisible(batDau > 0);
                capNhatCacChoDaChon();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void anpToaTauTruocOnMouseClicked(MouseEvent event) {
        int lengthList = toaTauList.size();
        if (trangToaTauHienTai > 1) {
            trangToaTauHienTai--;
            try {
                int batDau = Math.max(9 * (trangToaTauHienTai - 1), 0);
                int ketThuc = Math.min(9 * trangToaTauHienTai, lengthList);
                hienThiDanhSachToa(toaTauList, batDau, ketThuc);
                toaTauControllerList.get(0).chonToaTau_DoiVe();
                lblToaTau_Moi.setText(toaTauList.getFirst().getMaToaTau());
                anpToaTauSau.setVisible(lengthList > ketThuc);
                anpToaTauTruoc.setVisible(batDau > 0);
                capNhatCacChoDaChon();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void doiVe(){
        LocalDate ngayHienTai1 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String ngayHienTai = ngayHienTai1.format(formatter);
        String maVeMoi,maHoaDonMoi;
        String maVeLonNhatNgayHienTai = Ve_DAO.layMaVeCaNhanLonNhatCuaNgayHienTai(ngayHienTai);
        String maHoaDonLonNhatNGayHienTai = HoaDon_DAO.layMaHoaDonDoiLonNhatCuaNgayHienTai(ngayHienTai);
        if (maVeLonNhatNgayHienTai ==null){
            maVeMoi= "VECN"+ ngayHienTai +"00000001";
        }else {
            String soThuTuVeCu = maVeLonNhatNgayHienTai.substring(maVeLonNhatNgayHienTai.length()-8);
            int soThuTuMoiVe = Integer.parseInt(soThuTuVeCu)+1;
            maVeMoi ="VECN"+ngayHienTai+ String.format("%08d",soThuTuMoiVe);
        }
        if (maHoaDonLonNhatNGayHienTai==null){
            maHoaDonMoi = "HDDO" + ngayHienTai+"000001";
        }else {
            String soThuTuHDCu = maHoaDonLonNhatNGayHienTai.substring(maHoaDonLonNhatNGayHienTai.length()-6);
            int soThuTuMoiHD = Integer.parseInt(soThuTuHDCu)+1;
            maHoaDonMoi = "HDDO"+ngayHienTai+ String.format("%06d",soThuTuMoiHD);
        }
        veMoi = new Ve(maVeMoi,veKhachHang.getHoaDonBanVe(),veKhachHang.getThongTinGaTauDi(),veKhachHang.getThongTinGaTauDen(),LoaiVe.VECANHAN,TrangThaiVe.DADOI);
        ctVeMoi = new ChiTietVe(veMoi,new Cho(choChon.getMaCho()),ctVe.getKhachHang(),choChon.getGiaCho(),ctVe.getPhanTramGiamGia());
        hoaDonDoiVe = new HoaDonDoiVe(maHoaDonMoi,LocalDateTime.now(),veKhachHang,veMoi,new CaLamViec("CLV30112024C"));

        

        if(Ve_DAO.themVeMoi(veMoi,chuyenTauKH.getMaChuyenTau())){
            ChiTietVe_DAO.themChiTietVeMoi(ctVeMoi);
            HoaDon_DAO.themHoaDonDoiVe(hoaDonDoiVe);
            Ve_DAO.capNhatTrangThaiHuyChoVeDoi(veKhachHang.getMaVe());
//            CreatePDF.taoHoaDonDoiVe(hoaDonDoiVe,ctVe);
        }

    }

    public void tinhTongTien() {
        double giaChoCu = Double.parseDouble(lblGiaCu_ChiTietVeDoi.getText().replaceAll("[^\\d.]", ""));
        double giaChoMoi = Double.parseDouble(lblGiaMoi_ChiTietVeDoi.getText().replaceAll("[^\\d.]", ""));
        double tongTien = 0;
        if (giaChoMoi > (giaChoCu + 20000)) {
            tongTien = Math.abs(giaChoCu - giaChoMoi - 20000);
            txtTongTien.setText(CurrencyFormat.currencyFormat(tongTien));
            lblTongTien.setText("Thu thêm:");
        } else if (giaChoMoi < (giaChoCu + 20000)) {
            tongTien = Math.abs(giaChoMoi + 20000 - giaChoCu);
            txtTongTien.setText(CurrencyFormat.currencyFormat(tongTien));
            lblTongTien.setText("Trả lại khách:");
        } else {
            lblTongTien.setText("Tổng tiền");
            txtTongTien.setText(CurrencyFormat.currencyFormat(tongTien));
        }
    }

    @FXML
    void btnDoiVeOnAction(ActionEvent event) throws IOException {
        QuanLyVe_BUS quanLyVeBus = new QuanLyVe_BUS();
        if (!(choChon.getSoCho() > 0)) {
            main_Controller.showMessagesDialog("Chọn chỗ cần đổi!");
        } else {
            //quanLyVeBus.doiVe(veKhachHang.getMaVe(), ctVe.getCho().getMaCho(), choChon.getMaCho(), choChon.getGiaCho());
            doiVe();
            //timDanhSachCho(choChon.getToaTau().getMaToaTau());
            hienThiChuyenTau(chuyenTauKH);
            //main_Controller.showMessagesDialog("Đổi vé thành công!");
        }
        capNhatCacChoDaChon();
    }

    @FXML
    void btnTimVeOnAction(ActionEvent event) throws IOException {
        String maTim = txtMaVe.getText();
        QuanLyChuyenTau_BUS chuyenTauBus= new QuanLyChuyenTau_BUS();
        QuanLyVe_BUS ctVeBus = new QuanLyVe_BUS();
        if(ktMaVeNhap(maTim)){
            QuanLyVe_BUS veBus = new QuanLyVe_BUS();
            veKhachHang = veBus.getVeTheoMa(maTim);
        }else {
            return;
        }
        LocalDateTime thoiGianDi = veKhachHang.getThongTinGaTauDi().getThoiGianDi();
        LocalDateTime thoiGianHienTai = LocalDateTime.now();
        if (veKhachHang == null) {
            main_Controller.showMessagesDialog("Không tìm thấy vé với mã: " + maTim);
            txtMaVe.requestFocus();

        } else if (veKhachHang.getLoaiVe() != LoaiVe.VECANHAN) {
            main_Controller.showMessagesDialog("Phải là vé cá nhân");
            txtMaVe.requestFocus();

        } else
            if (thoiGianDi.isBefore(thoiGianHienTai)){
            main_Controller.showMessagesDialog("Chuyến tàu của vé đã khởi hành!");
            txtMaVe.requestFocus();
        }else
            if (!veKhachHang.getTrangThaiVe().equals(TrangThaiVe.DANGSUDUNG)){
            main_Controller.showMessagesDialog("Trạng thái vé không hợp lệ!");
            txtMaVe.requestFocus();
            }else
            {
            gaDi = veKhachHang.getThongTinGaTauDi().getGaTau();
            gaDen = veKhachHang.getThongTinGaTauDen().getGaTau();

            chuyenTauKH = chuyenTauBus.timChuyenTauTheoMaVe(veKhachHang.getMaVe());
            ctVe = ctVeBus.getCTVeTheoMaVe(veKhachHang.getMaVe());

            lblToaTau_Cu.setText(ctVe.getCho().getToaTau().getMaToaTau());
            lblCho_Cu.setText(String.format("%d", ctVe.getCho().getSoCho()));
            lblGiaCho_Cu.setText(CurrencyFormat.currencyFormat(ctVe.getGiaCho()));
            lblTenKH.setText(ctVe.getKhachHang().getTenKhachHang());
            lblCCCD.setText(ctVe.getKhachHang().getCCCD());
            lblLoaiKH.setText(ctVe.getKhachHang().getLoaiKhachHang().getTenLoaiKhachHang());
            lblGiaCu_ChiTietVeDoi.setText(CurrencyFormat.currencyFormat(ctVe.getGiaCho()));
            trangChuyenTauHienTai = 1;
            add1ChuyenTau.setVisible(true);
            hienThiChuyenTau(chuyenTauKH);
        }
    }

    public boolean ktMaVeNhap( String maTim) {
        if (maTim.equals("")) {
            main_Controller.showMessagesDialog("Vui lòng nhập mã vé!");
            txtMaVe.requestFocus();
            return false;
        }
//        else if (!maTim.matches("^VE\\d{16}$")) {
//            main_Controller.showMessagesDialog("Vé không hợp lệ. Định dạng đúng là 'VEXXXXXXXXXXXXXXXX' với X là chữ số.");
//            txtMaVe.requestFocus();
//            return false;
//        }
        return  true;
    }

    public void hienThiChuyenTau(ChuyenTau chuyenTau) throws IOException {
        add1ChuyenTau.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DoiVe_GUI_Items/ChuyenTau.fxml"));
        Parent anchorPane = loader.load();
        chuyenTauController = loader.getController();
        chuyenTauController.setDoiVe_gui_controller(this);
        chuyenTauController.setChuyenTau(chuyenTau);
        chuyenTauController.setChiTietChuyenTauDi(QuanLyChuyenTau_BUS.getChiTietTuyenTauTheoChuyenTauVaGaTau(chuyenTau, gaDi));
        chuyenTauController.setChiTietChuyenTauDen(QuanLyChuyenTau_BUS.getChiTietTuyenTauTheoChuyenTauVaGaTau(chuyenTau, gaDen));
        chuyenTauController.khoiTao();
        chuyenTauController.chon1ChuyenTau();
        add1ChuyenTau.getChildren().add(anchorPane);
    }

    public void timDanhSachToaTau(String maChuyenTau){
        try {
            toaTauList = QuanLyChuyenTau_BUS.getDanhSachToaTau(maChuyenTau, veKhachHang.getThongTinGaTauDi().getGaTau().getMaGaTau(), veKhachHang.getThongTinGaTauDen().getGaTau().getMaGaTau());
            anpToaTauTruoc.setVisible(false);
            anpToaTauSau.setVisible(toaTauList.size() > 9);
            trangToaTauHienTai = 1;
            hienThiDanhSachToa(toaTauList, 0, 9);
        } catch (Exception e) {

        }
    }

    public void hienThiDanhSachToa(ArrayList<ToaTau> toaTauList, int batDau, int ketThuc) throws IOException {
        hboxDanhSachToaTau.getChildren().clear();
        anpDanhSachCho.setVisible(false);
        grpDanhSachCho.setVisible(false);
        toaTauControllerList.clear();

        for(int i = batDau; i < ketThuc; i++){
            ToaTau toaTau = toaTauList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DoiVe_GUI_Items/ToaTau.fxml"));
            Parent anchorPane = loader.load();

            ToaTau_DoiVe_Controller controller = loader.getController();
            toaTauControllerList.add(controller);
            controller.setDoiVe_gui_controller(this);
            controller.setToaTau(toaTau);
            controller.khoiTao();
            controller.setSoThuTu(i);
            toaTauDangChon = 0;
            if (toaTau.getMaToaTau().equals(ctVe.getCho().getToaTau().getMaToaTau())){
                lblToaTau_Moi.setText(ctVe.getCho().getToaTau().getMaToaTau());
                toaTauDangChon = i;
                controller.chonToaTau_DoiVe();
            }

            hboxDanhSachToaTau.getChildren().add(anchorPane);
        }
    }

    public void boChonTatCaToaTau(){
        for(ToaTau_DoiVe_Controller toaTau_Controller : toaTauControllerList){
            toaTau_Controller.chinhMauKhongChon();
        }
    }

    public void timDanhSachCho(String maToaTau){
        choList = QuanLyChuyenTau_BUS.getDanhSachChoTheoMaToaTau(maToaTau, veKhachHang.getThongTinGaTauDi().getGaTau().getMaGaTau(), veKhachHang.getThongTinGaTauDen().getGaTau().getMaGaTau());
        try {
            hienThiDanhSachCho(choList);
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

        double doDaiChang = chuyenTauController.getChiTietChuyenTauDen().getSoKm() - chuyenTauController.getChiTietChuyenTauDi().getSoKm();

        for(int i = 0; i < length; i++){
            Cho cho = choList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DoiVe_GUI_Items/Cho.fxml"));
            Parent anchorPane = loader.load();
            Cho_DoiVe_Controller cho_controller = loader.getController();
            cho_controller.setDoiVe_gui_controller(this);
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
        capNhatChoDaChon();
    }

    public void capNhatChoDaChon(){

        for (Cho_DoiVe_Controller controller : choControllerList) {
            controller.chuyenMauMacDinh();

            if (choKH.getTrangThaiCho()==TrangThaiCho.CONTRONG) {
                controller.chuyenMauDangChon();
            }
            if (choKH.getTrangThaiCho()==TrangThaiCho.DABAN) {
                controller.chuyenMauDangChon();
            }
            if (choKH.getTrangThaiCho()==TrangThaiCho.DANHCHOCHANGDAIHON) {
                controller.chuyenMauDangChon();
            }
            if (choKH.getTrangThaiCho()==TrangThaiCho.DADAT) {
                controller.chuyenMauDangChon();
            }
            controller.capNhatTrangThai();
        }
    }

    public void capNhatCacChoDaChon(){
        for (Cho_DoiVe_Controller controller : choControllerList) {
            controller.chuyenMauMacDinh();
             dangChon = choChon.equals(controller.getCho());

            if (dangChon) {
                controller.chuyenMauDangChon();
                lblCho_Moi.setText(String.format("%d",choChon.getSoCho()));
            }
            controller.capNhatTrangThai();
        }
    }

}

