package lk.ijse.bo;

import lk.ijse.bo.custom.SuPerBO;
import lk.ijse.dto.BookingDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BookingBO extends SuPerBO {
    public  boolean saveBookingAndUpdate(BookingDTO bookingDTO) throws SQLException ;
    public List<BookingDTO> getAllBooking() throws SQLException ;
    public  boolean deleteBooking(String bookingId) throws SQLException;
    public  String getRoomIdByBookingId(String bookingId) throws SQLException ;

    public  String getLastBookingId() throws SQLException ;

    public boolean isRoomReserved(String roomId) throws SQLException ;

    public ResultSet CheackifRoomIdBooked(String roomId) throws SQLException ;

    public ResultSet isroombooked(String roomId) throws SQLException ;

    public boolean payIdUsed(String payId) throws SQLException ;
}
