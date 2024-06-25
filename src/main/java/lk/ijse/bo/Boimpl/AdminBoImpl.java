package lk.ijse.bo.Boimpl;

import lk.ijse.bo.AdminBO;
import lk.ijse.dao.AdminDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;



import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminBoImpl implements AdminBO {

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ADMIN);
    public ResultSet cheackCredintial(String userName) throws SQLException {
        return adminDAO.cheackCredintial(userName);
    }
}
