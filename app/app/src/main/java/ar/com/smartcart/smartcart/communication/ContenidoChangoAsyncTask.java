package ar.com.smartcart.smartcart.communication;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;

import ar.com.smartcart.smartcart.modelo.Chango;

public class ContenidoChangoAsyncTask extends AsyncTask<Long, Void, Chango> {
        @Override
        protected Chango doInBackground(Long... params) {
            try {
                return ProductosManager.getContenidoChango(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Smartcart", e.getMessage());
                return null;
            }
        }
}