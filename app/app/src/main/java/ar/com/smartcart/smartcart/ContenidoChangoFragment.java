package ar.com.smartcart.smartcart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import org.w3c.dom.Text;

import ar.com.smartcart.smartcart.communication.ProductosManager;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;
import ar.com.smartcart.smartcart.presentacion.ProductoEnChangoViewAdapter;

public class ContenidoChangoFragment extends android.support.v4.app.Fragment {

    private OnListFragmentInteractionListener mListener;
    private TextView txtCantidad;
    private TextView txtTotal;
    private RecyclerView recyclerView;
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

        LinearLayout bottomLayout = (LinearLayout) principalLayout.getChildAt(2);
        txtCantidad = bottomLayout.findViewById(R.id.txt_cantidad);
        txtTotal = bottomLayout.findViewById(R.id.txt_total);

        // Sets adapter
        updateView();
        return principalLayout;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
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
            txtTotal.setText("Total: " + ProductosManager.convertirEnPrecio(
                    chango.getTotal()));
        }
    }
}