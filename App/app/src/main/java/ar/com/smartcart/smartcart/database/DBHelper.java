package ar.com.smartcart.smartcart.database;

/**
 * Created by Munoz on 10/05/2015.
 */
public class DBHelper { //extends SQLiteOpenHelper {
    private static DBHelper instance = null;
    public static final String DATABASE_NAME = "smartCart.db";
    public static final String TABLE_NAME = "PRODUCT";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_CONTENT = "CONTENT";
    public static final String COL_X = "X";
    public static final String COL_Y = "Y";
    public static final String COL_Z = "Z";

//    //Singleton Pattern
//    public static DBHelper getInstance(Context context) {
//        if (instance == null) {
//            instance = new DBHelper(context);
//        }
//        return instance;
//    }
//
//    private DBHelper(Context context) {
//        super(context, DATABASE_NAME, null, 1);
//    }
//
//    //These is where we need to write create table statements. This is called when database is created.
//    @Override
//    public void onCreate(SQLiteDatabase db) {
////        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
////                COL_TITLE + " TEXT, " +
////                COL_CONTENT + " TEXT, " +
////                COL_X + " NUMBER, " +
////                COL_Y + " NUMBER, " +
////                COL_Z + " NUMBER);");
//    }
//
//    //This method is called when database is upgraded like modifying the table structure,
//    // adding constraints to database, etc.
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//
//    public boolean insertNote (Note note)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_TITLE, note.getTitle());
//        contentValues.put(COL_CONTENT, note.getContent());
//        contentValues.put(COL_X, note.getX());
//        contentValues.put(COL_Y, note.getY());
//        contentValues.put(COL_Z, note.getZ());
//
//        db.insert(TABLE_NAME, null, contentValues);
//        return true;
//    }
//
//    public boolean insertNoteWithoutDuplicate (Note note)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_TITLE, note.getTitle());
//        contentValues.put(COL_CONTENT, note.getContent());
//        contentValues.put(COL_X, note.getX());
//        contentValues.put(COL_Y, note.getY());
//        contentValues.put(COL_Z, note.getZ());
//
//        if(this.existNote(note.getTitle())){
//            this.deleteNote(note.getTitle());
//        }
//        db.insert(TABLE_NAME, null, contentValues);
//        return true;
//    }
//
//    //Note ID is the Title
//    public Note getNote(String title){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Note note = new Note();
//        Cursor res =  db.rawQuery( "SELECT * " +
//                                   "FROM " + TABLE_NAME +
//                                   " WHERE " + COL_TITLE + " = '" + title + "'", null );
//        res.moveToFirst();
//
//        if(res.isAfterLast() == Boolean.FALSE){
//            note.setTitle(res.getString(res.getColumnIndex(COL_TITLE)));
//            note.setContent(res.getString(res.getColumnIndex(COL_CONTENT)));
//            note.setX(res.getInt(res.getColumnIndex(COL_X)));
//            note.setY(res.getInt(res.getColumnIndex(COL_Y)));
//            note.setZ(res.getInt(res.getColumnIndex(COL_Z)));
//        }
//        return note;
//    }
//    public boolean validateKey(Note note){
//        Note noteOrig = getNote(note.getTitle());
//
//        return noteOrig.getX().equals(note.getX()) && noteOrig.getY().equals(note.getY()) && noteOrig.getZ().equals(note.getZ());
//    }
//    public Boolean existNote(String tittle){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * " +
//                "FROM " + TABLE_NAME +
//                " WHERE " + COL_TITLE + " = '" + tittle + "'";
//        Cursor res =  db.rawQuery( query, null );
//        return res.moveToFirst();
//    }
//
//    public Boolean updateNote (String oldTittle,Note note)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_TITLE, note.getTitle());
//        contentValues.put(COL_CONTENT, note.getContent());
//        contentValues.put(COL_X, note.getX());
//        contentValues.put(COL_Y, note.getY());
//        contentValues.put(COL_Z, note.getZ());
//        db.update(TABLE_NAME, contentValues, COL_TITLE + " = ? ", new String[] {oldTittle});
//        return Boolean.TRUE;
//    }
//
//    public Boolean updateNoteWithoutEncrypt (String oldTittle,Note note)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_TITLE, note.getTitle());
//        contentValues.put(COL_CONTENT, note.getContent());
//        db.update(TABLE_NAME, contentValues, COL_TITLE + " = ? ", new String[] {oldTittle});
//        return Boolean.TRUE;
//    }
//
//    public Integer deleteNote (String title)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, COL_TITLE + " = ? ", new String[] { title });
//    }
//
//    public Integer numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
//        return numRows;
//    }
//
//    public ArrayList<Note> getAllNotes()
//    {
//        ArrayList<Note> notes = new ArrayList<Note>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "SELECT * FROM " + TABLE_NAME, null );
//        res.moveToFirst();
//        while(res.isAfterLast() == Boolean.FALSE){
//            Note note = new Note();
//            note.setTitle(res.getString(res.getColumnIndex(COL_TITLE)));
//            note.setContent(res.getString(res.getColumnIndex(COL_CONTENT)));
//            note.setX((res.getColumnIndex(COL_X)));
//            note.setY((res.getColumnIndex(COL_Y)));
//            note.setZ((res.getColumnIndex(COL_Z)));
//
//            notes.add(note);
//
//            res.moveToNext();
//        }
//        return notes;
//    }
}