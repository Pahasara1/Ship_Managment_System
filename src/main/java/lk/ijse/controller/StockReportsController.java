package lk.ijse.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.StocksBO;
import lk.ijse.bo.custom.impl.StocksBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.StockReportsDAO;
import lk.ijse.dao.custom.impl.StockReporsDAOimpl;
import lk.ijse.db.DBconnection;
import lk.ijse.dto.StockDTO;
import lk.ijse.entity.Stocks;
import lk.ijse.tdm.StockTM;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class StockReportsController implements Initializable {

    public TableView <StockTM>tblStockReports;
    public TableColumn colEmployeeNic;
    public TableColumn colStockId;
    public TableColumn colSupplierNic;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colQuantity;

    public TextField txtStockId;
    public TextField txtEmployeeNic;
    public TextField txtSupplierNic;
    public TextField txtQuantity;

    public Button btnDelete;
    public Button btnSave;
    public Label lblTime;
    public Label lblDate;
    public ComboBox comBox;

    public Button btnEmployees6;
    public Button btnOrders6;
    public Button btnSuppliers6;
    public Button btnBuyers6;
    public Button materials6;
    public Button btnmachines6;
    public Button btnReports6;
    public Button btnLogOut;
    public Button btnDashBoard6;
    public TextField txtDate;
    public TextField txtTime;
    public Button btnSearch;
    public TextField txtSearch;
    public Button btnRefresh;

    StocksBOimpl stocksBOimpl= new StocksBOimpl();
    ObservableList<StockTM> stockTMS = FXCollections.observableArrayList();

    public void btnEmployeesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("employeemanagementform.fxml",event,null);
    }

    public void btnOrdersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("ordermanagementform.fxml",event,null);
    }

    public void btnSuppliersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("suppliermanagementform.fxml",event,null);
    }

    public void btnBuyersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("buyersmanagementform.fxml",event,null);
    }

    public void btnmaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }

    public void btnMachinesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("machinemanagementform.fxml",event,null);
    }

    public void btnReportOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("dailyreportmanagementform.fxml",event,null);
    }

    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }

    public void btndashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }

    StockReporsDAOimpl stockReporsDAOimpl = new StockReporsDAOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        getAllIds();
        getCellValueFactory();

        tblStockReports.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });

        ObservableList<String> obList = FXCollections.observableArrayList("Daily System Details", "Add Employee Details", "Stock Details","Used Machine Details");
        comBox.setItems(obList);
    }

    private void setData(StockTM tm) {
        btnSave.setText("Update");
        txtTime.setDisable(false);
        txtDate.setDisable(false);
        txtStockId.setText(tm.getStockId());
        txtEmployeeNic.setText(tm.getEmployeeNic());
        txtSupplierNic.setText(tm.getSupplierNic());
        txtTime.setText(tm.getTime());
        txtDate.setText(tm.getDate());
        txtQuantity.setText(tm.getQuantity());
    }

//Delete
    public void btnDeleteOnAction(ActionEvent event) {
        String code = txtStockId.getText();
        try {
            boolean isDeleted = stocksBOimpl.deleteStocks(code);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//Save
    public void btnSaveOnAction(ActionEvent event) {
        if (btnSave.getText().equals("Save")) {

            if (txtStockId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Stock Id").show();
            } else if (txtEmployeeNic.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Employee Nic").show();
            } else if (txtSupplierNic.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Supplier Nic").show();
            } else if (txtQuantity.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Quantity").show();
            } else {

                if (ValidateField.StockId(txtStockId.getText())) {
                    System.out.println("yes9");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Stock Id (Ex-ST001)").show();
                }

                if (ValidateField.Nic(txtEmployeeNic.getText())) {
                    System.out.println("yes10");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Employee Nic (Minimum 10 Characters)").show();
                }

                if (ValidateField.Nic(txtSupplierNic.getText())) {
                    System.out.println("yes10");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Supplier Nic (Minimum 10 Characters)").show();
                }

                if (ValidateField.StockId(txtStockId.getText()) && ValidateField.Nic(txtEmployeeNic.getText()) && ValidateField.Nic(txtSupplierNic.getText())) {
                    Stocks s = new Stocks(
                            txtStockId.getText(),
                            txtEmployeeNic.getText(),
                            txtSupplierNic.getText(),
                            lblDate.getText(),
                            lblTime.getText(),
                            txtQuantity.getText()
                    );
                    try {
                        boolean isSaved = stocksBOimpl.saveStocks(s);
                        if (isSaved) {
                            System.out.println("saved");
                            new Alert(Alert.AlertType.CONFIRMATION, "Saved...!").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Unsaved...!").show();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
//Update
        if (btnSave.getText().equals("Update")) {
            Stocks s = new Stocks(
                    txtStockId.getText(),
                    txtEmployeeNic.getText(),
                    txtSupplierNic.getText(),
                    txtTime.getText(),
                    txtDate.getText(),
                    txtQuantity.getText()
            );
            try {
                boolean isUpdate = stocksBOimpl.updateStocks(s);
                if (isUpdate) {
                    System.out.println("saved");
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Try Again...!").show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void getAllIds() {
        try {
            ArrayList<String> list = stockReporsDAOimpl.getAllId();

            for (int i = 0; i < list.size(); i++) {
                getAll(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    void getAll(String id){
        try {
            StockDTO stockDTO = stocksBOimpl.getAllStocks(id);
            StockTM stockTM = new StockTM();
            stockTM.setStockId(stockDTO.getStockId());
            stockTM.setEmployeeNic(stockDTO.getEmployeeNic());
            stockTM.setSupplierNic(stockDTO.getSupplierNic());
            stockTM.setTime(stockDTO.getTime());
            stockTM.setTime(stockDTO.getDate());
            stockTMS.add(stockTM);
            tblStockReports.setItems(stockTMS);
            System.out.println("stock report"+ stockDTO.getStockId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void comBoxOnAction(ActionEvent event) throws IOException {
        if(comBox.getValue().equals("Daily System Details")){
            Navigation.navigation("dailyreportmanagementform.fxml",event,null);
        }else if(comBox.getValue().equals("Add Employee Details")){
            Navigation.navigation("adminreports.fxml",event,null);
        }else if(comBox.getValue().equals("Stock Details")){
            Navigation.navigation("stockreport.fxml",event,null);
        }else if(comBox.getValue().equals("Used Machine Details")){
            Navigation.navigation("usingmachinesreports.fxml",event,null);
        }
    }

    void getCellValueFactory(){

        colStockId.setCellValueFactory(new PropertyValueFactory("StockId"));
        colEmployeeNic.setCellValueFactory(new PropertyValueFactory("EmployeeNic"));
        colSupplierNic.setCellValueFactory(new PropertyValueFactory("SupplierNic"));
        colTime.setCellValueFactory(new PropertyValueFactory("Time"));
        colDate.setCellValueFactory(new PropertyValueFactory("Date"));
        colQuantity.setCellValueFactory(new PropertyValueFactory("Quantity"));

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
                    lblTime.setText(timenow);
                    lblDate.setText(timenow1);
                });
            }
        });
        thread.start();
    }

    public void btnSearchOnAction(ActionEvent event) {
        btnSave.setText("Update");
        try {
            Stocks stock = (Stocks) stocksBOimpl.searchStocks(txtSearch.getText());

            txtStockId.setText(stock.getStockId());
            txtEmployeeNic.setText(stock.getEmployeeNic());
            txtSupplierNic.setText(stock.getSupplierNic());
            txtTime.setText(stock.getTime());
            txtDate.setText(stock.getDate());
            txtQuantity.setText(stock.getQuantity());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("stockreport.fxml",event,null);
    }

    public void generateReportsOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/Reports/StockReportsjrxml.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBconnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewOnAction(ActionEvent event) {
        btnSave.setText("Save");
        txtSupplierNic.setText("");
        txtEmployeeNic.setText("");
        txtStockId.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtQuantity.setText("");
        txtTime.setDisable(true);
        txtDate.setDisable(true);

    }
}
