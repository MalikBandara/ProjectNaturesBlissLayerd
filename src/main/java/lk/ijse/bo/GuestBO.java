package lk.ijse.bo;


import lk.ijse.bo.custom.SuPerBO;
import lk.ijse.dto.BookingDTO;
import lk.ijse.dto.GuestDTO;


import java.sql.SQLException;

import java.util.List;

public interface GuestBO extends SuPerBO {
    public  boolean saveGuest(GuestDTO guest) throws SQLException ;
    public  String getTouristEmailFromId(String id) throws SQLException ;
    public  GuestDTO getGuest(String id) throws SQLException ;
    public  String getGuestEmailFormID(String id) throws SQLException ;
    public  boolean changePassword(String id, String password) throws SQLException ;
    public List<String> getID() throws SQLException ;
    public  boolean updateGuest(List<BookingDTO> bookingList) ;
    public  GuestDTO searchGuesttById(String identity) throws SQLException ;

    int getGuestCount() throws SQLException;
}
