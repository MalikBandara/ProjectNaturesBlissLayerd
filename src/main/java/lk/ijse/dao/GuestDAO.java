package lk.ijse.dao;


import lk.ijse.dao.custom.CrudDAO;


import lk.ijse.entity.Guest;


import java.sql.SQLException;



public interface GuestDAO extends CrudDAO<Guest> {

    public  String getTouristEmailFromId(String id) throws SQLException ;
    public  Guest getGuest(String id) throws SQLException ;
    public  String getGuestEmailFormID(String id) throws SQLException ;
    public  boolean changePassword(String id, String password) throws SQLException ;
    public  Guest searchGuesttById(String identity) throws SQLException ;
}
