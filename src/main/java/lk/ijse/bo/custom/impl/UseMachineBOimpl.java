package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UseMachineBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dao.custom.UseMachineDAO;
import lk.ijse.db.DBconnection;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.dto.UsingMachinesDTO;
import lk.ijse.entity.Supplier;
import lk.ijse.entity.UseMachines;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UseMachineBOimpl implements UseMachineBO {

    UseMachineDAO useMachineDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.UseMachines);

    @Override
    public UsingMachinesDTO getAllMachines(String id) throws SQLException, ClassNotFoundException {
        UseMachines useMachines = useMachineDAO.getData(id);
        return new UsingMachinesDTO(useMachines.getMachineId(),
                useMachines.getMaterialId(),
                useMachines.getEmployeeNic(),
                useMachines.getDate(),
                useMachines.getTime()
        );
    }

    @Override
    public boolean saveUsemachines(UsingMachinesDTO dto) throws SQLException, ClassNotFoundException {
        return useMachineDAO.save( new UseMachines(dto.getMachineId(),dto.getMaterialId(),dto.getEmployeeNic(),dto.getTime(),dto.getDate()));
    }

    @Override
    public boolean updateUsemachines(UsingMachinesDTO dto) throws SQLException, ClassNotFoundException {
        return useMachineDAO.update( new UseMachines(dto.getMachineId(),dto.getMaterialId(),dto.getEmployeeNic(),dto.getTime(),dto.getDate()));
    }

    @Override
    public boolean deleteUseMachines(String id) throws SQLException, ClassNotFoundException {
        return useMachineDAO.delete(id);
    }

    @Override
    public UsingMachinesDTO searchUseMachines(String id) throws SQLException, ClassNotFoundException {
        UseMachines um = useMachineDAO.search(id);
        return new UsingMachinesDTO(um.getMachineId(),
                um.getMaterialId(),
                um.getEmployeeNic(),
                um.getDate(),
                um.getTime());
    }

    @Override
    public int getMachineCount() throws SQLException {
        return useMachineDAO.getUseMachineCount();
    }
}
