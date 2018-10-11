package ar.com.smartcart.smartcart;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import ar.com.smartcart.smartcart.presentacion.EditListaAdapter;

public class EditListaUsuarioActivity extends AppCompatActivity {

    private EditListaAdapter customAdapter;
    ListView listView;
    Cursor cursor;
    private final static String TAG = EditListaUsuarioActivity.class.getName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lista_usuario);
        customAdapter = new EditListaAdapter(EditListaUsuarioActivity.this,  cursor, 0);
        listView = (ListView) findViewById(R.id.list_prod);
        listView.setAdapter(customAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit ");
//                cursor=studentRepo.getStudentListByKeyword(s);
                customAdapter.swapCursor(cursor);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange ");
//                cursor=studentRepo.getStudentListByKeyword(s);
                if (cursor != null){
                    customAdapter.swapCursor(cursor);
                }
                return false;
            }
        });
        return true;
    }
}