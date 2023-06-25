package lk.ijse.bo.custom;

import lk.ijse.dto.DailyReportsDTO;
import lk.ijse.dto.EmployeesDTO;

import java.sql.SQLException;

public interface EmployeesBO {
    EmployeesDTO getAllEmployees(String id) throws SQLException, ClassNotFoundException;

    boolean saveEmployees(EmployeesDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateEmployees(EmployeesDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteEmployees(String id) throws SQLException, ClassNotFoundException;

    EmployeesDTO searchEmployees(String id) throws SQLException, ClassNotFoundException;

    public  int getEmployeeCount() throws SQLException;
}
