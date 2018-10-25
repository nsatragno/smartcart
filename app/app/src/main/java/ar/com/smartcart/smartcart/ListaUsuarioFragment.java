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
import android.widget.LinearLayout;

import java.util.ArrayList;

import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.ListaUsuario;
import ar.com.smartcart.smartcart.presentacion.ListaUsuarioViewAdapter;

public class ListaUsuarioFragment extends android.support.v4.app.Fragment {

    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    public ListaUsuarioFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup principalLayout = (ViewGroup) inflater.inflate(
                R.layout.fragment_lista_usuario, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Administración de Listas");

        recyclerView = (RecyclerView) ((ViewGroup) principalLayout.getChildAt(0)).getChildAt(1);
        DividerItemDecoration div = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(div);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ArrayList<ListaUsuario> listas = DBHelper.getInstance(
                context).getAllPlainListaUsuario();

        FloatingActionButton fbtnNew = principalLayout.findViewById(R.id.fbtn_nueva_list);
        fbtnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PrincipalActivity) getActivity()).setFragment
                        (((PrincipalActivity) getActivity()).EDIT_LISTA, null);
            }
        });
        recyclerView.setAdapter(new ListaUsuarioViewAdapter(listas, mListener));
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
        void onListFragmentInteraction(ListaUsuario item);
    }
}