

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {

//        Parent parent =  FXMLLoader.load(getClass().getResource("view/loadingform.fxml"));
        Parent parent =  FXMLLoader.load(getClass().getResource("view/loadingform.fxml"));
        //Parent load= FXMLLoader.load(resource);
        //Scene scene = new Scene(load);
        stage.setScene(new Scene(parent));
        stage.setTitle("Opening");
        stage.centerOnScreen();
        stage.show();
    }
}
