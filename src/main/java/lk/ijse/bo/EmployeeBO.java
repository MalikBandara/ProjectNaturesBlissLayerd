package lk.ijse.bo;

import lk.ijse.bo.custom.SuPerBO;
import lk.ijse.dto.EmployeeDTO;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuPerBO {
    public  boolean saveEmployee(EmployeeDTO employee) throws SQLException, SQLException ;
    public  boolean updateEmployee(EmployeeDTO employee) throws SQLException ;
    public  boolean deleteEmployee(String employeeId) throws SQLException ;
    public  EmployeeDTO searchEmployeeById(String employeeId) throws SQLException ;
    public List<EmployeeDTO> getAllEmployees() throws SQLException ;
    public  List<String> getAllEmployeeIds() throws SQLException ;
    public  String getLastEmployeeId() throws SQLException ;
    public int getEmpCount() throws SQLException;
}
