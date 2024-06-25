package lk.ijse.dao.impl;

import lk.ijse.dao.EmployeeDAO;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {

    public  boolean Save(Employee employee) throws SQLException, SQLException {

        return  SQLUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)",
                employee.getEmployeeId(),employee.getName(),employee.getAddress(),employee.getSalary(),
                employee.getType(),employee.getAvailability(),employee.getRoomId(),"1234567890");
    }


    public  boolean update(Employee employee) throws SQLException {

        return  SQLUtil.execute("UPDATE Employee SET Name = ?, Address = ?, Salary = ?, type = ?, availability = ?, Room_id = ? WHERE Employee_id = ?",
                employee.getName(),
                employee.getAddress(),
                employee.getSalary(),
                employee.getType(),
                employee.getAvailability(),
                employee.getRoomId(),
                employee.getEmployeeId());

    }

    public  boolean delete(String employeeId) throws SQLException {

        return SQLUtil.execute("DELETE FROM Employee WHERE Employee_id = ?",employeeId);
    }

    public  Employee search(String employeeId) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee WHERE Employee_id = ?",employeeId);
        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empAddress = resultSet.getString(3);
            double empSalary = resultSet.getDouble(4);
            String empType = resultSet.getString(5);
            String empAvailability = resultSet.getString(6);
            String rooID = resultSet.getString(7);


            Employee employee = new Employee(empId, empName, empAddress, empSalary, empType, empAvailability, rooID);

            return employee;
        } else {
            return null;
        }
    }
    public  List<Employee> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee");

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            String employeeName = resultSet.getString(2);
            String employeeAddress = resultSet.getString(3);
            double employeeSalary = resultSet.getDouble(4);
            String employeeType = resultSet.getString(5);
            String employeeAvailability = resultSet.getString(6);
            String rooID = resultSet.getString(7);


            Employee employee = new Employee(employeeId, employeeName, employeeAddress, employeeSalary, employeeType, employeeAvailability, rooID);
            employeeList.add(employee);
        }
        return employeeList;
    }

    public  List<String> getIds() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Employee_id FROM Employee");

        List<String> employeeIdList = new ArrayList<>();
        while (resultSet.next()){
            employeeIdList.add(resultSet.getString(1));
        }
        return employeeIdList;
    }


    public  String getLastId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Employee_id FROM Employee ORDER BY Employee_id DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }

    @Override
    public int getEmpCount() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS Emp_count FROM Employee");

        if(resultSet.next()) {
            return resultSet.getInt("Emp_count");
        }
        return 0;
    }
}
