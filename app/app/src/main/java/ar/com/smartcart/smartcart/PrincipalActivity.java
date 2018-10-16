package ar.com.smartcart.smartcart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ar.com.smartcart.smartcart.communication.AllProductosAsyncTask;
import ar.com.smartcart.smartcart.communication.ContenidoChangoAsyncTask;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.ListaUsuario;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    ContenidoChangoFragment.OnListFragmentInteractionListener,
                    QRScanFragment.OnFragmentInteractionListener,
                    ListaUsuarioFragment.OnListFragmentInteractionListener,
                    ListaActivaFragment.OnListFragmentInteractionListener {

    public static final String INICIO = "INICIO";
    public static final String QR_SCAN = "QR_SCAN";
    public static final String CONTENIDO_CHANGO = "CONTENIDO_CHANGO";
    public static final String LISTA_ACTIVA = "LISTA_ACTIVA";
    public static final String UBIC_PRODS = "UBIC_PRODS";
    public static final String ADMIN_LISTAS = "ADMIN_LISTAS";
    public static final String DESC_PRODS = "DESC_PRODS";
    public static final String PROMOS = "PROMOS";

    public static final int READ_TIME = 3000;

    private Chango chango;
    private Context context;

    public Chango getChango() {
        return chango;
    }

    public void setChango(Chango chango) {
        this.chango = chango;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        habilitarContenidoChangoMenuItem(Boolean.FALSE);
        navigationView.setNavigationItemSelectedListener(this);

        AllProductosAsyncTask task = new AllProductosAsyncTask(){
            @Override
            protected void onPostExecute(final ArrayList<Producto> response) {
                Toast.makeText(PrincipalActivity.this, "Productos actualizados correctamente.", Toast.LENGTH_LONG).show();
            }
        };
        task.execute(context);
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
        if (id == R.id.action_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.setChecked(true);
        if (id == R.id.nav_camera) {
            setFragment(QR_SCAN);
        } else if (id == R.id.nav_gallery) {
            setFragment(CONTENIDO_CHANGO);
        } else if (id == R.id.nav_slideshow) {
            setFragment(LISTA_ACTIVA);
        } else if (id == R.id.nav_manage) {
            setFragment(UBIC_PRODS);
        } else if (id == R.id.nav_share) {
            setFragment(ADMIN_LISTAS);
        } else if (id == R.id.nav_send) {
            //Promos
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(String position) {
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
            case CONTENIDO_CHANGO:
                ContenidoChangoFragment changoFrag = new ContenidoChangoFragment();
                fragMng.beginTransaction().add(R.id.fragment_container, changoFrag, CONTENIDO_CHANGO).commit();
                fragMng.beginTransaction().replace(R.id.fragment_container, changoFrag).commit();
                break;
            case LISTA_ACTIVA:
                ListaActivaFragment listActFrag = new ListaActivaFragment();
                fragMng.beginTransaction().add(R.id.fragment_container, listActFrag, LISTA_ACTIVA).commit();
                fragMng.beginTransaction().replace(R.id.fragment_container, listActFrag).commit();
                break;
            case ADMIN_LISTAS:
                ListaUsuarioFragment adminListFrag = new ListaUsuarioFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, adminListFrag).commit();
                break;
        }
    }

    public void callContenidoChangoTask(final Long changoID) {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        final Long id = changoID;
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        ContenidoChangoAsyncTask task = new ContenidoChangoAsyncTask(){
                            @Override
                            protected void onPostExecute(final Chango response) {
                                setChango(response);
                                FragmentManager fragMng = getSupportFragmentManager();
                                ContenidoChangoFragment changoFrg = ((ContenidoChangoFragment)fragMng
                                        .findFragmentByTag(CONTENIDO_CHANGO));
                                if(changoFrg != null){
                                    changoFrg.updateView();
                                }else{
                                    ListaActivaFragment listActviaFrg = ((ListaActivaFragment)fragMng
                                            .findFragmentByTag(LISTA_ACTIVA));
                                    if(listActviaFrg != null){
                                        listActviaFrg.updateView();
                                    }
                                }
                            }
                        };
                        task.execute(id);
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, READ_TIME);
    }

    @Override
    public void onFragmentInteraction(Long changoID, String changoCod) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView txt = (TextView) navigationView.findViewById(R.id.textView);
        txt.setText(changoCod);
        callContenidoChangoTask(changoID);
        setFragment(CONTENIDO_CHANGO);
        habilitarContenidoChangoMenuItem(Boolean.TRUE);
    }

    @Override
    public void onListFragmentInteraction(ProductoEnLista item) {
        Toast.makeText(this, item.getProducto().getDescripcion(), Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(PrincipalActivity.this, DescProductoActivity.class);;
        startActivity(myIntent);
    }

    @Override
    public void onListFragmentInteraction(ListaUsuario item) {
        Toast.makeText(this, item.getNombre(), Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(PrincipalActivity.this, EditListaUsuarioActivity.class);;
        startActivity(myIntent);
    }

    public void habilitarContenidoChangoMenuItem(Boolean enable){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.nav_gallery);
        if(item != null){
            item.setEnabled(enable);
        }
    }
}