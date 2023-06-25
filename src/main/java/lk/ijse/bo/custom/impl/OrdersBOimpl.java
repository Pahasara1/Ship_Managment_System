package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrdersBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.OrderDetailsDAO;
import lk.ijse.dao.custom.OrdersDAO;
import lk.ijse.dao.custom.StockReportsDAO;
import lk.ijse.dto.BuyersDTO;
import lk.ijse.dto.OrdersDTO;
import lk.ijse.entity.Buyers;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;
import lk.ijse.entity.Stocks;
import lk.ijse.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersBOimpl implements OrdersBO {
    OrdersDAO ordersDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Orders);
    StockReportsDAO stockReportsDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Stocks);
    OrderDetailsDAO orderDetailsDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.OrderDetail);


    @Override
    public OrdersDTO getAllOrders(String id) throws SQLException, ClassNotFoundException {
        Orders orders = ordersDAO.getData(id);
        return new OrdersDTO(orders.getOrderId(),
                orders.getOrderCompany(),
                orders.getOrderCountry(),
                orders.getStockId(),
                orders.getAdminNic(),
                orders.getDate(),
                orders.getTime()
        );
    }

    @Override
    public boolean placeOrder(String orderId, String OrderQty, String orderCountry, String stockId, String nic, String orderdate, String orderTime) throws SQLException {
        Connection con = null;
        try {
            con = DBconnection.getInstance().getConnection();
            con.setAutoCommit(false);
            System.out.println(orderId);
            System.out.println(OrderQty);
            System.out.println(orderCountry);
            System.out.println(stockId);
            System.out.println(nic);
            System.out.println(orderdate);
            System.out.println(orderTime);

            boolean isSaved = ordersDAO.save(new Orders(orderId,  OrderQty ,orderCountry, stockId,nic, orderdate, orderTime));
            if(isSaved) {
                System.out.println("Orders");
                boolean isUpdated = stockReportsDAO.updateQty(new Stocks(OrderQty,stockId));
                if(isUpdated) {
                    System.out.println("Stock");
                    boolean isOrdered = orderDetailsDAO.save(new OrderDetail(orderId,  stockId,OrderQty) );
                    if(isOrdered) {
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    @Override
    public int getOrdersCount() throws SQLException {
        return ordersDAO.getOrdersCount();
    }


}
