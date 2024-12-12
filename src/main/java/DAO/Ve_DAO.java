package DAO;

import BUS.QuanLyVe_BUS;
import DTO.*;
import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class Ve_DAO {
    private static Connection con = ConnectDB.getInstance().getConnection();
    public static String layDuoiMaVeLonNhatCuaNgayHienTai(String ngayHienTai){
        String maVeLonNhat = null;
        try {
            String query = "select max(SUBSTRING(maVe, LEN(maVe) - 7, 8)) as duoiMaVe from Ve where maVe like 'VE[A-Z][A-Z]' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maVeLonNhat = rs.getString("duoiMaVe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maVeLonNhat;
    }

    public static boolean themDanhSachVe(ArrayList<Ve> danhSachVe){
        String query = "insert into Ve values (?, ?, ?, ?, ?, ?, ?, ?)";
        for(Ve ve : danhSachVe){
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, ve.getMaVe());
                if(ve.getHoaDonBanVe() != null)
                    statement.setString(2, ve.getHoaDonBanVe().getMaHoaDonBanVe());
                else
                    statement.setNull(2, Types.VARCHAR);
                statement.setString(3, ve.getThongTinGaTauDi().getChuyenTau().getMaChuyenTau());
                statement.setString(4, ve.getThongTinGaTauDi().getGaTau().getMaGaTau());
                statement.setString(5, ve.getThongTinGaTauDen().getGaTau().getMaGaTau());
                statement.setDouble(6, ve.getPhanTramGiamGiaVeTapThe());
                statement.setString(7, ve.getLoaiVe().toStringSQL());
                statement.setString(8, ve.getTrangThaiVe().toString());
                statement.execute();

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Ve> getDanhSachVeTheoHoaDonBanVe(String maHoaDonBanVe){
        ArrayList<Ve> dsVe = new ArrayList<>();
        try {
            String query = "SELECT maVe, phanTramGiamGiaVeTapThe FROM Ve where maHoaDonBanVe = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maHoaDonBanVe);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                dsVe.add(new Ve(
                        rs.getString("maVe"),
                        rs.getDouble("phanTramGiamGiaVeTapThe"),
                        ChiTietVe_DAO.getDanhSachChiTietVeTheoMaVe_BaoCao(rs.getString("maVe"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }


    public static boolean themVeMoi(Ve ve,String maCT){
        try{
            String query ="insert into Ve values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,ve.getMaVe());
            statement.setNull(2, java.sql.Types.VARCHAR );
            statement.setString(3,maCT);
            statement.setString(4,ve.getThongTinGaTauDi().getGaTau().getMaGaTau());
            statement.setString(5,ve.getThongTinGaTauDen().getGaTau().getMaGaTau());
            statement.setDouble(6,0);
            statement.setString(7,ve.getLoaiVe().toStringSQL());
            statement.setString(8,ve.getTrangThaiVe().toString());
            statement.execute();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean capNhatTrangThaiHuyChoVeDoi(String maVe){
        try{
            String query = "UPDATE Ve SET trangThaiVe = 'DAHUY' WHERE maVe =?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maVe);
            statement.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String layMaVeCaNhanLonNhatCuaNgayHienTai(String ngayHienTai){
        String maVeLonNhat = null;
        try {
            String query = "select max(maVe) as maVe from Ve where maVe like 'VECN' + ? + '%'";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ngayHienTai);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                maVeLonNhat = rs.getString("maVe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maVeLonNhat;
    }

    public static Ve getVeTheoMa(String maVe){
        Ve veTim = new Ve();
        try{
            String query ="exec dbo.UDP_GetThongTinVeTheoMaVe ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, maVe);
            ResultSet rs = statement.executeQuery();
            while( rs.next()){
                String ma = rs.getString("maVe");
                HoaDonBanVe maHoaHon = new HoaDonBanVe(rs.getString("maHoaDonBanVe"));
                ChuyenTau maChuyenTau = new ChuyenTau(rs.getString("maChuyenTau"));
                GaTau gaDi = new GaTau(rs.getString("maGaDi"),rs.getString("tenGaDi"));
                GaTau gaDen= new GaTau(rs.getString("maGaDen"),rs.getString("tenGaDen"));
                ChiTietChuyenTau thongTinDi = new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),
                        gaDi,
                        rs.getTimestamp("thoiGianDi_Di").toLocalDateTime(),
                        rs.getTimestamp("thoiGianDen_Di").toLocalDateTime(),
                        rs.getInt("thuTuGa_Di"),rs.getDouble("soKm_Di"));
                ChiTietChuyenTau thongTinDen= new ChiTietChuyenTau(new ChuyenTau(rs.getString("maChuyenTau")),
                        gaDen,
                        rs.getTimestamp("thoiGianDi_Den").toLocalDateTime(),
                        rs.getTimestamp("thoiGianDen_Den").toLocalDateTime(),
                        rs.getInt("thuTuGa_Den"),rs.getDouble("soKm_Den"));
                LoaiVe loai = LoaiVe.valueOf(rs.getString("loaiVe"));
                TrangThaiVe trangThai = TrangThaiVe.valueOf(rs.getString("trangThaiVe"));
                veTim = new Ve(ma,maHoaHon,thongTinDi,thongTinDen,loai,trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veTim;
    }

    public static ArrayList<Ve> xuatDanhSachVeTheoMaHoaDonBanVe(String maHoaDon){
        ArrayList<Ve> danhSachVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query= "select * from ChiTietVe as ctv join Ve as v on ctv.maVe=v.maVe join HoaDonBanVe as hdbv on v.maHoaDonBanVe= hdbv.maHoaDonBanVe where hdbv.maHoaDonBanVe = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                ChiTietChuyenTau thongTinGaDi = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"),rs.getString("maGaDi"));
                ChiTietChuyenTau thongTinGaDen = ChiTietChuyenTau_DAO.getChiTietTuyenTauTheoChuyenTauVaGaTau(rs.getString("maChuyenTau"),rs.getString("maGaDen"));
                ChiTietVe chiTietVe = new ChiTietVe(new Ve(rs.getString("maVe")), new Cho_DAO().getChoTheoMaCho(rs.getString("maCho")) ,new KhachHang_DAO().getKhachHangTheoMaKhachHang(rs.getString("maKhachHang")), rs.getDouble("giaCho"));
                Ve ve = null;
                for(Ve v: danhSachVe){
                    if(v.getMaVe().equals(rs.getString("maVe"))){
                        ve=v;
                    }
                }
                    if(ve==null){
                        ve = new Ve(rs.getString("maVe"),new HoaDonBanVe(rs.getString("maHoaDonBanVe")),thongTinGaDi, thongTinGaDen,LoaiVe.valueOf(rs.getString("loaiVe")),TrangThaiVe.valueOf(rs.getString("trangThaiVe")));
                        danhSachVe.add(ve);
                    }
                ve.addChiTietVe(chiTietVe);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return danhSachVe;
    }
    public static ArrayList<Ve> getDanhSachVeTheoMaHoaDonDoi(String maHoaDon){
        ArrayList<Ve> dsVe = new ArrayList<>();
        Connection con = ConnectDB.getInstance().getConnection();
        String query = "select  * from HoaDonDoiVe ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,maHoaDon);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                ArrayList<ChiTietVe> dschiTietVeMoi = ChiTietVe_DAO.xuatDanhSachChiTietVeTheoMaVe(rs.getString("maVeMoi"));
                ArrayList<ChiTietVe> dschiTietVeCu = ChiTietVe_DAO.xuatDanhSachChiTietVeTheoMaVe(rs.getString("maVeCu"));
                Ve veMoi = getVeTheoMa(rs.getString("maVeMoi"));
                Ve veCu = getVeTheoMa(rs.getString("maVeCu"));
                veMoi.setDanhSachChiTietVe(dschiTietVeMoi);
                veCu.setDanhSachChiTietVe(dschiTietVeCu);
                dsVe.add(veMoi);
                dsVe.add(veCu);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dsVe;
    }

}
