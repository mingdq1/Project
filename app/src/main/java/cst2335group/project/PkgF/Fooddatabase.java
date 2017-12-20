package cst2335group.project.PkgF;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mingd on 12/17/2017.
 */

public class Fooddatabase extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "FOOD";
    private static final String DATABASE_NAME = "FOOD.db";
    private static final int VERSION_NUM = 1;
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_CALORIES = "CALORIES";
    private static final String COLUMN_FAT = "FAT";
    private static final String COLUMN_CARBOHYDRATE = "CARBOHYDRATE";
    private static final String COLUMN_DATE = "DATE";
    public SQLiteDatabase database;

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_CALORIES + " REAL, " +
            COLUMN_FAT + " REAL, " +
            COLUMN_CARBOHYDRATE + " REAL, " +
            COLUMN_DATE + " TEXT" +
            ")";

    public Fooddatabase(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Fooddatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destory all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(Fooddatabase.class.getName(), "Downgrading database from version " + newVersion
                + " to " + oldVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void openDatabase() {
        database = getWritableDatabase();
    }


    public void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    //Adding new food
    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, food.getName());
        values.put(COLUMN_CALORIES, food.getCalories());
        values.put(COLUMN_FAT, food.getFat());
        values.put(COLUMN_CARBOHYDRATE, food.getCarbohydrate());
        values.put(COLUMN_DATE, food.getDate().toString());

        database.insert(TABLE_NAME, null, values);
    }

    public void updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, food.getName());
        values.put(COLUMN_CALORIES, food.getCalories());
        values.put(COLUMN_FAT, food.getFat());
        values.put(COLUMN_CARBOHYDRATE, food.getCarbohydrate());
        values.put(COLUMN_DATE, food.getDate().toString());

        database.update(TABLE_NAME, values, COLUMN_ID + " = " + food.getId(), null);
    }

    public void delete(Food food) {
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + food.getId());
    }

    public Cursor read() {
        return database.query(TABLE_NAME, null, null, null, null, null, null);
    }
}



