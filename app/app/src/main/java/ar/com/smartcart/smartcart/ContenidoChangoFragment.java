package ar.com.smartcart.smartcart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.smartcart.smartcart.communication.ProductoManager;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;
import ar.com.smartcart.smartcart.presentacion.ProductoEnChangoViewAdapter;

public class ContenidoChangoFragment extends android.support.v4.app.Fragment {

    private OnListFragmentInteractionListener mListener;
    private TextView txtCantidad;
    private TextView txtTotal;
    private RecyclerView recyclerView;
    private Button btnPagoEfvo;
    private Button btnPagoTarj;
    private ProductoEnChangoViewAdapter adapter;

    public ContenidoChangoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout principalLayout = (LinearLayout) inflater.inflate(
                                R.layout.fragment_contenido_chango, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Contenido del Chango");

        recyclerView = (RecyclerView) principalLayout.getChildAt(1);
        DividerItemDecoration div = new DividerItemDecoration(recyclerView.getContext(),
                                                                LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(div);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        LinearLayout segLayout = (LinearLayout) principalLayout.getChildAt(2);
        txtCantidad = segLayout.findViewById(R.id.txt_cantidad);
        txtTotal = segLayout.findViewById(R.id.txt_total);

        btnPagoEfvo = segLayout.findViewById(R.id.btn_pago_efvo);
        btnPagoEfvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PrincipalActivity)getActivity()).setFragment
                            (((PrincipalActivity)getActivity()).PAGO_EFECTIVO, null);
            }
        });
        btnPagoTarj = segLayout.findViewById(R.id.btn_pago_tarj);
        btnPagoTarj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Pago con Tarjeta");
                builder.setMessage("¿Estás seguro que querés realizar el pago de tu compra" +
                        " con tarjeta?");

                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Pago realizado con éxito.",
                                                                Toast.LENGTH_LONG).show();
                        dialog.cancel();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        // Sets adapter
        updateView();
        return principalLayout;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_contenido_chango) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ProductoEnLista item);
    }

    public ProductoEnChangoViewAdapter getAdapter() {
        return adapter;
    }

    public void updateView(){
        adapter = (ProductoEnChangoViewAdapter) recyclerView.getAdapter();
        Chango chango = ((PrincipalActivity) getActivity()).getChango();
        if(chango != null){
            if(adapter != null){
                adapter.setmValues(chango.getProductos());
                adapter.notifyDataSetChanged();
            }else{
                adapter = new ProductoEnChangoViewAdapter(chango.getProductos(), mListener);
                recyclerView.setAdapter(adapter);
            }
            txtCantidad.setText("Cantidad: " + chango.getCantidadTotal().toString());
            txtTotal.setText("Total: " + ProductoManager.convertirEnPrecio(
                    chango.getTotal()));
        }
    }
}