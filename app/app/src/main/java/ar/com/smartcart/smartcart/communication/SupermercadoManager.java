package ar.com.smartcart.smartcart.communication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;

import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.modelo.Supermercado;

public class SupermercadoManager {
    private final static String SERVICIO_SUPERMERCADO = "supermercado";

    public static Supermercado getSupermercado() throws IOException {
        String response = HTTPHelper.request(SERVICIO_SUPERMERCADO);
        Log.d("Supermercado:", response);
        return new Gson().fromJson(response, Supermercado.class);
    }
}