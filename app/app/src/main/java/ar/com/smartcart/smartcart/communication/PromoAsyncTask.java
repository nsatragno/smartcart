package ar.com.smartcart.smartcart.communication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import ar.com.smartcart.smartcart.modelo.Promocion;
import ar.com.smartcart.smartcart.modelo.Supermercado;

public class PromoAsyncTask extends AsyncTask<Long, Void, ArrayList<Promocion>> {
        @Override
        protected ArrayList<Promocion> doInBackground(Long... params) {
            try {
                return PromoManager.getPromociones();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Smartcart", e.getMessage());
                return null;
            }
        }
}