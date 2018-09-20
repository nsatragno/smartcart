package ar.com.smartcart.smartcart.communication;

import android.util.Log;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.LoginResponse;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.modelo.ProductoEnLista;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class ProductosManager {
    private final static String SERVICIO_CONTENIDO_CHANGO = "changos/{id}";
    private final static String SERVICIO_ALL_PRODUCTOS = "productos";

    public static Chango getContenidoChango(String idChango) throws IOException {
        String response = HTTPHelper.request(SERVICIO_CONTENIDO_CHANGO.replace("{id}", idChango));
        Log.d("Contenido Chango:", response);
        return new Gson().fromJson(response, Chango.class);
    }

    public static ArrayList<Producto> getAllProductos() throws IOException {
        String response = HTTPHelper.request(SERVICIO_ALL_PRODUCTOS);
        Log.d("Todos los Productos:", response);
        Type listType = new TypeToken<ArrayList<Producto>>(){}.getType();
        ArrayList<Producto> productos = new Gson().fromJson(response, listType);
        return productos;
    }
}