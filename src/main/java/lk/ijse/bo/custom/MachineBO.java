package lk.ijse.bo.custom;

import lk.ijse.dto.EmployeesDTO;
import lk.ijse.dto.MachineDTO;

import java.sql.SQLException;

public interface MachineBO {
    MachineDTO getAllMachines(String id) throws SQLException, ClassNotFoundException;

    boolean saveMachines(MachineDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateMachines(MachineDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteMachines(String id) throws SQLException, ClassNotFoundException;

    MachineDTO searchMachines(String id) throws SQLException, ClassNotFoundException;
}
