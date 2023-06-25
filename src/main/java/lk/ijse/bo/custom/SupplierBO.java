package lk.ijse.bo.custom;

import lk.ijse.dto.BuyersDTO;
import lk.ijse.dto.SupplierDTO;

import java.sql.SQLException;

public interface SupplierBO {

    SupplierDTO getAllBuyers(String id) throws SQLException, ClassNotFoundException;

    boolean saveSuppliers(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateSuppliers(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException;

    SupplierDTO searchSuppliers(String id) throws SQLException, ClassNotFoundException;

    public  int getSupplierCount() throws SQLException;

}
