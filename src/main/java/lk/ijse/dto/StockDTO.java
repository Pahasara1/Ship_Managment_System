package lk.ijse.dto;


public class StockDTO{
    private String StockId;
    private String EmployeeNic;
    private String SupplierNic;
    private String Time;
    private String Date;
    private String Quantity;

    public StockDTO() {
    }

    public StockDTO(String stockId, String employeeNic, String supplierNic, String time, String date, String quantity) {
        StockId = stockId;
        EmployeeNic = employeeNic;
        SupplierNic = supplierNic;
        Time = time;
        Date = date;
        Quantity = quantity;
    }

    public String getStockId() {
        return StockId;
    }

    public void setStockId(String stockId) {
        StockId = stockId;
    }

    public String getEmployeeNic() {
        return EmployeeNic;
    }

    public void setEmployeeNic(String employeeNic) {
        EmployeeNic = employeeNic;
    }

    public String getSupplierNic() {
        return SupplierNic;
    }

    public void setSupplierNic(String supplierNic) {
        SupplierNic = supplierNic;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "StockId='" + StockId + '\'' +
                ", EmployeeNic='" + EmployeeNic + '\'' +
                ", SupplierNic='" + SupplierNic + '\'' +
                ", Time='" + Time + '\'' +
                ", Date='" + Date + '\'' +
                ", Quantity='" + Quantity + '\'' +
                '}';
    }
}
