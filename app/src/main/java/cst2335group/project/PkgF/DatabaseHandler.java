package cst2335group.project.PkgF;

/**
 * Created by mac on 31/12/2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "foodListManager";

    // Foods table name
    private static final String TABLE_FOOD = "foods";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CAL = "cal";
    private static final String KEY_FAT = "fat";
    private static final String KEY_CARBS = "carbs";
    private static final String KEY_TIME_STAMP = "timeStamp";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_NAME + " TEXT,"
                + KEY_CAL + " REAL,"
                + KEY_FAT + " REAL,"
                + KEY_CARBS + " REAL,"
                + KEY_TIME_STAMP + " TEXT" + ")";

        db.execSQL(CREATE_FOOD_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Food
    void addFoodItem(FoodModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, model.getFoodName());
        values.put(KEY_CAL, model.getCalories());
        values.put(KEY_FAT, model.getTotalFat());
        values.put(KEY_CARBS, model.getTotalCarbs());
        values.put(KEY_TIME_STAMP, model.getTimeStamp());

        // Inserting Row
        db.insert(TABLE_FOOD, null, values);
        db.close(); // Closing database connection
    }


    FoodModel getFoodItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FOOD, new String[]{KEY_ID,
                        KEY_NAME, KEY_CAL, KEY_FAT, KEY_CARBS, KEY_TIME_STAMP}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        FoodModel model = new FoodModel(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getDouble(4)
                , cursor.getString(5));
        return model;
    }

    // Getting All Foods
    public List<FoodModel> getAllFoods() {
        List<FoodModel> foodList = new ArrayList<FoodModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                FoodModel model = new FoodModel();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setFoodName(cursor.getString(1));
                model.setCalories(cursor.getDouble(2));
                model.setTotalFat(cursor.getDouble(3));
                model.setTotalCarbs(cursor.getDouble(4));
                model.setTimeStamp(cursor.getString(5));

                foodList.add(model);
            } while (cursor.moveToNext());
        }
        return foodList;
    }


    public int updateFoodItem(FoodModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, model.getFoodName());
        values.put(KEY_CAL, model.getCalories());
        values.put(KEY_FAT, model.getTotalFat());
        values.put(KEY_CARBS, model.getTotalCarbs());
      //  values.put(KEY_TIME_STAMP, model.getTimeStamp());

        // updating row
        return db.update(TABLE_FOOD, values, KEY_ID + " = ?",
                new String[]{String.valueOf(model.getId())});
    }

    public void deleteFoodItem(FoodModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD, KEY_ID + " = ?",
                new String[]{String.valueOf(model.getId())});
        db.close();
    }

    public int getItemCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

}
