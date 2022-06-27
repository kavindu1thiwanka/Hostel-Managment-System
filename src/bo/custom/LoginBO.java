package bo.custom;

import java.sql.SQLException;

public interface LoginBO {
    boolean ifUserExists(String userName, String Password) throws SQLException, ClassNotFoundException;
}
