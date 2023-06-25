package lk.ijse.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.impl.BuyersBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.impl.BuyersDAOimpl;
import lk.ijse.dto.BuyersDTO;
import lk.ijse.entity.Buyers;
import lk.ijse.tdm.BuyersTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class BuyersManagementFormController implements Initializable {

    public TableView <BuyersTM>tblBuyers;
    public TableColumn colBuyerId;
    public TableColumn colAdminNic;
    public TableColumn colContry;
    public TableColumn colBuyItem;
    public TableColumn colDate;
    public TableColumn colTime;

    public TextField txtBuyerId;
    public TextField txtAdminNic;
    public TextField txtCountry;
    public TextField txtBuyItem;
    public TextField txtDate;
    public TextField txtTime;

    public Button btnDelete;
    public Button btnsave;
    public Button btnEmployee1;
    public Button btnOrders1;
    public Button btnSuppliers1;
    public Button btnBuyers1;
    public Button btnMaterials1;
    public Button btnMachine1;
    public Button btnReports1;
    public Button btnLogOut;
    public Button btnDashBoard;
    public Label txtDate1;
    public Label txtTime1;
    public TextField txtSearch;
    public Button btnSearch;
    public Button btnRefresh;


    BuyersBOimpl buyersBOimpl = new BuyersBOimpl();


    ObservableList<BuyersTM> buyersTMS = FXCollections.observableArrayList();

    public void btnEmployeOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("employeemanagementform.fxml",event,null);
    }

    public void btnOrderOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("ordermanagementform.fxml",event,null);
    }

    public void btnSuppliersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("suppliermanagementform.fxml",event,null);
    }

    public void btnBuyersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("buyersmanagementform.fxml",event,null);
    }

    public void btnMaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }

    public void btnMachinesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("machinemanagementform.fxml",event,null);
    }

    public void btnReportsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("dailyreportmanagementform.fxml",event,null);
    }

    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }

    public void btnDashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }

    BuyersDAOimpl buyersDAOimpl = new BuyersDAOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        getAllIds();
        getCellValueFactory();

        tblBuyers.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });
    }

    private void setData(BuyersTM tm) {
        btnsave.setText("Update");
        txtDate.setDisable(false);
        txtTime.setDisable(false);
        txtBuyerId.setText(tm.getBuyerId());
        txtAdminNic.setText(tm.getAdminNic());
        txtCountry.setText(tm.getCountry());
        txtBuyItem.setText(tm.getBuyerItem());
        txtDate.setText(tm.getBuydate());
        txtTime.setText(tm.getTime());
    }

//Delete
    public void btnDeleteOnAction(ActionEvent event) {
        String code =txtBuyerId.getText();
        try {
            boolean isDeleted = buyersBOimpl.deleteBuyers(code);
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
        if (btnsave.getText().equals("Save")) {

            if (txtBuyerId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Buyer Id").show();
            } else if (txtAdminNic.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Your Nic").show();
            } else if (txtBuyItem.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Item").show();
            } else if (txtCountry.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Country").show();
            } else {

                if (ValidateField.Nic(txtBuyerId.getText())) {
                    System.out.println("Yes7");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Buyer Nic (Minimum 10 Characters)").show();
                }


                if (ValidateField.Nic(txtAdminNic.getText())) {
                    System.out.println("Yes8");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Your Valid Nic (Minimum 10 Characters)").show();
                }


                if (ValidateField.Nic(txtBuyerId.getText()) && ValidateField.Nic(txtAdminNic.getText())) {
                    Buyers b = new Buyers(
                            txtBuyerId.getText(),
                            txtAdminNic.getText(),
                            txtCountry.getText(),
                            txtBuyItem.getText(),
                            txtDate1.getText(),
                            txtTime1.getText()
                    );
                    try {
                        boolean isSaved = buyersBOimpl.saveBuyers((BuyersDTO) b);
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
        if (btnsave.getText().equals("Update")) {
            Buyers buy = new Buyers(
                    txtBuyerId.getText(),
                    txtAdminNic.getText(),
                    txtCountry.getText(),
                    txtBuyItem.getText(),
                    txtDate.getText(),
                    txtTime.getText()
            );
            try {
                boolean isUpdate = buyersBOimpl.updateBuyers((BuyersDTO) buy);
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
                ArrayList<String> list = buyersDAOimpl.getAllId();

                for (int i = 0; i < list.size(); i++) {
                    getAll(list.get(i));
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
    }
    void getAll(String id){

        try {
            BuyersDTO buyersDTO = buyersBOimpl.getAllBuyers(id);
            BuyersTM buyersTM = new BuyersTM();
            buyersTM.setBuyerId(buyersDTO.getBuyerId());
            buyersTM.setAdminNic(buyersDTO.getBuyerName());
            buyersTM.setCountry(buyersDTO.getCountry());
            buyersTM.setBuyerItem(buyersDTO.getBuyerItem());
            buyersTM.setBuydate(buyersDTO.getBuydate());
            buyersTM.setTime(buyersDTO.getTime());
//            System.out.println("Buyers id "+buyersDTO.getBuyerId());
            buyersTMS.add(buyersTM);
            tblBuyers.setItems(buyersTMS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void getCellValueFactory(){

        colBuyerId.setCellValueFactory(new PropertyValueFactory("BuyerId"));
        colAdminNic.setCellValueFactory(new PropertyValueFactory("AdminNic"));
        colContry.setCellValueFactory(new PropertyValueFactory("Country"));
        colBuyItem.setCellValueFactory(new PropertyValueFactory("BuyerItem"));
        colDate.setCellValueFactory(new PropertyValueFactory("Buydate"));
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
                    txtTime1.setText(timenow);
                    txtDate1.setText(timenow1);
                });
            }
        });
        thread.start();
    }

    public void btnSearchOnAction(ActionEvent event) {
        btnsave.setText("Update");
        try {
            lk.ijse.entity.Buyers buyers = buyersBOimpl.searchBuyers(txtSearch.getText());

            txtBuyerId.setText(buyers.getBuyerId());
            txtAdminNic.setText(buyers.getBuyerName());
            txtCountry.setText(buyers.getCountry());
            txtBuyItem.setText(buyers.getBuyerItem());
            txtDate.setText(buyers.getTime());
            txtTime.setText(buyers.getTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("buyersmanagementform.fxml",event,null);
    }

    public void btnNewOnAction(ActionEvent event) {
        btnsave.setText("Save");
        txtBuyerId.setText("");
        txtAdminNic.setText("");
        txtBuyItem.setText("");
        txtCountry.setText("");
        txtTime.setText("");
        txtDate.setText("");
        txtDate.setDisable(true);
        txtTime.setDisable(true);
    }
}
