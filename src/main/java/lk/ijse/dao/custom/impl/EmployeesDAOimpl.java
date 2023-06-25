package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.EmployeesDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.entity.Employees;
import lk.ijse.db.DBconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesDAOimpl implements EmployeesDAO {

    @Override
    public Employees getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM employees WHERE Employee_Id = ?",id);
        Employees employees = new Employees();
        if (set.next()) {
            employees.setId(set.getString(1));
            employees.setName(set.getString(2));
            employees.setPossision(set.getString(3));
            employees.setNicNumber(set.getString(4));
            employees.setAddDate(set.getString(5));
            employees.setTime(set.getString(6));

        }
        return employees;
    }

    @Override
    public boolean save(Employees dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO employees(Employee_Id,Employee_Name,Employee_Possision,Employee_Nic,Employee_AddDate,Employee_AddTime) " +
                "VALUES(?, ?, ?, ?, ?, ?)",
                dto.getId(),
                dto.getName(),
                dto.getPossision(),
                dto.getNicNumber(),
                dto.getAddDate(),
                dto.getTime());
    }

    @Override
    public boolean update(Employees dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE employees SET  Employee_Name= ? ,Employee_Possision= ?,Employee_Nic = ?, Employee_AddDate = ?, Employee_AddTime =? WHERE Employee_Id = ?",
                dto.getId(),
                dto.getName(),
                dto.getPossision(),
                dto.getNicNumber(),
                dto.getAddDate(),
                dto.getTime());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM employees WHERE Employee_Id=?";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, s);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Employees search(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM daily_reports WHERE Report_Id = ?", s);
        Employees employees= new Employees();
        if(set.next()) {

            employees.setId(set.getString(1));
            employees.setName(set.getString(2));
            employees.setPossision(set.getString(3));
            employees.setNicNumber(set.getString(4));
            employees.setAddDate(set.getString(5));
            employees.setTime(set.getString(6));

        }
        return employees;
    }
@Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Employee_Id FROM employees ORDER BY LENGTH(Employee_Id),Employee_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public  int getEmployeeCount() throws SQLException {
        String sql="SELECT COUNT(Employee_Nic) FROM employees";

        try(PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt(1);
            }
        }
        return 0;
    }
}
