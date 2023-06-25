package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.UseMachines;

import java.sql.SQLException;

public interface UseMachineDAO extends CrudDAO<UseMachines, String> {
    public  int getUseMachineCount() throws SQLException;
}
