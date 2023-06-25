package lk.ijse.controller.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateField {

    public static boolean emailCheck(String email){
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean PhoneNumber(String contact){
        Pattern namePattern = Pattern.compile("^(?:7|0|(?:\\\\+94))[0-9]{9,10}$");
        Matcher matcher = namePattern.matcher(contact);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    public static boolean Nic(String contact){
        Pattern namePattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        Matcher matcher = namePattern.matcher(contact);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
    public  static boolean machineId(String id) {
        Pattern idPattern = Pattern.compile("^(M)[0-9]{3}$");
        boolean matches = idPattern.matcher(id).matches();
        if(matches){
            return true;
        }else {
            return false;
        }
    }

    public  static boolean materialId(String id) {
        Pattern idPattern = Pattern.compile("^(MT)[0-9]{3}$");
        boolean matches = idPattern.matcher(id).matches();
        if(matches){
            return true;
        }else {
            return false;
        }
    }

    public  static boolean EmployeeId(String id) {
        Pattern idPattern = Pattern.compile("^(E)[0-9]{3}$");
        boolean matches = idPattern.matcher(id).matches();
        if(matches){
            return true;
        }else {
            return false;
        }
    }

    public  static boolean ReportId(String id) {
        Pattern idPattern = Pattern.compile("^(R)[0-9]{3}$");
        boolean matches = idPattern.matcher(id).matches();
        if(matches){
            return true;
        }else {
            return false;
        }
    }

    public  static boolean StockId(String id) {
        Pattern idPattern = Pattern.compile("^(ST)[0-9]{3}$");
        boolean matches = idPattern.matcher(id).matches();
        if(matches){
            return true;
        }else {
            return false;
        }
    }

    public  static boolean OrderId(String id) {
        Pattern idPattern = Pattern.compile("^(O)[0-9]{3}$");
        boolean matches = idPattern.matcher(id).matches();
        if(matches){
            return true;
        }else {
            return false;
        }
    }

    public  static boolean Employeename(String name) {
        Pattern idPattern = Pattern.compile("^[A-z\\s]{4,15}$");
        boolean matches = idPattern.matcher(name).matches();
        if(matches){
            return true;
        }else {
            return false;
        }
    }



    public  static boolean address(String name) {
        Pattern idPattern = Pattern.compile("[A-z @ 0-9]{4,20}");
        boolean matches = idPattern.matcher(name).matches();
        if(matches){
            return true;
        }else {
            return false;
        }
    }
    public  static boolean salary(String salary) {
        Pattern idPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        boolean matches = idPattern.matcher(salary).matches();
        if(matches){
            return true;
        }else {
            return false;
        }
    }
}