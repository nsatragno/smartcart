package ar.com.smartcart.smartcart;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;
import ar.com.smartcart.smartcart.communication.PromoAsyncTask;
import ar.com.smartcart.smartcart.modelo.Promocion;

public class PromosFragment extends android.support.v4.app.Fragment {

    private TextView txtPromo;
    private ImageView imgPromo;
    private Context context;
    private ArrayList<Promocion> promos;

    public PromosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout principalLayout = (LinearLayout) inflater.inflate(
                R.layout.fragment_promos, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Promociones Vigentes");

        context = getActivity().getApplicationContext();
        LinearLayout topLayout = principalLayout.findViewById(R.id.top_layout);
        txtPromo = topLayout.findViewById(R.id.txt_nombre_promo);
        imgPromo = principalLayout.findViewById(R.id.img_promo);

        PromoAsyncTask task = new PromoAsyncTask(){
            @Override
            protected void onPostExecute(final ArrayList<Promocion> response) {
                promos = response;
                DescargaImagenAsyncTask taskImg = new DescargaImagenAsyncTask(){
                    @Override
                    protected void onPostExecute(final Bitmap response) {
                        imgPromo.setImageBitmap(response);
                    }
                };
                if(promos != null){
                    taskImg.execute(promos.get(0).getUrl());
                    txtPromo.setText(promos.get(0).getNombre());
                }
            }};
        task.execute();
        return principalLayout;
    }
}