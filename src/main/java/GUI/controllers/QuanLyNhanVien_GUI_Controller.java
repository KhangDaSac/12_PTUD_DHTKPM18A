package GUI.controllers;

import DAO.NhanVien_DAO;
import DTO.LoaiNhanVien;
import DTO.NhanVien;
import DTO.TrangThaiNhanVien;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class QuanLyNhanVien_GUI_Controller implements Initializable {

    @FXML
    private JFXButton btnSuaNV;

    @FXML
    private JFXButton btnThemNV;

    @FXML
    private JFXButton btnXuatDSNV;

    @FXML
    private ComboBox<String> cmbLoaiNhanVien;

    @FXML
    private ComboBox<String> cmbTrangThai;

    @FXML
    private TableColumn<NhanVien, String> colSTT_NV;

    @FXML
    private TableColumn<NhanVien, String> colMa_NV;

    @FXML
    private TableColumn<NhanVien, String> colCCCD_NV;

    @FXML
    private TableColumn<NhanVien, String> colTen_NV;

    @FXML
    private TableColumn<NhanVien, String> colDiaChi_NV;

    @FXML
    private TableColumn<NhanVien, String> colSoDienThoai_NV;

    @FXML
    private TableColumn<NhanVien, String> colLoai_NV;

    @FXML
    private TableColumn<NhanVien, String> colTrangThai_NV;

    @FXML
    private TableView<NhanVien> tblNV;

    private ObservableList<NhanVien> listNV;

    @FXML
    private Label lblMaNV_Loi;

    @FXML
    private Label lblCCCD_Loi;

    @FXML
    private Label lblTenNV_Loi;

    @FXML
    private Label lblDiaChi_Loi;

    @FXML
    private Label lblSDT_Loi;

    @FXML
    private Label lblLoaiNV_Loi;

    @FXML
    private Label lblTrangThaiNV_Loi;

    @FXML
    private TextField txtMa_NV;

    @FXML
    private TextField txtCCCD_NV;

    @FXML
    private TextField txtTen_NV;

    @FXML
    private TextField txtDiaChi_NV;

    @FXML
    private TextField txtSDT_NV;

    @FXML
    private TextField txtTimKiem_NV;

    @FXML
    private AnchorPane scene;

    private NhanVien_DAO nV_DAO = new NhanVien_DAO();

    private Main_Controller main_controller;

    public Main_Controller getMain_controller() {
        return main_controller;
    }

    public void setMain_controller(Main_Controller main_controller) {
        this.main_controller = main_controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pushDataToTblFromDB();
        txtMa_NV.setDisable(true);

        ObservableList<String> listCBBLNV = FXCollections.observableArrayList();
        for(LoaiNhanVien loaiNhanVien : LoaiNhanVien.values()){
            listCBBLNV.add(loaiNhanVien.toString());
        }
        cmbLoaiNhanVien.setItems(listCBBLNV);

        ObservableList<String> listCBBTT = FXCollections.observableArrayList();
        for(TrangThaiNhanVien trangThai : TrangThaiNhanVien.values()){
            listCBBTT.add(trangThai.toString());
        }
        cmbTrangThai.setItems(listCBBTT);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) { // Hoặc phím bạn muốn
                tblNV.getSelectionModel().clearSelection(); // Bỏ chọn hàng
                clearTextFields(); // Làm trống các TextField
            }
        });

        timKiemNhanVien();

    }
    private void clearTextFields() {
        txtMa_NV.clear();
        txtCCCD_NV.clear();
        txtTen_NV.clear();
        txtDiaChi_NV.clear();
        txtSDT_NV.clear();
        cmbLoaiNhanVien.getSelectionModel().clearSelection();
        cmbTrangThai.getSelectionModel().clearSelection();
    }

    void pushDataToTblFromDB(){
        listNV = FXCollections.observableArrayList(nV_DAO.getAllEmployee());

        colSTT_NV.setCellFactory(column -> new TableCell<NhanVien, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1)); // Số thứ tự = chỉ số hàng + 1
                }
            }
        });
        colMa_NV.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        colCCCD_NV.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        colTen_NV.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
        colDiaChi_NV.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colSoDienThoai_NV.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        colLoai_NV.setCellValueFactory(param -> {
            NhanVien nhanVien = param.getValue();
            return new SimpleStringProperty(nhanVien.getLoaiNhanVien().toString());
        });
        colTrangThai_NV.setCellValueFactory(param -> {
            NhanVien nhanVien = param.getValue();
            return new SimpleStringProperty(nhanVien.getTrangThaiNhanVien().toString());
        });

        tblNV.setItems(listNV);
    }

    @FXML
    void tblOnMuseClick(MouseEvent event) {
        int row = tblNV.getSelectionModel().getSelectedIndex();
        txtMa_NV.setText(colMa_NV.getCellData(row));
        txtCCCD_NV.setText(colCCCD_NV.getCellData(row));
        txtTen_NV.setText(colTen_NV.getCellData(row));
        txtDiaChi_NV.setText(colDiaChi_NV.getCellData(row));
        txtSDT_NV.setText(colSoDienThoai_NV.getCellData(row));
        String loai = colLoai_NV.getCellData(row);
        cmbLoaiNhanVien.setValue(loai);
        String trangthai = colTrangThai_NV.getCellData(row);
        cmbTrangThai.setValue(trangthai);

        if (!txtMa_NV.getText().isEmpty()) lblMaNV_Loi.setText("");
        if (!txtCCCD_NV.getText().isEmpty()) lblCCCD_Loi.setText("");
        if (!txtTen_NV.getText().isEmpty()) lblTenNV_Loi.setText("");
        if (!txtDiaChi_NV.getText().isEmpty()) lblDiaChi_Loi.setText("");
        if (!txtSDT_NV.getText().isEmpty()) lblSDT_Loi.setText("");
        if (cmbLoaiNhanVien.getValue() != null) lblLoaiNV_Loi.setText("");
        if (cmbTrangThai.getValue() != null) lblTrangThaiNV_Loi.setText("");

    }

    void showMessage(String mess) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Thông báo!");

        Label messageLabel = new Label(mess);
        VBox vbox = new VBox(messageLabel);
        vbox.setSpacing(20);
        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.showAndWait();
    }

    public NhanVien getNhanVienFromTxt(NhanVien nv){
        nv.setCCCD(txtCCCD_NV.getText());
        nv.setTenNhanVien(txtTen_NV.getText());
        nv.setDiaChi(txtDiaChi_NV.getText());
        nv.setSoDienThoai(txtSDT_NV.getText());

        nv.setLoaiNhanVien(LoaiNhanVien.valueOf(cmbLoaiNhanVien.getValue()));
        nv.setTrangThaiNhanVien(TrangThaiNhanVien.valueOf(cmbTrangThai.getValue()));
        return nv;
    }

    @FXML
    void btnThemNhanVienOnAction(ActionEvent event) {
        NhanVien nv = new NhanVien();

        // Tự động sinh mã nhân viên mới
        String maNhanVienMoi = generateMaNhanVien();
        nv.setMaNhanVien(maNhanVienMoi);

        getNhanVienFromTxt(nv);

        listNV.add(nv);
        nV_DAO.create(nv);
        showMessage("Nhân viên đã được thêm thành công!");

        timKiemNhanVien();
        pushDataToTblFromDB();
    }

    private String generateMaNhanVien() {
        // Kiểm tra danh sách nhân viên
        if (listNV.isEmpty()) {
            // Nếu danh sách trống, khởi tạo mã mặc định
            return "NV20000001"; // Giả sử năm 2020
        }

        // Lấy mã nhân viên cuối cùng
        String maCuoi = listNV.getLast().getMaNhanVien();

        // Kiểm tra định dạng mã
        if (maCuoi == null || !maCuoi.startsWith("NV")) {
            return "NV20000001"; // Mặc định nếu mã không hợp lệ
        }

        // Tách năm và số từ mã
        String yearPart = maCuoi.substring(2, 4); // 2 ký tự tiếp theo là năm
        String numberPart = maCuoi.substring(4); // 4 ký tự cuối

        // Tăng số phát sinh
        int number = Integer.parseInt(numberPart);
        number++;

        // Tạo mã mới với 2 ký tự đầu và năm không đổi, 4 ký tự cuối mới
        return "NV" + yearPart + String.format("%06d", number); // Đảm bảo 4 chữ số
    }

    @FXML
    void btnSuaNhanVienOnAction(ActionEvent event) {
        NhanVien nv = new NhanVien();

        getNhanVienFromTxt(nv);
        NhanVien nhanVien = tblNV.getSelectionModel().getSelectedItem();
        nhanVien.setCCCD(nv.getCCCD());
        nhanVien.setTenNhanVien(nv.getTenNhanVien());
        nhanVien.setDiaChi(nv.getDiaChi());
        nhanVien.setSoDienThoai(nv.getSoDienThoai());
        nhanVien.setLoaiNhanVien(nv.getLoaiNhanVien());
        nhanVien.setTrangThaiNhanVien(nv.getTrangThaiNhanVien());

        tblNV.refresh();

        nV_DAO.update(nhanVien);

        pushDataToTblFromDB();
    }

    @FXML
    void btnXuatDanhSachNhanVienOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("file_ThongTinNhanVien");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showSaveDialog(tblNV.getScene().getWindow());

        if(file!=null){
            try(Workbook wb = new XSSFWorkbook()){
                Sheet sheet = wb.createSheet("DanhSachNhanVien");

                //tiêu đề cột
                Row headerRow = sheet.createRow(0);
                for(int i = 1; i< tblNV.getColumns().size();i++){
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(tblNV.getColumns().get(i).getText());
                }

                //ghi dữ liêu từ tbl vào sheet
                for(int i = 0; i< tblNV.getItems().size();i++){
                    Row row = sheet.createRow(i+1);
                    for(int j =1; j< tblNV.getColumns().size();j++){
                        Cell cell = row.createCell(j);
                        Object cellData = tblNV.getColumns().get(j).getCellData(i);
                        cell.setCellValue(cellData !=null ? cellData.toString() : "");
                    }
                }

                //ghi file excel ra ổ đĩa
                try (FileOutputStream fileOut = new FileOutputStream(file)) {
                    wb.write(fileOut);
                    System.out.println("Đã in ra excel");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        showMessage("Xuất file thành công!");
    }

    void timKiemNhanVien(){
        FilteredList<NhanVien> filData = new FilteredList<>(listNV, b -> true);
        txtTimKiem_NV.textProperty().addListener((observable,oldValue, newValue)->{ // sửa txt tìm kiếm
            filData.setPredicate( nhanVien -> {
                if(newValue==null || newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if(nhanVien.getMaNhanVien().toLowerCase().indexOf(searchKey) > -1){
                    return true;
                }else if(nhanVien.getCCCD().toLowerCase().indexOf(searchKey) > -1) {
                    return true;
                }else if(nhanVien.getTenNhanVien().toLowerCase().indexOf(searchKey) > -1){
                    return true;
                }else if(nhanVien.getDiaChi().toLowerCase().indexOf(searchKey) > -1){
                    return true;
                }else if(nhanVien.getSoDienThoai().toLowerCase().indexOf(searchKey) > -1){
                    return true;
                } else if(nhanVien.getLoaiNhanVien().toString().toLowerCase().indexOf(searchKey) > -1){
                    return true;
                }else if(nhanVien.getTrangThaiNhanVien().toString().toLowerCase().indexOf(searchKey) > -1){
                    return true;
                }else
                    return false;
            });
        });
        SortedList<NhanVien> dataDaSort = new SortedList<>(filData);
        dataDaSort.comparatorProperty().bind(tblNV.comparatorProperty());
        tblNV.setItems(dataDaSort);
    }

    private boolean isFieldClicked = false; // Biến để theo dõi trạng thái nhấp chuột

    @FXML
    void txtCCCD_OnMouseClicked(MouseEvent event) {
        isFieldClicked = true;
    }

    @FXML
    void txtTen_NV_OnMouseClicked(MouseEvent event) {
        isFieldClicked = true;
    }

    @FXML
    void txtDiaChi_NV_OnMouseClicked(MouseEvent event) {
        isFieldClicked = true;
    }

    @FXML
    void txtSDT_NV_OnMouseClicked(MouseEvent event) {
        isFieldClicked = true;
    }

    @FXML
    void txtLoai_NV_OnMouseClicked(MouseEvent event) {
        isFieldClicked = true;
    }

    @FXML
    void txtTrangThai_NV_OnMouseClicked(MouseEvent event) {
        isFieldClicked = true;
    }

    @FXML
    void txtCCCD_OnMouseExited(MouseEvent event) {
        if (isFieldClicked && txtCCCD_NV.getText().isEmpty()) {
            lblCCCD_Loi.setText("Lỗi nhập!");
            txtCCCD_NV.requestFocus();
        } else {
            lblCCCD_Loi.setText("");
        }
    }

    @FXML
    void txtTen_NV_OnMouseExited(MouseEvent event) {
        if (isFieldClicked && txtTen_NV.getText().isEmpty()) {
            lblTenNV_Loi.setText("Lỗi nhập!");
            txtTen_NV.requestFocus();
        } else {
            lblTenNV_Loi.setText("");
        }
    }

    @FXML
    void txtDiaChi_NV_OnMouseExited(MouseEvent event) {
        if (isFieldClicked && txtDiaChi_NV.getText().isEmpty()) {
            lblDiaChi_Loi.setText("Lỗi nhập!");
            txtDiaChi_NV.requestFocus();
        } else {
            lblDiaChi_Loi.setText("");
        }
    }

    @FXML
    void txtSDT_NV_OnMouseExited(MouseEvent event) {
        if (isFieldClicked && txtSDT_NV.getText().isEmpty()) {
            lblSDT_Loi.setText("Lỗi nhập!");
            txtSDT_NV.requestFocus();
        } else {
            lblSDT_Loi.setText("");
        }
    }

    @FXML
    void txtCCCD_OnKeyReleased() {
        if (!txtCCCD_NV.getText().isEmpty()) {
            lblCCCD_Loi.setText("");
        }
    }

    @FXML
    void txtTen_NV_OnKeyReleased() {
        if (!txtTen_NV.getText().isEmpty()) {
            lblTenNV_Loi.setText("");
        }
    }

    @FXML
    void txtDiaChi_NV_OnKeyReleased() {
        if (!txtDiaChi_NV.getText().isEmpty()) {
            lblDiaChi_Loi.setText("");
        }
    }

    @FXML
    void txtSDT_NV_OnKeyReleased() {
        if (!txtSDT_NV.getText().isEmpty()) {
            lblSDT_Loi.setText("");
        }
    }

    @FXML
    void cmbLoaiNhanVien_OnAction() {
        if (cmbLoaiNhanVien.getValue() != null) {
            lblLoaiNV_Loi.setText("");
        }
    }

    @FXML
    void cmbTrangThai_OnAction() {
        if (cmbTrangThai.getValue() != null) {
            lblTrangThaiNV_Loi.setText("");
        }
    }

}
