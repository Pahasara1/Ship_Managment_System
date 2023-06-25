package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DBconnection;
import lk.ijse.entity.Stocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StockReportsDAO extends CrudDAO<Stocks,String> {

    public boolean updateQty(Stocks stocks) throws SQLException;

    public boolean updateQty(String orderDtoList, String stockId) throws SQLException;

    public  int getStockCount() throws SQLException;

}
