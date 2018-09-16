package ar.com.smartcart.smartcart.communication;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {

    public final static String URL = "http://192.168.1.109:3000";

    public static String request(String uri) throws IOException {
        return request(uri, "GET");
    }

    public static String request(String uri, String method) throws IOException {
        Log.d("smartcart", "Intentando conexión a " + uri);
        StringBuilder sb = new StringBuilder();
        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept","application/json");

        if (method.equals("POST"))
            conn.setDoOutput(true);
        conn.setRequestMethod(method);

        conn.connect();
        Log.d("smartcart", "Conectado");

        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            Log.d("smartcart", "Respuesta:");
            while ((inputLine = bin.readLine()) != null) {
                Log.d("smartcart", inputLine);
                sb.append(inputLine);
            }
            in.close();
        } finally {
            conn.disconnect();
        }
        return sb.toString();
    }
}