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
        holder.txtCantidad.setText(holder.mItem.getCantidad().toString());

        //Ejecutar descarga imagen
        DescargaImagenAsyncTask task = new DescargaImagenAsyncTask(){
            @Override
            protected void onPostExecute(final Bitmap response) {
                holder.imgProd.setImageBitmap(response);
                marcarComoSeleccionado(holder);
            }
        };
        task.execute(holder.mItem.getProducto().getUrl());
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
        public final ImageView imgProd;
        public final TextView txtNombre;
        public final TextView txtCantidad;

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
    }

    public List<ProductoEnLista> getmValues() {
        return mValues;
    }

    public void setmValues(List<ProductoEnLista> mValues) {
        this.mValues = mValues;
    }
}