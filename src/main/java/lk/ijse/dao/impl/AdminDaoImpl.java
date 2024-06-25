package lk.ijse.dao.impl;

import lk.ijse.dao.AdminDAO;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDAO {

    public ResultSet cheackCredintial(String userName) throws SQLException {

        return SQLUtil.execute("SELECT Email , Passward FROM Admin WHERE Email = ?",userName);
    }

    @Override
    public boolean Save(Admin T) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Admin T) throws SQLException {
        return false;
    }

    @Override
    public Admin search(String Id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String roomId) throws SQLException {
        return false;
    }

    @Override
    public List<Admin> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
