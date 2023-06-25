package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Material;

import java.sql.SQLException;

public interface MaterialDAO extends CrudDAO<Material, String> {
    public  int getMaterialCount() throws SQLException;
}
