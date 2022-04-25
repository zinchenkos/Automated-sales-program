package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Good1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    Robot robot;

    @FXML
    private Button startButton;

    @FXML
    private MediaView MediaS;
    @FXML
    private Button admin;


    @FXML
    void initialize() {


        MediaPlayer mediaPlayer = new MediaPlayer(new Media(Paths.get("src/sample/3.mp4").toUri().toString()));
        mediaPlayer.setAutoPlay(true);
        MediaS.setMediaPlayer(mediaPlayer);


        admin.setOnAction(event -> {
            Stage stage = (Stage) admin.getScene().getWindow();
            // do what you have to do
            stage.close();
        });




        startButton.setOnAction(event -> {
            startButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("views/main1.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Users List");

            primaryStage_2.setScene(new Scene(root));
            primaryStage_2.setMaximized(true);
            primaryStage_2.setResizable(false);
            primaryStage_2.show();
        });

    }
}