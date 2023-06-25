package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployeesBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.DailyReportsDAO;
import lk.ijse.dao.custom.EmployeesDAO;
import lk.ijse.db.DBconnection;
import lk.ijse.dto.DailyReportsDTO;
import lk.ijse.dto.EmployeesDTO;
import lk.ijse.entity.DailyReports;
import lk.ijse.entity.Employees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeesBOimpl implements EmployeesBO {

    EmployeesDAO employeesDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Employees);

    @Override
    public EmployeesDTO getAllEmployees(String id) throws SQLException, ClassNotFoundException {
        Employees employees = employeesDAO.getData(id);
        return new EmployeesDTO(employees.getId(),
                employees.getName(),
                employees.getPossision(),
                employees.getNicNumber(),
                employees.getAddDate(),
                employees.getTime()
        );
    }

    @Override
    public boolean saveEmployees(EmployeesDTO dto) throws SQLException, ClassNotFoundException {
        return employeesDAO.save( new Employees(dto.getId(),dto.getName(),dto.getPossision(),dto.getNicNumber(),dto.getAddDate(),dto.getTime()));
    }

    @Override
    public boolean updateEmployees(EmployeesDTO dto) throws SQLException, ClassNotFoundException {
        return employeesDAO.update( new Employees(dto.getId(),dto.getName(),dto.getPossision(),dto.getNicNumber(),dto.getAddDate(),dto.getTime()));
    }

    @Override
    public boolean deleteEmployees(String id) throws SQLException, ClassNotFoundException {
        return employeesDAO.delete(id);
    }

    @Override
    public EmployeesDTO searchEmployees(String id) throws SQLException, ClassNotFoundException {
        Employees em = employeesDAO.search(id);
        return new EmployeesDTO(em.getId(),
                em.getName(),
                em.getPossision(),
                em.getNicNumber(),
                em.getAddDate(),
                em.getTime());
    }

    @Override
    public int getEmployeeCount() throws SQLException {
        return employeesDAO.getEmployeeCount();
    }
}
