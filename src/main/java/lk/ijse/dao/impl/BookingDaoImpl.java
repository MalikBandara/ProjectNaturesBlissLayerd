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
        /*Connection connection = DBConnection.getInstance().getConnection();

        connection.setAutoCommit(false);
        try {


            // Insert into booking table
            String bookingSql = "INSERT INTO Booking (Booking_id, Package_id, id,bookingDate,payId,Room_id) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement bookingPstm = connection.prepareStatement(bookingSql);
            bookingPstm.setString(1, bookingId);
            bookingPstm.setString(2, packageId);
            bookingPstm.setString(3, identitydetails);
            bookingPstm.setString(4, date);
            bookingPstm.setString(5, payid);
            bookingPstm.setString(6, roomID);


            int bookingResult = bookingPstm.executeUpdate();

            // Update Rooms table to set booked = true for the specified Room_id
            String roomUpdateSql = "UPDATE Room SET Status = 'Booked' WHERE Room_id = ?";
            PreparedStatement roomUpdatePstm = connection.prepareStatement(roomUpdateSql);
            roomUpdatePstm.setString(1, roomID);
            int roomUpdateResult = roomUpdatePstm.executeUpdate();



            String paymentUpdateSql = "UPDATE payment SET method = 'Paid' WHERE payId = ?";
            PreparedStatement paymentUpdatePstm = connection.prepareStatement(paymentUpdateSql);
            paymentUpdatePstm.setString(1, payid);
            int paymentUpdateResult = paymentUpdatePstm.executeUpdate();


            if (bookingResult > 0 && roomUpdateResult > 0 && paymentUpdateResult > 0) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);

        }

         */
              return   SQLUtil.execute("INSERT INTO Booking (Booking_id, Package_id, id,bookingDate,payId,Room_id) VALUES (?, ?, ?, ?,?,?)",
                booking.getBookingId(),
                booking.getPackageId(),
                booking.getIdentityDetails(),
                booking.getBookingDate(),
                booking.getPayId(),
                booking.getRoomId());

    }


    public  List<Booking> getAll() throws SQLException {
        /*
        String  sql = "SELECT * FROM Booking";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

         */
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
        /*
        String sql = "DELETE FROM Booking WHERE Booking_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, bookingId);

        return pstm.executeUpdate() > 0;

         */
        return SQLUtil.execute("DELETE FROM Booking WHERE Booking_id = ?",bookingId);
    }

    public  String getIds(String bookingId) throws SQLException {
        /*
        String sql = "SELECT Room_id FROM Booking WHERE Booking_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, bookingId);
        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet = SQLUtil.execute("SELECT Room_id FROM Booking WHERE Booking_id = ?",bookingId);

        if (resultSet.next()) {
            return resultSet.getString("Room_id");
        } else {
            return null;
        }
    }

    public  String getLastId() throws SQLException {
        /*

        String sql = "SELECT Booking_id FROM Booking ORDER BY id DESC LIMIT 1";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();


         */
        ResultSet resultSet = SQLUtil.execute("SELECT Booking_id FROM Booking ORDER BY id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }

    public boolean isRoomReserved(String roomId) throws SQLException {
        /*
        String sql = "SELECT * FROM Room WHERE Room_id = ? AND Status = 'Reservation Booked'";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, roomId);
        ResultSet resultSet = pstm.executeQuery();

        return resultSet.next(); // Returns true if the room is already booked, false otherwise

         */
        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Room WHERE Room_id = ? AND Status = 'Reservation Booked'",roomId);
        return resultSet.next();
    }

    public ResultSet CheackifRoomIdBooked(String roomId) throws SQLException {
        /*
        String sql = "SELECT * FROM Booking WHERE Room_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, roomId);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;

         */
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Booking WHERE Room_id = ?",roomId);
        return resultSet;
    }

    public ResultSet isroombooked(String roomId) throws SQLException {
        /*
        Connection connection = DBConnection.getInstance().getConnection();
        String roomStatusSql = "SELECT Status FROM Room WHERE Room_id = ?";
        PreparedStatement roomStatusPstm = connection.prepareStatement(roomStatusSql);
        roomStatusPstm.setString(1, roomId);
        ResultSet roomStatusResultSet = roomStatusPstm.executeQuery();
        return roomStatusResultSet;

         */
        ResultSet resultSet =  SQLUtil.execute("SELECT Status FROM Room WHERE Room_id = ?",roomId);
        return resultSet;

    }

    public boolean payIdUsed(String payId) throws SQLException {
        /*
        String sql = "SELECT * FROM Booking WHERE payId = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, payId);
        ResultSet resultSet = pstm.executeQuery();

        return resultSet.next(); // Returns true if the pay ID has been used before, false otherwise

         */
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

