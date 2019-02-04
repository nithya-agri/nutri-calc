package com.example.nutricalc.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CropNutrientDataHelper extends SQLiteOpenHelper {


    private static final String TAG = "CropNutrientDataHelper";

    private static final String DATABASE_NAME = "nutrient.db";
    private static final int DATABASE_VERSION = 1;


    private static final String CROP_TABLE_NAME = "crop";
    private static final String ID_COLUMN_NAME = "id";
    private static final String NAME_COLUMN_NAME = "name";


    public CropNutrientDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + CROP_TABLE_NAME +
                " (" + ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COLUMN_NAME + " TEXT);";
        sqLiteDatabase.execSQL(createTable);
        Log.v(TAG, "added data");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTable = "DROP TABLE IF EXISTS " + CROP_TABLE_NAME + ";";
        sqLiteDatabase.execSQL(dropTable);
        onCreate(sqLiteDatabase);
    }

    public void addCropData() {
        String[] CROPS = {"Rice", "Wheat"};
        SQLiteDatabase db = getWritableDatabase();

        for (String crop : CROPS) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME_COLUMN_NAME, crop);
            db.insert(CROP_TABLE_NAME, null, contentValues);
        }
    }

    public Cursor getCropData() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + CROP_TABLE_NAME + ";";
        return db.rawQuery(query, null);
    }

    public int getCropCount() {
        Cursor crops = getCropData();
        return crops.getCount();
    }


}
