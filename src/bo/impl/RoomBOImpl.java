package bo.impl;

import bo.custom.RoomBO;
import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.impl.RoomDAOImpl;
import dto.RoomDTO;
import entity.Room;


public class RoomBOImpl implements RoomBO {
    RoomDAOImpl roomDAO = DAOFactory.getInstance().getDAO(DAOType.ROOM);

    @Override
    public boolean add(RoomDTO roomDTO) throws Exception {
        return roomDAO.add(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getRoom_type(),
                roomDTO.getMonthly_rent(),
                roomDTO.getRoom_qty()
        ));
    }

    @Override
    public boolean update(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(new Room(
                roomDTO.getRoom_id(),
                roomDTO.getRoom_type(),
                roomDTO.getMonthly_rent(),
                roomDTO.getRoom_qty()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return roomDAO.delete(id);
    }
}
