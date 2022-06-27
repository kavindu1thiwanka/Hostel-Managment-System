package bo.impl;

import bo.SuperBO;
import bo.custom.LoginBO;
import dao.DAOFactory;
import dao.custom.LoginDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO , SuperBO {
//    private final LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOType.LOGIN);
    private final LoginDAO loginDAO = (LoginDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LOGIN);


    @Override
    public boolean ifUserExists(String userName, String Password) throws SQLException, ClassNotFoundException {
        return loginDAO.userSearch(userName,Password);
    }
}
