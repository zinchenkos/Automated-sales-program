package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

public class Admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private Button editTovar;


    @FXML
    private AnchorPane wrongPane;

    @FXML
    private Button deleteTovar;
    @FXML
    private Button backButton;
    @FXML
    private Button addTovar;

    @FXML
    private TextField adminCode;

    @FXML
    private TextField passwordCode;



    @FXML
    void initialize() {
        String f;


        adminCode.setOnAction(event -> {
            boolean a = checkAdmin(adminCode.getText() , passwordCode.getText());
            if(a) {
                addTovar.getScene().getWindow().hide();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("views/typeOfTovar.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage primaryStage_2 = new Stage();
                primaryStage_2.setTitle("Add product");

                primaryStage_2.setScene(new Scene(root));

                primaryStage_2.show();
            }
            System.out.println("fdswefwe");
        });





        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("views/main.fxml"));
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

        addTovar.setOnAction(event -> {
            if(checkAdmin(adminCode.getText() , passwordCode.getText())) {
                wrongPane.setVisible(false);
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("views/typeOfTovar.fxml"));
            } catch (IOException e) {
            e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Add product");

            primaryStage_2.setScene(new Scene(root));

            primaryStage_2.show();
            }

        });

        editTovar.setOnAction(event -> {
            if(checkAdmin(adminCode.getText() , passwordCode.getText())) {
                wrongPane.setVisible(false);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("views/editTovar.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage primaryStage_2 = new Stage();
                primaryStage_2.setTitle("Edit product");

                primaryStage_2.setScene(new Scene(root));

                primaryStage_2.show();
            }

        });


        deleteTovar.setOnAction(event -> {
            wrongPane.setVisible(false);
            if(checkAdmin(adminCode.getText() , passwordCode.getText())) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("views/deleteTovar.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage primaryStage_2 = new Stage();
                primaryStage_2.setTitle("Удаление Товара");

                primaryStage_2.setScene(new Scene(root));

                primaryStage_2.show();
            }
        });








    }


    private boolean checkAdmin(String barcode , String password) {
        JSONObject respond = new JSONObject();
        String status = new String();
        boolean a = true;

        try {
            String params = String.format("barcode=%s&&password=%s", barcode, password);

            respond = HttpURLConnectionExample.sendPOST(HttpURLConnectionExample.POST_URL_ADMIN, params);
            status = respond.getString("status");

        } catch (IOException e) {
            e.printStackTrace();
            wrongPane.setVisible(true);
            return false;

        } catch (JSONException e) {
            e.printStackTrace();
            wrongPane.setVisible(true);
            return false;
        }
        finally {
            if (status.equalsIgnoreCase("200")) return true;

            else{
                wrongPane.setVisible(true);
                return false;
            }

        }



    }


}