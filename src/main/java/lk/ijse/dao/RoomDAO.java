package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDAO;

import lk.ijse.entity.Room;

import java.sql.SQLException;


public interface RoomDAO extends CrudDAO<Room> {
    public  boolean updateRoomStatus(String roomId, String status) throws SQLException ;


    int getRoomsCount() throws SQLException;

    void UpdateReservationStatus(String roomid) throws SQLException;

    boolean UpdateRoomStatusForBooking(String roomId) throws SQLException ;

}
