package lk.ijse.controller;


import lk.ijse.entity.DailyReports;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.DailyReportsBO;
import lk.ijse.bo.custom.impl.DailyreportsBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.DailyReportsDAO;
import lk.ijse.dao.custom.impl.DailyReportsDAOimpl;
import lk.ijse.dto.DailyReportsDTO;
import lk.ijse.db.DBconnection;
import lk.ijse.tdm.DailyReportsTM;
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


public class DailyReportManagementFormController implements Initializable {


    DailyreportsBOimpl dailyreportsBOimpl = new DailyreportsBOimpl();

    public Label lblDate;
    public Label lblTime;

    public TableView <DailyReportsTM>tblReports;
    public TableColumn colReportId;
    public TableColumn colEmployeeNic;
    public TableColumn colTemperature;
    public TableColumn colMachineRunning;

    public TextField txtReportId;
    public TextField txtNic;
    public TextField txtTemperature;
    public TextField txtOther;
    public ComboBox comBox;

    public Button btnsave;
    public Button btnDelete;
    public Button btnEmployee2;
    public Button btnOrders2;
    public Button btnSuppliers2;
    public Button btnBuyers2;
    public Button btnMaterials2;
    public Button btnMachines2;
    public Button btnReports2;
    public Button btnLogOut;
    public Button btnDashBoard;
    public TextField txtSearch;
    public Button btnSearch;
    public Button btnRefresh;

    ObservableList<DailyReportsTM> dailyReportsTMS = FXCollections.observableArrayList();

    public void btnEmployeeOnAction(ActionEvent event) throws IOException {
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

    public void buyersOnACtion(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("buyersmanagementform.fxml",event,null);
    }

    public void btnMaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }

    public void btnMachinesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("machinemanagementform.fxml",event,null);
    }

    public void btnDailyReportsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("dailyreportmanagementform.fxml",event,null);
    }

    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }

    public void btnDashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }

    DailyReportsDAO dailyReportsDAO = new DailyReportsDAOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        getAllIds();
        getCellValueFactory();

        ObservableList<String> obList = FXCollections.observableArrayList("Daily System Details", "Add Employee Details", "Stock Details","Used Machine Details");
        comBox.setItems(obList);

        tblReports.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });
    }

    private void setData(DailyReportsTM tm) {
        btnsave.setText("Update");
        txtReportId.setText(tm.getReportId());
        txtNic.setText(tm.getEmployeeNic());
        txtTemperature.setText(tm.getTemperatureLevel());
        txtOther.setText(tm.getTemperatureLevel());
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

//Save
    public void txtBtnSaveOnAction(ActionEvent event) {
        if (btnsave.getText().equals("Save")) {

            if (txtReportId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Report Id").show();
            } else if (txtNic.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Nic").show();
            } else if (txtTemperature.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Temperature Level").show();
            } else if (txtOther.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Machine Running Pressure").show();
            } else {

                if (ValidateField.ReportId(txtReportId.getText())) {
                    System.out.println("Yes3");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Report Id (Ex- R001)").show();
                }

                if (ValidateField.Nic(txtNic.getText())) {
                    System.out.println("Yes4");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Nic (Minimum 10 Characters)").show();
                }

                if (ValidateField.ReportId(txtReportId.getText()) && ValidateField.Nic(txtNic.getText())) {
                    DailyReports dr = new DailyReports(
                            txtReportId.getText(),
                            txtNic.getText(),
                            txtTemperature.getText(),
                            txtOther.getText()
                    );
                    try {
                        boolean isSaved = dailyreportsBOimpl.saveReports((DailyReportsDTO) dr);
                        if (isSaved) {
                            System.out.println("saved");
                            new Alert(Alert.AlertType.CONFIRMATION, "Saved...!").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Saved...!").show();
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
            DailyReports dr = new DailyReports(
                    txtReportId.getText(),
                    txtNic.getText(),
                    txtTemperature.getText(),
                    txtOther.getText()
            );
            try {
                boolean isUpdate = dailyReportsDAO.update(dr);
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

    void getAll(String id){

        try {

            DailyReportsDTO dailyReportsDTO = dailyreportsBOimpl.getAllReports(id);
            DailyReportsTM dailyReportsTM = new DailyReportsTM();
            dailyReportsTM.setReportId(dailyReportsDTO.getReportId());
            dailyReportsTM.setEmployeeNic(dailyReportsDTO.getEmployeeNic());
            dailyReportsTM.setTemperatureLevel(dailyReportsDTO.getTemperatureLevel());
            dailyReportsTM.setMachineRunning(dailyReportsDTO.getMachineRunning());
            System.out.println("Daily Report id "+dailyReportsDTO.getReportId());
            dailyReportsTMS.add(dailyReportsTM);
            tblReports.setItems(dailyReportsTMS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getAllIds() {
        try {
            ArrayList<String> list = dailyReportsDAO.getAllId();

            for (int i = 0; i < list.size(); i++) {
                getAll(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory(){

        colReportId.setCellValueFactory(new PropertyValueFactory("ReportId"));
        colEmployeeNic.setCellValueFactory(new PropertyValueFactory("EmployeeNic"));
        colTemperature.setCellValueFactory(new PropertyValueFactory("TemperatureLevel"));
        colMachineRunning.setCellValueFactory(new PropertyValueFactory("MachineRunning"));

    }

//Delete
    public void btnDeleteOnAction(ActionEvent event) {

        String code = txtReportId.getText();
        try {
            boolean isDeleted = dailyreportsBOimpl.deleteReports(code);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSearchOnAction(ActionEvent event) {
        btnsave.setText("Update");
        try {
            DailyReports dailyReports = dailyreportsBOimpl.searchReports(txtSearch.getText());

            txtReportId.setText(dailyReports.getReportId());
            txtNic.setText(dailyReports.getEmployeeNic());
            txtTemperature.setText(dailyReports.getTemperatureLevel());
            txtOther.setText(dailyReports.getMachineRunning());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
                    lblTime.setText(timenow);
                    lblDate.setText(timenow1);
                });
            }
        });
        thread.start();
    }


    public void tbtnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("dailyreportmanagementform.fxml",event,null);
    }

    public void btnNewOnAction(ActionEvent event) {
        btnsave.setText("Save");
        txtReportId.setText("");
        txtNic.setText("");
        txtTemperature.setText("");
        txtOther.setText("");
    }

    public void btngeneratereportOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/Reports/SystemReports.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBconnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
