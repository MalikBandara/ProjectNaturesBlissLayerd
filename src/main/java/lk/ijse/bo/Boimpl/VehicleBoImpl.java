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
        /*
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Vehicle VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, vehicle.getVehicleId());
        pstm.setString(2, vehicle.getNumberOfSeats());
        pstm.setString(3, vehicle.getStatus());
        pstm.setString(4, vehicle.getEmployeeId());

        if(pstm.executeUpdate()>0){
            return true;
        }else{
            return false;
        }

         */
        return  vehicleDAO.Save(new Vehicle(vehicle.getVehicleId(),
                vehicle.getNumberOfSeats(),
                vehicle.getStatus(),
                vehicle.getEmployeeId()));

    }

    public  List<VehicleDTO> getAllVehicles() throws SQLException {

        /*
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Vehicle";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();

        List<VehicleDTO> list = new ArrayList<>();
        while(rst.next()){
            list.add(new VehicleDTO(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4)));
        }
        return list;

         */
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
        /*
        String sql = "UPDATE Vehicle SET Status = ?, No_of_seats = ?, Employee_id = ? WHERE Vehicle_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, vehicle.getStatus());
        pstm.setString(2, vehicle.getNumberOfSeats());
        pstm.setString(3, vehicle.getEmployeeId());
        pstm.setString(4, vehicle.getVehicleId());

        return pstm.executeUpdate() > 0;

         */
        return  vehicleDAO.update(new Vehicle(vehicle.getVehicleId(),
                vehicle.getNumberOfSeats(),
                vehicle.getStatus(),
                vehicle.getEmployeeId()));
    }

    public  boolean deleteVehicle(String vehicleId) throws SQLException {
        /*
        String sql = "DELETE FROM Vehicle WHERE Vehicle_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, vehicleId);

        return pstm.executeUpdate() > 0;

         */
        return vehicleDAO.delete(vehicleId);
    }

    public  VehicleDTO searchVehicleById(String vehicleId) throws SQLException {
        /*
        String sql = "SELECT * FROM Vehicle WHERE Vehicle_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, vehicleId); // Use setString instead of setObject for String parameters

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String id = resultSet.getString(1); // Assuming the column index for vehicle ID is 1
            String status = resultSet.getString(2); // Assuming the column index for status is 2
            String numberOfSeats = resultSet.getString(3); // Assuming the column index for number of seats is 3
            String employeeId = resultSet.getString(4); // Assuming the column index for employee ID is 4

            // Use the retrieved values to create a new Vehicle object
            VehicleDTO vehicle = new VehicleDTO(id, numberOfSeats, status, employeeId);

            return vehicle;
        } else {
            return null;
        }

         */
        Vehicle search = vehicleDAO.search(vehicleId);
        return  new VehicleDTO(search.getVehicleId(),
                search.getNumberOfSeats(),
                search.getStatus(),
                search.getEmployeeId());

    }

    public  String getLastVehicleId() throws SQLException {
        /*

        String sql = "SELECT Vehicle_id FROM Vehicle ORDER BY Vehicle_id DESC LIMIT 1";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }

         */
        return  vehicleDAO.getLastId();
    }
}
