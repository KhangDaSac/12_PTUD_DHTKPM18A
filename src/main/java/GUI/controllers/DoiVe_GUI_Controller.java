package GUI.controllers;

import BUS.QuanLyChuyenTau_BUS;
import BUS.QuanLyVe_BUS;
import DAO.ChiTietVe_DAO;
import DAO.Cho_DAO;
import DAO.ChuyenTau_DAO;
import DAO.ToaTau_DAO;
import DTO.*;
import GUI.controllers.BanVe_GUI_Items.Cho_Controller;
import GUI.controllers.BanVe_GUI_Items.ChuyenTau_Controller;
import GUI.controllers.BanVe_GUI_Items.ToaTau_Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import utils.CurrencyFormat;

import java.io.IOException;
import java.net.URL;
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
    private ComboBox<String> cmbLoaiVe;

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
    private ChuyenTau chuyenTauKH = new ChuyenTau();
    private ToaTau toaTauKH =new ToaTau();
    private Cho choKH = new Cho();
    private Cho choChon = new Cho();

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
        if (giaChoMoi ==0.0){
            lblGiaCho_Moi.setText("");
            lblGiaMoi_ChiTietVeDoi.setText("");
        }else {
            lblGiaCho_Moi.setText(String.format("%.3fVNĐ",giaChoMoi));
            lblGiaMoi_ChiTietVeDoi.setText(String.format("%.3fVNĐ",giaChoMoi));
        }

    }

    public void setLblCho_Moi(int choMoi) {
        if(choMoi==0){
            lblCho_Moi.setText("");
        }else {
            lblCho_Moi.setText(String.format("%d",choMoi));
        }

    }

    private ArrayList<GaTau> gaTauList;
    private ArrayList<ToaTau> toaTauList;
    private ArrayList<Cho> choList;

    private ArrayList<ToaTau_Controller> toaTauControllerList = new ArrayList<ToaTau_Controller>();
    private ArrayList<Cho_Controller> choControllerList = new ArrayList<Cho_Controller>();

    private int toaTauDangChon;
    private int trangToaTauHienTai;
    private int chuyenTauDangChon;
    private boolean dangChon;

    public void setChuyenTauDangChon(int chuyenTauDangChon) {
        this.chuyenTauDangChon = chuyenTauDangChon;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cho_Controller.loaiTrang("DoiVe_GUI.fxml");
        ToaTau_Controller.loaiTrang("DoiVe_GUI.fxml");
        ChuyenTau_Controller.loaiTrang("DoiVe_GUI.fxml");
        add1ChuyenTau.setVisible(false);
        anpToaTauTruoc.setVisible(false);
        anpToaTauSau.setVisible(false);
        anpDanhSachCho.setVisible(false);
        grpDanhSachCho.setVisible(false);
        hboxDanhSachToaTau.getChildren().clear();
        txtTongTien.setText(CurrencyFormat.currencyFormat(0));
        cmbLoaiVe.setValue(LoaiVe.VECANHAN.toString());

    }

    public void setToaTauDangChon(int toaTauDangChon) {
        this.toaTauDangChon = toaTauDangChon;
        lblToaTau_Moi.setText(toaTauKH.getMaToaTau());
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
        if(trangToaTauHienTai > 1){
            trangToaTauHienTai--;
            try {
                int batDau = Math.max(9*(trangToaTauHienTai - 1), 0);
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

//    @FXML
//    void btnDatLaiTrangOnAcTion(ActionEvent event) {
//        dangChon = false;
//        capNhatCacChoDaChon();
//    }

    @FXML
    void btnDoiVeOnAction(ActionEvent event) {
        double giaChoCu = Double.parseDouble(lblGiaCu_ChiTietVeDoi.getText().replace("VNĐ","").trim());
        double giaChoMoi = Double.parseDouble(lblGiaMoi_ChiTietVeDoi.getText().replace("VNĐ","").trim());
        double tongTien;
        QuanLyVe_BUS quanLyVeBus = new QuanLyVe_BUS();
        if (choKH.getGiaCho()>(choChon.getGiaCho()+50)){
            tongTien = giaChoCu - giaChoMoi -50;
            txtTongTien.setText(String.format("%.3fVNĐ",tongTien));
            lblTongTien.setText("Trả lại khách:");
        }else if (choKH.getGiaCho()>(choChon.getGiaCho()+50)){
            tongTien = giaChoMoi + 50 - giaChoCu;
            txtTongTien.setText(String.format("%.3fVNĐ",tongTien));
            lblTongTien.setText("Thu thêm:");
        }else {
            lblTongTien.setText("Tổng tiền");
            txtTongTien.setText("0VNĐ");
        }

        if (choChon.getSoCho()>0){
        choChon.setTrangThaiCho(TrangThaiCho.DADATHOACBAN);
        choKH.setTrangThaiCho(TrangThaiCho.CONTRONG);
        capNhatCacChoDaChon();
        quanLyVeBus.doiVe( veKhachHang.getMaVe(),ctVe.getCho().getMaCho(),choChon.getMaCho());
        }else {
            System.out.printf("chọn chỗ cần đổi");
        }
    }

    @FXML
    void btnTimVeOnAction(ActionEvent event) throws IOException {
        String maTim = txtMaVe.getText();
        QuanLyVe_BUS veBus = new QuanLyVe_BUS();
        if (maTim.equals("")) {
            System.out.println("Vui lòng nhập mã vé!!!");
            txtMaVe.requestFocus();
        } else if (!maTim.matches("^V\\d{12}$")) {
            System.out.println("Vé không hợp lệ. Định dạng đúng là 'VXXXXXXXXXXXX' với X là chữ số.");
            txtMaVe.requestFocus();
        } else {
            veKhachHang = veBus.getVeTheoMa(maTim);
            if (veKhachHang != null) {
                if (veKhachHang.getLoaiVe() == LoaiVe.VECANHAN) {
                    gaDi = veKhachHang.getThongTinGaTauDi().getGaTau();
                    gaDen = veKhachHang.getThongTinGaTauDen().getGaTau();
                    chuyenTauKH = ChuyenTau_DAO.timChuyenTauTheoMa(veKhachHang.getChuyenTau().getMaChuyenTau());
                    ctVe = ChiTietVe_DAO.getCTVeTheoMaVe(veKhachHang.getMaVe());

                    lblToaTau_Cu.setText(ctVe.getCho().getToaTau().getMaToaTau());
                    lblCho_Cu.setText(String.format("%d", ctVe.getCho().getSoCho()));
                    lblGiaCho_Cu.setText(String.format("%.3f VNĐ", ctVe.getGiaCho()));
                    lblTenKH.setText(ctVe.getKhachHang().getTenKhachHang());
                    lblCCCD.setText(ctVe.getKhachHang().getCCCD());
                    lblLoaiKH.setText(ctVe.getKhachHang().getLoaiKhachHang().getTenLoaiKhachHang());
                    lblGiaCu_ChiTietVeDoi.setText(String.format("%.3f VNĐ", ctVe.getGiaCho()));
                    trangChuyenTauHienTai = 1;
                    add1ChuyenTau.setVisible(true);
                    hienThiChuyenTau(chuyenTauKH);
                } else {
                    System.out.println("Phải là vé cá nhân!");
                    txtMaVe.requestFocus();
                }
            } else {
                System.out.println("Không tìm thấy vé với mã: " + maTim);
                txtMaVe.requestFocus();
            }
        }

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
        double doDaiChang = veKhachHang.getThongTinGaTauDen().getSoKm() - veKhachHang.getThongTinGaTauDi().getSoKm();
        for(int i = 0; i < length; i++){
            Cho cho = choList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/Cho.fxml"));
            Parent anchorPane = loader.load();
            Cho_Controller cho_controller = loader.getController();
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

    public void hienThiDanhSachToa(ArrayList<ToaTau> toaTauList, int batDau, int ketThuc) throws IOException {
        hboxDanhSachToaTau.getChildren().clear();
        anpDanhSachCho.setVisible(false);
        grpDanhSachCho.setVisible(false);
        toaTauControllerList.clear();

        for(int i = batDau; i < ketThuc; i++){
            ToaTau toaTau = toaTauList.get(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ToaTau.fxml"));
            Parent anchorPane = loader.load();

            ToaTau_Controller controller = loader.getController();
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

    public void hienThiChuyenTau(ChuyenTau chuyenTau) throws IOException {
        add1ChuyenTau.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BanVe_GUI_Items/ChuyenTau.fxml"));
        Parent anchorPane = loader.load();
        ChuyenTau_Controller controller = loader.getController();
        controller.setDoiVe_gui_controller(this);
        controller.setChuyenTau(chuyenTau);
        controller.setChiTietChuyenTauDi(QuanLyChuyenTau_BUS.getChiTietTuyenTauTheoChuyenTauVaGaTau(chuyenTau, gaDi));
        controller.setChiTietChuyenTauDen(QuanLyChuyenTau_BUS.getChiTietTuyenTauTheoChuyenTauVaGaTau(chuyenTau, gaDen));
        controller.khoiTao();
        controller.chon1ChuyenTau();
        add1ChuyenTau.getChildren().add(anchorPane);
    }

    public void boChonTatCaToaTau(){
        for(ToaTau_Controller toaTau_Controller : toaTauControllerList){
            toaTau_Controller.chinhMauKhongChon();
        }
    }

    public void capNhatChoDaChon(){

        for (Cho_Controller controller : choControllerList) {
            controller.chuyenMauMacDinh();

            if (choKH.getTrangThaiCho()==TrangThaiCho.CONTRONG) {
                controller.chuyenMauDangChon();
            }
            if (choKH.getTrangThaiCho()==TrangThaiCho.DADATHOACBAN) {
                controller.chuyenMauDangChon();
            }
            controller.capNhatTrangThai();
        }
    }

    public void capNhatCacChoDaChon(){
        for (Cho_Controller controller : choControllerList) {
            controller.chuyenMauMacDinh();

             dangChon = choChon.equals(controller.getCho());

            if (dangChon) {
                controller.chuyenMauDangChon();
                lblCho_Moi.setText(String.format("%d",choChon.getSoCho()));
            }
            if (choKH.getTrangThaiCho()==TrangThaiCho.CONTRONG){
                controller.chuyenMauMacDinh();
            }
            if (choChon.getTrangThaiCho()==TrangThaiCho.DADATHOACBAN){
                controller.chuyenMauMacDinh();
            }
            controller.capNhatTrangThai();
        }
    }


}
