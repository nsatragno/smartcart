package ar.com.smartcart.smartcart.presentacion;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import ar.com.smartcart.smartcart.R;

public class EditListaAdapter extends CursorAdapter {
    private LayoutInflater mInflater;

    public EditListaAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.activity_edit_lista_usuario_row,
                                                parent, false);
        ViewHolder holder = new ViewHolder();
        holder.txtId = (TextView)  view.findViewById(R.id.txtId);
        holder.txtName = (TextView)  view.findViewById(R.id.txtName);
        holder.txtEmail = (TextView)  view.findViewById(R.id.txtEmail);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //If you want to have zebra lines color effect uncomment below code
        /*if(cursor.getPosition()%2==1) {
             view.setBackgroundResource(R.drawable.item_list_backgroundcolor);
        } else {
            view.setBackgroundResource(R.drawable.item_list_backgroundcolor2);
        }*/
        ViewHolder holder  =   (ViewHolder)    view.getTag();
        holder.txtId.setText(cursor.getString(cursor.getColumnIndex("ID")));
        holder.txtName.setText(cursor.getString(cursor.getColumnIndex("NAME")));
        holder.txtEmail.setText(cursor.getString(cursor.getColumnIndex("EMAIL")));
    }

    static class ViewHolder {
        TextView txtId;
        TextView txtName;
        TextView txtEmail;
    }
}