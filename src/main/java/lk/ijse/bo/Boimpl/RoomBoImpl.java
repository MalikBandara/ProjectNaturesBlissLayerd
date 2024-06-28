package lk.ijse.bo.Boimpl;

import lk.ijse.bo.RoomBO;
import lk.ijse.dao.RoomDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;

import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBoImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);
    public  boolean saveRoom(RoomDTO roomDTO) throws SQLException {

        return roomDAO.Save(new Room(roomDTO.getRoomId(),
                roomDTO.getRoomNumber(),
                roomDTO.getType(),
                roomDTO.getRate(),
                roomDTO.getStatus()));

    }
    public  boolean updateRoomStatus(String roomId, String status) throws SQLException {

        return roomDAO.updateRoomStatus(roomId,status);
    }

    public  boolean updateRoom(RoomDTO roomDTO) throws SQLException {

        return roomDAO.update(new Room(roomDTO.getRoomId(),
                roomDTO.getRoomNumber(),
                roomDTO.getType(),
                roomDTO.getRate(),
                roomDTO.getStatus()));
    }


    public  RoomDTO searchRoomById(String roomId) throws SQLException {

        Room search = roomDAO.search(roomId);
        return new RoomDTO(search.getRoomId(),
                search.getRoomNumber(),
                search.getType(),
                search.getRate(),
                search.getStatus());
    }
    public  boolean deleteRoom(String roomId) throws SQLException {

        return  roomDAO.delete(roomId);
    }


    public List<RoomDTO> getAll() throws SQLException {

        List<RoomDTO> Roomlist = new ArrayList<>();
        List<Room> rooms = roomDAO.getAll();

        for (Room room : rooms){
            RoomDTO roomDTO = new RoomDTO(
                    room.getRoomId(),
                    room.getRoomNumber(),
                    room.getType(),
                    room.getRate(),
                    room.getStatus()
            );
            Roomlist.add(roomDTO);
        }
        return Roomlist;


    }

    public  List<String> getIds() throws SQLException {

        return roomDAO.getIds();

    }
    public  String getLastRoomId() throws SQLException {

        return roomDAO.getLastId();
    }

    @Override
    public int getRoomsCount() throws SQLException {
        int roomsCount = roomDAO.getRoomsCount();
        return roomsCount;
    }

}
