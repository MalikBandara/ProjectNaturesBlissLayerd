package lk.ijse.dao.impl;

import lk.ijse.dao.ClientDAO;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ClientDTO;
import lk.ijse.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDAO {
    public  boolean Save(Client entity) throws SQLException, SQLException {

        return  SQLUtil.execute("INSERT INTO Client (id, name, email, phone, address, check_in, check_out) VALUES (?, ?, ?, ?, ?, ?, ?)",
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getCheckIn(),
                entity.getCheckOut());

    }
    public  String getLatestClientId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Client ORDER BY id DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString("id");
        } else {
            return null;
        }
    }

    public  boolean update(Client entity) throws SQLException {

        return  SQLUtil.execute("UPDATE Client SET name = ?, email = ?, phone = ?, address = ?, check_in = ?, check_out = ? WHERE id = ?",
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getCheckIn(),
                entity.getCheckOut(),
                entity.getId());
    }

    public  boolean delete(String clientId) throws SQLException {

        return SQLUtil.execute("DELETE FROM Client WHERE id = ?",clientId);
    }

    public  Client search(String clientId) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Client WHERE id = ?",clientId);
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String phone = resultSet.getString(4);
            String address = resultSet.getString(5);
            String checkIn = resultSet.getString(6);
            String checkOut = resultSet.getString(7);


            Client client = new Client(id, name, email, phone, address, checkIn, checkOut);

            return client;
        } else {
            return null;
        }
    }
    public  List<Client> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Client");

        List<Client> clientList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String address = resultSet.getString("address");
            String  checkIn = resultSet.getString("check_in");
            String checkOut = resultSet.getString("check_out");

            Client client = new Client(id, name, email, phone, address, checkIn, checkOut);
            clientList.add(client);
        }
        return clientList;
    }


    public  List<String> getIds() throws SQLException {


        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Client");
        List<String> mtIDList = new ArrayList<>();
        while (resultSet.next()){
            mtIDList.add(resultSet.getString(1));
        }
        return mtIDList;

    }

    public  String getLastId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Client ORDER BY id DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }
}
