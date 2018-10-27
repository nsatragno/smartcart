package ar.com.smartcart.smartcart.communication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.Producto;

public class AllProductosAsyncTask extends AsyncTask<Context, Void, ArrayList<Producto>> {
        @Override
        protected ArrayList<Producto> doInBackground(Context... params) {
            try {
                ArrayList<Producto> productos = ProductoManager.getAllProductos();
                if(productos != null && !productos.isEmpty()){
                    DBHelper.getInstance(params[0]).actualizarProductos(productos);
                }
                return productos;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Smartcart", e.getMessage());
                return null;
            }
        }
}