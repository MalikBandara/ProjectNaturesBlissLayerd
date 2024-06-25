package lk.ijse.bo;

import lk.ijse.bo.custom.SpuerBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminBO extends SpuerBO {
    public ResultSet cheackCredintial(String userName) throws SQLException ;
}
