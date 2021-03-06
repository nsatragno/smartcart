package ar.com.smartcart.smartcart.presentacion;

import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import ar.com.smartcart.smartcart.ListaActivaFragment.OnListFragmentInteractionListener;
import ar.com.smartcart.smartcart.R;
import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;

import java.util.List;

public class ListaActivaViewAdapter extends RecyclerView.Adapter<ListaActivaViewAdapter.ViewHolder> {

    private List<ProductoEnLista> mValues;
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
        holder.txtNombre.setText(holder.mItem.getProducto().getNombre());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
        holder.txtCantidad.setText(holder.mItem.getCantidad().toString());
        holder.setImagen(holder.mItem.getProducto().getUrl());
        marcarComoSeleccionado(holder);
    }

    public void marcarComoSeleccionado(ViewHolder holder){
        if(holder.mItem.getEnChango()){
            holder.txtNombre.setPaintFlags(holder.txtNombre.getPaintFlags()
                                                        | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtNombre.setTypeface(holder.txtNombre.getTypeface(), Typeface.ITALIC);

            //Imagen Blanco y Negro
            if(holder.imgProd != null){
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                holder.imgProd.setColorFilter(filter);
            }
        }else{
            holder.txtNombre.setPaintFlags(0);
            holder.txtNombre.setTypeface(holder.txtNombre.getTypeface(), Typeface.NORMAL);

            //Imagen Color
            if(holder.imgProd != null){
                holder.imgProd.setColorFilter(null);
            }
        }
        holder.chkEnChango.setChecked(holder.mItem.getEnChango());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CheckBox chkEnChango;
        private final ImageView imgProd;
        public final TextView txtNombre;
        public final TextView txtCantidad;
        private DescargaImagenAsyncTask taskDescargarImagen;

        public ProductoEnLista mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            chkEnChango = view.findViewById(R.id.chk_en_chango);
            chkEnChango.setEnabled(Boolean.FALSE);
            imgProd = view.findViewById(R.id.img_prod);
            txtNombre = view.findViewById(R.id.nombre_prod);
            txtCantidad = view.findViewById(R.id.cant_prod);
        }

        public void setImagen(String url) {
            //Ejecutar descarga imagen
            imgProd.setImageBitmap(null);
            if (taskDescargarImagen != null)
                taskDescargarImagen.cancel(true);
            taskDescargarImagen = new DescargaImagenAsyncTask(){
                @Override
                protected void onPostExecute(final Bitmap response) {
                    imgProd.setImageBitmap(response);
                }
            };
            taskDescargarImagen.execute(url);
        }
    }

    public List<ProductoEnLista> getmValues() {
        return mValues;
    }

    public void actualizarLista(List<ProductoEnLista> newValues) {
        for (int i = 0; i < this.mValues.size();) {
            if (!newValues.contains(this.mValues.get(i))) {
                this.mValues.remove(i);
                notifyItemRemoved(i);
            } else {
                ++i;
            }
        }

        for (ProductoEnLista producto : newValues) {
            int index = this.mValues.indexOf(producto);
            if (index == -1) {
                this.mValues.add(producto);
                notifyItemInserted(this.mValues.size());
            } else {
                if (!this.mValues.get(index).getCantidad().equals(producto.getCantidad()) ||
                        !this.mValues.get(index).getEnChango().equals(producto.getEnChango())) {
                    this.mValues.get(index).setCantidad(producto.getCantidad());
                    this.mValues.get(index).setEnChango(producto.getEnChango());
                    notifyItemChanged(index, producto);
                }
            }
        }
    }
}