package lk.ijse.controller;

import javafx.collections.FXCollections;
//import lk.ijse.Model.Employees;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.EmployeesBO;
import lk.ijse.bo.custom.impl.EmployeesBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.EmployeesDAO;
import lk.ijse.dao.custom.impl.EmployeesDAOimpl;
import lk.ijse.dto.EmployeesDTO;
import lk.ijse.entity.Employees;
import lk.ijse.tdm.EmployeesTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class EmployeeManagementFormController implements Initializable {


    public TableView <EmployeesTM>tblEmployees;
    public TableColumn colEmployeeId;
    public TableColumn colEmployeeName;
    public TableColumn colPossision;
    public TableColumn colNicNumber;
    public TableColumn colAddDate;
    public TableColumn colTime;

    public TextField txtEmployeeId;
    public TextField txtEmployeeName;
    public TextField txtPossision;
    public TextField txtNicNumber;
    public TextField txtAddDate;
    public TextField txtTime;

    public Button btnDelete;
    public Button btnsave;
    public Button btnEmployee3;
    public Button btnOrders3;
    public Button btnSuppliers3;
    public Button btnBuyers3;
    public Button btnMaterials3;
    public Button btnmachines3;
    public Button btnReports3;
    public Button btnLogOut;
    public Button btnDashBoard3;
    public Label txtDate1;
    public Label txtTime1;
    public Button btnSearch;
    public TextField txtSearch;
    public Button btnRefresh;
    ObservableList <EmployeesTM> employeesTMS = FXCollections.observableArrayList();

    EmployeesBOimpl employeesBOimpl = new EmployeesBOimpl();


    public void btnEmployeeOnAction(ActionEvent event) throws IOException {
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

    public void btnMaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }

    public void btnMachinesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("machinemanagementform.fxml",event,null);
    }

    public void btnReportsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("dailyreportmanagementform.fxml",event,null);
    }

    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }

    public void btnDashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }

    EmployeesDAO employeesDAO = new EmployeesDAOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        getAllIds();
        getCellValueFactory();
//        tblEmployees.setItems(employeesTMS);
        tblEmployees.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });
    }

    private void setData(EmployeesTM tm) {
        btnsave.setText("Update");
        txtAddDate.setDisable(false);
        txtTime.setDisable(false);
        txtEmployeeId.setText(tm.getId());
        txtEmployeeName.setText(tm.getName());
        txtPossision.setText(tm.getPossision());
        txtNicNumber.setText(tm.getNicNumber());
        txtAddDate.setText(tm.getAddDate());
        txtTime.setText(tm.getTime());
    }

//Delete
    public void btnDeleteOnAction(ActionEvent event) {
        String code = txtEmployeeId.getText();
        try {
            boolean isDeleted = employeesBOimpl.deleteEmployees(code);
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

            if (txtEmployeeId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Employee Id").show();
            } else if (txtEmployeeName.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Employee Name").show();
            } else if (txtPossision.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Position").show();
            } else if (txtNicNumber.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR,"Please Enter Employee Nic Number").show();
            } else {

                if (ValidateField.EmployeeId(txtEmployeeId.getText())) {
                    System.out.println("Yes");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Input Correct Id (Ex-E001)").show();
                }

                if (ValidateField.Employeename(txtEmployeeName.getText())) {
                    System.out.println("Yes1");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Input Complete Name").show();
                }

                if (ValidateField.Nic(txtNicNumber.getText())) {
                    System.out.println("yes2");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Nic (Minimum 10 Characters)").show();
                }

                if (ValidateField.EmployeeId(txtEmployeeId.getText()) && ValidateField.Nic(txtNicNumber.getText()) && ValidateField.Employeename(txtEmployeeName.getText())) {
                    Employees em = new Employees(
                            txtEmployeeId.getText(),
                            txtEmployeeName.getText(),
                            txtPossision.getText(),
                            txtNicNumber.getText(),
                            txtDate1.getText(),
                            txtTime1.getText()
                    );
                    try {
                        boolean isSaved = employeesBOimpl.saveEmployees(em);
                        if (isSaved) {
                            System.out.println("saved");
                            new Alert(Alert.AlertType.CONFIRMATION, "Saved...!").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Unsaved...!").show();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();    EmployeesBOimpl employeesBOimpl = new EmployeesBOimpl();

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
//Update
        if (btnsave.getText().equals("Update")) {
            Employees emp = new Employees(
                    txtEmployeeId.getText(),
                    txtEmployeeName.getText(),
                    txtPossision.getText(),
                    txtNicNumber.getText(),
                    txtAddDate.getText(),
                    txtTime.getText()
            );
            try {
                boolean isUpdate = employeesBOimpl.updateEmployees(emp);
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

            EmployeesDTO employeesDTO = employeesBOimpl.getAllEmployees(id);
            EmployeesTM employeesTM = new EmployeesTM();
            employeesTM.setId(employeesDTO.getId());
            employeesTM.setName(employeesDTO.getName());
            employeesTM.setPossision(employeesDTO.getPossision());
            employeesTM.setNicNumber(employeesDTO.getNicNumber());
            employeesTM.setAddDate(employeesDTO.getAddDate());
            employeesTM.setTime(employeesDTO.getTime());
            System.out.println("Employee id "+employeesDTO.getId());
            employeesTMS.add(employeesTM);
            tblEmployees.setItems(employeesTMS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void getAllIds() {
        try {
            ArrayList<String> list = employeesDAO.getAllId();

            for (int i = 0; i < list.size(); i++) {
                getAll(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    void getCellValueFactory(){
        colEmployeeId.setCellValueFactory(new PropertyValueFactory("Id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory("Name"));
        colPossision.setCellValueFactory(new PropertyValueFactory("Possision"));
        colNicNumber.setCellValueFactory(new PropertyValueFactory("NicNumber"));
        colAddDate.setCellValueFactory(new PropertyValueFactory("AddDate"));
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
            Employees employees = (Employees) employeesBOimpl.searchEmployees(txtSearch.getText());

            txtEmployeeId.setText(employees.getId());
            txtEmployeeName.setText(employees.getName());
            txtPossision.setText(employees.getPossision());
            txtNicNumber.setText(employees.getNicNumber());
            txtAddDate.setText(employees.getAddDate());
            txtTime.setText(employees.getTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("employeemanagementform.fxml",event,null);
    }

    public void btnNewOnAction(ActionEvent event) {
        btnsave.setText("Save");
        txtEmployeeId.setText("");
        txtEmployeeName.setText("");
        txtPossision.setText("");
        txtNicNumber.setText("");
        txtAddDate.setText("");
        txtTime.setText("");
        txtAddDate.setDisable(true);
        txtTime.setDisable(true);
    }
}
