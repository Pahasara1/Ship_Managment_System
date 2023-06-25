package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.MaterialDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.db.DBconnection;
import lk.ijse.entity.Machine;
import lk.ijse.entity.Material;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialDAOimpl implements MaterialDAO {
    @Override
    public Material getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM materials WHERE Material_Id = ?",id);
        Material material = new Material();
        if (set.next()) {
            material.setMaterialId(set.getString(1));
            material.setMaterialname(set.getString(2));
            material.setSupplyCountry(set.getString(3));
            material.setQuality(set.getString(4));
            material.setSupplyDate(set.getString(5));
            material.setSupplyTime(set.getString(6));

        }
        return material;
    }

    @Override
    public boolean save(Material dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO materials(Material_Id,Material_Name,Supplier_Country,Quality,Supply_Date,Supply_Time) " +
                        "VALUES(?, ?, ?, ?, ?, ?)",
                dto.getMaterialId(),
                dto.getMaterialname(),
                dto.getSupplyCountry(),
                dto.getQuality(),
                dto.getSupplyDate(),
                dto.getSupplyTime());
    }

    @Override
    public boolean update(Material dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE materials SET  Material_Name= ? ,Supplier_Country= ?,Quality = ?, Supply_Date = ?, Supply_Time =? WHERE Material_Id = ?",
                dto.getMaterialId(),
                dto.getMaterialname(),
                dto.getSupplyCountry(),
                dto.getQuality(),
                dto.getSupplyDate(),
                dto.getSupplyTime());
    }

    @Override
    public boolean delete(String MaterialId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM materials WHERE Material_Id=?";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, MaterialId);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Material search(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM materials WHERE Material_Id = ?",s);
        Material material= new Material();
        if(set.next()) {

            material.setMaterialId(set.getString(1));
            material.setMaterialname(set.getString(2));
            material.setSupplyCountry(set.getString(3));
            material.setQuality(set.getString(4));
            material.setSupplyDate(set.getString(5));
            material.setSupplyTime(set.getString(6));

        }
        return material;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Material_Id FROM materials ORDER BY LENGTH(Material_Id),Material_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public int getMaterialCount() throws SQLException {
        String sql="SELECT COUNT(Material_Id) FROM materials";

        try(PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt(1);
            }
        }
        return 0;
    }
}
