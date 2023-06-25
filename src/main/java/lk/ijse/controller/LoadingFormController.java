package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lk.ijse.controller.util.Navigation;

import java.io.IOException;

public class LoadingFormController {
    public Button btnNext;


/*
       public void GotoLoginPage(ActionEvent actionEvent) {
        System.out.println("Clicked");
       }
*/

   @FXML
    public void btnNextOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxx");
        Navigation.navigation("loading1pagecorm.fxml",event,null);
    }
}
