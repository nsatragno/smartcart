package ar.com.smartcart.smartcart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.ListaUsuario;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.presentacion.ListaActivaViewAdapter;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class ListaActivaFragment extends android.support.v4.app.Fragment {

    private OnListFragmentInteractionListener mListener;

    public ListaActivaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate
                (R.layout.fragment_lista_activa, container, false);
        Context context = view.getContext();

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Lista Activa");
        ListaUsuario listaActiva = DBHelper.getInstance(
                context).getListaUsuarioActiva();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        view.setLayoutManager(layoutManager);
        DividerItemDecoration div = new DividerItemDecoration(view.getContext(),
                layoutManager.getOrientation());
        view.addItemDecoration(div);

        // Set the adapter
        if(listaActiva != null){
            listaActiva.setNombre("Marquitos List");
            ArrayList<Producto> prods= DBHelper.getInstance(
                    context).getAllProductos();

            for(Producto p : prods){
                ProductoEnLista pe = new ProductoEnLista();
                pe.setCantidad(2l);
                pe.setEnChango(Boolean.TRUE);
                pe.setProducto(p);
                listaActiva.getProductos().add(pe);
            }
            view.setAdapter(new ListaActivaViewAdapter(listaActiva.getProductos(), mListener));
            ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Lista Activa: " +
                                        listaActiva.getNombre());
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