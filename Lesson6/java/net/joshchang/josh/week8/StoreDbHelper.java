package net.joshchang.josh.week8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by JoshDesktop on 3/6/2016.
 */
public class StoreDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Store.db";

    public StoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StoreContract.SQL_CREATE_ENTRIES);
        long one = createInitial(db, new StoreItem(0, "Clicker helper", 2, 5, 0));
        long two = createInitial(db, new StoreItem(1, "Clicker mouse", 4, 20, 0));

        Log.d("thenewarray", "insert1: " + one + " insert2: " + two);
    }

    public long createInitial(SQLiteDatabase db, StoreItem item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StoreContract.ID, item.id);
        contentValues.put(StoreContract.NAME, item.name);
        contentValues.put(StoreContract.CLICK, item.click);
        contentValues.put(StoreContract.COST, item.cost);
        contentValues.put(StoreContract.LVL, item.lvl);
        return db.insert(StoreContract.TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(StoreContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertItem(StoreItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StoreContract.ID, item.id);
        contentValues.put(StoreContract.NAME, item.name);
        contentValues.put(StoreContract.CLICK, item.click);
        contentValues.put(StoreContract.COST, item.cost);
        contentValues.put(StoreContract.LVL, item.lvl);
        db.insert(StoreContract.TABLE_NAME, null, contentValues);
    }

    public void updateItem(StoreItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StoreContract.NAME, item.name);
        contentValues.put(StoreContract.CLICK, item.click);
        contentValues.put(StoreContract.COST, item.cost);
        contentValues.put(StoreContract.LVL, item.lvl);
        db.update(StoreContract.TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(item.id)});
    }

    public ArrayList<StoreItem> getAllItems() {
        ArrayList<StoreItem> list = new ArrayList<StoreItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from " + StoreContract.TABLE_NAME, null);
        Log.d("thenewarray", "res got back is: " + res.getCount());
        res.moveToFirst();
        while(!res.isAfterLast()) {
            Log.d("thenewarray", "inside the res table column: " + res.getColumnCount());
            String id = res.getString(0);
            Log.d("thenewarray", "id got is: " + id);
            String name = res.getString(1);
            int click = res.getInt(2);
            int cost = res.getInt(3);
            int lvl = res.getInt(4);
            list.add(new StoreItem(Integer.parseInt(id), name, click, cost, lvl));
            res.moveToNext();
        }
        Log.d("thenewarray", "size of get back is: " + list.size());
        return list;
    }
}

