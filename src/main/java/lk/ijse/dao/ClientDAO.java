package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDAO;
import lk.ijse.entity.Client;
import java.sql.SQLException;


public interface ClientDAO extends CrudDAO<Client> {

    public  String getLatestClientId() throws SQLException ;

}
