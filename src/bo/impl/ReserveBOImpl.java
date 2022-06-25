package bo.impl;

import bo.SuperBO;
import bo.custom.ReserveBO;
import dao.DAOFactory;
import dao.impl.ReserveDAOImpl;
import dto.ReserveDTO;
import dto.StudentDTO;
import entity.Reserve;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReserveBOImpl implements ReserveBO, SuperBO {

    private final ReserveDAOImpl reserveDAO = (ReserveDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVE);

    @Override
    public boolean add(ReserveDTO reserveDTO) throws Exception {
        return reserveDAO.add(new Reserve(
                reserveDTO.getRes_id(),
                reserveDTO.getDate(),
                reserveDTO.getKey_money(),
                reserveDTO.getStudent_id(),
                reserveDTO.getRoom_id()
        ));
    }

    @Override
    public boolean update(ReserveDTO reserveDTO) throws Exception {
        return reserveDAO.update(new Reserve(
                reserveDTO.getRes_id(),
                reserveDTO.getDate(),
                reserveDTO.getKey_money(),
                reserveDTO.getStudent_id(),
                reserveDTO.getRoom_id()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return reserveDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return reserveDAO.generateNewID();
    }

    @Override
    public ArrayList<ReserveDTO> getAllReserve() throws SQLException, ClassNotFoundException {
        ArrayList<ReserveDTO> allReserve = new ArrayList<>();
        ArrayList<Reserve> all = reserveDAO.getAll();
        for (Reserve reserve : all) {
            allReserve.add(new ReserveDTO(reserve.getRes_id(),reserve.getDate(),reserve.getKey_money(),reserve.getStudent_id(),reserve.getRoom_id()));
        }
        return allReserve;
    }

    @Override
    public boolean ifReserveExist(String id) throws SQLException, ClassNotFoundException {
        return reserveDAO.ifReserveExist(id);
    }
}
