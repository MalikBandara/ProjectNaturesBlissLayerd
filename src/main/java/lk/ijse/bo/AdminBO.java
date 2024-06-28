package lk.ijse.bo;

import lk.ijse.bo.custom.SuPerBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminBO extends SuPerBO {
    public ResultSet cheackCredintial(String userName) throws SQLException ;
}
