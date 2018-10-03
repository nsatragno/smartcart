package ar.com.smartcart.smartcart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    // TODO: Customize parameters
    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener;

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
        View view = inflater.inflate(R.layout.fragment_contenido_chango, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Contenido del Chango");
        Chango chango = ((PrincipalActivity) getActivity()).getChango();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            chango.getProductos().add(chango.getProductos().get(0));
            recyclerView.setAdapter(new ProductoEnChangoViewAdapter(chango.getProductos(), mListener));
        }
        return view;
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
}