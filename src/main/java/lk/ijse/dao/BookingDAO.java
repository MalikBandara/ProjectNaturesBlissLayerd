package lk.ijse.dao;


import lk.ijse.dao.custom.CrudDAO;
import lk.ijse.entity.Booking;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public interface BookingDAO extends CrudDAO<Booking> {
    public boolean Save(Booking booking) throws SQLException ;

    public  List<Booking> getAll() throws SQLException ;
    public  boolean delete(String bookingId) throws SQLException ;

    public  String getIds(String bookingId) throws SQLException ;

    public  String getLastId() throws SQLException ;

    public boolean isRoomReserved(String roomId) throws SQLException ;

    public ResultSet CheackifRoomIdBooked(String roomId) throws SQLException;

    public ResultSet isroombooked(String roomId) throws SQLException ;

    public boolean payIdUsed(String payId) throws SQLException ;

}
