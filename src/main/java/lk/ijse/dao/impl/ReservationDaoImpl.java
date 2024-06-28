package lk.ijse.dao.impl;


import javafx.scene.control.Alert;
import lk.ijse.dao.ReservationDAO;

import lk.ijse.dao.custom.SQLUtil;

import lk.ijse.entity.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservationDaoImpl implements ReservationDAO {




    public   String getRoomIdForReservation(String reservationId) throws SQLException {

             ResultSet resultSet = SQLUtil.execute("SELECT Room_id FROM Reservation WHERE id = ?",reservationId);
                if (resultSet.next()) {
                    return resultSet.getString("Room_id");
                } else {
                    throw new SQLException("Room ID not found for reservation: " + reservationId);
                }


    }


    public  boolean Save(Reservation reservation) throws SQLException {

        return SQLUtil.execute("INSERT INTO Reservation VALUES(?,?,?,?,?,?,?)",
                reservation.getId(),
                reservation.getGuestName(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                reservation.getRoomType(),
                reservation.getNumGuests(),
                reservation.getRoomId());



    }

    public  String getLastId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Reservation ORDER BY id DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }

    public boolean isRoomReserved(String roomId) throws SQLException {

        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Room WHERE Room_id = ? AND Status = 'Reservation Booked'" ,roomId);
        return resultSet.next();
    }

    public boolean isRoomIdRoom(String roomId) throws SQLException {

        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Room WHERE Room_id = ? AND Status = 'Booked'",roomId);
        return resultSet.next();
    }

    @Override
    public boolean update(Reservation T) throws SQLException {
        return false;
    }

    @Override
    public Reservation search(String Id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String roomId) throws SQLException {
        return false;
    }

    @Override
    public List<Reservation> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }
}
