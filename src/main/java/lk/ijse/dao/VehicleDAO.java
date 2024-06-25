package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDAO;

import lk.ijse.entity.Vehicle;

import java.sql.SQLException;


public interface VehicleDAO extends CrudDAO<Vehicle> {


    int getVehicleCount() throws SQLException;
}
