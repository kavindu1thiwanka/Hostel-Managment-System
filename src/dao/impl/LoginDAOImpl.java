package dao.impl;

import dao.custom.LoginDAO;
import entity.Login;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.FactoryConfiguration;
import java.sql.SQLException;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {

//    @Override
//    public boolean update(Login login) throws SQLException, ClassNotFoundException {
//        throw new UnsupportedOperationException("Not Supported Yet");
//    }

    @Override
    public boolean userSearch(String userName, String Password) throws SQLException, ClassNotFoundException {

        boolean login=false;
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query= (Query) session.createQuery("SELECT userName,password from Login where userName=:username and password=:password");
        query.setParameter("username", userName);
        query.setParameter("password", Password);
        Object user=  query.uniqueResult();
        if(user!=null) {
            System.out.println("username and password are valid");
            login=true;
        }else {
            login=false;
            System.out.println("username and password are not valid");
        }

        session.close();

        return login;
    }

    @Override
    public boolean add(Login entity) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Login entity) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean delete(String s) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Login find(String s) throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<Login> findAll() throws Exception {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Login get(String s) throws Exception {
        return null;
    }
}
