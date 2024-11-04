package GUI.controllers;

import DAO.HoaDon_DAO;
import DTO.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class QuanLyHoaDon_GUI_Controller implements Initializable {
    @FXML
    private TableColumn<HoaDon, String> colMaHoaDon;
    @FXML
    private TableColumn<HoaDon, LocalDateTime> colThoiGianLap;
    @FXML
    private TableColumn<HoaDon, Double> colTongTien;
    @FXML
    private TableColumn<HoaDon, Double> colTongTienCoc;
    @FXML
    private TableColumn<HoaDon, Double> colTongTienTra;
    @FXML
    private TableColumn<HoaDon, LoaiHoaDon> colLoaiHoaDon;
    @FXML
    private TableColumn<HoaDon, TrangThaiHoaDon> colTrangThaiHoaDon;
    @FXML
    private TableColumn<HoaDon, CaLamViec> colMaCaLamViec;
    @FXML
    private TableColumn<HoaDon, KhachHang> colMaKhachHang;
    @FXML
    private TableView<HoaDon> tableView;
    @FXML
    private TableColumn<Object, Object> colSTT;
    private Main_Controller main_controller;
    @FXML
    private ComboBox<String> cbmTrangThaiHoaDon;
    @FXML
    private ComboBox<String> cbmLoaiHoaDon;
    @FXML
    private javafx.scene.control.TextField txtMaHoaDon;
    @FXML
    private javafx.scene.control.TextField txtCCCD;
    @FXML
    private javafx.scene.control.TextField txtMaCaLamViec;
    @FXML
    private DatePicker dapThoiGianLap;
    @FXML
    private Button btnTimKiemHoaDon;
    @FXML
    private Button btnXuatHoaDon;

    private HoaDon selectedHoaDon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        colSTT.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(column.getValue()) + 1));
        colMaHoaDon.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
        colThoiGianLap.setCellValueFactory(new PropertyValueFactory<>("thoiGianLap"));
        colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        colTongTienCoc.setCellValueFactory(new PropertyValueFactory<>("tongTienDaDatCoc"));
        colTongTienTra.setCellValueFactory(new PropertyValueFactory<>("tongTienKhachHangTra"));
        colLoaiHoaDon.setCellValueFactory(new PropertyValueFactory<>("loaiHoaDon"));
        colTrangThaiHoaDon.setCellValueFactory(new PropertyValueFactory<>("trangThaiHoaDon"));
        colMaCaLamViec.setCellValueFactory(new PropertyValueFactory<HoaDon, CaLamViec>("caLamViec"));
        colMaCaLamViec.setCellFactory(column -> new TableCell<HoaDon, CaLamViec>() {
            @Override
            protected void updateItem(CaLamViec item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMaCaLamViec());
                }
            }
        });

        colMaKhachHang.setCellValueFactory(new PropertyValueFactory<HoaDon, KhachHang>("khachHangMua"));
        colMaKhachHang.setCellFactory(column -> new TableCell<HoaDon, KhachHang>() {
            @Override
            protected void updateItem(KhachHang item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getMaKhachHang());
                }
            }
        });

        // Add data to TableView
        tableView.setItems(getHoaDonData());

        // Set sự kiện chọn hàng trong TableView
        tableView.setOnMouseClicked(this::onTableClick);
    }

    public void onTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            selectedHoaDon = tableView.getSelectionModel().getSelectedItem();
        }
    }

    public void initializeComboBoxes() {
        ObservableList<String> trangThaiHoaDon = FXCollections.observableArrayList("Chờ lấy vé", "Đã lấy một phần", "Đã lấy toàn bộ", "Đã hủy hết vé");
        cbmTrangThaiHoaDon.setItems(trangThaiHoaDon);
        ObservableList<String> loaiHoaDon = FXCollections.observableArrayList("Hóa đơn đặt vé", "Hóa đơn bán vé");
        cbmLoaiHoaDon.setItems(loaiHoaDon);
    }

    public ObservableList<HoaDon> getHoaDonData() {
        ArrayList<HoaDon> hoaDonList = new HoaDon_DAO().getDanhSachHoaDon();
        return FXCollections.observableArrayList(hoaDonList);
    }

    public void btnXuatHoaDonOnAction(ActionEvent actionEvent) {
        if (selectedHoaDon == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một hóa đơn để xuất.");
            alert.showAndWait();
            return;
        }

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("HoaDon_BanVeTau_" + selectedHoaDon.getMaHoaDon() + ".pdf"));
            document.open();

            Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
            Paragraph title = new Paragraph("HOA DON BAN VE TAU", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            Font fontSection = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Font fontContent = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
            document.add(new Paragraph("                               Thong tin hoa don:  ", fontSection));
            document.add(new Paragraph("                                    Ma hoa don:    " + selectedHoaDon.getMaHoaDon(), fontContent));
            document.add(new Paragraph("                                    Ngay lap:      " +  selectedHoaDon.getThoiGianLap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), fontContent));
            document.add(new Paragraph("                                    Thoi gian tao hoa don: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) , fontContent));
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("                               Thong tin khach hang:", fontSection));
            document.add(new Paragraph("                                    Ma khach hang:   " + selectedHoaDon.getKhachHangMua().getMaKhachHang(), fontContent));
            document.add(new Paragraph("                                    Ten khach hang:  " + selectedHoaDon.getKhachHangMua().getTenKhachHang(), fontContent));
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("                               Thong tin nhan vien", fontSection));
            document.add(new Paragraph("                                    Ma ca lam viec:  " + selectedHoaDon.getCaLamViec().getMaCaLamViec(), fontContent));
            document.add(Chunk.NEWLINE);
            Font fontTien = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            document.add(new Paragraph("                                    Tong tien da tra:      " + selectedHoaDon.getTongTienKhachHangTra()+ " VND", fontTien));
            document.add(new Paragraph("                                    Tong tien coc:         " + selectedHoaDon.getTongTienDaDatCoc() + " VND", fontTien));
            document.add(new Paragraph("                                    Tong hoa don:          " + selectedHoaDon.getTongTien() + " VND", fontTien));
            document.add(new Paragraph("                                    So tien con lai:       " + (selectedHoaDon.getTongTien() - selectedHoaDon.getTongTienKhachHangTra()) + " VND", fontTien));

            document.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Hóa đơn đã được xuất thành công.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Có lỗi xảy ra khi xuất hóa đơn.");
            alert.showAndWait();
        }
    }

    public void setMain_Controller(Main_Controller mainController) {
        this.main_controller = mainController;
    }

    public void btnTimKiemOnAction(ActionEvent actionEvent) {
        String loaiHoaDon = null;
        String trangThaiHoaDon = null;

        if (cbmLoaiHoaDon.getValue() != null) {
            if (cbmLoaiHoaDon.getValue().equals("Hóa đơn đặt vé")) {
                loaiHoaDon = "HOADONDAT";
            } else if (cbmLoaiHoaDon.getValue().equals("Hóa đơn bán vé")) {
                loaiHoaDon = "HOADONBAN";
            }
        }

        if (cbmTrangThaiHoaDon.getValue() != null) {
            if (cbmTrangThaiHoaDon.getValue().equals("Chờ lấy vé")) {
                trangThaiHoaDon = "CHOLAYVE";
            } else if (cbmTrangThaiHoaDon.getValue().equals("Đã lấy một phần")) {
                trangThaiHoaDon = "DALAYMOTPHAN";
            } else if (cbmTrangThaiHoaDon.getValue().equals("Đã lấy toàn bộ")) {
                trangThaiHoaDon = "DALAYTOANBO";
            } else if (cbmTrangThaiHoaDon.getValue().equals("Đã hủy hết vé")) {
                trangThaiHoaDon = "DAHUYVE";
            }
        }

        ArrayList<HoaDon> hoaDonList = new HoaDon_DAO().getDSHoaDonTheoCacTieuChi(
                txtMaHoaDon.getText().isEmpty() ? null : txtMaHoaDon.getText(),
                txtCCCD.getText().isEmpty() ? null : txtCCCD.getText(),
                txtMaCaLamViec.getText().isEmpty() ? null : txtMaCaLamViec.getText(),
                trangThaiHoaDon,
                loaiHoaDon,
                dapThoiGianLap.getValue()
        );

        tableView.setItems(FXCollections.observableArrayList(hoaDonList));
    }
}
