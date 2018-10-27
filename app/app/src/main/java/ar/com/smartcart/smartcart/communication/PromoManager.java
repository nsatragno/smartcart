package ar.com.smartcart.smartcart.communication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.modelo.Promocion;
import ar.com.smartcart.smartcart.modelo.Supermercado;

public class PromoManager {
    private final static String SERVICIO_PROMOS = "promociones";

    public static ArrayList<Promocion> getPromociones() throws IOException {
        String response = HTTPHelper.request(SERVICIO_PROMOS);
        Log.d("Promociones:", response);
        Type listType = new TypeToken<ArrayList<Promocion>>(){}.getType();
        ArrayList<Promocion> promos = new Gson().fromJson(response, listType);
        return promos;
    }
}