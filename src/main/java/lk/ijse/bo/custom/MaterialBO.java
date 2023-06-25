package lk.ijse.bo.custom;

import lk.ijse.dto.MachineDTO;
import lk.ijse.dto.MaterialsDTO;

import java.sql.SQLException;

public interface MaterialBO {

    MaterialsDTO getAllMaterials(String id) throws SQLException, ClassNotFoundException;

    boolean saveMaterials(MaterialsDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateMaterials(MaterialsDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteMaterials(String id) throws SQLException, ClassNotFoundException;

    MaterialsDTO searchmaterials(String id) throws SQLException, ClassNotFoundException;

    public  int getMaterialCount() throws SQLException;


}
