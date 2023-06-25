package lk.ijse.controller;

import javafx.collections.FXCollections;
//import lk.ijse.Model.Materials;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.MaterialBO;
import lk.ijse.bo.custom.impl.MaterialBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.MaterialDAO;
import lk.ijse.dao.custom.impl.MaterialDAOimpl;
import lk.ijse.dto.MaterialsDTO;
import lk.ijse.entity.Material;
import lk.ijse.tdm.MaterialTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MaterialManagementFormController implements Initializable {


    public TableView <MaterialTM>tblMaterials;
    public TableColumn colSupplyCountry;
    public TableColumn colMaterialId;
    public TableColumn colMaterialName;
    public TableColumn colQuality;
    public TableColumn colDate;
    public TableColumn colTime;

    public TextField txtMaterialId;
    public TextField txtMaterialName;
    public TextField txtSupplierCountry;
    public TextField txtQuality;
    public TextField txtSupplydate;
    public TextField txtTime;

    public Button btnDelete;
    public Button btnsave;
    public Button btnEmployee5;
    public Button btnOrders5;
    public Button btnSuppliers5;
    public Button btnBuyers5;
    public Button btnMaterials5;
    public Button btnMachines5;
    public Button btnReports5;
    public Button btnLogOut;
    public Button btndashBoard3;
    public Label txtTime1;
    public Label txtDate;
    public Button btnSearch;
    public TextField txtSearch;
    public Button btnRefresh;

    MaterialBOimpl materialBOimpl = new MaterialBOimpl();
    ObservableList<MaterialTM> materialTMS = FXCollections.observableArrayList();

    public void btnEmployeeOnAction(ActionEvent event) throws IOException {
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

    public void btnDashBoardOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxx");
        Navigation.navigation("dashboardform.fxml",event,null);
    }
    MaterialDAO materialDAO = new MaterialDAOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timenow();
        getAllIds();
        getCellValueFactory();

        tblMaterials.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            setData(newValue);
        });
    }

    private void setData(MaterialTM tm) {
        btnsave.setText("Update");
        txtSupplydate.setDisable(false);
        txtTime.setDisable(false);
        txtMaterialId.setText(tm.getMaterialId());
        txtMaterialName.setText(tm.getMaterialname());
        txtSupplierCountry.setText(tm.getSupplyCountry());
        txtQuality.setText(tm.getQuality());
        txtSupplydate.setText(tm.getSupplyDate());
        txtTime.setText(tm.getSupplyTime());
    }

//Delete
    public void btnDeleteOnAction(ActionEvent event) {
        String code = txtMaterialId.getText();
        try {
            boolean isDeleted = materialBOimpl.deleteMaterials(code);
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

            if (txtMaterialId.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Material Id").show();
            } else if (txtMaterialName.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Material Name").show();
            } else if (txtSupplierCountry.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Spplier's Country").show();
            } else if (txtQuality.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Quality").show();
            } else {

                if (ValidateField.materialId(txtMaterialId.getText())) {
                    Material mm = new Material(
                            txtMaterialId.getText(),
                            txtMaterialName.getText(),
                            txtSupplierCountry.getText(),
                            txtQuality.getText(),
                            txtDate.getText(),
                            txtTime1.getText()
                    );
                    try {
                        boolean isSaved = materialBOimpl.saveMaterials(mm);
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
                    new Alert(Alert.AlertType.ERROR, "Please Enter Valid MaterialId (Ex-MT001)").show();
                }
            }
        }
//Update
        if (btnsave.getText().equals("Update")) {
            Material mt = new Material(
                    txtMaterialId.getText(),
                    txtMaterialName.getText(),
                    txtSupplierCountry.getText(),
                    txtQuality.getText(),
                    txtSupplydate.getText(),
                    txtTime.getText()
            );
            try {
                boolean isUpdate = materialBOimpl.updateMaterials((MaterialsDTO) mt);
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
            ArrayList<String> list = materialDAO.getAllId();

            for (int i = 0; i < list.size(); i++) {
                getAll(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    void getAll(String id){
        try {
            MaterialsDTO materialsDTO = materialBOimpl.getAllMaterials(id);
            MaterialTM materialTM = new MaterialTM();
            materialTM.setMaterialId(materialsDTO.getMaterialId());
            materialTM.setMaterialname(materialsDTO.getMaterialname());
            materialTM.setSupplyCountry(materialsDTO.getSupplyCountry());
            materialTM.setQuality(materialsDTO.getQuality());
            materialTM.setSupplyDate(materialsDTO.getSupplyDate());
            materialTM.setSupplyTime(materialsDTO.getSupplyTime());
//            System.out.println("Buyers id "+buyersDTO.getBuyerId());
            materialTMS.add(materialTM);
            tblMaterials.setItems(materialTMS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void getCellValueFactory(){

        colMaterialId.setCellValueFactory(new PropertyValueFactory("MaterialId"));
        colMaterialName.setCellValueFactory(new PropertyValueFactory("Materialname"));
        colSupplyCountry.setCellValueFactory(new PropertyValueFactory("SupplyCountry"));
        colQuality.setCellValueFactory(new PropertyValueFactory("Quality"));
        colDate.setCellValueFactory(new PropertyValueFactory("SupplyDate"));
        colTime.setCellValueFactory(new PropertyValueFactory("SupplyTime"));

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
                    txtDate.setText(timenow1);
                });
            }
        });
        thread.start();
    }

    public void btnSearchOnAction(ActionEvent event) {
        btnsave.setText("Update");
        try {
            Material materials = (Material) materialBOimpl.searchmaterials(txtSearch.getText());

            txtMaterialId.setText(materials.getMaterialId());
            txtMaterialName.setText(materials.getMaterialname());
            txtSupplierCountry.setText(materials.getSupplyCountry());
            txtQuality.setText(materials.getQuality());
            txtSupplydate.setText(materials.getSupplyDate());
            txtTime.setText(materials.getSupplyTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnRefreshOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("materialmanagementform.fxml",event,null);
    }

    public void btnNewOnAction(ActionEvent event) {
        btnsave.setText("Save");
        txtMaterialId.setText("");
        txtMaterialName.setText("");
        txtSupplierCountry.setText("");
        txtQuality.setText("");
        txtSupplydate.setText("");
        txtTime.setText("");
        txtTime.setDisable(true);
        txtSupplydate.setDisable(true);
    }
}
