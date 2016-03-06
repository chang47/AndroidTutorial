package net.joshchang.josh.week8;

import android.provider.BaseColumns;

/**
 * Created by JoshDesktop on 3/6/2016.
 */
public class StoreContract implements  BaseColumns {
    public static final String TABLE_NAME = "entry";
    public static final String NAME = "name";
    public static final String CLICK = "click";
    public static final String COST = "cost";
    public static final String LVL = "lvl";
    public static final String ID = "id";


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Store.db";

    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";

    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StoreContract.TABLE_NAME + " (" +
                    StoreContract.ID + " INTEGER PRIMARY KEY, " +
                    StoreContract.NAME + TEXT_TYPE + COMMA_SEP +
                    StoreContract.CLICK + INTEGER_TYPE + COMMA_SEP +
                    StoreContract.COST + INTEGER_TYPE + COMMA_SEP +
                    StoreContract.LVL + INTEGER_TYPE +
                    " )";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StoreContract.TABLE_NAME;


}
