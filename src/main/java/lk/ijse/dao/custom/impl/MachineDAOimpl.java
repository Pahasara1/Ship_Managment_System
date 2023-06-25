package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.MachineDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.db.DBconnection;
import lk.ijse.entity.Machine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineDAOimpl implements MachineDAO {
    @Override
    public Machine getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM machines WHERE Machine_Id = ?",id);
        Machine machine = new Machine();
        if (set.next()) {
            machine.setMachineId(set.getString(1));
            machine.setMachineName(set.getString(2));
            machine.setQuality(set.getString(3));
            machine.setRepairCount(set.getString(4));
            machine.setDate(set.getString(5));
            machine.setTime(set.getString(6));

        }
        return machine;
    }

    @Override
    public boolean save(Machine dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO machines(Machine_Id,Machine_Name,Quality,Repair_Count,Repair_Date,Repair_Time) " +
                        "VALUES(?, ?, ?, ?, ?, ?)",
                dto.getMachineId(),
                dto.getMachineName(),
                dto.getQuality(),
                dto.getRepairCount(),
                dto.getDate(),
                dto.getTime());
    }

    @Override
    public boolean update(Machine dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE machines SET  Machine_Name= ? ,Quality= ?,Repair_Count = ?, Repair_Date = ?, Repair_Time =? WHERE Machine_Id = ?",
                dto.getMachineName(),
                dto.getQuality(),
                dto.getRepairCount(),
                dto.getDate(),
                dto.getTime(),
                dto.getMachineId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM machines WHERE Machine_Id=?";
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
    public Machine search(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM machines WHERE Machine_Id = ?",s);
        Machine machine= new Machine();
        if(set.next()) {

            machine.setMachineId(set.getString(1));
            machine.setMachineName(set.getString(2));
            machine.setQuality(set.getString(3));
            machine.setRepairCount(set.getString(4));
            machine.setDate(set.getString(5));
            machine.setTime(set.getString(6));

        }
        return machine;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Machine_Id FROM machines ORDER BY LENGTH(Machine_Id),Machine_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
}
