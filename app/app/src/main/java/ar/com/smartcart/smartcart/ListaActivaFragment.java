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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ar.com.smartcart.smartcart.communication.ProductosManager;
import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.ListaUsuario;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.presentacion.ListaActivaViewAdapter;
import ar.com.smartcart.smartcart.presentacion.ProductoEnChangoViewAdapter;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class ListaActivaFragment extends android.support.v4.app.Fragment {

    private OnListFragmentInteractionListener mListener;
    private TextView txtSeleccion;
    private TextView txtPendiente;
    private RecyclerView recyclerView;
    private ListaUsuario listaActiva;

    public ListaActivaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout principalLayout = (LinearLayout) inflater.inflate(
                R.layout.fragment_lista_activa, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Lista Activa");

        recyclerView = (RecyclerView) principalLayout.getChildAt(1);
        DividerItemDecoration div = new DividerItemDecoration(recyclerView.getContext(),
                                                                        LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(div);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        LinearLayout bottomLayout = (LinearLayout) principalLayout.getChildAt(2);
        txtSeleccion = bottomLayout.findViewById(R.id.txt_seleccion);
        txtPendiente = bottomLayout.findViewById(R.id.txt_pendiente);

        //Lista activa de DB
        listaActiva = DBHelper.getInstance(context).getListaUsuarioActiva();

        // Sets adapter
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
        ListaActivaViewAdapter adapter = (ListaActivaViewAdapter) recyclerView.getAdapter();
        Chango chango = ((PrincipalActivity) getActivity()).getChango();
        if(chango != null){
            if(adapter != null){
                listaActiva = new ListaUsuario();
                listaActiva.setNombre("Mi Señora");
                listaActiva.setProductos(chango.getProductos());
                listaActiva.getProductos().get(3).setEnChango(Boolean.FALSE);
                listaActiva.getProductos().get(2).setEnChango(Boolean.FALSE);
                listaActiva.getProductos().get(1).setEnChango(Boolean.TRUE);
                listaActiva.getProductos().get(0).setEnChango(Boolean.TRUE);
                Collections.sort(listaActiva.getProductos(), new Comparator<ProductoEnLista>() {
                    @Override
                    public int compare(ProductoEnLista p1, ProductoEnLista p2) {
                        return Boolean.compare(p1.getEnChango(), p2.getEnChango());
                    }
                });
                adapter.setmValues(listaActiva.getProductos());
                adapter.notifyDataSetChanged();
            }else{
                if(listaActiva.getId() != null){
                    ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(listaActiva.getNombre());
                }else{
                    //Lista activa de DB
                    Context context = recyclerView.getContext();
                    listaActiva = DBHelper.getInstance(context).getListaUsuarioActiva();
                    if(listaActiva.getId() == null){
                        listaActiva = new ListaUsuario();
                        listaActiva.setNombre("Mi Señora");
                        listaActiva.setProductos(chango.getProductos());
                        listaActiva.getProductos().get(3).setEnChango(Boolean.FALSE);
                        listaActiva.getProductos().get(2).setEnChango(Boolean.FALSE);
                        listaActiva.getProductos().get(1).setEnChango(Boolean.TRUE);
                        listaActiva.getProductos().get(0).setEnChango(Boolean.TRUE);
                        Collections.sort(listaActiva.getProductos(), new Comparator<ProductoEnLista>() {
                            @Override
                            public int compare(ProductoEnLista p1, ProductoEnLista p2) {
                                return Boolean.compare(p1.getEnChango(), p2.getEnChango());
                            }
                        });
                    }
                }
                ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(listaActiva.getNombre());
                adapter = new ListaActivaViewAdapter(listaActiva.getProductos(), mListener);
                recyclerView.setAdapter(adapter);
            }
            txtPendiente.setText("Pendientes: " + listaActiva.getPendiente().toString());
            txtSeleccion.setText("Seleccionados: " + listaActiva.getSeleccionado().toString());
        }
    }
}