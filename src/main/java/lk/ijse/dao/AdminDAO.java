package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminDAO extends CrudDAO<Admin> {
     ResultSet cheackCredintial(String userName) throws SQLException ;
}
