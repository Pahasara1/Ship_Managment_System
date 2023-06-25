package lk.ijse.controller;

//import lk.ijse.view.tdm.assets.images.*;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.AdminReportsBO;
import lk.ijse.bo.custom.impl.AdminReportsBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.AdminReportsDAO;
import lk.ijse.dao.custom.impl.AdminReportsDAOimpl;
import lk.ijse.dto.AdminReportsDTO;
import lk.ijse.db.DBconnection;
import lk.ijse.entity.AdminReports;
import lk.ijse.tdm.AdminReportsTM;
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


public class AdminReportsController implements Initializable {

    public TableView<AdminReportsTM> tblAdminReports;
    public TableColumn colReportId;
    public TableColumn colAdminNic;
    public TableColumn colEmployeeNic;
    public TableColumn colTime;
    public TableColumn colDate;

    public TextField txtAdminNic;
    public TextField txtReportId;
    public TextField txtEmployeeNic;

    public Button btnDelete;
    public Button btnSave;
    public ComboBox comBox;
    public Label lblTime;
    public Label lblDate;
    public Button btnEmployees6;
    public Button btnOrders6;
    public Button btnSuppliers6;
    public Button btnBuyers6;
    public Button btnmaterials6;
    public Button btnmachines6;
    public Button btnReports6;
    public Button btnLogOut;
    public Button btnDashBoard6;
    public TextField txtTime;
    public TextField txtdate;
    public Button btnSearch;
    public TextField txtSearch;
    public Button btnRefresh;
    public ImageView pngCorrect;

    AdminReportsDAO adminReportsDAO = new AdminReportsDAOimpl();
    AdminReportsBO adminReportsBO = new AdminReportsBOimpl();
    AdminReportsDAOimpl adminReportsDAOimpl = new AdminReportsDAOimpl();
    AdminReportsBOimpl adminReportsBOimpl = new AdminReportsBOimpl();

    ObservableList<AdminReportsTM> adminReportsTMS = FXCollections.observableArrayList();


    public void btnEmployeesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("employeemanagementform.fxml", event, null);
    }

    public void btnOrdersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("ordermanagementform.fxml", event, null);
    }

    public void btnSuppliersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("suppliermanagementform.fxml", event, null);
    }

    public void btnBuyersOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("buyersmanagementform.fxml", event, null);
    }

    public void btnmaterialsOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("materialmanagementform.fxml", event, null);
    }

    public void btnMachinesOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("machinemanagementform.fxml", event, null);
    }

    public void btnReportOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("dailyreportmanagementform.fxml", event, null);
    }

    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml", event, null);
    }

    public void btndashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml", event, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
//        getAll();
        getAllIds();
        getCellValueFactory();

        tblAdminReports.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });

        ObservableList<String> obList = FXCollections.observableArrayList("Daily System Details", "Add Employee Details", "Stock Details", "Used Machine Details");
        comBox.setItems(obList);
    }

    private void setData(AdminReportsTM tm) {
        btnSave.setText("Update");
        txtTime.setDisable(false);
        txtdate.setDisable(false);
        txtReportId.setText(tm.getReportId());
        txtAdminNic.setText(tm.getAdminNic());
        txtEmployeeNic.setText(tm.getEmployeeNic());
        txtTime.setText(tm.getTime());
        txtdate.setText(tm.getDate());
    }

    //Delete
    public void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        String code = txtReportId.getText();
        try {
            boolean isDeleted = adminReportsBOimpl.deleteReports(code);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    //Save
    public void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        if (btnSave.getText().equals("Save")) {

            if (txtReportId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter ReportId").show();
            } else if (txtAdminNic.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter AdminNic").show();
            } else if (txtEmployeeNic.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter EmployeeNic").show();
            } else {

                if (ValidateField.ReportId(txtReportId.getText())) {
                    pngCorrect.setVisible(true);
                    System.out.println("Yes8");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Report Id (Ex- R001)").show();
                }

                if (ValidateField.Nic(txtAdminNic.getText())) {
                    System.out.println("Yes8");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Your Valid Nic (Minimum 10 Characters)").show();
                }

                if (ValidateField.Nic(txtEmployeeNic.getText())) {
                    System.out.println("Yes8");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid Employee Nic (Minimum 10 Characters)").show();
                }

                if (ValidateField.ReportId(txtReportId.getText()) && ValidateField.Nic(txtAdminNic.getText()) && ValidateField.Nic(txtEmployeeNic.getText())) {
                    AdminReports ar = new AdminReports(
                            txtReportId.getText(),
                            txtAdminNic.getText(),
                            txtEmployeeNic.getText(),
                            lblTime.getText(),
                            lblDate.getText()
                    );
                    try {
                        boolean isSaved = adminReportsBOimpl.saveReports(ar);
                        if (isSaved) {
                            System.out.println("saved");
                            new Alert(Alert.AlertType.CONFIRMATION, "Saved...!").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Unsaved...!").show();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
//Update
        if (btnSave.getText().equals("Update")) {
            AdminReports ar = new AdminReports(
                    txtReportId.getText(),
                    txtAdminNic.getText(),
                    txtEmployeeNic.getText(),
                    txtTime.getText(),
                    txtdate.getText()
            );
            try {
                boolean isUpdate = adminReportsBOimpl.updateReports(ar);
                if (isUpdate) {
                    System.out.println("saved");
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Try Again...!").show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void getAllIds() {
        try {
            ArrayList<String> list = adminReportsDAO.getAllId();

            for (int i = 0; i < list.size(); i++) {
                getAll(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    void getAll(String id) {
        try {

            AdminReportsDTO adminReportsDTO = adminReportsBOimpl.getAllReports(id);
            AdminReportsTM adminReportsTM = new AdminReportsTM();
            adminReportsTM.setReportId(adminReportsDTO.getReportId());
            adminReportsTM.setAdminNic(adminReportsDTO.getAdminNic());
            adminReportsTM.setEmployeeNic(adminReportsDTO.getEmployeeNic());
            adminReportsTM.setTime(adminReportsDTO.getTime());
            adminReportsTM.setDate(adminReportsDTO.getDate());
            adminReportsTMS.add(adminReportsTM);
            tblAdminReports.setItems(adminReportsTMS);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }



    void getCellValueFactory() {

        colReportId.setCellValueFactory(new PropertyValueFactory("ReportId"));
        colAdminNic.setCellValueFactory(new PropertyValueFactory("AdminNic"));
        colEmployeeNic.setCellValueFactory(new PropertyValueFactory("EmployeeNic"));
        colDate.setCellValueFactory(new PropertyValueFactory("Time"));
        colTime.setCellValueFactory(new PropertyValueFactory("Date"));

    }

    public void comBoxOnAction(ActionEvent event) throws IOException {
        if (comBox.getValue().equals("Daily System Details")) {
            Navigation.navigation("dailyreportmanagementform.fxml", event, null);
        } else if (comBox.getValue().equals("Add Employee Details")) {
            Navigation.navigation("adminreports.fxml", event, null);
        } else if (comBox.getValue().equals("Stock Details")) {
            Navigation.navigation("stockreport.fxml", event, null);
        } else if (comBox.getValue().equals("Used Machine Details")) {
            Navigation.navigation("usingmachinesreports.fxml", event, null);
        }
    }

    private void Timenow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM,  dd, yyyy");
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                String timenow1 = sdf1.format(new Date());

                Platform.runLater(() -> {
                    lblTime.setText(timenow);
                    lblDate.setText(timenow1);
                });
            }
        });
        thread.start();
    }

    public void btnSearchOnAction(ActionEvent event) throws ClassNotFoundException {

        btnSave.setText("Update");
        try {
            AdminReports adminReports = (AdminReports) adminReportsBOimpl.searchReports(txtSearch.getText());

            txtReportId.setText(adminReports.getReportId());
            txtAdminNic.setText(adminReports.getAdminNic());
            txtEmployeeNic.setText(adminReports.getEmployeeNic());
            txtTime.setText(adminReports.getTime());
            txtdate.setText(adminReports.getDate());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("adminreports.fxml", event, null);
    }

    public void btnNewOnAction(ActionEvent event) {
        btnSave.setText("Save");
        txtReportId.setText("");
        txtAdminNic.setText("");
        txtEmployeeNic.setText("");
        txtdate.setText("");
        txtTime.setText("");
        txtTime.setDisable(true);
        txtdate.setDisable(true);
    }

    public void generateReportOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/Reports/AdminReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBconnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
