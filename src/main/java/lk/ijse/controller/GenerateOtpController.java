package lk.ijse.controller;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
//import lk.ijse.Model.DBModel.OtpModel;
import lk.ijse.bo.custom.impl.RegisterBOimpl;
import lk.ijse.controller.util.EmailSend;
import lk.ijse.controller.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;


public class GenerateOtpController{
    public Button sendOtp;
    public Button btnComplete;
    public Button btnBack;
    public TextField txtmail;
    public TextField txtotp;
    public Label lblPassword;

    RegisterBOimpl registerBOimpl = new RegisterBOimpl();


    public static String generateOTP(){
        int randomNo=(int)(Math.random()*9000)+1000;
        String otp=String.valueOf(randomNo);
        return otp;
    }

    public void sendOtpOnAction(ActionEvent event) throws Exception{
        String otpSend=generateOTP();
        System.out.println("Your OTP no : "+otpSend);
        EmailSend.sendEmail(txtmail.getText());
    }

    public void btnCompleteOnAction(ActionEvent event) throws IOException {
              getPassword();
    }
    @FXML
    public void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigation("loginpage.fxml",event,null);
    }

    public void enterEmailOnAction(KeyEvent keyEvent) {
        if(txtmail.getText().isEmpty()){
            txtotp.setDisable(true);
        }else{
            txtotp.setDisable(false);
        }
    }

 //forget Password
    public void getPassword(){
        if(txtotp.getText().equals(EmailSend.num)){
            try {
                lblPassword.setText("Your Password is : "+registerBOimpl.getPassword());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void enterOtpOnAction(KeyEvent keyEvent) {
        if(txtotp.getText().isEmpty()){
            lblPassword.setDisable(true);
            btnComplete.setDisable(true);
            if(EmailSend.num.equals(txtotp.getText())){
            }
        }else{
            lblPassword.setDisable(false);
            btnComplete.setDisable(false);
        }
    }
}
