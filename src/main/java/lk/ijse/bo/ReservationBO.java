package lk.ijse.bo;


import javafx.scene.control.Alert;
import lk.ijse.bo.custom.SpuerBO;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public interface ReservationBO extends SpuerBO {
    public   String getRoomIdForReservation(String reservationId) throws SQLException ;

    public  boolean SaveReservation(ReservationDTO reservation) throws SQLException ;

    public  String getLastReservationId() throws SQLException ;

    public boolean isRoomReservedForReservation(String roomId) throws SQLException ;

    public boolean isRoomIdReservedForRoom(String roomId) throws SQLException ;

}
