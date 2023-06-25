package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MaterialBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MachineDAO;
import lk.ijse.dao.custom.MaterialDAO;
import lk.ijse.db.DBconnection;
import lk.ijse.dto.MachineDTO;
import lk.ijse.dto.MaterialsDTO;
import lk.ijse.entity.Machine;
import lk.ijse.entity.Material;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialBOimpl implements MaterialBO {

    MaterialDAO materialDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Materials);

    @Override
    public MaterialsDTO getAllMaterials(String id) throws SQLException, ClassNotFoundException {
        Material material = materialDAO.getData(id);
        return new MaterialsDTO(material.getMaterialId(),
                material.getMaterialname(),
                material.getSupplyCountry(),
                material.getQuality(),
                material.getSupplyDate(),
                material.getSupplyTime()
        );
    }

    @Override
    public boolean saveMaterials(MaterialsDTO dto) throws SQLException, ClassNotFoundException {
        return materialDAO.save( new Material(dto.getMaterialId(),dto.getMaterialname(),dto.getSupplyCountry(),dto.getQuality(),dto.getSupplyDate(),dto.getSupplyTime()));
    }

    @Override
    public boolean updateMaterials(MaterialsDTO dto) throws SQLException, ClassNotFoundException {
        return materialDAO.update( new Material(dto.getMaterialId(),dto.getMaterialname(),dto.getSupplyCountry(),dto.getQuality(),dto.getSupplyDate(),dto.getSupplyTime()));
    }

    @Override
    public boolean deleteMaterials(String id) throws SQLException, ClassNotFoundException {
        return materialDAO.delete(id);
    }

    @Override
    public MaterialsDTO searchmaterials(String id) throws SQLException, ClassNotFoundException {
        Material material = materialDAO.search(id);
        return new MaterialsDTO(material.getMaterialId(),
                material.getMaterialname(),
                material.getSupplyCountry(),
                material.getQuality(),
                material.getSupplyDate(),
                material.getSupplyTime());
    }

    @Override
    public int getMaterialCount() throws SQLException {
        return materialDAO.getMaterialCount();
    }

}
