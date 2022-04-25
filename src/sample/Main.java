package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.peer.DialogPeer;



public class Main extends Application {


    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/start.fxml"));
        primaryStage.setTitle("ScanPay");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        DialogPeer frame;
        primaryStage.setResizable(false);
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}