package net.joshchang.josh.cookieclicker;

import android.provider.BaseColumns;

public class StoreContract implements BaseColumns {
    // name of our table
    public static final String TABLE_NAME = "shop";

    // hardcoded name of our table variable names
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CLICK = "click";
    public static final String COST = "cost";
    public static final String LVL = "lvl";

    // information required for our database class
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Store.db";

    // hard coded SQL queries that we'll be using
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    // hard coded string for creating our SQL table
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StoreContract.TABLE_NAME + " (" +
                    StoreContract.ID + " INTEGER PRIMARY KEY, " +
                    StoreContract.NAME + TEXT_TYPE + COMMA_SEP +
                    StoreContract.CLICK + INTEGER_TYPE + COMMA_SEP +
                    StoreContract.COST + INTEGER_TYPE + COMMA_SEP +
                    StoreContract.LVL + INTEGER_TYPE +
                    " )";

    // hard coded string for deleting our table
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StoreContract.TABLE_NAME;
}
