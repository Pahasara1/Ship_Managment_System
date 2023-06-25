package lk.ijse.dto;


public class OrderDetailsDTO {

    private String StockId;
    private String Qty;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String stockId, String qty) {
        StockId = stockId;
        Qty = qty;
    }


    public String getStockId() {
        return StockId;
    }

    public void setStockId(String stockId) {
        StockId = stockId;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "StockId='" + StockId + '\'' +
                ", Qty='" + Qty + '\'' +
                '}';
    }
}
