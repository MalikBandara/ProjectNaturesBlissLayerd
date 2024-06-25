package lk.ijse.bo;


import lk.ijse.bo.custom.SpuerBO;
import lk.ijse.dto.Booking;
import lk.ijse.dto.GuestDTO;


import java.sql.SQLException;

import java.util.List;

public interface GuestBO extends SpuerBO {
    public  boolean saveGuest(GuestDTO guest) throws SQLException ;
    public  String getTouristEmailFromId(String id) throws SQLException ;
    public  GuestDTO getGuest(String id) throws SQLException ;
    public  String getGuestEmailFormID(String id) throws SQLException ;
    public  boolean changePassword(String id, String password) throws SQLException ;
    public List<String> getID() throws SQLException ;
    public  boolean updateGuest(List<Booking> bookingList) ;
    public  GuestDTO searchGuesttById(String identity) throws SQLException ;
}
