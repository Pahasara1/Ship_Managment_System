package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MachineBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.DailyReportsDAO;
import lk.ijse.dao.custom.MachineDAO;
import lk.ijse.dto.DailyReportsDTO;
import lk.ijse.dto.EmployeesDTO;
import lk.ijse.dto.MachineDTO;
import lk.ijse.entity.DailyReports;
import lk.ijse.entity.Employees;
import lk.ijse.entity.Machine;

import java.sql.SQLException;

public class MachineBOimpl implements MachineBO {

    MachineDAO machineDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Machine);

    @Override
    public MachineDTO getAllMachines(String id) throws SQLException, ClassNotFoundException {
        Machine machine = machineDAO.getData(id);
        return new MachineDTO(machine.getMachineId(),
                machine.getMachineName(),
                machine.getQuality(),
                machine.getRepairCount(),
                machine.getDate(),
                machine.getTime()
        );
    }

    @Override
    public boolean saveMachines(MachineDTO dto) throws SQLException, ClassNotFoundException {
        return machineDAO.save( new Machine(dto.getMachineId(),dto.getMachineName(),dto.getQuality(),String.valueOf(dto.getRepairCount()),dto.getDate(),dto.getTime()));
    }

    @Override
    public boolean updateMachines(MachineDTO dto) throws SQLException, ClassNotFoundException {
        return machineDAO.update( new Machine(dto.getMachineId(),dto.getMachineName(),dto.getQuality(),String.valueOf(dto.getRepairCount()),dto.getDate(),dto.getTime()));
    }

    @Override
    public boolean deleteMachines(String id) throws SQLException, ClassNotFoundException {
        return machineDAO.delete(id);
    }

    @Override
    public MachineDTO searchMachines(String id) throws SQLException, ClassNotFoundException {
        Machine machine = machineDAO.search(id);
        return new MachineDTO(machine.getMachineId(),
                machine.getMachineName(),
                machine.getQuality(),
                machine.getRepairCount(),
                machine.getDate(),
                machine.getTime());
    }
}
