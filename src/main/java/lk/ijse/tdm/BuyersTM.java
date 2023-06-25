package lk.ijse.tdm;

public class BuyersTM {
    private String BuyerId;
    private String AdminNic;
    private String Country;
    private String BuyerItem;
    private String Buydate;
    private String Time;

    public BuyersTM() {

    }

    public BuyersTM(String buyerId, String adminNic, String country, String buyerItem, String buydate, String time) {
        BuyerId = buyerId;
        AdminNic = adminNic;
        Country = country;
        BuyerItem = buyerItem;
        Buydate = buydate;
        Time = time;
    }

    public String getBuyerId() {
        return BuyerId;
    }

    public void setBuyerId(String buyerId) {
        BuyerId = buyerId;
    }

    public String getAdminNic() {
        return AdminNic;
    }

    public void setAdminNic(String adminNic) {
        AdminNic = adminNic;
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
        return "BuyersTM{" +
                "BuyerId='" + BuyerId + '\'' +
                ", AdminNic='" + AdminNic + '\'' +
                ", Country='" + Country + '\'' +
                ", BuyerItem='" + BuyerItem + '\'' +
                ", Buydate='" + Buydate + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
