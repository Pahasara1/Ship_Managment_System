package lk.ijse.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.UseMachineBO;
import lk.ijse.bo.custom.impl.UseMachineBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.UseMachineDAO;
import lk.ijse.dao.custom.impl.UseMachineDAOimpl;
import lk.ijse.db.DBconnection;
import lk.ijse.dto.UsingMachinesDTO;
import lk.ijse.entity.UseMachines;
import lk.ijse.tdm.UsingMachinesTM;
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

public class UsingMachineReportsController implements Initializable {

    public TableView <UsingMachinesTM>tblUseMachines;
    public TableColumn colMaterialId;
    public TableColumn colEmployeeNic;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colMachineId;

    public TextField txtMaterialId;
    public TextField txtMachineId;
    public TextField txtEmployeeNic;
    public TextField search;

    public Button btnDelete;
    public Button btnSave;

    public Label lblTime;
    public Label lblDate;
    public ComboBox comBox;
    public Button btnSearch;

    public Button btnEmployees6;
    public Button btnOrders6;
    public Button btnSuppliers6;
    public Button btnBuyers6;
    public Button btnmaterials6;
    public Button btnmachines6;
    public Button btnReports6;
    public Button btnLogOut;
    public Button btnDashBoard6;
    public Label lblTime1;
    public Label lblDate1;
    public TextField txtTime;
    public TextField txtDate;
    public TextField txtSearch;
    public Button btnRefresh;

    UseMachineBOimpl useMachineBOimpl = new UseMachineBOimpl();
    ObservableList<UsingMachinesTM> usingMachinesTMS = FXCollections.observableArrayList();


    public void btnEmployeesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("employeemanagementform.fxml",event,null);
    }

    public void btnOrdersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("ordermanagementform.fxml",event,null);
    }

    public void btnSuppliersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("suppliermanagementform.fxml",event,null);
    }

    public void btnBuyersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("buyersmanagementform.fxml",event,null);
    }

    public void btnmaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }

    public void btnMachinesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("machinemanagementform.fxml",event,null);
    }

    public void btnReportOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("dailyreportmanagementform.fxml",event,null);
    }

    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }

    public void btndashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }

    UseMachineDAO useMachineDAO = new UseMachineDAOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        getAllIds();
        getCellValueFactory();

        tblUseMachines.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });

        ObservableList<String> obList = FXCollections.observableArrayList("Daily System Details", "Add Employee Details", "Stock Details","Used Machine Details");
        comBox.setItems(obList);
    }

//Delete
    public void btnDeleteOnAction(ActionEvent event) {
        String code = txtMachineId.getText();
        try {
            boolean isDeleted = useMachineBOimpl.deleteUseMachines(code);
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


        if(btnSave.getText().equals("Save")) {

            if (txtMachineId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Machine Id").show();
            } else if (txtMaterialId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Material Id").show();
            } else if (txtEmployeeNic.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Employee Nic").show();
            } else {

                if (ValidateField.machineId(txtMachineId.getText())) {
                    System.out.println("yes5");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Machine Id (Ex-M001)").show();
                }

                if (ValidateField.materialId(txtMaterialId.getText())) {
                    System.out.println("yes6");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Material Id (Ex-MT001)").show();
                }

                if (ValidateField.Nic(txtEmployeeNic.getText())) {
                    System.out.println("yes7");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Correct Nic (Enter 10 Characters)").show();
                }


                if (ValidateField.machineId(txtMachineId.getText()) && ValidateField.materialId(txtMaterialId.getText()) && ValidateField.Nic(txtEmployeeNic.getText())) {
                    UseMachines um = new UseMachines(
                            txtMachineId.getText(),
                            txtMaterialId.getText(),
                            txtEmployeeNic.getText(),
                            lblDate1.getText(),
                            lblTime1.getText()
                    );

                    System.out.println("Yes");
                    try {
                        System.out.println(txtMachineId.getText());
                        System.out.println(txtMaterialId.getText());
                        System.out.println(txtEmployeeNic.getText());
                        System.out.println(lblDate1.getText());
                        System.out.println(lblTime1.getText());

                        boolean isSaved = useMachineBOimpl.saveUsemachines((UsingMachinesDTO) um);
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
        if(btnSave.getText().equals("Update")){
            UseMachines um = new UseMachines(
                    txtMachineId.getText(),
                    txtMaterialId.getText(),
                    txtEmployeeNic.getText(),
                    txtTime.getText(),
                    txtDate.getText()
            );
            try {
                boolean isUpdate = useMachineBOimpl.updateUsemachines((UsingMachinesDTO) um);
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
            ArrayList<String> list = useMachineDAO.getAllId();

            for (int i = 0; i < list.size(); i++) {
                getAll(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    void getAll(String id){

        try {
            UsingMachinesDTO usingMachinesDTO = useMachineBOimpl.getAllMachines(id);
            UsingMachinesTM usingMachinesTM = new UsingMachinesTM();
            usingMachinesTM.setMachineId(usingMachinesDTO.getMachineId());
            usingMachinesTM.setMaterialId(usingMachinesDTO.getMaterialId());
            usingMachinesTM.setEmployeeNic(usingMachinesDTO.getEmployeeNic());
            usingMachinesTM.setTime(usingMachinesDTO.getTime());
            usingMachinesTM.setDate(usingMachinesDTO.getDate());
            usingMachinesTMS.add(usingMachinesTM);
            tblUseMachines.setItems(usingMachinesTMS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    void getCellValueFactory(){

        colMachineId.setCellValueFactory(new PropertyValueFactory("MachineId"));
        colMaterialId.setCellValueFactory(new PropertyValueFactory("MaterialId"));
        colEmployeeNic.setCellValueFactory(new PropertyValueFactory("EmployeeNic"));
        colDate.setCellValueFactory(new PropertyValueFactory("Date"));
        colTime.setCellValueFactory(new PropertyValueFactory("Time"));

        tblUseMachines.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });
    }

    private void setData(UsingMachinesTM tm) {
        btnSave.setText("Update");
        txtDate.setDisable(false);
        txtTime.setDisable(false);
        txtMachineId.setText(tm.getMachineId());
        txtMaterialId.setText(tm.getMaterialId());
        txtEmployeeNic.setText(tm.getEmployeeNic());
        txtDate.setText(tm.getDate());
        txtTime.setText(tm.getTime());
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
                    lblDate1.setText(timenow1);
                });
            }
        });
        thread.start();
    }

    public void btnSearchOnAction(ActionEvent event) {
        btnSave.setText("Update");
        try {
            UseMachines usingMachines =  useMachineBOimpl.searchUseMachines(txtSearch.getText());

            txtMachineId.setText(usingMachines.getMachineId());
            txtMaterialId.setText(usingMachines.getMaterialId());
            txtEmployeeNic.setText(usingMachines.getEmployeeNic());
            txtDate.setText(usingMachines.getDate());
            txtTime.setText(usingMachines.getTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("usingmachinesreports.fxml",event,null);
    }

    public void btnNewOnAction(ActionEvent event) {
        btnSave.setText("Save");
        txtMachineId.setText("");
        txtEmployeeNic.setText("");
        txtMaterialId.setText("");
        txtTime.setText("");
        txtDate.setText("");
        txtDate.setDisable(true);
        txtTime.setDisable(true);
    }

    public void generrateReportOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/Reports/UseMachines.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBconnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
