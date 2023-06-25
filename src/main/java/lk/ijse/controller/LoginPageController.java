package lk.ijse.controller;

import lk.ijse.bo.custom.impl.MachineBOimpl;
import lk.ijse.bo.custom.impl.RegisterBOimpl;
import lk.ijse.dao.custom.MachineDAO;
import lk.ijse.dao.custom.RegisterDAO;
import lk.ijse.dao.custom.impl.MachineDAOimpl;
import lk.ijse.dao.custom.impl.RegisterDAOimpl;
import lk.ijse.entity.Register;
//import lk.ijse.Model.Register;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.controller.util.Navigation;
import lk.ijse.entity.Register;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;


public class LoginPageController {

    public PasswordField txtPassword;
    public TextField txtUserName;
    public Button lblRegister2;
    public Label btnRegister1;
    public Button lblRegister;
    public Button btnLogin;
    public Button btnDelete;
    public TextField txtCode;
    public Button btnCheck1;
    public Button btnCheck2;
    public Button btnCheck3;


    int attempts=0;
    int count=2;

    RegisterBOimpl registerBOimpl = new RegisterBOimpl();


    @FXML
    public void btnRegisterOnAction1(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxx");
        Navigation.navigation("registerform.fxml",event,null);
    }
    @FXML
    public void btnRegisterOnAction2(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxx");
        Navigation.navigation("registerform.fxml",event,null);
    }

    String pw;
    String nic;
    String code;


    int count1= count--;
    @FXML
    void btnloginOnAction(ActionEvent event) throws IOException {

        try {
            Register register=registerBOimpl.searchRegistration(txtUserName.getText());
            nic =register.getAdminNic();
            pw= register.getPassword();
            code=register.getAdmin_Code();

            System.out.println(pw);
            System.out.println(code);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        attempts++;
        if(attempts<=3) {

            if (txtUserName.getText().equals(nic) && txtPassword.getText().equals(pw) && txtCode.getText().equals(code) ) {

                new Alert(Alert.AlertType.CONFIRMATION, "Login Success").show();
                System.out.println("xxxxxxxxxxx");
                Navigation.navigation("dashboardform.fxml", event, null);

                if(txtUserName.getText().equals(nic)){
                    new Alert(Alert.AlertType.CONFIRMATION,"Matched").show();
                }else if(txtUserName.getText() != nic){
                    new Alert(Alert.AlertType.ERROR,"Does not matched Nic").show();
                }

            } else if(txtUserName.getText().equals(nic) && txtPassword.getText().equals(pw)){
                System.out.println("Failed");
                new Alert(Alert.AlertType.WARNING,"Login Failed "+count1+" step Setup").show();
            }else if(txtPassword.getText().equals(pw) && txtCode.getText().equals(code)){
                System.out.println("Failed");
                new Alert(Alert.AlertType.WARNING,"Login Failed "+count1+" step Setup").show();
            }else if(txtUserName.getText().equals(nic) && txtCode.getText().equals(code)){
                System.out.println("Failed");
                new Alert(Alert.AlertType.WARNING,"Login Failed "+count1+" step Setup").show();
            }else{
                System.out.println("Failed");
                new Alert(Alert.AlertType.WARNING,"Login Failed "+count1+" step Setup").show();
            }

            count1=count;

        }else{
            txtUserName.setEditable(false);
            txtPassword.setEditable(false);
            new Alert(Alert.AlertType.ERROR,"Please Close the System And Start Again").show();
        }


    }

    public void btnGoogleOnAction(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjj6fjShOb9AhX9XmwGHc_XAIEQPAgI"));
    }

    public void btnFacebookOnAction(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjj6fjShOb9AhX9XmwGHc_XAIEQPAgI"));
    }

    @FXML
    public void btnOtpPageOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("generateotpform.fxml",event,null);
    }


    public void btnDeleteAccountOnAction(ActionEvent event) throws IOException {
        String code = txtUserName.getText();
        try {
            boolean isDeleted = registerBOimpl.deleteRegistration(code);
            if (isDeleted) {
               // RegisterFormController.registerPane.setDisable(false);
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                Navigation.navigation("registerform.fxml",event,null);
            }else{
                //RegisterFormController.registerPane.setVisible(true);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnLoginDisableOnAction(KeyEvent keyEvent) {
        btnCheck3.setVisible(false);
        if(txtCode.getText().isEmpty()){
            btnCheck3.setDisable(true);
        }else {
            btnLogin.setDisable(false);
            if (attempts==2) {
                btnCheck3.setVisible(true);
                btnCheck3.setDisable(false);
            }
        }
    }

    public void btnCodeDisableOnAction(KeyEvent keyEvent) {

           btnCheck2.setVisible(false);
        if(txtPassword.getText().isEmpty()){
            txtCode.setDisable(true);
            btnCheck2.setDisable(true);
        }else {
            txtCode.setDisable(false);
            if (attempts==2) {
                btnCheck2.setVisible(true);
                btnCheck2.setDisable(false);
            }
        }
    }

    public void btnNicCheckOnAction(ActionEvent event) {

        if(attempts==2) {
            try {
                Register register = registerBOimpl.searchRegistration(txtUserName.getText());
                nic = register.getAdminNic();
                System.out.println(pw);
                System.out.println(code);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (txtUserName.getText().equals(nic)) {
                new Alert(Alert.AlertType.CONFIRMATION, " Completed..").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Incorrect Nic Number").show();
            }
        }
    }

    public void btnPaswordCheckOnAction(ActionEvent event) {

        if(attempts==2) {
            try {
                Register register = registerBOimpl.searchRegistration(txtUserName.getText());
                pw = register.getPassword();
                System.out.println(pw);
                System.out.println(code);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (txtPassword.getText().equals(pw)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Step 1 Completed..").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            }
        }
    }

    public void btnCodeCheckOnAction(ActionEvent event) {

        if(attempts==2) {
            try {
                Register register = registerBOimpl.searchRegistration(txtUserName.getText());
                code = register.getAdmin_Code();
                System.out.println(pw);
                System.out.println(code);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (txtCode.getText().equals(code)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Step 1 Completed..").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Code").show();
            }
        }
    }

    public void nicCheckOnAction(KeyEvent keyEvent) {


        //btnCheck1.setVisible(false);
        if (txtUserName.getText().isEmpty()) {
            //btnCheck1.setDisable(false);
        } else {
            if (attempts==2) {
                //btnCheck1.setVisible(false);
                //btnCheck1.setDisable(false);
            }
        }
    }
}

