package lk.ijse.bo.custom;

import lk.ijse.db.DBconnection;
import lk.ijse.dto.MachineDTO;
import lk.ijse.dto.StockDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StocksBO {
    StockDTO getAllStocks(String id) throws SQLException, ClassNotFoundException;

    boolean saveStocks(StockDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateStocks(StockDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteStocks(String id) throws SQLException, ClassNotFoundException;

    StockDTO searchStocks(String id) throws SQLException, ClassNotFoundException;

    public  boolean updateQty(String orderDtoList, String stockId) throws SQLException, ClassNotFoundException;

    public  int getStockCount() throws SQLException;
}
