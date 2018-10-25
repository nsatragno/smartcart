package ar.com.smartcart.smartcart.presentacion;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import ar.com.smartcart.smartcart.R;
import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;
import ar.com.smartcart.smartcart.modelo.Producto;

public class BusquedaProductoViewAdapter
        extends RecyclerView.Adapter<BusquedaProductoViewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Producto item);
    }

    private List<Producto> mValues;
    private List<Producto> mValuesFiltered;
    private final OnItemClickListener listener;

    public BusquedaProductoViewAdapter(List<Producto> items, OnItemClickListener listener) {
        mValues = items;
        mValuesFiltered = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_busqueda_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.txtNombre.setText(holder.mItem.getNombre());
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.mItem);
            }
        });

        //Ejecutar descarga imagen
        DescargaImagenAsyncTask task = new DescargaImagenAsyncTask(){
            @Override
            protected void onPostExecute(final Bitmap response) {
                holder.imgProd.setImageBitmap(response);
            }
        };
        task.execute(holder.mItem.getUrl());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imgProd;
        public final TextView txtNombre;
        public final ImageView imgAdd;

        public Producto mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imgProd = view.findViewById(R.id.img_prod);
            imgAdd = view.findViewById(R.id.ico_add);
            txtNombre = view.findViewById(R.id.nombre_prod);
        }
    }

    public List<Producto> getmValues() {
        return mValues;
    }

    public void setmValues(List<Producto> mValues) {
        this.mValues = mValues;
    }
}