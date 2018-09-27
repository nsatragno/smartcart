package ar.com.smartcart.smartcart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ar.com.smartcart.smartcart.ListaProductoFragment.OnListFragmentInteractionListener;
import ar.com.smartcart.smartcart.modelo.ProductoEnLista;

import java.util.List;

public class ProductoViewAdapter extends RecyclerView.Adapter<ProductoViewAdapter.ViewHolder> {

    private final List<ProductoEnLista> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ProductoViewAdapter(List<ProductoEnLista> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_producto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.txtDescripcion.setText(mValues.get(position).getProducto().getNombre());
        holder.txtCantidad.setText(mValues.get(position).getCantidad().toString());
        holder.txtPrecio.setText(mValues.get(position).getSubtotal().toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
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
        public final TextView txtDescripcion;
        public final TextView txtCantidad;
        public final TextView txtPrecio;

        public ProductoEnLista mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtDescripcion = (TextView) view.findViewById(R.id.desc_producto);
            txtCantidad = (TextView) view.findViewById(R.id.cant_producto);
            txtPrecio = (TextView) view.findViewById(R.id.precio_producto);
        }
    }
}
