package lk.ijse.entity;

import lk.ijse.dto.StockDTO;

public class Stocks extends StockDTO {
    private String StockId;
    private String EmployeeNic;
    private String SupplierNic;
    private String Time;
    private String Date;
    private String Quantity;

    public Stocks() {
    }

    public Stocks(String stockId, String employeeNic, String supplierNic, String time, String date, String quantity) {
        StockId = stockId;
        EmployeeNic = employeeNic;
        SupplierNic = supplierNic;
        Time = time;
        Date = date;
        Quantity = quantity;
    }

    public Stocks(String orderQty, String stockId) {
        this.StockId=stockId;
        this.Quantity=orderQty;
    }

    public void setStockId(String stockId) {
        StockId = stockId;
    }

    public void setEmployeeNic(String employeeNic) {
        EmployeeNic = employeeNic;
    }

    public void setSupplierNic(String supplierNic) {
        SupplierNic = supplierNic;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getStockId() {
        return StockId;
    }

    public String getEmployeeNic() {
        return EmployeeNic;
    }

    public String getSupplierNic() {
        return SupplierNic;
    }

    public String getTime() {
        return Time;
    }

    public String getDate() {
        return Date;
    }

    public String getQuantity() {
        return Quantity;
    }

    @Override
    public String toString() {
        return "StockTM{" +
                "StockId='" + StockId + '\'' +
                ", EmployeeNic='" + EmployeeNic + '\'' +
                ", SupplierNic='" + SupplierNic + '\'' +
                ", Time='" + Time + '\'' +
                ", Date='" + Date + '\'' +
                ", Quantity='" + Quantity + '\'' +
                '}';
    }
}
