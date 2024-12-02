package GUI.controllers;

import DTO.Ve;
import DTO.LoaiVe;
import DAO.HuyVe_DAO;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class HuyVe_GUI_Controller implements Initializable {

    @FXML
    private JFXButton btnHuyVe;

    @FXML
    private JFXButton btnTimKiemMaVe;

    @FXML
    private ComboBox<String> cmbTrangThaiVe;

    @FXML
    private TextField txtGaDen;

    @FXML
    private TextField txtGaDi;

    @FXML
    private TextField txtLePhiTraVe;

    @FXML
    private TextField txtLoaiKhachHang;

    @FXML
    private TextField txtLoaiVe;

    @FXML
    private TextField txtMaChuyenTau;

    @FXML
    private TextField txtMaHoaDon;

    @FXML
    private TextField txtMaKhachHang;

    @FXML
    private TextField txtMaVe;

    @FXML
    private TextField txtMaVe_TT;

    @FXML
    private TextField txtSoDienThoai;

    @FXML
    private TextField txtTenKhachHang;

    @FXML
    private TextField txtNgayKhoiHanh;

    @FXML
    private TextField txtTienHoanTra;

    @FXML
    private TextField txtTongTienVe;

    private ObservableList<Ve> listVe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khởi tạo ComboBox Trạng thái vé
        ObservableList<String> listCBBTTV = FXCollections.observableArrayList();
        for(LoaiVe loaiVe : LoaiVe.values()){
            listCBBTTV.add(loaiVe.toString());
        }
        cmbTrangThaiVe.setItems(listCBBTTV);
    }

    @FXML
    void btnHuyVeOnAction(ActionEvent event) {
        String maVe = txtMaVe.getText(); // Lấy mã vé từ TextField
        boolean result = HuyVe_DAO.updateTicketStatus(maVe); // Gọi phương thức updateTicketStatus từ BUS

        if (result) {
            System.out.println("Hủy vé thành công.");
        } else {
            System.out.println("Hủy vé thất bại.");
        }
    }

    @FXML
    void btnTimKiemMaVeOnAction(ActionEvent event) {
//        String maVe = txtMaVe.getText(); // Lấy mã vé từ TextField
//        Ve ve = HuyVe_DAO.getTicketByID(maVe); // Gọi phương thức getTicketByID từ BUS
//        HoaDon hoaDon = HoaDon_DAO.getOrderById(ve.getHoaDon().getMaHoaDon());
//        System.out.println(ve.getHoaDon().getMaHoaDon());
//        System.out.println(hoaDon.getKhachHang().getMaKhachHang());
//
//        if (ve != null && hoaDon.getKhachHang() != null) {
//            // Cập nhật các TextField với thông tin từ đối tượng Ve
//            txtMaKhachHang.setText(hoaDon.getKhachHang().getMaKhachHang());
//            txtTenKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
//            txtSoDienThoai.setText(hoaDon.getKhachHang().getSoDienThoai());
//            txtLoaiKhachHang.setText(String.valueOf(hoaDon.getKhachHang().getLoaiKhachHang()));
//
//            txtMaChuyenTau.setText(ve.getChuyenTau().getMaChuyenTau());
//            txtGaDi.setText(String.valueOf(ve.getGaTauDi()));
//            txtGaDen.setText(String.valueOf(ve.getGaTauDen()));
//            txtNgayKhoiHanh.setText(String.valueOf(ve.getChuyenTau().getNgayKhoiHanh()));
//
//            txtMaVe_TT.setText(ve.getMaVe()); // Hiển thị trạng thái vé
//            txtMaHoaDon.setText(ve.getHoaDon().getMaHoaDon());
//            txtLoaiVe.setText(ve.getLoaiVe().name()); // Hiển thị loại vé
//
//            try (Connection connection = ConnectDB.getInstance().getConnection()) {
//                // Giả sử bạn đã có biến connection từ lớp DAO để truyền vào
//                double[] ketQua = tinhGiaVaLePhiTraVe(ve.getMaVe(), String.valueOf(ve.getHoaDon().getKhachHang().getLoaiKhachHang()), connection);
//                double giaVe = ketQua[0];
//                double lePhiTraVe = ketQua[1];
//
//                // Cập nhật giá và lệ phí vào các TextField
//                txtTongTienVe.setText(String.format("%.2f", giaVe)); // Cập nhật giá vé
//                txtLePhiTraVe.setText(String.format("%.2f", lePhiTraVe)); // Hiển thị lệ phí trả vé
//
//                // Tính tiền hoàn trả
//                double tienHoanTra = giaVe - lePhiTraVe;
//                txtTienHoanTra.setText(String.format("%.2f", tienHoanTra)); // Hiển thị tiền hoàn trả
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.out.println("Có lỗi khi tính toán giá vé và lệ phí.");
//            }
//        } else {
//            // Xử lý trường hợp không tìm thấy vé
//            System.out.println("Không tìm thấy vé với mã: " + maVe);
//        }
    }

    public static double[] tinhGiaVaLePhiTraVe(String maVe, String loaiKhachHang, Connection connection) throws SQLException {
        double giaCoBan = 0; // Giá cơ bản sẽ dựa vào số km
        double heSoGiaToa = 1.0;
        double heSoGiaCho = 1.0;
        double giaVe = 0;
        double lePhi = 0;
        double soKm = 0;
        double giamGia = 0;
        String loaiVe = "";
        String loaiToa = "";
        String maLoaiCho = "";
        LocalDateTime thoiGianDi = null;

        // Truy vấn thông tin cần thiết từ bảng
        String sql = "SELECT Ve.loaiVe, Ve.giamGiaVeTapThe, ChiTietChuyenTau.soKm, ChiTietChuyenTau.thoiGianDi, "
                + "ChiTietChuyenTau.maLoaiCho, ChiTietChuyenTau.loaiToa "
                + "FROM Ve JOIN ChiTietChuyenTau ON Ve.maChuyenTau = ChiTietChuyenTau.maChuyenTau "
                + "WHERE Ve.maVe = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, maVe);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                loaiVe = rs.getString("loaiVe");
                soKm = rs.getDouble("soKm");
                thoiGianDi = rs.getTimestamp("thoiGianDi").toLocalDateTime();
                maLoaiCho = rs.getString("maLoaiCho");
                loaiToa = rs.getString("loaiToa");
            }
        }

        // Xác định giá cơ bản dựa trên số km
        giaCoBan = soKm * 1000; // Ví dụ: 1000 VNĐ mỗi km

        // Xác định hệ số giá toa dựa vào loại toa
        switch (loaiToa) {
            case "VIP":
                heSoGiaToa = 1.5;
                break;
            case "STANDARD":
                heSoGiaToa = 1.0;
                break;
            case "ECONOMY":
                heSoGiaToa = 0.8;
                break;
            default:
                heSoGiaToa = 1.0;
                break;
        }

        // Xác định hệ số giá chỗ dựa vào mã loại chỗ
        if ("CS".equals(maLoaiCho)) {
            heSoGiaCho = 1.2; // Chỗ gần cửa sổ
        } else {
            heSoGiaCho = 1.0; // Chỗ bình thường
        }

        // Tính giá chỗ (giá vé ban đầu)
        giaVe = giaCoBan * heSoGiaToa * heSoGiaCho;

        // Tính phần trăm giảm giá dựa trên loại khách hàng
        switch (loaiKhachHang) {
            case "TRE_EM_6_10":
                giamGia = giaVe * 0.25;
                break;
            case "CAO_TUOI":
                giamGia = giaVe * 0.15;
                break;
            case "HOC_SINH":
                giamGia = giaVe * 0.10;
                break;
            case "THUONG_BINH":
                giamGia = giaVe * 0.30;
                break;
            default:
                giamGia = 0;
                break;
        }

        // Tính giá vé sau giảm giá
        giaVe -= giamGia;

        // Tính lệ phí trả vé dựa trên loại vé và thời gian
        long hoursToDeparture = LocalDateTime.now().until(thoiGianDi, ChronoUnit.HOURS);

        if ("CA_NHAN".equals(loaiVe)) {
            if (hoursToDeparture >= 48) {
                lePhi = giaVe * 0.10;
            } else if (hoursToDeparture >= 4) {
                lePhi = giaVe * 0.20;
            } else {
                lePhi = 0; // Không hoàn vé khi dưới 4 giờ
            }
        } else if ("TAP_THE".equals(loaiVe)) {
            if (hoursToDeparture >= 72) {
                lePhi = giaVe * 0.20;
            } else if (hoursToDeparture >= 24) {
                lePhi = giaVe * 0.30;
            } else {
                lePhi = 0; // Không hoàn vé khi dưới 24 giờ
            }
        }

        return new double[] { giaVe, lePhi };
    }
}
