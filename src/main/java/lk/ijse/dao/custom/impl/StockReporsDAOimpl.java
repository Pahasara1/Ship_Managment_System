package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.StockReportsDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.entity.Stocks;
import lk.ijse.db.DBconnection;
import lk.ijse.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockReporsDAOimpl implements StockReportsDAO {
    public  boolean updateQty(Stocks stocks) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        String sql = "UPDATE stocks SET Quantity = (Quantity - ?) WHERE Stock_Id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, stocks.getQuantity());
        pstm.setString(2, stocks.getStockId());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public int getStockCount() throws SQLException {
        String sql = "SELECT sum(Quantity) FROM stocks";

        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    public  boolean updateQty(String orderDtoList, String stockId) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        String sql = "UPDATE stocks SET Quantity = (Quantity - ?) WHERE Stock_Id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, orderDtoList);
        pstm.setString(2,stockId);

        return pstm.executeUpdate() > 0;
    }

    @Override
    public Stocks getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM stocks WHERE Stock_Id = ?",id);
        Stocks stocks = new Stocks();
        while (set.next()) {
            stocks.setStockId(set.getString(1));
            stocks.setEmployeeNic(set.getString(2));
            stocks.setSupplierNic(set.getString(3));
            stocks.setTime(set.getString(4));
            stocks.setDate(set.getString(5));
            stocks.setQuantity(set.getString(6));
        }
        return stocks;
    }

    @Override
    public boolean save(Stocks dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO stocks(Stock_Id,Employee_Nic,Supplier_Nic,Time,Date,Quantity) " +
                        "VALUES(?, ?, ?, ?, ?, ?)",
                dto.getStockId(),
                dto.getEmployeeNic(),
                dto.getSupplierNic(),
                dto.getTime(),
                dto.getDate(),
                dto.getQuantity());
    }

    @Override
    public boolean update(Stocks dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE stocks SET  Employee_Nic = ?,Supplier_Nic= ? ,Time= ?,Date = ?,Quantity = ? WHERE Stock_Id = ?",
                dto.getEmployeeNic(),
                dto.getSupplierNic(),
                dto.getTime(),
                dto.getDate(),
                dto.getQuantity(),
                dto.getStockId());
    }

    @Override
    public boolean delete(String StockId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM stocks WHERE Stock_Id=?";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, StockId);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Stocks search(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM stocks WHERE Stock_Id = ?", id);
        Stocks stocks= new Stocks();
        if(set.next()) {

            stocks.setStockId(set.getString(1));
            stocks.setEmployeeNic(set.getString(2));
            stocks.setSupplierNic(set.getString(3));
            stocks.setTime(set.getString(4));
            stocks.setDate(set.getString(5));
            stocks.setQuantity(set.getString(6));

        }
        return stocks;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Stock_Id FROM stocks ORDER BY LENGTH(Stock_Id),Stock_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
}
