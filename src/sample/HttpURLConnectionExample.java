package sample;

//import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpURLConnectionExample {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String http = "http://cc2db5df.ngrok.io";
    public static final String POST_URL_ADMIN = http + "/entering";
    private static final String POST_URL_BARCODE = http + "/barcode";
    public static final String GET_URL_COUNT_ROW = http + "/rowcount";
    public static final String POST_URL_INFO = http + "/info";
    public static final String POST_URL_DELETE = http + "/delete";
    public static final String POST_URL_List_Product = http + "/listProductlimit";
    public static final String POST_URL_BARCODE_BOOL = http + "/checkbarcode";
    public static final String POST_URL_ADD = http + "/add";
    public static final String POST_URL_ADD_FEATURES = http + "/add_features";
    public static final String POST_URL_ALL = http + "/barcodeall";
    public static final String POST_URL_SUBCATEGORY = http + "/category";
    public static final String POST_URL_MANUFACTORY = http + "/manufacturer";
    public static final String POST_URL_EDIT = http + "/edit";
    public static final String POST_URL_EDIT_FEATURE = http + "/edit_features";


     private static final String POST_PARAMS_DEMO = "barcode=644832819197";



    public static JSONObject  sendGET(String link) throws IOException, JSONException {
        URL obj = new URL(link);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            in.close();
            //System.out.println(response.toString());
            JSONObject myResponse = new JSONObject(response.toString());


            return myResponse;
        } else {
            System.out.println("GET request not worked");
            throw new IOException("Not ok");
        }

    }


    public static JSONObject sendPOST(String link , String params) throws IOException, JSONException {
        URL obj = new URL(link);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        //con.setRequestProperty("authorization", "Java client");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(params.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //System.out.println(response.toString());
            JSONObject myResponse = new JSONObject(response.toString());


            return myResponse;



            //System.out.println(myResponse.toString());
            /*item m = new item(myResponse);
            System.out.println(m.getName());
            System.out.println(m.getBarcode());
            System.out.println(m.getPrice());
            System.out.println(m.getCategory());
            System.out.println(m.getPoints());
            System.out.println(m.getPhoto());*/}
        else {
            System.out.println("POST request not worked");
            throw new IOException("Not ok");
            }



    }


}

