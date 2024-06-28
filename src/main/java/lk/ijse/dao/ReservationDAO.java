package lk.ijse.dao;


import lk.ijse.dao.custom.CrudDAO;

import lk.ijse.entity.Reservation;


import java.sql.SQLException;

public interface ReservationDAO extends CrudDAO<Reservation> {
    public   String getRoomIdForReservation(String reservationId) throws SQLException ;
    public boolean isRoomReserved(String roomId) throws SQLException ;

    public boolean isRoomIdRoom(String roomId) throws SQLException ;
}
