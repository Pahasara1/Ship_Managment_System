package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.bo.custom.impl.RegisterBOimpl;
import lk.ijse.controller.util.Navigation;
import lk.ijse.controller.util.ValidateField;
import lk.ijse.dao.custom.RegisterDAO;
import lk.ijse.dao.custom.impl.RegisterDAOimpl;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.entity.Register;
import lk.ijse.tdm.RegisterTM;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterFormController {
    public Button btnRegister;
    public TextField txtName;
    public TextField txtPossision;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtContact;
    public Button btnLogin;
    public PasswordField txtPassword;
    public PasswordField txtPasswordConfirm;
    public CheckBox rememberMe;
    public TextField txtNicCode;
    public static AnchorPane registerPane;


    RegisterBOimpl registerBOimpl = new RegisterBOimpl();
    RegisterDAO registerDAO = new RegisterDAOimpl();

    ObservableList<RegisterTM> registerTMS = FXCollections.observableArrayList();


    public static String genarateCode(){
        int randomNo=(int)(Math.random()*9000)+1000;
        String Code=String.valueOf(randomNo);
        return Code;
    }

    @FXML
    public void btnRegisteOnAction(ActionEvent event) throws IOException {

        if(ValidateField.emailCheck(txtEmail.getText())) {
            System.out.println("Yes");
        }else{
            new Alert(Alert.AlertType.ERROR,"Please Input Correct Email").show();
            btnRegister.setDisable(true);
        }

        if(ValidateField.Nic(txtNic.getText())){
            System.out.println("yes");
        }else{
            new Alert(Alert.AlertType.ERROR,"Please Input Correct Nic").show();
            btnRegister.setDisable(true);
        }

        if(ValidateField.PhoneNumber(txtContact.getText())){
            System.out.println("yes");
        }else{
            new Alert(Alert.AlertType.ERROR,"Please Input Correct Contact").show();
            btnRegister.setDisable(true);
        }


        if(ValidateField.emailCheck(txtEmail.getText()) && ValidateField.Nic(txtNic.getText()) && ValidateField.PhoneNumber(txtContact.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"Correct Details Inputs").show();
            btnRegister.setDisable(false);
            Register r = new Register(
                    txtName.getText(),
                    txtPossision.getText(),
                    txtNic.getText(),
                    txtEmail.getText(),
                    txtPassword.getText(),
                    txtPasswordConfirm.getText(),
                    txtContact.getText(),
                    txtNicCode.getText()
            );
            try {
                boolean isSaved= registerDAO.save(r);
                if(isSaved){
                    System.out.println("saved");
                    new Alert(Alert.AlertType.CONFIRMATION,"Registration Succses...!").show();
                }else{
                    new  Alert(Alert.AlertType.ERROR,"Registration Failed...!").show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("xxxxxxxxxxxxxxx");
            Navigation.navigation("loginpage.fxml",event,null);
        }else{
            new Alert(Alert.AlertType.ERROR,"Incorrect Inputs").show();
            btnRegister.setDisable(true);
        }

    }

    public void btnLoginOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }


    public void rememberMeOnAction(ActionEvent event) {
        if(txtPassword.getText().equals(txtPasswordConfirm.getText())){
            btnRegister.setVisible(true);
            if(txtPassword.getText().isEmpty() && txtPasswordConfirm.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "UnSuccesed Password").show();
            }else if(txtPassword.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "UnSuccesed Password").show();
            }else if(txtPasswordConfirm.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "UnSuccesed Password").show();
            }
        }else{
            btnRegister.setVisible(false);
            new Alert(Alert.AlertType.ERROR,"Please ReEnter Password").show();
        }


        if(txtNic.getText().isEmpty() && txtEmail.getText().isEmpty() && txtPassword.getText().isEmpty() && txtPasswordConfirm.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Details").show();
        } else if(txtNic.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Nic Number").show();
        }else if(txtEmail.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Valid E-Mail ").show();
        }else if(txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Password").show();
        }else if(txtPasswordConfirm.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Conform your Password").show();
        } else if(txtNic.getText().isEmpty() && txtEmail.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Nic & E-Mail").show();
        }else if(txtNic.getText().isEmpty() && txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Nic & Password").show();
        }else if(txtNic.getText().isEmpty() && txtPasswordConfirm.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Nic & Confirm Password").show();
        }else if(txtEmail.getText().isEmpty() && txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your E-Mail & Password").show();
        }else if(txtEmail.getText().isEmpty() && txtPasswordConfirm.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your E-Mail & Confirm Password").show();
        }else if(txtPassword.getText().isEmpty() && txtPasswordConfirm.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Enter Your Password & Confirm Password").show();
        }
        else{
            String CodeSend=genarateCode();
            txtNicCode.setText(CodeSend);
            btnRegister.setDisable(false);
        }

    }
}
