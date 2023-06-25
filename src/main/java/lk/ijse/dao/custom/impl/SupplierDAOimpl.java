package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.db.DBconnection;
import lk.ijse.entity.Buyers;
import lk.ijse.entity.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOimpl implements SupplierDAO {
    @Override
    public Supplier getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM suppliers WHERE Supplier_Nic = ?",id);
        Supplier supplier = new Supplier();
        while (set.next()) {
            supplier.setSupplierId(set.getString(1));
            supplier.setSupplierName(set.getString(2));
            supplier.setItemId(set.getString(3));
            supplier.setItemName(set.getString(4));
            supplier.setDate(set.getString(5));
            supplier.setTime(set.getString(6));
        }
        return supplier;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO suppliers(Supplier_Nic,Supplier_Name,Item_Id,Item_Name,Supply_Date,Supply_Time) " +
                        "VALUES(?, ?, ?, ?, ?, ?)",
                dto.getSupplierId(),
                dto.getSupplierName(),
                dto.getItemId(),
                dto.getItemName(),
                dto.getDate(),
                dto.getTime());
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE suppliers SET  Supplier_Name = ?,Item_Id= ? ,Item_Name= ?,Supply_Date = ?, Supply_Time = ? WHERE Supplier_Nic = ?",
                dto.getSupplierId(),
                dto.getSupplierName(),
                dto.getItemId(),
                dto.getItemName(),
                dto.getDate(),
                dto.getTime());
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM suppliers WHERE Supplier_Nic=?";;
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, nic);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Supplier search(String nic) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM suppliers WHERE Supplier_Nic = ?", nic);
        Supplier supplier= new Supplier();
        if(set.next()) {

            supplier.setSupplierId(set.getString(1));
            supplier.setSupplierName(set.getString(2));
            supplier.setItemId(set.getString(3));
            supplier.setItemName(set.getString(4));
            supplier.setDate(set.getString(5));
            supplier.setTime(set.getString(6));

        }
        return supplier;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Supplier_Nic FROM suppliers ORDER BY LENGTH(Supplier_Nic),Supplier_Nic");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public int getSupplierCount() throws SQLException {
        String sql="SELECT COUNT(Supplier_Nic) FROM suppliers";

        try(PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt(1);
            }
        }
        return 0;
    }
}
