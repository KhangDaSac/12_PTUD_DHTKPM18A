package BUS;

import DAO.HuyVe_DAO;
import DTO.Ve;

public class HuyVe_BUS {
    private HuyVe_DAO huyVeDao;

    public HuyVe_BUS() {
        huyVeDao = new HuyVe_DAO();
    }

    // Phương thức để lấy vé theo mã
    public Ve getTicketByID(String maVe) {
        return huyVeDao.getTicketByID(maVe);
    }
// mo pj nay trong netbeans di
    // Phương thức để cập nhật trạng thái vé
    public boolean updateTicketStatus(String maVe) {
        return huyVeDao.updateTicketStatus(maVe);
    }
}
