package bo.custom;


import bo.SuperBO;
import dto.StudentDTO;

public interface StudentBO extends SuperBO {
    public boolean add(StudentDTO customerDTO) throws Exception;

    public boolean update(StudentDTO customerDTO) throws Exception;

    public boolean delete(String id) throws Exception;
}
