package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BuyersDAO;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.db.DBconnection;
import lk.ijse.dto.BuyersDTO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Buyers;
import lk.ijse.entity.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierBOimpl implements SupplierBO {

    SupplierDAO supplierDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Suppliers);


    @Override
    public SupplierDTO getAllBuyers(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.getData(id);
        return new SupplierDTO(supplier.getSupplierId(),
                supplier.getSupplierName(),
                supplier.getItemId(),
                supplier.getItemName(),
                supplier.getDate(),
                supplier.getTime()
        );
    }

    @Override
    public boolean saveSuppliers(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save( new Supplier(dto.getSupplierId(),dto.getSupplierName(),dto.getItemId(),dto.getItemName(),dto.getDate(),dto.getTime()));
    }

    @Override
    public boolean updateSuppliers(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update( new Supplier(dto.getSupplierId(),dto.getSupplierName(),dto.getItemId(),dto.getItemName(),dto.getDate(),dto.getTime()));
    }

    @Override
    public boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDTO searchSuppliers(String id) throws SQLException, ClassNotFoundException {
        Supplier sp = supplierDAO.search(id);
        return new SupplierDTO(sp.getSupplierId(),
                sp.getSupplierName(),
                sp.getItemId(),
                sp.getItemName(),
                sp.getDate(),
                sp.getTime());
    }

    @Override
    public int getSupplierCount() throws SQLException {
        return supplierDAO.getSupplierCount();
    }
}
