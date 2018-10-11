package ar.com.smartcart.smartcart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.ListaUsuario;
import ar.com.smartcart.smartcart.presentacion.ListaUsuarioViewAdapter;

public class ListaUsuarioFragment extends android.support.v4.app.Fragment {

    private OnListFragmentInteractionListener mListener;

    public ListaUsuarioFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate
                        (R.layout.fragment_lista_usuario, container, false);
        Context context = view.getContext();

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Administración de Listas");
        ArrayList<ListaUsuario> listas = DBHelper.getInstance(
                context).getAllPlainListaUsuario();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        view.setLayoutManager(layoutManager);
        DividerItemDecoration div = new DividerItemDecoration(view.getContext(),
                layoutManager.getOrientation());
        view.addItemDecoration(div);

//        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.btn_new_list);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent in = new Intent(getActivity(), EditListaUsuarioActivity.class);
//                startActivity(in);
//            }
//        });

        if(listas.isEmpty()){
            listas = new ArrayList<ListaUsuario>();
            ListaUsuario l1 = new ListaUsuario();
            l1.setId(1l);
            l1.setActiva(true);
            l1.setNombre("Mi señora");
            listas.add(l1);
            ListaUsuario l2 = new ListaUsuario();
            l2.setId(2l);
            l2.setActiva(false);
            l2.setNombre("Lionel List");
            listas.add(l2);
        }
        view.setAdapter(new ListaUsuarioViewAdapter(listas, mListener));
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
        void onListFragmentInteraction(ListaUsuario item);
    }
}