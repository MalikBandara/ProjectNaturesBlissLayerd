package lk.ijse.dao.impl;

import lk.ijse.dao.BookingDAO;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.entity.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookingDaoImpl implements BookingDAO {
    public boolean Save(Booking booking) throws SQLException {

        return   SQLUtil.execute("INSERT INTO Booking (Booking_id, Package_id, id,bookingDate,payId,Room_id) VALUES (?, ?, ?, ?,?,?)",
                booking.getBookingId(),
                booking.getPackageId(),
                booking.getIdentityDetails(),
                booking.getBookingDate(),
                booking.getPayId(),
                booking.getRoomId());

    }


    public  List<Booking> getAll() throws SQLException {

        List<Booking> puList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Booking");
        while (resultSet.next()){
            String Bookingid = resultSet.getString(1);
            String package_id = resultSet.getString(2);
            String identity = resultSet.getString(3);
            String payid = resultSet.getString(4);
            String roomId = resultSet.getString(5);
            String date = resultSet.getString(6);


            Booking Booking = new Booking(Bookingid, package_id, identity, payid,roomId,date);
            puList.add(Booking);
        }
        return puList;
    }


    public  boolean delete(String bookingId) throws SQLException {

        return SQLUtil.execute("DELETE FROM Booking WHERE Booking_id = ?",bookingId);
    }

    public  String getIds(String bookingId) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Room_id FROM Booking WHERE Booking_id = ?",bookingId);

        if (resultSet.next()) {
            return resultSet.getString("Room_id");
        } else {
            return null;
        }
    }

    public  String getLastId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Booking_id FROM Booking ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }

    public boolean isRoomReserved(String roomId) throws SQLException {

        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Room WHERE Room_id = ? AND Status = 'Reservation Booked'",roomId);
        return resultSet.next();
    }

    public ResultSet CheackifRoomIdBooked(String roomId) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Booking WHERE Room_id = ?",roomId);
        return resultSet;
    }

    public ResultSet isroombooked(String roomId) throws SQLException {

        ResultSet resultSet =  SQLUtil.execute("SELECT Status FROM Room WHERE Room_id = ?",roomId);
        return resultSet;

    }

    public boolean payIdUsed(String payId) throws SQLException {

        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Booking WHERE payId = ?",payId);
        return resultSet.next();
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }



    @Override
    public boolean update(Booking T) throws SQLException {
        return false;
    }

    @Override
    public Booking search(String Id) throws SQLException {
        return null;
    }
}

