package ar.com.smartcart.smartcart.communication;

import android.util.Log;
import com.google.gson.Gson;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.Producto;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class ProductosManager {
    private final static String SERVICIO_CONTENIDO_CHANGO = "changos/{id}";
    private final static String SERVICIO_ALL_PRODUCTOS = "productos";
    public final static String PRODUCTO_ENCONTRADO = "PRODUCTO_ENCONTRADO";

    public static Chango getContenidoChango(Long idChango) throws IOException {
        String response = HTTPHelper.request(SERVICIO_CONTENIDO_CHANGO.replace("{id}", idChango.toString()));
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

    public static String convertirEnPrecio(BigDecimal precio){
        return "$ " + precio.setScale(2).toString();
    }
}