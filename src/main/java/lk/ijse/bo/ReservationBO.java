package lk.ijse.bo;


import lk.ijse.bo.custom.SuPerBO;
import lk.ijse.dto.ReservationDTO;

import java.sql.SQLException;


public interface ReservationBO extends SuPerBO {
    public   String getRoomIdForReservation(String reservationId) throws SQLException ;

    public  boolean SaveReservation(ReservationDTO reservation) throws SQLException ;

    public  String getLastReservationId() throws SQLException ;

    public boolean isRoomReservedForReservation(String roomId) throws SQLException ;

    public boolean isRoomIdReservedForRoom(String roomId) throws SQLException ;

}
