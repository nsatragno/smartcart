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
import ar.com.smartcart.smartcart.ContenidoChangoFragment.OnListFragmentInteractionListener;
import ar.com.smartcart.smartcart.R;
import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;
import ar.com.smartcart.smartcart.communication.ProductosManager;

import java.util.ArrayList;
import java.util.List;

public class ProductoEnChangoViewAdapter extends RecyclerView.Adapter<ProductoEnChangoViewAdapter.ViewHolder>
        implements Filterable {

    private List<ProductoEnLista> mValues;
    private List<ProductoEnLista> mValuesFiltered;
    private final OnListFragmentInteractionListener mListener;

    public ProductoEnChangoViewAdapter(List<ProductoEnLista> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mValuesFiltered = items;
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

        //Ejecutar descarga imagen
        DescargaImagenAsyncTask task = new DescargaImagenAsyncTask(){
            @Override
            protected void onPostExecute(final Bitmap response) {
                holder.imgProd.setImageBitmap(response);
            }
        };
        task.execute(holder.mItem.getProducto().getUrl());

        holder.txtNombre.setText(holder.mItem.getProducto().getNombre());
        holder.txtCantidad.setText(holder.mItem.getCantidad().toString());
        holder.txtPrecio.setText(ProductosManager.convertirEnPrecio(holder.mItem.getSubtotal()));

        holder.txtNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mValuesFiltered = mValues;
                } else {
                    List<ProductoEnLista> filteredList = new ArrayList<>();
                    for (ProductoEnLista row : mValues) {

                        if (row.getProducto().getNombre().toLowerCase().contains
                                (charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    mValuesFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mValuesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mValuesFiltered = (ArrayList<ProductoEnLista>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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

        public ProductoEnLista mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtNombre = (TextView) view.findViewById(R.id.nombre_prod);
            txtCantidad = (TextView) view.findViewById(R.id.cant_prod);
            txtPrecio = (TextView) view.findViewById(R.id.precio_prod);
            imgProd = (ImageView) view.findViewById(R.id.img_prod);
        }
    }

    public List<ProductoEnLista> getmValues() {
        return mValues;
    }

    public void setmValues(List<ProductoEnLista> mValues) {
        this.mValues = mValues;
    }
}