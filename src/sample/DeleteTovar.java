package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sample.pojo.*;

import java.io.IOException;


public class DeleteTovar {

    public ObservableList<Product_value> productsData = FXCollections.observableArrayList();

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
    private TableColumn<Product_value, String> priceColumn ;

    @FXML
    private TableColumn<Product_value, String> quantityColumn;


    private void listProductUpload() {
        JSONObject product_json = new JSONObject();


        try {
            String params = String.format("startLimit=%s&&limit=%s", (currentPage - 1) * rowOnPage, rowOnPage);

            product_json = HttpURLConnectionExample.sendPOST(HttpURLConnectionExample.POST_URL_List_Product, params);

            JSONArray key = product_json.names();
            productsData = FXCollections.observableArrayList();
            for (int i = 0; i < key.length(); ++i) {
                String keys = null;
                try {
                    keys = key.getString(i);
                    productsData.add(new Product_value(new JSONObject(product_json.getString(keys))));
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
    private int maxPage = 1;
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
            if (currentPage < maxPage) {
                currentPage += 1;
                listProductUpload();
                pageField.setText(String.format("%s / %s", currentPage, maxPage));
            }

        });


        backPageButton.setOnAction(event -> {
            if (currentPage > 1) {
                currentPage -= 1;
                listProductUpload();
                pageField.setText(String.format("%s / %s", currentPage, maxPage));
            }

        });


        tableUsers.setRowFactory(tv -> {
            TableRow<Product_value> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product_value rowData = row.getItem();
                    deleteProduct(rowData);
                }
            });
            return row;
        });

    }

    public void deleteProduct(Product_value product){


        try {
            String params = String.format("barcode=%s", product.getBarcode());

            HttpURLConnectionExample.sendPOST(HttpURLConnectionExample.POST_URL_DELETE, params);

            JSONObject rowcountJson = new JSONObject();

            rowcountJson = HttpURLConnectionExample.sendGET(HttpURLConnectionExample.GET_URL_COUNT_ROW);
            if (rowcountJson.getInt("rowcount") % rowOnPage == 0)
                maxPage = (int) (rowcountJson.getInt("rowcount") / rowOnPage);
            else maxPage = (int) (rowcountJson.getInt("rowcount") / rowOnPage + 1);

            pageField.setText(String.format("%s / %s", currentPage, maxPage));

            listProductUpload();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

}

