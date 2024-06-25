package lk.ijse.dao.impl;

import javafx.scene.control.Alert;
import lk.ijse.dao.GuestDAO;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.Booking;
import lk.ijse.dto.GuestDTO;
import lk.ijse.entity.Guest;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDaoImpl implements GuestDAO {
    public  boolean Save(Guest entity) throws SQLException {

        return SQLUtil.execute("INSERT INTO Guest VALUES(?,?,?,?)", entity.getIdentityDetails(),
                entity.getName(), entity.getPassword(), entity.getEmail());
    }

    @Override
    public boolean update(Guest T) throws SQLException {
        return false;
    }

    public  String getTouristEmailFromId(String id) throws SQLException {

        ResultSet rs = SQLUtil.execute("SELECT Email FROM Guest WHERE identityDetails=?",id);
        if(rs.next()){
            return rs.getString("Email");
        }else{
            return null;
        }
    }
    public  Guest getGuest(String id) throws SQLException {
        try {

            ResultSet rs = SQLUtil.execute("SELECT * FROM Guest WHERE identityDetails=?",id);
            Guest entity = new Guest();
            if(rs.next()){
                entity.setIdentityDetails(rs.getString("identityDetails"));
                entity.setName(rs.getString("name"));
                entity.setPassword(rs.getString("password"));
                return entity;
            } else {
                return null;
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return null;
    }

    public  String getGuestEmailFormID(String id) throws SQLException {

        ResultSet rs = SQLUtil.execute("SELECT Email FROM Guest WHERE identityDetails=?",id);


        if(rs.next()){
            return rs.getString("email");
        }else{
            return null;
        }
    }

    //..............................................................................................................
    public  boolean changePassword(String id, String password) throws SQLException {

        return SQLUtil.execute("UPDATE Guest SET password= ? WHERE identityDetails =?",password,id);
    }


    public  List<String> getID() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT identityDetails FROM Guest");

        List<String> mtIDList = new ArrayList<>();
        while (resultSet.next()){
            mtIDList.add(resultSet.getString(1));
        }
        return mtIDList;

    }

    public  Guest search(String identity) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Guest WHERE identityDetails = ?",identity);
        if (resultSet.next()) {
            String identityDetails = resultSet.getString(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            String email = resultSet.getString(4);


            Guest guest = new Guest(identityDetails, name, password, email);

            return guest;
        } else {
            return null;
        }

    }

    @Override
    public boolean delete(String roomId) throws SQLException {
        return false;
    }

    @Override
    public List<Guest> getAll() throws SQLException {
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
    @Override
    public Guest searchGuesttById(String identity) throws SQLException {
        return null;
    }

    @Override
    public int getGuestCount() throws SQLException {
         ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS Guest_count FROM Guest");
        if(resultSet.next()) {
            return resultSet.getInt("Guest_count");
        }
        return 0;
    }

    public  boolean updateGuest(List<Booking> bookingList) {

        return true;
    }
}
