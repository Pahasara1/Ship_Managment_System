package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StocksBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MachineDAO;
import lk.ijse.dao.custom.StockReportsDAO;
import lk.ijse.db.DBconnection;
import lk.ijse.dto.MachineDTO;
import lk.ijse.dto.StockDTO;
import lk.ijse.entity.Buyers;
import lk.ijse.entity.Machine;
import lk.ijse.entity.Stocks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StocksBOimpl implements StocksBO {

    StockReportsDAO stockReportsDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Stocks);

    @Override
    public StockDTO getAllStocks(String id) throws SQLException, ClassNotFoundException {
        Stocks stocks = stockReportsDAO.getData(id);
        return new StockDTO(
                stocks.getStockId(),
                stocks.getEmployeeNic(),
                stocks.getSupplierNic(),
                stocks.getTime(),
                stocks.getDate(),
                stocks.getQuantity()

        );
    }

    @Override
    public boolean saveStocks(StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockReportsDAO.save( new Stocks(dto.getStockId(),dto.getEmployeeNic(),dto.getSupplierNic(),dto.getTime(),dto.getDate(),dto.getQuantity()));
    }

    @Override
    public boolean updateStocks(StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockReportsDAO.update( new Stocks(dto.getStockId(),dto.getEmployeeNic(),dto.getSupplierNic(),dto.getTime(),dto.getDate(),dto.getQuantity()));
    }

    @Override
    public boolean deleteStocks(String id) throws SQLException, ClassNotFoundException {
        return stockReportsDAO.delete(id);
    }

    @Override
    public StockDTO searchStocks(String id) throws SQLException, ClassNotFoundException {
        Stocks stocks = stockReportsDAO.search(id);
        return new StockDTO(stocks.getStockId(),
                stocks.getEmployeeNic(),
                stocks.getSupplierNic(),
                stocks.getTime(),
                stocks.getDate(),
                stocks.getQuantity()

        );
    }

    @Override
    public boolean updateQty(String orderDtoList, String stockId) throws SQLException, ClassNotFoundException {
        return stockReportsDAO.update( new Stocks(orderDtoList, stockId));
    }

    @Override
    public int getStockCount() throws SQLException {
        return stockReportsDAO.getStockCount();
    }
}
