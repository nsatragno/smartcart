package ar.com.smartcart.smartcart.presentacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.rdbActiva.setChecked(mValues.get(position).getActiva());
        holder.rdbActiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mItem.setActiva(Boolean.TRUE);
                for(ListaUsuario lista : mValues){
                    if(!holder.mItem.getId().equals(lista.getId())){
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
                }
            }
        });
        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.mView.getContext());
                    builder.setTitle("Eliminación de Lista");

                    String message = "¿Estás seguro que querés eliminar la lista " +
                                        "<b>" + holder.mItem.getNombre() + "</b>" + "?";

                    builder.setMessage(Html.fromHtml(message));

                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            DBHelper.getInstance(holder.mView.getContext())
                                    .borrarListaUsuario(holder.mItem.getId());
                            mValues.remove(position);
                            Toast.makeText(holder.mView.getContext(),
                                    holder.mItem.getNombre() + " eliminada.", Toast.LENGTH_LONG).show();
                            notifyDataSetChanged();
                            dialog.cancel();
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
            rdbActiva = view.findViewById(R.id.rdb_activa);
            txtNombre = view.findViewById(R.id.nombre_lista);
            editView = view.findViewById(R.id.ico_edit);
            deleteView = view.findViewById(R.id.ico_delete);
        }
    }
}