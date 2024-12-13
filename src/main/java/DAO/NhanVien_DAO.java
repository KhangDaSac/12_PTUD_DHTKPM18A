package DAO;

import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVien_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static NhanVien getNhanVienTheoMaNhanVien(String maNhanVien){
        try {
            String query = "select maNhanVien, CCCD, tenNhanVien, soDienThoai, email, loaiNhanVien from NhanVien where maNhanVien = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maNhanVien);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getString("maNhanVien"),
                        rs.getString("CCCD"),
                        rs.getString("tenNhanVien"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        LoaiNhanVien.valueOf(rs.getString("loaiNhanVien"))
                );
                return nhanVien;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    // Phương thức tiện ích để tạo NhanVien từ ResultSet
    private NhanVien mapNhanVien(ResultSet rs) throws SQLException {
        String maNhanVien = rs.getString("maNhanVien");
        String cccd = rs.getString("CCCD");
        String tenNhanVien = rs.getString("tenNhanVien");
        String diaChi = rs.getString("diaChi");
        String soDienThoai = rs.getString("soDienThoai");
        LoaiNhanVien loaiNhanVien = LoaiNhanVien.valueOf(rs.getString("loaiNhanVien"));
        TrangThaiNhanVien trangThaiNhanVien = TrangThaiNhanVien.valueOf(rs.getString("trangThaiNhanVien"));

        return new NhanVien(maNhanVien, cccd, tenNhanVien, diaChi, soDienThoai, loaiNhanVien, trangThaiNhanVien);
    }

    public ArrayList<NhanVien> getAllEmployee() {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        PreparedStatement sm = null;
        try {
            sm = con.prepareStatement("SELECT * FROM NhanVien");
            ResultSet rs = sm.executeQuery();

            while (rs.next()){
                String maNV = rs.getString("maNhanVien");
                String cCCD = rs.getString("CCCD");
                String tenNV = rs.getString("tenNhanVien");
                String soDienThoai = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");

                LoaiNhanVien loaiNhanVien = LoaiNhanVien.valueOf(rs.getString("loaiNhanVien"));
                TrangThaiNhanVien trangThaiNhanVien = TrangThaiNhanVien.valueOf(rs.getString("trangThaiNhanVien"));

                NhanVien nv = new NhanVien(maNV, cCCD, tenNV, diaChi, soDienThoai, email, loaiNhanVien, trangThaiNhanVien);
                dsNV.add(nv);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dsNV;
    }
    public void create(NhanVien nv){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("insert into NhanVien values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, nv.getMaNhanVien());
            stmt.setString(2, nv.getCCCD());
            stmt.setString(3, nv.getTenNhanVien());
            stmt.setString(4, nv.getSoDienThoai());
            stmt.setString(5, nv.getDiaChi());
            stmt.setString(7, nv.getLoaiNhanVien().toString());
            stmt.setString(6, nv.getTrangThaiNhanVien().toString());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(NhanVien nv){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("update NhanVien set CCCD = ?,tenNhanVien = ?, soDienThoai = ?, diaChi = ?, email = ?, loaiNhanVien = ?, trangThaiNhanVien = ? where maNhanVien = ?");
            stmt.setString(1, nv.getCCCD());
            stmt.setString(2, nv.getTenNhanVien());
            stmt.setString(3, nv.getSoDienThoai());
            stmt.setString(4, nv.getDiaChi());
            stmt.setString(5, nv.getEmail());
            stmt.setString(6, nv.getLoaiNhanVien().toString());
            stmt.setString(7, nv.getTrangThaiNhanVien().toString());
            stmt.setString(8, nv.getMaNhanVien());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Tìm nhân viên theo tiêu chí bất kì
    public ArrayList<NhanVien> getEmployees(String maNhanVien, String cCCD, String tenNhanVien,String soDienThoai, String diaChi, LoaiNhanVien loaiNhanVien, TrangThaiNhanVien trangThaiNhanVien) {
        ArrayList<NhanVien> dsNV = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement sm = null;
        StringBuilder query = new StringBuilder("SELECT * FROM NhanVien WHERE 1=1");

        // Danh sách các tham số
        ArrayList<Object> params = new ArrayList<>();

        // Thêm điều kiện tìm kiếm nếu có
        if (maNhanVien != null && !maNhanVien.isEmpty()) {
            query.append(" AND maNhanVien = ?");
            params.add(maNhanVien);
        }
        if (cCCD != null && !cCCD.isEmpty()) {
            query.append(" AND CCCD = ?");
            params.add(cCCD);
        }
        if (tenNhanVien != null && !tenNhanVien.isEmpty()) {
            query.append(" AND tenNhanVien LIKE ?");
            params.add("%" + tenNhanVien + "%");
        }
        if (diaChi != null && !diaChi.isEmpty()) {
            query.append(" AND diaChi LIKE ?");
            params.add("%" + diaChi + "%");
        }
        if (soDienThoai != null && !soDienThoai.isEmpty()) {
            query.append(" AND soDienThoai = ?");
            params.add(soDienThoai);
        }
        if (loaiNhanVien != null) {
            query.append(" AND loaiNhanVien = ?");
            params.add(loaiNhanVien.toString());
        }
        if (trangThaiNhanVien != null) {
            query.append(" AND trangThaiNhanVien = ?");
            params.add(trangThaiNhanVien.toString());
        }

        try {
            sm = con.prepareStatement(query.toString());

            // Thiết lập các giá trị tham số
            for (int i = 0; i < params.size(); i++) {
                sm.setObject(i + 1, params.get(i));
            }

            ResultSet rs = sm.executeQuery();

            while (rs.next()) {
                NhanVien nv = mapNhanVien(rs);
                dsNV.add(nv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsNV;
    }

    public static NhanVien getNhanvienTheoMa(String maNhanVien) {
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maNhanVien);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("maNhanVien"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setTenNhanVien(rs.getString("tenNhanVien"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setSoDienThoai(rs.getString("soDienThoai"));
                nv.setLoaiNhanVien(LoaiNhanVien.valueOf(rs.getString("loaiNhanVien")));
                nv.setTrangThaiNhanVien(TrangThaiNhanVien.valueOf(rs.getString("trangThaiNhanVien")));
                nv.setEmail(rs.getString("email"));
                return nv;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
