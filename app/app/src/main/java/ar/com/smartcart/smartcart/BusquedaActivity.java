package ar.com.smartcart.smartcart;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import ar.com.smartcart.smartcart.communication.ProductoManager;
import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.presentacion.BusquedaProductoViewAdapter;

public class BusquedaActivity extends AppCompatActivity {
    private SearchView searchView;
    private Context context;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        context = getApplicationContext();
        recyclerView = findViewById(R.id.lista_all_prods);
        DividerItemDecoration div = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(div);
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //Obtiene todos los productos de la DB
        ArrayList<Producto> productos = DBHelper.getInstance(context).getAllProductos();
        BusquedaProductoViewAdapter adapter = new BusquedaProductoViewAdapter(productos,
                new BusquedaProductoViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Producto item) {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(ProductoManager.PRODUCTO_ENCONTRADO, item.getId());
                            setResult(Activity.RESULT_OK, resultIntent);
                        Toast.makeText(BusquedaActivity.this,
                                item.getNombre() + " agregado.", Toast.LENGTH_LONG).show();
                        finish();
                        }
                });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        this.getSupportActionBar().setTitle("Búsqueda de Productos");

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Producto> result = DBHelper.getInstance(context).getProductosLikeNombre(query);
                ((BusquedaProductoViewAdapter)recyclerView.getAdapter()).setmValues(result);
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                ArrayList<Producto> result = DBHelper.getInstance(context).getProductosLikeNombre(query);
                ((BusquedaProductoViewAdapter)recyclerView.getAdapter()).setmValues(result);
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                return true;
            case R.id.action_search :
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}