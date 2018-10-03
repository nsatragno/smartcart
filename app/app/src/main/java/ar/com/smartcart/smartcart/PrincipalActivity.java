package ar.com.smartcart.smartcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.smartcart.smartcart.communication.ContenidoChangoAsyncTask;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    ContenidoChangoFragment.OnListFragmentInteractionListener,
                    QRScanFragment.OnFragmentInteractionListener{

    public static final int INICIO = 0;
    public static final int QR_SCAN = 1;
    public static final int ADMIN_LIST = 2;

    private Chango chango;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        chango = new Chango();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setFragment(INICIO);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.setChecked(true);

        if (id == R.id.nav_camera) {
            setFragment(QR_SCAN);
        } else if (id == R.id.nav_gallery) {
            setFragment(ADMIN_LIST);
        } else if (id == R.id.nav_slideshow) {
            setFragment(INICIO);
        } else if (id == R.id.nav_manage) {
            setFragment(INICIO);
        } else if (id == R.id.nav_share) {
            setFragment(ADMIN_LIST);
        } else if (id == R.id.nav_send) {
            setFragment(ADMIN_LIST);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(int position) {
        FragmentManager fragMng = getSupportFragmentManager();
        switch (position) {
            case INICIO:
                InicioFragment iniFrag = new InicioFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, iniFrag).commit();
                break;
            case QR_SCAN:
                QRScanFragment qrFrag = new QRScanFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, qrFrag).commit();
                break;
            case ADMIN_LIST:
                ContenidoChangoFragment listFrag = new ContenidoChangoFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, listFrag).commit();
                break;
        }
    }


    public Chango getChango() {
        return chango;
    }

    public void setChango(Chango chango) {
        this.chango = chango;
    }

    @Override
    public void onFragmentInteraction(Long changoID, String changoCod) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView txt = (TextView) navigationView.findViewById(R.id.textView);
        txt.setText(changoCod);
        ContenidoChangoAsyncTask task = new ContenidoChangoAsyncTask(){
            @Override
            protected void onPostExecute(final Chango response) {
                setChango(response);
                setFragment(ADMIN_LIST);
            }
        };
        task.execute(changoID);
    }

    @Override
    public void onListFragmentInteraction(ProductoEnLista item) {
        Toast.makeText(this, item.getProducto().getDescripcion(), Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(PrincipalActivity.this, DescProductoActivity.class);;
        startActivity(myIntent);
    }
}