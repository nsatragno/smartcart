package ar.com.smartcart.smartcart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import ar.com.smartcart.smartcart.communication.ContenidoChangoAsyncTask;
import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;
import ar.com.smartcart.smartcart.communication.HTTPHelper;
import ar.com.smartcart.smartcart.communication.ProductoManager;
import ar.com.smartcart.smartcart.communication.SupermercadoAsyncTask;
import ar.com.smartcart.smartcart.communication.SupermercadoManager;
import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.modelo.Supermercado;

public class MapaFragment extends android.support.v4.app.Fragment {

    private TextView txtNombreProd;
    private ImageView imgMapa;
    private Bitmap original;
    private Context context;
    private Supermercado sup;

    public MapaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ConstraintLayout principalLayout = (ConstraintLayout) inflater.inflate(
                R.layout.fragment_mapa, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Ubicación de Productos");

        context = getActivity().getApplicationContext();
        txtNombreProd = principalLayout.findViewById(R.id.txt_nombre_prod);
        imgMapa = principalLayout.findViewById(R.id.img_mapa);

        SupermercadoAsyncTask taskSuper = new SupermercadoAsyncTask(){
            @Override
            protected void onPostExecute(final Supermercado response) {
                sup = response;
                DescargaImagenAsyncTask taskImg = new DescargaImagenAsyncTask(){
                    @Override
                    protected void onPostExecute(final Bitmap response) {
                        imgMapa.setImageBitmap(response);
                        original = response;
                    }
                };
                if(sup != null){
                    taskImg.execute(sup.getPlano());
                }
            }};
        taskSuper.execute();

        FloatingActionButton fbtnNew = principalLayout.findViewById(R.id.fbtn_nueva_busqueda);
        fbtnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BusquedaActivity.class);
                startActivityForResult(intent, ((PrincipalActivity) getActivity()).BUSCADOR);
            }
        });
        return principalLayout;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PrincipalActivity.BUSCADOR: {
                if (resultCode == Activity.RESULT_OK) {
                    Long id = (Long) data.getExtras().getLong(ProductoManager.PRODUCTO_ENCONTRADO);
                    Producto prod = DBHelper.getInstance(context).getProducto(id);
                    txtNombreProd.setText(prod.getNombre());
                    Bitmap imagen = original;
                    Bitmap mutableBitmap = imagen.copy(Bitmap.Config.ARGB_8888, true);
                    Canvas canvas = new Canvas(mutableBitmap);
                    Paint paint = new Paint();
                    paint.setColor(Color.parseColor("#333E50"));
                    paint.setFlags(Paint.ANTI_ALIAS_FLAG);
                    paint.setStrokeWidth(4);
                    canvas.drawCircle(prod.getCategoria().getPosicion_x().floatValue(),
                                      prod.getCategoria().getPosicion_y().floatValue(),
                                      40, paint);
                    paint.setColor(Color.RED);
                    canvas.drawCircle(prod.getCategoria().getPosicion_x().floatValue(),
                            prod.getCategoria().getPosicion_y().floatValue(),
                            20, paint);
                    imgMapa.setImageBitmap(mutableBitmap);
                }
                break;
            }
        }
    }
}