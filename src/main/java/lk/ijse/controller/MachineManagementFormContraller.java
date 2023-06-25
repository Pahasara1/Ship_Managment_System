package lk.ijse.controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
//import lk.ijse.Model.Machine;
import lk.ijse.bo.custom.MachineBO;
import lk.ijse.bo.custom.impl.MachineBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.MachineDAO;
import lk.ijse.dao.custom.impl.MachineDAOimpl;
import lk.ijse.dto.MachineDTO;
import lk.ijse.entity.Machine;
import lk.ijse.tdm.MachineTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MachineManagementFormContraller implements Initializable {

    public TableView <MachineTM>tblMachine;
    public TableColumn colMachineId;
    public TableColumn colMachineName;
    public TableColumn colQuality;
    public TableColumn colRepairCount;
    public TableColumn colDate;
    public TableColumn colTime;
    public TextField txtMachineId;
    public TextField txtMachineName;
    public TextField txtQuality;
    public TextField txtRepairCount;
    public TextField txtDate;
    public TextField txtTime;

    public Button btnDelete;
    public Button btnsave;
    public Button btnEmployees4;
    public Button btnOrders4;
    public Button btnSupppliers4;
    public Button btnBuyers4;
    public Button btnMaterials4;
    public Button btnMachines4;
    public Button btnReports4;
    public Button btnLogOut;
    public Button btnDashBoard2;
    public Label txtTime1;
    public Label txtDate1;
    public Button btnSearch;
    public TextField txtSearch;
    public Button btnRefresh;

    MachineBOimpl machineBOimpl = new MachineBOimpl();
    ObservableList<MachineTM> machineTMS = FXCollections.observableArrayList();

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

    public void btnMaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }

    public void btnMachinesOnAction(ActionEvent event) throws IOException {
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

    public void btnDashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }
    MachineDAO machineDAO = new MachineDAOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        getAllIds();
        getCellValueFactory();

        tblMachine.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });
    }

    private void setData(MachineTM tm) {
        btnsave.setText("Update");
        txtDate.setDisable(false);
        txtTime.setDisable(false);
        txtMachineId.setText(tm.getMachineId());
        txtMachineName.setText(tm.getMachineName());
        txtQuality.setText(tm.getQuality());
        txtRepairCount.setText(String.valueOf(tm.getRepairCount()));
        txtDate.setText(tm.getDate());
        txtTime.setText(tm.getTime());
    }

//Delete
    public void btnDeleteOnAction(ActionEvent event) {
        String code = txtMachineId.getText();
        try {
            boolean isDeleted = machineBOimpl.deleteMachines(code);
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

            if (txtMachineId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Machine Id").show();
            } else if (txtMachineName.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Machine Name").show();
            } else if (txtQuality.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Quality").show();
            } else if (txtRepairCount.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Repair Count").show();
            } else {

                if (ValidateField.machineId(txtMachineId.getText())) {
                    Machine m = new Machine(
                            txtMachineId.getText(),
                            txtMachineName.getText(),
                            txtQuality.getText(),
                            txtRepairCount.getText(),
                            txtDate1.getText(),
                            txtTime1.getText()
                    );
                    try {
                        System.out.println("Try");
                        boolean isSaved = machineBOimpl.saveMachines(m);
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
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Machine Id (Ex- M001)").show();
                }
            }
        }
//Update
        if (btnsave.getText().equals("Update")) {
            Machine machine = new Machine(
                    txtMachineId.getText(),
                    txtMachineName.getText(),
                    txtQuality.getText(),
                    txtRepairCount.getText(),
                    txtDate.getText(),
                    txtTime.getText()
            );
            try {
                boolean isUpdate = machineBOimpl.updateMachines(machine);
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
            ArrayList<String> list = machineDAO.getAllId();

            for (int i = 0; i < list.size(); i++) {
                getAll(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    void getAll(String id){
        try {
            MachineDTO machineDTO = machineBOimpl.getAllMachines(id);
            MachineTM machineTM = new MachineTM();
            machineTM.setMachineId(machineDTO.getMachineId());
            machineTM.setMachineName(machineDTO.getMachineName());
            machineTM.setQuality(machineDTO.getQuality());
            machineTM.setRepairCount(machineDTO.getRepairCount());
            machineTM.setDate(machineDTO.getDate());
            machineTM.setTime(machineDTO.getTime());
//            System.out.println("Buyers id "+buyersDTO.getBuyerId());
            machineTMS.add(machineTM);
            tblMachine.setItems(machineTMS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void getCellValueFactory(){

        colMachineId.setCellValueFactory(new PropertyValueFactory("MachineId"));
        colMachineName.setCellValueFactory(new PropertyValueFactory("MachineName"));
        colQuality.setCellValueFactory(new PropertyValueFactory("Quality"));
        colRepairCount.setCellValueFactory(new PropertyValueFactory("RepairCount"));
        colDate.setCellValueFactory(new PropertyValueFactory("Date"));
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
            Machine machine = (Machine) machineBOimpl.searchMachines(txtSearch.getText());

            txtMachineId.setText(machine.getMachineId());
            txtMachineName.setText(machine.getMachineName());
            txtQuality.setText(machine.getQuality());
            txtRepairCount.setText(String.valueOf(machine.getRepairCount()));
            txtDate.setText(machine.getDate());
            txtTime.setText(machine.getTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("machinemanagementform.fxml",event,null);
    }

    public void btnNewOnAction(ActionEvent event) {
        btnsave.setText("Save");
        txtMachineId.setText("");
        txtMachineName.setText("");
        txtQuality.setText("");
        txtRepairCount.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtDate.setDisable(true);
        txtTime.setDisable(true);
    }
}
