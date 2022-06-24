package bo.custom;


import dto.RoomDTO;

public interface RoomBO {
    boolean add(RoomDTO roomDTO) throws Exception;

    boolean update(RoomDTO roomDTO) throws Exception;

    boolean delete(String id) throws Exception;
}
