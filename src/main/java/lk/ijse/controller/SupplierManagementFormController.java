package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.bo.custom.impl.SupplierBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dao.custom.impl.SupplierDAOimpl;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;
import lk.ijse.tdm.SupplierTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class SupplierManagementFormController implements Initializable {

    public TableView <SupplierTM>tblSupplier;
    public TableColumn colSupplierId;
    public TableColumn colSupplierName;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colDate;
    public TableColumn colTime;

    public TextField txtSupplierId;
    public TextField txtSupplierName;
    public TextField txtIitemId;
    public TextField txtItemname;
    public TextField txtdate;
    public TextField txtTime;
    public TextField txtSearchBar;

    public Button btnSave;
    public Button btnSearch;
    public Button btnDelete;

    public Button btnEmployees6;
    public Button btnOrders6;
    public Button btnSuppliers6;
    public Button btnBuyers6;
    public Button btnmaterials6;
    public Button btnmachines6;
    public Button btnReports6;
    public Button btnLogOut;
    public Button btnDashBoard6;
    public Button btnNewForm;
    public Label lblTime1;
    public Label lblDate11;
    public Button btnRefresh;

    SupplierDAO supplierDAO = new SupplierDAOimpl();

    @FXML
    public void btnEmployeesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxxx");
        Navigation.navigation("employeemanagementform.fxml",event,null);
    }
    @FXML
    public void btnOrdersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxxx");
        Navigation.navigation("ordermanagementform.fxml",event,null);
    }
    @FXML
    public void btnSuppliersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxxx");
        Navigation.navigation("suppliermanagementform.fxml",event,null);
    }
    @FXML
    public void btnBuyersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxxx");
        Navigation.navigation("buyersmanagementform.fxml",event,null);
    }
    @FXML
    public void btnmaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }
    @FXML
    public void btnMachinesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxxx");
        Navigation.navigation("machinemanagementform.fxml",event,null);
    }
    @FXML
    public void btnReportOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxxx");
        Navigation.navigation("dailyreportmanagementform.fxml",event,null);
    }
    @FXML
    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }
    @FXML
    public void btndashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }

    SupplierBOimpl supplierBOimpl = new SupplierBOimpl();
    ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        getAllIds();
        getCellValueFactory();

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });
    }

//Delete
    public void btnDeleteOnAction(ActionEvent event) {

        String code = txtSupplierId.getText();
        try {
            boolean isDeleted = supplierBOimpl.deleteSuppliers(code);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").showAndWait();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(SupplierTM tm) {
        btnSave.setText("Update");
        txtdate.setDisable(false);
        txtTime.setDisable(false);
        txtSupplierId.setText(tm.getSupplierId());
        txtSupplierName.setText(tm.getSupplierName());
        txtIitemId.setText(tm.getItemId());
        txtItemname.setText(tm.getItemName());
        txtdate.setText(tm.getDate());
        txtTime.setText(tm.getTime());
    }

//Save
    public void btnSaveOnAction(ActionEvent event) {
        if (btnSave.getText().equals("Save")) {

            if (txtSupplierId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Supplier Nic").show();
            } else if (txtSupplierName.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Supplier Name").show();
            } else if (txtIitemId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Item Id").show();
            } else if (txtItemname.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Item Name").show();
            } else {

                if (ValidateField.Nic(txtSupplierId.getText())) {

                    Supplier s = new Supplier(
                            txtSupplierId.getText(),
                            txtSupplierName.getText(),
                            txtIitemId.getText(),
                            txtItemname.getText(),
                            lblDate11.getText(),
                            lblTime1.getText()
                    );
                    try {
                        boolean isSaved = supplierBOimpl.saveSuppliers(s);
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
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Nic").show();
                }
            }
        }
//Update
        if (btnSave.getText().equals("Update")) {
            Supplier s1 = new Supplier(
                    txtSupplierId.getText(),
                    txtSupplierName.getText(),
                    txtIitemId.getText(),
                    txtItemname.getText(),
                    txtdate.getText(),
                    txtTime.getText()
            );
            try {
                boolean isUpdate = supplierBOimpl.updateSuppliers(s1);
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
            ArrayList<String> list = supplierDAO.getAllId();

            for (int i = 0; i < list.size(); i++) {
                getAll(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    void getAll(String id){
        try {
            SupplierDTO supplierDTO = supplierBOimpl.getAllBuyers(id);
            SupplierTM supplierTM = new SupplierTM();
            supplierTM.setSupplierId(supplierDTO.getSupplierId());
            supplierTM.setSupplierName(supplierDTO.getSupplierName());
            supplierTM.setItemId(supplierDTO.getItemId());
            supplierTM.setItemName(supplierDTO.getItemName());
            supplierTM.setDate(supplierDTO.getDate());
            supplierTM.setTime(supplierDTO.getTime());
            supplierTMS.add(supplierTM);
            tblSupplier.setItems(supplierTMS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void getCellValueFactory(){

        colSupplierId.setCellValueFactory(new PropertyValueFactory("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory("supplierName"));
        colItemId.setCellValueFactory(new PropertyValueFactory("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory("itemName"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colTime.setCellValueFactory(new PropertyValueFactory("time"));

    }

    public void btnNewFormOnAction(ActionEvent event) {

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
                    lblTime1.setText(timenow);
                    lblDate11.setText(timenow1);
                });
            }
        });
        thread.start();
    }

    public void btnSearchOnAction1(ActionEvent event) {
        btnSave.setText("Update");
        try {
            Supplier supplier = (Supplier) supplierBOimpl.searchSuppliers(txtSearchBar.getText());

            txtSupplierId.setText(supplier.getSupplierId());
            txtSupplierName.setText(supplier.getSupplierName());
            txtIitemId.setText(supplier.getItemId());
            txtItemname.setText(supplier.getItemName());
            txtdate.setText(supplier.getDate());
            txtTime.setText(supplier.getTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("suppliermanagementform.fxml",event,null);
    }

    public void btnNewOnAction(ActionEvent event) {
        btnSave.setText("Save");
        txtSupplierId.setText("");
        txtSupplierName.setText("");
        txtIitemId.setText("");
        txtItemname.setText("");
        txtdate.setText("");
        txtTime.setText("");
        txtTime.setDisable(true);
        txtdate.setDisable(true);
    }
}