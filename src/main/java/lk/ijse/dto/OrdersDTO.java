package lk.ijse.dto;

public class OrdersDTO {
     private String OrderId;
     private String OrderCompany;
     private String OrderCountry;
     private String StockId;
     private String AdminNic;
     private String Date;
     private String Time;

    public OrdersDTO() {

    }

    public OrdersDTO(String orderId, String orderCompany, String orderCountry, String stockId, String adminnic, String date, String time) {
        OrderId = orderId;
        OrderCompany = orderCompany;
        OrderCountry = orderCountry;
        StockId = stockId;
        AdminNic = adminnic;
        Date = date;
        Time = time;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getOrderCompany() {
        return OrderCompany;
    }

    public void setOrderCompany(String orderCompany) {
        OrderCompany = orderCompany;
    }

    public String getOrderCountry() {
        return OrderCountry;
    }

    public void setOrderCountry(String orderCountry) {
        OrderCountry = orderCountry;
    }

    public String getStockId() {
        return StockId;
    }

    public void setStockId(String stockId) {
        StockId = stockId;
    }

    public String getAdminNic() {
        return AdminNic;
    }

    public void setAdminNic(String adminnic) {
        AdminNic = adminnic;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrderId='" + OrderId + '\'' +
                ", OrderCompany='" + OrderCompany + '\'' +
                ", OrderCountry='" + OrderCountry + '\'' +
                ", ItemId='" + StockId + '\'' +
                ", Itemname='" + AdminNic + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }


}
