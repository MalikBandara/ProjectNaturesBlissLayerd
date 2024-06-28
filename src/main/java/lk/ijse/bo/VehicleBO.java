package lk.ijse.bo;

import lk.ijse.bo.custom.SuPerBO;
import lk.ijse.dto.VehicleDTO;


import java.sql.SQLException;
import java.util.List;

public interface VehicleBO  extends SuPerBO {
    public  boolean saveVehicle(VehicleDTO vehicle) throws SQLException ;
    public  List<VehicleDTO> getAllVehicles() throws SQLException ;
    public  boolean updateVehicle(VehicleDTO vehicle) throws SQLException ;

    public  boolean deleteVehicle(String vehicleId) throws SQLException ;

    public  VehicleDTO searchVehicleById(String vehicleId) throws SQLException;

    public  String getLastVehicleId() throws SQLException ;

    int getVehicleCount() throws SQLException;
}
