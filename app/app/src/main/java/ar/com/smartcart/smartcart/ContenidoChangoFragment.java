package ar.com.smartcart.smartcart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import ar.com.smartcart.smartcart.communication.ProductosManager;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;
import ar.com.smartcart.smartcart.presentacion.ProductoEnChangoViewAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ContenidoChangoFragment extends android.support.v4.app.Fragment {

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private TextView txtCantidad;
    private TextView txtTotal;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
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
        LinearLayout bottomLayout = (LinearLayout) principalLayout.getChildAt(2);
        txtCantidad = bottomLayout.findViewById(R.id.txtCantidad);
        txtTotal = bottomLayout.findViewById(R.id.txtTotal);

        // Set the adapter
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        updateView();
        return principalLayout;
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

    public void updateView(){
        ProductoEnChangoViewAdapter adapter = (ProductoEnChangoViewAdapter) recyclerView.getAdapter();
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