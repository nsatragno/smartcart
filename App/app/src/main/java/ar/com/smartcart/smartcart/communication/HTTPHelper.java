package ar.com.smartcart.smartcart.communication;

//import org.apache.http.client.HttpClient;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {

    public String request(String uri) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept","application/json");
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.connect();

        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
//            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            // temporary string to hold each line read from the reader.
            String inputLine;
            while ((inputLine = bin.readLine()) != null) {
//            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
        } finally {
            // regardless of success or failure, we will disconnect from the URLConnection.
//            urlConnection.disconnect();
        }
        return sb.toString();
    }
}