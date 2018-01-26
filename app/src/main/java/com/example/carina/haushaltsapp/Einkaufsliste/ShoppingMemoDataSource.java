package com.example.carina.haushaltsapp.Einkaufsliste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ShoppingMemoDataSource {

    private SQLiteDatabase database;
    private ShoppingMemoDbHelper dbHelper;

    private String[] columns = {
            ShoppingMemoDbHelper.COLUMN_ID,
            ShoppingMemoDbHelper.COLUMN_PRODUCT,
            ShoppingMemoDbHelper.COLUMN_QUANTITY
    };

    public ShoppingMemoDataSource(Context context) {

        // DataSource erzeugt den dbHelper
        dbHelper = new ShoppingMemoDbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        // Datenbank mit Hilfe des DbHelpers geschlossen
        dbHelper.close();
    }

    public ShoppingMemo createShoppingMemo(String product, int quantity) {
        ContentValues values = new ContentValues();
        values.put(ShoppingMemoDbHelper.COLUMN_PRODUCT, product);
        values.put(ShoppingMemoDbHelper.COLUMN_QUANTITY, quantity);

        long insertId = database.insert(ShoppingMemoDbHelper.TABLE_SHOPPING_LIST, null, values);

        Cursor cursor = database.query(ShoppingMemoDbHelper.TABLE_SHOPPING_LIST,
                columns, ShoppingMemoDbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        ShoppingMemo shoppingMemo = cursorToShoppingMemo(cursor);
        cursor.close();

        return shoppingMemo;
    }

    private ShoppingMemo cursorToShoppingMemo(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(ShoppingMemoDbHelper.COLUMN_ID);
        int idProduct = cursor.getColumnIndex(ShoppingMemoDbHelper.COLUMN_PRODUCT);
        int idQuantity = cursor.getColumnIndex(ShoppingMemoDbHelper.COLUMN_QUANTITY);

        String product = cursor.getString(idProduct);
        int quantity = cursor.getInt(idQuantity);
        long id = cursor.getLong(idIndex);

        ShoppingMemo shoppingMemo = new ShoppingMemo(product, quantity, id);

        return shoppingMemo;
    }

    public void deleteShoppingMemo(ShoppingMemo shoppingMemo) {
        long id = shoppingMemo.getId();

        database.delete(ShoppingMemoDbHelper.TABLE_SHOPPING_LIST,
                ShoppingMemoDbHelper.COLUMN_ID + "=" + id,
                null);
    }

    public ShoppingMemo updateShoppingMemo(long id, String newProduct, int newQuantity) {
        ContentValues values = new ContentValues();
        values.put(ShoppingMemoDbHelper.COLUMN_PRODUCT, newProduct);
        values.put(ShoppingMemoDbHelper.COLUMN_QUANTITY, newQuantity);

        database.update(ShoppingMemoDbHelper.TABLE_SHOPPING_LIST,
                values,
                ShoppingMemoDbHelper.COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(ShoppingMemoDbHelper.TABLE_SHOPPING_LIST,
                columns, ShoppingMemoDbHelper.COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        ShoppingMemo shoppingMemo = cursorToShoppingMemo(cursor);
        cursor.close();

        return shoppingMemo;
    }

    public List<ShoppingMemo> getAllShoppingMemos() {
        List<ShoppingMemo> shoppingMemoList = new ArrayList<>();

        Cursor cursor = database.query(ShoppingMemoDbHelper.TABLE_SHOPPING_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        ShoppingMemo shoppingMemo;

        while(!cursor.isAfterLast()) {
            shoppingMemo = cursorToShoppingMemo(cursor);
            shoppingMemoList.add(shoppingMemo);
            cursor.moveToNext();
        }

        cursor.close();

        return shoppingMemoList;
    }
}
