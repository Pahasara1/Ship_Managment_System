package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.OrdersDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.entity.Buyers;
import lk.ijse.entity.Orders;
import lk.ijse.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOimpl implements OrdersDAO {
    @Override
    public Orders getData(String Order_Id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM orders WHERE Order_Id = ?", Order_Id);
        Orders orders= new Orders();
        if(set.next()) {

            orders.setOrderId(set.getString(1));
            orders.setOrderCompany((set.getString(2)));;
            orders.setOrderCountry(set.getString(3));
            orders.setStockId(set.getString(4));
            orders.setAdminNic(set.getString(5));
            orders.setDate(set.getString(6));
            orders.setTime(set.getString(7));

        }
        return orders;
    }

    public boolean save(Orders orders) throws SQLException {
        Connection con = DBconnection.getInstance().getConnection();

        String sql = "INSERT INTO orders(Order_Id, Order_Company, Order_Country, Stock_Id, Admin_Nic, Order_Date,Order_Time)" +
                " VALUES(?, ?, ?, ?, ? , ?,  ?)";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, orders.getOrderId());
        pstm.setString(2, orders.getOrderCompany());
        pstm.setString(3, orders.getOrderCountry());
        pstm.setString(4, orders.getStockId());
        pstm.setString(5, orders.getAdminNic());
        pstm.setString(6, orders.getDate());
        pstm.setString(7, orders.getTime());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Orders dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE orders SET Order_Company= ?, Order_Country = ?, Stock_Id = ?,Admin_Nic = ?,Order_Date = ?, Order_Time = ? WHERE Order_Id = ?",
                dto.getOrderCompany(),
                dto.getOrderCountry(),
                dto.getStockId(),
                dto.getAdminNic(),
                dto.getDate(),
                dto.getTime(),
                dto.getOrderId());
    }

    @Override
    public boolean delete(String Order_Id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM orders WHERE Order_Id=?";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, Order_Id);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Orders search(String Order_Id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM orders WHERE Order_Id = ?", Order_Id);
        Orders orders= new Orders();
        if(set.next()) {

            orders.setOrderId(set.getString(1));
            orders.setOrderCompany((set.getString(2)));;
            orders.setOrderCountry(set.getString(3));
            orders.setStockId(set.getString(4));
            orders.setAdminNic(set.getString(5));
            orders.setDate(set.getString(6));
            orders.setTime(set.getString(7));

        }
        return orders;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT Order_Id FROM orders ORDER BY LENGTH(Order_Id),Order_Id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public  int getOrdersCount() throws SQLException {
        String sql = "SELECT COUNT(Order_Id) FROM orders";

        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }


}
