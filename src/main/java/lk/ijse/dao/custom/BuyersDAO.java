package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Buyers;

import java.sql.SQLException;

public interface BuyersDAO extends CrudDAO<Buyers, String> {
    public  int getBuyersCount() throws SQLException;
}
