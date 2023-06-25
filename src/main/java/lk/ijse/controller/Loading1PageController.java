package lk.ijse.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.controller.util.Navigation;

public class Loading1PageController {
    @FXML
    public ProgressBar loading;
    @FXML
    public ResourceBundle resources;
    @FXML
    public URL location;
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public Button btn;
    public AnchorPane loadingP;

    public void initialize() {
        new ShowSplashScreen().start();
    }

    @FXML
    public void LoadingOnAction(ActionEvent event) throws IOException {
        System.out.println("xxxxxxxxxxxxxxx");
        Navigation.navigation("loginpage.fxml",event,null);
    }


    class ShowSplashScreen extends Thread {
        public void run() {
            try {
                for (int i = 0; i <= 10; i++) {
                    double x = i * (0.1);
                    loading.setProgress(x);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/view/loginpage.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(Loading1PageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stage.setScene(new Scene(root));
                    stage.setTitle("R.H.P.M Services");
                    stage.show();
                    loadingP.getScene().getWindow().hide();
                });

            } catch (Exception ex) {
                Logger.getLogger(Loading1PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}