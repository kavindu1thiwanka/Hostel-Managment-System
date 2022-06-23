package dao.custom;

import dao.SuperDAO;
import entity.Login;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO<Login, String> {
    Login userSearch(String userName,String Password) throws SQLException, ClassNotFoundException;
}
