package lk.ijse.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import lk.ijse.Model.DBModel.*;
import lk.ijse.bo.custom.EmployeesBO;
import lk.ijse.bo.custom.impl.*;
import lk.ijse.controller.util.Navigation;
import lk.ijse.dao.custom.EmployeesDAO;
import lk.ijse.dao.custom.impl.EmployeesDAOimpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class DashBoardController implements Initializable {
    public Button btnEmployee;
    public Button btnOrders;
    public Button btnSuppliers;
    public Button btnBuyers;
    public Button btnMaterials;
    public Button btnMachines;
    public Button btnReports;
    public Button btnLogOut;
    public Label time;
    public Label date;
    public Button btnDashBoard;
    public Label lblEmployees;
    public Label lblStocks;
    public Label lblMaterials;
    public Label lblOrders;
    public Label lblSupplier;
    public Button btnDeleteAccount;
    public Label lblStocks1;

    int count1;
    int count2;
    int count3;
    int count4;
    int count5;
    int count6;
    int count7;


    @FXML
    private LineChart<?, ?> LineChart;
    @FXML
    private PieChart pieChart;

    @FXML
    public void btnEmployeeOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("employeemanagementform.fxml",event,null);
    }
    @FXML
    public void btnOrdersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("ordermanagementform.fxml",event,null);
    }
    @FXML
    public void btnSuppliersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("suppliermanagementform.fxml",event,null);
    }
    @FXML
    public void btnBuyersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("buyersmanagementform.fxml",event,null);
    }
    @FXML
    public void btnMaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }
    @FXML
    public void btnMachinesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("machinemanagementform.fxml",event,null);
    }
    @FXML
    public void dailyReportOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("dailyreportmanagementform.fxml",event,null);
    }
    @FXML
    public void btnDashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }
    @FXML
    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }

    EmployeesBOimpl employeesBOimpl = new EmployeesBOimpl();
    OrdersBOimpl ordersBOimpl =new OrdersBOimpl();
    StocksBOimpl stocksBOimpl= new StocksBOimpl();
    BuyersBOimpl buyersBOimpl = new BuyersBOimpl();
    MaterialBOimpl materialBOimpl = new MaterialBOimpl();
    SupplierBOimpl supplierBOimpl =  new SupplierBOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        try {
            inLineChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            iniPieChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        EmployeeCount();
        StocksCount();
        MaterialCount();
        OrdersCount();
        SupplierCount();
        AvailableStocks();
        BuyersCount();

    }

    private void inLineChart() throws SQLException {
        XYChart.Series series=new XYChart.Series();
        series.getData().add(new XYChart.Data("Stocks",stocksBOimpl.getStockCount()));
        series.getData().add(new XYChart.Data("Employees",employeesBOimpl.getEmployeeCount()));
        series.getData().add(new XYChart.Data("Buyers",buyersBOimpl.getBuyersCount()));
        series.getData().add(new XYChart.Data("Materials",materialBOimpl.getMaterialCount()));
        series.getData().add(new XYChart.Data("Orders",ordersBOimpl.getOrdersCount()));
        series.getData().add(new XYChart.Data("Suppliers",supplierBOimpl.getSupplierCount()));
        LineChart.getData().addAll(series);
    }


    private void Timenow(){
        Thread thread =new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM,  dd, yyyy");
            while (true){
                try{
                    Thread.sleep(1000);

                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                String timenow1 = sdf1.format(new Date());

                Platform.runLater(() ->{
                    time.setText(timenow);
                    date.setText(timenow1);
                });
            }
        });
        thread.start();
    }

    public  void EmployeeCount(){
        //int count1;
            try {
                count1 = employeesBOimpl.getEmployeeCount();
                System.out.println("Employee"+count1);
                lblEmployees.setText(String.valueOf(count1));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }


    public void StocksCount() {
        //int count2;
        try {
            count2 = stocksBOimpl.getStockCount();
            System.out.println("Stock"+count2);
            lblStocks.setText(String.valueOf(count2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void MaterialCount() {
        //int count3;
        try {
            count3 = materialBOimpl.getMaterialCount();
            System.out.println("Materials"+count3);
            lblMaterials.setText(String.valueOf(count3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void OrdersCount() {
        //int count4;
        try {
            count4 = ordersBOimpl.getOrdersCount();
            System.out.println("Orders"+count4);
            lblOrders.setText(String.valueOf(count4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void SupplierCount() {
        //int count5;
        try {
            count5 = supplierBOimpl.getSupplierCount();
            System.out.println("Suppliers"+count5);
            lblSupplier.setText(String.valueOf(count5));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void BuyersCount(){
        try {
            count7 = buyersBOimpl.getBuyersCount();
            System.out.println("Suppliers"+count7);
            //lblSupplier.setText(String.valueOf(count7));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void AvailableStocks(){
        try {
            count6= ordersBOimpl.getOrdersCount();
            System.out.println(count6);
            lblStocks1.setText(String.valueOf(count6));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void iniPieChart() throws SQLException {
        System.out.println(count1+"\n"+count2+"\n"+count3+"\n"+count4+"\n"+count5);

        ObservableList<PieChart.Data>pieChartData=FXCollections.observableArrayList(

                new PieChart.Data("Employees",employeesBOimpl.getEmployeeCount()),
                new PieChart.Data("Buyers",buyersBOimpl.getBuyersCount()),
                new PieChart.Data("Materials",materialBOimpl.getMaterialCount()),
                new PieChart.Data("Orders",ordersBOimpl.getOrdersCount()),
                new PieChart.Data("Suppliers",supplierBOimpl.getSupplierCount())

        );

        pieChart.setData(pieChartData);
        System.out.println(pieChartData);
    }

}

