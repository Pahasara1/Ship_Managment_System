package lk.ijse.controller;


import javafx.collections.FXCollections;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.custom.OrdersBO;
import lk.ijse.bo.custom.impl.OrdersBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.impl.OrdersDAOimpl;
import lk.ijse.dto.OrdersDTO;
import lk.ijse.entity.Orders;
import lk.ijse.tdm.OrdersTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderManagementController implements Initializable {

    public TableView <OrdersTM>tblOrders;
    public TableColumn colOrderId;
    public TableColumn colOrderCompany;
    public TableColumn colOrderCountry;
    public TableColumn colItemId;
    public TableColumn colAdmnNic;
    public TableColumn coldate;
    public TableColumn colTime;

    public TextField txtOrderCountry;
    public TextField txtOrderId;
    public TextField txtOrderCompany;
    public TextField txtAdminNic;
    public Label txtTime;
    public Label txtDate;

    public Button btnDelete;
    public Button btnSave;
    public Button btnEmployees5;
    public Button btnOrders5;
    public Button btnSuppliers5;
    public Button btnBuyers5;
    public Button btnMaterials5;
    public Button btnMachines5;
    public Button btnReports5;
    public Button btnLogOut;
    public Button btnDashBoard5;
    public TextField txtDate1;
    public TextField txtTime1;
    public Button btnSearch;
    public TextField txtSearch;
    public Button btnRefresh;
    public TextField txtStockId;
    public TableColumn colStockId;

    ObservableList<OrdersTM> ordersTMS = FXCollections.observableArrayList();

    OrdersBO ordersBO= BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Orders);

    OrdersBOimpl ordersBOimpl=new OrdersBOimpl();


    public void btnEmployeesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("employeemanagementform.fxml",event,null);
    }

    public void btnOrdersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("ordermanagementform.fxml",event,null);
    }

    public void btnSuppliersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("suppliermanagementform.fxml",event,null);
    }

    public void btnBuyersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("buyersmanagementform.fxml",event,null);
    }

    public void btnmaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }

    public void btnmachinesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("machinemanagementform.fxml",event,null);
    }

    public void btnReportsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("dailyreportmanagementform.fxml",event,null);
    }

    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }

    public void btndashBoardOnAction5(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }

    OrdersDAOimpl ordersDAOimpl = new OrdersDAOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        getCellValueFactory();
        getAllIds();


        tblOrders.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });
    }

    private void setData(OrdersTM tm) {
        btnSave.setText("Update");
        txtDate1.setDisable(false);
        txtTime1.setDisable(false);
        txtOrderId.setText(tm.getOrderId());
        txtOrderCompany.setText(tm.getOrderCompany());
        txtOrderCountry.setText(tm.getOrderCountry());
        txtStockId.setText(tm.getStockId());
        txtAdminNic.setText(tm.getAdminNic());
        txtDate1.setText(tm.getDate());
        txtTime1.setText(tm.getTime());
    }

//Delete
    public void btnDeleteOnAction(ActionEvent event) {
        String code = txtOrderId.getText();
        try {
            boolean isDeleted = ordersDAOimpl.delete(code);
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

            if (txtOrderId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Order Id").show();
            } else if (txtOrderCountry.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Order Country").show();
            } else if (txtOrderCompany.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Quantity").show();
            } else if (txtStockId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Stock Id").show();
            } else if (txtAdminNic.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Your Nic").show();
            } else {

                if (ValidateField.OrderId(txtOrderId.getText())) {
                    System.out.println("yes11");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Order Id (Ex-O001)").show();
                }

                if (ValidateField.StockId(txtStockId.getText())) {
                    System.out.println("yes12");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Stock Id (Ex-ST001)").show();
                }

                if (ValidateField.Nic(txtAdminNic.getText())) {
                    System.out.println("yes13");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Your Valid Nic (Minimum 10 Characters)").show();
                }


                if (ValidateField.OrderId(txtOrderId.getText()) && ValidateField.StockId(txtStockId.getText()) && ValidateField.Nic(txtAdminNic.getText())) {

                    String OrderId = txtOrderId.getText();
                    String OrderQty = txtOrderCompany.getText();
                    String OrderCountry = txtOrderCountry.getText();
                    String StockId = txtStockId.getText();
                    String Orderdate = txtDate.getText();
                    String OrderTime = txtTime.getText();
                    String nic = txtAdminNic.getText();

                    if (ValidateField.Nic(txtAdminNic.getText())) {


                        boolean isPlaced = false;
                        try {
                            isPlaced = ordersBO.placeOrder(OrderId, OrderQty, OrderCountry, StockId, nic, Orderdate, OrderTime);
                            if (isPlaced) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed").show();
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Order Not Placed").show();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
                        }

                    } else {
                        new Alert(Alert.AlertType.ERROR, "Enter Valid Your Nic Number").show();
                    }
                }
            }
        }
//Update
        if (btnSave.getText().equals("Update")) {
            Orders o = new Orders(
                    txtOrderId.getText(),
                    txtOrderCompany.getText(),
                    txtOrderCountry.getText(),
                    txtStockId.getText(),
                    txtAdminNic.getText(),
                    txtTime1.getText(),
                    txtDate1.getText()
            );
            try {
                boolean isUpdate = ordersDAOimpl.update(o);
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
            ArrayList<String> list = ordersDAOimpl.getAllId();

            for (int i = 0; i < list.size(); i++) {
                getAll(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    void getAll(String id){

        try {
            OrdersDTO ordersDTO = ordersBOimpl.getAllOrders(id);
            OrdersTM ordersTM = new OrdersTM();
            ordersTM.setOrderId(ordersDTO.getOrderId());
            ordersTM.setOrderCompany(ordersDTO.getOrderCompany());
            ordersTM.setOrderCountry(ordersDTO.getOrderCountry());
            ordersTM.setStockId(ordersDTO.getStockId());
            ordersTM.setAdminNic(ordersDTO.getAdminNic());
            ordersTM.setDate(ordersDTO.getDate());
            ordersTM.setTime(ordersDTO.getTime());
            ordersTMS.add(ordersTM);
            tblOrders.setItems(ordersTMS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void getCellValueFactory(){

        colOrderId.setCellValueFactory(new PropertyValueFactory("OrderId"));
        colOrderCompany.setCellValueFactory(new PropertyValueFactory("OrderCompany"));
        colOrderCountry.setCellValueFactory(new PropertyValueFactory("OrderCountry"));
        colStockId.setCellValueFactory(new PropertyValueFactory("StockId"));
        colAdmnNic.setCellValueFactory(new PropertyValueFactory("AdminNic"));
        coldate.setCellValueFactory(new PropertyValueFactory("Date"));
        colTime.setCellValueFactory(new PropertyValueFactory("Time"));

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
                    txtTime.setText(timenow);
                    txtDate.setText(timenow1);
                });
            }
        });
        thread.start();
    }

    public void btnSearchOnAction(ActionEvent event) {
        btnSave.setText("Update");
        try {
            Orders orders = (Orders) ordersDAOimpl.search(txtSearch.getText());

            txtOrderId.setText(orders.getOrderId());
            txtOrderCountry.setText(orders.getOrderCountry());
            txtOrderCompany.setText(orders.getOrderCompany());
            txtStockId.setText(orders.getStockId());
            txtAdminNic.setText(orders.getAdminNic());
            txtDate1.setText(orders.getDate());
            txtTime1.setText(orders.getTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("ordermanagementform.fxml",event,null);
    }

    public void btnNewOnAction(ActionEvent event) {
        btnSave.setText("Save");
        txtOrderId.setText("");
        txtStockId.setText("");
        txtOrderCountry.setText("");
        txtOrderCompany.setText("");
        txtTime1.setText("");
        txtDate1.setText("");
        txtAdminNic.setText("");
        txtDate1.setDisable(true);
        txtTime1.setDisable(true);
    }
}
