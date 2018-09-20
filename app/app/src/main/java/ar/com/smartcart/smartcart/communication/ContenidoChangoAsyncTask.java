package ar.com.smartcart.smartcart.communication;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;

import ar.com.smartcart.smartcart.modelo.Chango;

public class ContenidoChangoAsyncTask extends AsyncTask<Void, Void, Chango> {
        @Override
        protected Chango doInBackground(Void... params) {
            try {
                return ProductosManager.getContenidoChango("1");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Smartcart", e.getMessage());
                return null;
            }
        }
}