package ar.com.smartcart.smartcart.presentacion;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import ar.com.smartcart.smartcart.ListaActivaFragment.OnListFragmentInteractionListener;
import ar.com.smartcart.smartcart.R;
import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;

import java.util.List;

public class ListaActivaViewAdapter extends RecyclerView.Adapter<ListaActivaViewAdapter.ViewHolder> {

    private final List<ProductoEnLista> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ListaActivaViewAdapter(List<ProductoEnLista> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lista_activa_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        //Ejecutar descarga imagen
        DescargaImagenAsyncTask task = new DescargaImagenAsyncTask(){
            @Override
            protected void onPostExecute(final Bitmap response) {
                holder.imgProd.setImageBitmap(response);
            }
        };
        task.execute(holder.mItem.getProducto().getUrl());

        holder.chkEnChango.setSelected(holder.mItem.getEnChango());
        holder.txtNombre.setText(holder.mItem.getProducto().getNombre());
        holder.txtCantidad.setText(holder.mItem.getCantidad().toString());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CheckBox chkEnChango;
        public final ImageView imgProd;
        public final TextView txtNombre;
        public final TextView txtCantidad;

        public ProductoEnLista mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            chkEnChango = (CheckBox) view.findViewById(R.id.chk_en_chango);
            imgProd = (ImageView) view.findViewById(R.id.img_prod);
            txtNombre = (TextView) view.findViewById(R.id.nombre_prod);
            txtCantidad = (TextView) view.findViewById(R.id.cant_prod);
        }
    }
}
