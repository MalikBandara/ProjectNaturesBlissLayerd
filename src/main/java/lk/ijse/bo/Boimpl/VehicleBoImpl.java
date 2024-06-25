package lk.ijse.bo.Boimpl;

import lk.ijse.bo.VehicleBO;
import lk.ijse.dao.VehicleDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.VehicleDTO;
import lk.ijse.entity.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBoImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.VEHICLE);
    public  boolean saveVehicle(VehicleDTO vehicle) throws SQLException {

        return  vehicleDAO.Save(new Vehicle(vehicle.getVehicleId(),
                vehicle.getNumberOfSeats(),
                vehicle.getStatus(),
                vehicle.getEmployeeId()));

    }

    public  List<VehicleDTO> getAllVehicles() throws SQLException {

        List<VehicleDTO> Vehiclelist = new ArrayList<>();
        List<Vehicle> vehicles = vehicleDAO.getAll();
        for (Vehicle vehicle : vehicles){
            VehicleDTO vehicleDTO = new VehicleDTO(vehicle.getVehicleId(),
                    vehicle.getNumberOfSeats(),
                    vehicle.getStatus(),
                    vehicle.getEmployeeId());
            Vehiclelist.add(vehicleDTO);
        }
        return Vehiclelist;

    }
    public  boolean updateVehicle(VehicleDTO vehicle) throws SQLException {

        return  vehicleDAO.update(new Vehicle(vehicle.getVehicleId(),
                vehicle.getNumberOfSeats(),
                vehicle.getStatus(),
                vehicle.getEmployeeId()));
    }

    public  boolean deleteVehicle(String vehicleId) throws SQLException {

        return vehicleDAO.delete(vehicleId);
    }

    public  VehicleDTO searchVehicleById(String vehicleId) throws SQLException {

        Vehicle search = vehicleDAO.search(vehicleId);
        return  new VehicleDTO(search.getVehicleId(),
                search.getNumberOfSeats(),
                search.getStatus(),
                search.getEmployeeId());

    }

    public  String getLastVehicleId() throws SQLException {

        return  vehicleDAO.getLastId();
    }

    @Override
    public int getVehicleCount() throws SQLException {
        return vehicleDAO.getVehicleCount();
    }
}
