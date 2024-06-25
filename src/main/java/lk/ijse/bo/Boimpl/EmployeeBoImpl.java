package lk.ijse.bo.Boimpl;

import lk.ijse.bo.EmployeeBO;
import lk.ijse.dao.EmployeeDAO;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.EMPLOYEE);

    public  boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, SQLException {

        return  employeeDAO.Save(new Employee(employeeDTO.getEmployeeId(),
                employeeDTO.getName(),
                employeeDTO.getAddress(),
                employeeDTO.getSalary(),
                employeeDTO.getType(),
                employeeDTO.getAvailability(),
                employeeDTO.getRoomId()));
    }


    public  boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException {


        return  employeeDAO.update(new Employee(employeeDTO.getName(),
                employeeDTO.getAddress(),
                employeeDTO.getSalary(),
                employeeDTO.getType(),
                employeeDTO.getAvailability(),
                employeeDTO.getRoomId(),
                employeeDTO.getEmployeeId()));
    }

    public  boolean deleteEmployee(String employeeId) throws SQLException {

        return employeeDAO.delete(employeeId);
    }

    public  EmployeeDTO searchEmployeeById(String employeeId) throws SQLException {

        Employee search = employeeDAO.search(employeeId);
        return  new EmployeeDTO(search.getEmployeeId(),
                search.getName(),search.getAddress(),search.getSalary(),search.getType(),search.getAvailability(),
                search.getRoomId());
    }
    public  List<EmployeeDTO> getAllEmployees() throws SQLException {

        List<EmployeeDTO> employeeList = new ArrayList<>();

        List<Employee> employees = employeeDAO.getAll();
        for (Employee employee : employees){
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getSalary(),
                    employee.getType(),
                    employee.getAvailability(),
                    employee.getRoomId()
                    );
            employeeList.add(employeeDTO);
        }
        return employeeList;
    }

    public  List<String> getAllEmployeeIds() throws SQLException {

        return employeeDAO.getIds();
    }


    public  String getLastEmployeeId() throws SQLException {

        return employeeDAO.getLastId();
    }

    @Override
    public int getEmpCount() throws SQLException {
        int empCount = employeeDAO.getEmpCount();
        return empCount;

    }
}
