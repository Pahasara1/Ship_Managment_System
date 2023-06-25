package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        AdminReports,Buyers,dailyReports,Employees,OrderDetails,GenerateOtp,Machine,Materials,Orders,Register,Stocks,Suppliers,UseMachines
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case AdminReports:
                return (T) new AdminReportsBOimpl();
            case Buyers:
                return (T) new BuyersBOimpl();
            case dailyReports:
                return (T) new DailyreportsBOimpl();
            case Employees:
                return (T) new EmployeesBOimpl();
            case Machine:
                return (T) new MachineBOimpl();
            case Materials:
                return (T) new MaterialBOimpl();
            case Orders:
                return (T) new OrdersBOimpl();
            case Register:
                return (T) new RegisterBOimpl();
            case Stocks:
                return (T) new StocksBOimpl();
            case Suppliers:
                return (T) new SupplierBOimpl();
            case UseMachines:
                return (T) new UseMachineBOimpl();
            default:
                return null;
        }
    }
}
