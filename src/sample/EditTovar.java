package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sample.pojo.Product_value;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditTovar {


    public ObservableList<Product_value> productsData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TableView<Product_value> tableUsers;


    @FXML
    private Label pageField;


    @FXML
    private Button backButton;

    @FXML
    private Button backPageButton;

    @FXML
    private Button nextPageButton;


    @FXML
    private TableColumn<Product_value, Integer> idColumn;

    @FXML
    private TableColumn<Product_value, String> nameColumn;

    @FXML
    private TableColumn<Product_value, String> barcodeColumn;


    @FXML
    private TableColumn<Product_value, String> priceColumn;

    @FXML
    private TableColumn<Product_value, String> quantityColumn;

    private void listProductUpload() {
        JSONObject product_json = new JSONObject();


        try {
            String params = String.format("startLimit=%s&&limit=%s", (currentPage-1)*rowOnPage , rowOnPage);

            product_json = HttpURLConnectionExample.sendPOST(HttpURLConnectionExample.POST_URL_List_Product, params);

            JSONArray key = product_json.names ();
            productsData = FXCollections.observableArrayList();
            for (int i = 0; i < key.length (); ++i) {
                String keys = null;
                try {
                    keys = key.getString(i);
                    productsData.add( new Product_value(new JSONObject(product_json.getString(keys))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tableUsers.setItems(productsData);

    }


    private int currentPage = 1;
    private int maxPage=1;
    private static int rowOnPage = 10;





    @FXML
    private void initialize() {


        idColumn.setCellValueFactory(new PropertyValueFactory<Product_value, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product_value, String>("name"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<Product_value, String>("barcode"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product_value, String>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product_value, String>("quantity"));


        tableUsers.setItems(productsData);



        JSONObject rowcountJson = new JSONObject();

        try {
            rowcountJson = HttpURLConnectionExample.sendGET(HttpURLConnectionExample.GET_URL_COUNT_ROW);
            if (rowcountJson.getInt("rowcount") % rowOnPage == 0)
                maxPage = (int) (rowcountJson.getInt("rowcount") / rowOnPage);
            else maxPage = (int) (rowcountJson.getInt("rowcount") / rowOnPage + 1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        pageField.setText(String.format("%s / %s", currentPage, maxPage));

        listProductUpload();



        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
        });


        nextPageButton.setOnAction(event -> {
            if(currentPage < maxPage){
                currentPage +=1;
                listProductUpload();
                pageField.setText(String.format("%s / %s", currentPage, maxPage));
            }

        });


        backPageButton.setOnAction(event -> {
            if(currentPage > 1){
                currentPage -=1;
                listProductUpload();
                pageField.setText(String.format("%s / %s", currentPage, maxPage));
            }

        });


        tableUsers.setRowFactory(tv -> {
            TableRow<Product_value> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product_value rowData = row.getItem();
                    showPersonEditDialog(rowData);
                }
            });
            return row;
        });





    }

    public boolean showPersonEditDialog(Product_value product) {
        try {

            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            if(product.get_category().equalsIgnoreCase( "одежда") ){

                loader.setLocation(getClass().getResource("views/editClothes.fxml"));

                AnchorPane page = (AnchorPane) loader.load();
                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Info about clothes");
                dialogStage.initModality(Modality.WINDOW_MODAL);

                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the person into the controller.
                EditClothes controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setProduct(product);
                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();
                return true;}
            else {
                if (product.get_category().equalsIgnoreCase( "продукты")) {
                    loader.setLocation(getClass().getResource("views/editFood.fxml"));

                    AnchorPane page = (AnchorPane) loader.load();
                    // Create the dialog Stage.
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Info about food");
                    dialogStage.initModality(Modality.WINDOW_MODAL);

                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);

                    // Set the person into the controller.
                    EditFood controller = loader.getController();
                    controller.setDialogStage(dialogStage);
                    controller.setProduct(product);

                    // Show the dialog and wait until the user closes it
                    dialogStage.showAndWait();
                    return true;
                } else {
                    if (product.get_category().equalsIgnoreCase( "техника")) {
                        loader.setLocation(getClass().getResource("views/editTech.fxml"));
                        AnchorPane page = (AnchorPane) loader.load();

                        // Create the dialog Stage.
                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Info about Tech");
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        // Create the dialog С.
                        Scene scene = new Scene(page);
                        dialogStage.setScene(scene);

                        // Set the person into the controller.
                        EditTech controller = loader.getController();
                        controller.setDialogStage(dialogStage);
                        controller.setProduct(product);

                        // Show the dialog and wait until the user closes it
                        dialogStage.showAndWait();
                        listProductUpload();
                        return true;
                    } else{
                        if (product.get_category().equalsIgnoreCase( "бытовая_химия")) {
                            loader.setLocation(getClass().getResource("views/editChem.fxml"));
                            AnchorPane page = (AnchorPane) loader.load();

                            // Create the dialog Stage.
                            Stage dialogStage = new Stage();
                            dialogStage.setTitle("Info about Chemical");
                            dialogStage.initModality(Modality.WINDOW_MODAL);
                            // Create the dialog С.
                            Scene scene = new Scene(page);
                            dialogStage.setScene(scene);

                            // Set the person into the controller.
                            EditChemical controller = loader.getController();
                            controller.setDialogStage(dialogStage);
                            controller.setProduct(product);

                            // Show the dialog and wait until the user closes it
                            dialogStage.showAndWait();
                            listProductUpload();
                            return true;
                        } else return false;
                    }
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }








}