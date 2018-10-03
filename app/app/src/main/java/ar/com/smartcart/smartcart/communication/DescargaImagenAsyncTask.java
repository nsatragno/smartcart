package ar.com.smartcart.smartcart.communication;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;

public class DescargaImagenAsyncTask extends AsyncTask<String, Integer, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            Bitmap bmap = HTTPHelper.descargarImg(params[0]);
            return bmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Smartcart", e.getMessage());
            return null;
        }
    }
}