package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseColor {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button idImage1;

    @FXML
    private Button idImage2;

    @FXML
    void initialize() {


        idImage2.setOnAction(event -> {
            idImage2.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("views/start.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Store");
            primaryStage_2.setScene(new Scene(root));
            primaryStage_2.setMaximized(true);
            primaryStage_2.setResizable(false);
            primaryStage_2.show();
        });

        idImage1.setOnAction(event -> {
            idImage1.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("views/start1.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Store");
            primaryStage_2.setScene(new Scene(root));
            primaryStage_2.setMaximized(true);
            primaryStage_2.setResizable(false);
            primaryStage_2.show();
        });

    }
}