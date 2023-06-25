package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        AdminReports,Buyers,dailyReports,Employees,Machine,Materials,Orders,Register,Stocks,Suppliers,UseMachines,OrderDetail
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case AdminReports:
                return (T) new AdminReportsDAOimpl();
            case Buyers:
                return (T) new BuyersDAOimpl();
            case dailyReports:
                return (T) new DailyReportsDAOimpl();
            case Employees:
                return (T) new EmployeesDAOimpl();
            case Machine:
                return (T) new MachineDAOimpl();
            case Materials:
                return (T) new MaterialDAOimpl();
            case Orders:
                return (T) new OrdersDAOimpl();
            case Register:
                return (T) new RegisterDAOimpl();
            case Stocks:
                return (T) new StockReporsDAOimpl();
            case Suppliers:
                return (T) new SupplierDAOimpl();
            case UseMachines:
                return (T) new UseMachineDAOimpl();
            case OrderDetail:
                return (T) new OrderDetailsDAOimpl();
            default:
                return null;
        }
    }
}
