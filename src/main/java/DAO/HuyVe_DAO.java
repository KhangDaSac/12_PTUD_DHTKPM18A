package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HuyVe_DAO {
    public static Ve getTicketByID(String maVe) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement sm = null;
        Ve ve = null;
        try {
            con = ConnectDB.getConnection();
            if (con == null) {
                throw new SQLException("Không thể thiết lập kết nối với cơ sở dữ liệu.");
            }
            sm = con.prepareStatement("SELECT * FROM Ve WHERE maVe = ?");
            sm.setString(1, maVe);

            ResultSet rs = sm.executeQuery();

            while (rs.next()) {
                maVe = rs.getString("maVe");
                String maHoaDon = rs.getString("maHoaDon");
                String maChuyenTau = rs.getString("maChuyenTau");
                String maGaDi = rs.getString("maGaDi");
                String maGaDen = rs.getString("maGaDen");
                double giamGiaVeTapThe = rs.getDouble("giamGiaVeTapThe");
                double tongTienVe = rs.getDouble("tongTienVe");

                // Lấy giá trị cho loại vé và trạng thái vé mà không sử dụng valueOf
                String loaiVeString = rs.getString("loaiVe");
                LoaiVe loaiVe = LoaiVe.valueOf(loaiVeString.toUpperCase()); // Chuyển đổi loại vé sang chữ hoa

                String trangThaiVeString = rs.getString("trangThaiVe");
                TrangThaiVe trangThaiVe = TrangThaiVe.valueOf(trangThaiVeString.toUpperCase()); // Chuyển đổi trạng thái vé sang chữ hoa

                // Khởi tạo các đối tượng liên quan
                HoaDonBanVe hoaDon = new HoaDonBanVe(maHoaDon);
                ChuyenTau chuyenTau = new ChuyenTau(maChuyenTau);
                GaTau gaTauDi = new GaTau(maGaDi);
                GaTau gaTauDen = new GaTau(maGaDen);

                // Khởi tạo đối tượng Ve với tất cả các thông tin cần thiết
                //ve = new Ve(maVe, hoaDon, chuyenTau, gaTauDi, gaTauDen, giamGiaVeTapThe, tongTienVe, loaiVe, trangThaiVe);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng PreparedStatement và Connection
            try {
                if (sm != null) {
                    sm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ve;
    }

    public static boolean updateTicketStatus(String maVe) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement sm = null;

        try {
            // Truy vấn SQL để cập nhật trạng thái vé
            String sql = "UPDATE Ve SET trangThaiVe = ? WHERE maVe = ? AND trangThaiVe = ?";
            sm = con.prepareStatement(sql);

            // Thiết lập giá trị cho câu lệnh SQL
            sm.setString(1, TrangThaiVe.DAHUY.name()); // Trạng thái mới
            sm.setString(2, maVe); // Mã vé cần cập nhật
            sm.setString(3, TrangThaiVe.DANGSUDUNG.name()); // Trạng thái hiện tại để đảm bảo cập nhật chỉ khi đang sử dụng

            // Thực thi câu lệnh cập nhật
            int rowsUpdated = sm.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất 1 bản ghi được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        } finally {
            // Đóng kết nối và PreparedStatement
            try {
                if (sm != null) {
                    sm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
