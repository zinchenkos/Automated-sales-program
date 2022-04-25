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

public class    TypeOfTovar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button techButton;

    @FXML
    private Button chemiclButton;

    @FXML
    private Button foodButton;

    @FXML
    private Button clothesButton;

    @FXML
    void initialize() {

        clothesButton.setOnAction(event -> {
            clothesButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("views/addClothes.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Add Clothes");
            primaryStage_2.setScene(new Scene(root));

            primaryStage_2.show();
        });

        foodButton.setOnAction(event -> {
            foodButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("views/addFood.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Add Food");
            primaryStage_2.setScene(new Scene(root));

            primaryStage_2.show();
        });

        techButton.setOnAction(event -> {
            techButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("views/addTech.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Add Tech");
            primaryStage_2.setScene(new Scene(root));

            primaryStage_2.show();
        });


        chemiclButton.setOnAction(event -> {
            chemiclButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("views/addChemical.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Add Chemical");
            primaryStage_2.setScene(new Scene(root));

            primaryStage_2.show();
        });

    }
}
