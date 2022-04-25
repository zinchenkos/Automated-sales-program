package sample;




import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import org.json.JSONException;
import org.json.JSONObject;

import sample.pojo.Product_value;


public class InfoAboutFood {

    private Stage dialogStage;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField barcodeField;

    @FXML
    private ImageView imgSet;

    @FXML
    private TextField priceField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField manufacturerField;
    @FXML
    private TextField categoryField;

    @FXML
    private CheckBox perishableField;

    @FXML
    private Button buttonOK;


    public void setProduct(Product_value product) {
        JSONObject features = new JSONObject();
        String perishable = new String();


        try {
            String params = String.format("barcode=%s", product.getBarcode());

            features = HttpURLConnectionExample.sendPOST(HttpURLConnectionExample.POST_URL_INFO, params);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            perishable = features.getString("быстропортящийся");
        }
        catch (JSONException e){
            e.printStackTrace();
        }



        nameField.setText(product.getName());
        barcodeField.setText(String.valueOf(product.getBarcode()));
        priceField.setText(String.valueOf(product.getPrice()));
        categoryField.setText(String.valueOf(product.get_category()));
        manufacturerField.setText(String.valueOf(product.get_manufacturer()));

        perishableField.setAllowIndeterminate(false);
        if(perishable.equalsIgnoreCase("0")||perishable.equals("")) {
            perishableField.setSelected(false);
        }
        else {
            perishableField.setSelected(true);
        }


        Image image = new Image(getClass().getResource(product.getPhoto()).toExternalForm());

        imgSet.setImage(image);
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void initialize() {



        buttonOK.setOnAction(event -> {
            dialogStage.close();


        });


    }
}