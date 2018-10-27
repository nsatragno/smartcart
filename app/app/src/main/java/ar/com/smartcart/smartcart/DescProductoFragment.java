package ar.com.smartcart.smartcart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;
import ar.com.smartcart.smartcart.communication.ProductoManager;
import ar.com.smartcart.smartcart.communication.SupermercadoAsyncTask;
import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.modelo.Supermercado;

public class DescProductoFragment extends android.support.v4.app.Fragment {

    private TextView txtNombre;
    private TextView txtDesc;
    private ImageView imgProd;
    private CheckBox chkDiabeticos;
    private CheckBox chkCeliacos;

    private Producto prod;
    private Context context;

    public Producto getProducto() {
        return prod;
    }

    public void setProducto(Producto prod) {
        this.prod = prod;
    }

    public DescProductoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout principalLayout = (LinearLayout) inflater.inflate(
                R.layout.fragment_desc_prod, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Descripción del Producto");
        context = getActivity().getApplicationContext();

        LinearLayout topLayout = principalLayout.findViewById(R.id.top_layout);
        txtNombre = topLayout.findViewById(R.id.txt_nombre_prod);
        txtNombre.setText(prod.getNombre());

        LinearLayout middleLayout = principalLayout.findViewById(R.id.middle_layout);
        txtDesc = middleLayout.findViewById(R.id.txt_desc);
        txtDesc.setText(prod.getDescripcion());

        LinearLayout bottomLayout = principalLayout.findViewById(R.id.bottom_layout);
        chkDiabeticos = bottomLayout.findViewById(R.id.chk_diabeticos);
        chkDiabeticos.setChecked(prod.getApto_diabeticos());

        chkCeliacos = bottomLayout.findViewById(R.id.chk_celiacos);
        chkCeliacos.setChecked(prod.getApto_celiacos());

        imgProd = principalLayout.findViewById(R.id.img_prod);
        DescargaImagenAsyncTask taskImg = new DescargaImagenAsyncTask() {
            @Override
            protected void onPostExecute(final Bitmap response) {
                imgProd.setImageBitmap(response);
            }
        };
        taskImg.execute(prod.getUrl());
        return principalLayout;
    }
}