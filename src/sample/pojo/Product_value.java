package sample.pojo;

import org.json.JSONException;
import org.json.JSONObject;


public class Product_value {

    private int id;
    private String name;
    private Long barcode;
    private double price;
    private String category;
    private String subcategory;
    private String manufacturer;
    private String photo;
    private int points;
    private String delivery;
    private int quantity;

    public Product_value(int id, String name, Long barcode, double price, String category, String subcategory, String manufacturer, String photo, int points, String delivery, int quantity) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.category = category;
        this.subcategory = subcategory;
        this.manufacturer = manufacturer;
        this.photo = photo;
        this.points = points;
        this.delivery = delivery;
        this.quantity = quantity;
    }

    public Product_value() {
        photo="0.png";
    }
    public Product_value(Product_value another) {
        this.id=another.id;
        this.barcode=another.barcode;
        this.name=another.name;
        this.price=another.price;
        this.category = another.category;
        this.subcategory = another.subcategory;
        this.manufacturer = another.manufacturer;
        this.photo=another.photo;
        this.points = another.points;
        this.delivery = another.delivery;
        this.quantity = another.quantity;
    }

    public Product_value(JSONObject b) {

        try {
            this.id = b.getInt("id_product_value");

        this.name = b.getString("name");
        this.barcode = b.getLong("bordercode");
        this.price = b.getDouble("price");
        this.category = b.getString("category");
        this.subcategory = b.getString("subcategory");
        this.manufacturer = b.getString("manufacturer");
        this.photo = b.getString("photo");
        this.points = b.getInt("points");
        this.delivery = b.getString("delivery_date");
        this.quantity = b.getInt("quantity");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String get_category() {
        return category;
    }

    public void set_category(String category) {
        this.category = category;
    }

    public String get_subcategory() {
        return subcategory;
    }

    public void set_subcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String get_manufacturer() {
        return manufacturer;
    }

    public void set_manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDelivery(){return delivery;}

    public void setDelivery(String delivery){this.delivery = delivery;}

    public int getQuantity(){return quantity;}

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}

