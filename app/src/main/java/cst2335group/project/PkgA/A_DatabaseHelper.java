package cst2335group.project.PkgA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xman on 12/15/2017.
 */

public class A_DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Auto.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Auto";
    public static final String COLUMN_ID = "AutoID";
    public static final String COLUMN_LITRES = "Litres";
    public static final String COLUMN_PRICE = "Price";
    public static final String COLUMN_KM = "Kilometers";
    public static final String COLUMN_DATE = "Date";
    public SQLiteDatabase database;



    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_LITRES + " TEXT, " +
            COLUMN_PRICE + " TEXT, " +
            COLUMN_KM + " TEXT, " +
            COLUMN_DATE + " TEXT" +
            ")";

    public A_DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void openDatabase() {
        database = getWritableDatabase();
    }

    public void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    public void insert(String Litres, String Price, String Kilometers, String Date) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_LITRES, Litres);
        values.put(COLUMN_PRICE, Price);
        values.put(COLUMN_KM, Kilometers);
        values.put(COLUMN_DATE, Date);

        database.insert(TABLE_NAME, null, values);

    }

    public void delete(String id) {
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id);
    }

    public void update(String id, String Litres, String Price, String Kilometers, String Date) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_LITRES, Litres);
        values.put(COLUMN_PRICE, Price);
        values.put(COLUMN_KM, Kilometers);
        values.put(COLUMN_DATE, Date);

        database.update(TABLE_NAME, values, COLUMN_ID + " = " + id, null);
    }

    public Cursor read() {
        return database.query(TABLE_NAME, null, null, null, null, null, null);
    }

}
