package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sample.pojo.Category;
import sample.pojo.Manufactory;
import sample.pojo.Product_value;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import static sample.HttpURLConnectionExample.*;

public class EditTech {
    private Stage dialogStage;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField barcodeField;

    @FXML
    private TextField priceField;



    @FXML
    private TextField nameField;

    @FXML
    private Button canceButton;
    @FXML
    private Button addButton;

    @FXML
    private CheckBox guaranteeField;


    @FXML
    private ChoiceBox<Category> categoryBox;

    @FXML
    private ChoiceBox<Manufactory> manufactureBox;

    @FXML
    private Label wrongText;

    @FXML
    private AnchorPane wrongPane;

    @FXML
    private TextField quantilyField;

    @FXML
    private DatePicker datePicker;

    private ObservableList<Category> fList = FXCollections.observableArrayList();
    private ObservableList<Manufactory> mList = FXCollections.observableArrayList();

    private int id_product ;
    private int point;
    private String photo;
    private int category = 2;

    @FXML
    void initialize() {

        LocalDate maxDate = LocalDate.now();
        datePicker.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate));
                    }});


        JSONObject features = new JSONObject();



        try {
            String params = "category=техника";

            features = HttpURLConnectionExample.sendPOST(HttpURLConnectionExample.POST_URL_SUBCATEGORY, params);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray key = features.names ();
        for (int i = 0; i < key.length (); ++i) {
            String keys = null;
            try {
                keys = key.getString(i);
                fList.add( new Category(Integer.parseInt(keys), features.getString(keys)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        JSONObject manufactory = new JSONObject();


        try {
            String params = "category=техника";

            manufactory = HttpURLConnectionExample.sendPOST(HttpURLConnectionExample.POST_URL_MANUFACTORY, params);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        key = manufactory.names ();
        for (int i = 0; i < key.length (); ++i) {
            String keys = null;
            try {
                keys = key.getString(i);
                mList.add( new Manufactory(Integer.parseInt(keys), manufactory.getString(keys)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        categoryBox.setItems(fList);
        manufactureBox.setItems(mList);






        addButton.setOnAction(event -> {
            if (priceField.getText().isEmpty()  || nameField.getText().isEmpty() || manufactureBox.getItems() == null || barcodeField.getText().isEmpty() || quantilyField.getText().isEmpty()|| datePicker.getValue() == null  || categoryBox.getItems()  == null ) {
                wrongText.setText("Please fill all fields");
                wrongPane.setVisible(true);
            }
            else{
                try {
                    Double d1 = new Double(priceField.getText());
                    try {
                        int guarantee = 0;
                        if(guaranteeField.isSelected()){
                            guarantee = 1;
                        }
                        Integer d2 = new Integer(quantilyField.getText());


                        String params=String.format("id_product=%s&&name=%s&&price=%s&&id_category=%s&&id_subcategory=%s&&id_manufacturer=%s&&photo=%s&&points=%s&&delivery_date=%s&&quantity=%s",id_product,nameField.getText(), priceField.getText(),category,categoryBox.getValue().getId(),manufactureBox.getValue().getId(),photo,point,datePicker.getValue(),quantilyField.getText());


                        HttpURLConnectionExample.sendPOST(POST_URL_EDIT, params);

                        params=String.format("value=%s&&id_feature=%s&&id_product=%s",guarantee, 5, id_product);

                        HttpURLConnectionExample.sendPOST(POST_URL_EDIT_FEATURE, params);

                        addButton.getScene().getWindow().hide();


                    }catch (NumberFormatException e) {
                        quantilyField.clear();
                        wrongText.setText("Quantily is number!!");
                        wrongPane.setVisible(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                        barcodeField.clear();
                        wrongText.setText("BD has this barcode!!");
                        wrongPane.setVisible(true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (NumberFormatException e) {
                    priceField.clear();
                    wrongText.setText("Price is number!!");
                    wrongPane.setVisible(true);
                }

            }
        });
        canceButton.setOnAction(event -> {
            dialogStage.close();

        });
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product_value product) {

        id_product = product.getId();
        point = product.getPoints();
        photo = product.getPhoto();

        JSONObject features = new JSONObject();
        String guarantee = new String();


        try {
            String params = String.format("barcode=%s", product.getBarcode());

            features = HttpURLConnectionExample.sendPOST(HttpURLConnectionExample.POST_URL_INFO, params);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            guarantee = features.getString("гарантия");
        }
        catch (JSONException e){
            e.printStackTrace();
        }



        nameField.setText(product.getName());
        barcodeField.setText(String.valueOf(product.getBarcode()));
        priceField.setText(String.valueOf(product.getPrice()));
        for (int i = 0 ; i<fList.size(); i++){
            if(fList.get(i).getName().equalsIgnoreCase(product.get_subcategory())) categoryBox.setValue(fList.get(i));
        }

        for (int i = 0 ; i<mList.size(); i++){
            if(mList.get(i).getName().equalsIgnoreCase(product.get_manufacturer())) manufactureBox.setValue(mList.get(i));
        }

        guaranteeField.setAllowIndeterminate(false);
        if(guarantee.equalsIgnoreCase("0")||guarantee.equals("")) {
            guaranteeField.setSelected(false);
        }
        else {
            guaranteeField.setSelected(true);
        }

        quantilyField.setText(String.valueOf(product.getQuantity()));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Date date = formatter.parse(product.getDelivery());
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            datePicker.setValue(localDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }






    }

}

