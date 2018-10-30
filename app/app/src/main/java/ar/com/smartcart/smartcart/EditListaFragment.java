package ar.com.smartcart.smartcart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import ar.com.smartcart.smartcart.communication.ProductoManager;
import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.ListaUsuario;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.presentacion.EditListaViewAdapter;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class EditListaFragment extends android.support.v4.app.Fragment {

    private OnListFragmentInteractionListener mListener;
    private Button btnGuardar;
    private Button btnCancelar;
    private EditText txtNombreLista;
    private RecyclerView recyclerView;
    private Context context;

    private ListaUsuario lista;

    public ListaUsuario getLista() {
        return lista;
    }

    public void setLista(ListaUsuario lista) {
        this.lista = lista;
    }

    public EditListaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout principalLayout = (LinearLayout) inflater.inflate(
                R.layout.fragment_edit_lista, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Edición de Lista");

        LinearLayout topLayout = principalLayout.findViewById(R.id.top_layout);
        txtNombreLista = topLayout.findViewById(R.id.txt_nombre_lista);
        txtNombreLista.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                lista.setNombre(txtNombreLista.getText().toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        recyclerView = principalLayout.findViewById(R.id.lista_usuario_edit);

        DividerItemDecoration div = new DividerItemDecoration(recyclerView.getContext(),
                                                                    LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(div);
        context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        LinearLayout bottomLayout = principalLayout.findViewById(R.id.bottom_layout);
        btnGuardar = bottomLayout.findViewById(R.id.btn_guardar_lista);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lista.getId() == null){
                    DBHelper.getInstance(context).insertarListaUsuario(lista);
                }else{
                    DBHelper.getInstance(context).actualizarListaUsuario(lista);
                }
                Toast.makeText(getActivity(), lista.getNombre() + " guardada.", Toast.LENGTH_LONG).show();
                ((PrincipalActivity) getActivity()).setFragment(
                                    ((PrincipalActivity) getActivity()).ADMIN_LISTAS, null);
            }
        });
        btnCancelar = bottomLayout.findViewById(R.id.btn_cancelar_lista);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Edición de lista cancelada.", Toast.LENGTH_LONG).show();
                ((PrincipalActivity) getActivity()).setFragment(
                                    ((PrincipalActivity) getActivity()).ADMIN_LISTAS, null);
            }
        });

        FloatingActionButton fbtnNew = principalLayout.findViewById(R.id.fbtn_nuevo_prod);
        fbtnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BusquedaActivity.class);
                startActivityForResult(intent, ((PrincipalActivity) getActivity()).BUSCADOR);
            }
        });
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
        if(lista != null){
            //Es una edicion
            lista = DBHelper.getInstance(context).getListaUsuario(lista.getId());
        }else{
            lista = new ListaUsuario();
        }
        EditListaViewAdapter adapter = (EditListaViewAdapter) recyclerView.getAdapter();
        if(adapter == null){
            adapter = new EditListaViewAdapter(lista.getProductos(), mListener);
            recyclerView.setAdapter(adapter);
        }else{
            adapter.setmValues(lista.getProductos());
            adapter.notifyDataSetChanged();
        }
        if(lista.getNombre() != null){
            txtNombreLista.setText(lista.getNombre());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PrincipalActivity.BUSCADOR: {
                if (resultCode == Activity.RESULT_OK) {
                    Long id = (Long) data.getExtras().getLong(ProductoManager.PRODUCTO_ENCONTRADO);
                    Producto prod = DBHelper.getInstance(context).getProducto(id);
                    Boolean enLista = Boolean.FALSE;
                    for (ProductoEnLista prodLista : lista.getProductos()) {
                        if(prodLista.getProducto().compareTo(prod) == 0){
                            prodLista.setCantidad(prodLista.getCantidad() + 1);
                            enLista = Boolean.TRUE;
                            break;
                        }
                    }
                    if(!enLista){
                        lista.getProductos().add(ProductoEnLista.parseEnLista(prod));
                    }
                    EditListaViewAdapter adapter = (EditListaViewAdapter) recyclerView.getAdapter();
                    adapter.setmValues(lista.getProductos());
                    adapter.notifyDataSetChanged();
                }
                break;
            }
        }
    }
}