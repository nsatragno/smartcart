package ar.com.smartcart.smartcart;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;
import ar.com.smartcart.smartcart.communication.ProductoManager;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.presentacion.ProductoEnChangoViewAdapter;

public class PagoEfectivoFragment extends android.support.v4.app.Fragment {

    private TextView txtCodChango;
    private TextView txtTotal;
    private ImageView imgCajas;
    private TextView txtCajas;
    private Context context;

    public PagoEfectivoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout principalLayout = (LinearLayout) inflater.inflate(
                R.layout.fragment_pago_efvo, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Pago en Efectivo");
        context = getActivity().getApplicationContext();

        txtCodChango = principalLayout.findViewById(R.id.txt_cod_chango);
        txtTotal = principalLayout.findViewById(R.id.txt_total);
        imgCajas = principalLayout.findViewById(R.id.ico_cajas);
        txtCajas = principalLayout.findViewById(R.id.txt_cajas);
        updateView();
        return principalLayout;
    }

    public void updateView(){
        Chango chango = ((PrincipalActivity) getActivity()).getChango();
        if(chango != null){
            String codChango = "Código de Chango" + "\r\n" + "<b>" + chango.getCodigo() + "</b>";
            txtCodChango.setText(Html.fromHtml(codChango));
            String total = "Total a Pagar " + "\r\n" + "<b>" + ProductoManager.convertirEnPrecio(
                                                    chango.getTotal()) + "</b>";
            txtTotal.setText(Html.fromHtml(total));
        }
    }
}