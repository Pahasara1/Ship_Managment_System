package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.OrdersDTO;
import lk.ijse.dto.StockDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrdersBO extends SuperBO {

    OrdersDTO getAllOrders(String id) throws SQLException, ClassNotFoundException;
    public boolean placeOrder(String orderId, String OrderQty, String orderCountry, String stockId, String nic, String orderdate, String orderTime) throws SQLException;
    public  int getOrdersCount() throws SQLException;

    }
