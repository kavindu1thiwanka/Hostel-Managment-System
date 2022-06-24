package bo.custom;



import dto.StudentDTO;

public interface StudentBO {
    boolean add(StudentDTO customerDTO) throws Exception;

    boolean update(StudentDTO customerDTO) throws Exception;

    boolean delete(String id) throws Exception;
}
