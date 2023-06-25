package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Employees;

import java.sql.SQLException;

public interface EmployeesDAO extends CrudDAO<Employees,String> {
    public  int getEmployeeCount() throws SQLException ;
}
