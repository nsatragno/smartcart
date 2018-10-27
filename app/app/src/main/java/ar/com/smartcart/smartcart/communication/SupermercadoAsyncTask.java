package ar.com.smartcart.smartcart.communication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.Supermercado;

public class SupermercadoAsyncTask extends AsyncTask<Long, Void, Supermercado> {
        @Override
        protected Supermercado doInBackground(Long... params) {
            try {
                return SupermercadoManager.getSupermercado();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Smartcart", e.getMessage());
                return null;
            }
        }
}