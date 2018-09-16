package ar.com.smartcart.smartcart.communication;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;

import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.LoginResponse;

public class ProductosManager {
    private final static String URL_CHANGO =
            HTTPHelper.URL + "/changos/{id}";

    public static Chango getChango() throws IOException {
        String response = HTTPHelper.request(URL_CHANGO.replace("{id}", "1"));

        Log.d("smartcart", response);
        return new Gson().fromJson(response, Chango.class);
    }
}
