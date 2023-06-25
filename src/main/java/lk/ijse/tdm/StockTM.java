package lk.ijse.tdm;

import lk.ijse.entity.Stocks;


public class StockTM extends Stocks {
    private String StockId;
    private String EmployeeNic;
    private String SupplierNic;
    private String Time;
    private String Date;
    private String Quantity;

    public StockTM() {
    }

    public StockTM(String stockId, String employeeNic, String supplierNic, String time, String date, String quantity) {
        StockId = stockId;
        EmployeeNic = employeeNic;
        SupplierNic = supplierNic;
        Time = time;
        Date = date;
        Quantity = quantity;
    }

    @Override
    public String getStockId() {
        return StockId;
    }

    @Override
    public void setStockId(String stockId) {
        StockId = stockId;
    }

    @Override
    public String getEmployeeNic() {
        return EmployeeNic;
    }

    @Override
    public void setEmployeeNic(String employeeNic) {
        EmployeeNic = employeeNic;
    }

    @Override
    public String getSupplierNic() {
        return SupplierNic;
    }

    @Override
    public void setSupplierNic(String supplierNic) {
        SupplierNic = supplierNic;
    }

    @Override
    public String getTime() {
        return Time;
    }

    @Override
    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String getDate() {
        return Date;
    }

    @Override
    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String getQuantity() {
        return Quantity;
    }

    @Override
    public void setQuantity(String quantity) {
        Quantity = quantity;
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
