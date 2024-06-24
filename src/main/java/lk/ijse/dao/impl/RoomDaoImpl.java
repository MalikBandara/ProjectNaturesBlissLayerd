package lk.ijse.dao.impl;

import javafx.scene.control.Alert;
import lk.ijse.dao.RoomDAO;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.Booking;
import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDAO {

    public  boolean Save(Room entity) throws SQLException {

        return SQLUtil.execute("INSERT INTO Room (Room_id, Room_number, Type, Rate, Status) VALUES (?,?,?,?,?)",
                entity.getRoomId(),
                entity.getRoomNumber(),
                entity.getType(),
                entity.getRate(),
                entity.getStatus());
    }
    public  boolean updateRoomStatus(String roomId, String status) throws SQLException {

        return SQLUtil.execute("UPDATE Room SET status = ? WHERE Room_id = ?",roomId,status);
    }

    public  boolean update(Room entity) throws SQLException {

        return SQLUtil.execute("UPDATE Room SET Room_number = ?, Type = ?, Rate = ?, Status = ? WHERE Room_id = ?",
                entity.getRoomNumber(),
                entity.getType(),
                entity.getRate(),
                entity.getStatus(),
                entity.getRoomId());
    }


    public Room search(String roomId) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Room WHERE Room_id = ?",roomId);
        if (resultSet.next()) {
            String roomID = resultSet.getString(1);
            String roomNumber = resultSet.getString(2);
            String type = resultSet.getString(3);
            String rate = resultSet.getString(4);
            String status = resultSet.getString(5);
            Room room = new Room(roomID, roomNumber, type, rate, status);

            return room;
        } else {
            return null;
        }
    }
    public  boolean delete(String roomId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Room WHERE Room_id = ?",roomId);
    }


    public  List<Room> getAll() throws SQLException {
        List<Room> list = new ArrayList<>();
        try {
            ResultSet rst = SQLUtil.execute("SELECT * FROM Room");
            while(rst.next()){
                list.add(new Room(rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5)));
            }
            return list;
        }catch (Exception e ){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return list;

    }

    public  List<String> getIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Room_id FROM Room");

        List<String> mtIDList = new ArrayList<>();
        while (resultSet.next()){
            mtIDList.add(resultSet.getString(1));
        }
        return mtIDList;
    }

    public  String getLastId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Room_id FROM Room ORDER BY Room_id DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }
}
