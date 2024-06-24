package lk.ijse.bo;

import javafx.scene.control.Alert;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.Booking;
import lk.ijse.dto.RoomDTO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoomBO extends SpuerBO {
    public  boolean saveRoom(RoomDTO room) throws SQLException ;
    public  boolean updateRoomStatus(String roomId, String status) throws SQLException ;

    public  boolean updateRoom(RoomDTO room) throws SQLException ;


    public  RoomDTO searchRoomById(String roomId) throws SQLException ;
    public  boolean deleteRoom(String roomId) throws SQLException ;


    public List<RoomDTO> getAll() throws SQLException ;

    public  List<String> getIds() throws SQLException ;

    public  boolean updateRooms(List<Booking> odList) ;

    public  String getLastRoomId() throws SQLException ;

}
