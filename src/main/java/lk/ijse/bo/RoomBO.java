package lk.ijse.bo;

import lk.ijse.bo.custom.SuPerBO;
import lk.ijse.dto.RoomDTO;


import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuPerBO {
    public  boolean saveRoom(RoomDTO room) throws SQLException ;
    public  boolean updateRoomStatus(String roomId, String status) throws SQLException ;
    public  boolean updateRoom(RoomDTO room) throws SQLException ;
    public  RoomDTO searchRoomById(String roomId) throws SQLException ;
    public  boolean deleteRoom(String roomId) throws SQLException ;
    public List<RoomDTO> getAll() throws SQLException ;
    public  List<String> getIds() throws SQLException ;
    public  String getLastRoomId() throws SQLException ;

    int getRoomsCount() throws SQLException;
}
