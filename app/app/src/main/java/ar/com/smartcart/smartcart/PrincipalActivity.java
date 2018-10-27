package ar.com.smartcart.smartcart;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ar.com.smartcart.smartcart.communication.AllProductosAsyncTask;
import ar.com.smartcart.smartcart.communication.ContenidoChangoAsyncTask;
import ar.com.smartcart.smartcart.database.DBHelper;
import ar.com.smartcart.smartcart.modelo.Chango;
import ar.com.smartcart.smartcart.modelo.ListaUsuario;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    ContenidoChangoFragment.OnListFragmentInteractionListener,
                    QRScanFragment.OnFragmentInteractionListener,
                    ListaUsuarioFragment.OnListFragmentInteractionListener,
                    EditListaFragment.OnListFragmentInteractionListener,
                    ListaActivaFragment.OnListFragmentInteractionListener {

    public static final String INICIO = "INICIO";
    public static final String QR_SCAN = "QR_SCAN";
    public static final String CONTENIDO_CHANGO = "CONTENIDO_CHANGO";
    public static final String LISTA_ACTIVA = "LISTA_ACTIVA";
    public static final String UBIC_PRODS = "UBIC_PRODS";
    public static final String ADMIN_LISTAS = "ADMIN_LISTAS";
    public static final String EDIT_LISTA = "EDIT_LISTA";
    public static final String DESC_PRODS = "DESC_PRODS";
    public static final String PROMOS = "PROMOS";

    public static final int BUSCADOR = 1;
    public static final int READ_TIME = 3000;
    private Context context;

    private Chango chango;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Borrado de la BD
//        context.deleteDatabase(DBHelper.DATABASE_NAME);

        NavigationView navigationView = findViewById(R.id.nav_view);
        habilitarContenidoChangoMenuItem(Boolean.FALSE);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        //Obtengo mail del usuario logueado de la SM
        SharedPreferences preferences = getSharedPreferences("smartcart", Context.MODE_PRIVATE);
        String email = preferences.getString("USUARIO", "");
        TextView txtEmail = headerView.findViewById(R.id.txt_email);
        txtEmail.setText(email);

        AllProductosAsyncTask task = new AllProductosAsyncTask(){
            @Override
            protected void onPostExecute(final ArrayList<Producto> response) {
                Toast.makeText(PrincipalActivity.this, "Productos actualizados correctamente.", Toast.LENGTH_LONG).show();
            }
        };
        task.execute(context);
        setFragment(INICIO, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_contenido_chango) {
            NavigationView navigationView = findViewById(R.id.nav_view);
            Menu menu = navigationView.getMenu();
            MenuItem contChangoItem = menu.findItem(R.id.nav_contenido_chango);
            if(contChangoItem != null && contChangoItem.isEnabled()){
                setFragment(CONTENIDO_CHANGO, null);
            }else{
                Toast.makeText(this, "Chango no vinculado.", Toast.LENGTH_LONG).show();
            }
            return super.onOptionsItemSelected(item);
        }
        return true;
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.setChecked(true);
        if (id == R.id.nav_qr_scan) {
            setFragment(QR_SCAN, null);
        } else if (id == R.id.nav_contenido_chango) {
            setFragment(CONTENIDO_CHANGO, null);
        } else if (id == R.id.nav_lista_activa) {
            setFragment(LISTA_ACTIVA, null);
        } else if (id == R.id.nav_ubicacion) {
            setFragment(UBIC_PRODS, null);
        } else if (id == R.id.nav_admin_list) {
            setFragment(ADMIN_LISTAS, null);
        } else if (id == R.id.nav_promos) {
            setFragment(PROMOS, null);
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(String position, Object[] params) {
        FragmentManager fragMng = getSupportFragmentManager();
        switch (position) {
            case INICIO:
                InicioFragment iniFrag = new InicioFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, iniFrag,
                                                                            INICIO).commit();
                break;
            case QR_SCAN:
                QRScanFragment qrFrag = new QRScanFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, qrFrag,
                                                                        QR_SCAN).commit();
                break;
            case CONTENIDO_CHANGO:
                ContenidoChangoFragment changoFrag = new ContenidoChangoFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, changoFrag,
                                                                    CONTENIDO_CHANGO).commit();
                break;
            case LISTA_ACTIVA:
                ListaActivaFragment listActFrag = new ListaActivaFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, listActFrag,
                                                                        LISTA_ACTIVA).commit();
                break;
            case ADMIN_LISTAS:
                ListaUsuarioFragment adminListFrag = new ListaUsuarioFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, adminListFrag,
                                                                        ADMIN_LISTAS).commit();
                break;
            case EDIT_LISTA:
                EditListaFragment editListFrag = new EditListaFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, editListFrag,
                                                                        EDIT_LISTA).commit();
                if(params != null) {
                    editListFrag.setLista((ListaUsuario) params[0]);
                }
                break;
            case UBIC_PRODS:
                MapaFragment mapaFrag = new MapaFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, mapaFrag,
                        UBIC_PRODS).commit();
                break;
            case PROMOS:
                PromosFragment promoFrag = new PromosFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, promoFrag,
                        PROMOS).commit();
                break;
            case DESC_PRODS:
                DescProductoFragment descFrag = new DescProductoFragment();
                fragMng.beginTransaction().replace(R.id.fragment_container, descFrag,
                        DESC_PRODS).commit();
                if(params != null) {
                    descFrag.setProducto((Producto) params[0]);
                }
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
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView txtCodChango = headerView.findViewById(R.id.txt_cod_chango);
        txtCodChango.setText(changoCod);
        callContenidoChangoTask(changoID);
        setFragment(CONTENIDO_CHANGO, null);
        habilitarContenidoChangoMenuItem(Boolean.TRUE);
    }

    @Override
    public void onListFragmentInteraction(ProductoEnLista item) {
        setFragment(DESC_PRODS, new Object[] {item.getProducto()});
    }

    @Override
    public void onListFragmentInteraction(ListaUsuario item) {
        setFragment(EDIT_LISTA, new Object[] {item});
    }

    public void habilitarContenidoChangoMenuItem(Boolean enable){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.nav_contenido_chango);
        if(item != null){
            item.setEnabled(enable);
        }
    }
}