package ar.com.smartcart.smartcart.presentacion;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ar.com.smartcart.smartcart.ContenidoChangoFragment.OnListFragmentInteractionListener;
import ar.com.smartcart.smartcart.R;
import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;
import ar.com.smartcart.smartcart.communication.ProductoManager;
import java.util.List;

public class ProductoEnChangoViewAdapter extends RecyclerView.Adapter<ProductoEnChangoViewAdapter.ViewHolder> {

    private List<ProductoEnLista> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ProductoEnChangoViewAdapter(List<ProductoEnLista> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contenido_chango_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.setImagen(holder.mItem.getProducto().getUrl());
        holder.txtNombre.setText(holder.mItem.getProducto().getNombre());
        holder.txtCantidad.setText(holder.mItem.getCantidad().toString());
        holder.txtPrecio.setText(ProductoManager.convertirEnPrecio(holder.mItem.getSubtotal()));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtNombre;
        public final TextView txtCantidad;
        public final TextView txtPrecio;
        public final ImageView imgProd;
        private DescargaImagenAsyncTask taskDescargarImagen;

        public ProductoEnLista mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtNombre = view.findViewById(R.id.nombre_prod);
            txtCantidad = view.findViewById(R.id.cant_prod);
            txtPrecio = view.findViewById(R.id.precio_prod);
            imgProd = view.findViewById(R.id.img_prod);
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

    public void setmValues(List<ProductoEnLista> mValues) {
        this.mValues = mValues;
    }
}