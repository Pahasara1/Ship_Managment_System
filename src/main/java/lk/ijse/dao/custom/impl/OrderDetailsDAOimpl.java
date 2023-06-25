package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.OrderDetailsDAO;
import lk.ijse.entity.OrderDetail;
import lk.ijse.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOimpl implements OrderDetailsDAO {

    @Override
    public OrderDetail getData(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean save(OrderDetail orderDetail) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();
        String sql = "INSERT INTO orderdetails(Order_Id, Stock_Id, Qty)" +
                "VALUES(?, ?, ?)";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, orderDetail.getOrderId());
        pstm.setString(2, orderDetail.getStockId());
        pstm.setString(3, orderDetail.getQty());

        return pstm.executeUpdate() > 0;

    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
