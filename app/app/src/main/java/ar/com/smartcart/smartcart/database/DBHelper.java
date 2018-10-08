package ar.com.smartcart.smartcart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.smartcart.smartcart.modelo.Categoria;
import ar.com.smartcart.smartcart.modelo.ListaUsuario;
import ar.com.smartcart.smartcart.modelo.Producto;
import ar.com.smartcart.smartcart.presentacion.ProductoEnLista;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance = null;
    public static final String DATABASE_NAME = "smartCart.db";
    public static final String COL_ID = "ID";
    public static final String COL_NOMBRE = "NOMBRE";

    public static final String TBL_LISTA_USUTARIO = "LISTA_USUTARIO";
    public static final String COL_ACTIVA = "ACTIVA";

    public static final String TBL_PRODUCTO_LISTA = "PRODUCTO_LISTA";
    public static final String COL_PRODUCTO_ID = "PRODUCTO_ID";
    public static final String COL_LISTA_USUARIO_ID = "LISTA_USUARIO_ID";
    public static final String COL_CANTIDAD = "CANTIDAD";
    public static final String COL_EN_CHANGO = "EN_CHANGO";

    public static final String TBL_PRODUCTO = "PRODUCTO";
    public static final String COL_DESC = "DESCRIPCION";
    public static final String COL_PRECIO = "PRECIO";
    public static final String COL_CELIACOS = "APTO_CELIACOS";
    public static final String COL_DIABETICOS = "APTO_DIABETICOS";
    public static final String COL_URL = "URL";
    public static final String COL_CATEGORIA_ID = "CATEGORIA_ID";

    public static final String TBL_CATEGORIA = "CATEGORIA";
    public static final String COL_POS_X = "POS_X";
    public static final String COL_POS_Y = "POS_Y";

    //Singleton Pattern
    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //These is where we need to write create table statements. This is called when database is created.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_LISTA_USUTARIO + "(" +
                COL_ID + " NUMBER, " +
                COL_NOMBRE + " TEXT, " +
                COL_CANTIDAD + " NUMBER, " +
                COL_ACTIVA + " BOOLEAN);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_PRODUCTO + "(" +
                COL_ID + " NUMBER, " +
                COL_NOMBRE + " TEXT, " +
                COL_DESC + " TEXT, " +
                COL_PRECIO + " NUMBER, " +
                COL_CELIACOS + " BOOLEAN, " +
                COL_DIABETICOS + " BOOLEAN, " +
                COL_URL + " TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_PRODUCTO_LISTA + "(" +
                COL_PRODUCTO_ID + " NUMBER, " +
                COL_LISTA_USUARIO_ID + " NUMBER, " +
                COL_EN_CHANGO + " BOOLEAN, " +
                COL_CANTIDAD + " NUMBER);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_CATEGORIA + "(" +
                COL_ID + " NUMBER, " +
                COL_NOMBRE + " TEXT, " +
                COL_POS_X + " NUMBER, " +
                COL_POS_Y + " NUMBER);");
    }

    //This method is called when database is upgraded like modifying the table structure,
    // adding constraints to database, etc.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_LISTA_USUTARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_PRODUCTO);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_PRODUCTO_LISTA);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_CATEGORIA);
        onCreate(db);
    }

    //Inserts
    public Boolean insertProducto(Producto prod)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, prod.getId());
        contentValues.put(COL_NOMBRE, prod.getNombre());
        contentValues.put(COL_DESC, prod.getDescripcion());
        contentValues.put(COL_PRECIO, prod.getPrecio().floatValue());
        contentValues.put(COL_CELIACOS, prod.getApto_celiacos());
        contentValues.put(COL_DIABETICOS, prod.getApto_diabeticos());
        contentValues.put(COL_URL, prod.getUrl());
        contentValues.put(COL_CATEGORIA_ID, prod.getCategoria().getId());
        db.insert(TBL_PRODUCTO, null, contentValues);
        return true;
    }

    public Boolean insertarProductos(ArrayList<Producto> productos){
        Boolean result = Boolean.FALSE;
        for (Producto prod : productos) {
            result = insertProducto(prod);
        }
        return result;
    }

    public Boolean insertCategoria(Categoria cat)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, cat.getId());
        contentValues.put(COL_NOMBRE, cat.getNombre());
        contentValues.put(COL_POS_X, cat.getPosicion_x().floatValue());
        contentValues.put(COL_POS_Y, cat.getPosicion_y().floatValue());
        db.insert(TBL_CATEGORIA, null, contentValues);
        return true;
    }

    public Boolean insertarListaUsuario(ListaUsuario lista){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, lista.getId());
        contentValues.put(COL_NOMBRE, lista.getNombre());
        contentValues.put(COL_ACTIVA, lista.getActiva());
        long result = db.insert(TBL_LISTA_USUTARIO, null, contentValues);
        if(result == 1L && !lista.getProductos().isEmpty()){
            insertarProductosEnLista(lista);
        }
        return true;
    }

    public Boolean insertarProductosEnLista(ListaUsuario lista){
        SQLiteDatabase db = this.getWritableDatabase();
        for(ProductoEnLista prod : lista.getProductos()){
            ContentValues contentValues = new ContentValues();
            contentValues = new ContentValues();
            contentValues.put(COL_PRODUCTO_ID, prod.getProducto().getId());
            contentValues.put(COL_LISTA_USUARIO_ID, prod.getCantidad());
            contentValues.put(COL_EN_CHANGO, prod.getEnChango());
            contentValues.put(COL_CANTIDAD, prod.getCantidad());
            db.insert(TBL_PRODUCTO_LISTA, null, contentValues);
        }
        return true;
    }

    //Deletes
    public Boolean borrarProducto (Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(TBL_PRODUCTO, COL_ID + " = ? ", new String[] { id.toString()});
        if(result == 1){
            return true;
        }
        return false;
    }

    public Boolean borrarAllProductos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(TBL_PRODUCTO, null, null);
        return true;
    }

    public Boolean borrarCategoria (Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(TBL_CATEGORIA, COL_ID + " = ? ", new String[] { id.toString()});
        if(result == 1){
            return true;
        }
        return false;
    }

    public Boolean borrarListaUsuario (Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(TBL_LISTA_USUTARIO, COL_ID + " = ? ", new String[] { id.toString()});
        if(result == 1){
            return borrarProductosLista(id);
        }
        return false;
    }

    public Boolean borrarProductosLista (Long idLista){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(TBL_PRODUCTO_LISTA,
                COL_LISTA_USUARIO_ID + " = ? ", new String[] { idLista.toString()});
        if(result == 1){
            return true;
        }
        return false;
    }

    //Getters
    public Producto getProducto(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Producto prod = new Producto();
        Cursor res =  db.rawQuery( " SELECT * " +
                                   " FROM " + TBL_PRODUCTO +
                                   " WHERE " + COL_ID + " = " + id, null );
        res.moveToFirst();
        if(res.isAfterLast() == Boolean.FALSE){
          fillProducto(prod, res);
        }
        return prod;
    }

    public void fillProducto(Producto prod, Cursor res){
        prod.setId(res.getLong(res.getColumnIndex(COL_ID)));
        prod.setNombre(res.getString(res.getColumnIndex(COL_NOMBRE)));
        prod.setDescripcion(res.getString(res.getColumnIndex(COL_DESC)));
        prod.setPrecio(new BigDecimal(res.getFloat(res.getColumnIndex(COL_PRECIO))));
        prod.setUrl(res.getString(res.getColumnIndex(COL_URL)));
        prod.setApto_celiacos(res.getLong(res.getColumnIndex(COL_CELIACOS)) == 1L
                ? Boolean.TRUE : Boolean.FALSE);
        prod.setApto_celiacos(res.getLong(res.getColumnIndex(COL_CELIACOS)) == 1L
                ? Boolean.TRUE : Boolean.FALSE);
    }

    public ArrayList<Producto> getAllProductos(){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( " SELECT * "
                                + " FROM " + TBL_PRODUCTO, null );
        res.moveToFirst();
        while(res.isAfterLast() == Boolean.FALSE){
            Producto prod = new Producto();
            fillProducto(prod, res);
            productos.add(prod);
            res.moveToNext();
        }
        return productos;
    }

    public List<Producto> getProductosLikeNombre(String nombre){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( " SELECT * " +
                                   " FROM " + TBL_PRODUCTO +
                                   " WHERE " + COL_NOMBRE +
                                        " LIKE '" + nombre + "'", null );
        res.moveToFirst();
        while(res.isAfterLast() == Boolean.FALSE){
            Producto prod = new Producto();
            fillProducto(prod, res);
            productos.add(prod);
            res.moveToNext();
        }
        return productos;
    }

    public ListaUsuario getPlainListaUsuario(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        ListaUsuario lista = new ListaUsuario();
        Cursor res =  db.rawQuery( " SELECT * " +
                " FROM " + TBL_LISTA_USUTARIO +
                " WHERE " + COL_ID + " = " + id, null );
        res.moveToFirst();
        if(res.isAfterLast() == Boolean.FALSE){
           fillPlainListaUsuario(lista, res);
        }
        return lista;
    }

    public ArrayList<ListaUsuario> getAllPlainListaUsuario(){
        ArrayList<ListaUsuario> listas = new ArrayList<ListaUsuario>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( " SELECT * "
                + " FROM " + TBL_LISTA_USUTARIO, null );
        res.moveToFirst();
        while(res.isAfterLast() == Boolean.FALSE){
            ListaUsuario lista = new ListaUsuario();
            fillPlainListaUsuario(lista, res);
            listas.add(lista);
            res.moveToNext();
        }
        return listas;
    }

    public void fillPlainListaUsuario(ListaUsuario lista, Cursor res){
        lista.setId(res.getLong(res.getColumnIndex(COL_ID)));
        lista.setNombre(res.getString(res.getColumnIndex(COL_NOMBRE)));
        lista.setActiva(res.getLong(res.getColumnIndex(COL_ACTIVA)) == 1L
                ? Boolean.TRUE : Boolean.FALSE);
    }

    public ListaUsuario getListaUsuario(Long id){
        ListaUsuario lista = getPlainListaUsuario(id);
        if(lista != null){
            lista.setProductos(getProductosEnLista(id));
        }
        return lista;
    }

    public ArrayList<ProductoEnLista> getProductosEnLista(Long idLista){
        ArrayList<ProductoEnLista> productos = new ArrayList<ProductoEnLista>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( " SELECT * " +
                                " FROM " + TBL_PRODUCTO_LISTA + " PL " +
                                    " JOIN " + TBL_PRODUCTO + " P " +
                                        " ON (PL." + COL_PRODUCTO_ID + " = " +
                                              "P." + COL_ID + ")" +
                                " WHERE " + COL_LISTA_USUARIO_ID + " = " + idLista, null );
        res.moveToFirst();
        while(res.isAfterLast() == Boolean.FALSE){
            ProductoEnLista prod = new ProductoEnLista();
            fillProductoEnLista(prod, res);
            productos.add(prod);
            res.moveToNext();
        }
        return productos;
    }

    public Integer getCantAllProductos(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TBL_PRODUCTO);
        return numRows;
    }

    public void fillProductoEnLista(ProductoEnLista prod, Cursor res){
        Producto p = new Producto();
        fillProducto(p, res);
        prod.setCantidad(res.getLong(res.getColumnIndex(COL_CANTIDAD)));
        prod.setEnChango(res.getLong(res.getColumnIndex(COL_EN_CHANGO)) == 1L
                ? Boolean.TRUE : Boolean.FALSE);
    }

    //Updates
    public Boolean actualizarPlainListaUsuario(ListaUsuario lista){
        ListaUsuario listaDB = getPlainListaUsuario(lista.getId());
        if(listaDB.getId() == null){
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOMBRE, lista.getNombre());
        contentValues.put(COL_ACTIVA, lista.getActiva());
        Integer result = db.update(TBL_LISTA_USUTARIO, contentValues,
                            COL_ID + " = ? ", new String[] {lista.getId().toString()});
        if(result == 1){
            return true;
        }
        return false;
    }

    public Boolean activarListaUsuario(Long id){
        Boolean result = Boolean.FALSE;
        ListaUsuario listaDB = getPlainListaUsuario(id);
        if(listaDB.getId() == null){
            return false;
        }
        listaDB.setActiva(Boolean.TRUE);
        result = actualizarPlainListaUsuario(listaDB);
        if(result){
            result = desactivarListas(id);
        }
        return result;
    }

    public Boolean desactivarListas(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ACTIVA, Boolean.FALSE);
        Integer result = db.update(TBL_LISTA_USUTARIO, contentValues,
                COL_ID + " <> ? ", new String[] {id.toString()});
        if(result == 1){
            return true;
        }
        return false;
    }

    public Boolean actualizarProdsListaUsuario(ListaUsuario lista){
        if(getPlainListaUsuario(lista.getId()).getId() == null){
            return false;
        }
        Boolean result = borrarProductosLista(lista.getId());
        if(result){
            return insertarProductosEnLista(lista);
        }
        return false;
    }

    public Boolean actualizarProductos(ArrayList<Producto> productos){
        Boolean result = borrarAllProductos();
        if(result){
            return insertarProductos(productos);
        }
        return false;
    }
}