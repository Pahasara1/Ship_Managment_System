package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.UseMachineDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.db.DBconnection;
import lk.ijse.entity.Supplier;
import lk.ijse.entity.UseMachines;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UseMachineDAOimpl implements UseMachineDAO {
    @Override
    public UseMachines getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM usemachinesconformation WHERE Machine_Id = ?",id);
        UseMachines useMachines = new UseMachines();
        if (set.next()) {
            useMachines.setMachineId(set.getString(1));
            useMachines.setMaterialId(set.getString(2));
            useMachines.setEmployeeNic(set.getString(3));
            useMachines.setDate(set.getString(4));
            useMachines.setTime(set.getString(5));
        }
        return useMachines;
    }

    @Override
    public boolean save(UseMachines dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO usemachinesconformation(Machine_Id, Material_Id, Employee_Nic, Time, Date) " +
                        "VALUES(?, ?, ?, ?, ?)",
                dto.getMachineId(),
                dto.getMaterialId(),
                dto.getEmployeeNic(),
                dto.getTime(),
                dto.getDate());
    }

    @Override
    public boolean update(UseMachines dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE usemachinesconformation SET  Material_Id= ? ,Employee_Nic= ?,Time = ?, Date = ? WHERE Machine_Id = ?",
                dto.getMachineId(),
                dto.getMaterialId(),
                dto.getEmployeeNic(),
                dto.getTime(),
                dto.getDate());
    }

    @Override
    public boolean delete(String MachineId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM usemachinesconformation WHERE Machine_Id=?";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, MachineId);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public UseMachines search(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM usemachinesconformation WHERE Machine_Id = ?", id);
        UseMachines useMachines= new UseMachines();
        if(set.next()) {

            useMachines.setMachineId(set.getString(1));
            useMachines.setMaterialId(set.getString(2));
            useMachines.setEmployeeNic(set.getString(3));
            useMachines.setTime(set.getString(4));
            useMachines.setDate(set.getString(5));

        }
        return useMachines;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Machine_Id FROM usemachinesconformation ORDER BY LENGTH(Machine_Id),Machine_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public int getUseMachineCount() throws SQLException {
        String sql="SELECT COUNT(Machine_Id) FROM usemachinesconformation";

        try(PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt(1);
            }
        }
        return 0;
    }
}
