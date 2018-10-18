package ar.com.smartcart.smartcart.presentacion;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import ar.com.smartcart.smartcart.EditListaFragment;
import ar.com.smartcart.smartcart.R;
import ar.com.smartcart.smartcart.communication.DescargaImagenAsyncTask;
import ar.com.smartcart.smartcart.database.DBHelper;

public class EditListaViewAdapter extends RecyclerView.Adapter<EditListaViewAdapter.ViewHolder> {

    private List<ProductoEnLista> mValues;
    private final EditListaFragment.OnListFragmentInteractionListener mListener;

    public EditListaViewAdapter(List<ProductoEnLista> items, EditListaFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_edit_lista_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.txtNombre.setText(holder.mItem.getProducto().getNombre());
        holder.txtCantidad.setText(holder.mItem.getCantidad().toString());
        holder.txtCantidad.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                holder.mItem.setCantidad(Long.parseLong(holder.txtCantidad.getText().toString()));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValues.remove(position);
                notifyDataSetChanged();
            }
        });

        //Ejecutar descarga imagen
        DescargaImagenAsyncTask task = new DescargaImagenAsyncTask(){
            @Override
            protected void onPostExecute(final Bitmap response) {
                holder.imgProd.setImageBitmap(response);
            }
        };
        task.execute(holder.mItem.getProducto().getUrl());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imgProd;
        public final TextView txtNombre;
        public final EditText txtCantidad;
        public final ImageView imgDelete;

        public ProductoEnLista mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imgProd = (ImageView) view.findViewById(R.id.img_prod);
            imgDelete = (ImageView) view.findViewById(R.id.ico_delete);
            txtNombre = (TextView) view.findViewById(R.id.nombre_prod);
            txtCantidad = (EditText) view.findViewById(R.id.cant_prod);
            txtCantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    public List<ProductoEnLista> getmValues() {
        return mValues;
    }

    public void setmValues(List<ProductoEnLista> mValues) {
        this.mValues = mValues;
    }
}