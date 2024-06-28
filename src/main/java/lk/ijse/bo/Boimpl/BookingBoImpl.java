package lk.ijse.bo.Boimpl;

import javafx.scene.control.Alert;
import lk.ijse.bo.BookingBO;
import lk.ijse.dao.BookingDAO;
import lk.ijse.dao.PaymentDAO;
import lk.ijse.dao.RoomDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.BookingDTO;
import lk.ijse.entity.Booking;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingBoImpl implements BookingBO {

    BookingDAO bookingDAO = (BookingDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.BOOKING);

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.PAYMENT);
    public  boolean saveBookingAndUpdate(BookingDTO bookingDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        connection.setAutoCommit(false);
        try {

                   boolean bookingUpdate =  bookingDAO.Save(new Booking(bookingDTO.getBookingId(),
                    bookingDTO.getPackageId(),
                    bookingDTO.getIdentityDetails(),
                    bookingDTO.getBookingDate(),
                    bookingDTO.getPayId(),
                    bookingDTO.getRoomId()));



            boolean roomUpdateResult = roomDAO.UpdateRoomStatusForBooking(bookingDTO.getRoomId());



            boolean paymentIdStatusForBooking = paymentDAO.PaymentIdStatusForBooking(bookingDTO.getPayId());


            if (bookingUpdate && roomUpdateResult && paymentIdStatusForBooking) {
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
    }


    public  List<BookingDTO> getAllBooking() throws SQLException {

        List<BookingDTO> puList = new ArrayList<>();
        List<Booking> bookings = bookingDAO.getAll();
        for (Booking booking : bookings){
            BookingDTO bookingDTO = new BookingDTO(
                    booking.getBookingId(),
                    booking.getPackageId(),
                    booking.getIdentityDetails(),
                    booking.getBookingDate(),
                    booking.getPayId(),
                    booking.getRoomId()
            );
            puList.add(bookingDTO);

        }
        return puList;
    }
    public  boolean deleteBooking(String bookingId) throws SQLException {

        return bookingDAO.delete(bookingId);
    }

    public  String getRoomIdByBookingId(String bookingId) throws SQLException {

        return bookingDAO.getIds(bookingId);
    }

    public  String getLastBookingId() throws SQLException {

        return bookingDAO.getLastId();
    }

    public boolean isRoomReserved(String roomId) throws SQLException {

       return bookingDAO.isRoomReserved(roomId);
    }

    public ResultSet CheackifRoomIdBooked(String roomId) throws SQLException {

        return bookingDAO.CheackifRoomIdBooked(roomId);
    }

    public ResultSet isroombooked(String roomId) throws SQLException {

        return bookingDAO.isroombooked(roomId);

    }

    public boolean payIdUsed(String payId) throws SQLException {


        return bookingDAO.payIdUsed(payId);
    }
}
