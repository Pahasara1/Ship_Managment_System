package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.BuyersDAO;
import lk.ijse.dao.custom.impl.util.SQLUtill;
import lk.ijse.entity.Buyers;
import lk.ijse.db.DBconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuyersDAOimpl implements BuyersDAO {
    @Override
    public Buyers getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM buyers WHERE Buyer_Nic = ?",id);
        Buyers buyers = new Buyers();
        while (set.next()) {
            buyers.setBuyerId(set.getString(1));
            buyers.setBuyerName(set.getString(2));
            buyers.setCountry(set.getString(3));
            buyers.setTime(set.getString(4));
            buyers.setBuydate(set.getString(5));
        }
        return buyers;
    }

    @Override
    public boolean save(Buyers dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO buyers(Buyer_Nic,Admin_Nic,Buyer_Country,Buy_Item,Buy_Date,Buy_Time) " +
                "VALUES(?, ?, ?, ?, ?, ?)",
                dto.getBuyerId(),
                dto.getBuyerName(),
                dto.getCountry(),
                dto.getCountry(),
                dto.getBuydate(),
                dto.getTime());
    }

    @Override
    public boolean update(Buyers dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE buyers SET  Admin_Nic= ? ,Buyer_Country= ?, Buy_Item = ?, Buy_Date = ?, Buy_Time = ? WHERE Buyer_Nic = ?",
                dto.getBuyerName(),
                dto.getCountry(),
                dto.getBuyerItem(),
                dto.getBuydate(),
                dto.getTime(),
                dto.getBuyerId());
    }

    @Override
    public boolean delete(String BuyerNic) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM buyers WHERE Buyer_Nic=?";
        try (PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, BuyerNic);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Buyers search(String buyerNic) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM buyers WHERE Buyer_Nic = ?", buyerNic);
        Buyers buyers= new Buyers();
        if(set.next()) {

            buyers.setBuyerId(set.getString(1));
            buyers.setBuyerName(set.getString(2));
            buyers.setCountry(set.getString(3));
            buyers.setBuyerItem(set.getString(4));
            buyers.setBuydate(set.getString(5));
            buyers.setTime(set.getString(6));

        }
        return buyers;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {

            ResultSet set = SQLUtill.execute("SELECT Buyer_Nic FROM buyers ORDER BY LENGTH(Buyer_Nic),Buyer_Nic");
            ArrayList<String> list = new ArrayList<>();

            while (set.next()) {
                list.add(set.getString(1));
            }
            return list;
    }

    @Override
    public int getBuyersCount() throws SQLException {
        String sql="SELECT COUNT(Buyer_Nic) FROM buyers";

        try(PreparedStatement pstm = DBconnection.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt(1);
            }
        }
        return 0;
    }
}
