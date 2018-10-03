package ar.com.smartcart.smartcart.communication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {

    public static String SERVER_URL;
    public final static String METODO_GET = "GET";
    public final static String METODO_POST = "POST";
    public final static int TIMEOUT_DEFAULT = 2000;

    public static String crearURL(String servicio){
        return "http://" + SERVER_URL + "/" + servicio;
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
        conn.setConnectTimeout(TIMEOUT_DEFAULT);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept","application/json");
        conn.setRequestMethod(method);
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

    public static Bitmap descargarImg(String uri) throws IOException {
        if (uri == null) {
            return null;
        }
//        uri = "https://cdn0.woolworths.media/content/wowproductimages/large/032731.jpg";
        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(METODO_GET);
        conn.connect();
        Log.d("smartcart", "Conectado con servidor");
        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            Log.d("smartcart", "Respuesta:");
            Bitmap bmap = BitmapFactory.decodeStream(in);
            in.close();
            return bmap;
        } finally {
            conn.disconnect();
        }
    }
}