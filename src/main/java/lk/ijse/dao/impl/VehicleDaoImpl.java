package lk.ijse.dao.impl;

import lk.ijse.dao.VehicleDAO;
import lk.ijse.dao.custom.SQLUtil;

import lk.ijse.entity.Vehicle;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImpl implements VehicleDAO {
    public  boolean Save(Vehicle vehicle) throws SQLException {

        return  SQLUtil.execute("INSERT INTO Vehicle VALUES(?,?,?,?)",vehicle.getVehicleId(),
                vehicle.getNumberOfSeats(),
                vehicle.getStatus(),
                vehicle.getEmployeeId());


    }
    public  List<Vehicle> getAll() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM Vehicle");
        List<Vehicle> list = new ArrayList<>();
        while(rst.next()){
            list.add(new Vehicle(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4)));
        }
        return list;

    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    public  boolean update(Vehicle vehicle) throws SQLException {

        return  SQLUtil.execute("UPDATE Vehicle SET Status = ?, No_of_seats = ?, Employee_id = ? WHERE Vehicle_id = ?",
                vehicle.getStatus(),
                vehicle.getNumberOfSeats(),
                vehicle.getEmployeeId(),
                vehicle.getVehicleId());
    }

    public  boolean delete(String vehicleId) throws SQLException {

        return  SQLUtil.execute("DELETE FROM Vehicle WHERE Vehicle_id = ?",vehicleId);
    }

    public  Vehicle search(String vehicleId) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Vehicle WHERE Vehicle_id = ?",vehicleId);
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String status = resultSet.getString(2);
            String numberOfSeats = resultSet.getString(3);
            String employeeId = resultSet.getString(4);


            Vehicle vehicle = new Vehicle(id, numberOfSeats, status, employeeId);

            return vehicle;
        } else {
            return null;
        }
    }

    public  String getLastId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Vehicle_id FROM Vehicle ORDER BY Vehicle_id DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }
}
