package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDAO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {

    int getEmpCount() throws SQLException;
}
