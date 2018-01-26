package com.example.carina.haushaltsapp.Einkaufsliste;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShoppingMemoDbHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "shopping_list.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_SHOPPING_LIST = "shopping_list";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCT = "product";
    public static final String COLUMN_QUANTITY = "quantity";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_SHOPPING_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PRODUCT + " TEXT NOT NULL, " +
                    COLUMN_QUANTITY + " INTEGER NOT NULL);";

    public ShoppingMemoDbHelper(Context context) {
        //super(context, "PLATZHALTER_DATENBANKNAME", null, 1);
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}