package lk.ijse.entity;

public class OrderDetail {
    private String OrderId;
    private String StockId;
    private String Qty;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, String stockId, String qty) {
        OrderId = orderId;
        StockId = stockId;
        Qty = qty;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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
        return "OrderDetail{" +
                "OrderId='" + OrderId + '\'' +
                ", StockId='" + StockId + '\'' +
                ", Qty='" + Qty + '\'' +
                '}';
    }
}
