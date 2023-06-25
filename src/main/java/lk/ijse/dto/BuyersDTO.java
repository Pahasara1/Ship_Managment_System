package lk.ijse.dto;

import lk.ijse.entity.Buyers;
import lk.ijse.tdm.BuyersTM;

import java.util.List;

public class BuyersDTO extends Buyers {
    private String BuyerId;
    private String AdminNic;
    private String Country;
    private String BuyerItem;
    private String Buydate;
    private String Time;

    public BuyersDTO() {
    }

    public BuyersDTO(String buyerId, String adminNic, String country, String buyerItem, String buydate, String time) {
        BuyerId = buyerId;
        AdminNic = adminNic;
        Country = country;
        BuyerItem = buyerItem;
        Buydate = buydate;
        Time = time;
    }

    public static List<BuyersTM> getAll() {
        return getAll();
    }

    public String getBuyerId() {
        return BuyerId;
    }

    public void setBuyerId(String buyerId) {
        BuyerId = buyerId;
    }

    public String getBuyerName() {
        return AdminNic;
    }

    public void setBuyerName(String buyerName) {
        AdminNic = buyerName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getBuyerItem() {
        return BuyerItem;
    }

    public void setBuyerItem(String buyerItem) {
        BuyerItem = buyerItem;
    }

    public String getBuydate() {
        return Buydate;
    }

    public void setBuydate(String buydate) {
        Buydate = buydate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "Buyers{" +
                "BuyerId='" + BuyerId + '\'' +
                ", BuyerName='" + AdminNic + '\'' +
                ", Country='" + Country + '\'' +
                ", BuyerItem='" + BuyerItem + '\'' +
                ", Buydate='" + Buydate + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
