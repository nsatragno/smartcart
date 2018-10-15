package ar.com.smartcart.smartcart.presentacion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import ar.com.smartcart.smartcart.ListaUsuarioFragment.OnListFragmentInteractionListener;
import ar.com.smartcart.smartcart.R;
import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.ListaUsuario;

import java.util.List;

public class ListaUsuarioViewAdapter extends RecyclerView.Adapter<ListaUsuarioViewAdapter.ViewHolder> {

    private final List<ListaUsuario> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ListaUsuarioViewAdapter(List<ListaUsuario> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lista_usuario_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.rdbActiva.setSelected(mValues.get(position).getActiva());
        holder.txtNombre.setText(mValues.get(position).getNombre());

        holder.rdbActiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(ListaUsuario lista : mValues){
                    if(!holder.mItem.getId().equals(lista.getId())){
                        holder.rdbActiva.setSelected(Boolean.FALSE);
                        lista.setActiva(Boolean.FALSE);
                    }
                }
                DBHelper.getInstance(holder.mView.getContext())
                        .activarListaUsuario(holder.mItem.getId());
                notifyDataSetChanged();
            }
        });
        holder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                    notifyDataSetChanged();
                }
            }
        });
        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.getInstance(holder.mView.getContext())
                        .borrarListaUsuario(holder.mItem.getId());
                mValues.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final RadioButton rdbActiva;
        public final TextView txtNombre;
        public final ImageView editView;
        public final ImageView deleteView;

        public ListaUsuario mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            rdbActiva = (RadioButton) view.findViewById(R.id.rdb_activa);
            txtNombre = (TextView) view.findViewById(R.id.nombre_lista);
            editView = (ImageView) view.findViewById(R.id.ico_edit);
            deleteView = (ImageView) view.findViewById(R.id.ico_delete);
        }
    }
}