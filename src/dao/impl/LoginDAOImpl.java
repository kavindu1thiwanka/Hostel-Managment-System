package dao.impl;

import entity.Login;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.FactoryConfiguration;
import java.sql.SQLException;

public class LoginDAOImpl {

//    @Override
//    public boolean update(Login login) throws SQLException, ClassNotFoundException {
//        throw new UnsupportedOperationException("Not Supported Yet");
//    }

//    @Override
    public Login userSearch(String userName, String Password) throws SQLException, ClassNotFoundException {
        System.out.println("Hello");
        Session session = FactoryConfiguration.getInstance().getSession();
        System.out.println("Hello1");
        Query query= (Query) session.createQuery("from Login where userName=:username and password=:password");
        query.setParameter("userName", userName);
        query.setParameter("password", Password);
        System.out.println("Hello2");
        Login user=(Login)query.uniqueResult();
        if(user!=null) {
            System.out.println("username and password are valid");
        }else {
            System.out.println("username and password are not valid");
        }

        session.close();

        return new Login(userName,Password);
    }
}
