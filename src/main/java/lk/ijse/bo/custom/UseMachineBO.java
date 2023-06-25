package lk.ijse.bo.custom;

import lk.ijse.dto.SupplierDTO;
import lk.ijse.dto.UsingMachinesDTO;

import java.sql.SQLException;

public interface UseMachineBO {

    UsingMachinesDTO getAllMachines(String id) throws SQLException, ClassNotFoundException;

    boolean saveUsemachines(UsingMachinesDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateUsemachines(UsingMachinesDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteUseMachines(String id) throws SQLException, ClassNotFoundException;

    UsingMachinesDTO searchUseMachines(String id) throws SQLException, ClassNotFoundException;

    public  int getMachineCount() throws SQLException;
}
