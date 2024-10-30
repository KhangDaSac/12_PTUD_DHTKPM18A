package DAO;

import DTO.LoaiNhanVien;
import DTO.NhanVien;
import DTO.TrangThaiNhanVien;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVien_DAO {
    public NhanVien_DAO(){
        super();
    }

    public ArrayList<NhanVien> getAllEmployee() {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement sm = null;
        try {
            sm = con.prepareStatement("SELECT * FROM NhanVien");
            ResultSet rs = sm.executeQuery();

            while (rs.next()){
                String maNV = rs.getString("maNV");
                String cCCD = rs.getString("CCCD");
                String tenNV = rs.getString("tenNhanVien");
                String soDienThoai = rs.getString("soDienThoai");
                String diaChi = rs.getString("diaChi");

                TrangThaiNhanVien trangThaiNhanVien = TrangThaiNhanVien.valueOf(rs.getString("trangThaiNhanVien").toUpperCase());
                LoaiNhanVien loaiNhanVien = LoaiNhanVien.valueOf(rs.getString("loaiNhanVien").toUpperCase());

                NhanVien nv = new NhanVien(maNV, cCCD, tenNV, diaChi, soDienThoai, loaiNhanVien, trangThaiNhanVien);
                dsNV.add(nv);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                sm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dsNV;
    }

    public boolean create(NhanVien nv){
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
            stmt.setString(6, nv.getTrangThaiNhanVien().toString());
            stmt.setString(7, nv.getLoaiNhanVien().toString());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public boolean update(NhanVien nv){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("update NhanVien set CCCD = ?,ten = ?, diaChi = ?, soDienThoai = ?, loaiNhanVien = ?, trangThaiNhanVien = ? where maNV = ?");
            stmt.setString(1, nv.getCCCD());
            stmt.setString(2, nv.getTenNhanVien());
            stmt.setString(3, nv.getDiaChi());
            stmt.setString(4, nv.getSoDienThoai());
            stmt.setString(5, nv.getLoaiNhanVien().toString());
            stmt.setString(6, nv.getTrangThaiNhanVien().toString());
            stmt.setString(7, nv.getMaNhanVien());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

//    //Lấy nhân viên theo tên nhân viên
//    public ArrayList<NhanVien> getEmployee_ByName(String tenNhanVien) {
//        ArrayList<NhanVien> dsNV = new ArrayList<>();
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement sm = null;
//        try {
//            sm = con.prepareStatement("SELECT * FROM NhanVien WHERE tenNhanVien LIKE ?");
//            sm.setString(1, "%" + tenNhanVien + "%");
//            ResultSet rs = sm.executeQuery();
//
//            while (rs.next()) {
//                NhanVien nv = mapNhanVien(rs);
//                dsNV.add(nv);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (sm != null) sm.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return dsNV;
//    }
//
//    // Lấy nhân viên theo mã nhân viên
//    public NhanVien getEmployee_ByID(String maNhanVien) {
//        NhanVien nv = null;
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement sm = null;
//        try {
//            sm = con.prepareStatement("SELECT * FROM NhanVien WHERE maNhanVien = ?");
//            sm.setString(1, maNhanVien);
//            ResultSet rs = sm.executeQuery();
//
//            if (rs.next()) {
//                nv = mapNhanVien(rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (sm != null) sm.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return nv;
//    }
//
//    // Lấy nhân viên theo loại nhân viên
//    public ArrayList<NhanVien> getEmployee_ByType(LoaiNhanVien loaiNhanVien) {
//        ArrayList<NhanVien> dsNV = new ArrayList<>();
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement sm = null;
//        try {
//            sm = con.prepareStatement("SELECT * FROM NhanVien WHERE loaiNhanVien = ?");
//            sm.setString(1, loaiNhanVien.toString());
//            ResultSet rs = sm.executeQuery();
//
//            while (rs.next()) {
//                NhanVien nv = mapNhanVien(rs);
//                dsNV.add(nv);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (sm != null) sm.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return dsNV;
//    }
//
//    // Lấy nhân viên theo trạng thái
//    public ArrayList<NhanVien> getEmployee_ByState(TrangThaiNhanVien trangThaiNhanVien) {
//        ArrayList<NhanVien> dsNV = new ArrayList<>();
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement sm = null;
//        try {
//            sm = con.prepareStatement("SELECT * FROM NhanVien WHERE trangThaiNhanVien = ?");
//            sm.setString(1, trangThaiNhanVien.toString());
//            ResultSet rs = sm.executeQuery();
//
//            while (rs.next()) {
//                NhanVien nv = mapNhanVien(rs);
//                dsNV.add(nv);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (sm != null) sm.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return dsNV;
//    }
//
//    // Lấy nhân viên theo số điện thoại
//    public ArrayList<NhanVien> getEmployee_ByNumber(String soDienThoai) {
//        ArrayList<NhanVien> dsNV = new ArrayList<>();
//        ConnectDB.getInstance();
//        Connection con = ConnectDB.getConnection();
//        PreparedStatement sm = null;
//        try {
//            sm = con.prepareStatement("SELECT * FROM NhanVien WHERE soDienThoai = ?");
//            sm.setString(1, soDienThoai);
//            ResultSet rs = sm.executeQuery();
//
//            while (rs.next()) {
//                NhanVien nv = mapNhanVien(rs);
//                dsNV.add(nv);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (sm != null) sm.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return dsNV;
//    }

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
            e.printStackTrace();
        } finally {
            try {
                if (sm != null) sm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dsNV;
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

}
