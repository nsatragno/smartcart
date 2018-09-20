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

    public final static String SERVER_IP = "http://192.168.1.110";
    public final static String SERVER_PUERTO = "3000";
    public final static String METODO_GET = "GET";
    public final static String METODO_POST = "POST";

    public static String crearURL(String servicio){
        return SERVER_IP + ":" + SERVER_PUERTO + "/" + servicio;
    }

    public static String request(String uri) throws IOException {
        return request(uri, METODO_GET);
    }

    public static String request(String urlServicio, String method) throws IOException {
        String uri = crearURL(urlServicio);
        Log.d("smartcart", "Intentando conexión a " + uri);
        StringBuilder sb = new StringBuilder();
        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept","application/json");
        conn.setRequestMethod(method);
//        conn.setDoOutput(true);
        conn.connect();
        Log.d("smartcart", "Conectado con servidor");

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