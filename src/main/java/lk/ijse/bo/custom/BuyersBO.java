package lk.ijse.bo.custom;

import lk.ijse.dto.AdminReportsDTO;
import lk.ijse.dto.BuyersDTO;

import java.sql.SQLException;

public interface BuyersBO {

    BuyersDTO getAllBuyers(String id) throws SQLException, ClassNotFoundException;

    boolean saveBuyers(BuyersDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateBuyers(BuyersDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteBuyers(String id) throws SQLException, ClassNotFoundException;

    BuyersDTO searchBuyers(String id) throws SQLException, ClassNotFoundException;

    public  int getBuyersCount() throws SQLException;

}
