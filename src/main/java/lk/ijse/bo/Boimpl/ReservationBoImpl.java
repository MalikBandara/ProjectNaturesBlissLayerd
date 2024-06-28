package lk.ijse.bo.Boimpl;


import javafx.scene.control.Alert;
import lk.ijse.bo.ReservationBO;
import lk.ijse.dao.ReservationDAO;
import lk.ijse.dao.RoomDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.Reservation;


import java.sql.Connection;

import java.sql.SQLException;

public class ReservationBoImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.RESERVATION);

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);
    public   String getRoomIdForReservation(String reservationId) throws SQLException {

        return reservationDAO.getRoomIdForReservation(reservationId);


    }


    public  boolean SaveReservation(ReservationDTO reservation) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);


            boolean b = reservationDAO.Save(new Reservation(reservation.getId(),
                    reservation.getGuestName(),
                    reservation.getCheckInDate(),
                    reservation.getCheckOutDate(),
                    reservation.getRoomType(),
                    reservation.getNumGuests(),
                    reservation.getRoomId()));



            roomDAO.UpdateReservationStatus(reservation.getRoomId());


            if (b) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);

        }
    }

    public  String getLastReservationId() throws SQLException {

        return reservationDAO.getLastId();
    }

    public boolean isRoomReservedForReservation(String roomId) throws SQLException {

        return reservationDAO.isRoomReserved(roomId);
    }

    public boolean isRoomIdReservedForRoom(String roomId) throws SQLException {

        return reservationDAO.isRoomIdRoom(roomId);
    }


}
