package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BuyersBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminReportsDAO;
import lk.ijse.dao.custom.BuyersDAO;
import lk.ijse.db.DBconnection;
import lk.ijse.dto.AdminReportsDTO;
import lk.ijse.dto.BuyersDTO;
import lk.ijse.entity.AdminReports;
import lk.ijse.entity.Buyers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyersBOimpl implements BuyersBO {

    BuyersDAO buyersDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Buyers);

    @Override
    public BuyersDTO getAllBuyers(String id) throws SQLException, ClassNotFoundException {
        Buyers buyers = buyersDAO.getData(id);
        return new BuyersDTO(buyers.getBuyerId(),
                buyers.getBuyerName(),
                buyers.getCountry(),
                buyers.getBuyerItem(),
                buyers.getBuydate(),
                buyers.getTime()
                );
    }

    @Override
    public boolean saveBuyers(BuyersDTO dto) throws SQLException, ClassNotFoundException {
        return buyersDAO.save( new Buyers(dto.getBuyerId(),dto.getBuyerName(),dto.getCountry(),dto.getBuyerItem(),dto.getBuydate(),dto.getTime()));
    }

    @Override
    public boolean updateBuyers(BuyersDTO dto) throws SQLException, ClassNotFoundException {
        return buyersDAO.update( new Buyers(dto.getBuyerId(),dto.getBuyerName(),dto.getCountry(),dto.getBuyerItem(),dto.getBuydate(),dto.getTime()));
    }

    @Override
    public boolean deleteBuyers(String id) throws SQLException, ClassNotFoundException {
        return buyersDAO.delete(id);
    }

    @Override
    public BuyersDTO searchBuyers(String id) throws SQLException, ClassNotFoundException {
        Buyers bu = buyersDAO.search(id);
        return new BuyersDTO(bu.getBuyerId(),
                bu.getBuyerName(),
                bu.getCountry(),
                bu.getBuyerItem(),
                bu.getBuydate(),
                bu.getTime());
    }

    @Override
    public int getBuyersCount() throws SQLException {
        return buyersDAO.getBuyersCount();
    }
}
