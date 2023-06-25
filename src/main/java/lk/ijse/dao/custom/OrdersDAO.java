package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders,String> {
    public boolean save(Orders orders) throws SQLException;
    public  int getOrdersCount() throws SQLException;
}
