package lk.ijse.controller.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class Navigation {
    private static Stage stage;
    private static Scene scene;
    private static Parent parent;

    public static void navigation(String link,ActionEvent event, MouseEvent mouseEvent) throws IOException {
        parent = FXMLLoader.load(Navigation.class.getResource("/view/" + link));

        scene = new Scene(parent);

        if (event==null){
            stage=(Stage)((Node) mouseEvent.getSource()).getScene().getWindow();

        }else if(mouseEvent==null){
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();

        }
        stage.setScene(scene);
        stage.setTitle("R.H.P.M services Management System");
        stage.centerOnScreen();
        stage.show();
    }
}
