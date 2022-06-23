package bo.impl;


import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;

public class StudentBOImpl implements StudentBO {

    StudentDAOImpl studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public boolean add(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(
                studentDTO.getId(),
                studentDTO.getFullName(),
                studentDTO.getAddress(),
                studentDTO.getRoomNum(),
                studentDTO.getKeyMoneyStatus()
        ));
    }

    @Override
    public boolean update(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(
                studentDTO.getId(),
                studentDTO.getFullName(),
                studentDTO.getAddress(),
                studentDTO.getRoomNum(),
                studentDTO.getKeyMoneyStatus()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);
    }

}
