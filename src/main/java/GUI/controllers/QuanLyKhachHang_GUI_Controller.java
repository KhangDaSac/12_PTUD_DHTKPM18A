package GUI.controllers;

import DAO.KhachHang_DAO;
import DAO.LoaiKhachHang_DAO;
import DTO.KhachHang;
import DTO.LoaiKhachHang;
import com.jfoenix.controls.JFXButton;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import utils.ShowMessagesDialog;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class QuanLyKhachHang_GUI_Controller implements Initializable {
    @FXML
    private JFXButton btnSuaKH;

    @FXML
    private JFXButton btnThemKH;


    @FXML
    private JFXButton btnXuatDSKH;

    @FXML
    private ComboBox<String> cmbLoaiKhachHang;

    @FXML
    private TableView<KhachHang> tblKH;

    @FXML
    private TableColumn<KhachHang, String> colCCCD_KH;

    @FXML
    private TableColumn<KhachHang, String> colLoai_KH;

    @FXML
    private TableColumn<KhachHang, Double> colMGG_KH;

    @FXML
    private TableColumn<KhachHang, String> colMa_KH;

    @FXML
    private TableColumn<KhachHang, String> colSDT_KH;

    @FXML
    private TableColumn<KhachHang, Integer> colSTT_KH;

    @FXML
    private TableColumn<KhachHang, LocalDate> colNgaySinh_KH;

    @FXML
    private TableColumn<KhachHang, String> colTen_KH;

    private ObservableList<KhachHang> listKH;

    @FXML
    private TextField txtCCCD_KH;

    @FXML
    private TextField txtMaKH;


    @FXML
    private DatePicker datePickerNgaySinh_KH;

    @FXML
    private TextField txtSDT_KH;

    @FXML
    private TextField txtTenKH;

    @FXML
    private TextField txtTimKiemKH;

    @FXML
    private Label lblCCCD_Loi;

    @FXML
    private Label lblLoaiKH_Loi;

    @FXML
    private Label lblMaKH_Loi;

    @FXML
    private Label lblNgay_Loi;

    @FXML
    private Label lblSDT_Loi;

    @FXML
    private Label lblTenKH_Loi;

    private KhachHang_DAO kH_DAO = new KhachHang_DAO();

    private Main_Controller main_Controller;

    public Main_Controller getMain_Controller() {
        return main_Controller;
    }

    public void setMain_Controller(Main_Controller main_Controller) {
        this.main_Controller = main_Controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        upDataTbl();
        cmbLoaiKhachHang.setEditable(false);
        ObservableList<String> listCBB = FXCollections.observableArrayList();
        for(KhachHang kh : listKH){
            String maloai = kh.getLoaiKhachHang().getTenLoaiKhachHang();
            if(maloai !=null && !maloai.isEmpty() && !listCBB.contains(maloai)){
                listCBB.add(maloai);
            }
        }
        cmbLoaiKhachHang.setItems(listCBB);

        timKiemKhachHang();
    }
    void upDataTbl(){
        listKH =FXCollections.observableArrayList(kH_DAO.xuatDanhSachKhachHang());
        colSTT_KH.setCellFactory(column -> new TableCell<KhachHang, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1)); // Số thứ tự = chỉ số hàng + 1
                }
            }
        });
        colMa_KH.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
        colTen_KH.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        colCCCD_KH.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        colSDT_KH.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        colNgaySinh_KH.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        colMGG_KH.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<KhachHang, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<KhachHang, Double> maGG) {
                ReadOnlyObjectWrapper<Double> voBoc = new ReadOnlyObjectWrapper<>(); // tao mot cai de boc. nguyen cai du lieu thanh double
                Double phanTramGiamGia = maGG.getValue().getLoaiKhachHang().getPhanTramGiamGia()*100;
                voBoc.set(Math.floor(phanTramGiamGia));
                return voBoc.getReadOnlyProperty();
            }
        });
        colLoai_KH.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<KhachHang, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<KhachHang, String> param) {
                KhachHang khachHang = param.getValue();
                return new SimpleStringProperty(khachHang.getLoaiKhachHang().getTenLoaiKhachHang());
            }
        });
        tblKH.setItems(listKH);
    }
    @FXML
    void tblOnMuseClick(MouseEvent event) {
        int row = tblKH.getSelectionModel().getSelectedIndex();
        String loai = colLoai_KH.getCellData(row).toString();
        txtMaKH.setText(colMa_KH.getCellData(row).toString());
        txtCCCD_KH.setText(colCCCD_KH.getCellData(row).toString());
        txtTenKH.setText(colTen_KH.getCellData(row));
        datePickerNgaySinh_KH.setValue(colNgaySinh_KH.getCellData(row));
        txtSDT_KH.setText(colSDT_KH.getCellData(row));
        cmbLoaiKhachHang.setValue(loai);
    }

    boolean batLoiKyTu(){
        if(txtMaKH.getText().isEmpty()){
            lblMaKH_Loi.setText("Lỗi nhập!");
            txtMaKH.requestFocus();
            return false;
        }else{
            lblMaKH_Loi.setText("");
        }
        if(txtCCCD_KH.getText().isEmpty()){
            lblCCCD_Loi.setText("Lỗi nhập!");
            txtCCCD_KH.requestFocus();
            return false;
        }else{
            lblCCCD_Loi.setText("");
        }
        if(txtTenKH.getText().isEmpty()){
            lblTenKH_Loi.setText("Lỗi nhập!");
            txtTenKH.requestFocus();
            return false;
        }else{
            lblTenKH_Loi.setText("");
        }
        if(datePickerNgaySinh_KH.getValue()==null){
            lblNgay_Loi.setText("Lỗi nhập!");
            datePickerNgaySinh_KH.requestFocus();
            return false;
        }else{
            lblNgay_Loi.setText("");
        }
        if(txtSDT_KH.getText().isEmpty()){
            lblSDT_Loi.setText("Lỗi nhập!");
            txtSDT_KH.requestFocus();
            return false;
        }else{
            lblSDT_Loi.setText("");
        }
        if(cmbLoaiKhachHang.getValue()==null){
            lblLoaiKH_Loi.setText("Chọn loại!");
            cmbLoaiKhachHang.requestFocus();
            return false;
        }else{
            lblLoaiKH_Loi.setText("");
        }
        if(txtCCCD_KH.getText().isEmpty()){
            lblCCCD_Loi.setText("Lỗi nhập!");
            txtCCCD_KH.requestFocus();
            return false;
        }else{
            lblCCCD_Loi.setText("");
        }
        return true;
    }

    public KhachHang getKHtuTXT(KhachHang kh){
        String tenLoai =cmbLoaiKhachHang.getValue();
        String maLoai="";
        Double giamGia=0.0;
        if (tenLoai.equals("Người cao tuổi từ 60 tuổi trở lên")) {
            maLoai = "CT";
            giamGia= 0.15;
        } else if (tenLoai.equals("Học sinh, sinh viên")) {
            maLoai = "HS";
            giamGia= 0.1;
        } else if (tenLoai.equals("Khách hàng thông thường")) {
            maLoai = "TT";
            giamGia= 0.0;
        } else if (tenLoai.equals("Thương binh, người hưởng chính sách như thương binh, nạn nhân chất độc hóa học giảm")) {
            maLoai = "TB";
            giamGia= 0.3;
        } else if (tenLoai.equals("Thương binh, người hưởng chính sách như thương binh, nạn nhân chất độc hóa học giảm")) {
            maLoai = "TE";
            giamGia= 0.25;
        }

        String kHcuoi = listKH.getLast().getMaKhachHang();
        if (kHcuoi == null || !kHcuoi.startsWith("KH2000")) {
            kHcuoi= "KH20000001";
        }
        String numberPart = kHcuoi.substring(6);
        int number = Integer.parseInt(numberPart);
        number++;
        System.out.println("KH2000"+number);
        kh.setMaKhachHang(String.format("KH2000%04d",number));
        kh.setCCCD(txtCCCD_KH.getText());
        kh.setTenKhachHang(txtTenKH.getText());
        kh.setSoDienThoai(txtSDT_KH.getText());
        kh.setNgaySinh(datePickerNgaySinh_KH.getValue());
        kh.setLoaiKhachHang(new LoaiKhachHang(maLoai,tenLoai,giamGia));
        return kh;
    }
    @FXML
    void btnThemKhachHangOnAction(ActionEvent event) {
        //batLoiKyTu();
        KhachHang kh =new KhachHang();
        getKHtuTXT(kh);
        boolean isDuplicate = false;
        for (KhachHang kh1 : listKH) {
            if (kh1.getMaKhachHang().equals(kh.getMaKhachHang())) {
                isDuplicate = true;
                main_Controller.showMessagesDialog("Trùng mã khách hàng");
                txtMaKH.requestFocus();
                break;
            }
        }
        if (!isDuplicate) {
            listKH.add(kh);
            kH_DAO.addKhachHang(kh);
            main_Controller.showMessagesDialog("Thêm khách hàng thành công!");
        }
    }

    @FXML
    void btnSuaKhachHangOnAction(ActionEvent event) {
        KhachHang kh = new KhachHang();
        getKHtuTXT(kh);
        KhachHang khachH = tblKH.getSelectionModel().getSelectedItem();
        khachH.setMaKhachHang(kh.getMaKhachHang());
        khachH.setNgaySinh(kh.getNgaySinh());
        khachH.setTenKhachHang(kh.getTenKhachHang());
        khachH.setCCCD(kh.getCCCD());
        khachH.setSoDienThoai(kh.getSoDienThoai());
        khachH.setLoaiKhachHang(kh.getLoaiKhachHang());
        tblKH.refresh();
        kH_DAO.suaThongTinKhachHang(kh);
        main_Controller.showMessagesDialog("Sửa thông tin khách hàng thành công!");
    }

    @FXML
    void btnXuatDanhSachKhachHangOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("fileThongTinKhachHang");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(tblKH.getScene().getWindow());

        if(file!=null){
            try(Workbook wb = new XSSFWorkbook()){
                Sheet sheet = wb.createSheet("DanhSachKhachHang");

                //tiêu đề cột
                Row headerRow = sheet.createRow(0);
                for(int i = 1; i< tblKH.getColumns().size();i++){
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(tblKH.getColumns().get(i).getText());
                }

                //ghi dữ liêu từ tbl vào sheet
                for(int i = 0; i< tblKH.getItems().size();i++){
                    Row row = sheet.createRow(i+1);
                    for(int j =1; j< tblKH.getColumns().size();j++){
                        Cell cell = row.createCell(j);
                        Object cellData = tblKH.getColumns().get(j).getCellData(i);
                        cell.setCellValue(cellData !=null ? cellData.toString() : "");
                    }
                }

                //ghi file excel ra ổ đĩa
                try (FileOutputStream fileOut = new FileOutputStream(file)) {
                    wb.write(fileOut);
                    main_Controller.showMessagesDialog("Đã xuất ra excel thành công!");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void timKiemKhachHang() {
        FilteredList<KhachHang> filData = new FilteredList<>(listKH, b -> true);
        txtTimKiemKH.textProperty().addListener((observable,oldValue, newValue)->{ // sửa txt tìm kiếm
                filData.setPredicate( khachHang -> {
                            if(newValue==null || newValue.isEmpty()){
                                return true;
                            }
                            String searchKey = newValue.toLowerCase();
                            if(khachHang.getMaKhachHang().toLowerCase().indexOf(searchKey) > -1){
                                return true;
                            }else if(khachHang.getTenKhachHang().toLowerCase().indexOf(searchKey) > -1){
                                return true;
                            }else if(khachHang.getSoDienThoai().toLowerCase().indexOf(searchKey) > -1){
                                return true;
                            }else if(khachHang.getLoaiKhachHang().getTenLoaiKhachHang().toLowerCase().indexOf(searchKey) > -1){
                                return true;
                            }else
                                return false;
                });
        });
        SortedList<KhachHang> dataDaSort = new SortedList<>(filData);
        dataDaSort.comparatorProperty().bind(tblKH.comparatorProperty());
        tblKH.setItems(dataDaSort);
    }

    @FXML
    void txtCCCD_OnMouseExit(MouseEvent event) {
        if(txtCCCD_KH.getText().isEmpty()){
            lblCCCD_Loi.setText("Lỗi nhập!");
            txtCCCD_KH.requestFocus();
        }else{
            lblCCCD_Loi.setText("");
        }
    }

    @FXML
    void txtLoaiKH_OnMouseExit(MouseEvent event) {
        if(cmbLoaiKhachHang.getValue()==null){
            lblLoaiKH_Loi.setText("Chọn loại!");
            cmbLoaiKhachHang.requestFocus();
        }else{
            lblLoaiKH_Loi.setText("");
        }
    }

    @FXML
    void txtMaKH_MouseExit(MouseEvent event) {
        if(txtMaKH.getText().isEmpty()){
            lblMaKH_Loi.setText("Lỗi nhập!");
            txtMaKH.requestFocus();
        }else{
            lblMaKH_Loi.setText("");
        }
    }

    @FXML
    void txtNgay_OnMouseExit(MouseEvent event) {
        if(datePickerNgaySinh_KH.getValue()==null){
            lblNgay_Loi.setText("Lỗi nhập!");
            datePickerNgaySinh_KH.requestFocus();
        }else{
            lblNgay_Loi.setText("");
        }
    }

    @FXML
    void txtSDT_OnMouseExit(MouseEvent event) {
        if(txtSDT_KH.getText().isEmpty()){
            lblSDT_Loi.setText("Lỗi nhập!");
            txtSDT_KH.requestFocus();
        }else{
            lblSDT_Loi.setText("");
        }
    }

    @FXML
    void txtTenKH_OnMouseExit(MouseEvent event) {
        if(txtTenKH.getText().isEmpty()){
            lblTenKH_Loi.setText("Lỗi nhập!");
            txtTenKH.requestFocus();
        }else{
            lblTenKH_Loi.setText("");
        }
    }

}
