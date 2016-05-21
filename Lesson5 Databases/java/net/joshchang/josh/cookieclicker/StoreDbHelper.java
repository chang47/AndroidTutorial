package net.joshchang.josh.cookieclicker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class StoreDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Store.db";

    // constructor to create our object
    public StoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // method that gets called when the database is called for the first time ever in the app
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StoreContract.SQL_CREATE_ENTRIES);

        // creates the initial database entries
        long one = createInitial(db, new StoreItem(0, "Clicker helper", 2, 5, 0));
        long two = createInitial(db, new StoreItem(1, "Clicker mouse", 50, 100, 0));
    }

    // method that we call to help us create our initial entries into our store database
    public long createInitial(SQLiteDatabase db, StoreItem item) {
        // Stores data in a ContentValue object that is used to interact with our table
        ContentValues contentValues = new ContentValues();
        contentValues.put(StoreContract.ID, item.id);
        contentValues.put(StoreContract.NAME, item.name);
        contentValues.put(StoreContract.CLICK, item.click);
        contentValues.put(StoreContract.COST, item.cost);
        contentValues.put(StoreContract.LVL, item.lvl);
        return db.insert(StoreContract.TABLE_NAME, null, contentValues);
    }

    // required for when we're upgrading our database. We would just delete our database
    // and create our new one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(StoreContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    // required method used to upgrade our database
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // updates the entry in our database for whenever the user purchases an upgrade
    public void updateItem(StoreItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Stores data in a ContentValue object that is used to interact with our table
        ContentValues contentValues = new ContentValues();
        contentValues.put(StoreContract.NAME, item.name);
        contentValues.put(StoreContract.CLICK, item.click);
        contentValues.put(StoreContract.COST, item.cost);
        contentValues.put(StoreContract.LVL, item.lvl);

        // updates our specific entry in the database based off of our item Id
        db.update(StoreContract.TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(item.id)});
    }

    // returns a list of StoreItem to represent our upgrades and their current levels
    public ArrayList<StoreItem> getAllItems() {
        ArrayList<StoreItem> list = new ArrayList<StoreItem>();
        SQLiteDatabase db = this.getReadableDatabase();

        // SQL query to grab all columns from our table that returns a cursor object to iterate over
        Cursor res = db.rawQuery( "select * from " + StoreContract.TABLE_NAME, null);

        // set our cursor to the first result
        res.moveToFirst();

        // go through each returned object to create our StoreItem class and add it into a list
        while(!res.isAfterLast()) {
            String id = res.getString(0);
            String name = res.getString(1);
            int click = res.getInt(2);
            int cost = res.getInt(3);
            int lvl = res.getInt(4);
            list.add(new StoreItem(Integer.parseInt(id), name, click, cost, lvl));
            res.moveToNext();
        }
        return list;
    }
}

