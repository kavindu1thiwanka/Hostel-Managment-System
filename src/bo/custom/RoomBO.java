package bo.custom;


import dto.RoomDTO;

public interface RoomBO {
    public boolean add(RoomDTO roomDTO) throws Exception;

    public boolean update(RoomDTO roomDTO) throws Exception;

    public boolean delete(String id) throws Exception;
}
